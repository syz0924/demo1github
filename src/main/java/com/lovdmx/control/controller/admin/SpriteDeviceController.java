package com.lovdmx.control.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.utils.JsonUtils;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.SpriteDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.SpriteDevice;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.SpriteDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SpriteDeviceController.java
 * @Description: 录放精灵 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月3日 下午4:43:55
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/spriteDevice/")
public class SpriteDeviceController extends BaseController {

	@Autowired
	private SpriteDeviceService spriteDeviceService; // 录放精灵

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 展示 录放精灵设备 列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午10:56:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(SpriteDeviceJoinRackDeviceVo spriteDeviceInfo, Integer pageNo, ModelMap map) {

		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();
		if (spriteDeviceInfo.getProjectId() != null) {
			// 根据项目id 获取所有节点信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(spriteDeviceInfo.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
		}
		// 模糊查询 分页
		PageInfo<SpriteDeviceJoinRackDeviceVo> pageInfo = spriteDeviceService
				.queryPageListByWhereOrSpriteMacOrIsOnlineOrRackId(spriteDeviceInfo, pageNo, rows);
		map.put("spriteDeviceInfo", spriteDeviceInfo);
		map.put("pageInfo", pageInfo);
		map.put("projectList", projectList);

		return "device/spriteDevice/spriteDevice_list";
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 添加 sprite设备
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
		JSONObject jsonObject = new JSONObject();
		// 获取所有机柜信息
		List<SpriteDevice> spriteDeviceList = spriteDeviceService.findAll();
		// 获取所有机柜信息
		List<RackDevice> rackDeviceList = rackDeviceService.findAll();

		jsonObject.put("spriteDeviceList", spriteDeviceList);
		jsonObject.put("rackDeviceList", rackDeviceList);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 编辑 sprite设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:32:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer spriteId) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 获取sprite 设备信息
		SpriteDevice spriteDevice = spriteDeviceService.findById(spriteId);

		if (spriteDevice.getRackId() != null) {
			// 获取 节点信息
			RackDevice rackDevice = rackDeviceService.findById(spriteDevice.getRackId());
			spriteDevice.setRackName(rackDevice.getRackName());
			rackDevice.setProjectName(projectService.findById(rackDevice.getProjectId()).getProjectName());
			// 根据项目id 获取节点列表
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(rackDevice.getProjectId());
			resultJson.put("rackDeviceList", rackDeviceList);
			resultJson.put("rackDevice", rackDevice);
		}
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();
		resultJson.put("projectList", projectList);
		resultJson.put("spriteDevice", spriteDevice);

		return resultJson;
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 保存sprite 设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:35:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(SpriteDevice spriteDevice) {
		// 反馈
		String result = "保存失败";
		JSONObject jsonObject = new JSONObject();

		// 保存 Sprite设备信息
		Integer num = spriteDeviceService.save(spriteDevice);
		if (num == 1) {
			result = "保存成功";
			// 机柜信息
			RackDevice rackDevice = rackDeviceService.findById(spriteDevice.getRackId());
			// 项目信息
			Project project = projectService.findById(rackDevice.getProjectId());
			spriteDevice.setRackIndex(rackDevice.getRackIndex());
			spriteDevice.setRackName(rackDevice.getRackName());
			spriteDevice.setProjectName(project.getProjectName());
			jsonObject.put("spriteDevice", spriteDevice);
		}
		// 反馈
		jsonObject.put("result", result);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 修改sprite 设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:35:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(SpriteDevice spriteDevice) {
		// 反馈
		String result = "修改失败";
		JSONObject jsonObject = new JSONObject();

		// 保存 Sprite设备信息
		Integer num = spriteDeviceService.update(spriteDevice);
		if (num == 1) {
			result = "修改成功";
			// 机柜信息
			RackDevice rackDevice = rackDeviceService.findById(spriteDevice.getRackId());
			// 项目信息
			Project project = projectService.findById(rackDevice.getProjectId());
			spriteDevice.setRackIndex(rackDevice.getRackIndex());
			spriteDevice.setRackName(rackDevice.getRackName());
			spriteDevice.setProjectName(project.getProjectName());
			jsonObject.put("spriteDevice", spriteDevice);
		}
		// 反馈
		jsonObject.put("result", result);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 判断Mac 是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:35:06
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "judgeMacIsExits.do", method = RequestMethod.GET)
	public void judgeMacIsExits(String spriteMac, HttpServletResponse response) {
		// 根据mac 获取Sprite信息
		SpriteDevice spriteDevice = spriteDeviceService.findByMac(spriteMac);
		ResponseUtils.renderText(response, spriteDevice == null ? "true" : "false");
	}

	/**
	 * 
	 * @Function: SpriteDeviceController.java
	 * @Description: 根据机柜id 获取录放精灵设备信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午11:11:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "getSpriteDeviceInfoByRackId.do", method = RequestMethod.GET)
	public void getSpriteDeviceInfoByRackId(Integer rackId, HttpServletResponse repsonse) {
		// 根据机柜id获取信息
		List<SpriteDevice> spriteDeviceList = spriteDeviceService.findRackId(rackId);
		ResponseUtils.renderJson(repsonse, JsonUtils.objectToJson(spriteDeviceList));
	}

}
