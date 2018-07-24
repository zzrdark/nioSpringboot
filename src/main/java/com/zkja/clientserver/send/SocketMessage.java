package com.zkja.clientserver.send;

import com.zkja.clientserver.common.TcpFormatUtils;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.stream.InputRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @authon zzr
 */
public class SocketMessage {
    private static Logger logger = LoggerFactory.getLogger(SocketMessage.class);
    /**
     * 这里是bio调用的  已废弃
     *
     * @param queueManager
     * @param data
     * @param inputRunnable
     * @return
     */
    public static boolean sendsmc(QueueManager queueManager, String data, InputRunnable inputRunnable) {
        TcpRes tcpRes = TcpFormatUtils.getRes(data);
        logger.info("Thread:" + Thread.currentThread().getName() + " 接收到Socket  Tcp" + tcpRes);
        inputRunnable.setImei(tcpRes.getImei());
        return queueManager.addSmcQueue(tcpRes);
    }

    public static boolean sendsmc(QueueManager queueManager, String data, SelectionKey selectionKey) {
        TcpRes tcpRes = TcpFormatUtils.getRes(data);
        if(tcpRes.getImei()!=null||!tcpRes.getImei().trim().isEmpty()){
            if(selectionKey.attachment()==null||((String)selectionKey.attachment()).equals(tcpRes.getImei())){
                selectionKey.attach(tcpRes.getImei());
            }
        }
        logger.info("Thread:" + Thread.currentThread().getName() + " 接收到SocketChannel  Tcp" + tcpRes);
        return queueManager.addSmcQueue(tcpRes);
    }

    /**
     * 这里是bio调用的  已废弃
     * @param socket
     * @param list
     * @return
     */
    public static boolean sendsmu(Socket socket, List<TcpReq> list) {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            for (TcpReq tcpReq : list) {
                String strData = TcpFormatUtils.getReq(tcpReq);
                logger.debug("Thread:" + Thread.currentThread().getName() + " 发送到Socket Tcp" + tcpReq);
                os.write(strData.getBytes());
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean ReadAndaddQueue(ByteBuffer byteBuffer, SocketChannel socketChannel, QueueManager queueManager,SelectionKey selectionKey) throws IOException {
        int len = 0;
        StringBuffer sb = new StringBuffer();
        boolean eof = false;
        String[] strs = null;
        Integer sum = 0;
        while ((len = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            sum +=len;

            String str = new String(byteBuffer.array(), 0, len);
            sb.append(str);
            if (sb.toString().indexOf("]") != -1) {
                strs = sb.toString().split("]");

                for (int i = 0;i<strs.length;i++){
                    strs[i] = strs[i]+"]";
                }
                logger.debug("SocketMessage收到终端的消息："+sb.toString());
                for (String string: strs ){
                    logger.debug("SocketMessage收到终端的消息：经过处理"+string);
                }
                eof = true;

            }
            if (eof) {
                for (String string : strs) {
                    sendsmc(queueManager, string, selectionKey);
                }
                //清空  如果同一条数据只发一半过来会出错
                sb.setLength(0);
                eof = false;
            }
            byteBuffer.compact();
        }
        if(sum == 0){
            return false;
        }
        return true;
    }

    public static boolean WriteSmu(ByteBuffer byteBuffer, SocketChannel socketChannel, List<TcpReq> list) throws IOException {
        for (TcpReq tcpReq : list){
            String data = TcpFormatUtils.getReq(tcpReq);
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }
        return true;
    }


}
