package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SolveAlarmData.java
 * @Description: 获取报警数据，判断解决的报警数据
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月27日 下午4:17:14
 *
 */
public class SolveAlarmData {
	// 报警标识
	private Integer errId;
	// 机架名
	private String rackName;
	// 任务数量
	private Integer taskCount = 0;
	// 视频数量
	private Integer videoCount = 0;
	// 灯光文件数据
	private Integer edlmxCount = 0;
	// 任务数量
	private Integer deleteTaskCount = 0;
	// 任务报警状态 (0:未报警 1:以报警)
	private Integer taskOfflineAlarmStatus = 0;
	// 视频报警状态 (0:未报警 1:以报警)
	private Integer videoOfflineAlarmStatus = 0;
	// 灯光文件报警状态 (0:未报警 1:以报警)
	private Integer edlmxOfflineAlarmStatus = 0;
	// 删除任务报警状态 (0:未报警 1:以报警)
	private Integer deleteTaskOfflineAlarmStatus = 0;

	public SolveAlarmData() {
		super();
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	public Integer getEdlmxCount() {
		return edlmxCount;
	}

	public void setEdlmxCount(Integer edlmxCount) {
		this.edlmxCount = edlmxCount;
	}

	public Integer getTaskOfflineAlarmStatus() {
		return taskOfflineAlarmStatus;
	}

	public void setTaskOfflineAlarmStatus(Integer taskOfflineAlarmStatus) {
		this.taskOfflineAlarmStatus = taskOfflineAlarmStatus;
	}

	public Integer getVideoOfflineAlarmStatus() {
		return videoOfflineAlarmStatus;
	}

	public void setVideoOfflineAlarmStatus(Integer videoOfflineAlarmStatus) {
		this.videoOfflineAlarmStatus = videoOfflineAlarmStatus;
	}

	public Integer getEdlmxOfflineAlarmStatus() {
		return edlmxOfflineAlarmStatus;
	}

	public void setEdlmxOfflineAlarmStatus(Integer edlmxOfflineAlarmStatus) {
		this.edlmxOfflineAlarmStatus = edlmxOfflineAlarmStatus;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getDeleteTaskCount() {
		return deleteTaskCount;
	}

	public void setDeleteTaskCount(Integer deleteTaskCount) {
		this.deleteTaskCount = deleteTaskCount;
	}

	public Integer getDeleteTaskOfflineAlarmStatus() {
		return deleteTaskOfflineAlarmStatus;
	}

	public void setDeleteTaskOfflineAlarmStatus(Integer deleteTaskOfflineAlarmStatus) {
		this.deleteTaskOfflineAlarmStatus = deleteTaskOfflineAlarmStatus;
	}

}
