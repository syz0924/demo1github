package com.lovdmx.control.httpVo.excelExprot.gatewayDevice;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: GatewayDeviceMacVo.java
 * @Description: 网关设备MAC
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月2日 上午11:00:41
 *
 */
public class GatewayDeviceMacVo {
	// 网关设备 mac
	private String gatewayDeviceMac;
	// 网关设备詳情集合
	private List<GatewayDeviceDetailsVo> gatewayDeviceDetailsVoList;

	public String getGatewayDeviceMac() {
		return gatewayDeviceMac;
	}

	public void setGatewayDeviceMac(String gatewayDeviceMac) {
		this.gatewayDeviceMac = gatewayDeviceMac;
	}

	public List<GatewayDeviceDetailsVo> getGatewayDeviceDetailsVoList() {
		return gatewayDeviceDetailsVoList;
	}

	public void setGatewayDeviceDetailsVoList(List<GatewayDeviceDetailsVo> gatewayDeviceDetailsVoList) {
		this.gatewayDeviceDetailsVoList = gatewayDeviceDetailsVoList;
	}

}
