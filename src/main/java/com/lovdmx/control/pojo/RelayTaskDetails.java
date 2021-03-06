package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayTaskDetails.java
 * @Description: 继电器定时任务详情
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月1日 上午10:08:23
 *
 */
public class RelayTaskDetails implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 2279193647594430151L;
	// 标识
	private Integer detlId;
	// 星期id
	private Integer weekId;
	// 延时时间(毫秒为单位)
	private Integer delayTime;
	// 继电器id
	private Integer relayId;
	// 回路操作列表
	private String loopNums;
	// 定时任务
	private Integer taskId;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;

	public RelayTaskDetails() {
		super();
	}


	public RelayTaskDetails(Integer weekId, Integer relayId, String loopNums) {
		super();
		this.weekId = weekId;
		this.relayId = relayId;
		this.loopNums = loopNums;
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

	public Integer getRelayId() {
		return relayId;
	}

	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
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

	public void setRelayId(Integer relayId) {
		this.relayId = relayId;
	}

	public String getLoopNums() {
		return loopNums;
	}

	public void setLoopNums(String loopNums) {
		this.loopNums = loopNums == null ? null : loopNums.trim();
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
}