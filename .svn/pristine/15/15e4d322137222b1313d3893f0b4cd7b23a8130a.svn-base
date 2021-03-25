/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.vo 
 * @author: Administrator   
 * @date: 2019年2月18日 下午1:49:25 
 */
package com.lovdmx.control.vo;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProtocolParameterVo.java
 * @Description: 协议参数
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月18日 下午1:49:25
 *
 */
public class ProtocolParameterVo implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 3340311869409190757L;
	// 读取opCode(唯一标识)
	private int opCode;
	// 长度
	private int length;
	// CRC16校验码
	private int parity;

	/**
	 * @Function: ProtocolParameterVo.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年2月18日 下午1:56:46
	 */
	public ProtocolParameterVo(int opCode, int length, int parity) {
		super();
		this.opCode = opCode;
		this.length = length;
		this.parity = parity;
	}

	public void setProtocolParameterVo(int opCode, int length, int parity) {
		this.opCode = opCode;
		this.length = length;
		this.parity = parity;
	}

	public int getOpCode() {
		return opCode;
	}

	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getParity() {
		return parity;
	}

	public void setParity(int parity) {
		this.parity = parity;
	}

}
