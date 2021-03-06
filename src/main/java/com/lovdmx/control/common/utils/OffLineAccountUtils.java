package com.lovdmx.control.common.utils;

import java.io.IOException;

import org.springframework.web.socket.WebSocketSession;

import com.lovdmx.control.common.websocket.SpringWebSocketHandler;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.vo.WebSocketVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: OffLineAccountUtils.java
 * @Description: 账号离线 公共类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月5日 上午10:00:23
 *
 */
public class OffLineAccountUtils {

	/**
	 * 
	 * @Function: OffLineAccountUtils.java
	 * @Description: 移除webSocket信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws IOException
	 * @date: 2019年6月4日 下午5:30:20
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月4日 Administrator v1.0.0 修改原因
	 */
	public static void removeAccountWebSocket(Account account) throws IOException {

		WebSocketVo webSocket = SpringWebSocketHandler.dataMap.get(account.getProjectId() + "");
		if (webSocket != null) {
			for (WebSocketSession webSocketSession : webSocket.getUsers()) {
				// 获取websocket中的session信息
				Account account1 = (Account) webSocketSession.getAttributes().get("webSocket_admin");
				if (account1.getUserName().equals(account.getUserName())) {
					// 移除
					webSocket.getUsers().remove(webSocketSession);
					webSocketSession.close();
				}
			}
		}
	}

}
