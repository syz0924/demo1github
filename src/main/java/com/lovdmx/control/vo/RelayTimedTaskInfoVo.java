package com.lovdmx.control.vo;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: IntelligentGatewayVo.java
 * @Description: 继电器 定时任务详情信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月11日 下午3:29:08
 *
 */
public class RelayTimedTaskInfoVo {
	// 唯一标识
	private String uuid;
	// 选中的星期几（多选）,逗号隔开
	private String dayofweek;
	// 延时时间(毫秒为单位)
	private String delaymsc;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 内容md5值
	private String taskMd5;
	// 任务模式
	private Integer taskMode;
	// 定时任务
	private List<RelayTimedTaskCtrVo> timlyTasks;

	public String getTaskMd5() {
		return taskMd5;
	}

	public void setTaskMd5(String taskMd5) {
		this.taskMd5 = taskMd5;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public Integer getTaskMode() {
		return taskMode;
	}

	public void setTaskMode(Integer taskMode) {
		this.taskMode = taskMode;
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

	public List<RelayTimedTaskCtrVo> getTimlyTasks() {
		return timlyTasks;
	}

	public void setTimlyTasks(List<RelayTimedTaskCtrVo> timlyTasks) {
		this.timlyTasks = timlyTasks;
	}

	public String getDelaymsc() {
		return delaymsc;
	}

	public void setDelaymsc(String delaymsc) {
		this.delaymsc = delaymsc;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
