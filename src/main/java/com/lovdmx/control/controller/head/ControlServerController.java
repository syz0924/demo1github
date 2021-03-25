package com.lovdmx.control.controller.head;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;
import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.JsonUtils;
import com.lovdmx.control.common.utils.MD5Utils;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.common.utils.SFTPUtil;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.RealtimeTasks;
import com.lovdmx.control.pojo.RelayTaskDetails;
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.TimedTasks;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumCyclicMode;
import com.lovdmx.control.pojo.enums.EnumErrType;
import com.lovdmx.control.pojo.enums.EnumFileType;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.pojo.enums.EnumTaskType;
import com.lovdmx.control.service.ErrService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RealtimeTasksService;
import com.lovdmx.control.service.SubtasksService;
import com.lovdmx.control.service.TasksService;
import com.lovdmx.control.service.TimedTasksService;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.FilePathVo;
import com.lovdmx.control.vo.RelayCtrVo;
import com.lovdmx.control.vo.RelayRealTimeTaskCtrVo;
import com.lovdmx.control.vo.RelayRealTimeTaskDataFormatVo;
import com.lovdmx.control.vo.RelayRealTimeTaskInfoVo;
import com.lovdmx.control.vo.RelayTaskDetailsVo;
import com.lovdmx.control.vo.RelayTimedTaskCtrVo;
import com.lovdmx.control.vo.RelayTimedTaskDataFormatVo;
import com.lovdmx.control.vo.RelayTimedTaskInfoVo;
import com.lovdmx.control.vo.SendSplitVo;
import com.lovdmx.control.vo.SocketClientInfoVo;
import com.lovdmx.control.vo.SplitFileVo;
import com.lovdmx.control.vo.TaskAssembleMd5Vo;
import com.lovdmx.control.vo.TaskFileTypeInfoVo;
import com.lovdmx.control.vo.TaskPauseOrRestoreVo;
import com.lovdmx.control.vo.TimedTaskVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SendControlServerController.java
 * @Description: 发送中控服务器 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月11日 下午2:51:58
 *
 */
@RequestMapping("/controlServer/")
@Controller
@CrossOrigin
public class ControlServerController extends BaseController {

	@Autowired
	private SubtasksService subtasksService; // 子任务

	@Autowired
	private TasksService tasksService; // 定时任务

	@Autowired
	private UploadVideosService uploadVideosService; // 已上传视频

	@Autowired
	private UploadEdlmxService uploadEdlmxService; // 已上传灯光文件

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private ErrService errService; // 报警

	@Autowired
	private RealtimeTasksService realtimeTasksService; // 即时任务

	@Autowired
	private TimedTasksService timedTasksService; // 定时任务

	/**
	 * 
	 * @Function: SendControlServerController.java
	 * @Description: 切割指定视频
	 *
	 * @param: sendSplitVo 发送分发文件
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月21日 上午10:13:09
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月21日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("sendVideoSplit")
	public JSONObject sendVideoSplit(SendSplitVo sendSplitVo, HttpSession session) throws Exception {

		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 判断是否在线
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				sendSplitVo.setAccountId(account.getAccountId());
				sendSplitVo.setProjectId(account.getProjectId());
				// 分割为数组ID
				String[] arrayId = sendSplitVo.getIds().split(",");
				// 所有机柜编号(逗号隔开)
				String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(sendSplitVo.getProjectId());
				// 根据数组id 获取为切割的视频数据
				List<UploadVideos> uploadVideosList = uploadVideosService.findNotSpiltByIds(arrayId);

				if (uploadVideosList == null || uploadVideosList.size() == 0) {
					result = "already split";
				} else {
					List<FilePathVo> filePathList = new ArrayList<FilePathVo>();
					// 遍历组装
					for (UploadVideos uploadVideo : uploadVideosList) {
						// 路径
						String path = uploadVideo.getFilePath();
						// 组装中控路径
						path = SFTPUtil.controlBasePath
								+ path.substring(path.lastIndexOf("/", path.lastIndexOf("/") - 1) + 1);

						FilePathVo filePath = new FilePathVo(path, uploadVideo.getMd5());
						filePathList.add(filePath);
					}
					SplitFileVo splitFile = new SplitFileVo(cabinetNums, filePathList);
					// 转成JSON格式数据
					net.sf.json.JSONObject dataJson = net.sf.json.JSONObject.fromObject(splitFile);
					// 获取UUID
					String uuid = splitFile.getFileNames().get(0).getMd5();
					dataJson.put("uuid", uuid);

					// 组装web数据（用于后续操作）
					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"切割视频", new Date(), new Date());
					webJsonData.put("log", log);
					// state (-1 分发丢失,1 正常分发);
					webJsonData.put("state", 1);
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1001, dataJson.toString());
					// 发送数据
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				}
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 切割指定灯光
	 *
	 * @param: sendSplit 发送分发文件
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月21日 上午10:13:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月21日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("sendEdlmxSplit")
	public JSONObject sendEdlmxSplit(SendSplitVo sendSplitVo, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 判断是否在线
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				sendSplitVo.setAccountId(account.getAccountId());
				sendSplitVo.setProjectId(account.getProjectId());
				// 分割为数组ID
				String[] arrayId = sendSplitVo.getIds().split(",");
				// 所有机柜编号(逗号隔开)
				String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(sendSplitVo.getProjectId());
				// 根据数组id 获取为切割的视频数据
				List<UploadEdlmx> uploadEdlmxList = uploadEdlmxService.findNotSpiltByIds(arrayId);
				List<FilePathVo> filePathList = new ArrayList<FilePathVo>();
				if (uploadEdlmxList == null || uploadEdlmxList.size() == 0) {
					result = "already split";
				} else {
					// 遍历组装
					for (UploadEdlmx uploadEdlmx : uploadEdlmxList) {
						String path = uploadEdlmx.getFilePath();
						// 组装中控路径
						path = SFTPUtil.controlBasePath
								+ path.substring(path.lastIndexOf("/", path.lastIndexOf("/") - 1) + 1);
						FilePathVo filePath = new FilePathVo(path, uploadEdlmx.getMd5());
						filePathList.add(filePath);
					}
					SplitFileVo splitFile = new SplitFileVo(cabinetNums, filePathList);
					// 转成JSON格式数据
					net.sf.json.JSONObject dataJson = net.sf.json.JSONObject.fromObject(splitFile);
					// 获取UUID
					String uuid = splitFile.getFileNames().get(0).getMd5();
					dataJson.put("uuid", uuid);
					// 组装web数据（用于后续操作）
					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"添加上传信息", new Date(), new Date());
					webJsonData.put("log", log);
					// state (-1 分发丢失,1 正常分发);
					webJsonData.put("state", 1);
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1002, dataJson.toString());
					// 发送数据
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				}
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 添加定时任务
	 *
	 * @param: task 定時任務对象
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年3月21日 上午10:11:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月21日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "addTimedTask", method = RequestMethod.GET)
	public JSONObject addTimedTask(Tasks task, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {

				// 判断定时任务名是否存在
				Tasks tak = tasksService.findByProjectIdAndTaskName(account.getProjectId(), task.getTaskName());
				if (tak == null) {
					// 所有机柜编号(逗号隔开)
					String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());
					// 定时任务组装md5值 初始化构造方法
					TaskAssembleMd5Vo assembleMd5Vo = new TaskAssembleMd5Vo(cabinetNums, task.getTaskType(),
							task.getCyclicMode(), task.getCyclicDate(), task.getProjectId(),
							DateUtils.stampToDate(task.getStartTimestamp()),
							DateUtils.stampToDate(task.getEndTimestamp()), task.getStrStartTime(),
							task.getStrEndTime());

					// 数据md5值
					String taskMd5 = MD5Utils.encrypByMD5(JsonUtils.objectToJson(assembleMd5Vo));
					// 根据项目id 和任务md5获取定时任务信息
					Tasks tsk_md5 = tasksService.findByProjectIdAndTaskMd5(account.getProjectId(), taskMd5);

					boolean flag = true;

					// 判断定时任务是否存在
					if (tsk_md5 != null) {
						// 所有丢失报警机柜ID(逗号隔开)
						cabinetNums = errService.findLoseGroupCounatRackIdByTaskNameAndErrType(account.getProjectId(),
								task.getTaskName(), EnumErrType.Task.name());
						System.out.println("=================>>定时任务已存在");
						// 判断所有机柜 定时任务是否存在
						if (cabinetNums.equals("")) {
							System.out.println("=====================>>>1");
							result = "task already exists";
							flag = false;
						} else {
							System.out.println("=====================>>>2");
							resultJson.put("rackIds", cabinetNums);
						}

					}
					if (flag) {
						// 赋值数据
						task.initTaskDate(taskMd5, EnumTaskType.getTaskTypeName(task.getType()),
								EnumCyclicMode.getCyclicModeName(task.getMode()), account.getProjectId(), 1,
								DateUtils.currentlyDate(DateUtils.stampToDate(task.getStartTimestamp())),
								DateUtils.currentlyDate(DateUtils.stampToDate(task.getEndTimestamp())),
								DateUtils.fmtStrDateToTime(task.getStrStartTime()),
								DateUtils.fmtStrDateToTime(task.getStrEndTime()), cabinetNums, new Date());

						String[] arrayIds = task.getSubtaskIds().split(",");
						// 根据ids 获取子任务信息
						List<Subtasks> subtskList = subtasksService.findByIds(arrayIds);
						// 根据ids 获取子任务只获取LightingFile类型信息
						List<Subtasks> lmxSubtskList = subtasksService.findByIdsAndFileType(arrayIds,
								EnumFileType.LightingFile.name());
						// 根据ids 获取子任务只获取VideoFile类型信息
						List<Subtasks> videoSubtskList = subtasksService.findByIdsAndFileType(arrayIds,
								EnumFileType.VideoFile.name());
						// 组装灯光文件的 md5值（逗号隔开）
						StringBuilder lmxsbl = new StringBuilder();
						// 组装视频文件的 md5值（逗号隔开）
						StringBuilder videosbl = new StringBuilder();
						for (Subtasks subtasks : lmxSubtskList) {
							lmxsbl.append(subtasks.getMd5()).append(",");
						}

						for (Subtasks subtasks : videoSubtskList) {
							videosbl.append(subtasks.getMd5()).append(",");
						}

						// 根据md5 获取已上传的视频信息
						List<UploadVideos> uploadVideosList = uploadVideosService
								.findByMd5s(videosbl.toString().split(","));
						// 根据md5 获取已上传的灯光信息
						List<UploadEdlmx> uploadEdlmxList = uploadEdlmxService.findByMd5s(lmxsbl.toString().split(","));
						// 遍历组装 任务信息
						List<TaskFileTypeInfoVo> taskFileTypeInfoList = new ArrayList<TaskFileTypeInfoVo>();
						for (Subtasks subtask : subtskList) {
							TaskFileTypeInfoVo taskFileTypeInfo = new TaskFileTypeInfoVo();
							taskFileTypeInfo.setFileType(subtask.getFileType());
							// 视频组装
							if (subtask.getFileType().equals(EnumFileType.VideoFile.name())) {
								for (UploadVideos uploadVideo : uploadVideosList) {
									if (subtask.getMd5().equals(uploadVideo.getMd5())) {
										taskFileTypeInfo.setFileInfo("" + uploadVideo.getFileIndex());
										taskFileTypeInfo.setFileDuration("" + uploadVideo.getTime());
										break;
									}
								}
								// 灯光组装
							} else {
								for (UploadEdlmx uploadEdlmx : uploadEdlmxList) {
									if (subtask.getMd5().equals(uploadEdlmx.getMd5())) {
										taskFileTypeInfo.setFileInfo("" + uploadEdlmx.getMd5());
										taskFileTypeInfo.setFileDuration("" + uploadEdlmx.getTime());
										break;
									}
								}
							}
							taskFileTypeInfoList.add(taskFileTypeInfo);
						}

						// 获取UUID
						String uuid = UUID.randomUUID().toString();
						TimedTaskVo taskVo = new TimedTaskVo(task.getTaskName(), task.getTaskMd5(), task.getType(),
								task.getMode(), task.getCyclicDate(), DateUtils.currentlyStrDate(task.getStartDate()),
								DateUtils.currentlyStrDate(task.getEndDate()), task.getStrStartTime(),
								task.getStrEndTime(), taskFileTypeInfoList, uuid);

						// 组装发送数据
						JSONObject dataJson = new JSONObject();
						dataJson.put("taskMsg", taskVo);
						dataJson.put("rackId", cabinetNums);
						dataJson.put("uuid", uuid);

						// 组装web数据（用于后续操作）
						JSONObject webJsonData = new JSONObject();
						// 日志
						Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
								"添加上传信息", new Date(), new Date());
						webJsonData.put("task", task);
						webJsonData.put("accountId", account.getAccountId());
						webJsonData.put("log", log);
						// state (-1 分发丢失,1 正常分发);
						webJsonData.put("state", 1);
						// 添加ProjectAnalysisOpCodeThread对象的map中
						ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);

						// 组装数据
						byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1003, dataJson.toJSONString());
						// 发送数据
						AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
						result = "send successful";
					}
				} else {
					result = "taskName already exists";
				}
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 发送暂停任务
	 *
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月21日 上午10:45:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月21日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("sendPauseTask")
	public JSONObject sendPauseTask(HttpSession session) throws Exception {

		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 所有机柜编号(逗号隔开)
				String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				JSONObject jsonData = new JSONObject();
				TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "0");

				jsonData.put("rackId", cabinetNums);
				jsonData.put("uuid", uuid);
				jsonData.put("taskMsg", taskPauseOrRestore);

				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"修改所有定时任务状态为暂停", new Date(), new Date());
				webJsonData.put("account", account);
				webJsonData.put("log", log);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1004, jsonData.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 发送恢复任务
	 *
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月21日 上午10:45:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月21日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("sendRestoreTask")
	public JSONObject sendRestoreTask(HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 所有机柜编号(逗号隔开)
				String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				JSONObject jsonData = new JSONObject();

				TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "1");

				jsonData.put("rackId", cabinetNums);
				jsonData.put("uuid", uuid);
				jsonData.put("taskMsg", taskPauseOrRestore);

				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"修改所有定时任务状态为启用", new Date(), new Date());
				webJsonData.put("account", account);
				webJsonData.put("log", log);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1005, jsonData.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Description: 指定节点暂停定时任务
	 * 
	 * @param: rackIds 机柜ids
	 * @param: taskMd5 任务md5s
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年7月26日 下午1:49:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("sendAssignNodePauseTask")
	public JSONObject sendAssignNodePauseTask(String rackIds, String taskMd5s, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				JSONObject jsonData = new JSONObject();
				// 转集合
				List<String> taskMd5List = Arrays.asList(taskMd5s.split(","));
				TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "0", taskMd5List);

				jsonData.put("rackId", rackIds);
				jsonData.put("uuid", uuid);
				jsonData.put("taskMsg", taskPauseOrRestore);

				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"修改指定定时任务状态为暂停", new Date(), new Date());
				webJsonData.put("account", account);
				webJsonData.put("log", log);
				webJsonData.put("taskPauseOrRestore", taskPauseOrRestore);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1004, jsonData.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Description: 指定节点启用定时任务
	 *
	 * @param: rackIds 机柜ids
	 * @param: taskMd5 任务md5s
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月26日 下午2:03:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "sendAssignNodeRestoreTask", method = RequestMethod.GET)
	public JSONObject sendAssignNodeRestoreTask(String rackIds, String taskMd5s, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				JSONObject jsonData = new JSONObject();

				// 转集合
				List<String> taskMd5List = Arrays.asList(taskMd5s.split(","));
				TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "1", taskMd5List);

				jsonData.put("rackId", rackIds);
				jsonData.put("uuid", uuid);
				jsonData.put("taskMsg", taskPauseOrRestore);

				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"修改指定定时任务状态为启用", new Date(), new Date());
				webJsonData.put("account", account);
				webJsonData.put("log", log);
				webJsonData.put("taskPauseOrRestore", taskPauseOrRestore);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1005, jsonData.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description:发送（指定录放精灵）丢失的 切割灯光文件
	 *
	 * @param:fileMd5 丢失文件 md5值
	 * 
	 * @return：JSONObject
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
	@RequestMapping("sendLoseEdlmxFileSpilt")
	public JSONObject sendLoseEdlmxFileSpilt(String fileMd5, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 判断是否在线
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 所有丢失机柜ID(逗号隔开)
				String cabinetNums = errService.findLoseGroupCounatRackIdByTypeMd5AndErrType(account.getProjectId(),
						fileMd5, EnumErrType.Lmx.name());
				// 获取已上传的灯光文件信息
				UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(fileMd5);
				// 组装数据
				List<FilePathVo> filePathList = new ArrayList<FilePathVo>();

				// 路径
				String path = uploadEdlmx.getFilePath();
				// 组装中控路径
				path = SFTPUtil.controlBasePath + path.substring(path.lastIndexOf("/", path.lastIndexOf("/") - 1) + 1);

				FilePathVo filePath = new FilePathVo(path, uploadEdlmx.getMd5());
				filePathList.add(filePath);
				SplitFileVo splitFile = new SplitFileVo(cabinetNums, filePathList);
				// 转成JSON格式数据
				net.sf.json.JSONObject dataJson = net.sf.json.JSONObject.fromObject(splitFile);
				dataJson.put("uuid", fileMd5);
				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"添加上传信息", new Date(), new Date());
				webJsonData.put("log", log);
				// state (-1 分发丢失,1 正常分发);
				webJsonData.put("state", -1);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(fileMd5, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1002, dataJson.toString());

				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description:发送（指定RTR服务器）丢失的 切割视频文件
	 *
	 * @param:fileMd5 丢失文件 md5值
	 * 
	 * @return：JSONObject
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
	@RequestMapping("sendLoseVideoFileSpilt")
	public JSONObject sendLoseVideoFileSpilt(String fileMd5, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 判断是否在线
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 所有丢失机柜ID(逗号隔开)
				String cabinetNums = errService.findLoseGroupCounatRackIdByTypeMd5AndErrType(account.getProjectId(),
						fileMd5, EnumErrType.Video.name());
				// 获取已上传的灯光文件信息
				UploadVideos uploadVideo = uploadVideosService.findByMd5(fileMd5);
				// 组装数据
				List<FilePathVo> filePathList = new ArrayList<FilePathVo>();

				// 路径
				String path = uploadVideo.getFilePath();
				// 组装中控路径
				path = SFTPUtil.controlBasePath + path.substring(path.lastIndexOf("/", path.lastIndexOf("/") - 1) + 1);

				FilePathVo filePath = new FilePathVo(path, uploadVideo.getMd5());
				filePathList.add(filePath);
				SplitFileVo splitFile = new SplitFileVo(cabinetNums, filePathList);
				// 转成JSON格式数据
				net.sf.json.JSONObject dataJson = net.sf.json.JSONObject.fromObject(splitFile);
				dataJson.put("uuid", fileMd5);
				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
						"添加上传信息", new Date(), new Date());
				webJsonData.put("log", log);
				// state (-1 分发丢失,1 正常分发);
				webJsonData.put("state", -1);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(fileMd5, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1001, dataJson.toString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description:发送（指定录放精灵）丢失的 定时任务
	 *
	 * @param:taskName 丢失的定时任务名
	 * 
	 * @return：JSONObject
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
	@RequestMapping("sendLoseTimedTask")
	public JSONObject sendLoseTimedTask(String taskName, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {

				// 所有丢失报警机柜ID(逗号隔开)
				String cabinetNums = errService.findLoseGroupCounatRackIdByTaskNameAndErrType(account.getProjectId(),
						taskName, EnumErrType.Task.name());
				// 跟项目id和任务名获取 任务信息
				Tasks task = tasksService.findByProjectIdAndTaskName(account.getProjectId(), taskName);

				String[] arrayIds = task.getSubtaskIds().split(",");
				// 根据ids 获取子任务信息
				List<Subtasks> subtskList = subtasksService.findByIds(arrayIds);
				// 根据ids 获取子任务只获取LightingFile类型信息
				List<Subtasks> lmxSubtskList = subtasksService.findByIdsAndFileType(arrayIds,
						EnumFileType.LightingFile.name());
				// 根据ids 获取子任务只获取VideoFile类型信息
				List<Subtasks> videoSubtskList = subtasksService.findByIdsAndFileType(arrayIds,
						EnumFileType.VideoFile.name());
				// 组装灯光文件的 md5值（逗号隔开）
				StringBuilder lmxsbl = new StringBuilder();
				// 组装视频文件的 md5值（逗号隔开）
				StringBuilder videosbl = new StringBuilder();
				for (Subtasks subtasks : lmxSubtskList) {
					lmxsbl.append(subtasks.getMd5()).append(",");
				}

				for (Subtasks subtasks : videoSubtskList) {
					videosbl.append(subtasks.getMd5()).append(",");
				}

				// 根据md5 获取已上传的视频信息
				List<UploadVideos> uploadVideosList = uploadVideosService.findByMd5s(videosbl.toString().split(","));
				// 根据md5 获取已上传的灯光信息
				List<UploadEdlmx> uploadEdlmxList = uploadEdlmxService.findByMd5s(lmxsbl.toString().split(","));
				// 遍历组装 任务信息
				List<TaskFileTypeInfoVo> taskFileTypeInfoList = new ArrayList<TaskFileTypeInfoVo>();
				for (Subtasks subtask : subtskList) {
					TaskFileTypeInfoVo taskFileTypeInfo = new TaskFileTypeInfoVo();
					taskFileTypeInfo.setFileType(subtask.getFileType());
					// 视频组装
					if (subtask.getFileType().equals(EnumFileType.VideoFile.name())) {
						for (UploadVideos uploadVideo : uploadVideosList) {
							if (subtask.getMd5().equals(uploadVideo.getMd5())) {
								taskFileTypeInfo.setFileInfo("" + uploadVideo.getFileIndex());
								taskFileTypeInfo.setFileDuration("" + uploadVideo.getTime());
								break;
							}
						}
						// 灯光组装
					} else {
						for (UploadEdlmx uploadEdlmx : uploadEdlmxList) {
							if (subtask.getMd5().equals(uploadEdlmx.getMd5())) {
								taskFileTypeInfo.setFileInfo("" + uploadEdlmx.getMd5());
								taskFileTypeInfo.setFileDuration("" + uploadEdlmx.getTime());
								break;
							}
						}
					}
					taskFileTypeInfoList.add(taskFileTypeInfo);
				}

				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				TimedTaskVo taskVo = new TimedTaskVo(task.getTaskName(), task.getTaskMd5(),
						EnumTaskType.getEnumNameByIndex(task.getTaskType()),
						EnumCyclicMode.getEnumNameByIndex(task.getCyclicMode()), task.getCyclicDate(),
						DateUtils.currentlyStrDate(task.getStartDate()), DateUtils.currentlyStrDate(task.getEndDate()),
						DateUtils.fmtDateToStrTime(task.getStartTime()), DateUtils.fmtDateToStrTime(task.getEndTime()),
						taskFileTypeInfoList, uuid);

				// 组装发送数据
				JSONObject dataJson = new JSONObject();
				dataJson.put("taskMsg", taskVo);
				dataJson.put("rackId", cabinetNums);
				dataJson.put("uuid", uuid);

				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
						"添加上传信息", new Date(), new Date());
				webJsonData.put("task", task);
				webJsonData.put("accountId", account.getAccountId());
				webJsonData.put("log", log);
				// state (-1 分发丢失,1 正常分发);
				webJsonData.put("state", -1);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1003, dataJson.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 发送(中控) 添加 继电器定时任务
	 *
	 * @param: relayTimedTaskDataFormat 定时任务数据
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月15日 下午3:35:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月15日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "sendAddRelayTimedTask", method = RequestMethod.POST)
	public JSONObject sendAddRelayTimedTask(
			@RequestBody(required = false) RelayTimedTaskDataFormatVo relayTimedTaskDataFormat, HttpSession session)
			throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 任务详情
				RelayTimedTaskInfoVo taskMsg = relayTimedTaskDataFormat.getTaskMsg();
				// 定时任务
				List<RelayTimedTaskCtrVo> timlyTasks = taskMsg.getTimlyTasks();
				// 根据网关Mac 区分定时任务
				Map<String, RelayTaskDetailsVo> map = new HashMap<String, RelayTaskDetailsVo>();
				// 模式 0正常 1修改模式
				Integer mode = 1;
				TimedTasks timedTasks = null;
				if (timlyTasks != null) {
					// 星期（逗号隔开）
					String dayofweek = taskMsg.getDayofweek();
					// 分割
					String[] split = dayofweek.split(",");
					// 遍历组装数据
					for (RelayTimedTaskCtrVo relayTimedTaskCtr : timlyTasks) {
						List<RelayCtrVo> ctrInfo = relayTimedTaskCtr.getTasks();
						for (RelayCtrVo relayCtr : ctrInfo) {
							// 判断map是否存在
							RelayTaskDetailsVo relayTaskDetailsVo = map.get(relayTimedTaskCtr.getGatewayMac());
							List<RelayTaskDetails> list = null;
							if (relayTaskDetailsVo == null) {
								// 不存在 初始化
								relayTaskDetailsVo = new RelayTaskDetailsVo();
								relayTaskDetailsVo.setRackId(relayTimedTaskCtr.getRackId());
								list = new ArrayList<RelayTaskDetails>();
								relayTaskDetailsVo.setRelayTaskDetailsList(list);
								map.put(relayTimedTaskCtr.getGatewayMac(), relayTaskDetailsVo);
							} else {
								list = relayTaskDetailsVo.getRelayTaskDetailsList();
							}
							// 遍历星期
							for (String weekId : split) {
								// 组装继电器详情定时任务信息
								RelayTaskDetails relayTaskDetails = new RelayTaskDetails(Integer.parseInt(weekId),
										relayCtr.getRelayId(), relayCtr.getLoopNums());
								list.add(relayTaskDetails);
							}
						}
					}
					// 内容md5值
					String taskMd5 = MD5Utils
							.encrypByMD5(JsonUtils.objectToJson(taskMsg.getTimlyTasks()) + taskMsg.getDayofweek()
									+ taskMsg.getStartTime() + taskMsg.getEndTime() + taskMsg.getDelaymsc());
					relayTimedTaskDataFormat.getTaskMsg().setTaskMd5(taskMd5);
					// 智能网关定时任务
					timedTasks = new TimedTasks(relayTimedTaskDataFormat.getTaskName(), taskMd5,taskMsg.getTaskMode(),
							Integer.parseInt(taskMsg.getDelaymsc()), null, taskMsg.getDayofweek(),
							JsonUtils.objectToJson(taskMsg.getTimlyTasks()), account.getProjectId(),
							DateUtils.fmtStrDateToTime(taskMsg.getStartTime()),
							DateUtils.fmtStrDateToTime(taskMsg.getEndTime()), new Date());
					mode = 0;
				}

				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				// 设置uuid
				taskMsg.setUuid(uuid);
				relayTimedTaskDataFormat.setUuid(uuid);

				// json 数据
				String dataJson = JsonUtils.objectToJson(relayTimedTaskDataFormat);

				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
						relayTimedTaskDataFormat.getRackId(), new Date(), new Date());
				webJsonData.put("log", log);
				webJsonData.put("map", map);
				webJsonData.put("timedTasks", timedTasks);
				webJsonData.put("mode", mode);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1017, dataJson);
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 发送(中控) 继电器实时任务
	 *
	 * @param: relayRealTimeTaskDataFormat 即时任务数据
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月18日 上午10:02:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "sendAddIntelligentGatewayTimedTask", method = RequestMethod.POST)
	public JSONObject sendAddIntelligentGatewayTimedTask(
			@RequestBody(required = false) RelayRealTimeTaskDataFormatVo relayRealTimeTaskDataFormat,
			HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 任务详情
				RelayRealTimeTaskInfoVo taskMsg = relayRealTimeTaskDataFormat.getTaskMsg();
				// 即时任务
				List<RelayRealTimeTaskCtrVo> realTimeTasks = taskMsg.getRealTimeTasks();

				if (realTimeTasks != null) {
					if (relayRealTimeTaskDataFormat.getSaveState()) {

						relayRealTimeTaskDataFormat.setSaveState(false);

						RealtimeTasks realtimeTasks = new RealtimeTasks(relayRealTimeTaskDataFormat.getTaskName(),
								new Date(), JsonUtils.objectToJson(relayRealTimeTaskDataFormat),
								account.getProjectId());
						// 保存即时任务
						realtimeTasksService.save(realtimeTasks);
					}
					// 数据md5值
					taskMsg.setTaskMd5(MD5Utils.encrypByMD5(JsonUtils.objectToJson(realTimeTasks)));
				}

				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				// 设置uuid
				taskMsg.setUuid(uuid);
				relayRealTimeTaskDataFormat.setUuid(uuid);
				// json 数据
				String dataJson = JsonUtils.objectToJson(relayRealTimeTaskDataFormat);

				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(), null,
						new Date(), new Date());
				webJsonData.put("log", log);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1018, dataJson);
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
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
	 * @Function: SendControlServerController.java
	 * @Description: 发送(中控) 删除智能网关定时任务
	 *
	 * @param: timedTasks 智能网关定时任务
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月18日 上午10:02:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "sendDeleteIntelligentGatewayRelayTimedTask", method = RequestMethod.POST)
	public JSONObject sendDeleteIntelligentGatewayRelayTimedTask(@RequestBody TimedTasks timedTasks,
			HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {

				// 获取定时任务信息
				timedTasks = timedTasksService.findById(timedTasks.getTaskId());

				if (timedTasks.getRackIds() != null && timedTasks.getRackIds().length() > 0) {

					JSONObject dataJson = new JSONObject();
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					dataJson.put("uuid", uuid);
					dataJson.put("rackId", timedTasks.getRackIds());
					// 信息
					JSONObject taskMsg = new JSONObject();
					taskMsg.put("uuid", uuid);
					taskMsg.put("taskmd5", timedTasks.getTaskMd5());
					dataJson.put("taskMsg", taskMsg);

					// 组装web数据（用于后续操作）
					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.DELETE.name(),
							null, new Date(), new Date());
					webJsonData.put("log", log);
					webJsonData.put("timedTasks", timedTasks);
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1024, dataJson.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
				} else {
					// 删除定时任务
					timedTasksService.deleteById(timedTasks.getTaskId());
				}
				result = "send successful";
			} else {
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
	 * @Description: 发送批量删除从录放精灵 定时任务
	 *
	 * @param: taskMd5s 定时任务md5值（逗号隔开,）
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年7月20日 上午10:50:12
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "sendBatchDeleteSpriteTimedTask.do", method = RequestMethod.GET)
	public JSONObject sendBatchDeleteSpriteTimedTask(String taskMd5s, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 获取中控客户端信息
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getControlClient();
			if (socketClientInfoVo != null) {
				// 所有机柜ID(逗号隔开)
				String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());

				JSONObject dataJson = new JSONObject();
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				dataJson.put("uuid", uuid);
				dataJson.put("rackId", cabinetNums);

				List<String> md5List = Arrays.asList(taskMd5s.split(","));

				// 信息
				JSONObject taskMsg = new JSONObject();
				taskMsg.put("uuid", uuid);
				taskMsg.put("taskMd5s", md5List);
				dataJson.put("taskMsg", taskMsg);
				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				// 日志
				Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.DELETE.name(), null,
						new Date(), new Date());
				webJsonData.put("log", log);
				webJsonData.put("taskMd5s", taskMd5s);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1023, dataJson.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);

				result = "send successful";
			} else {
				result = "not online";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

}
