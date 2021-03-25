package com.lovdmx.control.pojo;

import java.io.Serializable;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RtrVideo.java
 * @Description: 存储RTR服务器视频文件表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:49:41
 *
 */
public class RtrVideo implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 4439799366508418878L;
	// 编号
	private Integer id;
	// mac地址
	private String rtrMac;
	// 视频文件MD5值
	private String orgvideoMd5;
	// 切割视频名
	private String newvideoName;


	public RtrVideo(String rtrMac, String orgvideoMd5, String newvideoName) {
		super();
		this.rtrMac = rtrMac;
		this.orgvideoMd5 = orgvideoMd5;
		this.newvideoName = newvideoName;
	}

	/**
	 * @Function: RtrVideo.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月8日 上午10:38:57
	 */
	public RtrVideo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRtrMac() {
		return rtrMac;
	}

	public void setRtrMac(String rtrMac) {
		this.rtrMac = rtrMac;
	}

	public String getOrgvideoMd5() {
		return orgvideoMd5;
	}

	public void setOrgvideoMd5(String orgvideoMd5) {
		this.orgvideoMd5 = orgvideoMd5;
	}

	public String getNewvideoName() {
		return newvideoName;
	}

	public void setNewvideoName(String newvideoName) {
		this.newvideoName = newvideoName;
	}
}