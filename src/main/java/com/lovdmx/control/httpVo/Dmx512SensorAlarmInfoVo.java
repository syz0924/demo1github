package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512SensorAlarmInfoVo.java
 * @Description: dmx512 传感器报警信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月9日 下午2:13:09
 *
 */
public class Dmx512SensorAlarmInfoVo {

	// 项目id
	private Integer projectId;
	// 机柜id
	private Integer rackId;
	// 机柜名称
	private String rackName;
	// dmx512 ip
	private String dmx512Ip;
	// dmx512 mac
	private String dmx512Mac;
	// 温度范围
	private String temperatureRange;
	// 湿度范围
	private String humidityRange;
	// 温度
	private Integer temperature;
	// 湿度
	private Integer humidity;

	public String getTemperatureRange() {
		return temperatureRange;
	}

	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange;
	}

	public String getHumidityRange() {
		return humidityRange;
	}

	public void setHumidityRange(String humidityRange) {
		this.humidityRange = humidityRange;
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

	public String getDmx512Ip() {
		return dmx512Ip;
	}

	public void setDmx512Ip(String dmx512Ip) {
		this.dmx512Ip = dmx512Ip;
	}

	public String getDmx512Mac() {
		return dmx512Mac;
	}

	public void setDmx512Mac(String dmx512Mac) {
		this.dmx512Mac = dmx512Mac;
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

}
