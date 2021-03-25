package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MonitoringDevice.java
 * @Description: 监控设备
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月24日 上午11:12:22
 *
 */
public class MonitoringDevice implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 6109761191251905510L;
	// 标识下标
	private Integer deviceId;
	// 设备序列号（标识）
	private String deviceSerial;
	// 授权码
	private String authCode;
	// 云石萤账号
	private String appKey;
	// 程序秘钥
	private String appSecret;
	// 设备名
	private String deviceName;
	// 机柜id
	private Integer rackId;
	// 项目id
	private Integer projectId;
	// 项目名
	private String projectName;
	// 机柜名
	private String rackName;
	// 凭证
	private String accessToken;
	// 过期时间
	private Date expirationTime;
	// 创建时间
	private Date createTime;

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial == null ? null : deviceSerial.trim();
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode == null ? null : authCode.trim();
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey == null ? null : appKey.trim();
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret == null ? null : appSecret.trim();
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName == null ? null : deviceName.trim();
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken == null ? null : accessToken.trim();
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}