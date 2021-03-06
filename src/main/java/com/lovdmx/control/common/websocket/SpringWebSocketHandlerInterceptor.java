package com.lovdmx.control.common.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.lovdmx.control.pojo.Account;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpringWebSocketHandlerInterceptor.java
 * @Description: webSocket拦截器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月30日 下午3:08:02
 *
 */
public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			if (session != null) {
				// 使用userName区分WebSocketHandler，以便定向发送消息
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				if (account == null) {
					//System.err.println("=========>>>未登录");
				} else {
					attributes.put("webSocket_admin", account);
				}
			}
		}
		return super.beforeHandshake(request, response, wsHandler, attributes);

	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
