/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.thread 
 * @author: syz  
 * @date: 2019年3月19日 上午10:31:36 
 */
package com.lovdmx.control.common.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.utils.SocketUtils;
import com.lovdmx.control.common.websocket.SpringWebSocketHandler;
import com.lovdmx.control.httpVo.AlarmDataVo;
import com.lovdmx.control.httpVo.AlarmDeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmDmx512DeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmGatewayDeviceInfoVo;
import com.lovdmx.control.httpVo.Dmx512SensorAlarmInfoVo;
import com.lovdmx.control.httpVo.GatewayDeviceSensorAlarmInfoVo;
import com.lovdmx.control.httpVo.SolveAlarmData;
import com.lovdmx.control.pojo.Err;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.enums.EnumErrDeviceType;
import com.lovdmx.control.pojo.enums.EnumErrRank;
import com.lovdmx.control.pojo.enums.EnumErrType;
import com.lovdmx.control.vo.WebSocketVo;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProjectAnalysisDeviceThread.java
 * @Description: 定时解析设备（RTR服务器和录放精灵）报警信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月19日 上午10:31:36
 *
 */
public class ProjectAnalysisDeviceThread extends SocketUtils implements Runnable {

	// 启动线程
	private boolean isOK = true;
	// 保存新增,修改报警数据
	private JSONObject jsonData = new JSONObject();

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	/**
	 * @Function: RealTimeDeviceInfoThread.java
	 * @Description: 构造函数
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年2月21日 下午4:11:47
	 */
	public ProjectAnalysisDeviceThread() {
		super();
	}

	/**
	 * 
	 * @Function: ProjectAnalysisDeviceThread.java
	 * @Description: 判断报警是否解决
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午5:05:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void judgeAlarmIsSolved(Integer projectId, JSONObject jsonObject) {
		// 获取 报警已解决的数据
		List<Err> updateErrStatusList = new ArrayList<Err>();
		// 根据报警类型获取离线设备是否已解决 (设备在线表示已解决)
		List<AlarmDeviceInfoVo> offLineAlarmInfoList = errService.findErrTypteOffLineAlarmInfoByProjectId(projectId);

		// 遍历查看已报警的是否解决
		for (AlarmDeviceInfoVo alarmDeviceInfo : offLineAlarmInfoList) {
			// 判断报警类型
			if (alarmDeviceInfo.getRtrOfflineAlarmStatus() == 1) {
				// 如果报警 查看设置是否在线（1表示在线），在线则修改报警状态（标识为已解决）
				if (alarmDeviceInfo.getRtrIsOnline() == 1) {
					Err err = new Err(alarmDeviceInfo.getErrId(), 1, new Date(), alarmDeviceInfo.getRackName());
					updateErrStatusList.add(err);
				}
			} else if (alarmDeviceInfo.getSpriteOfflineAlarmStatus() == 1) {
				// 如果报警 查看设置是否在线（1表示在线），在线则修改报警状态（标识为已解决）
				if (alarmDeviceInfo.getSpriteIsOnline() == 1) {
					Err err = new Err(alarmDeviceInfo.getErrId(), 1, new Date(), alarmDeviceInfo.getRackName());
					updateErrStatusList.add(err);
				}
			} else if (alarmDeviceInfo.getDmxOfflineAlarmStatus() == 1) {
				// 如果报警 查看设置是否在线（1表示在线），在线则修改报警状态（标识为已解决）
				if (alarmDeviceInfo.getDmxNowAlarmNumber() == 0) {
					Err err = new Err(alarmDeviceInfo.getErrId(), 1, new Date(), alarmDeviceInfo.getRackName());
					updateErrStatusList.add(err);
				}
			}
		}
		// 根据设备类型为DMX 查看传感器值(温度,湿度,烟雾)是否已解决 (值在规定范围标识已解决)
		List<AlarmDmx512DeviceInfoVo> alarmDmx512DeviceInfoList = errService
				.findErrTypeDmx512RdmtsAlarmInfoByProjectId(projectId);

		for (AlarmDmx512DeviceInfoVo alarmDmx512DeviceInfo : alarmDmx512DeviceInfoList) {
			String errType = alarmDmx512DeviceInfo.getErrType();
			// 溫度
			if (errType.equals(EnumErrType.Temperature.name())) {
				// 溫度范围
				String[] split = alarmDmx512DeviceInfo.getTemperatureRange().split(",");
				if (split.length == 2) {
					// 在范围 代表报警已解除
					if (alarmDmx512DeviceInfo.getTemperature() > Integer.parseInt(split[0])
							&& alarmDmx512DeviceInfo.getTemperature() < Integer.parseInt(split[1])) {
						Err err = new Err(alarmDmx512DeviceInfo.getErrId(), 1, new Date(),
								alarmDmx512DeviceInfo.getRackName());
						updateErrStatusList.add(err);
					}
				}
				// 烟雾
			} else {
				// 0:标识报警 1:未报警
				if (alarmDmx512DeviceInfo.getSmoke() == 0) {
					Err err = new Err(alarmDmx512DeviceInfo.getErrId(), 1, new Date(),
							alarmDmx512DeviceInfo.getRackName());
					updateErrStatusList.add(err);
				}
			}
		}

		// 查看传感器值(温度,烟雾)是否已解决 (值在规定范围标识已解决)
		List<AlarmGatewayDeviceInfoVo> alarmGatewayDeviceInfoList = errService
				.findErrTypeGatewayDeviceAlarmInfoByProjectId(projectId);
		for (AlarmGatewayDeviceInfoVo alarmGatewayDeviceInfo : alarmGatewayDeviceInfoList) {
			String errType = alarmGatewayDeviceInfo.getErrType();
			// 溫度
			if (errType.equals(EnumErrType.Temperature.name())) {
				// 溫度范围
				String[] split = alarmGatewayDeviceInfo.getTemperatureRange().split(",");
				if (split.length == 2) {
					// 在范围 代表报警已解除
					if (alarmGatewayDeviceInfo.getTemp() > Integer.parseInt(split[0])
							&& alarmGatewayDeviceInfo.getTemp() < Integer.parseInt(split[1])) {
						Err err = new Err(alarmGatewayDeviceInfo.getErrId(), 1, new Date(),
								alarmGatewayDeviceInfo.getRackName());
						updateErrStatusList.add(err);
					}
				}
				// 烟雾
			} else {
				// 0:标识报警 1:未报警
				if (alarmGatewayDeviceInfo.getFire() == 0) {
					Err err = new Err(alarmGatewayDeviceInfo.getErrId(), 1, new Date(),
							alarmGatewayDeviceInfo.getRackName());
					updateErrStatusList.add(err);
				}
			}
		}

		/*
		 * 根据项目ID，获取报警类型为(Video,Lmx,Task)的数据详情（注:判断(Video,Lmx,Task) 已发布的数量和反馈是否相等）
		 */
		List<SolveAlarmData> solveAlarmDataList = errService.findErrTypteFileAlarmInfoByProjectId(projectId);
		if (solveAlarmDataList != null && solveAlarmDataList.size() > 0) {
			for (SolveAlarmData solveAlarmData : solveAlarmDataList) {
				// 先判断是那个报警,然后 判断如果数量一致,报警标识为已解决
				if (solveAlarmData.getEdlmxOfflineAlarmStatus() == 1) {
					if (solveAlarmData.getEdlmxCount() > 0) {
						Err err = new Err(solveAlarmData.getErrId(), 1, new Date(), solveAlarmData.getRackName());
						updateErrStatusList.add(err);
					}
				} else if (solveAlarmData.getTaskOfflineAlarmStatus() == 1) {
					if (solveAlarmData.getTaskCount() > 0) {
						Err err = new Err(solveAlarmData.getErrId(), 1, new Date(), solveAlarmData.getRackName());
						updateErrStatusList.add(err);
					}
				} else if (solveAlarmData.getVideoOfflineAlarmStatus() == 1) {
					if (solveAlarmData.getVideoCount() > 0) {
						Err err = new Err(solveAlarmData.getErrId(), 1, new Date(), solveAlarmData.getRackName());
						updateErrStatusList.add(err);
					}
				} else if (solveAlarmData.getDeleteTaskOfflineAlarmStatus() == 1) {
					if (solveAlarmData.getDeleteTaskCount() > 0) {
						Err err = new Err(solveAlarmData.getErrId(), 1, new Date(), solveAlarmData.getRackName());
						updateErrStatusList.add(err);
					}
				}
			}
		}
		// 批量修改报警 已解决的状态
		if (updateErrStatusList.size() > 0) {
			errService.batchUpdateResoutionStateByErrIdList(updateErrStatusList);
			jsonObject.put("solveDalarmDataList", updateErrStatusList);
		}
	}

	/**
	 * 
	 * @Function: ProjectAnalysisDeviceThread.java
	 * @Description: 添加或修改报警数据
	 *
	 * @param: projectId 项目ID
	 * @param: projectDeviceInfo 设备信息
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午5:09:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void addOrUpdateAlarmData(Integer projectId, JSONObject jsonObject) {
		// 获取是否有新的报警信息
		List<AlarmDeviceInfoVo> alarmDeviceList = projectService.findProjectRackDeviceAlarmInfo(projectId);
		// 添加报警信息
		List<Err> addErrList = new ArrayList<Err>();
		// 修改报警信息
		List<Err> updateErrList = new ArrayList<Err>();
		// 用于 sprite 过滤重复数据
		int rackId_temp = -1;
		// 遍历 添加或修改报警信息
		// 报警数据中不存在的,就新添加一条报警信息
		for (AlarmDeviceInfoVo alarmDeviceInfo : alarmDeviceList) {
			if (alarmDeviceInfo.getRtrMac() != null && !alarmDeviceInfo.getRtrMac().equals("")) {
				if (alarmDeviceInfo.getRtrIsOnline() == 0) {
					// 判断报警信息是否存在
					if (alarmDeviceInfo.getRtrOfflineAlarmStatus() == 0) {
						// 组装构造函数
						Err err = new Err(alarmDeviceInfo.getRtrMac(), alarmDeviceInfo.getRtrIp(),
								EnumErrDeviceType.RTR.name(), projectId, alarmDeviceInfo.getRackId(),
								EnumErrType.OffLine.name(), EnumErrRank.General.name(), "", 0, 1, "RTR掉线", new Date(),
								new Date(), alarmDeviceInfo.getRackName());
						// 添加 报警信息
						addErrList.add(err);
					}
				}
				// DMX512 报警(判断是否有未在线的)
				if (alarmDeviceInfo.getDmxNowAlarmNumber() > 0) {
					if (alarmDeviceInfo.getDmxOfflineAlarmStatus() == 0) {
						Err err = new Err(alarmDeviceInfo.getRtrMac(), alarmDeviceInfo.getRtrIp(),
								EnumErrDeviceType.DMX512.name(), projectId, alarmDeviceInfo.getRackId(),
								EnumErrType.OffLine.name(), EnumErrRank.General.name(), "", 0,
								alarmDeviceInfo.getDmxNowAlarmNumber(),
								"控制盒掉线" + alarmDeviceInfo.getDmxNowAlarmNumber() + "个", new Date(), new Date(),
								alarmDeviceInfo.getRackName());
						addErrList.add(err);
					} else {
						// 判断之前报警数量和现在报警数量不同,则修改数据库
						if (alarmDeviceInfo.getDmxBeforeAlarmNumber() != alarmDeviceInfo.getDmxNowAlarmNumber()) {
							// 组装构造函数
							Err err = new Err(alarmDeviceInfo.getErrId(),
									"控制盒掉线" + alarmDeviceInfo.getDmxNowAlarmNumber() + "个",
									alarmDeviceInfo.getDmxNowAlarmNumber(), alarmDeviceInfo.getRackName());
							// 添加 需要修改的报警信息
							updateErrList.add(err);
						}
					}
				}
			}
			// 录放精灵 始终只有一个 ，所有出现两次 过滤
			if (rackId_temp != alarmDeviceInfo.getRackId()) {
				// 赋值 当前rackId
				rackId_temp = alarmDeviceInfo.getRackId();
				// 报警(判断是否有未在线的)
				if (alarmDeviceInfo.getSpriteMac() != null && !alarmDeviceInfo.getSpriteMac().equals("")) {
					if (alarmDeviceInfo.getSpriteIsOnline() == 0) {
						if (alarmDeviceInfo.getSpriteOfflineAlarmStatus() == 0) {
							Err err = new Err(alarmDeviceInfo.getSpriteMac(), alarmDeviceInfo.getSpriteIp(),
									EnumErrDeviceType.Sprite.name(), projectId, alarmDeviceInfo.getRackId(),
									EnumErrType.OffLine.name(), EnumErrRank.General.name(), "", 0, 1, "Sprite掉线",
									new Date(), new Date(), alarmDeviceInfo.getRackName());
							addErrList.add(err);
						}
					}
				}
			}
		}
		// 获取 dmx512温度报警信息
		List<Dmx512SensorAlarmInfoVo> dmx512TemperatureAlarmList = projectService
				.findDMX512NotExistTemperatureAlarmByProjectId(projectId);
		// 获取 dmx512烟雾报警信息
		List<Dmx512SensorAlarmInfoVo> dmx512SmokeAlarmList = projectService
				.findDMX512NotExistSmokeAlarmByProjectId(projectId);

		// 遍历DMX512温度 报警
		for (Dmx512SensorAlarmInfoVo dmx512TemperatureAlarmInfo : dmx512TemperatureAlarmList) {
			// 截取获取温度范围
			String[] split = dmx512TemperatureAlarmInfo.getTemperatureRange().split(",");
			if (split.length == 2) {
				Err err = null;
				// 判断是高温还是低温报警
				if (dmx512TemperatureAlarmInfo.getTemperature() < Integer.parseInt(split[0])) {
					err = new Err(dmx512TemperatureAlarmInfo.getDmx512Mac(), dmx512TemperatureAlarmInfo.getDmx512Ip(),
							EnumErrDeviceType.DMX512.name(), projectId, dmx512TemperatureAlarmInfo.getRackId(),
							EnumErrType.Temperature.name(), EnumErrRank.Grave.name(), "", 0, 1, "低温报警", new Date(),
							new Date(), dmx512TemperatureAlarmInfo.getRackName());
				} else {
					err = new Err(dmx512TemperatureAlarmInfo.getDmx512Mac(), dmx512TemperatureAlarmInfo.getDmx512Ip(),
							EnumErrDeviceType.DMX512.name(), projectId, dmx512TemperatureAlarmInfo.getRackId(),
							EnumErrType.Temperature.name(), EnumErrRank.Grave.name(), "", 0, 1, "高温报警", new Date(),
							new Date(), dmx512TemperatureAlarmInfo.getRackName());
				}
				addErrList.add(err);
			}
		}

		// 遍历DMX512烟雾 报警
		for (Dmx512SensorAlarmInfoVo dmx512TemperatureAlarmInfo : dmx512SmokeAlarmList) {
			Err err = new Err(dmx512TemperatureAlarmInfo.getDmx512Mac(), dmx512TemperatureAlarmInfo.getDmx512Ip(),
					EnumErrDeviceType.DMX512.name(), projectId, dmx512TemperatureAlarmInfo.getRackId(),
					EnumErrType.Smoke.name(), EnumErrRank.Grave.name(), "", 0, 1, "烟雾报警", new Date(), new Date(),
					dmx512TemperatureAlarmInfo.getRackName());
			addErrList.add(err);
		}

		/**
		 * 网关设备报警
		 */
		// 获取 网关设备(传感器)温度报警信息
		List<GatewayDeviceSensorAlarmInfoVo> gatewayDeviceTemperatureAlarmList = projectService
				.findGatewayDeviceNotExistTemperatureAlarmByProjectId(projectId);
		// 获取 网关设备(传感器)烟雾报警信息
		List<GatewayDeviceSensorAlarmInfoVo> gatewayDeviceSmokeAlarmList = projectService
				.findGatewayDeviceNotExistSmokeAlarmByProjectId(projectId);

		// 遍历 网关设备(传感器)温度报警
		for (GatewayDeviceSensorAlarmInfoVo gatewayDeviceSensorAlarmInfo : gatewayDeviceTemperatureAlarmList) {
			// 截取获取温度范围
			String[] split = gatewayDeviceSensorAlarmInfo.getTemperatureRange().split(",");
			if (split.length == 2) {
				Err err = null;
				// 判断是高温还是低温报警
				if (gatewayDeviceSensorAlarmInfo.getTemp() < Integer.parseInt(split[0])) {
					err = new Err(gatewayDeviceSensorAlarmInfo.getDeviceMac(), gatewayDeviceSensorAlarmInfo.getIp(),
							EnumErrDeviceType.Gateway.name(), projectId, gatewayDeviceSensorAlarmInfo.getRackId(),
							EnumErrType.Temperature.name(), EnumErrRank.Grave.name(), "", 0, 1, "低温报警", new Date(),
							new Date(), gatewayDeviceSensorAlarmInfo.getRackName());
				} else {
					err = new Err(gatewayDeviceSensorAlarmInfo.getDeviceMac(), gatewayDeviceSensorAlarmInfo.getIp(),
							EnumErrDeviceType.Gateway.name(), projectId, gatewayDeviceSensorAlarmInfo.getRackId(),
							EnumErrType.Temperature.name(), EnumErrRank.Grave.name(), "", 0, 1, "高温报警", new Date(),
							new Date(), gatewayDeviceSensorAlarmInfo.getRackName());
				}
				addErrList.add(err);
			}
		}

		// 遍历网关设备(传感器)烟雾 报警
		for (GatewayDeviceSensorAlarmInfoVo gatewayDeviceSensorAlarm : gatewayDeviceSmokeAlarmList) {
			Err err = new Err(gatewayDeviceSensorAlarm.getDeviceMac(), gatewayDeviceSensorAlarm.getIp(),
					EnumErrDeviceType.Gateway.name(), projectId, gatewayDeviceSensorAlarm.getRackId(),
					EnumErrType.Smoke.name(), EnumErrRank.Grave.name(), "", 0, 1, "烟雾报警", new Date(), new Date(),
					gatewayDeviceSensorAlarm.getRackName());
			addErrList.add(err);
		}

		// RTR丢失的视频文件信息
		List<AlarmDataVo> rtrVideoAlarmMessageList = projectService.findRTRNotExistVideoByParentId(projectId);
		// Sprite丢失的灯光文件信息
		List<AlarmDataVo> spriteLmxAlarmMessage = projectService.findSpriteNotExistLmxByParentId(projectId);
		// Sprite丢失的任务信息
		List<AlarmDataVo> spriteTaskAlarmMessage = projectService.findSpriteNotExistTasksByParentId(projectId);
		// Sprite丢失的删除任务信息
		List<AlarmDataVo> spriteDeleteTaskAlarmMessage = projectService
				.findSpriteNotExistDeleteTasksByParentId(projectId);

		// 遍历RTR视频文件 报警
		for (AlarmDataVo alarmMessage : rtrVideoAlarmMessageList) {
			if (alarmMessage.getOfflineAlarmStatus() == 0) {
				// 文件名
				String fileName = alarmMessage.getFilePath().substring(
						(alarmMessage.getFilePath().lastIndexOf("/") + 1), alarmMessage.getFilePath().length());

				Err err = new Err(alarmMessage.getMac(), alarmMessage.getIp(), EnumErrDeviceType.RTR.name(), projectId,
						alarmMessage.getRackId(), EnumErrType.Video.name(), EnumErrRank.Grave.name(),
						alarmMessage.getFileMd5(), 0, 1, "RTR丢失" + fileName + "视频文件", new Date(), new Date(),
						alarmMessage.getRackName());
				addErrList.add(err);
			}
		}
		// 遍历Sprite灯光文件 报警
		for (AlarmDataVo alarmMessage : spriteLmxAlarmMessage) {
			if (alarmMessage.getOfflineAlarmStatus() == 0) {
				// 文件名
				String fileName = alarmMessage.getFilePath().substring(
						(alarmMessage.getFilePath().lastIndexOf("/") + 1), alarmMessage.getFilePath().length());

				Err err = new Err(alarmMessage.getMac(), alarmMessage.getIp(), EnumErrDeviceType.Sprite.name(),
						projectId, alarmMessage.getRackId(), EnumErrType.Lmx.name(), EnumErrRank.Grave.name(),
						alarmMessage.getFileMd5(), 0, 1, "Sprite丢失" + fileName + "灯光文件", new Date(), new Date(),
						alarmMessage.getRackName());
				addErrList.add(err);
			}
		}
		// 遍历Sprite任务信息 报警
		for (AlarmDataVo alarmMessage : spriteTaskAlarmMessage) {
			if (alarmMessage.getOfflineAlarmStatus() == 0) {
				Err err = new Err(alarmMessage.getMac(), alarmMessage.getIp(), EnumErrDeviceType.Sprite.name(),
						projectId, alarmMessage.getRackId(), EnumErrType.Task.name(), EnumErrRank.Grave.name(),
						alarmMessage.getFileMd5(), 0, 1, "Sprite丢失任务(" + alarmMessage.getTaskName() + ")", new Date(),
						new Date(), alarmMessage.getRackName());
				err.setTaskName(alarmMessage.getTaskName());
				addErrList.add(err);
			}
		}
		// 遍历Sprite删除任务信息 报警
		for (AlarmDataVo alarmMessage : spriteDeleteTaskAlarmMessage) {
			if (alarmMessage.getOfflineAlarmStatus() == 0) {
				Err err = new Err(alarmMessage.getMac(), alarmMessage.getIp(), EnumErrDeviceType.Sprite.name(),
						projectId, alarmMessage.getRackId(), EnumErrType.DeleteTask.name(), EnumErrRank.Grave.name(),
						alarmMessage.getFileMd5(), 0, 1, "Sprite丢失删除任务(" + alarmMessage.getTaskName() + ")", new Date(),
						new Date(), alarmMessage.getRackName());
				err.setTaskName(alarmMessage.getTaskName());
				addErrList.add(err);
			}
		}

		// 有数据则批量添加报警信息
		if (addErrList.size() > 0) {
			errService.batchInsert(addErrList);
			jsonObject.put("addAlarmDataList", addErrList);
		}
		// 有数据则批量修改报警信息
		if (updateErrList.size() > 0) {
			errService.batchUpdate(updateErrList);
			jsonObject.put("updateAlarmDataList", updateErrList);
		}
	}

	@Override
	public void run() {
		// 标识
		jsonData.put("opCode", 2222);
		while (isOK) {
			// 获取所有项目
			List<Project> projectList = projectService.findAll();
			// 遍历 项目列表
			for (Project project : projectList) {
				// 修改报警数据
				JSONObject alarmDataObject = new JSONObject();
				// 判断报警是否解决
				judgeAlarmIsSolved(project.getProjectId(), alarmDataObject);
				// 添加或修改 报警信息
				addOrUpdateAlarmData(project.getProjectId(), alarmDataObject);
				// 获取最新的机架设备（RTR，Sprite,Dmx512）信息
				Project projectDeviceInfo = projectService.findProjectRackDeviceInfo(project.getProjectId());

				// 获取最新的报警信息
				List<Err> errList = errService.findByProjectId(project.getProjectId());
				jsonData.put("deviceDataJson", projectDeviceInfo);
				jsonData.put("alarmDataJson", errList);
				// 备份最新的数据
				Map<String, WebSocketVo> dataMap = SpringWebSocketHandler.dataMap;
				WebSocketVo webSocketVo = dataMap.get("" + project.getProjectId());
				if (webSocketVo == null) {
					webSocketVo = new WebSocketVo();
					webSocketVo.setDataJson(jsonData.toJSONString());
					webSocketVo.setUsers(new CopyOnWriteArraySet<WebSocketSession>());
					dataMap.put("" + project.getProjectId(), webSocketVo);
				} else {
					webSocketVo.setDataJson(jsonData.toJSONString());
				}
				// 发送 修改的报警数据
				if (alarmDataObject.containsKey("solveDalarmDataList")
						|| alarmDataObject.containsKey("addAlarmDataList")
						|| alarmDataObject.containsKey("updateAlarmDataList")) {
					alarmDataObject.put("opCode", 1111);
					// System.err.println(alarmDataObject.toJSONString());
					SpringWebSocketHandler.sendMessageToUsers(project.getProjectId(),
							new TextMessage(alarmDataObject.toJSONString()));
				}
			}
			// 休眠10秒
			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
