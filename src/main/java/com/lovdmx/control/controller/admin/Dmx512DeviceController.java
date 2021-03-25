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
import com.lovdmx.control.pojo.Dmx512Device;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.RtrDevice;
import com.lovdmx.control.service.Dmx512DeviceService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RtrDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512DeviceController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月4日 下午1:54:20
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/dmx512Device/")
public class Dmx512DeviceController extends BaseController {

	@Autowired
	private Dmx512DeviceService dmx512DeviceService; // 512控制盒

	@Autowired
	private RtrDeviceService rtrDeviceService; // rtr设备

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: Dmx512DeviceController.java
	 * @Description: DMX512 控制盒 列表 展示
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午2:26:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Dmx512Device dmx512Device, Integer pageNo, ModelMap map) {

		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();
		if (dmx512Device.getProjectId() != null) {
			// 根据项目id 获取所有机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(dmx512Device.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
			if (dmx512Device.getRackId() != null) {
				// 根据节点id 获取rtr设备信息
				List<RtrDevice> rtrDeviceList = rtrDeviceService.findByRackId(dmx512Device.getRackId());
				map.put("rtrDeviceList", rtrDeviceList);
			}
		}

		// 根据rtrMAc 分页
		PageInfo<Dmx512Device> pageInfo = dmx512DeviceService.queryPageListByWhereRtrMac(dmx512Device, pageNo, rows);
		map.put("pageInfo", pageInfo);
		map.put("dmx512Device", dmx512Device);
		map.put("projectList", projectList);
		return "device/dmx512Device/dmx512Device_list";
	}

	/**
	 * 
	 * @Function: Dmx512DeviceController.java
	 * @Description: 编辑
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午2:26:42
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer dmxId) {
		JSONObject jsonObject = new JSONObject();
		// dmx512信息
		Dmx512Device dmx512Device = dmx512DeviceService.findById(dmxId);
		jsonObject.put("dmx512Device", dmx512Device);
		return jsonObject;
	}

	/**
	 * 
	 * @Function: Dmx512DeviceController.java
	 * @Description: 修改
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午2:30:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(Dmx512Device dmx512Device) {
		JSONObject jsonObject = new JSONObject();
		// 修改dmx512控制盒
		Integer num = dmx512DeviceService.update(dmx512Device);
		jsonObject.put("result", num == 0 ? "修改失败" : "修改成功");
		jsonObject.put("dmx512Device", dmx512Device);
		return jsonObject;
	}

}
