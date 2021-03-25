package com.lovdmx.control.controller.head1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.SftpException;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;
import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.JsonUtils;
import com.lovdmx.control.common.utils.MD5Utils;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.common.utils.SFTPUtil;
import com.lovdmx.control.common.utils.StringJudgeUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumCyclicMode;
import com.lovdmx.control.pojo.enums.EnumErrType;
import com.lovdmx.control.pojo.enums.EnumFileType;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.pojo.enums.EnumTaskType;
import com.lovdmx.control.service.ErrService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.SpriteDeviceService;
import com.lovdmx.control.service.SubtasksService;
import com.lovdmx.control.service.TasksService;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.FilePathVo;
import com.lovdmx.control.vo.SendSplitVo;
import com.lovdmx.control.vo.SocketClientInfoVo;
import com.lovdmx.control.vo.SplitFileVo;
import com.lovdmx.control.vo.TaskAssembleMd5Vo;
import com.lovdmx.control.vo.TaskFileTypeInfoVo;
import com.lovdmx.control.vo.TaskPauseOrRestoreVo;
import com.lovdmx.control.vo.TimedTaskVo;
import com.lovdmx.control.vo.UploadFileVo;
import com.lovdmx.control.vo.UploadStatus;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProgramController.java
 * @Description: 节目管理控制器
 *
 * @version: v1.1.0
 * @author: syz
 * @date: 2019年10月15日 下午1:44:33
 *
 */
@Controller
@CrossOrigin
@RequestMapping("/program/")
public class ProgramController extends BaseController {

	// 录放精灵
	@Autowired
	private SpriteDeviceService spriteDeviceService;

	// 上传灯光文件
	@Autowired
	private UploadEdlmxService uploadEdlmxService;

	// 上传视频文件
	@Autowired
	private UploadVideosService uploadVideosService;

	// 子任务
	@Autowired
	private SubtasksService subtasksService;

	// 录放精灵定时任务
	@Autowired
	private TasksService tasksService;

	// 机架
	@Autowired
	private RackDeviceService rackDeviceService;

	// 报警
	@Autowired
	private ErrService errService;

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
	public JSONObject getDirectoryDataList(HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 主录放精灵狀態
			boolean spriteOnlineStatus = (boolean) request.getAttribute("sprite_Online_Status");
			// 判断是否在线
			if (spriteOnlineStatus) {
				// 获取主录放精灵客户端信息
				SocketClientInfoVo spriteSocketClient = OnLineClientUtils.getSpriteClient();
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x2021, null);
				// 发送
				AnalysisClientSocketThread.transmit(spriteSocketClient.getAnalysisSocketThread(), dataBytes);
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
	public JSONObject getSpriteOnlineNumber(HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
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
				result = "no permission";
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
	public JSONObject getTasksDataList(HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				List<Tasks> tasksList = tasksService.findByProjectId(account.getProjectId());
				resultJson.put("tasksList", tasksList);
				result = "true";
			} else {
				result = "no permission";
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
	public JSONObject getSubtasksDataList(HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 所有子任务
				List<Subtasks> subtasksList = subtasksService.findByParentId(account.getProjectId());
				resultJson.put("subtasksList", subtasksList);
				result = "true";
			} else {
				result = "no permission";
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
	public JSONObject updateSubtask(Subtasks subtask, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";

		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);

		try {
			// 登录状态
			boolean loginStatus = (boolean) request.getAttribute("login_Status");
			// 判断是否在线
			if (loginStatus) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 3)) {
					// 获取子任务信息
					Subtasks isExitsSubtasks = subtasksService.findBySubtaskName(subtask.getSubtaskName(),
							account.getAccountId());
					if (isExitsSubtasks == null || isExitsSubtasks.getSubtaskId() == subtask.getSubtaskId()) {
						// 保存
						subtasksService.update(subtask);
						result = "succeed";
						// 日志
						Log log = new Log(account.getProjectId(), account.getAccountId(),
								EnumOperationMode.UPDATE.name(), "修改子任务", new Date(), new Date());
						// 添加操作日志
						addlogInfo(log);
					} else {
						result = "already exists";
					}
				} else {
					result = "no permission";
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
	public JSONObject addSubtask(Subtasks subtask, HttpServletRequest request, HttpSession session) {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		try {
			// 登录状态
			boolean loginStatus = (boolean) request.getAttribute("login_Status");
			// 判断是否在线
			if (loginStatus) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 1)) {
					// 判断子任务名是否存在
					Subtasks subtasks = subtasksService.findBySubtaskName(subtask.getSubtaskName(),
							account.getProjectId());
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
					result = "no permission";
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
	public JSONObject findSubtaskExits(String subtaskName, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 根据条件获取报警信息
				Subtasks subtask = subtasksService.findBySubtaskName(subtaskName, account.getProjectId());
				if (subtask == null) {
					result = "true";
				} else {
					result = "already exists";
				}
			} else {
				result = "no permission";
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
	public JSONObject findTaskExit(String taskName, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 根据条件获取报警信息
				Tasks task = tasksService.findByProjectIdAndTaskName(account.getProjectId(), taskName);
				if (task == null) {
					result = "true";
				} else {
					result = "already exists";
				}
			} else {
				result = "no permission";
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
	public JSONObject sendVideoSplit(SendSplitVo sendSplitVo, HttpServletRequest request, HttpSession session)
			throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
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
						dataJson.put("projectId", account.getProjectId());

						// 组装web数据（用于后续操作）
						JSONObject webJsonData = new JSONObject();
						// 日志
						Log log = new Log(account.getProjectId(), account.getAccountId(),
								EnumOperationMode.UPDATE.name(), "切割视频", new Date(), new Date());
						webJsonData.put("log", log);
						webJsonData.put("projectId", account.getProjectId());
						// state (-1 分发丢失,1 正常分发);
						webJsonData.put("state", 1);
						// 添加ProjectAnalysisOpCodeThread对象的map中
						ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
						// 组装数据
						byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1001, dataJson.toString());
						// 发送数据
						AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
						result = "send successful";
					}
				} else {
					result = "no permission";
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
	public JSONObject sendEdlmxSplit(SendSplitVo sendSplitVo, HttpServletRequest request, HttpSession session)
			throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
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
						dataJson.put("projectId", account.getProjectId());
						// 组装web数据（用于后续操作）
						JSONObject webJsonData = new JSONObject();
						// 日志
						Log log = new Log(account.getProjectId(), account.getAccountId(),
								EnumOperationMode.UPDATE.name(), "添加上传信息", new Date(), new Date());
						webJsonData.put("log", log);
						webJsonData.put("projectId", account.getProjectId());
						// state (-1 分发丢失,1 正常分发);
						webJsonData.put("state", 1);
						// 添加ProjectAnalysisOpCodeThread对象的map中
						ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
						// 组装数据
						byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1002, dataJson.toString());
						// 发送数据
						AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
						result = "send successful";
					}
				} else {
					result = "no permission";
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
	public JSONObject addTimedTask(Tasks task, HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
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
							cabinetNums = errService.findLoseGroupCounatRackIdByTaskNameAndErrType(
									account.getProjectId(), task.getTaskName(), EnumErrType.Task.name());
							// 判断所有机柜 定时任务是否存在
							if (cabinetNums.equals("")) {
								result = "task already exists";
								flag = false;
							} else {
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
							List<UploadEdlmx> uploadEdlmxList = uploadEdlmxService
									.findByMd5s(lmxsbl.toString().split(","));
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
									task.getMode(), task.getCyclicDate(),
									DateUtils.currentlyStrDate(task.getStartDate()),
									DateUtils.currentlyStrDate(task.getEndDate()), task.getStrStartTime(),
									task.getStrEndTime(), taskFileTypeInfoList, uuid);

							// 组装发送数据
							JSONObject dataJson = new JSONObject();
							dataJson.put("taskMsg", taskVo);
							dataJson.put("rackId", cabinetNums);
							dataJson.put("projectId", account.getProjectId());
							dataJson.put("uuid", uuid);

							// 组装web数据（用于后续操作）
							JSONObject webJsonData = new JSONObject();
							// 日志
							Log log = new Log(account.getProjectId(), account.getAccountId(),
									EnumOperationMode.ADD.name(), "添加上传信息", new Date(), new Date());
							webJsonData.put("task", task);
							webJsonData.put("accountId", account.getAccountId());
							webJsonData.put("projectId", account.getProjectId());
							webJsonData.put("log", log);
							// state (-1 分发丢失,1 正常分发);
							webJsonData.put("state", 1);
							// 添加ProjectAnalysisOpCodeThread对象的map中
							ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);

							// 组装数据
							byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1003, dataJson.toJSONString());
							// 发送数据
							AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(),
									dataBytes);
							result = "send successful";
						}
					} else {
						result = "taskName already exists";
					}
				} else {
					result = "no permission";
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
	 * @Function: UploadFileController.java
	 * @Description: web上传视频文件
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 上午11:41:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月28日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam(value = "videoFile", required = false) CommonsMultipartFile videoFile,
			Integer minute, HttpServletRequest request, HttpSession session) {
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 反馈状态
		String result = "";
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 1)) {
				// 关闭事务自动提交
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				TransactionStatus status = platformTransactionManager.getTransaction(def);
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 组装新增的文件路径
				StringBuilder addFilePaths = new StringBuilder();
				// sftp对象
				SFTPUtil sftp = null;
				// 文件名
				String newFileNameVideo = "";

				try {
					// 文件的MD5值
					String md5 = DigestUtils.md5Hex(videoFile.getInputStream());
					// 判断文件是否上传过
					UploadVideos video = uploadVideosService.findByMd5(md5);
					// 初始化sftp
					sftp = new SFTPUtil();
					// 认证
					sftp.login();
					// 上传文件名
					String originFileNameVideo = videoFile.getOriginalFilename();
					// 新文件名
					newFileNameVideo = UUID.randomUUID()
							+ originFileNameVideo.substring(originFileNameVideo.lastIndexOf("."));
					if (video == null) {
						// 上传文件
						sftp.upload(SFTPUtil.basePath, SFTPUtil.videoDirectory, newFileNameVideo,
								videoFile.getInputStream());
						// 保存上传文件名
						addFilePaths.append(newFileNameVideo);
						// 获取web上传的最后一条信息
						UploadVideos lastUploadVideo = uploadVideosService
								.findWebUploadLastInfoByProjectIdAndUploadRole(account.getProjectId(), 0);
						int index = 1;
						if (lastUploadVideo == null) {
							index = 101;
						} else {
							// 下标加一
							index += lastUploadVideo.getFileIndex();
						}
						// 构造函数赋值
						UploadVideos uploadVideo = new UploadVideos(
								SFTPUtil.webBasePath + SFTPUtil.videoDirectory + newFileNameVideo, originFileNameVideo,
								account.getProjectId(), 0, index, md5, minute, 0);
						// 保存数据
						uploadVideosService.save(uploadVideo);
						result = "succeed";
					} else {
						// 文件目录
						String filePath = video.getFilePath();
						// 获取文件信息
						try {
							// 抛出异常 则该文件 不存在, 重新添加文件
							sftp.getFileInfo(filePath);
							result = "already exists";
						} catch (SftpException sftpex) {
							// 上传文件
							sftp.upload(SFTPUtil.basePath, SFTPUtil.videoDirectory, newFileNameVideo,
									videoFile.getInputStream());
							// 保存上传文件名
							addFilePaths.append(newFileNameVideo);
							video.setFilePath(SFTPUtil.basePath + SFTPUtil.videoDirectory + newFileNameVideo);
							// 修改文件路径
							uploadVideosService.update(video);
							result = "succeed";
						}
					}
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
							"上传视频", new Date(), new Date());
					// 添加操作日志
					addlogInfo(log);
					platformTransactionManager.commit(status);
					// 逻辑代码，可以写上你的逻辑处理代码
				} catch (Exception ex) {
					ex.printStackTrace();
					platformTransactionManager.rollback(status);
					result = "fail";
					if (sftp != null) {
						try {
							// 删除文件
							sftp.delete(SFTPUtil.basePath + SFTPUtil.videoDirectory, addFilePaths.toString());
						} catch (SftpException e) {
							e.printStackTrace();
						}
					}
				} finally {
					// 关闭流
					if (sftp != null)
						sftp.logout();
				}
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		return result;
	}

	/**
	 * 这里是获取上传文件状态信息的访问接口
	 * 
	 * @param session
	 * @return UploadStatus
	 */
	@ResponseBody
	@RequestMapping("getStatus")
	public UploadStatus getStatus(HttpSession session) {
		return (UploadStatus) session.getAttribute("upload_status");
	}

	@ResponseBody
	@RequestMapping("getInfo")
	public Map<String, Object> getUploadInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		response.setHeader("Cache-Control", "no-store"); // 禁止浏览器缓存
		response.setHeader("Pragrma", "no-cache"); // 禁止浏览器缓存
		response.setDateHeader("Expires", 0); // 禁止浏览器缓存

		UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("upload_status");// 从session中读取上传信息
		if (status == null) {
			result.put("error", "没发现上传文件!");
			return result;
		}

		long startTime = status.getStartTime(); // 上传开始时间
		long currentTime = System.currentTimeMillis(); // 现在时间
		long time = (currentTime - startTime) / 1000 + 1;// 已经传顺的时间 单位：s
		double velocity = status.getBytesRead() / time; // 传输速度：byte/s
		double totalTime = status.getContentLength() / velocity; // 估计总时间
		double timeLeft = totalTime - time; // 估计剩余时间
		int percent = (int) (100 * (double) status.getBytesRead() / (double) status.getContentLength()); // 百分比
		double length = status.getBytesRead() / 1024 / 1024; // 已完成数
		double totalLength = status.getContentLength() / 1024 / 1024; // 总长度 M
		result.put("startTime", startTime);
		result.put("currentTime", currentTime);
		result.put("time", time);
		result.put("velocity", velocity);
		result.put("totalTime", totalTime);
		result.put("timeLeft", timeLeft);
		result.put("percent", percent);
		result.put("length", length);
		result.put("totalLength", totalLength);
		return result;
	}

	/**
	 * 
	 * @Function: SendSpriteServiceController.java
	 * @Description: (主)录放精灵上传文件
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月2日 下午5:24:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月2日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("uploadSpriteFile")
	public JSONObject uploadSpriteFile(UploadFileVo uploadFileVo, HttpServletRequest request, HttpSession session)
			throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 主录放精灵狀態
			boolean spriteOnlineStatus = (boolean) request.getAttribute("sprite_Online_Status");
			// 判断是否在线
			if (spriteOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo spriteSocketClient = OnLineClientUtils.getSpriteClient();
					uploadFileVo.setAccountId(account.getAccountId());
					uploadFileVo.setProjectId(account.getProjectId());
					boolean uploadStatus = false;
					if (uploadFileVo.getFileType() == 0) {
						UploadVideos uploadVideo = uploadVideosService.findByMd5(uploadFileVo.getMd5());
						uploadStatus = uploadVideo == null ? true : false;
					} else {
						UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(uploadFileVo.getMd5());
						uploadStatus = uploadEdlmx == null ? true : false;
					}
					// 判断是否存在
					if (uploadStatus) {
						// 组装web数据（用于后续操作）
						JSONObject webJsonData = (JSONObject) JSONObject.toJSON(uploadFileVo);
						// 日志
						Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
								"添加上传信息", new Date(), new Date());
						// 获取UUID
						String uuid = UUID.randomUUID().toString();
						webJsonData.put("uuid", uuid);
						webJsonData.put("log", log);
						webJsonData.put("projectId", account.getProjectId());

						// 组装发送客户端数据
						JSONObject sendClientJson = new JSONObject();
						sendClientJson.put("filePath", uploadFileVo.getFilePath());
						sendClientJson.put("uuid", uuid);
						sendClientJson.put("projectId", account.getProjectId());

						// 添加ProjectAnalysisOpCodeThread对象的map中
						ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);

						// 组装数据
						byte[] dataBytes = PageageUtils.assemblyDataPackage(0x2025, sendClientJson.toJSONString());
						// 发送
						AnalysisClientSocketThread.transmit(spriteSocketClient.getAnalysisSocketThread(), dataBytes);
						result = "send successful";
					} else {
						result = "already exists";
					}
				} else {
					result = "no permission";
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
	public JSONObject sendPauseTask(HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
					// 所有机柜编号(逗号隔开)
					String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					JSONObject jsonData = new JSONObject();
					TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "0");

					jsonData.put("rackId", cabinetNums);
					jsonData.put("projectId", account.getProjectId());
					jsonData.put("uuid", uuid);
					jsonData.put("taskMsg", taskPauseOrRestore);

					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"修改所有定时任务状态为暂停", new Date(), new Date());
					webJsonData.put("account", account);
					webJsonData.put("log", log);
					webJsonData.put("projectId", account.getProjectId());
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1004, jsonData.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "no permission";
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
	public JSONObject sendRestoreTask(HttpServletRequest request, HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
					// 所有机柜编号(逗号隔开)
					String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					JSONObject jsonData = new JSONObject();

					TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "1");

					jsonData.put("rackId", cabinetNums);
					jsonData.put("projectId", account.getProjectId());
					jsonData.put("uuid", uuid);
					jsonData.put("taskMsg", taskPauseOrRestore);

					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"修改所有定时任务状态为启用", new Date(), new Date());
					webJsonData.put("account", account);
					webJsonData.put("log", log);
					webJsonData.put("projectId", account.getProjectId());
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1005, jsonData.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "no permission";
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
	public JSONObject sendAssignNodePauseTask(String rackIds, String taskMd5s, HttpServletRequest request,
			HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					JSONObject jsonData = new JSONObject();
					// 转集合
					List<String> taskMd5List = Arrays.asList(taskMd5s.split(","));
					TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "0", taskMd5List);

					jsonData.put("rackId", rackIds);
					jsonData.put("projectId", account.getProjectId());
					jsonData.put("uuid", uuid);
					jsonData.put("taskMsg", taskPauseOrRestore);

					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"修改指定定时任务状态为暂停", new Date(), new Date());
					webJsonData.put("account", account);
					webJsonData.put("log", log);
					webJsonData.put("taskPauseOrRestore", taskPauseOrRestore);
					webJsonData.put("projectId", account.getProjectId());
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1004, jsonData.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "no permission";
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
	public JSONObject sendAssignNodeRestoreTask(String rackIds, String taskMd5s, HttpServletRequest request,
			HttpSession session) throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					JSONObject jsonData = new JSONObject();

					// 转集合
					List<String> taskMd5List = Arrays.asList(taskMd5s.split(","));
					TaskPauseOrRestoreVo taskPauseOrRestore = new TaskPauseOrRestoreVo(uuid, "1", taskMd5List);

					jsonData.put("rackId", rackIds);
					jsonData.put("projectId", account.getProjectId());
					jsonData.put("uuid", uuid);
					jsonData.put("taskMsg", taskPauseOrRestore);

					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.UPDATE.name(),
							"修改指定定时任务状态为启用", new Date(), new Date());
					webJsonData.put("account", account);
					webJsonData.put("log", log);
					webJsonData.put("taskPauseOrRestore", taskPauseOrRestore);
					webJsonData.put("projectId", account.getProjectId());
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1005, jsonData.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "no permission";
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
	@RequestMapping(value = "sendBatchDeleteSpriteTimedTask", method = RequestMethod.GET)
	public JSONObject sendBatchDeleteSpriteTimedTask(String taskMd5s, HttpServletRequest request, HttpSession session)
			throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 账号
			Account account = (Account) session.getAttribute("lovdmxAdmin");
			// 中控狀態
			boolean controlOnlineStatus = (boolean) request.getAttribute("control_Online_Status");
			// 判断是否在线
			if (controlOnlineStatus) {
				// 获取角色
				Role role = (Role) session.getAttribute("role");
				// 判断是否有权限
				if (role.getRoleId() == 1 || role.getRoleId() == 2
						|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 5)) {
					// 获取主录放精灵客户端信息
					SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
					// 所有机柜ID(逗号隔开)
					String cabinetNums = rackDeviceService.findGroupCounatRackIdByProjectId(account.getProjectId());

					JSONObject dataJson = new JSONObject();
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					dataJson.put("uuid", uuid);
					dataJson.put("rackId", cabinetNums);
					dataJson.put("projectId", account.getProjectId());

					List<String> md5List = Arrays.asList(taskMd5s.split(","));

					// 信息
					JSONObject taskMsg = new JSONObject();
					taskMsg.put("uuid", uuid);
					taskMsg.put("taskMd5s", md5List);
					dataJson.put("taskMsg", taskMsg);
					// 组装web数据（用于后续操作）
					JSONObject webJsonData = new JSONObject();
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.DELETE.name(),
							null, new Date(), new Date());
					webJsonData.put("log", log);
					webJsonData.put("taskMd5s", taskMd5s);
					webJsonData.put("projectId", account.getProjectId());
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1023, dataJson.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);

					result = "send successful";
				} else {
					result = "no permission";
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

}