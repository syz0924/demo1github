package com.lovdmx.control.vo;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: IntelligentGatewayVo.java
 * @Description: 继电器 实时任务详情信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月11日 下午3:29:08
 *
 */
public class RelayRealTimeTaskInfoVo {
	// 唯一标识
	private String uuid;
	// 延时时间(毫秒为单位)
	private String delaymsc;
	// 内容md5值
	private String taskMd5;

	// 定时任务
	private List<RelayRealTimeTaskCtrVo> realTimeTasks;

	public String getTaskMd5() {
		return taskMd5;
	}

	public void setTaskMd5(String taskMd5) {
		this.taskMd5 = taskMd5;
	}

	public String getDelaymsc() {
		return delaymsc;
	}

	public void setDelaymsc(String delaymsc) {
		this.delaymsc = delaymsc;
	}

	public List<RelayRealTimeTaskCtrVo> getRealTimeTasks() {
		return realTimeTasks;
	}

	public void setRealTimeTasks(List<RelayRealTimeTaskCtrVo> realTimeTasks) {
		this.realTimeTasks = realTimeTasks;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
