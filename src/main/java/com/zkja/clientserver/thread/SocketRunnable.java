package com.zkja.clientserver.thread;


import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.thread.socket.SocketManager;
import com.zkja.clientserver.thread.stream.InputRunnable;

import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @authon zzr
 */
public class SocketRunnable implements Runnable {

    /**
     * 存储socket 以imei 为键值
     */
    private String imei = null;

//    volatile private Map<String,Socket> map;
    private Socket socket;
    private Lock lock;
    private volatile QueueManager queueManager;
    private volatile SocketManager socketManager;

    public SocketRunnable(Lock lock, QueueManager queueManager, SocketManager socketManager){
        this.lock = lock;
        this.queueManager = queueManager;
        this.socketManager = socketManager;
    }

    @Override
    public void run() {
        Runnable inR = new InputRunnable(queueManager,socket,socketManager,imei);
        new Thread(inR).start();
        Map map = socketManager.getMap();
        while(true){
            //等待登录
            if(imei != null){
                if(map.get(imei)==null){
                    map.put(imei,socket);
                }
                List<TcpReq> list = ((SocketQueueManager)queueManager).getImeiList(imei);
                socketManager.putStrData(socket, list);
            }
        }
    }

    public void setSocket(Socket socket) {
        lock.lock();
        this.socket = socket;
        lock.unlock();
    }
}
