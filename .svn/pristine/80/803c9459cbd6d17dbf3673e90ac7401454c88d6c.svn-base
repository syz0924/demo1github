/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月1日 上午11:37:23 
 */
package com.lovdmx.control.httpVo;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: LightFile.java
 * @Description: (主录放精灵)灯光文件 列表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月1日 上午11:37:23
 *
 */
public class LightFile implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 534460245537232141L;
	// 下标
	private Integer index;
	// 文件全路径
	private String filePath;
	// 文件名
	private String name;
	// 时长
	private Integer time;
	// md5值
	private String md5;
	// 上传状态(0:未上传 , 1:已上传 2:已分发)
	private Integer status = 0;
	// 文件类型
	private String fileType;
	// 已上传的灯光文件ID
	private Integer uploadId;

	public LightFile() {
		super();
	}

	public LightFile(Integer index, String filePath, String name,  String md5, Integer time,Integer status,
			String fileType, Integer uploadId) {
		super();
		this.index = index;
		this.filePath = filePath;
		this.name = name;
		this.time = time;
		this.md5 = md5;
		this.status = status;
		this.fileType = fileType;
		this.uploadId = uploadId;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getUploadId() {
		return uploadId;
	}

	public void setUploadId(Integer uploadId) {
		this.uploadId = uploadId;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
