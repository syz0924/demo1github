package com.lovdmx.control.httpVo.excelExprot.dmx;

import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ExcelExportDmx512DeviceVo.java
 * @Description: 导出dmx512数据到excel表单
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月19日 下午3:52:23
 *
 */
public class ExcelExportDmx512DeviceVo {

	// RTR设备
	private String rtrMac;
	// 机架名
	private String rackName;
	// 项目名
	private String projectName;
	// dmx512日期 集合
	private List<Dmx512DeviceDateVo> dmx512DateList;

	public String getRtrMac() {
		return rtrMac;
	}

	public void setRtrMac(String rtrMac) {
		this.rtrMac = rtrMac;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Dmx512DeviceDateVo> getDmx512DateList() {
		return dmx512DateList;
	}

	public void setDmx512DateList(List<Dmx512DeviceDateVo> dmx512DateList) {
		this.dmx512DateList = dmx512DateList;
	}

}
