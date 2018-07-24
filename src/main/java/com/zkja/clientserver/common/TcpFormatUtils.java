package com.zkja.clientserver.common;

import com.zkja.clientserver.domain.A2Res;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;

/**
 * @authon zzr
 */
public class TcpFormatUtils {

    /**
     * 解析smu发来的报文
     * @param data
     * @return
     */
    public static TcpRes getRes(String data){
        if(data==null){
            return null;
        }
        TcpRes t = new TcpRes();
        String bwlx = data.substring(1,3);//报文类型
        String bwlsh = data.substring(3,12);//流水号,9位
        String imei = data.substring(13, 28);//Imei码，15位
        String pid = data.substring(28, 42);//pid码，14位
        String sjh = data.substring(42, 53);//手机号码，11位
        t.setBwlsh(bwlsh);
        t.setImei(imei);
        t.setBwlx(bwlx);
        t.setPid(pid);
        t.setSjh(sjh);
        //登录
        if (SmuConstant.ACTION_LOGIN.equals(bwlx)) {
//        	t.setYjlx();//硬件类型
        }else if(SmuConstant.ACTION_REPORT.equals(bwlx)){
        	data.substring(54, 54);//手机号码，11位
        }
        return t;
    }

    /**
     * 拼装报文
     * @param tcpReq
     * @return String
     */
    public static String getReq(TcpReq tcpReq){

    	if("00".equals(tcpReq.getBwlx())){
    		 StringBuffer sb = new StringBuffer();
             sb.append("[");
             sb.append("888888888888888");
             sb.append("]");
             return sb.toString();
    	}
    	
        //监禁
        if ("84".equals(tcpReq.getBwlx())) {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            sb.append(tcpReq.getBwlx());
            sb.append(tcpReq.getBwlsh());
            sb.append(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
            sb.append("]");
            return sb.toString();
        }
        return null;
    }
}
