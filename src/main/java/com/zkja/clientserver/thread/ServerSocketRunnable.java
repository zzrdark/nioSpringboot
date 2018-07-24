package com.zkja.clientserver.thread;

import com.zkja.clientserver.property.ServerSocketProperties;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzr
 */
public class ServerSocketRunnable implements Runnable{

    private ServerSocketProperties serverSocketProperties;

    Logger logger = Logger.getLogger(ServerSocketRunnable.class);

    private volatile QueueManager queueManager;

    private ThreadPoolTaskExecutor taskExecutor;

    private volatile SocketManager socketManager;
    
    
    
    public ServerSocketRunnable(ServerSocketProperties serverSocketProperties,QueueManager queueManager,
			ThreadPoolTaskExecutor taskExecutor, SocketManager socketManager) {
		this.serverSocketProperties = serverSocketProperties;
		this.queueManager = queueManager;
		this.taskExecutor = taskExecutor;
		this.socketManager = socketManager;
	}



	@Override
    public void run() {
        while (true){
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(Integer.valueOf(serverSocketProperties.getPort()));

                //Map<String,Socket> synMap = Collections.synchronizedMap(new HashMap<String,Socket>());
                Map<String,Socket> synMap = new ConcurrentHashMap<String,Socket>(1000);
                //socketManager.setMap(synMap);
                Lock lock = new ReentrantLock();
                SocketRunnable socketRunnable = new SocketRunnable(lock, queueManager, socketManager);
                while (true) {
                    Socket socket = serverSocket.accept();
                    lock.lock();
                    socketRunnable.setSocket(socket);
                    taskExecutor.execute(socketRunnable);
                    lock.unlock();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                logger.error("serverSocket关闭了");
                taskExecutor.shutdown();
                try {
                    if(!serverSocket.isClosed()){
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
