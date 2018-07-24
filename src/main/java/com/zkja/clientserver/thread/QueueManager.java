package com.zkja.clientserver.thread;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @authon zzr
 */

public class QueueManager {
    LinkedBlockingQueue<TcpReq> sendSmuQueue = new LinkedBlockingQueue<TcpReq>();
    LinkedBlockingQueue<TcpRes> sendSmcQueue = new LinkedBlockingQueue<TcpRes>();
    Logger logger = Logger.getLogger(QueueManager.class);
    public boolean addSmuQueue(TcpReq tcpReq){
        return sendSmuQueue.offer(tcpReq);
    }
    public boolean addSmcQueue(TcpRes tcpRes){
        logger.debug("sendSmcQueue: "+ tcpRes);
        return sendSmcQueue.offer(tcpRes);
    }

    public TcpReq pollSmuQueue(){
        TcpReq tcpReq= sendSmuQueue.poll();
        return tcpReq;

    }
    public TcpRes pollSmcQueue(){
        TcpRes tcpRes = sendSmcQueue.poll();
        return tcpRes;

    }


}
