package com.lovdmx.control.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.utils.MonitoringUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.MonitoringDevice;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.service.MonitoringDeviceService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MonitoringDeviceController.java
 * @Description: 监控 设备 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月24日 上午11:17:49
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/monitoringDevice/")
public class MonitoringDeviceController extends BaseController {

	@Autowired
	private MonitoringDeviceService monitoringDeviceService; // 监控

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: MonitoringDeviceController.java
	 * @Description: 展示监控列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 上午11:33:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(MonitoringDevice monitoringDevice, Integer pageNo, ModelMap map) {

		// 获取所有项目
		List<Project> projectList = projectService.findAll();

		if (monitoringDevice.getProjectId() != null) {
			// 根据项目id 获取所有机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(monitoringDevice.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
		}

		// 模糊查询 分页
		PageInfo<MonitoringDevice> pageInfo = monitoringDeviceService.queryPageListByWhereOrRackId(monitoringDevice,
				pageNo, rows);
		map.put("monitoringDevice", monitoringDevice);
		map.put("pageInfo", pageInfo);
		map.put("projectList", projectList);
		return "device/monitoringDevice/monitoringDevice_list";
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 添加 监控设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:32:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	public JSONObject add() {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 获取所有项目 列表
		List<Project> projectList = projectService.findAll();
		resultJson.put("projectList", projectList);
		return resultJson;
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 编辑 监控设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:32:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer deviceId) {
		JSONObject jsonObject = new JSONObject();
		// 监控信息
		MonitoringDevice monitoringDevice = monitoringDeviceService.findById(deviceId);
		// 节点信息
		RackDevice rackDevice = rackDeviceService.findById(monitoringDevice.getRackId());
		rackDevice.setProjectName(projectService.findById(rackDevice.getProjectId()).getProjectName());
		monitoringDevice.setRackName(rackDevice.getRackName());
		// 获取所有项目 列表
		List<Project> projectList = projectService.findAll();
		// 获取所有机柜信息
		List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(rackDevice.getProjectId());
		jsonObject.put("projectList", projectList);
		jsonObject.put("rackDeviceList", rackDeviceList);
		jsonObject.put("rackDevice", rackDevice);
		jsonObject.put("monitoringDevice", monitoringDevice);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: MonitoringDeviceController.java
	 * @Description: 保存 监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午2:03:47
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(MonitoringDevice monitoring) {
		JSONObject jsonObject = new JSONObject();
		// 反馈状态
		String result = "";
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
			monitoring.setCreateTime(new Date());

			// 保存
			Integer num = monitoringDeviceService.save(monitoring);
			jsonObject.put("monitoringDevice", monitoring);

			// 反馈
			result = num == 0 ? "添加失败" : "添加成功";
		} else if (code.equals("10001")) {
			result = "参数错误!   描述:参数为空或格式不正确";
		} else if (code.equals("10005")) {
			result = "appKey异常!   描述:appKey被冻结";
		} else if (code.equals("10017")) {
			result = "appKey不存在!   描述:确认appKey是否正确";
		} else if (code.equals("10030")) {
			result = "appkey和appSecret不匹配";
		} else if (code.equals("49999")) {
			result = "数据异常！  描述:接口调用异常";
		}
		jsonObject.put("result", result);

		return jsonObject;
	}

	/**
	 * 
	 * @Function: MonitoringDeviceController.java
	 * @Description: 保存 监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午2:03:47
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(MonitoringDevice monitoring) {
		JSONObject jsonObject = new JSONObject();
		// 反馈状态
		String result = "";

		MonitoringDevice monitoringDevice = monitoringDeviceService.findById(monitoring.getDeviceId());

		Date expirationTime = monitoringDevice.getExpirationTime();
		Date nowTime = new Date();

		String code = "";
		// 判断 appKey 或 appSecret是否修改 或 现在时间大于过期时间 修改 更新 accessToken
		if (!monitoringDevice.getAppKey().equals(monitoring.getAppKey())
				|| !monitoringDevice.getAppSecret().equals(monitoring.getAppSecret())
				|| nowTime.getTime() > expirationTime.getTime()) {
			// 获取凭证反馈
			String jsonResult = MonitoringUtils.getAccessToken(monitoring);
			// 转成 JSON 格式
			JSONObject jsonData = JSONObject.parseObject(jsonResult);
			// 反馈码
			code = jsonData.getString("code");
			// 200 操作成功
			if (code.equals("200")) {
				JSONObject data = (JSONObject) jsonData.get("data");
				// 凭证
				String accessToken = data.getString("accessToken");
				// 过期时间
				long expireTime = data.getLong("expireTime");
				monitoring.setAccessToken(accessToken);
				monitoring.setExpirationTime(new Date(expireTime));
			} else if (code.equals("10001")) {
				result = "参数错误!   描述:参数为空或格式不正确";
			} else if (code.equals("10005")) {
				result = "appKey异常!   描述:appKey被冻结";
			} else if (code.equals("10017")) {
				result = "appKey不存在!   描述:确认appKey是否正确";
			} else if (code.equals("10030")) {
				result = "appkey和appSecret不匹配";
			} else if (code.equals("49999")) {
				result = "数据异常！  描述:接口调用异常";
			}
		} else {
			// 界面展示 要更新数据
			monitoring.setAccessToken(monitoringDevice.getAccessToken());
			monitoring.setExpirationTime(expirationTime);
		}
		// 200 或 "" 进行修改
		if (code.equals("200") || code.equals("")) {
			// 保存
			Integer num = monitoringDeviceService.update(monitoring);
			jsonObject.put("monitoringDevice", monitoring);
			// 反馈
			result = num == 0 ? "修改失败" : "修改成功";
		}

		jsonObject.put("result", result);

		return jsonObject;
	}

	/**
	 * 
	 * @Function: MonitoringDeviceController.java
	 * @Description: 删除
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午3:15:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public String delete(Integer deviceId) {
		String result = "";
		Integer num = monitoringDeviceService.deleteById(deviceId);
		result = num == 0 ? "删除失败" : "删除成功";
		return result;
	}

	/**
	 * 
	 * @Function: MonitoringDeviceController.java
	 * @Description: 批量删除
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午3:16:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "batchDelete.do", method = RequestMethod.GET)
	public String batchDelete(@RequestParam Integer[] arrIds) {
		String result = "";
		// 批量删除
		Integer num = monitoringDeviceService.batchDelete(arrIds);
		result = num == 0 ? "删除失败" : "删除成功";
		return result;
	}

}
