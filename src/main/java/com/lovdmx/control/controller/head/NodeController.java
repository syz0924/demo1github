package com.lovdmx.control.controller.head;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumFileType;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.service.SubtasksService;
import com.lovdmx.control.service.TasksService;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: NodeController.java
 * @Description: 节点控制器
 *
 * @version: v1.1.0
 * @author: syz
 * @date: 2019年10月15日 下午1:40:21
 *
 */
@Controller
@RequestMapping("/node/")
public class NodeController extends BaseController {

	//上传灯光文件
	@Autowired
	private UploadEdlmxService uploadEdlmxService;
	
	//上传视频文件
	@Autowired
	private UploadVideosService uploadVideosService;
	
	//子任务
	@Autowired
	private SubtasksService subtasksService;
	
	//录放精灵定时任务
	@Autowired
	private TasksService tasksService;
	
	
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
	
}
