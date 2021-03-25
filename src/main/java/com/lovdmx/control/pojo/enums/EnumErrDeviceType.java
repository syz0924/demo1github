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
public enum EnumErrDeviceType {

	RTR(1, "RTR服务器"), DMX512(2, "控制盒"), Sprite(3, "录放精灵"), Gateway(4, "智能网关");

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

	private EnumErrDeviceType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumErrDeviceType.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取设备类型
	public static String getDeviceTypeName(Integer index) {
		EnumErrDeviceType[] enumDeviceTypes = values();
		for (EnumErrDeviceType enumDeviceType : enumDeviceTypes) {
			if (enumDeviceType.getIndex() == index) {
				return enumDeviceType.toString();
			}
		}
		return null;
	}
}
