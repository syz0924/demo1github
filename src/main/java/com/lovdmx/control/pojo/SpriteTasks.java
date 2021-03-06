package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteTasks.java
 * @Description: 存储录放精灵任务表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:55:25
 *
 */
public class SpriteTasks implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -5116112819351413456L;
	// 编号
	private Integer id;
	// MAC地址
	private String spriteMac;
	// MD5值
	private Integer taskId;
	// 状态(0:暂停 1:启用)
		private Integer status;

	public SpriteTasks() {
	}

	public SpriteTasks(String spriteMac, Integer taskId) {
		super();
		this.spriteMac = spriteMac;
		this.taskId = taskId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpriteMac() {
		return spriteMac;
	}

	public void setSpriteMac(String spriteMac) {
		this.spriteMac = spriteMac;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}