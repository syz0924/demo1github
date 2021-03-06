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
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.common.utils.SecurityUtil;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.AccountService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RoleService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: AccountController.java
 * @Description: 前端管理员控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月19日 下午3:35:33
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/account/")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService; // 账号业务层

	@Autowired
	private ProjectService projectService; // 项目业务层

	@Autowired
	private RoleService roleService; // 角色业务层

	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 查询 前端账号信息
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Account account, Integer pagoNo, ModelMap map) {
		// List<Account> accountList =
		// accountService.findByAccountIdORUserName(account);
		// 分页查询
		PageInfo<Account> pageInfo = accountService.queryPageListByAccount(account, pagoNo, rows);
		// 获取所有角色信息
		List<Role> roleList = roleService.findAll();
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();

		map.put("pageInfo", pageInfo);
		map.put("account", account);
		map.put("roleList", roleList);
		map.put("projectList", projectList);
		return "account/account_list";
	}

	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 编辑管理员账号信息
	 *
	 * @param: accountId 账号id
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:44:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer accountId, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		// 账号
		Account account = accountService.findById(accountId);
		if (account.getRoleId() != 1) {
			Project project = projectService.findById(account.getProjectId());
			account.setProjectName(project.getProjectName());
		} else {
			account.setProjectName("管理所有项目");
		}
		// 角色名
		account.setRoleName(roleService.findById(account.getRoleId()).getRoleName());
		// 所有項目信息
		List<Project> projectList = projectService.findAll();
		// 所有角色信息
		List<Role> roleList = roleService.findAll();
		json.put("account", account);
		json.put("projectList", projectList);
		json.put("roleList", roleList);
		return json;
	}

	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 编辑管理员账号信息
	 *
	 * @param: accountId 账号id
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:44:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	public JSONObject add() {
		JSONObject json = new JSONObject();
		// 所有項目信息
		List<Project> projectList = projectService.findAll();
		// 所有角色信息
		List<Role> roleList = roleService.findAll();
		json.put("projectList", projectList);
		json.put("roleList", roleList);
		return json;
	}

	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 保存管理员 注意： 随机产生 加密码盐值 status默认值为0，表示禁用 1： 启用
	 * @param: account 账号信息
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:48:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(Account account, HttpServletResponse response) {

		JSONObject jSONObject = new JSONObject();

		// 加密码盐值
		String salt = SecurityUtil.md5Encode(account.getPassword());
		account.setSalt(salt);
		String pwd = account.getPassword();
		// 密码
		String pwdSalt = SecurityUtil.md5Encode(pwd + account.getSalt());
		account.setPassword(pwdSalt);
		// 创建时间
		account.setCreateTime(new Date());

		if (account.getRoleId() == 1) {
			account.setProjectId(0);
		}

		// 添加
		Integer num = accountService.save(account);

		jSONObject.put("result", num == 0 ? "添加失败" : "添加成功");
		jSONObject.put("account", account);

		return jSONObject;
	}

	/**
	 * 
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public String update(Account account, HttpServletResponse response) {

		if (account.getPassword() != null) {
			// 加密码盐值
			String salt = SecurityUtil.md5Encode(account.getPassword());
			account.setSalt(salt);
			String pwd = account.getPassword();
			// 密码
			String pwdSalt = SecurityUtil.md5Encode(pwd + account.getSalt());
			account.setPassword(pwdSalt);
		}
		// 修改
		Integer num = accountService.update(account);
		return num == 0 ? "修改失败" : "修改成功";
	}

	/**
	 * 
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "updateStatus.do", method = RequestMethod.GET)
	public void updateStatus(Integer accountId, Short status, HttpServletResponse response) {
		JSONObject json = new JSONObject(); // 对象

		Account account = accountService.findById(accountId);
		account.setStatus(status);
		Integer num = accountService.update(account);
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
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public void delete(Integer accountId, HttpServletResponse response) {
		String result = "";
		Integer num = accountService.deleteById(accountId);
		result = num == 0 ? "删除失败" : "删除成功";
		ResponseUtils.renderText(response, result);
	}

	/**
	 * 
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "findExistsByUserName.do", method = RequestMethod.GET)
	public void findExistsByUserName(String userName, HttpServletResponse response) {
		String result = "";
		Account account = accountService.findByUserName(userName);
		result = account == null ? "true" : "false";

		ResponseUtils.renderText(response, result);
	}

	/**
	 * 
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "judgePassword.do", method = RequestMethod.POST)
	public void judgePassword(Account account, HttpServletResponse response) {
		boolean bool = accountService.judgePassword(account.getAccountId(), account.getPassword());
		ResponseUtils.renderText(response, bool + "");
	}

	/**
	 * 
	 * @Function: AccountController.java
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
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "updatePwd.do", method = RequestMethod.POST)
	public void updatePwd(Account account, HttpServletResponse response) {

		String result = "";
		// 加密码盐值
		String salt = SecurityUtil.md5Encode(account.getPassword());
		account.setSalt(salt);
		String pwd = account.getPassword();
		// 密码
		String pwdSalt = SecurityUtil.md5Encode(pwd + account.getSalt());
		account.setPassword(pwdSalt);
		Integer num = accountService.update(account);
		result = num == 0 ? "false" : "true";
		ResponseUtils.renderText(response, result);
	}

}
