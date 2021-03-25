package com.lovdmx.control.pojo.enums;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumTaskType.java
 * @Description: 任务类型 枚举
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月3日 下午5:57:20
 *
 */
public enum EnumTaskType {

	NormalMode(1, "常规模式"), VacationMode(2, "假期模式"), EventMode(3, "事件模式");
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

	private EnumTaskType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumTaskType.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取任务类型
	public static String getTaskTypeName(Integer index) {
		EnumTaskType[] enumTaskTypes = values();
		for (EnumTaskType enumTaskType : enumTaskTypes) {
			if (enumTaskType.getIndex() == index) {
				return enumTaskType.toString();
			}
		}
		return null;
	}
	
	//根据匹配枚举的值获取key
    public static int getEnumNameByIndex(String enumName){
        for (EnumTaskType s : EnumTaskType.values()) {
            if(enumName.equals(s.name())){
                return s.getIndex();
            }
        }
        return 0;
    }
    
}
