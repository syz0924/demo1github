/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月11日 下午2:09:07 
 */
package com.lovdmx.control.vo;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: FileNames.java
 * @Description: 文件路径
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月11日 下午2:09:07
 *
 */
public class FilePathVo {
	// 文件路径
	private String fileName;
	// MD5值
	private String md5;

	public FilePathVo() {
	}

	public FilePathVo(String fileName, String md5) {
		super();
		this.fileName = fileName;
		this.md5 = md5;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
