package com.zkja.clientserver.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zkja.clientserver.common.GsonUtil;
import com.zkja.clientserver.domain.A2Res;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkja.clientserver.common.HttpGetPostUtil;
import com.zkja.clientserver.common.SmuConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * @author zzr
 */
//@RequestMapping("sc-")
@RestController
public class A2Controller {

	private static Logger logger = Logger.getLogger(A2Controller.class);
    /**
     * smu监禁 解禁
     */
    @RequestMapping("sc-imprisonUrl")
    public void imprisonUrl(HttpServletRequest request, HttpServletResponse response){
        try {
            A2Res a2Res = beforeRequest(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 登录
     * @throws IOException 
     */
    @RequestMapping("loginUrl")
    public void login() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bwlx", SmuConstant.ACTION_LOGIN);
		paramsMap.put("bwlsh", "999999999");
		paramsMap.put("imei", "860194037267648");
		paramsMap.put("pid", "11111111111112");
		paramsMap.put("sjh", "15113039774");
		paramsMap.put("yjlx", "02");
		String reqJson = GsonUtil.toJson(paramsMap);
		logger.info("requestLogin==="+reqJson);
		String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
		logger.info("responeLogin==="+respJson);
		Map<String, String> map = GsonUtil.jsonToMap(respJson);
		System.out.println(map);
		
    	//解析smc的数据返回给设备
    }
    
    /**
     * 退出
     * @throws IOException 
     */
    @RequestMapping("logoutUrl")
    public void logout() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", SmuConstant.ACTION_LOGOUT);
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	String reqJson = GsonUtil.toJson(paramsMap);
		logger.info("requestLogout==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeLogout==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    }
    
    /**
     * 链路检测
     * @throws IOException 
     */
    @RequestMapping("heartbeatUrl")
    public void heartbeat() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", SmuConstant.ACTION_HEARTBEAT);
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	String reqJson = GsonUtil.toJson(paramsMap);
    	logger.info("requestHeartbeat==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeHeartbeat==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    }
    
    /**
     * SMU设备安装
     * @throws IOException 
     */
    @RequestMapping("setupUrl")
    public void setup() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", SmuConstant.ACTION_SMU_SETUP);
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	String reqJson = GsonUtil.toJson(paramsMap);
    	logger.info("requestSetup==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeSetup==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    }
    
    
    /**
     * 定时报告Report
     * @throws IOException 
     */
    @RequestMapping("reportUrl")
    public void report() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", SmuConstant.ACTION_REPORT);
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	paramsMap.put("qdlx", "1");
    	paramsMap.put("cbwlx", "GPRMC");
    	String time = new SimpleDateFormat("yyyymmddHHMMSS").format(new Date());
    	paramsMap.put("gpstime", time);
    	paramsMap.put("av", "A");
    	paramsMap.put("la", "22.123456");
    	paramsMap.put("wdbs", "N");
    	paramsMap.put("lo", "112.123456");
    	paramsMap.put("jdbs", "15113039774");
    	paramsMap.put("sd", "10");
    	paramsMap.put("fx", "120");
    	paramsMap.put("locid", "20");
    	paramsMap.put("ccid", "20");
    	paramsMap.put("sjdl", "15");
    	paramsMap.put("sbdl", "12");
    	paramsMap.put("dssclx", "1");
    	paramsMap.put("sjjg", "5");
    	paramsMap.put("jqlx", "");
    	paramsMap.put("sbaz", "1");
    	paramsMap.put("sfjj", "1");
    	String reqJson = GsonUtil.toJson(paramsMap);
    	logger.info("requestReport==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeReport==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    	
    }
    
    /**
     * 多基站定位
     * @throws IOException 
     */
    @RequestMapping("mbsLocateUrl")
    public void mbsLocate() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", SmuConstant.ACTION_REPORT_NEW);
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	paramsMap.put("qdlx", "1");
    	paramsMap.put("cbwlx", "GPRMC");
    	String time = new SimpleDateFormat("yyyymmddHHMMSS").format(new Date());
    	paramsMap.put("gpstime", time);
    	paramsMap.put("av", "A");
    	paramsMap.put("la", "22.123456");
    	paramsMap.put("wdbs", "N");
    	paramsMap.put("lo", "112.123456");
    	paramsMap.put("jdbs", "15113039774");
    	paramsMap.put("sd", "10");
    	paramsMap.put("fx", "120");
    	paramsMap.put("locid", "20");
    	paramsMap.put("ccid", "20");
    	paramsMap.put("sjdl", "15");
    	paramsMap.put("sbdl", "12");
    	paramsMap.put("dssclx", "1");
    	paramsMap.put("sjjg", "5");
    	paramsMap.put("jqlx", "");
    	paramsMap.put("sbaz", "1");
    	paramsMap.put("sfjj", "1");
    	paramsMap.put("iccid", "12345678");
    	paramsMap.put("jrfs", "0");
    	paramsMap.put("cbwsm", "8");
    	paramsMap.put("yl", "");
    	paramsMap.put("mobilenet", "");
    	paramsMap.put("wifi", "");
    	paramsMap.put("bt", "");
    	paramsMap.put("terminalip", "127.0.0.1");
    	paramsMap.put("reserved", "1");
    	paramsMap.put("sourceip", "220.170.123.222");
    	paramsMap.put("reserved_wd", "1");
    	paramsMap.put("reserved_xy", "1");
    	paramsMap.put("reserved_xl", "1");
    	paramsMap.put("reserved_jb", "1");
    	paramsMap.put("reserved_ryzt", "1");
    	paramsMap.put("reserved_xdgl", "1");
    	String reqJson = GsonUtil.toJson(paramsMap);
    	logger.info("requestMbsLocate==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeMbsLocate==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    	
    }
    
    
    /**
     * 运动参数定时报告F9-TCP报文
     * @throws IOException 
     */
    @RequestMapping("sportReportUrl")
    public void sportReport() throws IOException{
    	
    	//接收到设备tcp数据
    	
    	
    	//解析出数据发送到smc
    	String url = "http://localhost:8080/smcServer/servlet/a1";
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("bwlx", "");//服务端没有
    	paramsMap.put("bwlsh", "999999999");
    	paramsMap.put("imei", "860194037267648");
    	paramsMap.put("pid", "11111111111112");
    	paramsMap.put("sjh", "15113039774");
    	paramsMap.put("sbwd", "1");
    	paramsMap.put("ryxy", "1");
    	paramsMap.put("ryxl", "1");
    	paramsMap.put("xzbs", "1");
    	paramsMap.put("gl", "10%");
    	paramsMap.put("jlcs", "15");
    	paramsMap.put("mostyxf", "A");
    	paramsMap.put("jbz", "5");
    	paramsMap.put("mopoyxf", "A");
    	paramsMap.put("yddw", "");
    	paramsMap.put("dycjlsj", "08:23:16");
    	paramsMap.put("dycjlrq", "11-08-2018");
    	String reqJson = GsonUtil.toJson(paramsMap);
    	logger.info("requestMbsLocate==="+reqJson);
    	String respJson = HttpGetPostUtil.doPostStr(url, reqJson);
    	logger.info("responeMbsLocate==="+respJson);
    	Map<String, String> map = GsonUtil.jsonToMap(respJson);
    	System.out.println(map);
    	
    	//解析smc的数据返回给设备
    	
    }

    public A2Res beforeRequest(HttpServletRequest request) throws Exception{
        String jsonData = Streams.asString(request.getInputStream(), "gbk");
        GsonUtil.getEntityFromRequest(jsonData, A2Res.class);

        logger.info("request==="+jsonData);
        A2Res a2Res = (A2Res) GsonUtil.getEntityFromRequest(
                jsonData, A2Res.class);
        String remotePort = request.getRemotePort()+"";
        String ssPort = (String) request.getParameter("port");
        String sourceIp = (String) request.getParameter("sourceIp");
        logger.info("url Parameter----ssPort="+ssPort+",sourceIp="+sourceIp);
        return a2Res;
    }
}
