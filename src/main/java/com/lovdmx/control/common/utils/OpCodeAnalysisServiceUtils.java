/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.utils 
 * @author: syz  
 * @date: 2019年3月1日 上午11:26:07 
 */
package com.lovdmx.control.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.httpVo.Directory;
import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.DelSpriteTasks;
import com.lovdmx.control.pojo.DelTasks;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.Relay;
import com.lovdmx.control.pojo.RelayTaskDetails;
import com.lovdmx.control.pojo.SpriteTasks;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.TimedTasks;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.vo.RelayTaskDetailsVo;
import com.lovdmx.control.vo.SocketClientInfoVo;
import com.lovdmx.control.vo.TaskPauseOrRestoreVo;
import com.lovdmx.control.vo.UploadFileVo;

import net.sf.json.JSONArray;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteAnalysisServiceUtils.java
 * @Description: socket业务判断
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月1日 上午11:26:07
 *
 */
public class OpCodeAnalysisServiceUtils extends SocketUtils {

	/**
	 * 
	 * @Function: SpriteAnalysisServiceUtils.java
	 * @Description: 组装设备信息
	 *
	 * @param:jsonObject JSON对象
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月1日 下午3:23:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public static String getAssembleDeviceInfo(JSONObject jsonObject) throws Exception {
		// 項目ID
		Integer projectId = (Integer) jsonObject.get("projectId");
		// 1、使用JSONArray
		JSONArray json = JSONArray.fromObject(jsonObject.get("dataJson"));// userStr是json字符串
		// 2、 添加子类型的class
		Map classMap = new HashMap();
		classMap.put("lightFileList", LightFile.class);
		classMap.put("videoFileList", VideoFile.class);
		// 3、JOSN数据转 对象
		List<Directory> directoryList = (List<Directory>) JSONArray.toList(json, Directory.class, classMap);
		// 获取已上传的视频信息
		List<UploadVideos> uploadVideosList = uploadVideosService.findByProjectId(projectId);
		// 获取已上传的灯光文件信息
		List<UploadEdlmx> uploadEdlmxList = uploadEdlmxService.findByProjectId(projectId);
		// 4、遍历 组装上传状态
		for (Directory directory : directoryList) {
			for (LightFile lightFile : directory.getLightFileList()) {
				for (UploadEdlmx uploadEdlmx : uploadEdlmxList) {
					// 判断是否上传
					if (uploadEdlmx.getMd5().equals(lightFile.getMd5())) {
						lightFile.setFilePath(uploadEdlmx.getFilePath());
						lightFile.setUploadId(uploadEdlmx.getEdlmxId());
						// 判断是否分发
						if (uploadEdlmx.getRtrLoaded() == 1) {
							lightFile.setStatus(2);
						} else {
							lightFile.setStatus(1);
						}
						uploadEdlmx.setExits(true);
						break;
					}
				}
			}

			for (VideoFile videoFile : directory.getVideoFileList()) {
				for (UploadVideos uploadVideo : uploadVideosList) {
					// 判断是否上传
					if (uploadVideo.getMd5().equals(videoFile.getMd5())) {
						videoFile.setFilePath(uploadVideo.getFilePath());
						videoFile.setUploadId(uploadVideo.getVideoId());
						// 判断是否分发
						if (uploadVideo.getRtrLoaded() == 1) {
							videoFile.setStatus(2);
						} else {
							videoFile.setStatus(1);
						}
						uploadVideo.setExits(true);
						break;
					}
				}
			}
		}
		// 数据库已上传(目录不存在)添加到集合

		List<LightFile> lightFileList = directoryList.get(directoryList.size() - 1).getLightFileList();
		List<VideoFile> videoFileList = directoryList.get(directoryList.size() - 1).getVideoFileList();

		for (UploadEdlmx uploadEdlmx : uploadEdlmxList) {
			if (!uploadEdlmx.isExits()) {
				LightFile lightFile = new LightFile(uploadEdlmx.getFileIndex(), uploadEdlmx.getFilePath(),
						uploadEdlmx.getFileName(), uploadEdlmx.getMd5(), uploadEdlmx.getTime(),
						uploadEdlmx.getRtrLoaded() == 0 ? 1 : 2, "DMX", uploadEdlmx.getEdlmxId());
				lightFileList.add(lightFile);
			}
		}
		for (UploadVideos uploadVideo : uploadVideosList) {
			if (!uploadVideo.isExits()) {
				VideoFile videoFile = new VideoFile(uploadVideo.getFileIndex(), uploadVideo.getFilePath(),
						uploadVideo.getFileName(), uploadVideo.getMd5(), uploadVideo.getTime(),
						uploadVideo.getRtrLoaded() == 0 ? 1 : 2, "VIDEO", uploadVideo.getVideoId());
				videoFileList.add(videoFile);
			}
		}

		return JsonUtils.objectToJson(directoryList);
	}

	/**
	 * @Function: SpriteAnalysisServiceUtils.java
	 * @Description: 添加视频或灯光文件
	 *
	 * @param:dataJson 上传成功添加 数据
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月1日 下午2:56:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月1日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void addUploadVideoOrEdlmxFile(JSONObject dataJson, UploadFileVo uploadFileVo) throws Exception {
		// 文件路径
		String filePath = (String) dataJson.get("filePath");
		// 组装web路径
		filePath = SFTPUtil.webBasePath
				+ filePath.substring(filePath.lastIndexOf("/", filePath.lastIndexOf("/") - 1) + 1);
		// 文件名
		String fileName = filePath.substring((filePath.lastIndexOf("/") + 1), filePath.length());
		Integer num = 0;
		// 灯光
		if (uploadFileVo.getFileType() == 0) {
			// JOSN对象转 类对象
			UploadEdlmx uploadEdlmx = JSON.toJavaObject(dataJson, UploadEdlmx.class);
			// 获取主录放精灵上传的最后一条信息
			UploadEdlmx lastUploadEdlmx = uploadEdlmxService
					.findWebUploadLastInfoByProjectIdAndUploadRole(uploadEdlmx.getProjectId(), 1);
			int index = 1;
			if (lastUploadEdlmx != null) {
				// 下标加一
				index += lastUploadEdlmx.getFileIndex();
			}
			uploadEdlmx.setRtrLoaded(0);
			uploadEdlmx.setFileIndex(index);
			uploadEdlmx.setUploadRole(1);
			uploadEdlmx.setTime(uploadFileVo.getTime());
			uploadEdlmx.setFilePath(filePath);
			uploadEdlmx.setFileName(fileName);
			num = uploadEdlmxService.save(uploadEdlmx);
			// 视频
		} else {
			// JOSN对象转 类对象
			UploadVideos uploadVideo = JSON.toJavaObject(dataJson, UploadVideos.class);
			// 获取主录放精灵上传的最后一条信息
			UploadVideos lastUploadVideo = uploadVideosService
					.findWebUploadLastInfoByProjectIdAndUploadRole(uploadVideo.getProjectId(), 1);
			int index = 1;
			if (lastUploadVideo != null) {
				// 下标加一
				index += lastUploadVideo.getFileIndex();
			}
			uploadVideo.setRtrLoaded(0);
			uploadVideo.setFileIndex(index);
			uploadVideo.setUploadRole(1);
			uploadVideo.setTime(uploadFileVo.getTime());
			uploadVideo.setFilePath(filePath);
			uploadVideo.setFileName(fileName);
			num = uploadVideosService.save(uploadVideo);
		}
		// dataJson.put("mac", uploadFileVo.getMac());
		// 添加状态
		dataJson.put("result", num == 0 ? true : false);
	}

	/**
	 * 
	 * @Function: SpriteAnalysisServiceUtils.java
	 * @Description: 添加操作日志信息
	 *
	 * @param:jsonObject JSON对象
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月5日 下午5:01:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月5日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean addLogInfo(Log log) throws Exception {
		// 添加操作日志
		Integer save = logService.save(log);
		return save == 0 ? false : true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加上传的灯光或视频文件
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:37:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean addUploadVideoOrLmxInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {
		// 反馈上传状态
		Integer status = (Integer) socketClientData.get("status");
		// 判断是否成功
		if (status == 1 || status == 2) {
			UploadFileVo uploadFileVo = (UploadFileVo) JSONObject.toJavaObject(webClientData, UploadFileVo.class);

			// 判断是否存在
			boolean isExits = false;
			if (status == 2) {
				String md5 = socketClientData.getString("md5");
				// 判断是否存在
				if (uploadFileVo.getFileType() == 0) {
					UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(md5);
					socketClientData.put("status", 1);
					isExits = uploadEdlmx != null ? true : false;
				} else {
					UploadVideos uploadVideos = uploadVideosService.findByMd5(md5);
					socketClientData.put("status", 1);
					isExits = uploadVideos != null ? true : false;
				}
			}
			// 不存在添加
			if (!isExits) {
				// 上传成功添加 记录
				OpCodeAnalysisServiceUtils.addUploadVideoOrEdlmxFile(socketClientData, uploadFileVo);
				// 日志
				Log log = (Log) webClientData.get("log");

				OpCodeAnalysisServiceUtils.addLogInfo(log);
				// 更新 目录文件列表
				JSONObject directoryDataJson = new JSONObject();
				directoryDataJson.put("dataJson", ServerSocketThread.getDirectoryListData());
				directoryDataJson.put("projectId", projectId);
				String dataJson = OpCodeAnalysisServiceUtils.getAssembleDeviceInfo(directoryDataJson);

				// 赋值最新的文件信息
				ServerSocketThread.setDirectoryListData(dataJson);
				socketClientData.put("dataJson", dataJson);
			}
		}

		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 修改已上传视频分发状态
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData    web客户传送数据
	 * @param uuid             md5值
	 * @param webClientDataMap web信息map对象
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:44:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 *        JSON======>>>{"MAC":"00:E0:AC:CE:35:0D","OrgFileMd5":
	 *        "0e66e0c78734d14bdebbdc992aafa0aa","Percentage":"100"}
	 */
	public static boolean updateUpdateVideoSplitStatus(JSONObject socketClientData, JSONObject webClientData,
			String uuid, Map<String, JSONObject> webClientDataMap) throws Exception {
		// state (-1 分发丢失,1 正常分发);
		Integer state = (Integer) webClientData.get("state");
		// 进度
		String percentage = socketClientData.getString("Percentage");
		Log log = (Log) webClientData.get("log");
		if (percentage.equals("100")) {
			// 获取md5值
			String fileMd5 = socketClientData.getString("OrgFileMd5");
			UploadVideos uploadVideos = uploadVideosService.findByMd5(fileMd5);
			// 日志
			if (state == 1) {
				uploadVideos.setRtrLoaded(1);
				uploadVideosService.update(uploadVideos);
				log.setContent(uuid + "视频分发成功");

				SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getSpriteClient();
				if (socketClientInfoVo != null) {
					// 请求灯光和视频文件包
					byte[] dataByte = PageageUtils.assemblyDataPackage(0x2021, null);
					// 发送
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataByte);
				}

			} else {
				log.setContent(uuid + "丢失视频分发成功");
			}
			webClientData.put("md5", uploadVideos.getMd5());
		} else {
			if (state == 1) {
				log.setContent(uuid + "视频分发失败");
			} else {
				log.setContent(uuid + "丢失视频分发失败");
			}
			webClientDataMap.remove(uuid);
		}
		// 添加日志
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加 RTR视频信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午4:17:33
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackRtrVideoInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) {

		JSONObject jsonData = new JSONObject();
		// RTR MAC
		String rtrMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdRtrMac(projectId, rtrMac);
		// 进度
		String percentage = socketClientData.getString("Percentage");
		if (percentage.equals("-1")) {
			jsonData.put("result", "false");
		} else if (percentage.equals("100")) {
			jsonData.put("result", "true");
		} else {
			jsonData.put("result", "download..");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 修改已上传灯光分发状态
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData    web客户传送数据
	 * @param uuid             md5值
	 * @param webClientDataMap web信息map对象
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:44:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean updateUpdateLightingSplitStatus(JSONObject socketClientData, JSONObject webClientData,
			String uuid, Map<String, JSONObject> webClientDataMap) throws Exception {
		// state (-1 分发丢失,1 正常分发);
		Integer state = (Integer) webClientData.get("state");
		// 进度
		String percentage = socketClientData.getString("Percentage");
		Log log = (Log) webClientData.get("log");
		if (percentage.equals("100")) {
			// 获取md5值
			String fileMd5 = socketClientData.getString("OrgFileMd5");
			UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(fileMd5);
			if (state == 1) {
				uploadEdlmx.setRtrLoaded(1);
				uploadEdlmxService.update(uploadEdlmx);
				log.setContent(uuid + "灯光分发成功");

				SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getSpriteClient();
				if (socketClientInfoVo != null) {
					// 请求灯光和视频文件包
					byte[] dataByte = PageageUtils.assemblyDataPackage(0x2021, null);
					// 发送
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataByte);
				}
			} else {
				log.setContent(uuid + "丢失灯光分发成功");
			}
			webClientData.put("uploadEdlmx", uploadEdlmx);
		} else {
			if (state == 1) {
				log.setContent(uuid + "灯光分发失败");
			} else {
				log.setContent(uuid + "丢失灯光分发失败");
			}
			webClientDataMap.remove(uuid);
		}
		// 添加日志
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加 SpriteLmx信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午4:17:33
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteLmxInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		String percentage = socketClientData.getString("Percentage");
		if (percentage.equals("-1")) {
			jsonData.put("result", "false");
		} else if (percentage.equals("100")) {
			jsonData.put("result", "true");
		} else {
			jsonData.put("result", "download..");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加定时任务
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:53:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackControlAddTask(JSONObject socketClientData, JSONObject webClientData, String uuid,
			Map<String, JSONObject> webClientDataMap) throws Exception {
		// state (-1 分发丢失,1 正常分发);
		Integer state = (Integer) webClientData.get("state");
		// 获取md5值
		String status = socketClientData.getString("status");

		Tasks task = (Tasks) webClientData.get("task");
		Log log = (Log) webClientData.get("log");
		if (status.equals("1")) {
			if (state == 1) {
				// 添加定时任务
				tasksService.save(task);
				log.setContent(task.getTaskName() + "任务添加成功");
			} else {
				log.setContent(task.getTaskName() + "丢失任务添加成功");
			}
			webClientData.put("taskId", task.getTaskId());

		} else if (status.equals("0")) {
			if (state == 1) {
				log.setContent(task.getTaskName() + "任务添加失败");
			} else {
				log.setContent(task.getTaskName() + "丢失任务添加失败");
			}
			// 移除
			webClientDataMap.remove(uuid);
		}
		// 添加日志
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 反馈暂停任务信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午4:44:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteTaskInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("mac");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {
			Tasks task = (Tasks) webClientData.get("task");
			SpriteTasks spriteTasks = new SpriteTasks(spriteMac, task.getTaskId());
			// 保存任务
			spriteTasksService.save(spriteTasks);

			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加暫停任務信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:57:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackControlPauseTaskInfo(JSONObject socketClientData, JSONObject webClientData)
			throws Exception {
		// 获取md5值
		String status = socketClientData.getString("status");

		Log log = (Log) webClientData.get("log");

		if (status.equals("1")) {
			log.setContent("暂停任务策略 成功");
			// 账号
			Account account = (Account) socketClientData.get("account");
			// 修改任务状态
			tasksService.updateAllStatusByProjectIdAndStatus(account.getProjectId(), 0);
		} else {
			log.setContent("暂停任务策略 失败");
		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 反馈暂停任务信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午4:44:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpritePauseTaskInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {
			// 根据MAC,批量修改任务状态为暂停
			spriteTasksService.updateAllStatusBySpriteMacAndStatus(spriteMac, 0);
			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 添加恢复任務信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午3:57:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackControlRestoreTaskInfo(JSONObject socketClientData, JSONObject webClientData)
			throws Exception {
		// 获取md5值
		String status = socketClientData.getString("status");

		Log log = (Log) webClientData.get("log");

		if (status.equals("1")) {
			log.setContent("恢复任务策略 成功");
			// 账号
			Account account = (Account) socketClientData.get("account");
			// 修改任务状态
			tasksService.updateAllStatusByProjectIdAndStatus(account.getProjectId(), 1);
		} else {
			log.setContent("恢复任务策略 失败");
		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 反馈恢复任务信息
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月26日 下午4:44:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月26日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteRestoreTaskInfo(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {
			// 根据MAC,批量修改任务状态为恢复
			spriteTasksService.updateAllStatusBySpriteMacAndStatus(spriteMac, 1);
			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 智能网关实时任务策略反馈
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年5月16日 上午10:52:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月16日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackIntelligentGatewayAddRealTimeTask(JSONObject socketClientData,
			JSONObject webClientData) throws Exception {
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 日志
		Log log = (Log) webClientData.get("log");
		// 网关Mac
		String gatewayMac = socketClientData.getString("mac");

		if (status.equals("1")) {
			log.setContent(gatewayMac + "操作智能网关实时任务 成功");
			IntelligentGateway intelligentGateway = new IntelligentGateway(gatewayMac, 1);
			// 修改 任务模式
			intelligentGatewayService.updateByMac(intelligentGateway);
		} else {
			log.setContent(gatewayMac + "操作智能网关实时任务 失败");
		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 中控对智能网关定时任务策略反馈
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData    web客户传送数据
	 * @param uuid             md5值
	 * @param webClientDataMap web信息map对象
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月16日 上午10:15:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月16日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackControlGatewayAddTimedTask(JSONObject socketClientData, JSONObject webClientData,
			String uuid, Map<String, JSONObject> webClientDataMap, Integer projectId) throws Exception {
		TimedTasks timedTasks = (TimedTasks) webClientData.get("timedTasks");
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 0保存 1修改
		Integer saveOrUpdate = (Integer) webClientData.get("saveOrUpdate");

		if (status.equals("1")) {
			if (saveOrUpdate == 0) {
				// 判断任务是否存在
				TimedTasks timedTasks1 = timedTasksService.findByTaskMd5(timedTasks.getTaskMd5(), projectId);
				if (timedTasks1 != null) {
					webClientData.put("timedTasks", timedTasks1);
					// socketClientData.put("status", "2");
				} else {
					// 添加 智能网关定时任务
					timedTasksService.save(timedTasks);
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 智能网关定时任务策略反馈
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年5月16日 上午10:16:20
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月16日
	 *        Administrator v1.0.0 修改原因
	 */
	@SuppressWarnings("unchecked")
	public static boolean feedbackIntelligentGatewayAddTimedTask(JSONObject socketClientData, JSONObject webClientData)
			throws Exception {
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 日志
		Log log = (Log) webClientData.get("log");
		// 网关Mac
		String gatewayMac = socketClientData.getString("mac");
		if (status.equals("1")) {
			// 0保存 1修改
			Integer saveOrUpdate = (Integer) webClientData.get("saveOrUpdate");
			if (saveOrUpdate == 0) {
				// 定时任务
				TimedTasks timedTasks = (TimedTasks) webClientData.get("timedTasks");
				Map<String, RelayTaskDetailsVo> map = (HashMap<String, RelayTaskDetailsVo>) webClientData.get("map");
				if (map != null && map.size() > 0) {

					// 判断是否 是单任务 ,如果是单任务删除之前单任务信息
					if (timedTasks.getTaskMode() == 1) {
						// 智能网关信息
						IntelligentGateway intelligentGateway = intelligentGatewayService.findByMac(gatewayMac);
						// 根据网关mac获取 所有继电器信息
						List<Relay> relayList = relayService.findByGatewayMac(gatewayMac);
						StringBuilder sbld = new StringBuilder();
						for (Relay relay : relayList) {
							sbld.append(relay.getId());
							sbld.append(",");
						}
						String[] arrayRelayId = sbld.toString().substring(0, sbld.length() - 1).split(",");

						timedTasks.setRackId(intelligentGateway.getRackId());
						// 获取单任务信息
						List<TimedTasks> timedTaskList = timedTasksService.queryListByConditionIf(timedTasks);

						// 遍历删除
						for (TimedTasks timedTasks2 : timedTaskList) {
							// 根据定时任务id，删除定时任务详情信息
							relayTaskDetailsService.deleteByTaskIdAndArrayRelayId(timedTasks2.getTaskId(),
									arrayRelayId);
							// 机架id(逗号隔开)
							String rackIds = timedTasks2.getRackIds();
							String[] split = rackIds.split(",");
							if (split.length == 1) {
								if (split[0].equals("" + intelligentGateway.getRackId())) {
									// 删除定时任务
									timedTasksService.deleteById(timedTasks2.getTaskId());
								}
							} else {
								// 转成 集合 ,遍历 移除指定机柜id
								List<String> list = Arrays.asList(split);
								StringBuilder sbl = new StringBuilder();
								for (String string : list) {
									if (!string.equals(intelligentGateway.getRackId() + "")) {
										sbl.append(string).append(",");
									}
								}
								// 移除最后一个逗号
								String before_rackIds = sbl.toString();
								if (before_rackIds.length() > 0) {
									before_rackIds = before_rackIds.substring(0, before_rackIds.length() - 1);
								}

								timedTasks2.setRackIds(before_rackIds);
								// 修改
								timedTasksService.update(timedTasks2);
							}
						}

					}

					// 定时任务工具类
					RelayTaskDetailsVo relayTaskDetailsVo = map.get(gatewayMac);
					// 定时任务详情
					List<RelayTaskDetails> relayTaskDetailsList = relayTaskDetailsVo.getRelayTaskDetailsList();
					// 添加成功添加 机架id
					String rackIds = timedTasks.getRackIds();
					// 状态
					boolean flag = false;
					// 判断是否存在
					if (rackIds != null && !rackIds.equals("")) {
						// 判断该任务什么以添加
						List<String> list = Arrays.asList(rackIds.split(","));
						for (String rackId : list) {
							if (rackId.equals("" + relayTaskDetailsVo.getRackId())) {
								flag = true;
								break;
							}
						}
					}
					// 存在则过滤
					if (!flag) {
						// 遍历赋值任务ID
						for (RelayTaskDetails relayTaskDetails : relayTaskDetailsList) {
							relayTaskDetails.setTaskId(timedTasks.getTaskId());
						}
						// 批量添加继电器定时任务
						relayTaskDetailsService.batchInsert(relayTaskDetailsList);
						// 添加成功添加 机架id
						// String rackIds = timedTasks.getRackIds();
						if (rackIds != null && !rackIds.equals("")) {
							rackIds += "," + relayTaskDetailsVo.getRackId();
						} else {
							rackIds = relayTaskDetailsVo.getRackId() + "";
						}
						timedTasks.setRackIds(rackIds);
						// 添加机架id
						timedTasksService.update(timedTasks);
						log.setContent(gatewayMac + "添加智能网关定时任务 成功");
					} else {
						log.setContent(gatewayMac + "智能网关定时任务 已存在");
						socketClientData.put("status", "2");
					}
				}
			}
			// 0 标识定时任务
			IntelligentGateway intelligentGateway = new IntelligentGateway(gatewayMac, 0);
			// 任务模式
			Integer taskMode = webClientData.getInteger("taskMode");
			if (saveOrUpdate == 1 && taskMode != null) {
				intelligentGateway.setTaskMode(taskMode);
			}
			// 修改 任务模式
			Integer num = intelligentGatewayService.updateByMac(intelligentGateway);
			if (num > 0) {
				log.setContent(gatewayMac + "模式修改为定时任务 成功");
			}

		} else {
			log.setContent("中控发送" + gatewayMac + "智能网关定时任务 失败");

		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 智能网关反馈定时任务状态
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年6月5日 下午4:35:03
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月5日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackIntelligentGatewayDeleteTimedTask(JSONObject socketClientData,
			JSONObject webClientData) throws Exception {
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 日志
		Log log = (Log) webClientData.get("log");
		// 网关Mac
		String gatewayMac = socketClientData.getString("mac");
		// 定时任务
		TimedTasks timedTasks = (TimedTasks) webClientData.get("timedTasks");
		if (status.equals("1")) {
			// 智能网关信息
			IntelligentGateway intelligentGateway = intelligentGatewayService.findByMac(gatewayMac);
			// 根据网关mac获取 所有继电器信息
			List<Relay> relayList = relayService.findByGatewayMac(gatewayMac);
			StringBuilder sbld = new StringBuilder();
			for (Relay relay : relayList) {
				sbld.append(relay.getId());
				sbld.append(",");
			}
			String[] arrayRelayId = sbld.toString().substring(0, sbld.length() - 1).split(",");
			// 根据定时任务id，删除定时任务详情信息
			relayTaskDetailsService.deleteByTaskIdAndArrayRelayId(timedTasks.getTaskId(), arrayRelayId);
			// 机架id(逗号隔开)
			String rackIds = timedTasks.getRackIds();
			String[] split = rackIds.split(",");
			if (split.length == 1) {
				if (split[0].equals("" + intelligentGateway.getRackId())) {
					// 删除定时任务
					timedTasksService.deleteById(timedTasks.getTaskId());
					log.setContent("定时任务" + timedTasks.getTaskName() + "删除成功");
				}
			} else {
				// 转成 集合 ,遍历 移除指定机柜id
				List<String> list = Arrays.asList(split);
				StringBuilder sbl = new StringBuilder();
				for (String string : list) {
					if (!string.equals(intelligentGateway.getRackId() + "")) {
						sbl.append(string).append(",");
					}
				}
				// 移除最后一个逗号
				String before_rackIds = sbl.toString();
				if (before_rackIds.length() > 0) {
					before_rackIds = before_rackIds.substring(0, before_rackIds.length() - 1);
				}

				timedTasks.setRackIds(before_rackIds);
				// 修改
				timedTasksService.update(timedTasks);
				log.setContent(gatewayMac + "智能网关删除" + timedTasks.getTaskName() + "定时任务成功");
			}

		} else {
			log.setContent(gatewayMac + "智能网关删除" + timedTasks.getTaskName() + "定时任务失败");
		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 录放精灵反馈 指定节点恢复任务
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @param: projectId 项目id
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 上午11:07:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteAssignNodeRestoreTask(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {

			TaskPauseOrRestoreVo taskPauseOrRestore = (TaskPauseOrRestoreVo) webClientData.get("taskPauseOrRestore");
			// 根据任务md5s值 获取定时任务id
			String taskIds = tasksService.findTaskIdByTaskMd5List(taskPauseOrRestore.getTaskMd5s());
			// 根据Mac和 任务id 状态修改为恢复
			spriteTasksService.updateAllStatusByTaskIdListAndSpirteMacAndStatus(Arrays.asList(taskIds.split(",")),
					spriteMac, 1);
			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 录放精灵反馈 指定节点暂停任务
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @param: projectId 项目id
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 上午11:05:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteAssignNodePauseTask(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) throws Exception {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 进度
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {

			TaskPauseOrRestoreVo taskPauseOrRestore = (TaskPauseOrRestoreVo) webClientData.get("taskPauseOrRestore");
			// 根据任务md5s值 获取定时任务id
			String taskIds = tasksService.findTaskIdByTaskMd5List(taskPauseOrRestore.getTaskMd5s());
			// 根据Mac和 任务id 状态修改为暂停
			spriteTasksService.updateAllStatusByTaskIdListAndSpirteMacAndStatus(Arrays.asList(taskIds.split(",")),
					spriteMac, 0);
			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/***
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 中控 反馈删除定时任务状态
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 下午4:32:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackControlDelelteTask(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) {
		// 状态
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {
			// 任务md5(逗号隔开)
			String taskMd5s = webClientData.getString("taskMd5s");

			// 根据任务md5(集合) 获取 任务详细信息
			List<Tasks> taskList = tasksService.findTaskListByTaskMd5List(Arrays.asList(taskMd5s.split(",")));
			// 任务id(逗号隔开)
			String taskIds = tasksService.findTaskIdByTaskMd5List(Arrays.asList(taskMd5s.split(",")));
			// 根据任务id 批量删除 录放精灵定时任务
			spriteTasksService.batchDeleteByTaskIdList(Arrays.asList(taskIds.split(",")));
			// 根据任务md5 批量删除 定时任务
			tasksService.batchDeleteByTaskMd5List(Arrays.asList(taskMd5s.split(",")));
			// 已删除的定时任务信息
			List<DelTasks> delTaskList = new ArrayList<DelTasks>();
			// 组装数据
			for (Tasks task : taskList) {
				// 初始化
				DelTasks delTasks = new DelTasks(task.getSubtaskIds(), task.getTaskName(), task.getTaskMd5(),
						task.getTaskType(), task.getCyclicMode(), task.getCyclicDate(), task.getProjectId(),
						task.getStatus(), task.getStartDate(), task.getEndDate(), task.getStartTime(),
						task.getEndTime(), task.getCreateTime(), new Date());
				delTaskList.add(delTasks);
			}
			// 批量添加 已删除的定时任务
			delTasksService.batchInsert(delTaskList);

			// Err err = new Err(EnumDeviceType.Sprite.name(), projectId,
			// EnumErrType.DeleteTask.name(), 1, new Date());

			// errService.batchUpdateResoutionStateByDeviceTypeAndErrTypeAndTypeMd5List(err);

			// 添加到JSON中
			webClientData.put("delTaskList", delTaskList);
		} else {

		}
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 该函数的功能描述
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param webClientData web客户传送数据
	 * @param: projectId 项目id
	 * @return: JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 下午4:32:16
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static JSONObject feedbackSpriteDeleteTask(JSONObject socketClientData, JSONObject webClientData,
			Integer projectId) {

		JSONObject jsonData = new JSONObject();
		// Sprite MAC
		String spriteMac = socketClientData.getString("MAC");
		// 机架信息
		RackDevice rackDevice = rackDeviceService.findByAndProjectIdSpriteMac(projectId, spriteMac);
		// 状态
		Integer status = (Integer) socketClientData.get("status");
		if (status.equals(1)) {
			// 获取 已删除的定时任务信息
			@SuppressWarnings("unchecked")
			List<DelTasks> delTaskList = (List<DelTasks>) webClientData.get("delTaskList");
			// 录放精灵 已删除的定时任务信息
			List<DelSpriteTasks> delSpriteTaskList = new ArrayList<DelSpriteTasks>();
			// 组装数据
			for (DelTasks delTasks : delTaskList) {
				// 初始化
				DelSpriteTasks delSpriteTasks = new DelSpriteTasks(delTasks.getDelId(), projectId, spriteMac,
						new Date());
				delSpriteTaskList.add(delSpriteTasks);
			}
			// 批量添加 录放精灵 已删除的定时任务
			delSpriteTasksService.batchInsert(delSpriteTaskList);

			jsonData.put("result", "true");
		} else if (status.equals(0)) {
			jsonData.put("result", "false");
		}
		jsonData.put("rackDevice", rackDevice);
		return jsonData;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 智能网关任务模式删除定时任务策略反馈
	 *
	 * @param: socketClientData socket客户端反馈数据
	 * @param: webClientData web客户传送数据
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年11月13日 上午11:31:05
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月13日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean feedbackIntelligentGatewayTaskModeDeleteTimedTask(JSONObject socketClientData,
			JSONObject webClientData) throws Exception {
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 日志
		Log log = (Log) webClientData.get("log");
		// 网关Mac
		String gatewayMac = socketClientData.getString("mac");
		// 定时任务
		TimedTasks timedTasks = (TimedTasks) webClientData.get("timedTasks");
		if (status.equals("1")) {
			// 智能网关信息
			IntelligentGateway intelligentGateway = intelligentGatewayService.findByMac(gatewayMac);
			// 根据网关mac获取 所有继电器信息
			List<Relay> relayList = relayService.findByGatewayMac(gatewayMac);
			StringBuilder sbld = new StringBuilder();
			for (Relay relay : relayList) {
				sbld.append(relay.getId());
				sbld.append(",");
			}
			String[] arrayRelayId = sbld.toString().substring(0, sbld.length() - 1).split(",");

			timedTasks.setRackId(intelligentGateway.getRackId());
			if (timedTasks.getTaskMode() == 3) {
				timedTasks.setTaskMode(null);
			}
			// 條件查詢 (根据任务模式和项目rackId和rackIds存在的私有数据) 或(项目rackId和rackIds存在的私有数据)
			List<TimedTasks> timedTaskList = timedTasksService.queryListByConditionIf(timedTasks);
			// 删除任务集合(保存任务id)
			List<Integer> deleteList = new ArrayList<Integer>();
			// 修改任务集合(保存定时任务)
			List<TimedTasks> updateList = new ArrayList<TimedTasks>();

			// 遍历删除
			for (TimedTasks timedTasks2 : timedTaskList) {
				// 根据定时任务id，删除定时任务详情信息
				relayTaskDetailsService.deleteByTaskIdAndArrayRelayId(timedTasks2.getTaskId(), arrayRelayId);
				// 机架id(逗号隔开)
				String rackIds = timedTasks2.getRackIds();
				String[] split = rackIds.split(",");
				if (split.length == 1) {
					if (split[0].equals("" + intelligentGateway.getRackId())) {
						// 保存 删除任务id
						deleteList.add(timedTasks2.getTaskId());
						// 删除定时任务
						// timedTasksService.deleteById(timedTasks2.getTaskId());
					}
				} else {
					// 转成 集合 ,遍历 移除指定机柜id
					List<String> list = Arrays.asList(split);
					StringBuilder sbl = new StringBuilder();
					for (String string : list) {
						if (!string.equals(intelligentGateway.getRackId() + "")) {
							sbl.append(string).append(",");
						}
					}
					// 移除最后一个逗号
					String before_rackIds = sbl.toString();
					if (before_rackIds.length() > 0) {
						before_rackIds = before_rackIds.substring(0, before_rackIds.length() - 1);
					}
					timedTasks2.setRackIds(before_rackIds);
					// 添加 修改定时任务对象
					updateList.add(timedTasks2);
					// 修改
					// timedTasksService.update(timedTasks2);
				}
			}
			// 批量删除
			if (deleteList.size() > 0) {
				timedTasksService.batchDelete(deleteList);
			}
			// 批量修改
			if (updateList.size() > 0) {
				timedTasksService.batchUpdateRackIds(updateList);
			}
			log.setContent(gatewayMac + "智能网关"
					+ (timedTasks.getTaskMode() == null ? "所有任务" : timedTasks.getTaskMode() == 1 ? "单任务" : "多任务")
					+ "模式删除定时任务成功");

		} else {
			log.setContent(gatewayMac + "智能网关删除定时任务模式失败");
		}
		OpCodeAnalysisServiceUtils.addLogInfo(log);
		return true;
	}

	/**
	 * 
	 * @Function: OpCodeAnalysisServiceUtils.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月12日 上午11:04:03
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月12日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void feedbackIntelligentGatewayRemoteConfigurationRelay(JSONObject socketClientData,
			JSONObject webClientData) {
		// 状态（0:失败 1:成功）
		String status = socketClientData.getString("status");
		// 模式 (0:修改模式 1:添加模式)
		String mode = webClientData.getString("mode");
		if (status.equals("1")) {
			// 添加 ,把报废的485 下的继电器都删除
			if (mode == "1") {
				// 获取继电器信息
				Relay relay = (Relay) webClientData.get("relay");
				// 根据网关MAC 和 485Ip 删除继电器信息
				relayService.deleteByGatewayMacAndRs485toNetIp(relay.getGatewayMac(), relay.getRs485toNetIp());
			}
		}
	}

}
