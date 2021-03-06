/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月19日 下午3:58:34 
 */
package com.lovdmx.control.vo;

import java.util.List;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SplitFileVo.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月19日 下午3:58:34
 *
 */
public class SplitFileVo {
	// 机柜编号
	private String rackId;
	// 设备MAC地址
	private String mac;

	// 多个文件路径(,逗号隔开)
	private String filePaths;
	// 离线报警状态 (0:未报警 1:以报警)
	private Integer offlineAlarmStatus;

	// 多个文件md5值(,逗号隔开)
	private String md5s;

	// 文件信息
	private List<FilePathVo> fileNames;

	public SplitFileVo() {

	}

	public SplitFileVo(String rackId, List<FilePathVo> fileNames) {
		super();
		this.rackId = rackId;
		this.fileNames = fileNames;
	}

	public String getRackId() {
		return rackId;
	}

	public String getMac() {
		return mac;
	}

	public Integer getOfflineAlarmStatus() {
		return offlineAlarmStatus;
	}

	public void setOfflineAlarmStatus(Integer offlineAlarmStatus) {
		this.offlineAlarmStatus = offlineAlarmStatus;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}

	public String getMd5s() {
		return md5s;
	}

	public void setMd5s(String md5s) {
		this.md5s = md5s;
	}

	public void setRackId(String rackId) {
		this.rackId = rackId;
	}

	public List<FilePathVo> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<FilePathVo> fileNames) {
		this.fileNames = fileNames;
	}

}
