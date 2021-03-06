/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.pojo 
 * @author: syz  
 * @date: 2019年3月23日 下午4:56:56 
 */
package com.lovdmx.control.pojo.enums;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumErrRank.java
 * @Description: 枚举 错误级别
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月23日 下午4:56:56
 *
 */
public enum EnumErrRank {

	General(1, "一般"), Grave(2, "严重");

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

	private EnumErrRank(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumErrRank.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取错误等级
	public static String getErrRankName(Integer index) {
		EnumErrRank[] enumErrRanks = values();
		for (EnumErrRank enumErrRank : enumErrRanks) {
			if (enumErrRank.getIndex() == index) {
				return enumErrRank.toString();
			}
		}
		return null;
	}
}
