package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: TimedTasks.java
 *
 * @Description: 智能网关定时任务
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月1日 上午10:11:38
 *
 */
public class TimedTasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 4399355918791727247L;
	// 标识
	private Integer taskId;
	// 任务名
	private String taskName;
	// 任务md5值
	private String taskMd5;
	// 任务模式 (1:单任务模式 2:多任务模式 3:所有任务模式)
	private Integer taskMode;
	// 延时时间
	private Integer delayTime;
	// 机架ID(逗号隔开)
	private String rackIds;
	// 机架名
	private String rackNames;
	// 任务内容
	private String taskContent;
	// 星期（逗号隔开)
	private String weekIds;
	// 星期多个
	private String weeks;
	// 项目id
	private Integer projectId;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 创建时间
	private Date createTime;
	// 开启时间
	private String openTime;
	// 关闭时间
	private String closeTime;
	// 项目名
	private String projectName;
	// 机架id
	private Integer rackId;

	public TimedTasks() {

	}

	public TimedTasks(String taskName, String taskMd5, Integer taskMode, Integer delayTime, String rackIds,
			String weekIds, String taskContent, Integer projectId, Date startTime, Date endTime, Date createTime) {
		super();
		this.taskName = taskName;
		this.taskMd5 = taskMd5;
		this.taskMode = taskMode;
		this.delayTime = delayTime;
		this.rackIds = rackIds;
		this.taskContent = taskContent;
		this.weekIds = weekIds;
		this.projectId = projectId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	public Integer getTaskMode() {
		return taskMode;
	}

	public void setTaskMode(Integer taskMode) {
		this.taskMode = taskMode;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRackIds() {
		return rackIds;
	}

	public String getRackNames() {
		return rackNames;
	}

	public void setRackNames(String rackNames) {
		this.rackNames = rackNames;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public void setRackIds(String rackIds) {
		this.rackIds = rackIds;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public String getWeekIds() {
		return weekIds;
	}

	public void setWeekIds(String weekIds) {
		this.weekIds = weekIds;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
}