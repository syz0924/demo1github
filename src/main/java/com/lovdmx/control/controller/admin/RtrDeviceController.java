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
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.RtrDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.RtrDevice;
import com.lovdmx.control.pojo.enums.EnumCurMode;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RtrDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RtrDeviceController.java
 * @Description: RTR设备 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月1日 上午11:03:27
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/rtrDevice/")
public class RtrDeviceController extends BaseController {

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private RtrDeviceService rtrDeviceService; // RTR

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 展示rtr设备列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 上午11:08:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(RtrDeviceJoinRackDeviceVo rtrDeviceInfo, Integer pageNo, ModelMap map) {

		// 获取所有项目列表
		List<Project> projectList = projectService.findAll();
		if (rtrDeviceInfo.getProjectId() != null) {
			// 获取当前项目下 所有机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(rtrDeviceInfo.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
		}
		// 模糊查询 分页
		PageInfo<RtrDeviceJoinRackDeviceVo> pageInfo = rtrDeviceService
				.queryPageListByWhereOrRtrMacOrModeIdOrIsOnlineOrRackId(rtrDeviceInfo, pageNo, rows);
		map.put("rtrDeviceInfo", rtrDeviceInfo);
		map.put("pageInfo", pageInfo);
		map.put("projectList", projectList);
		map.put("curModeList", EnumCurMode.values());

		return "device/rtrDevice/rtrDevice_list";
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 添加 rtr设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午2:25:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	public JSONObject add() {
		JSONObject jsonObject = new JSONObject();
		// 获取所有项目
		List<Project> projectList = projectService.findAll();

		jsonObject.put("projectList", projectList);
		jsonObject.put("curModeList", EnumCurMode.values());

		return jsonObject;
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 编辑 rtr设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午2:25:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer rtrId) {
		//反馈
		JSONObject resultJson = new JSONObject();
		// 获取rtr 设备信息
		RtrDevice rtrDevice = rtrDeviceService.findById(rtrId);
		if (rtrDevice.getRackId() != null) {
			// 获取 节点信息
			RackDevice rackDevice = rackDeviceService.findById(rtrDevice.getRackId());
			rtrDevice.setRackName(rackDevice.getRackName());
			rackDevice.setProjectName(projectService.findById(rackDevice.getProjectId()).getProjectName());
			// 根据项目id 获取节点列表
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(rackDevice.getProjectId());
			resultJson.put("rackDeviceList", rackDeviceList);
			resultJson.put("rackDevice", rackDevice);
		}
		// 获取所有项目
		List<Project> projectList = projectService.findAll();

		resultJson.put("projectList", projectList);
		resultJson.put("rtrDevice", rtrDevice);
		resultJson.put("curModeList", EnumCurMode.values());

		return resultJson;
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 保存rtr 设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午3:00:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(RtrDevice rtrDevice) {
		// 反馈
		String result = "保存失败";
		JSONObject jsonObject = new JSONObject();

		// 保存 RTR设备信息
		Integer num = rtrDeviceService.save(rtrDevice);
		if (num == 1) {
			result = "保存成功";
			// 机柜信息
			RackDevice rackDevice = rackDeviceService.findById(rtrDevice.getRackId());
			// 项目信息
			Project project = projectService.findById(rackDevice.getProjectId());
			rtrDevice.setRackIndex(rackDevice.getRackIndex());
			rtrDevice.setRackName(rackDevice.getRackName());
			rtrDevice.setProjectName(project.getProjectName());
			jsonObject.put("rtrDevice", rtrDevice);
		}
		// 反馈
		jsonObject.put("result", result);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 修改rtr 设备
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午3:00:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(RtrDevice rtrDevice) {
		// 反馈
		String result = "修改失败";
		JSONObject jsonObject = new JSONObject();

		// 保存 RTR设备信息
		Integer num = rtrDeviceService.update(rtrDevice);
		if (num == 1) {
			result = "修改成功";
			// 机柜信息
			RackDevice rackDevice = rackDeviceService.findById(rtrDevice.getRackId());
			// 项目信息
			Project project = projectService.findById(rackDevice.getProjectId());
			rtrDevice.setRackIndex(rackDevice.getRackIndex());
			rtrDevice.setRackName(rackDevice.getRackName());
			rtrDevice.setProjectName(project.getProjectName());
			jsonObject.put("rtrDevice", rtrDevice);
		}
		// 反馈
		jsonObject.put("result", result);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 判断Mac 是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午3:42:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "judgeMacIsExits.do", method = RequestMethod.GET)
	public void judgeMacIsExits(String rtrMac, HttpServletResponse response) {
		// 根据mac 获取RTR信息
		RtrDevice rtrDevice = rtrDeviceService.findByMac(rtrMac);
		ResponseUtils.renderText(response, rtrDevice == null ? "true" : "false");
	}

	/**
	 * 
	 * @Function: RtrDeviceController.java
	 * @Description: 根据机柜id 获取rtr设备信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 上午11:11:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("getRtrDeviceInfoByRackId.do")
	public List<RtrDevice> getRtrDeviceInfoByRackId(Integer rackId) {
		// 根据机柜id 获取信息
		List<RtrDevice> rtrDeviceList = rtrDeviceService.findByRackId(rackId);
		return rtrDeviceList;

	}

}
