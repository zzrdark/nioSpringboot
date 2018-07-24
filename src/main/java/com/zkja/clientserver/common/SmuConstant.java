package com.zkja.clientserver.common;

public class SmuConstant {

	public static String ACTION_LOGIN="80";
	public static String ACTION_LOGOUT="81";
	public static String ACTION_SYSTEM_LOGOUT="99";
	public static String ACTION_HEARTBEAT="82";
	public static String ACTION_SMU_SETUP="83";
	public static String ACTION_REPORT="F2";
	public static String SYSTEM_SMC="SMC";
	
	public static String OPCODE_SP_DEV_STATUS="sp_dev_status";
	public static String OPCODE_SP_DEV_WARNINFO="sp_dev_warnInfo";
	public static String OPCODE_SP_REPORTINFO="sp_reportInfo";
	public static String OPCODE_SP_RUNINFO="sp_runInfo";
	
	public static String ACTION_IMPRISON_DISABLE="84";
	public static String ACTION_IMPRISON_ENABLED="85";
	public static String ACTION_STARTREPORT="F1";
	public static String ACTION_STOPREPORT="F3";
	public static String ACTION_SMUINFO="F4";
	public static String ACTION_SYSCONF="IP";
	public static String ACTION_FENCECONF="FE";
	public static String ACTION_LIGHTCONF="LE";
	public static String ACTION_WN="WN";
	public static String ACTION_REPORT_NEW = "F5";
	public static String ACTION_REPORT_RUN = "F9";
	
	public static Integer ON_LINE= 1; //在线
	public static Integer OFF_LINE= 0; //离线
	
	public static String PHONE_PID= "00000000000000"; //手机+蓝牙手表
	public static String WATCH_PID= "11111111111111"; //GSM手表
	
	public static String IMPRISON_SUCCECC= "1"; //成功
	public static String IMPRISON_FAILURE= "0"; //失败
	
	public static String WARN_SOSWARNFALG= "1"; //1:SOS求救报警；
	public static String WARN_BLUETOOTH_DISCONNECT= "2"; //2:蓝牙断开报警；
	public static String WARN_STRAPS_DISASSEMBLE= "3"; //3:腕带拆解报警；
	public static String WARN_BLUETOOTH_AND_STRAPS= "4"; //4:蓝牙断开报警+腕带拆解报警；
	public static String WARN_PHONE_LOW_BATTERY= "5"; //5:手机低电报警；
	public static String WARN_WATCH_LOW_BATTERY= "6"; //6:手表低电报警；
	public static String WARN_PHONE_AND_WATCH= "7"; //7:手机低电报警+手表低电报警;
	public static String WARN_FENCE= "8"; //8:围栏报警;
	public static String WARN_DISASSEMBLE= "9"; //9:拆解报警(围栏报警以后PID开盖报警);
	
	public static String ACTION_SMU_LOGOUT = "01";
	
}
