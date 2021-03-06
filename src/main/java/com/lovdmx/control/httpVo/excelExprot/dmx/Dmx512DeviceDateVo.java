package com.lovdmx.control.httpVo.excelExprot.dmx;

import java.util.List;
/**
 * 
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Dmx512DeviceDateVo.java
* @Description: dmx512设备日期
*
* @version: v1.0.0
* @author: syz
* @date: 2019年9月20日 上午10:59:20 
*
 */
public  class Dmx512DeviceDateVo {
	// 日期
	private String dmx512Date;
	// dmx512MAC集合
	private List<Dmx512DeviceMacVo> dmx512MacList;

	public List<Dmx512DeviceMacVo> getDmx512MacList() {
		return dmx512MacList;
	}

	public void setDmx512MacList(List<Dmx512DeviceMacVo> dmx512MacList) {
		this.dmx512MacList = dmx512MacList;
	}

	public String getDmx512Date() {
		return dmx512Date;
	}

	public void setDmx512Date(String dmx512Date) {
		this.dmx512Date = dmx512Date;
	}

}