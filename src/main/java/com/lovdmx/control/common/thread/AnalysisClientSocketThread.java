package com.lovdmx.control.common.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.exception.OpCodeNotExitException;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.utils.CRC16Util;
import com.lovdmx.control.common.utils.NumUtil;
import com.lovdmx.control.pojo.enums.EnumDeviceType;
import com.lovdmx.control.vo.ProtocolParameterVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: AnalysisSocketThread.java
 * @Description: 解析客户端socket
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年4月10日 上午10:26:20
 *
 */
public class AnalysisClientSocketThread extends ServerSocketThread implements Runnable {

	// 服务端socket
	private Socket clientSocket;

	// 判断传送格式是否正确
	private boolean isRunning;

	// 启动线程
	private boolean isOK = true;

	// 输出流
	private DataOutputStream dos = null;

	// 输入流
	private DataInputStream dis = null;

	// MAC 地址
	private String mac;

	// 协议公共参数(对象)
	private ProtocolParameterVo protocolParameter = null;

	// 反馈数据
	private JSONObject dataJson = null;

	// 设备类型
	private String deviceType;

	public Socket getClientSocket() {
		return socket;
	}

	// 构造函数
	public AnalysisClientSocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		// 初始化
		this.isRunning = init();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getMac() {
		return mac;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public DataInputStream getDis() {
		return dis;
	}

	/**
	 * 
	 * @Function: AnalysisSocketThread.java
	 * @Description: 初始化,判断客户端连接格式是否正确
	 *
	 * @param:描述1描述
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年1月22日 下午4:01:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月22日
	 *        Administrator v1.0.0 修改原因
	 */
	public boolean init() {

		try {
			// 判断流是否初始化
			if (dis == null)
				dis = new DataInputStream(clientSocket.getInputStream());

			if (dos == null)
				dos = new DataOutputStream(clientSocket.getOutputStream());

			// 获取 客户端的数据
			JSONObject jSONObject = judgeFormat(dis);
			boolean running = (boolean) jSONObject.get("isRunning");
			// 判断 格式 是否正确
			if (running) {
				JSONObject receiveData = receiveData(dis);
				boolean bool = (boolean) receiveData.get("crc16Validation");
				return bool;
			} else {
				return false;
			}
		} catch (SocketTimeoutException e) {
			logger.error("init time err=========>>>> Socket timed out!");
		} catch (OpCodeNotExitException e) {
			logger.error("init opCode err=========>>>>" + e.getMessage());
		} catch (Exception e) {
			logger.error("init exc err=========>>>>" + e.getMessage());
		}

		return false;
	}

	/**
	 * @see java.lang.Runnable#run()
	 * @Function: AnalysisSocketThread.java
	 * @Description: 解析Socket的信息线程
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年1月18日 下午5:17:23
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@Override
	public void run() {
		try {
			// 心跳获取客户端传的值
			while (isOK) {
				// 解析格式
				JSONObject json = judgeFormat(dis);
				isRunning = (boolean) json.get("isRunning");
				if (!isRunning) {
					// 格式错误
					logger.error("解析格式错误 throw Exception=============>>>>>");
					throw new Exception();
				}
				// 解析内容
				dataJson = receiveData(dis);
				// 获取opCode
				int opCode = (int) dataJson.get("opCode");
				// 过滤心跳包
				if (opCode != 0x1011 && opCode != 0x2020) {
					// 解析OpCode业务逻辑
					ProjectAnalysisOpCodeThread.addJSONObject(dataJson);
				}
			}
			// 客户端 关闭时
		} catch (SocketTimeoutException e) {
			logger.error("run socketTime err=========>>>>" + e.getMessage());
		} catch (IOException e) {
			logger.error("run ioe err=========>>>>" + e.getMessage());
		} catch (OpCodeNotExitException e) {
			logger.error("run opCode err=========>>>>" + e.getMessage());
		} catch (Exception e) {
			logger.error("run error err=========>>>" + e.getMessage());
		} finally {
			// 删除socket信息
			ServerSocketThread.removeMap(deviceType, mac, socket);
		}
	}

	/**
	 * 
	 * @Function: AnalysisSocketThread.java
	 * @Description: 判断文件格式
	 *
	 * @param dis 输入流
	 * @return：IOException, SocketTimeoutException
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年1月22日 下午3:59:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月22日
	 *        Administrator v1.0.0 修改原因
	 */
	public synchronized JSONObject judgeFormat(DataInputStream dis) throws IOException, SocketTimeoutException {
		JSONObject jsonObject = new JSONObject();
		byte[] byHead = new byte[3];
		// 读取头部
		dis.readFully(byHead);
		// 把读取的字节数组转成字符串
		String head = new String(byHead);
		if (head.equals("CCS")) {
			// 读取opCode(唯一标识)
			short opCode = dis.readShort();
			// 内容长度
			short length = dis.readShort();
			// CRC16校验值
			byte[] crc16Byte = new byte[2];
			dis.readFully(crc16Byte);
			int parity = NumUtil.char2int(crc16Byte);
			// 初始化
			if (protocolParameter == null) {
				protocolParameter = new ProtocolParameterVo(opCode, length, parity);
			} else {
				protocolParameter.setProtocolParameterVo(opCode, length, parity);
			}
			jsonObject.put("isRunning", true);
		} else {
			jsonObject.put("isRunning", false);
		}
		return jsonObject;
	}

	/**
	 * 
	 * @Function: AnalysisSocketThread.java
	 * @Description: 根据opCode 读取客户端数据
	 *
	 * @param opCode 标识
	 * @param dis    输入流
	 * @return JSONObject
	 * @throws SocketTimeoutException(抛出 超时异常), OpCodeNotExitException(抛出
	 *                                   自定义OpCode异常)
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年1月22日 下午4:00:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月22日
	 *        Administrator v1.0.0 修改原因
	 */
	public synchronized JSONObject receiveData(DataInputStream dis)
			throws IOException, SocketTimeoutException, OpCodeNotExitException, Exception {
		// opCode
		int opCode = protocolParameter.getOpCode();
		// System.err.println("opCode==========>>>" + opCode);
		// 内容长度
		int length = protocolParameter.getLength();
		// CRC16校验值
		int parity = protocolParameter.getParity();
		// CRC16校验位
		int calcCrc16 = 0;
		// JSON对象
		JSONObject jSONObject = new JSONObject();
		if (ServerSocketThread.controlMap.containsKey(opCode) || ServerSocketThread.spriteMap.containsKey(opCode)) {
			if (length > 0) {
				byte[] byteData = new byte[length];
				// JSON值
				dis.readFully(byteData);
				// crc校验值
				calcCrc16 = CRC16Util.calcCrc16(byteData);
				String dataJson = new String(byteData);
				 System.err.println("opCode==========>>>" + opCode + "\t" + "JSON======>>>" +
				 dataJson);
				// 判断是否一致
				// if (calcCrc16 == parity) {
				Object json = JSON.parse(dataJson);
				// 判断字符串是JSONObject还是JSONArray
				if (json instanceof JSONObject) {
					jSONObject = (JSONObject) json;
				} else if (json instanceof JSONArray) {
					jSONObject.put("dataJson", dataJson);
				}
				jSONObject.put("crc16Validation", true);
				/*
				 * } else { log.error("opCode=>>>" + opCode + "\tCRC16错误" + parity + "\t" +
				 * calcCrc16); jSONObject.put("crc16Validation", false); return jSONObject; }
				 */
			}
			// 区分客户端类型
			if (opCode == 0x2020) {
				if (mac == null || mac.equals("")) {
					mac = (String) jSONObject.get("mac");
					// 主录放精灵
					deviceType = EnumDeviceType.Sprite.name();
				}
			} else if (opCode == 0x1011) {
				if (mac == null || mac.equals("")) {
					mac = (String) jSONObject.get("MAC");
					// Control中控
					deviceType = EnumDeviceType.Control.name();
				}
			}

		} else {
			// opCode不存在
			throw new OpCodeNotExitException("opCode  not exist====>>" + opCode);
		}
		jSONObject.put("opCode", opCode);
		// 包尾ID /r/n
		dis.readFully(new byte[2]);
		return jSONObject;
	}

	/**
	 * 传送数据 给客户端
	 * 
	 * @param by 数据
	 */
	public static void transmit(AnalysisClientSocketThread analysisSocketThread, byte[] bytes) throws Exception {
		DataOutputStream dos2 = null;
		try {
			dos2 = analysisSocketThread.dos;
			// 发送给客户端
			dos2.write(bytes);
		} catch (IOException e) {
			logger.error("transmit ioe err=========>>>>" + e.getMessage());
			// 删除socket信息
			ServerSocketThread.removeMap(analysisSocketThread.getDeviceType(), analysisSocketThread.getMac(),
					analysisSocketThread.getClientSocket());
		} catch (Exception e) {
			logger.error("transmit exc err=========>>>>" + e.getMessage());
			// 删除socket信息
			ServerSocketThread.removeMap(analysisSocketThread.getDeviceType(), analysisSocketThread.getMac(),
					analysisSocketThread.getClientSocket());
		}
	}

}
