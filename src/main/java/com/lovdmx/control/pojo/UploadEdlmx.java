package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: UploadEdlmx.java
 * @Description: 已上传灯光文件表(灯光文件上传到公共网盘)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午11:03:55
 *
 */
public class UploadEdlmx implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -2897979773025036079L;
	// 编号
	private Integer edlmxId;
	// 文件路径(网盘路径)
	private String filePath;
	// 文件名
	private String fileName;
	// 项目ID
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 发布状态（0:未发布 1:已发布）
	private Integer rtrLoaded;
	// 分发状态值
	private String rtrLoadedValue;
	// 位置值
	private Integer fileIndex;
	// MD5值
	private String md5;
	// 上传角色（0：web端,1:主录放精灵）
	private Integer uploadRole;
	// 上传角色值
	private String uploadRoleValue;
	// 上传进度
	private Integer progressPercentage;
	// 上传文件路径
	private Integer fileType;
	// 灯光文件时长
	private Integer time;
	// 判断是否存在
	private boolean isExits;

	public UploadEdlmx() {
	}

	/**
	 * @Function: UploadEdlmx.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 上午11:37:22
	 */
	public UploadEdlmx(String filePath, Integer projectId, Integer rtrLoaded, Integer fileIndex, String md5,
			Integer uploadRole, Integer time) {
		super();
		this.filePath = filePath;
		this.projectId = projectId;
		this.rtrLoaded = rtrLoaded;
		this.fileIndex = fileIndex;
		this.md5 = md5;
		this.uploadRole = uploadRole;
		this.time = time;
	}

	public UploadEdlmx(Integer edlmxId, String filePath, Integer projectId, Integer rtrLoaded, Integer fileIndex,
			String md5, Integer uploadRole, Integer progressPercentage, Integer fileType, Integer time,
			boolean isExits) {
		super();
		this.edlmxId = edlmxId;
		this.filePath = filePath;
		this.projectId = projectId;
		this.rtrLoaded = rtrLoaded;
		this.fileIndex = fileIndex;
		this.md5 = md5;
		this.uploadRole = uploadRole;
		this.progressPercentage = progressPercentage;
		this.fileType = fileType;
		this.time = time;
		this.isExits = isExits;
	}

	public String getUploadRoleValue() {
		return uploadRoleValue;
	}

	public void setUploadRoleValue(String uploadRoleValue) {
		this.uploadRoleValue = uploadRoleValue;
	}

	public String getRtrLoadedValue() {
		return rtrLoadedValue;
	}

	public void setRtrLoadedValue(String rtrLoadedValue) {
		this.rtrLoadedValue = rtrLoadedValue;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getEdlmxId() {
		return edlmxId;
	}

	public boolean isExits() {
		return isExits;
	}

	public void setExits(boolean isExits) {
		this.isExits = isExits;
	}

	public Integer getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(Integer progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public void setEdlmxId(Integer edlmxId) {
		this.edlmxId = edlmxId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath == null ? null : filePath.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRtrLoaded() {
		return rtrLoaded;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setRtrLoaded(Integer rtrLoaded) {
		this.rtrLoaded = rtrLoaded;
	}

	public Integer getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(Integer fileIndex) {
		this.fileIndex = fileIndex;
	}

	public Integer getUploadRole() {
		return uploadRole;
	}

	public void setUploadRole(Integer uploadRole) {
		this.uploadRole = uploadRole;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5 == null ? null : md5.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}