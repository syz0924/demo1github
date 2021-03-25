package com.lovdmx.control.vo;

import java.util.List;

import com.lovdmx.control.pojo.RelayTaskDetails;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayTaskDetailsVo.java
 * @Description: 继电器定时任务信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月5日 下午3:11:57
 *
 */
public class RelayTaskDetailsVo {
	// 机架ID
	private Integer rackId;
	// 继电器定时任务详情
	private List<RelayTaskDetails> relayTaskDetailsList;

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public List<RelayTaskDetails> getRelayTaskDetailsList() {
		return relayTaskDetailsList;
	}

	public void setRelayTaskDetailsList(List<RelayTaskDetails> relayTaskDetailsList) {
		this.relayTaskDetailsList = relayTaskDetailsList;
	}

}
