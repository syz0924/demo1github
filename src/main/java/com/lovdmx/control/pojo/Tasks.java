package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Tasks.java
 * @Description: 定时任务
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月3日 下午5:04:42
 *
 */
public class Tasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -2389990401208025256L;
	// 标识
	private Integer taskId;
	// 子任务id(逗号隔开)
	private String subtaskIds;
	// 子任务名（逗号隔开）
	private String subtaskNames;
	// 任务名
	private String taskName;
	// 定时任务MD5值
	private String taskMd5;
	// 任务类型
	private String taskType;
	// 任务类型id
	private Integer taskTypeId;
	// 循环模式
	private String cyclicMode;
	// 循环模式id
	private Integer cyclicModeId;
	// 循环日期
	private String cyclicDate;
	// 项目ID
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 状态(0:暂停 1:启用)
	private Integer status;
	// 开始日期
	private Date startDate;
	// 结束日期
	private Date endDate;
	// 开始时间
	private Date startTime;
	// 结束日期
	private Date endTime;
	// 创建时间
	private Date createTime;
	// mac地址
	private String mac;
	// 开始日期时间戳
	private long startTimestamp;
	// 结束日期时间戳
	private long endTimestamp;
	// 字符串开始时间
	private String strStartTime;
	// 字符串结束时间
	private String strEndTime;
	// 任务类型
	private Integer type;
	// 任务模式
	private Integer mode;
	// 机架ID(逗号隔开)
	private String cabinetNums;
	// 时间范围
	private String timeScope;

	public Tasks() {
		super();
	}

	public void initTaskDate(String taskMd5, String taskType, String cyclicMode, Integer projectId, Integer status,
			Date startDate, Date endDate, Date startTime, Date endTime, String cabinetNums, Date createTime) {
		this.taskMd5 = taskMd5;
		this.taskType = taskType;
		this.cyclicMode = cyclicMode;
		this.projectId = projectId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cabinetNums = cabinetNums;
		this.createTime = createTime;
	}

	public Tasks(String taskName, Integer status) {
		super();
		this.taskName = taskName;
		this.status = status;
	}

	public Tasks(Integer taskId, String subtaskIds, String subtaskNames, String taskName, String taskMd5,
			String taskType, String cyclicMode, String cyclicDate, Integer projectId, Date startDate, Date endDate,
			Date startTime, Date endTime, Date createTime, String mac, long startTimestamp, long endTimestamp,
			String strStartTime, String strEndTime, Integer type, Integer mode, String cabinetNums) {
		super();
		this.taskId = taskId;
		this.subtaskIds = subtaskIds;
		this.subtaskNames = subtaskNames;
		this.taskName = taskName;
		this.taskMd5 = taskMd5;
		this.taskType = taskType;
		this.cyclicMode = cyclicMode;
		this.cyclicDate = cyclicDate;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.mac = mac;
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.strStartTime = strStartTime;
		this.strEndTime = strEndTime;
		this.type = type;
		this.mode = mode;
		this.cabinetNums = cabinetNums;
	}

	public String getTaskMd5() {
		return taskMd5;
	}

	public void setTaskMd5(String taskMd5) {
		this.taskMd5 = taskMd5;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public String getSubtaskNames() {
		return subtaskNames;
	}

	public void setSubtaskNames(String subtaskNames) {
		this.subtaskNames = subtaskNames;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getCabinetNums() {
		return cabinetNums;
	}

	public void setCabinetNums(String cabinetNums) {
		this.cabinetNums = cabinetNums;
	}

	public String getMac() {
		return mac;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getStrStartTime() {
		return strStartTime;
	}

	public void setStrStartTime(String strStartTime) {
		this.strStartTime = strStartTime;
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSubtaskIds() {
		return subtaskIds;
	}

	public void setSubtaskIds(String subtaskIds) {
		this.subtaskIds = subtaskIds == null ? null : subtaskIds.trim();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType == null ? null : taskType.trim();
	}

	public String getCyclicMode() {
		return cyclicMode;
	}

	public void setCyclicMode(String cyclicMode) {
		this.cyclicMode = cyclicMode == null ? null : cyclicMode.trim();
	}

	public String getCyclicDate() {
		return cyclicDate;
	}

	public void setCyclicDate(String cyclicDate) {
		this.cyclicDate = cyclicDate == null ? null : cyclicDate.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public Integer getCyclicModeId() {
		return cyclicModeId;
	}

	public void setCyclicModeId(Integer cyclicModeId) {
		this.cyclicModeId = cyclicModeId;
	}

	public String getTimeScope() {
		return timeScope;
	}

	public void setTimeScope(String timeScope) {
		this.timeScope = timeScope;
	}

}