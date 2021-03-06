package com.lovdmx.control.common.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.common.utils.SocketUtils;
import com.lovdmx.control.pojo.enums.EnumDeviceType;
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ServerSocketThread.java
 * @Description: 启动socket服务端,监听客户端(中控,主录放精灵)
 *
 * @version: v1.0.1
 * @author: syz
 * @date: 2019年6月3日 上午9:45:26
 *
 */
public class ServerSocketThread extends SocketUtils implements Runnable {

	public static Socket socket = null;

	// 服务端socket
	private ServerSocket serverScoket = null;

	// Map中Integer 代表发送端的标识
	// 根据key 来开启线程，所有的线程都保存到map键值对中
	public static Map<String, SocketClientInfoVo> spriteClientMap = Collections
			.synchronizedMap(new HashMap<String, SocketClientInfoVo>());

	// 根据key 来开启线程，所有的线程都保存到map键值对中
	public static Map<String, SocketClientInfoVo> controlClientMap = Collections
			.synchronizedMap(new HashMap<String, SocketClientInfoVo>());

	// 文件JSON数据
	private static String directoryListData = null;
	// 状态
	public static boolean FLAG = true;

	public static Socket getSocket() {
		return socket;
	}

	public ServerSocket getServerScoket() {
		return serverScoket;
	}

	public synchronized static void setFLAG(boolean isOk) {
		ServerSocketThread.FLAG = isOk;
	}

	public synchronized static String getDirectoryListData() {
		return directoryListData;
	}

	public synchronized static void setDirectoryListData(String directoryListData) {
		ServerSocketThread.directoryListData = directoryListData;
	}

	public ServerSocketThread() {

	}

	/**
	 * @see java.lang.Runnable#run()
	 * @Function: ServerSocketThread.java
	 * @Description: 监听客户端线程
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年1月18日 下午3:32:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@Override
	public void run() {

		try {
			serverScoket = new ServerSocket();
			serverScoket.bind(new InetSocketAddress(ServerIP, ServerPort));
			// 端口复用
			serverScoket.setReuseAddress(true);
			logger.error("socket start");

			boolean isOk = true;
			while (isOk) {
				synchronized (this) {
					isOk = FLAG;
				}
				try {
					// 侦听并接受到此套接字的连接。
					socket = serverScoket.accept();
					// 打印连接地址
					logger.error(socket.getInetAddress().getHostAddress());
					// 指定超时时间
					socket.setKeepAlive(true);// 开启保持活动状态的套接字
					// 指定超时时间
					socket.setSoTimeout(1000 * 10);

					AnalysisClientSocketThread analysisSocketThread = new AnalysisClientSocketThread(socket);
					boolean running = analysisSocketThread.isRunning();
					if (running) {
						// MAC 地址
						String mac = analysisSocketThread.getMac();
						// 获取设备类型
						String deviceType = analysisSocketThread.getDeviceType();
						// 判断设备是否存在
						boolean judgeDeviceIsExits = judgeDeviceIsExits(
								EnumDeviceType.Control.name().equals(deviceType) ? controlClientMap : spriteClientMap,
								mac);
						if (!judgeDeviceIsExits) {
							SocketClientInfoVo socketClientInfoVo = new SocketClientInfoVo(analysisSocketThread);
							// 开启心跳包线程
							Thread t = new Thread(analysisSocketThread);
							threadPoolTaskExecutor.execute(t);

							// 判断是
							if (EnumDeviceType.Control.name().equals(deviceType)) {
								controlClientMap.put(mac, socketClientInfoVo);
							} else {
								spriteClientMap.put(mac, socketClientInfoVo);

								// 请求灯光和视频文件包
								byte[] dataByte = PageageUtils.assemblyDataPackage(0x2021, null);

								// 发送
								AnalysisClientSocketThread.transmit(analysisSocketThread, dataByte);
							}
						} else {
							logger.error("======>>macExits");
							try {
								Map<String, SocketClientInfoVo> map = EnumDeviceType.Control.name().equals(deviceType)
										? controlClientMap
										: spriteClientMap;
								AnalysisClientSocketThread analysisSocketThread2 = map.get(mac)
										.getAnalysisSocketThread();
								// 发送 紧急数据包
								AnalysisClientSocketThread.transmit(analysisSocketThread2, urgentDataByte);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								// 关闭socket
								SocketUtils.close(socket);
							}
						}
					} else {
						// 关闭socket
						SocketUtils.close(socket);
						logger.error("格式错误!!!!!!!!!");
					}
				} catch (IOException e) {
					logger.error("ioe", e);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exc", e);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = null;
			}
			if (serverScoket != null) {
				try {
					serverScoket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serverScoket = null;
			}
		}

	}

	/**
	 * 
	 * @Function: SocketThread.java
	 * @Description: 删除线程
	 *
	 * @param: uid 设备UID
	 * @return: void
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年1月16日 下午5:28:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月16日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void removeMap(String deviceType, String mac, Socket socket) {
		// 根据设备类型获取
		Map<String, SocketClientInfoVo> map = EnumDeviceType.Control.name().equals(deviceType) ? controlClientMap
				: spriteClientMap;
		// 判断当前编号是否存在
		SocketClientInfoVo socketClientInfo = map.get(mac);
		if (socketClientInfo != null) {
			// 中断线程。
			socketClientInfo.getAnalysisSocketThread().setOK(false);
			// 关闭流
			SocketUtils.close(socket);
			// 移除
			map.remove(mac);
			logger.info("remove succeed mac===>>>" + mac); // 成功
		}
	}

	/**
	 * 
	 * @Function: ServerSocketThread.java
	 * @Description: 判断设备是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 上午10:50:41
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月10日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean judgeDeviceIsExits(Map<String, SocketClientInfoVo> map, String mac) {
		// 判断当前map中 mac是否存在
		if (!map.containsKey(mac)) {
			if (map.size() > 0) {
				// 大于零 遍历移除
				for (Map.Entry<String, SocketClientInfoVo> entry : map.entrySet()) {
					// 移除
					AnalysisClientSocketThread analysisSocketThread2 = entry.getValue().getAnalysisSocketThread();
					// close
					removeMap(analysisSocketThread2.getDeviceType(), entry.getKey(),
							analysisSocketThread2.getClientSocket());
				}
			}
			return false;
		} else {
			return true;
		}
	}

}
