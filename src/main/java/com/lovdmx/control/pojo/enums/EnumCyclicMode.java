package com.lovdmx.control.pojo.enums;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumCyclicMode.java
 * @Description: 循环模式 枚举
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月3日 下午5:58:06
 *
 */
public enum EnumCyclicMode {
	LoopFree(1, "无循环"), Everyday(2, "每天"), Weekly(3, "每周"), Monthly(4, "每月");
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

	private EnumCyclicMode(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumCyclicMode.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取循环模式
	public static String getCyclicModeName(Integer index) {
		EnumCyclicMode[] enumCyclicModes = values();
		for (EnumCyclicMode enumCyclicMode : enumCyclicModes) {
			if (enumCyclicMode.getIndex() == index) {
				return enumCyclicMode.toString();
			}
		}
		return null;
	}
	
	//根据匹配枚举的值获取key
    public static int getEnumNameByIndex(String enumName){
        for (EnumCyclicMode s : EnumCyclicMode.values()) {
            if(enumName.equals(s.name())){
                return s.getIndex();
            }
        }
        return 0;
    }
}
