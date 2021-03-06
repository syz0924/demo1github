/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月11日 下午2:07:34 
 */
package com.lovdmx.control.vo;

import java.util.List;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpiltFileData.java
 * @Description: 分割（分发）文件数据 (注：用于组装JSON格式)
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月11日 下午2:07:34
 *
 */
public class SpiltFileData {
	// 机架 使用逗号分割(,)
	private String cabinetNums;
	// 文件路径
	private List<FilePathVo> fileNames;

	public String getCabinetNums() {
		return cabinetNums;
	}

	public void setCabinetNums(String cabinetNums) {
		this.cabinetNums = cabinetNums;
	}

	public List<FilePathVo> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<FilePathVo> fileNames) {
		this.fileNames = fileNames;
	}

}