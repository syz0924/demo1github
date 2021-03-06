/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.pojo 
 * @author: syz  
 * @date: 2019年3月23日 下午4:47:26 
 */
package com.lovdmx.control.pojo.enums;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumDeviceType.java
 * @Description: 枚举 设备类型
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月23日 下午4:47:26
 *
 */
public enum EnumDeviceType {

	RTR(1, "RTR服务器"), Sprite(2, "录放精灵"), Control(3, "中控服务器");

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

	private EnumDeviceType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumDeviceType.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取设备类型
	public static String getDeviceTypeName(Integer index) {
		EnumDeviceType[] enumDeviceTypes = values();
		for (EnumDeviceType enumDeviceType : enumDeviceTypes) {
			if (enumDeviceType.getIndex() == index) {
				return enumDeviceType.toString();
			}
		}
		return null;
	}
}
