package com.lovdmx.control.pojo.enums;

public enum EnumOperationMode {

	ADD(1, "添加"), DELETE(2, "删除"), UPDATE(3, "修改");

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

	private EnumOperationMode(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumOperationMode.valueOf(enumName).getIndex() == code;
	}

	// 根据下标获取操作类型
	public static String getOperationModeName(Integer index) {
		EnumOperationMode[] enumOperationModes = values();
		for (EnumOperationMode enumOperationMode : enumOperationModes) {
			if (enumOperationMode.getIndex() == index) {
				return enumOperationMode.toString();
			}
		}
		return null;
	}
}
