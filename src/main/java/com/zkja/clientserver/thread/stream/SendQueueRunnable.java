package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.SocketQueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @authon zzr
 */
public class SendQueueRunnable implements Runnable {
    Logger logger = LoggerFactory.getLogger(SendQueueRunnable.class);
    private volatile QueueManager queueManager;
    private volatile SocketManager socketManager;

    public SendQueueRunnable(QueueManager queueManager, SocketManager socketManager) {
        this.queueManager = queueManager;
        this.socketManager = socketManager;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String,List> map = ((SocketQueueManager)queueManager).getImeiMap();
            if(map.size()>0){
                for (Map.Entry m : map.entrySet()) {
                    SelectionKey selectionKey = socketManager.getMap().get(m.getKey());
                    selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
                    logger.info("readOps: "+ selectionKey.readyOps() + "  interestOps: "+selectionKey.interestOps());
                }
            }
        }
    }
}
