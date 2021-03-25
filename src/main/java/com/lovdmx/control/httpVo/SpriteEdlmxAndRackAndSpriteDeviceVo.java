package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RackAndSpriteDeviceAndSpriteEdlmx.java
 * @Description: 展示录放精灵灯光文件信息（机柜和录放精灵设备）
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月17日 上午10:15:42
 *
 */
public class SpriteEdlmxAndRackAndSpriteDeviceVo {

	// 编号
	private Integer edlmxId;
	// MAC地址
	private String spriteMac;
	// 灯光文件名
	private String newlmxName;
	// 源灯光文件MD5值
	private String orglmxName;
	// 机柜id
	private Integer rackId;
	// 机柜名
	private String rackName;

	public Integer getEdlmxId() {
		return edlmxId;
	}

	public void setEdlmxId(Integer edlmxId) {
		this.edlmxId = edlmxId;
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

}
