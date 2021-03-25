/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.utils 
 * @author: syz  
 * @date: 2019年3月6日 下午4:12:47 
 */
package com.lovdmx.control.common.utils;

import java.util.Map;

import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: OnLineClientUtils.java
 * @Description: 客户端在线状态
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月6日 下午4:12:47
 *
 */
public class OnLineClientUtils {

	/**
	 * 
	 * @Function: OnLineClientUtils.java
	 * @Description: 获取主录放精灵客户端数据
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 上午11:24:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年4月10日 Administrator v1.0.0 修改原因
	 */
	public static SocketClientInfoVo getSpriteClient() {
		SocketClientInfoVo socketClientInfoVo = null;
		// 获取主录放精灵客户端信息
		Map<String, SocketClientInfoVo> spriteClientMap = ServerSocketThread.spriteClientMap;
		// 遍历获取中控线程
		if (spriteClientMap.size() > 0) {
			for (Map.Entry<String, SocketClientInfoVo> entry : spriteClientMap.entrySet()) {
				socketClientInfoVo = entry.getValue();
				break;
			}
		}
		return socketClientInfoVo;
	}

	/**
	 * 
	 * @Function: OnLineClientUtils.java
	 * @Description: 获取中控客户端数据
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 上午11:24:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年4月10日 Administrator v1.0.0 修改原因
	 */
	public static SocketClientInfoVo getControlClient() {
		SocketClientInfoVo socketClientInfoVo = null;
		// 获取中控客户端信息
		Map<String, SocketClientInfoVo> controlClientMap = ServerSocketThread.controlClientMap;
		// 遍历获取中控线程
		if (controlClientMap.size() > 0) {
			for (Map.Entry<String, SocketClientInfoVo> entry : controlClientMap.entrySet()) {
				socketClientInfoVo = entry.getValue();
				break;
			}
		}
		return socketClientInfoVo;
	}

	/**
	 * 
	 * @Function: OnLineClientUtils.java
	 * @Description: 判断账号是否在线
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月9日 下午5:51:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年4月9日 Administrator v1.0.0 修改原因
	 */
	public static boolean judgeAccountIsOnline(Account account) {
		return account == null ? false : true;
	}

}
