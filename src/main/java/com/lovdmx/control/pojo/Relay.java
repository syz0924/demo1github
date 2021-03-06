package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Relay.java
 * @Description: 继电器 实体类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月14日 上午10:17:22
 *
 */
public class Relay implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 8236106822846079683L;
	// 标识
	private Integer id;
	// 网关MAC
	private String gatewayMac;
	// 当前rs485toNet的IP地址
	private String rs485toNetIp;
	// 新rs485toNet的IP地址
	private String newRs485toNetIp;
	// 端口号
	private Integer port;
	// 从站地址
	private Integer slaveAddr;
	// 继电器个数
	private Integer number;
	// 继电器回路状态为“开
	private String electricRelayLoopON;
	// 继电器回路状态为“关”
	private String electricRelayLoopOFF;
	// 在线状态
	private Integer isOnline;
	// 根据rs485toNetIp分组 获取继电器
	private List<Relay> relayList;
	// 在线状态集合
	private List<Integer> onlineStateList;

	// 机柜id
	private Integer rackId;
	// 机柜名
	private String rackName;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac == null ? null : gatewayMac.trim();
	}

	public String getRackName() {
		return rackName;
	}

	public List<Integer> getOnlineStateList() {
		return onlineStateList;
	}

	public void setOnlineStateList(List<Integer> onlineStateList) {
		this.onlineStateList = onlineStateList;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String getRs485toNetIp() {
		return rs485toNetIp;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setRs485toNetIp(String rs485toNetIp) {
		this.rs485toNetIp = rs485toNetIp == null ? null : rs485toNetIp.trim();
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getElectricRelayLoopON() {
		return electricRelayLoopON;
	}

	public void setElectricRelayLoopON(String electricRelayLoopON) {
		this.electricRelayLoopON = electricRelayLoopON;
	}

	public String getElectricRelayLoopOFF() {
		return electricRelayLoopOFF;
	}

	public void setElectricRelayLoopOFF(String electricRelayLoopOFF) {
		this.electricRelayLoopOFF = electricRelayLoopOFF;
	}

	public Integer getSlaveAddr() {
		return slaveAddr;
	}

	public void setSlaveAddr(Integer slaveAddr) {
		this.slaveAddr = slaveAddr;
	}

	public List<Relay> getRelayList() {
		return relayList;
	}

	public void setRelayList(List<Relay> relayList) {
		this.relayList = relayList;
	}

	public String getNewRs485toNetIp() {
		return newRs485toNetIp;
	}

	public void setNewRs485toNetIp(String newRs485toNetIp) {
		this.newRs485toNetIp = newRs485toNetIp;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

}