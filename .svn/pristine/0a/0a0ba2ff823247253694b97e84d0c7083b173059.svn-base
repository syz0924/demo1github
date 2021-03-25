/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.pojo 
 * @author: syz  
 * @date: 2019年2月22日 下午4:36:28 
 */
package com.lovdmx.control.pojo.enums;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumFileType.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月22日 下午4:36:28
 *
 */
public enum EnumFileType {
	LightingFile(1, "灯光文件"), VideoFile(2, "视频文件");

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

	private EnumFileType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumFileType.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取文件类型
	public static String getFileTypeName(Integer index) {
		EnumFileType[] enumFileTypes = values();
		for (EnumFileType enumFileType : enumFileTypes) {
			if (enumFileType.getIndex() == index) {
				return enumFileType.toString();
			}
		}
		return null;
	}

}
