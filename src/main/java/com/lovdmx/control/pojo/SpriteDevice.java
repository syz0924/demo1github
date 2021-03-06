package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteDevice.java
 * @Description: 录放精灵信息表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:51:37
 *
 */
public class SpriteDevice implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 4542687092360997530L;
	// 编号
	private Integer spriteId;
	// IP地址
	private String ip;
	// MAC地址
	private String mac;
	// 机架ID
	private Integer rackId;
	// 机柜名
	private String rackName;
	// 机柜下标
	private Integer rackIndex;
	// 项目名
	private String projectName;
	// 在线状态（0未在线 1在线）
	private Short isOnline;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;
	// 离线报警状态 (0:未报警 1:以报警)
	private Integer offlineAlarmStatus = 0;
	// 异常Id
	private Integer errId;

	/**
	 * @Function: SpriteDevice.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午1:50:25
	 */
	public SpriteDevice() {
		super();
	}

	/**
	 * @Function: SpriteDevice.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午1:50:15
	 */
	public SpriteDevice(String ip, String mac, Integer rackId, Short isOnline) {
		super();
		this.ip = ip;
		this.mac = mac;
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

	public Integer getOfflineAlarmStatus() {
		return offlineAlarmStatus;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setOfflineAlarmStatus(Integer offlineAlarmStatus) {
		this.offlineAlarmStatus = offlineAlarmStatus;
	}

	public Integer getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(Integer spriteId) {
		this.spriteId = spriteId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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