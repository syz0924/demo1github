package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SendSplitVo.java
 * @Description: 发送分发文件类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月3日 上午10:43:33
 *
 */
public class SendSplitVo extends BaseInfoVo {

	// 分发文件的id(逗号隔开)
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
