package com.lovdmx.control.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;
import com.lovdmx.control.common.utils.ApplicationFileUtils;
import com.lovdmx.control.common.utils.AssembleDataUtils;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.common.utils.UploadImageUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.Relay;
import com.lovdmx.control.service.IntelligentGatewayService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RelayService;
import com.lovdmx.control.vo.SocketClientInfoVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayController.java
 * @Description: 继电器控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月6日 下午4:31:04
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/relay/")
public class RelayController extends BaseController {

	@Autowired
	private RelayService relayService; // 继电器
	@Autowired
	private IntelligentGatewayService intelligentGatewayService; // 智能网关
	@Autowired
	private RackDeviceService rackDeviceService; // 机柜
	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 展示继电器列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月23日 下午3:23:29
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月23日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Relay relay, Integer pageNo, ModelMap map) {

		// 获取所有项目列表
		List<Project> projectList = projectService.findAll();

		// 模糊查询 分页
		PageInfo<Relay> pageInfo = relayService.queryPageListByWhereRelay(relay, pageNo, rows);
		for (Relay relayInfo : pageInfo.getList()) {
			// 有序排序在线和不在线信息
			String[] split = relayInfo.getElectricRelayLoopOFF().split(",");
			relayInfo.setOnlineStateList(
					AssembleDataUtils.getAssembleDataIsOnlineStateList(relayInfo.getNumber(), split));
		}
		if (relay.getProjectId() != null) {
			// 获取所有机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findAll();
			map.put("rackDeviceList", rackDeviceList);
			if (relay.getRackId() != null) {
				// 智能网关信息
				List<IntelligentGateway> intelligentGatewayList = intelligentGatewayService
						.findByRackId(relay.getRackId());
				map.put("intelligentGatewayList", intelligentGatewayList);
			}
		}
		map.put("projectList", projectList);
		map.put("relay", relay);
		map.put("pageInfo", pageInfo);
		map.put("controlOnlineStatus", OnLineClientUtils.getControlClient() == null ? false : true);
		return "intelligentGateway/relay/relay_list";
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 继电器程序列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月10日 上午9:50:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "applicationIndex.do", method = RequestMethod.GET)
	public String applicationIndex(ModelMap map, HttpSession session) {
		// 项目路径
		String basePath = session.getServletContext().getRealPath("/");
		// 文件路径
		String path = "upload" + File.separator + "relay" + File.separator + "application";
		// 程序版本号
		String[] strArr = ApplicationFileUtils
				.getRelayApplicationFile(basePath + File.separator + path + File.separator + "version.txt");
		// 是否存在
		boolean isExist = false;
		if (strArr != null) {
			isExist = true;
			map.put("applicationVersion", strArr[0].split("\n")[0]);
			map.put("applicationName", strArr[1].split("\n")[0]);
		}
		map.put("application", isExist);

		return "application/intelligentGateway/relay_application";
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 编辑继电器信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月9日 上午9:47:24
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月9日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject edit(Integer id) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 获取继电器信息
		Relay relay = relayService.findById(id);
		resultJson.put("relay", relay);

		return resultJson;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 删除继电器
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月12日 下午3:40:05
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月12日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Integer id) {
		// 删除继电器
		Integer deleteId = relayService.deleteById(id);

		return deleteId == 0 ? "删除失败" : "删除成功";
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 根据网关MAC 获取rs485toNetIp 列表
	 *
	 * @param:描述1描述
	 * @return：List<Relay>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月11日 下午5:19:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月11日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "queryRs485ToNetIp.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Relay> queryRs485ToNetIp(String gatewayMac) {
		// 根据网关MAC 分组 rs485toNetIp
		List<Relay> relayList = relayService.findGrouprs485toNetIpBygatewayMac(gatewayMac);
		return relayList;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 编辑继电器程序
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月10日 下午1:50:05
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "editApplication.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject editApplication(HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();

		// 路徑
		String basePath = session.getServletContext().getRealPath("/");
		// 文件路径
		String path = "upload" + File.separator + "relay" + File.separator + "application";
		String[] strArr = ApplicationFileUtils
				.getRelayApplicationFile(basePath + File.separator + path + File.separator + "version.txt");
		resultJson.put("applicationVersion", strArr[0].split("\n")[0]);
		resultJson.put("applicationPath", strArr[1].split("\n")[0]);

		return resultJson;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 添加程序
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月10日 上午11:24:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "saveApplication", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveApplication(@RequestParam(value = "file") MultipartFile file,
			@RequestParam String applicationVersion, HttpSession session) {
		// 反馈
		boolean bool = false;
		// 路徑
		String basePath = session.getServletContext().getRealPath("/");
		// 文件路径
		String path = "upload" + File.separator + "relay" + File.separator + "application";
		// 程序名称
		String applicationName = file.getOriginalFilename();
		try {
			// 更新程序
			UploadImageUtils.uploadImage(file, path, basePath, applicationName);
			// 添加文件
			ApplicationFileUtils
					.isExitsRelayMkdirFile(new File(basePath + File.separator + path + File.separator + "version.txt"));
			// 更新程序文件
			ApplicationFileUtils.setRelayApplicationFile(
					basePath + File.separator + path + File.separator + "version.txt", applicationVersion,
					applicationName);
			bool = true;
		} catch (Exception ex) {
			bool = false;
			ex.printStackTrace();
			// 移除文件
			UploadImageUtils.deteleImage(basePath + File.separator + path, applicationName);
		}
		return bool;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 更新继电器程序
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月9日 下午5:52:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月9日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "updateApplication.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateApplication(@RequestParam(value = "file") MultipartFile file, String applicationVersion,
			String version, HttpSession session) {
		// 反馈
		boolean bool = false;
		// 路徑
		String basePath = session.getServletContext().getRealPath("/");
		// 绝对路径
		String path = "upload" + File.separator + "relay" + File.separator + "application";
		// 程序名称
		String applicationName = file.getOriginalFilename();
		try {
			// 获取配置文件
			String[] strArr = ApplicationFileUtils
					.getRelayApplicationFile(basePath + File.separator + path + File.separator + "version.txt");

			// 更新程序
			UploadImageUtils.uploadImage(file, path, basePath, applicationName);
			// 更新程序文件
			ApplicationFileUtils.setRelayApplicationFile(
					basePath + File.separator + path + File.separator + "version.txt", applicationVersion,
					applicationName);
			String[] split = strArr[1].split("\n");
			if (!split[0].equals(applicationName)) {
				UploadImageUtils.deteleImage(basePath + File.separator + path, split[0]);
			}
			bool = true;
		} catch (Exception ex) {
			bool = false;
			ex.printStackTrace();
			// 移除文件
			UploadImageUtils.deteleImage(basePath + File.separator + path, applicationName);
		}
		return bool;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 根据网关Mac 获取继电器信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月10日 下午2:51:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月10日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRelayInfoByGatewayMac.do", method = RequestMethod.GET)
	public List<Relay> getRelayInfoByGatewayMac(String gatewayMac) {
		// 根据网关Mac 获取继电器信息
		List<Relay> relayList = relayService.findByGatewayMac(gatewayMac);
		return relayList;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 远程添加485Ip继电器
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月12日 上午10:54:17
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月12日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "sendRemoteConfigurationAddRelay.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject sendRemoteConfigurationAddRelay(Relay relay) {
		// 反馈信息
		JSONObject resultJson = new JSONObject();
		// 状态
		String result = null;

		try {
			// 获取主录放精灵客户端信息
			SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
			// 判断中控是否在线
			if (controlSocketClient != null) {
				// 继电器列表
				List<Relay> relayList = relayService.findByGatewayMacAndRs485toNetIp(relay.getGatewayMac(),
						relay.getRs485toNetIp());

				// 网关信息
				IntelligentGateway intelligentGateway = intelligentGatewayService.findByMac(relay.getGatewayMac());
				// 反馈反馈
				JSONObject sendJson = new JSONObject();
				// 获取UUID
				String uuid = UUID.randomUUID().toString();
				sendJson.put("uuid", uuid);
				sendJson.put("rackId", "" + intelligentGateway.getRackId());
				// 內容
				JSONObject msgJson = new JSONObject();
				msgJson.put("uuid", uuid);
				msgJson.put("gatewaMac", relay.getGatewayMac());
				msgJson.put("rs485toNetIp", relay.getNewRs485toNetIp());
				// 0:修改 1:添加
				msgJson.put("mode", "1");
				// 继电器信息
				List<JSONObject> relayJsonList = new ArrayList<JSONObject>();
				// 遍历继电器列表
				for (Relay rel : relayList) {
					JSONObject relayJson = new JSONObject();
					relayJson.put("number", rel.getNumber());
					relayJson.put("slaveAddr", rel.getSlaveAddr());
					relayJsonList.add(relayJson);
				}
				msgJson.put("relayList", relayJsonList);
				sendJson.put("taskMsg", msgJson);
				// 组装web数据（用于后续操作）
				JSONObject webJsonData = new JSONObject();
				webJsonData.put("projectId", intelligentGateway.getProjectId());
				webJsonData.put("relay", relay);
				// 添加ProjectAnalysisOpCodeThread对象的map中
				ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
				// 组装数据
				byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1032, sendJson.toJSONString());
				// 发送数据
				AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
				result = "send successful";
			} else {
				result = "control not online";
			}
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: RelayController.java
	 * @Description: 发生 远程配置 继电器信息
	 *
	 * @param:描述1描述
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @throws Exception
	 * @date: 2019年12月7日 下午4:37:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月7日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "sendRemoteConfigurationUpdateRelay.do", method = RequestMethod.POST)
	public JSONObject sendRemoteConfigurationUpdateRelay(Relay relay) throws Exception {
		// 反馈信息
		JSONObject resultJson = new JSONObject();
		// 状态
		String result = null;
		try {
			// 获取主录放精灵客户端信息
			SocketClientInfoVo controlSocketClient = OnLineClientUtils.getControlClient();
			// 判断中控是否在线
			if (controlSocketClient != null) {
				// 反馈反馈
				JSONObject sendJson = new JSONObject();
				// 继电器
				Relay beforeRelay = relayService.findById(relay.getId());
				// 节点信息
				RackDevice rackDevice = rackDeviceService
						.findById(intelligentGatewayService.findByMac(relay.getGatewayMac()).getRackId());
				// 判断继电器是否在线
				if (beforeRelay.getIsOnline() == 1) {
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					sendJson.put("uuid", uuid);
					sendJson.put("rackId", "" + rackDevice.getRackId());
					// 內容
					JSONObject msgJson = new JSONObject();
					msgJson.put("uuid", uuid);
					msgJson.put("gatewaMac", relay.getGatewayMac());
					msgJson.put("rs485toNetIp", relay.getRs485toNetIp());
					// 0:修改 1:添加
					msgJson.put("mode", "0");
					// 继电器信息
					List<JSONObject> relayJsonList = new ArrayList<JSONObject>();
					JSONObject relayJson = new JSONObject();
					relayJson.put("number", relay.getNumber());
					relayJson.put("slaveAddr", relay.getSlaveAddr());
					relayJsonList.add(relayJson);
					msgJson.put("relayList", relayJsonList);
					sendJson.put("taskMsg", msgJson);
					// 组装web数据（用于后续操作）
					JSONObject webJsonData = new JSONObject();
					webJsonData.put("projectId", rackDevice.getProjectId());
					webJsonData.put("relay", relay);
					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);
					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x1032, sendJson.toJSONString());
					// 发送数据
					AnalysisClientSocketThread.transmit(controlSocketClient.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "relay not online";
				}
			} else {
				result = "control not online";
			}
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
		resultJson.put("result", result);
		return resultJson;
	}

}
