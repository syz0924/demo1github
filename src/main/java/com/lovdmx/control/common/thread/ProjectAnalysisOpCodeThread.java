package com.lovdmx.control.common.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.socket.ServerSocketThread;
import com.lovdmx.control.common.utils.OpCodeAnalysisServiceUtils;
import com.lovdmx.control.common.utils.SocketUtils;
import com.lovdmx.control.common.websocket.SpringWebSocketHandler;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProjectAnalysisOpCodeThread.java
 * @Description: 根据OpCode解析业务
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月19日 上午10:32:52
 *
 */
public class ProjectAnalysisOpCodeThread extends SocketUtils implements Runnable {

	// 启动线程
	private boolean isOK = true;
	// web客户端 传入数据
	private static Map<String, JSONObject> webClientDataMap = Collections
			.synchronizedMap(new HashMap<String, JSONObject>());
	// 录放精灵客户端 然后数据
	private static List<JSONObject> socketClientDataList = Collections.synchronizedList(new ArrayList<JSONObject>());

	@Override
	public void run() {
		List<JSONObject> dataList = Collections.synchronizedList(new ArrayList<JSONObject>());
		while (isOK) {
			synchronized (this) {
				dataList = socketClientDataList;
			}
			if (dataList.size() > 0) {
				// 客户端反馈JSON
				JSONObject socketClientData = dataList.get(0);
				// opCode
				Integer opCode = (Integer) socketClientData.get("opCode");

				String uuid = null;

				// 关闭事务自动提交
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				TransactionStatus status = platformTransactionManager.getTransaction(def);
				try {
					if (opCode == 0x2022) {
						// 组装 文件上传状态
						String dataJson = OpCodeAnalysisServiceUtils.getAssembleDeviceInfo(socketClientData);
						socketClientData.put("dataJson", dataJson);
						// 赋值最新的文件信息
						ServerSocketThread.setDirectoryListData(dataJson);
						continue;
					} else {

						if (socketClientData.containsKey("uuid")) {
							// 获取key 标识 UUID
							uuid = (String) socketClientData.get("uuid");
						} else if (socketClientData.containsKey("OrgFileMd5")) {
							// 获取key 标识 UUID
							uuid = (String) socketClientData.get("OrgFileMd5");
						}

						// 根据uuid获取 客户端传入的数据
						JSONObject webClientData = webClientDataMap.get(uuid);
						// 项目id
						Integer projectId = webClientData.getInteger("projectId");
						switch (opCode) {
						case 0x2026:
							// 上传灯光或视频文件反馈
							OpCodeAnalysisServiceUtils.addUploadVideoOrLmxInfo(socketClientData, webClientData,
									projectId);
							break;
						case 0x1015:
							// 视频切割策略的反馈
							OpCodeAnalysisServiceUtils.updateUpdateVideoSplitStatus(socketClientData, webClientData,
									uuid, webClientDataMap);
							break;
						case 0x1016:
							// 灯光文件切割策略的反馈
							OpCodeAnalysisServiceUtils.updateUpdateLightingSplitStatus(socketClientData, webClientData,
									uuid, webClientDataMap);
							break;
						case 0x1008:
							// 中控定时任务反馈
							OpCodeAnalysisServiceUtils.feedbackControlAddTask(socketClientData, webClientData, uuid,
									webClientDataMap);
							break;
						case 0x1009:
							// 中控暂停任务策略反饋
							OpCodeAnalysisServiceUtils.feedbackControlPauseTaskInfo(socketClientData, webClientData);
							break;
						case 0x1010:
							// 中控恢复任务策略反饋
							OpCodeAnalysisServiceUtils.feedbackControlRestoreTaskInfo(socketClientData, webClientData);
							break;
						case 0x1012:
							// 录放精灵定时任务任务反馈
							OpCodeAnalysisServiceUtils.feedbackSpriteTaskInfo(socketClientData, webClientData,
									projectId);
							break;
						case 0x1013:
							// 录放精灵暂停任务反馈
							OpCodeAnalysisServiceUtils.feedbackSpritePauseTaskInfo(socketClientData, webClientData,
									projectId);
							break;
						case 0x1014:
							// 录放精灵恢复任务反馈
							OpCodeAnalysisServiceUtils.feedbackSpriteRestoreTaskInfo(socketClientData, webClientData,
									projectId);
							break;
						case 0x1006:
							// 反馈添加RTR 视频信息
							OpCodeAnalysisServiceUtils.feedbackRtrVideoInfo(socketClientData, webClientData, projectId);
							break;
						case 0x1007:
							// 反馈添加Sprite 灯光信息
							OpCodeAnalysisServiceUtils.feedbackSpriteLmxInfo(socketClientData, webClientData,
									projectId);
							break;
						case 0x1026:
							// 智能网关实时控制策略反馈
							OpCodeAnalysisServiceUtils.feedbackIntelligentGatewayAddRealTimeTask(socketClientData,
									webClientData);
							break;
						case 0x1020:
							// 中控对智能网关定时任务策略反馈
							OpCodeAnalysisServiceUtils.feedbackControlGatewayAddTimedTask(socketClientData,
									webClientData, uuid, webClientDataMap, projectId);
							break;
						case 0x1027:
							// 智能网关定时任务策略反馈
							OpCodeAnalysisServiceUtils.feedbackIntelligentGatewayAddTimedTask(socketClientData,
									webClientData);
							break;
						case 0x1028:
							// 智能网关删除定时任务策略反馈
							OpCodeAnalysisServiceUtils.feedbackIntelligentGatewayDeleteTimedTask(socketClientData,
									webClientData);
							break;
						case 0x1030:
							// 智能网关任务模式删除定时任务策略反馈
							OpCodeAnalysisServiceUtils
									.feedbackIntelligentGatewayTaskModeDeleteTimedTask(socketClientData, webClientData);
							break;
						case 0x1036:
							// 智能网关远程配置继电器策略反馈
							OpCodeAnalysisServiceUtils.feedbackIntelligentGatewayRemoteConfigurationRelay(
									socketClientData, webClientData);
							break;
						}
						// 广播发送
						SpringWebSocketHandler.sendMessageToUsers(projectId,
								new TextMessage(socketClientData.toJSONString()));
					}

				} catch (Exception ex) {
					// 回滚
					platformTransactionManager.rollback(status);
					ex.printStackTrace();
					logger.error(ex.getMessage());

				} finally {
					// 移除
					socketClientDataList.remove(0);
					// 过滤
					if (!filtrationMap.containsKey(opCode)) {
						webClientDataMap.remove(uuid);
					}

				}
				// 提交
				platformTransactionManager.commit(status);
			} else {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		webClientDataMap.clear();
		socketClientDataList.clear();
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public static void addJSONObject(JSONObject jsonObject) {
		socketClientDataList.add(jsonObject);
	}

	public static void addMapInfo(String uuid, JSONObject jsonObject) {
		webClientDataMap.put(uuid, jsonObject);
	}

	public JSONObject getMapInfo(String uuid) {
		return webClientDataMap.get("uuid");
	}

	public ProjectAnalysisOpCodeThread() {
	}

}
