package com.lovdmx.control.vo;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayRealTimeVo.java
 * @Description: 继电器 实时任务 控制信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月15日 下午3:46:57
 *
 */
public class RelayRealTimeTaskCtrVo {

	// IP地址
	private String rs485toNetIP;
	// 端口
	private String port;
	// 继电器(即时任务)控制
	private List<RelayCtrVo> tasks;

	public String getRs485toNetIP() {
		return rs485toNetIP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

}
