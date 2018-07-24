package com.zkja.clientserver.thread;


import com.zkja.clientserver.thread.socket.SocketManager;
import com.zkja.clientserver.thread.stream.IteratorRunnable;
import com.zkja.clientserver.thread.stream.SendQueueRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @authon zzr
 */
public class NioSocketRunnable implements Runnable {

    /**
     * 存储socket 以imei 为键值
     * 已经将imei 存到了 SelectionKey特殊对象里了
     */
    //private String imei = null;

    private Logger logger = LoggerFactory.getLogger(NioSocketRunnable.class);

    private Selector selector;
    private volatile QueueManager queueManager;
    private volatile SocketManager socketManager;
    private volatile ThreadPoolTaskExecutor taskExecutor;
//    private Lock lock = new ReentrantLock();

    private ByteBuffer recBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public NioSocketRunnable(QueueManager queueManager, SocketManager socketManager, ThreadPoolTaskExecutor taskExecutor) throws IOException {
        this.queueManager = queueManager;
        this.socketManager = socketManager;
        this.selector = Selector.open();
        this.taskExecutor = taskExecutor;
        logger.info("初始化NioSocketRunnable");
    }


    @Override
    public void run() {
        try {
            SendQueueRunnable sendQueueRunnable = new SendQueueRunnable(queueManager, socketManager);
            taskExecutor.execute(sendQueueRunnable);
            while (true){
                if(selector.select(10)>0){
                    Set selectionKeys = selector.selectedKeys();
                    Iterator iterator = selectionKeys.iterator();
                    IteratorRunnable iteratorRunnable = new IteratorRunnable(iterator, socketManager, queueManager);
                    iteratorRunnable.run();
                    //适用于高并发测试的时候适用
                    /*Integer threadCount = selectionKeys.size()/100;
                    for (int i = 0;i<threadCount;i++){
                        logger.info("开启"+threadCount+"个线程处理事件");
                        taskExecutor.execute(iteratorRunnable);
                    }*/
                    //适用于低连接测试的时候适用
                    //taskExecutor.execute(iteratorRunnable);
                    //对smuQueue队列进行增加 事件

                    /*Condition condition = lock.newCondition();
                    logger.info("NioSocketRunnable线程开始等待");
                    while(selector.select(100)>0){
                        lock.lock();
                        //等待await不消耗Cpu资源
                        condition.awaitNanos(1000);
                        lock.unlock();
                    }
                    logger.info("NioSocketRunnable线程结束等待");*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                logger.info("selector关闭");
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void channelRegister(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
    }

}
