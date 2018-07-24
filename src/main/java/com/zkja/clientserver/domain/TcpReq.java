package com.zkja.clientserver.domain;

/**
 * @authon zzr
 */
public class TcpReq {

    /**
     * 信息类型，如 80
     */
    private String bwlx;
    /**
     * 报文流水号
     */
    private String bwlsh;
    /**
     * Imei码，15位
     */
    private String imei;
    /**
     * Pid码，14位
     */
    private String pid;
    /**
     * 手机号码，11位
     */
    private String sjh;
    /**
     * 硬件类型
     */
    private String yjlx;
    /**
     * 启动类型1：普通启动；2：过滤启动
     */
    private String qdlx;
    /**
     * 定时上传的时间
     */
    private String dsscsj;
    /**
     * 是否监禁过
     */
    private String sfjj;
    /**
     * 时间同步
     */
    private String sjtb;

    /**
     * 次报文类型，一般固定为GPRMC
     */
    private String cbwlx;
    
    /**
     * GPS时间
     */
    private String gpstime;
    /**
     * 定位标识 A：定位；V：不定位
     */
    private String av;
    /**
     * 纬度,解析后为WGS84坐标
     */
    private String la;
    /**
     * 纬度标识 “N”：表示北纬；“S”：表示南纬
     */
    private String wdbs;
    /**
     * 经度,解析后为WGS84坐标。如：112.123456
     */
    private String lo;
    /**
     * 经度标识。“E”：表示东经；“W”：表示西经
     */
    private String jdbs;
    /**
     * 速度
     */
    private String sd;
    /**
     * 方向，取值范围 0－360
     */
    private String fx;
    /**
     * 基站locid值
     */
    private String locid;
    /**
     * 基站ccid
     */
    private String ccid;
    /**
     * 手机电量
     */
    private String sjdl;
    /**
     * 手表电量
     */
    private String sbdl;
    /**
     * 定时上传类型 0：不启动；1：普通启动，处理所有信息；2：过滤报警信息启动
     */
    private String dssclx;
    /**
     * 上传时间间隔
     */
    private String sjjg;
    /**
     * 报警内容。无报警的时候，该内容为空
     */
    private String jqlx;
    /**
     * 设备是否安装，0：未安装；1：安装
     */
    private String sbaz;

    public String getBwlx() {
        return bwlx;
    }

    public void setBwlx(String bwlx) {
        this.bwlx = bwlx;
    }

    public String getBwlsh() {
        return bwlsh;
    }

    public void setBwlsh(String bwlsh) {
        this.bwlsh = bwlsh;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

	public String getYjlx() {
		return yjlx;
	}

	public void setYjlx(String yjlx) {
		this.yjlx = yjlx;
	}

	public String getQdlx() {
		return qdlx;
	}

	public void setQdlx(String qdlx) {
		this.qdlx = qdlx;
	}

	public String getDsscsj() {
		return dsscsj;
	}

	public void setDsscsj(String dsscsj) {
		this.dsscsj = dsscsj;
	}

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public String getSjtb() {
		return sjtb;
	}

	public void setSjtb(String sjtb) {
		this.sjtb = sjtb;
	}

	public String getCbwlx() {
		return cbwlx;
	}

	public void setCbwlx(String cbwlx) {
		this.cbwlx = cbwlx;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public String getAv() {
		return av;
	}

	public void setAv(String av) {
		this.av = av;
	}

	public String getLa() {
		return la;
	}

	public void setLa(String la) {
		this.la = la;
	}

	public String getWdbs() {
		return wdbs;
	}

	public void setWdbs(String wdbs) {
		this.wdbs = wdbs;
	}

	public String getLo() {
		return lo;
	}

	public void setLo(String lo) {
		this.lo = lo;
	}

	public String getJdbs() {
		return jdbs;
	}

	public void setJdbs(String jdbs) {
		this.jdbs = jdbs;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}

	public String getSjdl() {
		return sjdl;
	}

	public void setSjdl(String sjdl) {
		this.sjdl = sjdl;
	}

	public String getSbdl() {
		return sbdl;
	}

	public void setSbdl(String sbdl) {
		this.sbdl = sbdl;
	}

	public String getDssclx() {
		return dssclx;
	}

	public void setDssclx(String dssclx) {
		this.dssclx = dssclx;
	}

	public String getSjjg() {
		return sjjg;
	}

	public void setSjjg(String sjjg) {
		this.sjjg = sjjg;
	}

	public String getJqlx() {
		return jqlx;
	}

	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}

	public String getSbaz() {
		return sbaz;
	}

	public void setSbaz(String sbaz) {
		this.sbaz = sbaz;
	}

	
}
