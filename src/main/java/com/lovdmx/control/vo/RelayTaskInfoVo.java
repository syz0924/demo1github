package com.lovdmx.control.vo;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayTaskInfoVo.java
 * @Description: 继电器任务信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月17日 下午3:31:58
 *
 */
public class RelayTaskInfoVo extends BaseIntelligentGatewayVo {

	// 继电器定时任务信息
	private List<RelayTimedTaskInfoVo> relayTimedTaskInfoList;
	// 继电器实时任务信息
	private List<RelayRealTimeTaskInfoVo> relayRealTimeTaskList;

	public List<RelayTimedTaskInfoVo> getRelayTimedTaskInfoList() {
		return relayTimedTaskInfoList;
	}

	public void setRelayTimedTaskInfoList(List<RelayTimedTaskInfoVo> relayTimedTaskInfoList) {
		this.relayTimedTaskInfoList = relayTimedTaskInfoList;
	}

	public List<RelayRealTimeTaskInfoVo> getRelayRealTimeTaskList() {
		return relayRealTimeTaskList;
	}

	public void setRelayRealTimeTaskList(List<RelayRealTimeTaskInfoVo> relayRealTimeTaskList) {
		this.relayRealTimeTaskList = relayRealTimeTaskList;
	}

}
