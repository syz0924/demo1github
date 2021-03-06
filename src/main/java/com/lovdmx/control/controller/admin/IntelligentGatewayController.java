package com.lovdmx.control.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.service.IntelligentGatewayService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: IntelligentGatewayController.java
 * @Description: 智能网关 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月6日 下午4:30:36
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/intelligentGateway/")
public class IntelligentGatewayController extends BaseController {

	@Autowired
	private ProjectService projectService; // 项目

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private IntelligentGatewayService intelligentGatewayService; // 智能网关

	/**
	 * 
	 * @Function: IntelligentGatewayController.java
	 * @Description: 展示 智能网关列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午5:34:38
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(IntelligentGateway intelligentGateway, Integer pageNo, ModelMap map) {
		// 获取所有项目列表
		List<Project> projectList = projectService.findAll();
		if (intelligentGateway.getProjectId() != null) {
			// 获取所有 机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(intelligentGateway.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
		}
		// 模糊查询 智能网关列表 （分页）
		PageInfo<IntelligentGateway> pageInfo = intelligentGatewayService
				.queryPageListByWhereRackIdOrMacOrIsOnline(intelligentGateway, pageNo, rows);

		map.put("pageInfo", pageInfo);
		map.put("intelligentGateway", intelligentGateway);
		map.put("projectList", projectList);

		return "intelligentGateway/intelligentGateway_list";
	}

	/**
	 * 
	 * @Function: IntelligentGatewayController.java
	 * @Description: 编辑
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月6日 下午2:37:47
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月6日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer intelligentGatewayId) {

		JSONObject jsonObject = new JSONObject();

		// 根据id 获取网关信息
		IntelligentGateway intelligentGateway = intelligentGatewayService.findById(intelligentGatewayId);
		// 获取所有 机柜信息
		List<RackDevice> rackDeviceList = rackDeviceService.findAll();
		if (intelligentGateway.getRackId() != null) {
			intelligentGateway.setRackName(rackDeviceService.findById(intelligentGateway.getRackId()).getRackName());
		} else {
			intelligentGateway.setRackName("请选择机柜");
			intelligentGateway.setRackId(-1);
		}
		jsonObject.put("intelligentGateway", intelligentGateway);
		jsonObject.put("rackDeviceList", rackDeviceList);

		return jsonObject;
	}

	/**
	 * 
	 * @Function: IntelligentGatewayController.java
	 * @Description: 修改
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月6日 下午3:16:24
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月6日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(IntelligentGateway intelligentGateway) {

		JSONObject jsonObject = new JSONObject();
		// 修改
		Integer num = intelligentGatewayService.update(intelligentGateway);

		jsonObject.put("result", num == 0 ? "修改失败" : "修改成功");
		return jsonObject;
	}

	/**
	 * 
	 * @Function: IntelligentGatewayController.java
	 * @Description: 根据机架id获取 智能网关信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月8日 上午10:01:43
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月8日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("getIntelligentGatewayInfoByRackId.do")
	public List<IntelligentGateway> getIntelligentGatewayInfoByRackId(Integer rackId) {
		// 根据机柜id 获取信息
		List<IntelligentGateway> intelligentGatewayList = intelligentGatewayService.findByRackId(rackId);
		return intelligentGatewayList;

	}

}
