package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: IntelligentGateway.java
 * @Description: 智能网关
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月11日 下午2:41:23
 *
 */
public class IntelligentGateway implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 924473936718843892L;
	// 标识
	private Integer id;
	// ip地址
	private String ip;
	// Mac 地址
	private String mac;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 机架ID
	private Integer rackId;
	// 机架下标
	private Integer rackIndex;
	// 机架名
	private String rackName;
	// 在线状态
	private Integer isOnline;
	// 模式（0:定时任务 1:即时任务）
	private Integer mode;
	// 任务模式（1:单任务 2:多任务）
	private Integer taskMode;
	// 版本
	private String version;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;
	// 分组继电器ip
	private List<Relay> groupRelayIp;
	// 网关设备(传感器)
	private List<GatewayDevice> sonGatewayDeviceList;

	public IntelligentGateway() {

	}

	public IntelligentGateway(String mac, Integer mode) {
		super();
		this.mac = mac;
		this.mode = mode;
	}

	public Integer getRackIndex() {
		return rackIndex;
	}

	public void setRackIndex(Integer rackIndex) {
		this.rackIndex = rackIndex;
	}

	public Integer getTaskMode() {
		return taskMode;
	}

	public void setTaskMode(Integer taskMode) {
		this.taskMode = taskMode;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac == null ? null : mac.trim();
	}

	public Integer getRackId() {
		return rackId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public List<Relay> getGroupRelayIp() {
		return groupRelayIp;
	}

	public void setGroupRelayIp(List<Relay> groupRelayIp) {
		this.groupRelayIp = groupRelayIp;
	}

	public List<GatewayDevice> getSonGatewayDeviceList() {
		return sonGatewayDeviceList;
	}

	public void setSonGatewayDeviceList(List<GatewayDevice> sonGatewayDeviceList) {
		this.sonGatewayDeviceList = sonGatewayDeviceList;
	}

}