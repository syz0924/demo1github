package com.lovdmx.control.pojo.enums;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumCurMode.java
 * @Description: 工作模式 枚举
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月2日 下午3:41:13
 *
 */
public enum EnumCurMode {

	NOMode(1, "NOMode"), RTR(2, "RTR"), VIDEO(3, "VIDEO"), PICTURE(4, "PICTURE"), HDMI(5, "HDMI"), ERROR(6, "ERROR");
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

	private EnumCurMode(int index, String value) {
		this.index = index;
		this.value = value;
	}

	// 根据下标获取任务类型
	public static String getEnumCurModeName(Integer index) {
		EnumCurMode[] enumCurModes = values();
		for (EnumCurMode enumCurMode : enumCurModes) {
			if (enumCurMode.getIndex() == index) {
				return enumCurMode.toString();
			}
		}
		return null;
	}
}
