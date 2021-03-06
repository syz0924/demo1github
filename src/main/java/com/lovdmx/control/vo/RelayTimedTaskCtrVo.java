package com.lovdmx.control.vo;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: GatewayTaskVo.java
 * @Description: 继电器 定时任务 控制信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月15日 上午10:28:56
 *
 */
public class RelayTimedTaskCtrVo {
	// 智能网关
	private String gatewayMac;
	// IP地址
	private String rs485toNetIP;
	// 端口
	private String port;
	// 继电器控制
	private List<RelayCtrVo> tasks;
	// 机架id
	private Integer rackId;

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac;
	}

	public String getRs485toNetIP() {
		return rs485toNetIP;
	}

	public void setRs485toNetIP(String rs485toNetIP) {
		this.rs485toNetIP = rs485toNetIP;
	}

	public List<RelayCtrVo> getTasks() {
		return tasks;
	}

	public void setTasks(List<RelayCtrVo> tasks) {
		this.tasks = tasks;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

}
