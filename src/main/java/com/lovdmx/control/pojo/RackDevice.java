package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RackDevice.java
 * @Description: 小型服务器机架表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:39:30
 *
 */
public class RackDevice implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 3299563032155639859L;
	// 编号
	private Integer rackId;
	// 机架编号
	private Integer rackIndex;
	// 机架名称
	private String rackName;
	// 项目ID
	private Integer projectId;
	// 视频切割起点x
	private Integer splitVideoX;
	// 视频切割起点y
	private Integer splitVideoY;
	// 视频切割width
	private Integer splitVideoWidth;
	// 视频切割height
	private Integer splitVideoHeight;
	// 经度
	private Double rackLongitude;
	// 纬度
	private Double rackLatitude;
	// 实时电流
	private Integer current;
	// 实时电压
	private Integer voltage;
	// 创建时间
	private Date createTime;
	// 报警信息ID
	private Integer errId;
	// RTR服务器集合信息(子类)
	private List<RtrDevice> sonRtrDeviceList;
	// 录放精灵集合信息(子类)
	private List<SpriteDevice> sonSpriteDeviceList;
	// 监控集合信息（子类）
	private List<MonitoringDevice> sonMonitoringDeviceList;
	// 智能网关信息(子类)
	private List<IntelligentGateway> sonIntelligentGatewayList;
	// 项目 名
	private String projectName;

	public RackDevice() {
	}

	/**
	 * @Function: RackDevice.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午1:45:07
	 */
	public RackDevice(Integer rackIndex, String rackName, Integer projectId, Integer current, Integer voltage,
			Date createTime) {
		super();
		this.rackIndex = rackIndex;
		this.rackName = rackName;
		this.projectId = projectId;
		this.current = current;
		this.voltage = voltage;
		this.createTime = createTime;
	}

	public List<IntelligentGateway> getSonIntelligentGatewayList() {
		return sonIntelligentGatewayList;
	}

	public void setSonIntelligentGatewayList(List<IntelligentGateway> sonIntelligentGatewayList) {
		this.sonIntelligentGatewayList = sonIntelligentGatewayList;
	}

	public Integer getRackId() {
		return rackId;
	}

	public Integer getErrId() {
		return errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	public Integer getSplitVideoX() {
		return splitVideoX;
	}

	public void setSplitVideoX(Integer splitVideoX) {
		this.splitVideoX = splitVideoX;
	}

	public Integer getSplitVideoY() {
		return splitVideoY;
	}

	public void setSplitVideoY(Integer splitVideoY) {
		this.splitVideoY = splitVideoY;
	}

	public Integer getSplitVideoWidth() {
		return splitVideoWidth;
	}

	public void setSplitVideoWidth(Integer splitVideoWidth) {
		this.splitVideoWidth = splitVideoWidth;
	}

	public Integer getSplitVideoHeight() {
		return splitVideoHeight;
	}

	public void setSplitVideoHeight(Integer splitVideoHeight) {
		this.splitVideoHeight = splitVideoHeight;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getRackIndex() {
		return rackIndex;
	}

	public void setRackIndex(Integer rackIndex) {
		this.rackIndex = rackIndex;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName == null ? null : rackName.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public List<RtrDevice> getSonRtrDeviceList() {
		return sonRtrDeviceList;
	}

	public void setSonRtrDeviceList(List<RtrDevice> sonRtrDeviceList) {
		this.sonRtrDeviceList = sonRtrDeviceList;
	}

	public List<SpriteDevice> getSonSpriteDeviceList() {
		return sonSpriteDeviceList;
	}

	public void setSonSpriteDeviceList(List<SpriteDevice> sonSpriteDeviceList) {
		this.sonSpriteDeviceList = sonSpriteDeviceList;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getRackLongitude() {
		return rackLongitude;
	}

	public void setRackLongitude(Double rackLongitude) {
		this.rackLongitude = rackLongitude;
	}

	public Double getRackLatitude() {
		return rackLatitude;
	}

	public void setRackLatitude(Double rackLatitude) {
		this.rackLatitude = rackLatitude;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<MonitoringDevice> getSonMonitoringDeviceList() {
		return sonMonitoringDeviceList;
	}

	public void setSonMonitoringDeviceList(List<MonitoringDevice> sonMonitoringDeviceList) {
		this.sonMonitoringDeviceList = sonMonitoringDeviceList;
	}

	@Override
	public String toString() {
		return "RackDevice [rackId=" + rackId + ", rackIndex=" + rackIndex + ", rackName=" + rackName + ", projectId="
				+ projectId + ", splitVideoX=" + splitVideoX + ", splitVideoY=" + splitVideoY + ", splitVideoWidth="
				+ splitVideoWidth + ", splitVideoHeight=" + splitVideoHeight + ", rackLongitude=" + rackLongitude
				+ ", rackLatitude=" + rackLatitude + ", current=" + current + ", voltage=" + voltage + ", createTime="
				+ createTime + ", errId=" + errId + ", sonRtrDeviceList=" + sonRtrDeviceList + ", sonSpriteDeviceList="
				+ sonSpriteDeviceList + ", projectName=" + projectName + "]";
	}

}