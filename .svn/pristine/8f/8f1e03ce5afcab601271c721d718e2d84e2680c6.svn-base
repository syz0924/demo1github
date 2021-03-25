/**
 * 
 */
package com.lovdmx.control.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.common.utils.SecurityUtil;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Manage;
import com.lovdmx.control.service.ManageService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ManageController.java
 * @Description: 后端管理员控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月19日 下午3:34:55
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/manage/")
public class ManageController extends BaseController {

	@Autowired
	private ManageService manageService;

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 查看所有 管理员信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:29:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Manage manage, ModelMap map) {
		List<Manage> manageList = manageService.findByManageIdORLoginName(manage);
		map.put("manageList", manageList);
		return "manage/manage_list";
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 编辑管理员账号信息
	 *
	 * @param: manageId
	 *             账号id
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:44:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public net.sf.json.JSONObject edit(HttpServletResponse response, Integer manageId) {
		Manage manage = manageService.findById(manageId);
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(manage);
		return json;
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 保存管理员 注意： 随机产生 加密码盐值 status默认值为0，表示禁用 1： 启用
	 * @param: manage
	 *             账号信息
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:48:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(Manage manage, HttpServletResponse response) {

		JSONObject jSONObject = new JSONObject();

		// 加密码盐值
		String salt = SecurityUtil.md5Encode(manage.getPassword());
		manage.setSalt(salt);
		String pwd = manage.getPassword();
		// 密码
		String pwdSalt = SecurityUtil.md5Encode(pwd + manage.getSalt());
		manage.setPassword(pwdSalt);
		// 创建时间
		manage.setCreateTime(new Date());
		// 状态
		manage.setStatus((short) 1);
		// 反馈
		String result = "";
		// 添加
		Integer num = manageService.save(manage);

		result = num == 0 ? "添加失败" : "添加成功";
		jSONObject.put("result", result);
		jSONObject.put("manage", manage);

		return jSONObject;
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 修改 管理员信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:30:16
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "updateInfo.do")
	public String updateInfo(Manage manage, HttpServletResponse response) {
		// 修改
		Integer num = manageService.update(manage);
		return num == 0 ? "修改失败" : "修改成功";
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 启用或禁用(要求用ajax)
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:52:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "updateStatus.do", method = RequestMethod.GET)
	public void updateStatus(Integer manageId, Short status, HttpServletResponse response) {
		JSONObject json = new JSONObject(); // 对象

		Manage manage = manageService.findById(manageId);
		manage.setStatus(status);
		Integer num = manageService.update(manage);
		if (num == 1) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		String value = json.toJSONString();
		ResponseUtils.renderJson(response, value);

	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 删除管理员信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:30:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public void delete(Integer manageId, HttpServletResponse response) {
		String result = "";
		Integer num = manageService.deleteById(manageId);
		result = num == 0 ? "删除失败" : "删除成功";
		ResponseUtils.renderText(response, result);
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 判断登录账号是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:31:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "findExistsByLoginName.do", method = RequestMethod.GET)
	public void findExistsByLoginName(String loginName, HttpServletResponse response) {
		String result = "";
		Manage manage = manageService.findByLoginName(loginName);
		result = manage == null ? "true" : "false";

		ResponseUtils.renderText(response, result);
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 账号信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:31:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "info.do", method = RequestMethod.GET)
	public String info(Integer manageId, ModelMap map) {
		Manage manageInfo = manageService.findById(manageId);
		map.put("manageInfo", manageInfo);
		return "manage/manage_info";
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 跳转密码界面
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:31:12
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "upadtePwd.do", method = RequestMethod.GET)
	public String upadtePwd() {
		return "manage/manage_upd_password";
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 判断密码是否正确
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:31:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "judgePassword.do", method = RequestMethod.POST)
	public void judgePassword(Manage manage, HttpServletResponse response) {
		boolean bool = manageService.judgePassword(manage.getManageId(), manage.getPassword());
		ResponseUtils.renderText(response, bool + "");
	}

	/**
	 * 
	 * @Function: ManageController.java
	 * @Description: 修改密码
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午1:31:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "updatePwd.do", method = RequestMethod.POST)
	public void updatePwd(Manage manage, HttpServletResponse response) {

		String result = "";
		// 加密码盐值
		String salt = SecurityUtil.md5Encode(manage.getPassword());
		manage.setSalt(salt);
		String pwd = manage.getPassword();
		// 密码
		String pwdSalt = SecurityUtil.md5Encode(pwd + manage.getSalt());
		manage.setPassword(pwdSalt);
		Integer num = manageService.update(manage);
		result = num == 0 ? "false" : "true";
		ResponseUtils.renderText(response, result);
	}

}
