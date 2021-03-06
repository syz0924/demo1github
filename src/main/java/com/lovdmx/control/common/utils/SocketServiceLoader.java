package com.lovdmx.control.common.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.thread.ProjectAnalysisDeviceThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;
import com.lovdmx.control.service.AccountService;
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
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * 将socket service随tomcat启动
 * 
 * @author syz
 * 
 */
public class SocketServiceLoader implements ServletContextListener {
	// socket server 线程

	private ServletContext servletContext= null;
	//socket 线程
	private Thread t1= null;
	// 项目设备报警线程
	private Thread t2= null;
	// 线程处理opCode业务层线程
	private Thread t3= null;

	// socket 线程
	private ServerSocketThread socketThread = null;
	// 项目设备报警线程
	private ProjectAnalysisDeviceThread projectAnalysisDeviceThread = null;
	// 线程处理opCode业务层线程
	private ProjectAnalysisOpCodeThread projectAnalysisOpCodeThread = null;
	// 连接池
	private ThreadPoolTaskExecutor threadPoolTaskExecutor = null;

	@Override
	@SuppressWarnings("static-access")
	public void contextDestroyed(ServletContextEvent arg0) {
		// 关闭线程
		if (t1 != null && t1.interrupted()) {
			t1.interrupt();
			ServerSocketThread.setFLAG(false);
			ServerSocket serverScoket = socketThread.getServerScoket();
			Socket socket = socketThread.getSocket();
			if (serverScoket != null) {
				try {
					serverScoket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				serverScoket = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
		if (t2 != null && t2.interrupted()) {
			t2.interrupt();
			projectAnalysisDeviceThread.setOK(false);
		}
		if (t3 != null && t3.interrupted()) {
			t3.interrupt();
			projectAnalysisOpCodeThread.setOK(false);
		}
		for (String in : ServerSocketThread.spriteClientMap.keySet()) {
			SocketClientInfoVo socketClientInfo = ServerSocketThread.spriteClientMap.get(in);
			AnalysisClientSocketThread analysisSocketThread = socketClientInfo.getAnalysisSocketThread();
			ServerSocketThread.removeMap(analysisSocketThread.getDeviceType(), analysisSocketThread.getMac(),
					analysisSocketThread.getSocket());
			analysisSocketThread.setOK(false);
		}
		for (String in : ServerSocketThread.controlClientMap.keySet()) {
			SocketClientInfoVo socketClientInfo = ServerSocketThread.controlClientMap.get(in);
			AnalysisClientSocketThread analysisSocketThread = socketClientInfo.getAnalysisSocketThread();
			ServerSocketThread.removeMap(analysisSocketThread.getDeviceType(), analysisSocketThread.getMac(),
					analysisSocketThread.getSocket());
			analysisSocketThread.setOK(false);
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		servletContext = servletContextEvent.getServletContext();
		// 获取IOC所有对象
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// 注入
		// 连接池
		threadPoolTaskExecutor = (ThreadPoolTaskExecutor) context.getBean("threadPoolTaskExecutor");
		// 项目业务层
		ProjectService projectService = (ProjectService) context.getBean("projectService");
		// 已上传视频业务层
		UploadVideosService uploadVideosService = (UploadVideosService) context.getBean("uploadVideosService");
		// 已上传灯光业务层
		UploadEdlmxService uploadEdlmxService = (UploadEdlmxService) context.getBean("uploadEdlmxService");
		// 日志业务层
		LogService logService = (LogService) context.getBean("logService");
		// 报警业务层
		ErrService errService = (ErrService) context.getBean("errService");
		// Sprite定时任务业务层
		TasksService tasksService = (TasksService) context.getBean("tasksService");
		// 机架业务层
		RackDeviceService rackDeviceService = (RackDeviceService) context.getBean("rackDeviceService");
		// 录放精灵 任务 业务层
		SpriteTasksService spriteTasksService = (SpriteTasksService) context.getBean("spriteTasksService");

		// RTR 设备 业务层
		RtrDeviceService rtrDeviceService = (RtrDeviceService) context.getBean("rtrDeviceService");
		// 录放精灵 设备 业务层
		SpriteDeviceService spriteDeviceService = (SpriteDeviceService) context.getBean("spriteDeviceService");
		// DMX512 控制盒 业务层
		Dmx512DeviceService dmx512DeviceService = (Dmx512DeviceService) context.getBean("dmx512DeviceService");
		// 智能网关 业务层
		IntelligentGatewayService intelligentGatewayService = (IntelligentGatewayService) context
				.getBean("intelligentGatewayService");
		// 账号 业务层
		AccountService accountService = (AccountService) context.getBean("accountService");
		// 事务
		PlatformTransactionManager platformTransactionManager = (PlatformTransactionManager) context
				.getBean("transactionManager");
		// 智能网关定时任务
		TimedTasksService timedTasksService = (TimedTasksService) context.getBean("timedTasksService");
		// 继电器详情定时任务
		RelayTaskDetailsService relayTaskDetailsService = (RelayTaskDetailsService) context
				.getBean("relayTaskDetailsService");
		// 继电器
		RelayService relayService = (RelayService) context.getBean("relayService");
		// 已删除的定时任务
		DelTasksService delTasksService = (DelTasksService) context.getBean("delTasksService");
		// 录放精灵 已删除的定时任务
		DelSpriteTasksService delSpriteTasksService = (DelSpriteTasksService) context.getBean("delSpriteTasksService");

		// 修改所有账号为不在线
		accountService.updateAllOnlineStatus(0);

		// 初始化 业务层对象
		SocketUtils.setInitServiceObject(threadPoolTaskExecutor, projectService, uploadVideosService,
				uploadEdlmxService, logService, errService, rackDeviceService, tasksService, spriteTasksService,
				rtrDeviceService, spriteDeviceService, dmx512DeviceService, intelligentGatewayService,
				platformTransactionManager, timedTasksService, relayTaskDetailsService, relayService, delTasksService,
				delSpriteTasksService, context.getServletContext().getRealPath("/"));
		//设备报警 线程
		projectAnalysisDeviceThread = new ProjectAnalysisDeviceThread();
		//处理opCode 线程
		ProjectAnalysisOpCodeThread projectAnalysisOpCodeThread = new ProjectAnalysisOpCodeThread();
		t2 = new Thread(projectAnalysisDeviceThread);
		t3 = new Thread(projectAnalysisOpCodeThread);
		// 启动 录放精灵socket
		socketThread = new ServerSocketThread();
		t1 = new Thread(socketThread);
		//保存到线程池
		threadPoolTaskExecutor.execute(t1);
		threadPoolTaskExecutor.execute(t2);
		threadPoolTaskExecutor.execute(t3);
	}
}