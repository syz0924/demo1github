package com.lovdmx.control.common.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpringWebSocketConfig.java
 * @Description: webSocket配置
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月30日 下午3:08:23
 *
 */

@Configuration
@EnableWebMvc
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/websocket/socketServer")
				.addInterceptors(new SpringWebSocketHandlerInterceptor()).setAllowedOrigins("*");
		registry.addHandler(webSocketHandler(), "/sockjs/socketServer")
				.addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
	}

	@Bean
	public TextWebSocketHandler webSocketHandler() {
		return new SpringWebSocketHandler();
	}
}
