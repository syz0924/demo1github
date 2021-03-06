package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: TaskAssembleMd5Vo.java
 * @Description: 定时任务 组装md5 实体类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月12日 下午2:51:03
 *
 */
public class TaskAssembleMd5Vo {

	// 子任务id(逗号隔开)
	private String subtaskIds;
	// 任务类型
	private String taskType;
	// 循环模式
	private String cyclicMode;
	// 循环日期
	private String cyclicDate;
	// 项目ID
	private Integer projectId;
	// 开始日期
	private String startDate;
	// 结束日期
	private String endDate;
	// 开始时间
	private String startTime;
	// 结束日期
	private String endTime;

	public TaskAssembleMd5Vo() {

	}

	public TaskAssembleMd5Vo(String subtaskIds, String taskType, String cyclicMode, String cyclicDate,
			Integer projectId, String startDate, String endDate, String startTime, String endTime) {
		super();
		this.subtaskIds = subtaskIds;
		this.taskType = taskType;
		this.cyclicMode = cyclicMode;
		this.cyclicDate = cyclicDate;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getSubtaskIds() {
		return subtaskIds;
	}

	public void setSubtaskIds(String subtaskIds) {
		this.subtaskIds = subtaskIds;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCyclicMode() {
		return cyclicMode;
	}

	public void setCyclicMode(String cyclicMode) {
		this.cyclicMode = cyclicMode;
	}

	public String getCyclicDate() {
		return cyclicDate;
	}

	public void setCyclicDate(String cyclicDate) {
		this.cyclicDate = cyclicDate;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
