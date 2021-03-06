package com.lovdmx.control.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.lovdmx.control.service.DelSpriteTasksService;
import com.lovdmx.control.service.DelTasksService;
import com.lovdmx.control.service.Dmx512DeviceService;
import com.lovdmx.control.service.ErrService;
import com.lovdmx.control.service.IntelligentGatewayService;
import com.lovdmx.control.service.LogService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RelayService;
import com.lovdmx.control.service.RelayTaskDetailsService;
import com.lovdmx.control.service.RtrDeviceService;
import com.lovdmx.control.service.SpriteDeviceService;
import com.lovdmx.control.service.SpriteTasksService;
import com.lovdmx.control.service.TasksService;
import com.lovdmx.control.service.TimedTasksService;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: UtilThread.java
 * @Description: Socket 公共类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年1月18日 下午5:21:35
 *
 */
public class SocketUtils {
	// 日志
	public static Logger logger = Logger.getLogger(SocketUtils.class);

	// 包头
	public static byte[] headByte = new byte[9];
	// 包尾
	public static byte[] endByte = new byte[2];
	// 紧急数据包
	public static byte[] urgentDataByte = new byte[1];

	// 中控 opCode
	public static Map<Integer, String> controlMap = new HashMap<Integer, String>();
	// 主录放精灵 opCode
	public static Map<Integer, String> spriteMap = new HashMap<Integer, String>();
	// 过滤 opCode
	public static Map<Integer, String> filtrationMap = new HashMap<Integer, String>();

	// 线程池
	public static ThreadPoolTaskExecutor threadPoolTaskExecutor;
	// 项目业务层
	public static ProjectService projectService;
	// 已上传视频业务层
	public static UploadVideosService uploadVideosService;
	// 已上传灯光业务层
	public static UploadEdlmxService uploadEdlmxService;
	// 上传的定时任务 业务层
	public static TasksService tasksService;
	// 异常报警 业务层
	public static ErrService errService;
	// 机架 业务层
	public static RackDeviceService rackDeviceService;
	// 日志 业务层
	public static LogService logService;
	// 录放精灵 任务 业务层
	public static SpriteTasksService spriteTasksService;
	// RTR设备 业务层
	public static RtrDeviceService rtrDeviceService;
	// 录放精灵设备 业务层
	public static SpriteDeviceService spriteDeviceService;
	// DMX512设备 业务层
	public static Dmx512DeviceService dmx512DeviceService;
	// 智能网关
	public static IntelligentGatewayService intelligentGatewayService;
	// 事务
	public static PlatformTransactionManager platformTransactionManager;
	// 智能网关定时任务
	public static TimedTasksService timedTasksService;
	// 继电器详情定时任务
	public static RelayTaskDetailsService relayTaskDetailsService;
	// 继电器
	public static RelayService relayService;
	// 已删除的定时任务
	public static DelTasksService delTasksService;
	// 录放精灵 已删除的定时任务
	public static DelSpriteTasksService delSpriteTasksService;

	// 项目路径
	public static String basePath;

	// 项目key
	public static String key;

	// IP
	public static String ServerIP;

	// 端口号
	public static int ServerPort;

	public SocketUtils() {
	}

	// 初始化
	static {

		// 获取文本内容
		Properties properties = new Properties();
		// 输入流
		InputStream in = null;
		try {
			// 获取输入流
			in = Thread.currentThread().getContextClassLoader().getResource("application.properties").openStream();
			// load 方法 输入流
			properties.load(in);
			key = properties.getProperty("key");
			ServerIP = properties.getProperty("ServerIP");
			ServerPort = Integer.parseInt(properties.getProperty("ServerPort"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in = null;
			}
		}

		// 包头
		headByte[0] = 'C';
		headByte[1] = 'C';
		headByte[2] = 'S';
		// OpCode
		headByte[3] = 0x00;
		headByte[4] = 0x00;
		// 内容长度
		headByte[5] = 0x00;
		headByte[6] = 0x00;
		// 校验码
		headByte[7] = 0x00;
		headByte[8] = 0x00;

		// 包尾
		endByte[0] = 0x0D; // /r回车
		endByte[1] = 0x0A; // /n换行

		// 紧急数据包
		urgentDataByte[0] = 0x00;

		// 中控 opCode
		controlMap.put(0x1006, null);
		controlMap.put(0x1007, null);
		controlMap.put(0x1008, null);
		controlMap.put(0x1009, null);
		controlMap.put(0x1010, null);
		controlMap.put(0x1011, null);
		controlMap.put(0x1012, null);
		controlMap.put(0x1013, null);
		controlMap.put(0x1014, null);
		controlMap.put(0x1015, null);
		controlMap.put(0x1016, null);
		controlMap.put(0x1019, null);
		controlMap.put(0x1020, null);
		controlMap.put(0x1027, null);
		controlMap.put(0x1026, null);
		controlMap.put(0x1028, null);
		controlMap.put(0x1025, null);
		controlMap.put(0x1029, null);
		controlMap.put(0x1030, null);
		controlMap.put(0x1035, null);
		controlMap.put(0x1036, null);

		// 主录放精灵 opCode
		spriteMap.put(0x2020, null);
		spriteMap.put(0x2022, null);
		spriteMap.put(0x2024, null);
		spriteMap.put(0x2026, null);

		// 过滤 opCode
		filtrationMap.put(0x1015, null);
		filtrationMap.put(0x1016, null);
		filtrationMap.put(0x1008, null);
		filtrationMap.put(0x1019, null);
		filtrationMap.put(0x1020, null);
		filtrationMap.put(0x1026, null);
		filtrationMap.put(0x1027, null);
		filtrationMap.put(0x1028, null);
		filtrationMap.put(0x1025, null);
		filtrationMap.put(0x1029, null);
		filtrationMap.put(0x1030, null);
		filtrationMap.put(0x1035, null);
		filtrationMap.put(0x1036, null);
	}

	// 初始化业务层 对象
	public static void setInitServiceObject(ThreadPoolTaskExecutor threadPoolTaskExecutor,
			ProjectService projectService, UploadVideosService uploadVideosService,
			UploadEdlmxService uploadEdlmxService, LogService logService, ErrService errService,
			RackDeviceService rackDeviceService, TasksService tasksService, SpriteTasksService spriteTasksService,
			RtrDeviceService rtrDeviceService, SpriteDeviceService spriteDeviceService,
			Dmx512DeviceService dmx512DeviceService, IntelligentGatewayService intelligentGatewayService,
			PlatformTransactionManager platformTransactionManager, TimedTasksService timedTasksService,
			RelayTaskDetailsService relayTaskDetailsService, RelayService relayService, DelTasksService delTasksService,
			DelSpriteTasksService delSpriteTasksService, String basePath) {
		SocketUtils.threadPoolTaskExecutor = threadPoolTaskExecutor;
		SocketUtils.projectService = projectService;
		SocketUtils.uploadVideosService = uploadVideosService;
		SocketUtils.uploadEdlmxService = uploadEdlmxService;
		SocketUtils.logService = logService;
		SocketUtils.errService = errService;
		SocketUtils.rackDeviceService = rackDeviceService;
		SocketUtils.tasksService = tasksService;
		SocketUtils.spriteTasksService = spriteTasksService;
		SocketUtils.rtrDeviceService = rtrDeviceService;
		SocketUtils.spriteDeviceService = spriteDeviceService;
		SocketUtils.dmx512DeviceService = dmx512DeviceService;
		SocketUtils.intelligentGatewayService = intelligentGatewayService;
		SocketUtils.platformTransactionManager = platformTransactionManager;
		SocketUtils.timedTasksService = timedTasksService;
		SocketUtils.relayTaskDetailsService = relayTaskDetailsService;
		SocketUtils.relayService = relayService;
		SocketUtils.delTasksService = delTasksService;
		SocketUtils.delSpriteTasksService = delSpriteTasksService;
		SocketUtils.basePath = basePath;
	}

	/**
	 * 
	 * @Function: ClientServiceUtils.java
	 * @Description: 关闭流
	 *
	 * @param: socket socket对象
	 * @param: dis 输入流
	 * @param: dos 输出流
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年1月17日 下午2:42:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年1月17日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void close(Socket socket) {
		if (socket != null) {
			// 关闭输入输出流
			try {
				/*
				 * socket.shutdownInput(); socket.shutdownOutput();
				 */
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket = null;
		}
	}

}
