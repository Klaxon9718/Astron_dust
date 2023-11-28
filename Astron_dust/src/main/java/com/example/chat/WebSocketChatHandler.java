package com.example.chat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Component
public class WebSocketChatHandler extends TextWebSocketHandler{
	
	// 연결된 세션들을 저장하는 리스트
	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
	// 사용자 아이디와 웹소켓 세션을 매핑하여 저장하는 Map 객체
    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
	
	// 클라이언트로부터 WebSocket을 통해 텍스트 메시지를 받았을 때 호출됨
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { 
		String payload = message.getPayload(); //받은 메시지의 내용 반환
		System.out.println("payload" + payload);
		
		try {
			MessageModel msg = new Gson().fromJson(payload, MessageModel.class);
			if("JOIN".equals(msg.getType())) {
				// 메시지 타입이 'JOIN'인 경우, 사용자 ID를 웹소켓 세션과 매핑하여 저장합니다.
		        userSessions.put(msg.getUserId(), session);
		        for (WebSocketSession webSocketSession : sessions) {
					if(webSocketSession.isOpen()) {
						MessageModel joinMsg = new MessageModel();
						joinMsg.setType("JOIN");
						joinMsg.setSender("server");
						joinMsg.setText(msg.getUserId() + "님이 입장하셨습니다.");
						String joinMsgJson = new Gson().toJson(joinMsg);
						webSocketSession.sendMessage(new TextMessage(joinMsgJson));
					}
				}
			}else if ("TALK".equals(msg.getType())) {
				// 받은 메시지를 모든 세션에 보냄
				for (WebSocketSession webSocketSession : sessions) {
					if(webSocketSession.isOpen()) {
						// MessageModel 객체를 JSON 문자열로 변환
			            String msgJson = new Gson().toJson(msg);
						webSocketSession.sendMessage(new TextMessage(msgJson));
					}
				}
			}
		} catch (JsonSyntaxException e) {
			System.out.println("JSON parsing error: " + e.getMessage());
		}
		System.out.println("userSessions" + userSessions);
	}
	
	// 클라이언트와 연결이 열렸을 때 호출됨
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		sessions.add(session);
	}
	
	// 클라이언트와 연결이 닫혔을 때 호출됨
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("연결 종료: " + status);
		sessions.remove(session);
		
		for(Map.Entry<String, WebSocketSession> entry : userSessions.entrySet()) {
	        if(entry.getValue().equals(session)) {
	            userSessions.remove(entry.getKey());
	            break;
	        }
	    }
	}
	
}
