/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.pojo 
 * @author: syz  
 * @date: 2019年3月23日 下午4:54:23 
 */
package com.lovdmx.control.pojo.enums;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumErrType.java
 * @Description: 枚举 错误类型
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月23日 下午4:54:23
 *
 */
public enum EnumErrType {

	OffLine(1, "离线"), Task(2, "定时任务"), Lmx(3, "灯光文件"), Video(4, "视频文件"), DeleteTask(5, "删除定时任务"),Temperature(6,"温度"),Smoke(7,"烟雾");

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

	private EnumErrType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumErrType.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取错误类型
	public static String getErrTypeName(Integer index) {
		EnumErrType[] enumErrTypes = values();
		for (EnumErrType enumErrType : enumErrTypes) {
			if (enumErrType.getIndex() == index) {
				return enumErrType.toString();
			}
		}
		return null;
	}
}
