package com.lovdmx.control.controller.head1;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.StringJudgeUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Dmx512Device;
import com.lovdmx.control.pojo.Dmx512DeviceDetails;
import com.lovdmx.control.pojo.GatewayDevice;
import com.lovdmx.control.pojo.GatewayDeviceDetails;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.Dmx512DeviceDetailsService;
import com.lovdmx.control.service.Dmx512DeviceService;
import com.lovdmx.control.service.GatewayDeviceDetailsService;
import com.lovdmx.control.service.GatewayDeviceService;

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
@CrossOrigin
@RequestMapping("/node/")
public class NodeController extends BaseController {

	// dmx512设备 实时数据
	@Autowired
	private Dmx512DeviceService dmx512DeviceService;

	// dmx512设备 历史数据
	@Autowired
	private Dmx512DeviceDetailsService dmx512DeviceDetailsService;

	// 网关设备 实时数据
	@Autowired
	private GatewayDeviceService gatewayDeviceService;

	// 网关设备 历史数据
	@Autowired
	private GatewayDeviceDetailsService gatewayDeviceDetailsService;

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据dmx512 MAC获取 控制盒（实时）信息
	 *
	 * @param:dmx512Mac mac
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 上午11:13:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryDmx512DeviceInfo", method = RequestMethod.GET)
	public JSONObject queryDmx512DeviceInfo(String dmx512Mac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 根据dmx512Mac 查控制盒信息
				Dmx512Device dmx512Device = dmx512DeviceService.findByDmx512Mac(dmx512Mac);

				resultJson.put("dmx512Device", dmx512Device);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据RTRMAC获取 所有控制盒（实时）信息
	 *
	 * @param: rtrMac mac
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 上午11:13:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryDmx512DeviceListInfo", method = RequestMethod.GET)
	public JSONObject queryDmx512DeviceListInfo(String rtrMac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 根据RTRMAC 控制盒信息
				List<Dmx512Device> dmx512DeviceList = dmx512DeviceService.findByRtrMac(rtrMac);

				resultJson.put("dmx512DeviceList", dmx512DeviceList);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 根据dmx512 MAC获取 控制盒(当天历史)信息
	 *
	 * @param:dmx512Mac mac
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 上午11:13:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryDmx512DeviceDetailsListInfo", method = RequestMethod.GET)
	public JSONObject queryDmx512DeviceDetailsInfo(String dmx512Mac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// dmx512设备详情
				Dmx512DeviceDetails dmx512DeviceDetails = dmx512DeviceDetailsService.findByDmx512MacAndToday(dmx512Mac,
						DateUtils.currentlyStrDate(new Date()));

				resultJson.put("dmx512DeviceDetails", dmx512DeviceDetails);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: NodeController.java
	 * @Description: 根据网关设备MAC获取 网关设备(实时)信息
	 *
	 * @param: deviceMac 网关设备MAC
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午4:40:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryGatewayDeviceInfo", method = RequestMethod.GET)
	public JSONObject queryGatewayDeviceInfo(String deviceMac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 根据设备按MAC 获取信息
				GatewayDevice gatewayDevice = gatewayDeviceService.findByDeviceMac(deviceMac);

				resultJson.put("gatewayDevice", gatewayDevice);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: NodeController.java
	 * @Description: 根据网关MAC获取 所有网关设备(实时)信息
	 *
	 * @param: gatewayMac 网关MAC
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午4:40:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryGatewayDeviceListInfo", method = RequestMethod.GET)
	public JSONObject queryGatewayDeviceListInfo(String gatewayMac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 根据设备按MAC 获取信息
				List<GatewayDevice> gatewayDeviceList = gatewayDeviceService.findByGatewayMac(gatewayMac);
				resultJson.put("gatewayDeviceList", gatewayDeviceList);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

	/**
	 * 
	 * @Function: NodeController.java
	 * @Description: 根据设备MAC 获取网关设备(当天历史)信息
	 *
	 * @param:deviceMac 设备MAC
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午5:08:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "queryGatewayDeviceDetailsInfo", method = RequestMethod.GET)
	public JSONObject queryGatewayDeviceDetailsInfo(String deviceMac, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 登录状态
		boolean loginStatus = (boolean) request.getAttribute("login_Status");
		// 判断是否在线
		if (loginStatus) {
			// 获取角色
			Role role = (Role) session.getAttribute("role");
			// 判断是否有权限
			if (role.getRoleId() == 1 || role.getRoleId() == 2
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 4)) {
				// 根据设备按MAC 获取信息
				GatewayDeviceDetails gatewayDeviceDetails = gatewayDeviceDetailsService
						.findByDeviceMacAndToday(deviceMac, DateUtils.currentlyStrDate(new Date()));
				resultJson.put("gatewayDeviceDetails", gatewayDeviceDetails);
				result = "true";
			} else {
				result = "no permission";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

}
