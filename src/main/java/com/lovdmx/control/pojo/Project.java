package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Project.java
 * @Description: 项目表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:38:51
 *
 */
public class Project implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 8393781890090421169L;
	// 编号
	private Integer projectId;
	// 项目名
	private String projectName;
	// 创建时间
	private Date createTime;
	// 机架集合（子类）
	private List<RackDevice> sonRackDeviceList;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public List<RackDevice> getSonRackDeviceList() {
		return sonRackDeviceList;
	}

	public void setSonRackDeviceList(List<RackDevice> sonRackDeviceList) {
		this.sonRackDeviceList = sonRackDeviceList;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", createTime=" + createTime
				+ ", sonRackDeviceList=" + sonRackDeviceList + "]";
	}
}