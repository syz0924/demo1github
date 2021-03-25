package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: GatewayDevice.java
 * @Description: 网关设备（传感器）实时
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年10月31日 上午10:22:58
 *
 */
public class GatewayDevice implements Serializable{
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 3056242312326562236L;
	//标识	
	private Integer id;
	// ip地址
	private String ip;
	//设备 MAC
	private String deviceMac;
	//网关MAC
	private String gatewayMac;
	// C16 组号
	private String universe;
	//温度范围
	private String temperatureRange;
	//温度
	private Integer temp;
	//湿度
	private Integer humi;
	//烟雾报警（0:未报警 1:报警）
	private Integer fire;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac == null ? null : deviceMac.trim();
	}

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac == null ? null : gatewayMac.trim();
	}

	public String getUniverse() {
		return universe;
	}

	public void setUniverse(String universe) {
		this.universe = universe == null ? null : universe.trim();
	}

	public String getTemperatureRange() {
		return temperatureRange;
	}

	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange == null ? null : temperatureRange.trim();
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Integer getHumi() {
		return humi;
	}

	public void setHumi(Integer humi) {
		this.humi = humi;
	}

	public Integer getFire() {
		return fire;
	}

	public void setFire(Integer fire) {
		this.fire = fire;
	}
}