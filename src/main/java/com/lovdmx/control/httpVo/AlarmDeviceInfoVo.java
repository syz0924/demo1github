package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: AlarmDeviceInfoVo.java
 * @Description: 报警的设备信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月10日 上午9:37:51
 *
 */
public class AlarmDeviceInfoVo {
	// 项目ID
	private Integer projectId;
	// 机架ID
	private Integer rackId;
	// 机架编号
	private Integer rackIndex;
	// 机架名
	private String rackName;
	// 报警ID
	private Integer errId;
	// RTR Ip
	private String rtrIp;
	// Sprite Ip
	private String spriteIp;
	// Rtr Mac
	private String rtrMac;
	// Sprite Mac
	private String spriteMac;
	// Sprite 在线状态 （0未在线 1在线）
	private Integer spriteIsOnline;
	// RTR 在线状态 （0未在线 1在线）
	private Short rtrIsOnline = 0;
	// RTR离线报警状态 (0:未报警 1:以报警)
	private Integer rtrOfflineAlarmStatus = 0;
	// Sprite离线报警状态 (0:未报警 1:以报警)
	private Integer spriteOfflineAlarmStatus = 0;
	// DMX512控制盒离线报警状态 (0:未报警 1:以报警)
	private Integer dmxOfflineAlarmStatus = 0;
	// DMX512控制盒之前报警数量
	private Integer dmxBeforeAlarmNumber = 0;
	// DMX512控制盒现在报警数量
	private Integer dmxNowAlarmNumber = 0;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public Integer getSpriteIsOnline() {
		return spriteIsOnline;
	}

	public void setSpriteIsOnline(Integer spriteIsOnline) {
		this.spriteIsOnline = spriteIsOnline;
	}

	public Short getRtrIsOnline() {
		return rtrIsOnline;
	}

	public void setRtrIsOnline(Short rtrIsOnline) {
		this.rtrIsOnline = rtrIsOnline;
	}

	public Integer getRtrOfflineAlarmStatus() {
		return rtrOfflineAlarmStatus;
	}

	public void setRtrOfflineAlarmStatus(Integer rtrOfflineAlarmStatus) {
		this.rtrOfflineAlarmStatus = rtrOfflineAlarmStatus;
	}

	public Integer getSpriteOfflineAlarmStatus() {
		return spriteOfflineAlarmStatus;
	}

	public void setSpriteOfflineAlarmStatus(Integer spriteOfflineAlarmStatus) {
		this.spriteOfflineAlarmStatus = spriteOfflineAlarmStatus;
	}

	public Integer getDmxOfflineAlarmStatus() {
		return dmxOfflineAlarmStatus;
	}

	public void setDmxOfflineAlarmStatus(Integer dmxOfflineAlarmStatus) {
		this.dmxOfflineAlarmStatus = dmxOfflineAlarmStatus;
	}

	public Integer getDmxBeforeAlarmNumber() {
		return dmxBeforeAlarmNumber;
	}

	public void setDmxBeforeAlarmNumber(Integer dmxBeforeAlarmNumber) {
		this.dmxBeforeAlarmNumber = dmxBeforeAlarmNumber;
	}

	public Integer getDmxNowAlarmNumber() {
		return dmxNowAlarmNumber;
	}

	public String getRtrMac() {
		return rtrMac;
	}

	public void setRtrMac(String rtrMac) {
		this.rtrMac = rtrMac;
	}

	public String getSpriteMac() {
		return spriteMac;
	}

	public void setSpriteMac(String spriteMac) {
		this.spriteMac = spriteMac;
	}

	public void setDmxNowAlarmNumber(Integer dmxNowAlarmNumber) {
		this.dmxNowAlarmNumber = dmxNowAlarmNumber;
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

	public String getRtrIp() {
		return rtrIp;
	}

	public void setRtrIp(String rtrIp) {
		this.rtrIp = rtrIp;
	}

	public String getSpriteIp() {
		return spriteIp;
	}

	public void setSpriteIp(String spriteIp) {
		this.spriteIp = spriteIp;
	}
}
