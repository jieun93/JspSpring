package kr.or.ddit.websocket;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PushMessageHandler extends TextWebSocketHandler {
	
	@Resource(name="wsSessionList")
	List<WebSocketSession> wsSessionList;  // 누군가 새로운 사람이 로그인할때,
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		wsSessionList.add(session);  // 계속 더해주는거 
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		wsSessionList.remove(session);  // 다른 페이지 이동시 빼주는거 
	}
	
	

}
