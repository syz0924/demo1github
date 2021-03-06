package com.lovdmx.control.vo;

import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketVo {

	// session信息
	private CopyOnWriteArraySet<WebSocketSession> users;
	// 设备内容(JSON格式)
	private String dataJson;

	public WebSocketVo() {
		super();
	}

	public CopyOnWriteArraySet<WebSocketSession> getUsers() {
		return users;
	}

	public void setUsers(CopyOnWriteArraySet<WebSocketSession> users) {
		this.users = users;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

}
