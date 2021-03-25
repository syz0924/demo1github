package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteEdlmx.java
 * @Description: 存储录放精灵灯光文件表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:54:35
 *
 */
public class SpriteEdlmx implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 7616242635286853918L;
	// 编号
	private Integer id;
	// MAC地址
	private String spriteMac;
	// 灯光文件名
	private String newlmxName;
	// 源灯光文件MD5值
	private String orglmxName;

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

	public String getNewlmxName() {
		return newlmxName;
	}

	public void setNewlmxName(String newlmxName) {
		this.newlmxName = newlmxName;
	}

	public String getOrglmxName() {
		return orglmxName;
	}

	public void setOrglmxName(String orglmxName) {
		this.orglmxName = orglmxName;
	}

}