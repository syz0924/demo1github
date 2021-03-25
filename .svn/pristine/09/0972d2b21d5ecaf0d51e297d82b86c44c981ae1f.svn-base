package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: delSpriteTasks.java
 * @Description: 从录放精灵以删除的定时任务
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月26日 下午3:33:13
 *
 */
public class DelSpriteTasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -3587472594153695288L;
	// 标识
	private Integer id;
	// 删除的定时任务id
	private Integer delTaskId;
	// 项目id
	private Integer projectId;
	// 录放精灵Mac
	private String spriteMac;
	// 删除时间
	private Date deleteTime;

	public DelSpriteTasks() {
		super();
	}

	public DelSpriteTasks(Integer delTaskId, Integer projectId, String spriteMac, Date deleteTime) {
		super();
		this.delTaskId = delTaskId;
		this.projectId = projectId;
		this.spriteMac = spriteMac;
		this.deleteTime = deleteTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDelTaskId() {
		return delTaskId;
	}

	public void setDelTaskId(Integer delTaskId) {
		this.delTaskId = delTaskId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getSpriteMac() {
		return spriteMac;
	}

	public void setSpriteMac(String spriteMac) {
		this.spriteMac = spriteMac == null ? null : spriteMac.trim();
	}
}