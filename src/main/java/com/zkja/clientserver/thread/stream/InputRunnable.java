package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;

import java.net.Socket;

/**
 * 这里是bio调用的  已废弃
 * @author zzr
 */
public class InputRunnable implements Runnable {

    private volatile QueueManager queueManager;

    private Socket socket;

    private volatile SocketManager socketManager;

    private volatile  String imei;


    public InputRunnable(QueueManager queueManager, Socket socket, SocketManager socketManager, String imei) {
        this.queueManager = queueManager;
        this.socket = socket;
        this.socketManager = socketManager;
        this.imei = imei;
    }

    @Override
    public void run() {
        socketManager.getStrData(socket, queueManager,this);
    }

    public void setImei(String imei){
        if(imei == null||this.imei != imei){
            imei = imei;
        }

    }
}
