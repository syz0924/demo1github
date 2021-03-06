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
import com.lovdmx.control.common.utils.MonitoringUtils;
import com.lovdmx.control.common.utils.StringJudgeUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.MonitoringDevice;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.MonitoringDeviceService;
import com.lovdmx.control.service.RackDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MonitoringController.java
 * @Description: 监控控制器
 *
 * @version: v1.1.0
 * @author: syz
 * @date: 2019年10月15日 下午1:43:16
 *
 */
@Controller
@CrossOrigin
@RequestMapping("/monitoring/")
public class MonitoringController extends BaseController {

	// 机架
	@Autowired
	private RackDeviceService rackDeviceService;

	// 监控
	@Autowired
	private MonitoringDeviceService monitoringDeviceService;

	/**
	 * 
	 * @Function: WebControlServerController.java
	 * @Description: 获取机柜中 所有监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:06:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "getRackJoinMonitoringAllInfo", method = RequestMethod.GET)
	public JSONObject getMonitoringAllInfo(HttpServletRequest request, HttpSession session) {
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
				// 账号
				Account account = (Account) session.getAttribute("lovdmxAdmin");
				// 根据项目id 获取 机柜中 监控信息
				List<RackDevice> rackDeviceList = rackDeviceService
						.findRackDeviceJoinMonitoringDeviceByProjectId(account.getProjectId());
				resultJson.put("rackDeviceList", rackDeviceList);
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
	 * @Description: 根据序列号 获取监控信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:50:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "updateMonitoringDeviceAccessTokenByDeviceSerial", method = RequestMethod.GET)
	public JSONObject updateMonitoringDeviceAccessTokenByDeviceSerial(String deviceSerial, HttpServletRequest request,
			HttpSession session) {
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
					|| StringJudgeUtils.judgeWhetherIsLimits(role.getRoleLimit(), 3)) {
				// 根据设备序列号获取 监控信息
				MonitoringDevice monitoring = monitoringDeviceService.findByDeviceSerail(deviceSerial);

				Date expirationTime = monitoring.getExpirationTime();
				Date nowTime = new Date();

				if (nowTime.getTime() > expirationTime.getTime()) {
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
						// 修改数据
						monitoringDeviceService.update(monitoring);
					}
					result = code;
				} else {
					result = "true";
				}
				resultJson.put("monitoringDevice", monitoring);
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
