package com.lovdmx.control.common.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.vo.WebSocketVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpringWebSocketHandler.java
 * @Description: webSocket 管理
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月30日 下午3:07:12
 *
 */
public class SpringWebSocketHandler extends TextWebSocketHandler {
	// 日志
	// private static Log logger = LogFactory.getLog(BaseController.class);

	// 这个会出现性能问题，最好用Map来存储，key用userid
	public static Map<String, WebSocketVo> dataMap;

	static {
		dataMap = Collections.synchronizedMap(new HashMap<String, WebSocketVo>());
	}

	/**
	 * 
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
	 * @Function: SpringWebSocketHandler.java
	 * @Description: 连接成功时候，会触发页面上onopen方法
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:35:40
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		// 连接的账号
		Account account = (Account) session.getAttributes().get("webSocket_admin");
		if (account != null) {

			// 项目ID
			Integer projectId = account.getProjectId();
			// 用户
			String userName = account.getUserName();
			WebSocketVo webSocket = dataMap.get("" + projectId);
			if (webSocket != null) {
				CopyOnWriteArraySet<WebSocketSession> users = webSocket.getUsers();
				if (users.size() > 0) {
					for (WebSocketSession webSocketSession : users) {
						Account account1 = (Account) webSocketSession.getAttributes().get("webSocket_admin");
						if (account1.getUserName().equals(userName)) {
							webSocketSession.close();
							break;
						}
					}
				}
				// 保存客户端对象
				users.add(session);
			} else {
				webSocket = new WebSocketVo();
				CopyOnWriteArraySet<WebSocketSession> users = new CopyOnWriteArraySet<>();
				users.add(session);
				dataMap.put(userName, webSocket);
			}
			// 这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
			TextMessage returnMessage = new TextMessage(webSocket.getDataJson());
			session.sendMessage(returnMessage);
		}

	}

	/**
	 * 
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionClosed(org.springframework.web.socket.WebSocketSession,
	 *      org.springframework.web.socket.CloseStatus)
	 * @Function: SpringWebSocketHandler.java
	 * @Description: 关闭连接时触发
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:38:23
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		// 账号信息
		Account account = (Account) session.getAttributes().get("webSocket_admin");
		if (account != null) {
			// 项目ID
			Integer projectId = account.getProjectId();
			WebSocketVo webSocketVo = dataMap.get(projectId + "");
			webSocketVo.getUsers().remove(session);

		}
	}

	/**
	 * 
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTextMessage(org.springframework.web.socket.WebSocketSession,
	 *      org.springframework.web.socket.TextMessage)
	 * @Function: SpringWebSocketHandler.java
	 * @Description: js调用websocket.send时候，会调用该方法
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:38:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
	}

	/**
	 * 
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTransportError(org.springframework.web.socket.WebSocketSession,
	 *      java.lang.Throwable)
	 * @Function: SpringWebSocketHandler.java
	 * @Description: webSocket异常时,会关闭与客户端的连接
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:56:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		// 账号信息
		Account account = (Account) session.getAttributes().get("webSocket_admin");
		if (account != null) {
			// 项目ID
			Integer projectId = account.getProjectId();
			WebSocketVo webSocketVo = dataMap.get(projectId + "");
			webSocketVo.getUsers().remove(session);
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 
	 * @Function: SpringWebSocketHandler.java
	 * @Description: 给指定用户发送消息
	 *
	 * @param:projectId 项目ID
	 * @param: userName:用户(账号唯一)
	 * @param: message
	 *             发送内容
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:39:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	public static void sendMessageToUser(Integer projectId, String userName, TextMessage message) {

		WebSocketVo webSocketVo = dataMap.get(projectId + "");
		CopyOnWriteArraySet<WebSocketSession> users = webSocketVo.getUsers();

		for (WebSocketSession user : users) {
			if (((Account) user.getAttributes().get("webSocket_admin")).getUserName().equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	/**
	 * 
	 * @Function: SpringWebSocketHandler.java
	 * @Description: 给所有在线用户发送消息
	 *
	 * @param:projectId 项目ID
	 * @param: message
	 *             发送内容
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午2:40:15
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月30日 Administrator v1.0.0 修改原因
	 */
	public static void sendMessageToUsers(Integer projectId, TextMessage message) {
		// 根据项目ID获取，改项目下的所有用户信息
		WebSocketVo webSocketVo = dataMap.get(projectId + "");
		CopyOnWriteArraySet<WebSocketSession> users = webSocketVo.getUsers();
		// 广播遍历，给所有在线的用户发送信息
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
