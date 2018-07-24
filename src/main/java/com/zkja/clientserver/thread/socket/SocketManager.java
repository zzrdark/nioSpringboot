package com.zkja.clientserver.thread.socket;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.send.SocketMessage;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.stream.InputRunnable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 对socket进行管理
 *
 * @authon zzr
 */
@Component
public class SocketManager {
    Map<String,SelectionKey> map = new ConcurrentHashMap<String,SelectionKey>();
    /**
     * 存储失效的Socket
     */
    private LinkedBlockingQueue<String> unableImeis = new LinkedBlockingQueue<String>();

    public Map<String, SelectionKey> getMap() {
        return map;
    }

    public void setMap(Map<String, SelectionKey> map) {
        this.map = map;
    }

    /**
     * 这里是bio调用的  已废弃
     * @param socket
     * @param queueManager
     * @param inputRunnable
     */
    public void getStrData(Socket socket, QueueManager queueManager, InputRunnable inputRunnable){
        InputStream is = null;
        try {
            is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            boolean eof = false;
            while (true){    
                    if (eof){
                        SocketMessage.sendsmc(queueManager,sb.toString(),inputRunnable);
                        //清空
                        sb.setLength(0);
                        eof = false;
                    }
                    while((len=is.read(bytes))!=-1){
                        String str = new String(bytes,0,len);
                        sb.append(str);
                        if(sb.toString().indexOf("]")!=-1){
                            eof = true;
                            break;
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 这里是bio调用的  已废弃
     * @param socket
     * @param list
     */
    public void putStrData(Socket socket, List<TcpReq> list){

        SocketMessage.sendsmu(socket,list);

    }

    /**
     *返回false表示没取到数据
     * @param byteBuffer
     * @param socketChannel
     * @param queueManager
     * @param selectionKey
     * @throws IOException
     */
    public boolean sendSmc(ByteBuffer byteBuffer, SocketChannel socketChannel, QueueManager queueManager, SelectionKey selectionKey) throws IOException {
       return SocketMessage.ReadAndaddQueue(byteBuffer, socketChannel, queueManager, selectionKey);

    }

    /**
     *
     * @param byteBuffer
     * @param socketChannel
     * @param list
     * @throws IOException
     */
    public void sendSmu(ByteBuffer byteBuffer, SocketChannel socketChannel,List list) throws IOException {
        SocketMessage.WriteSmu(byteBuffer,socketChannel, list);
    }

    public boolean addUnableSocket(String imei){
        return unableImeis.offer(imei);
    }

    public boolean pollUnableSocketQueue(){
        String imei;
        while ((imei = unableImeis.poll())!=null){
            if(map.remove(imei)==null){
                return false;
            }else {
                return true;
            }

        }
        return false;
    }




}
