package com.lovdmx.control.httpVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MonitoringVo.java
 * @Description: 监控 访问（https://open.ys7.com/api/lapp/device/ptz/start）
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月23日 下午2:45:37
 *
 */
public class MonitoringVo {
	// 授权过程获取的access_token
	private String accessToken;
	// 设备序列号,存在英文字母的设备序列号,字母需要大写
	private String deviceSerial;
	// 通道号
	private Integer channelNo;
	// 操作命令
	private Integer direction;
	// 云台速度
	private Integer speed;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public Integer getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

}
