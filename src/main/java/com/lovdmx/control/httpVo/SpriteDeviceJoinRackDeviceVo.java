package com.lovdmx.control.httpVo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteDeviceJoinRackDeviceVo.java
 * @Description: 展示 Sprite设备信息(Sprite，机柜 多表连接)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月17日 下午4:15:43
 *
 */
public class SpriteDeviceJoinRackDeviceVo implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -4844714875454908949L;
	// 编号
	private Integer spriteId;
	// MAC地址
	private String spriteMac;
	// 项目id
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 机架ID
	private Integer rackId;
	// 机柜名
	private String rackName;
	// 机柜下标
	private Integer rackIndex;
	// 在线状态（0未在线 1在线）
	private Short isOnline;
	// 在线时间
	private Date onlineTime;
	// 离线时间
	private Date lastTime;
	// 已丢失的 灯光文件名
	private String loseLmxNames;
	// 已存在的 灯光文件名
	private String existLmxNames;
	// 已丢失的 任务名
	private String loseTaskNames;
	// 已存在的 任务名
	private String existTaskNames;

	public Integer getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(Integer spriteId) {
		this.spriteId = spriteId;
	}

	public String getSpriteMac() {
		return spriteMac;
	}

	public void setSpriteMac(String spriteMac) {
		this.spriteMac = spriteMac;
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

	public String getLoseLmxNames() {
		return loseLmxNames;
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

	public void setLoseLmxNames(String loseLmxNames) {
		this.loseLmxNames = loseLmxNames;
	}

	public String getExistLmxNames() {
		return existLmxNames;
	}

	public void setExistLmxNames(String existLmxNames) {
		this.existLmxNames = existLmxNames;
	}

	public String getLoseTaskNames() {
		return loseTaskNames;
	}

	public void setLoseTaskNames(String loseTaskNames) {
		this.loseTaskNames = loseTaskNames;
	}

	public String getExistTaskNames() {
		return existTaskNames;
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

	public void setExistTaskNames(String existTaskNames) {
		this.existTaskNames = existTaskNames;
	}

}
