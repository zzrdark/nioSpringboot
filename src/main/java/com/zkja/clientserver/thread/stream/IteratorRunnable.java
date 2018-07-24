package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.SocketQueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/**
 * @author zzr
 */
public class IteratorRunnable implements Runnable {

    private Logger logger = LoggerFactory.getLogger(IteratorRunnable.class);
//    private  Lock lock;
    private  Iterator iterator;
    private  SocketManager socketManager;
    private  QueueManager queueManager;
    private ByteBuffer recBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public IteratorRunnable(Iterator iterator, SocketManager socketManager, QueueManager queueManager) {
        this.iterator = iterator;
        this.socketManager = socketManager;
        this.queueManager = queueManager;
    }

    @Override
    public void run()  {
        SelectionKey selectionKey = null;
        try {
            while (iterator.hasNext()) {
                //这里存在线程安全问题
                /*lock.lock();
                if (!iterator.hasNext()) {
                    return;
                }*/
                selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                /*lock.unlock();*/

                if (selectionKey.isValid() && selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    logger.debug("收到一条");
                    //返回false表示没取到数据
                    Boolean statusRead = socketManager.sendSmc(recBuffer, socketChannel, queueManager, selectionKey);
                    if( socketManager.getMap().get((String)selectionKey.attachment())==null){
                        socketManager.getMap().put((String)selectionKey.attachment(),selectionKey);
                    }
                }

                if (selectionKey.isValid() && selectionKey.isWritable() && selectionKey.attachment() != null) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    String imei = (String) selectionKey.attachment();

                    List<TcpReq> list = ((SocketQueueManager) queueManager).getImeiList(imei);
                    if(list == null){
                        selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
                    }else{
                        logger.debug("开始向imei:"+imei+"发送消息"+list.size());
                        socketManager.sendSmu(sendBuffer, socketChannel, list);
                        selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_READ);
                    }

                }
            }
        } catch (IOException e) {
            logger.info("selectionKey已被channel");
            selectionKey.channel();
            e.printStackTrace();
        }
    }
}
