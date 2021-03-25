/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月1日 上午11:33:50 
 */
package com.lovdmx.control.httpVo;

import java.io.Serializable;
import java.util.List;

import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.httpVo.VideoFile;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Catalogue.java
 * @Description: (主录放精灵)目录列表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月1日 上午11:33:50
 *
 */
public class Directory implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 5748101255108519411L;
	// 目录下标
	private Integer directoryIndex;
	// 目录名
	private String directoryName;
	// 灯光文件
	private List<LightFile> lightFileList;
	// 灯光文件
	private List<VideoFile> videoFileList;

	public Integer getDirectoryIndex() {
		return directoryIndex;
	}

	public void setDirectoryIndex(Integer directoryIndex) {
		this.directoryIndex = directoryIndex;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public List<LightFile> getLightFileList() {
		return lightFileList;
	}

	public void setLightFileList(List<LightFile> lightFileList) {
		this.lightFileList = lightFileList;
	}

	public List<VideoFile> getVideoFileList() {
		return videoFileList;
	}

	public void setVideoFileList(List<VideoFile> videoFileList) {
		this.videoFileList = videoFileList;
	}

}
