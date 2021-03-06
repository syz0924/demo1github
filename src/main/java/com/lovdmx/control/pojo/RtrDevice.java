package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RtrDevice.java
 * @Description: RTR服务器表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:46:53
 *
 */
public class RtrDevice implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -899126116455074876L;
	// 编号
	private Integer rtrId;
	// IP地址
	private String ip;
	// 异常Id
	private Integer errId;
	// MAC地址
	private String mac;
	// 模型尺寸
	private String modelSize;
	// 当前工作模式
	private String curMode;
	// 工作模式下标
	private Integer modeId;
	// 机架ID
	private Integer rackId;
	// 项目名
	private String projectName;
	// 机架名
	private String rackName;
	// 机架下标
	private Integer rackIndex;
	// 在线状态 （0未在线 1在线）
	private Short isOnline;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;

	// RTR离线报警状态 (0:未报警 1:以报警)
	private Integer rtrOfflineAlarmStatus = 0;
	// DMX512控制盒离线报警状态 (0:未报警 1:以报警)
	private Integer dmxOfflineAlarmStatus = 0;
	// DMX512控制盒之前报警数量
	private Integer dmxBeforeAlarmNumber = 0;
	// DMX512控制盒现在报警数量
	private Integer dmxNowAlarmNumber = 0;
	// 控制盒集合信息(子类)
	private List<Dmx512Device> sonDmx512DeviceList;

	/**
	 * @Function: RtrDevice.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午1:50:03
	 */
	public RtrDevice() {
		super();
	}

	/**
	 * @Function: RtrDevice.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午1:49:57
	 */
	public RtrDevice(String ip, String mac, String modelSize, String curMode, Integer rackId, Short isOnline) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.modelSize = modelSize;
		this.curMode = curMode;
		this.rackId = rackId;
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

	public Integer getDmxBeforeAlarmNumber() {
		return dmxBeforeAlarmNumber;
	}

	public void setDmxBeforeAlarmNumber(Integer dmxBeforeAlarmNumber) {
		this.dmxBeforeAlarmNumber = dmxBeforeAlarmNumber;
	}

	public Integer getModeId() {
		return modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	public Integer getDmxNowAlarmNumber() {
		return dmxNowAlarmNumber;
	}

	public void setDmxNowAlarmNumber(Integer dmxNowAlarmNumber) {
		this.dmxNowAlarmNumber = dmxNowAlarmNumber;
	}

	public Integer getRtrId() {
		return rtrId;
	}

	public void setRtrId(Integer rtrId) {
		this.rtrId = rtrId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getRackIndex() {
		return rackIndex;
	}

	public void setRackIndex(Integer rackIndex) {
		this.rackIndex = rackIndex;
	}

	public String getIp() {
		return ip;
	}

	public Integer getRtrOfflineAlarmStatus() {
		return rtrOfflineAlarmStatus;
	}

	public void setRtrOfflineAlarmStatus(Integer rtrOfflineAlarmStatus) {
		this.rtrOfflineAlarmStatus = rtrOfflineAlarmStatus;
	}

	public Integer getDmxOfflineAlarmStatus() {
		return dmxOfflineAlarmStatus;
	}

	public void setDmxOfflineAlarmStatus(Integer dmxOfflineAlarmStatus) {
		this.dmxOfflineAlarmStatus = dmxOfflineAlarmStatus;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getModelSize() {
		return modelSize;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<Dmx512Device> getSonDmx512DeviceList() {
		return sonDmx512DeviceList;
	}

	public void setSonDmx512DeviceList(List<Dmx512Device> sonDmx512DeviceList) {
		this.sonDmx512DeviceList = sonDmx512DeviceList;
	}

	public void setModelSize(String modelSize) {
		this.modelSize = modelSize == null ? null : modelSize.trim();
	}

	public String getCurMode() {
		return curMode;
	}

	public void setCurMode(String curMode) {
		this.curMode = curMode == null ? null : curMode.trim();
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Short getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Short isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}
}