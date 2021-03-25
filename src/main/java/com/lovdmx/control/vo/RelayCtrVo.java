package com.lovdmx.control.vo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: GatewayCtrVo.java
 * @Description: 继电器控制
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月15日 上午10:59:15
 *
 */
public class RelayCtrVo {
	// 继电器ID
	private Integer relayId;
	// 从站地址，表示哪个继电器
	private String slaveAddr;
	// 开回路列表
	private String loopNums;
	// 关回路
	private String state;

	public RelayCtrVo() {
		super();
	}

	public RelayCtrVo(String slaveAddr, String loopNums, String state) {
		super();
		this.slaveAddr = slaveAddr;
		this.loopNums = loopNums;
		this.state = state;
	}

	public String getSlaveAddr() {
		return slaveAddr;
	}

	public void setSlaveAddr(String slaveAddr) {
		this.slaveAddr = slaveAddr;
	}

	public String getLoopNums() {
		return loopNums;
	}

	public void setLoopNums(String loopNums) {
		this.loopNums = loopNums;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRelayId() {
		return relayId;
	}

	public void setRelayId(Integer relayId) {
		this.relayId = relayId;
	}

}
