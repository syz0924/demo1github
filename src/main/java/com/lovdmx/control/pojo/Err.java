package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Err.java
 * @Description: 错误报警信息表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:32:59
 *
 */
public class Err implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -8915397077170848299L;
	// 编号
	private Integer errId;
	// MAC地址
	private String deviceMac;
	// IP地址
	private String ip;
	// 设备类型
	private String deviceType;
	// 项目ID
	private Integer projectId;
	// 机架ID
	private Integer rackId;
	// 机架名
	private String rackName;
	// 错误类型
	private String errType;
	// 错误级别
	private String errRank;
	// 定时任务名
	private String taskName;
	// MD5值
	private String typeMd5;
	// 状态(0:未解决 1:解决)
	private Integer resolutionState;
	// 错误描述
	private String errDescribe;
	// 报警数量
	private Integer number = 0;
	// 报警开始时间(精确到毫秒)
	private Date startTime;
	// 报警结束时间(精确到毫秒)
	private Date endTime;
	// 报警日期
	private Date alarmDate;
	// 报警日期的数量
	private Integer alarmNumber;

	public Err() {
		super();
	}

	public Err(String deviceType, Integer projectId, String errType, Integer resolutionState, Date endTime) {
		super();
		this.deviceType = deviceType;
		this.projectId = projectId;
		this.errType = errType;
		this.resolutionState = resolutionState;
		this.endTime = endTime;
	}

	public Err(Integer errId, Integer resolutionState, Date endTime, String rackName) {
		super();
		this.errId = errId;
		this.resolutionState = resolutionState;
		this.endTime = endTime;
		this.rackName = rackName;
	}

	public Err(String deviceMac, String ip, String deviceType, Integer projectId, Integer rackId, String errType,
			String errRank, String typeMd5, Integer resolutionState, Integer number, String errDescribe, Date startTime,
			Date alarmDate) {
		super();
		this.deviceMac = deviceMac;
		this.ip = ip;
		this.deviceType = deviceType;
		this.projectId = projectId;
		this.rackId = rackId;
		this.errType = errType;
		this.errRank = errRank;
		this.typeMd5 = typeMd5;
		this.resolutionState = resolutionState;
		this.errDescribe = errDescribe;
		this.startTime = startTime;
		this.number = number;
		this.alarmDate = alarmDate;
	}

	public Err(String deviceMac, String ip, String deviceType, Integer projectId, Integer rackId, String errType,
			String errRank, String typeMd5, Integer resolutionState, Integer number, String errDescribe, Date startTime,
			Date alarmDate, String rackName) {
		super();
		this.deviceMac = deviceMac;
		this.ip = ip;
		this.deviceType = deviceType;
		this.projectId = projectId;
		this.rackId = rackId;
		this.errType = errType;
		this.errRank = errRank;
		this.typeMd5 = typeMd5;
		this.resolutionState = resolutionState;
		this.errDescribe = errDescribe;
		this.startTime = startTime;
		this.number = number;
		this.alarmDate = alarmDate;
		this.rackName = rackName;
	}

	public Err(Integer errId, String errDescribe, Integer number, String rackName) {
		super();
		this.errId = errId;
		this.errDescribe = errDescribe;
		this.number = number;
		this.rackName = rackName;
	}

	public Err(Integer errId, String typeMd5, String errDescribe, Integer number) {
		super();
		this.errId = errId;
		this.typeMd5 = typeMd5;
		this.errDescribe = errDescribe;
		this.number = number;
	}

	public String getErrDescribe() {
		return errDescribe;
	}

	public void setErrDescribe(String errDescribe) {
		this.errDescribe = errDescribe;
	}

	public Integer getErrId() {
		return errId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getErrRank() {
		return errRank;
	}

	public void setErrRank(String errRank) {
		this.errRank = errRank;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType == null ? null : deviceType.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType == null ? null : errType.trim();
	}

	public String getTypeMd5() {
		return typeMd5;
	}

	public void setTypeMd5(String typeMd5) {
		this.typeMd5 = typeMd5 == null ? null : typeMd5.trim();
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getResolutionState() {
		return resolutionState;
	}

	public void setResolutionState(Integer resolutionState) {
		this.resolutionState = resolutionState;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Integer getAlarmNumber() {
		return alarmNumber;
	}

	public void setAlarmNumber(Integer alarmNumber) {
		this.alarmNumber = alarmNumber;
	}

}