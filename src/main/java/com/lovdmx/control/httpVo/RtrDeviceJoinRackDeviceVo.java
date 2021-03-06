package com.lovdmx.control.httpVo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RtrDeviceJoinRackDeviceJoinProjectVo.java
 * @Description: 展示 RTR设备信息(RTR，机柜,项目 多表连接)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月17日 下午3:26:12
 *
 */
public class RtrDeviceJoinRackDeviceVo implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 3502486050298062755L;
	// 编号
	private Integer rtrId;
	// MAC地址
	private String rtrMac;
	// 模型尺寸
	private String modelSize;
	// 当前工作模式
	private String curMode;
	// 工作模式下标
	private Integer modeId;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 机架ID
	private Integer rackId;
	// 机架名
	private String rackName;
	// 机架下标
	private Integer rackIndex;
	// 在线状态 （0未在线 1在线）
	private Short isOnline;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;
	// 以丢失的 视频名
	private String loseVideoNames;
	// 以存在的 视频名
	private String existVideoNames;

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

	public Integer getRtrId() {
		return rtrId;
	}

	public void setRtrId(Integer rtrId) {
		this.rtrId = rtrId;
	}

	public String getRtrMac() {
		return rtrMac;
	}

	public void setRtrMac(String rtrMac) {
		this.rtrMac = rtrMac;
	}

	public String getModelSize() {
		return modelSize;
	}

	public void setModelSize(String modelSize) {
		this.modelSize = modelSize;
	}

	public String getCurMode() {
		return curMode;
	}

	public void setCurMode(String curMode) {
		this.curMode = curMode;
	}

	public Integer getModeId() {
		return modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getRackIndex() {
		return rackIndex;
	}

	public void setRackIndex(Integer rackIndex) {
		this.rackIndex = rackIndex;
	}

	public Short getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Short isOnline) {
		this.isOnline = isOnline;
	}

	public String getLoseVideoNames() {
		return loseVideoNames;
	}

	public void setLoseVideoNames(String loseVideoNames) {
		this.loseVideoNames = loseVideoNames;
	}

	public String getExistVideoNames() {
		return existVideoNames;
	}

	public void setExistVideoNames(String existVideoNames) {
		this.existVideoNames = existVideoNames;
	}

}
