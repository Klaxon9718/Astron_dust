package com.example.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	private final WebSocketHandler webSocketHandler;
	@Autowired
	public WebSocketConfig(WebSocketChatHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
		//해당 경로로 들어오는 WebSocket연결 요청을 webSocketHandler가 처리하도록 설정
	}
	
}
