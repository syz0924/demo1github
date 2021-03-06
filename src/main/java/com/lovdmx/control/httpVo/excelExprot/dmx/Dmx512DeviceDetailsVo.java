package com.lovdmx.control.httpVo.excelExprot.dmx;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512DeviceDetailsVo.java
 * @Description: Dmx512控制盒 詳情（打印Excel表单）
 *
 * @version: v1.0.0
 * @author: syz
 * @String: 2019年9月18日 下午2:43:01
 *
 */
public class Dmx512DeviceDetailsVo {

	// 温度
	private Integer temperature = 0;
	// 湿度
	private Integer humidity = 0;
	// 烟雾
	private String smokeAlarmSate = "无";
	// 创建时间
	private String createTime;

	public Dmx512DeviceDetailsVo() {

	}

	public Dmx512DeviceDetailsVo(Integer temperature, Integer humidity, String smokeAlarmSate, String createTime) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.smokeAlarmSate = smokeAlarmSate;
		this.createTime = createTime;
	}

	public String getSmokeAlarmSate() {
		return smokeAlarmSate;
	}

	public void setSmokeAlarmSate(String smokeAlarmSate) {
		this.smokeAlarmSate = smokeAlarmSate;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
