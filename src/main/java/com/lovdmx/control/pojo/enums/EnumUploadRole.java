package com.lovdmx.control.pojo.enums;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumUploadRole.java
 * @Description: 上传角色 枚举
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月15日 下午4:44:36
 *
 */
public enum EnumUploadRole {

	WebUpload(0, "web上传"), SpriteUpload(1, "主录放精灵上传");
	
	private int index;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private EnumUploadRole(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumUploadRole.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取任务类型
	public static String getTaskTypeName(Integer index) {
		EnumUploadRole[] enumUploadRoles = values();
		for (EnumUploadRole enumUploadRole : enumUploadRoles) {
			if (enumUploadRole.getIndex() == index) {
				return enumUploadRole.toString();
			}
		}
		return null;
	}
}
