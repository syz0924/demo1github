package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: BaseIntelligentGatewayVo.java
 * @Description: 智能网关 公共属性
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月15日 下午3:32:48
 *
 */
public class BaseIntelligentGatewayVo {

	// 机架ID,逗号隔开
	private String rackIds;
	// 继电器Ids
	private String relayIds;
	//智能网关 Mac
	private String gatewayMac;

	public String getRackIds() {
		return rackIds;
	}

	public void setRackIds(String rackIds) {
		this.rackIds = rackIds;
	}

	public String getRelayIds() {
		return relayIds;
	}

	public void setRelayIds(String relayIds) {
		this.relayIds = relayIds;
	}

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac;
	}
}
