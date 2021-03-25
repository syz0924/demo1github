package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: AlarmMessageVo.java
 * @Description: 报警信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月26日 下午1:30:16
 *
 */
public class AlarmDataVo {
	// MAC地址
	private String mac;
	// 设备IP地址
	private String ip;
	// 机架下标
	private Integer rackId;
	// 机架名
	private String rackName;
	// 任务名
	private String taskName;
	// md5值
	private String fileMd5;
	// 文件路径
	private String filePath;
	// 报警数据ID
	private Integer errId;
	// 文件之前报警数量
	private Integer beforeAlarmNumber = 0;
	// 文件现在报警数量
	private Integer nowAlarmNumber = 0;
	// 离线报警状态 (0:未报警 1:以报警)
	private Integer offlineAlarmStatus = 0;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getOfflineAlarmStatus() {
		return offlineAlarmStatus;
	}

	public void setOfflineAlarmStatus(Integer offlineAlarmStatus) {
		this.offlineAlarmStatus = offlineAlarmStatus;
	}

	public String getIp() {
		return ip;
	}

	public String getTaskName() {
		return taskName;
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public Integer getBeforeAlarmNumber() {
		return beforeAlarmNumber;
	}

	public void setBeforeAlarmNumber(Integer beforeAlarmNumber) {
		this.beforeAlarmNumber = beforeAlarmNumber;
	}

	public Integer getNowAlarmNumber() {
		return nowAlarmNumber;
	}

	public void setNowAlarmNumber(Integer nowAlarmNumber) {
		this.nowAlarmNumber = nowAlarmNumber;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
