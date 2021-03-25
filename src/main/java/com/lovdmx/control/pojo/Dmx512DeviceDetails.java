package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512DeviceDetails.java
 * @Description: dmx512 控制盒詳情（历史记录 主要保存几天数据）
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月17日 下午4:11:17
 *
 */
public class Dmx512DeviceDetails implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -5321159812987064886L;
	// 控制盒mac
	private String dmx512Mac;
	// 温度和湿度和烟雾(1:温度 2:湿度 3:烟雾 ,逗号隔开)
	private String rdmts;
	// 温度
	private String temperatures;
	// 湿度
	private String humiditys;
	// 创建时间
	private Date createTime;
	// 時間
	private String createTimes;

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	public String getDmx512Mac() {
		return dmx512Mac;
	}

	public void setDmx512Mac(String dmx512Mac) {
		this.dmx512Mac = dmx512Mac == null ? null : dmx512Mac.trim();
	}

	public String getRdmts() {
		return rdmts;
	}

	public void setRdmts(String rdmts) {
		this.rdmts = rdmts == null ? null : rdmts.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(String temperatures) {
		this.temperatures = temperatures;
	}

	public String getHumiditys() {
		return humiditys;
	}

	public void setHumiditys(String humiditys) {
		this.humiditys = humiditys;
	}

}