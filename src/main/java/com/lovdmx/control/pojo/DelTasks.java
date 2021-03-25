package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: delTasks.java
 * @Description: 以删除定时任务
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月26日 下午3:55:42
 *
 */
public class DelTasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -7960854179550531410L;
	// 标识
	private Integer delId;
	// 子任务id(逗号隔开)
	private String subtaskIds;
	// 任务名
	private String taskName;
	// 定时任务MD5值
	private String taskMd5;
	// 任务类型
	private String taskType;
	// 循环模式
	private String cyclicMode;
	// 循环日期
	private String cyclicDate;
	// 项目ID
	private Integer projectId;
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
	// 时间时间
	private Date deleteTime;

	public DelTasks() {
		super();
	}

	public DelTasks(String subtaskIds, String taskName, String taskMd5, String taskType, String cyclicMode,
			String cyclicDate, Integer projectId, Integer status, Date startDate, Date endDate, Date startTime,
			Date endTime, Date createTime, Date deleteTime) {
		super();
		this.subtaskIds = subtaskIds;
		this.taskName = taskName;
		this.taskMd5 = taskMd5;
		this.taskType = taskType;
		this.cyclicMode = cyclicMode;
		this.cyclicDate = cyclicDate;
		this.projectId = projectId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.deleteTime = deleteTime;
	}

	public Integer getDelId() {
		return delId;
	}

	public void setDelId(Integer delId) {
		this.delId = delId;
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

	public String getTaskMd5() {
		return taskMd5;
	}

	public void setTaskMd5(String taskMd5) {
		this.taskMd5 = taskMd5 == null ? null : taskMd5.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
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
}