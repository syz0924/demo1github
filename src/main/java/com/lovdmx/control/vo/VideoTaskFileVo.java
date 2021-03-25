package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: TaskVideoFileVo.java
 * @Description: 视频文件任务信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月25日 下午2:55:38
 *
 */
public class VideoTaskFileVo {

	// 上传视频ID
	private Integer uploadVideoId;
	// 视频文件小标
	private Integer fileIndex;
	// 播放任务下标
	private Integer index;

	public VideoTaskFileVo() {
	}

	public VideoTaskFileVo(Integer fileIndex, Integer index) {
		super();
		this.fileIndex = fileIndex;
		this.index = index;
	}

	public Integer getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(Integer fileIndex) {
		this.fileIndex = fileIndex;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getUploadVideoId() {
		return uploadVideoId;
	}

	public void setUploadVideoId(Integer uploadVideoId) {
		this.uploadVideoId = uploadVideoId;
	}

}
