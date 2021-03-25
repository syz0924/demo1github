package com.lovdmx.control.pojo.enums;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: EnumRtrLoaded.java
 * @Description: 文件上传状态 枚举
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月15日 下午4:25:33
 *
 */
public enum EnumRtrLoaded {

	NotUpload(0,"未上传"),Upload(1,"已上传"),Distribute(2,"已分发");
	
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

	private EnumRtrLoaded(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public static boolean isEquals(String enumName, int code) {
		return EnumRtrLoaded.valueOf(enumName).getIndex() == code;
	}

}
