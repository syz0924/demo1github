package com.lovdmx.control.httpVo;

import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayTaskDetailsVo.java
 * @Description: 机柜和网关个继电器任务详情
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月10日 下午1:51:43
 *
 */
public class RackAndGatewayAndRelayTaskDetailsAndWeekVo {
	// 继电器定时任务详情id
	private Integer detlId;
	// 星期id
	private Integer weekId;
	// 星期
	private String week;
	// 继电器id
	private Integer relayId;
	// 网关Mac
	private String gatewayMac;
	// 回路操作列表
	private String loopNums;
	// 继电器项目id
	private Integer taskId;
	// 继电器项目名
	private String taskName;
	// 机柜id
	private Integer rackId;
	// 机柜名
	private String rackName;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 485Ip
	private String rs485toNetIp;
	// 从站地址
	private Integer slaveAddr;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;

	public String getRs485toNetIp() {
		return rs485toNetIp;
	}

	public void setRs485toNetIp(String rs485toNetIp) {
		this.rs485toNetIp = rs485toNetIp;
	}

	public Integer getSlaveAddr() {
		return slaveAddr;
	}

	public void setSlaveAddr(Integer slaveAddr) {
		this.slaveAddr = slaveAddr;
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

	public Integer getDetlId() {
		return detlId;
	}

	public void setDetlId(Integer detlId) {
		this.detlId = detlId;
	}

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public String getWeek() {
		return week;
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

	public void setWeek(String week) {
		this.week = week;
	}

	public Integer getRelayId() {
		return relayId;
	}

	public void setRelayId(Integer relayId) {
		this.relayId = relayId;
	}

	public String getLoopNums() {
		return loopNums;
	}

	public void setLoopNums(String loopNums) {
		this.loopNums = loopNums;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac;
	}

}
