package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: UploadVideos.java
 * @Description: 已上传视频文件表(视频文件上传到公共网盘)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午11:08:21
 *
 */
public class UploadVideos implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -7305095049049063711L;
	// 编号
	private Integer videoId;
	// 文件路径
	private String filePath;
	// 文件名
	private String fileName;
	// 项目ID
	private Integer projectId;
	// 项目名称
	private String projectName;
	// 分发状态（0:未发布到RTR服务器 1:以发布）
	private Integer rtrLoaded;
	// 分发状态值
	private String rtrLoadedValue;
	// 位置值
	private Integer fileIndex;
	// MD5值
	private String md5;
	// 视频时长
	private Integer time;
	// 上传角色（0：web端,1:主录放精灵）
	private Integer uploadRole;
	// 上传角色值
	private String uploadRoleValue;
	// 上传进度
	private Integer progressPercentage;
	// 上传文件路径
	private Integer fileType;
	// 判断是否存在
	private boolean isExits;

	public UploadVideos() {
	}

	/**
	 * @Function: UploadVideos.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 上午10:39:52
	 */
	public UploadVideos(String filePath, String fileName, Integer projectId, Integer rtrLoaded, Integer fileIndex,
			String md5, Integer time, Integer uploadRole) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.projectId = projectId;
		this.rtrLoaded = rtrLoaded;
		this.fileIndex = fileIndex;
		this.md5 = md5;
		this.time = time;
		this.uploadRole = uploadRole;
	}

	public String getRtrLoadedValue() {
		return rtrLoadedValue;
	}

	public void setRtrLoadedValue(String rtrLoadedValue) {
		this.rtrLoadedValue = rtrLoadedValue;
	}

	public String getUploadRoleValue() {
		return uploadRoleValue;
	}

	public void setUploadRoleValue(String uploadRoleValue) {
		this.uploadRoleValue = uploadRoleValue;
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

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
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

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getRtrLoaded() {
		return rtrLoaded;
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

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5 == null ? null : md5.trim();
	}

	public Integer getUploadRole() {
		return uploadRole;
	}

	public void setUploadRole(Integer uploadRole) {
		this.uploadRole = uploadRole;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}