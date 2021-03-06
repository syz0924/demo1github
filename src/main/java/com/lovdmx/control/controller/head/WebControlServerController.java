/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.controller.head 
 * @author: syz  
 * @date: 2019年2月21日 下午5:32:48 
 */
package com.lovdmx.control.controller.head;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.MonitoringUtils;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Dmx512Device;
import com.lovdmx.control.pojo.Dmx512DeviceDetails;
import com.lovdmx.control.pojo.Err;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.MonitoringDevice;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.RealtimeTasks;
import com.lovdmx.control.pojo.Relay;
import com.lovdmx.control.pojo.RelayTaskDetails;
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.TimedTasks;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumFileType;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.service.Dmx512DeviceDetailsService;
import com.lovdmx.control.service.Dmx512DeviceService;
import com.lovdmx.control.service.ErrService;
import com.lovdmx.control.service.IntelligentGatewayService;
import com.lovdmx.control.service.MonitoringDeviceService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RealtimeTasksService;
import com.lovdmx.control.service.RelayService;
import com.lovdmx.control.service.RelayTaskDetailsService;
import com.lovdmx.control.service.SpriteDeviceService;
import com.lovdmx.control.service.SubtasksService;
import com.lovdmx.control.service.TasksService;
import com.lovdmx.control.service.TimedTasksService;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ControlServerController.java
 * @Description: web中控服务端控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月21日 下午5:32:48
 *
 */
@RequestMapping("/webControlServer/")
@CrossOrigin
@Controller
public class WebControlServerController extends BaseController {

	@Autowired
	private SubtasksService subtasksService; // 子任务

	@Autowired
	private TasksService tasksService; // 定时任务

	@Autowired
	private UploadVideosService uploadVideosService; // 已上传视频

	@Autowired
	private UploadEdlmxService uploadEdlmxService; // 已上传灯光文件

	@Autowired
	private ErrService errService; // 报警

	@Autowired
	private SpriteDeviceService spriteDeviceService;

	@Autowired
	private IntelligentGatewayService intelligentGatewayService; // 智能网关

	@Autowired
	private RelayService relayService; // 继电器

	@Autowired
	private RealtimeTasksService realtimeTasksService; // 即时任务

	@Autowired
	private RelayTaskDetailsService relayTaskDetailsService; // 继电器详情定时任务

	@Autowired
	private TimedTasksService timedTasksService; // 智能网关 定时任务

	@Autowired
	private MonitoringDeviceService monitoringDeviceService; // 监控

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private Dmx512DeviceService dmx512DeviceService; // dmx512控制盒

	@Autowired
	private Dmx512DeviceDetailsService dmx512DeviceDetailsService; // dmx512控制盒详情

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取目录信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年4月1日 下午4:02:00
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getDirectoryDataList", method = RequestMethod.GET)
	public JSONObject getDirectoryDataList(HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取主录放精灵客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getSpriteClient();
			// 判断是否在线
			if (socketClientInfoVo != null) {
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x2021, null);
				// 发送
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				resultJson.put("directoryList", ServerSocketThread.getDirectoryListData());
				result = "send successful";
			} else {
				// 已上传的灯光文件列表
				List<LightFile> lightFileList = uploadEdlmxService.findByLmxProjectId(account.getProjectId());
				// 已上传的视频文件列表
				List<VideoFile> videoFileList = uploadVideosService.findByVideoProjectId(account.getProjectId());
				resultJson.put("uploadEdlmxList", lightFileList);
				resultJson.put("uploadVideoList", videoFileList);
				result = "not online";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取任务信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月1日 下午4:01:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getTasksDataList", method = RequestMethod.GET)
	public JSONObject getTasksDataList(HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			List<Tasks> tasksList = tasksService.findByProjectId(account.getProjectId());
			resultJson.put("tasksList", tasksList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取子任务信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月1日 下午4:01:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getSubtasksDataList", method = RequestMethod.GET)
	public JSONObject getSubtasksDataList(HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 所有子任务
			List<Subtasks> subtasksList = subtasksService.findByParentId(account.getProjectId());
			resultJson.put("subtasksList", subtasksList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 修改子任务
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月22日 下午4:22:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "updateSubtask", method = RequestMethod.GET)
	public JSONObject updateSubtask(Subtasks subtask, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";

		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);

		try {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 判断是否在线
			if (OnLineClientUtils.judgeAccountIsOnline(account)) {
				Subtasks isExitsSubtasks = subtasksService.findBySubtaskName(subtask.getSubtaskName(),
						account.getAccountId());
				if (isExitsSubtasks == null || isExitsSubtasks.getSubtaskId() == subtask.getSubtaskId()) {
					// 保存
					subtasksService.update(subtask);
					result = "succeed";
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"修改子任务", new Date(), new Date());
					// 添加操作日志
					addlogInfo(log);
				} else {
					result = "already exists";
				}
			} else {
				result = "false";
			}
		} catch (Exception ex) {
			result = "fail";
			ex.printStackTrace();
			platformTransactionManager.rollback(status);
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 添加子任务
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月22日 下午4:22:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "addSubtask", method = RequestMethod.GET)
	public JSONObject addSubtask(Subtasks subtask, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		try {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");

			// 判断是否在线
			if (OnLineClientUtils.judgeAccountIsOnline(account)) {

				// 判断子任务名是否存在
				Subtasks subtasks = subtasksService.findBySubtaskName(subtask.getSubtaskName(), account.getProjectId());
				if (subtasks == null) {
					// 判断是灯光文件还是视频文件
					if (subtask.getTypeId() == 0) {
						subtask.setFileType(EnumFileType.LightingFile.name());
						// 根据md5值查询 灯光是否存在
						UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(subtask.getMd5());
						// 灯光文件存在状态改为1（上传）,路径改为 上传的路径
						if (uploadEdlmx != null) {
							subtask.setRtrLoaded(uploadEdlmx.getRtrLoaded());
						}
					} else {
						subtask.setFileType(EnumFileType.VideoFile.name());
						// 根据md5值查询 视频是否存在
						UploadVideos uploadVideo = uploadVideosService.findByMd5(subtask.getMd5());
						// 视频文件存在状态改为1（上传）,路径改为 上传的路径
						if (uploadVideo != null) {
							subtask.setRtrLoaded(uploadVideo.getRtrLoaded());
						}
					}
					subtask.setProjectId(account.getProjectId());
					// 创建时间
					subtask.setCreateTime(new Date());
					// 保存
					subtasksService.save(subtask);
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
							"添加子任务", new Date(), new Date());
					// 添加操作日志
					addlogInfo(log);
					result = "succeed";
					platformTransactionManager.commit(status);
				} else {
					result = "already exists";
				}
			} else {
				result = "false";
			}
		} catch (Exception ex) {
			result = "fail";
			ex.printStackTrace();
			platformTransactionManager.rollback(status);
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取Sprite 在线和未在线数量
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月24日 下午4:56:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("getSpriteOnlineNumber")
	public JSONObject getSpriteOnlineNumber(HttpSession session) {

		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// Not Online 数量
			Integer notOnlineNumber = spriteDeviceService.findNumberByProjectIdAndIsOnlineStatus(0,
					account.getProjectId());
			// Online 数量
			Integer onlineNumber = spriteDeviceService.findNumberByProjectIdAndIsOnlineStatus(1,
					account.getProjectId());
			resultJson.put("online", onlineNumber);
			resultJson.put("notOnlineNumber", notOnlineNumber);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 根据 参数获取报警信息
	 *
	 * @param:deviceType 设备类型
	 * @param: resolutionState 解决状态
	 * @param: startTime 开始时间
	 * @param: endTime 结束时间
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 下午1:43:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("findConditionQueryAlarmData")
	public JSONObject findConditionQueryAlarmData(String deviceType, Integer resolutionState, String startTime,
			String endTime, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据条件获取报警信息
			List<Err> errList = errService.findConditionQueryAlarmData(account.getProjectId(), deviceType,
					resolutionState, startTime, endTime);
			// 根据条件查询 分组获取报警日期的数量
			List<Err> alarmDateList = errService.findAlarmDateNumber(account.getProjectId(), deviceType,
					resolutionState, startTime, endTime);

			resultJson.put("errList", errList);
			resultJson.put("alarmDateList", alarmDateList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 判断子任务是否存在
	 *
	 * @param:deviceType 设备类型
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 下午1:43:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("findSubtaskExits")
	public JSONObject findSubtaskExits(String subtaskName, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据条件获取报警信息
			Subtasks subtask = subtasksService.findBySubtaskName(subtaskName, account.getProjectId());
			if (subtask == null) {
				result = "true";
			} else {
				result = "already exists";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 判断定时任务是否存在
	 *
	 * @param:deviceType 设备类型
	 * 
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月10日 下午1:43:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("findTaskExit")
	public JSONObject findTaskExit(String taskName, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据条件获取报警信息
			Tasks task = tasksService.findByProjectIdAndTaskName(account.getProjectId(), taskName);
			if (task == null) {
				result = "true";
			} else {
				result = "already exists";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取网关信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午11:03:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getIntelligentGatewayInfo", method = RequestMethod.GET)
	public JSONObject getIntelligentGatewayInfo(HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			List<IntelligentGateway> intelligentGatewayList = intelligentGatewayService
					.findRackJoinIntelligentGatewayByProjectId(account.getProjectId());
			// 获取子类所有信息
			for (IntelligentGateway intelligentGateway : intelligentGatewayList) {
				List<Relay> groupRelayIp = relayService.findGrouprs485toNetIpBygatewayMac(intelligentGateway.getMac());
				for (Relay relay : groupRelayIp) {
					relay.setRelayList(relayService.findByGatewayMacAndRs485toNetIp(intelligentGateway.getMac(),
							relay.getRs485toNetIp()));
				}
				intelligentGateway.setGroupRelayIp(groupRelayIp);
			}

			resultJson.put("intelligentGatewayList", intelligentGatewayList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 根据网关MAC 获取继电器信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午11:03:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRelayInfoByGatewayMac", method = RequestMethod.GET)
	public JSONObject getRelayInfoByGatewayMac(String gatewayMac, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据rs485toNetIp 分组
			List<Relay> relayList = relayService.findByGatewayMac(gatewayMac);
			resultJson.put("relayList", relayList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 根据继电器ID 获取定时任务详情
	 *
	 * @param: relayId 继电器ID
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午11:03:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRelayTaskDetailsInfo", method = RequestMethod.GET)
	public JSONObject getRelayTaskDetailsInfo(Integer relayId, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据继电器id 获取定时任务
			List<RelayTaskDetails> relayTaskDetailsList = relayTaskDetailsService.findByRelayId(relayId);
			resultJson.put("relayTaskDetailsList", relayTaskDetailsList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 根据网关MAC 分组获取 rs485toNetIp
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月18日 下午3:39:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRs485toNetIp", method = RequestMethod.GET)
	public JSONObject getRs485toNetIp(String gatewayMac, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据rs485toNetIp 分组
			List<Relay> rs485toNetIpList = relayService.findGrouprs485toNetIpBygatewayMac(gatewayMac);
			resultJson.put("rs485toNetIpList", rs485toNetIpList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 根据网关MAC 和 rs485toNetIp 获取485转ip 子类下的所有继电器
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月18日 下午3:39:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getSonRelayInfoByRs485toNetIp", method = RequestMethod.GET)
	public JSONObject getSonRelayInfoByRs485toNetIp(String gatewayMac, String rs485toNetIp, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据网关Mac和 485转ip获取子类的所有继电器
			List<Relay> sonRelayList = relayService.findByGatewayMacAndRs485toNetIp(gatewayMac, rs485toNetIp);
			resultJson.put("sonRelayList", sonRelayList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 判断即时任务是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月25日 下午3:13:23
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "findRealtimeTaskIsExitByTaskName", method = RequestMethod.GET)
	public JSONObject findRealtimeTaskExitsByTaskName(String taskName, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据任务名 判断是否存在
			RealtimeTasks realtimeTasks = realtimeTasksService.findByTaskName(account.getProjectId(), taskName);
			resultJson.put("exitState", realtimeTasks == null ? false : true);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取所有即时任务
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月25日 下午5:14:42
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRealtimeTaskAll", method = RequestMethod.GET)
	public JSONObject getRealtimeTaskAll(HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据任务名 判断是否存在
			List<RealtimeTasks> realtimeTasksList = realtimeTasksService.findByProjectId(account.getProjectId());
			resultJson.put("realtimeTasksList", realtimeTasksList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据任务id 删除智能网关实时任务
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月3日 上午10:48:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRealtimeTask", method = RequestMethod.GET)
	public JSONObject deleteRealtimeTask(Integer taskId, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		try {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 判断是否在线
			if (OnLineClientUtils.judgeAccountIsOnline(account)) {
				RealtimeTasks realtimeTasks = realtimeTasksService.findById(taskId);
				// 删除
				realtimeTasksService.deleteById(taskId);

				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.DELETE.name(),
						account.getUserName() + "删除" + realtimeTasks.getTaskName() + "实时任务", new Date(), new Date());
				addlogInfo(log);
				resultJson.put("flag", "succeed");
				platformTransactionManager.commit(status);
				result = "succeed";
			} else {
				result = "false";
			}
		} catch (Exception ex) {
			result = "fail";
			ex.printStackTrace();
			platformTransactionManager.rollback(status);
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description:获取智能网关中的所有定时任务
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月4日 上午11:41:40
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月4日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getGatewayTimedTaskAll", method = RequestMethod.GET)
	public JSONObject getGatewayTimedTaskAll(HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据任务名 判断是否存在
			List<TimedTasks> timedTasksList = timedTasksService.findByProjectId(account.getProjectId());
			resultJson.put("timedTasksList", timedTasksList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据任务名 判断定时任务是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月4日 上午11:53:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月4日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "findGatewayTimedTaskExit", method = RequestMethod.GET)
	public JSONObject findGatewayTimedTaskExit(String taskName, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据任务名 判断是否存在
			TimedTasks timedTasks = timedTasksService.findByTaskName(taskName);
			result = timedTasks == null ? "true" : "already exists";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;

	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 編輯网关实时任务信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月5日 上午11:30:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "editRealtimeTask", method = RequestMethod.GET)
	public JSONObject editRealtimeTask(Integer taskId, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 跟id查询
			RealtimeTasks realtimeTasks = realtimeTasksService.findById(taskId);
			resultJson.put("realtimeTasks", realtimeTasks);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 修改智能网关实时任务名
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月5日 上午11:36:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "updateRealtimeTask", method = RequestMethod.GET)
	public JSONObject updateRealtimeTask(RealtimeTasks realtimeTasks, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";

		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		try {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 判断是否在线
			if (OnLineClientUtils.judgeAccountIsOnline(account)) {

				RealtimeTasks task = realtimeTasksService.findById(realtimeTasks.getTaskId());
				// 修改
				realtimeTasksService.update(realtimeTasks);

				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"把" + realtimeTasks.getTaskName() + "修改为" + task.getTaskName() + "实时任务名", new Date(),
						new Date());
				// 添加日志
				addlogInfo(log);
				result = "succeed";
			} else {
				result = "false";
			}
		} catch (Exception ex) {
			result = "fail";
			ex.printStackTrace();
			platformTransactionManager.rollback(status);
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 获取机柜中 所有监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:06:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRackJoinMonitoringAllInfo", method = RequestMethod.GET)
	public JSONObject getMonitoringAllInfo(HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据项目id 获取 机柜中 监控信息
			List<RackDevice> rackDeviceList = rackDeviceService
					.findRackDeviceJoinMonitoringDeviceByProjectId(account.getProjectId());
			resultJson.put("rackDeviceList", rackDeviceList);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据序列号 获取监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:50:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "updateMonitoringDeviceAccessTokenByDeviceSerial", method = RequestMethod.GET)
	public JSONObject updateMonitoringDeviceAccessTokenByDeviceSerial(String deviceSerial, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (!OnLineClientUtils.judgeAccountIsOnline(account)) {

			// 根据设备序列号获取 监控信息
			MonitoringDevice monitoring = monitoringDeviceService.findByDeviceSerail(deviceSerial);

			Date expirationTime = monitoring.getExpirationTime();

			if (new Date().getTime() > expirationTime.getTime()) {
				// 获取凭证反馈
				String jsonResult = MonitoringUtils.getAccessToken(monitoring);
				// 转成 JSON 格式
				JSONObject jsonData = JSONObject.parseObject(jsonResult);
				// 反馈码
				String code = jsonData.getString("code");
				// 200 操作成功
				if (code.equals("200")) {
					JSONObject data = (JSONObject) jsonData.get("data");
					// 凭证
					String accessToken = data.getString("accessToken");
					// 过期时间
					long expireTime = data.getLong("expireTime");
					monitoring.setAccessToken(accessToken);
					monitoring.setExpirationTime(new Date(expireTime));
					// 修改数据
					monitoringDeviceService.update(monitoring);
				}
				result = code;
			} else {
				result = "true";
			}
			resultJson.put("monitoringDevice", monitoring);
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据dmx512 MAC获取 控制盒信息
	 *
	 * @param:dmx512Mac mac
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 上午11:13:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryDmx512DeviceInfo", method = RequestMethod.GET)
	public JSONObject queryDmx512DeviceInfo(String dmx512Mac, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 根据dmx512Mac 查控制盒信息
			Dmx512Device dmx512Device = dmx512DeviceService.findByDmx512Mac(dmx512Mac);
			// dmx512设备详情
			Dmx512DeviceDetails dmx512DeviceDetails = dmx512DeviceDetailsService.findByDmx512MacAndToday(dmx512Mac,
					DateUtils.currentlyStrDate(new Date()));

			resultJson.put("dmx512Device", dmx512Device);
			resultJson.put("dmx512DeviceDetails", dmx512DeviceDetails);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

}
