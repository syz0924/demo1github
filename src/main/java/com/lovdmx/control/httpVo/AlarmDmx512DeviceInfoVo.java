package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: AlarmDmxDeviceInfoVo.java
 * @Description: DMX512 报警信息(温度,湿度,烟雾)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月9日 上午11:20:16
 *
 */
public class AlarmDmx512DeviceInfoVo {

	// 项目ID
	private Integer projectId;
	// 机架ID
	private Integer rackId;
	// 机架名
	private String rackName;
	// 报警ID
	private Integer errId;
	// 报警类型
	private String errType;
	// 温度范围
	private String temperatureRange;
	// 温度
	private Integer temperature;
	// 湿度
	private Integer humidity;
	// 烟雾
	private Integer smoke;

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getTemperatureRange() {
		return temperatureRange;
	}

	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getSmoke() {
		return smoke;
	}

	public void setSmoke(Integer smoke) {
		this.smoke = smoke;
	}

}
