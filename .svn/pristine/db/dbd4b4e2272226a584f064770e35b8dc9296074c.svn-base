package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RealtimeTasks.java
 * @Description: (智能网关)即时任务
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月25日 上午10:11:42
 *
 */
public class RealtimeTasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 7889622622297378233L;
	// 标识
	private Integer taskId;
	// 任务名
	private String taskName;
	// 创建时间
	private Date createTime;
	// 任务内容(JSON格式)
	private String taskData;
	// 项目ID
	private Integer projectId;
	// 项目名
	private String projectName;

	public RealtimeTasks() {

	}

	public RealtimeTasks(String taskName, Date createTime, String taskData, Integer projectId) {
		super();
		this.taskName = taskName;
		this.createTime = createTime;
		this.taskData = taskData;
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTaskData() {
		return taskData;
	}

	public void setTaskData(String taskData) {
		this.taskData = taskData == null ? null : taskData.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}