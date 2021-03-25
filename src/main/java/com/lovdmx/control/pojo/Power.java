package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Power.java
 * @Description: 权限
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月19日 下午4:18:56
 *
 */
public class Power implements Serializable{
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -3323873346012966701L;
	//标识
	private Integer id;
	//权限名称
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}