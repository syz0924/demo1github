package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayRealTimeTaskDataFormatVo.java
 * @Description: 定时任务数据格式
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月25日 下午2:01:51
 *
 */
public class RelayTimedTaskDataFormatVo {
	// 机架id（逗号隔开）
	private String rackId;
	// 項目id
	private Integer projectId;
	// 定时任务内容
	private RelayTimedTaskInfoVo taskMsg;
	// uuid
	private String uuid;
	//任务名
	private String taskName;
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getRackId() {
		return rackId;
	}

	public void setRackId(String rackId) {
		this.rackId = rackId;
	}

	public RelayTimedTaskInfoVo getTaskMsg() {
		return taskMsg;
	}

	public void setTaskMsg(RelayTimedTaskInfoVo taskMsg) {
		this.taskMsg = taskMsg;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
