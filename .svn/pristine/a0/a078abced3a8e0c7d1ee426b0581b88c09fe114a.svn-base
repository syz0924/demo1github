package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512Device.java
 * @Description: Dmx512控制盒信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:26:47
 *
 */
public class Dmx512Device implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -8088400085559410205L;
	// 编号
	private Integer dmxId;
	// dmx512控制Mac地址
	private String dmx512Mac;
	// ip地址
	private String ip;
	// Universe信息 (逗号隔开)
	private String universe;
	// RDM个数
	private Integer rdmNums;
	// 传感器采集数据(1:温度 2:湿度 3:烟雾)
	private String rdmts;
	// RTR服务器MAC
	private String rtrMac;
	// 在线状态 （0:未在线 1在线）
	private Short isOnline;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;
	// 溫度范围
	private String temperatureRange;
	// 机架id
	private Integer rackId;
	// 机架名
	private String rackName;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;

	/**
	 * @Function: Dmx512Device.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午2:01:09
	 */
	public Dmx512Device() {
		super();
	}

	/**
	 * @Function: Dmx512Device.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午2:00:49
	 */
	public Dmx512Device(String ip, String universe, Integer rdmNums, String rdmts, String rtrMac, Short isOnline) {
		super();
		this.ip = ip;
		this.universe = universe;
		this.rdmNums = rdmNums;
		this.rdmts = rdmts;
		this.rtrMac = rtrMac;
		this.isOnline = isOnline;
	}

	public Integer getDmxId() {
		return dmxId;
	}

	public void setDmxId(Integer dmxId) {
		this.dmxId = dmxId;
	}

	public String getDmx512Mac() {
		return dmx512Mac;
	}

	public void setDmx512Mac(String dmx512Mac) {
		this.dmx512Mac = dmx512Mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUniverse() {
		return universe;
	}

	public void setUniverse(String universe) {
		this.universe = universe;
	}

	public Integer getRdmNums() {
		return rdmNums;
	}

	public void setRdmNums(Integer rdmNums) {
		this.rdmNums = rdmNums;
	}

	public String getRdmts() {
		return rdmts;
	}

	public void setRdmts(String rdmts) {
		this.rdmts = rdmts;
	}

	public String getRtrMac() {
		return rtrMac;
	}

	public void setRtrMac(String rtrMac) {
		this.rtrMac = rtrMac;
	}

	public Short getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Short isOnline) {
		this.isOnline = isOnline;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getTemperatureRange() {
		return temperatureRange;
	}

	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}