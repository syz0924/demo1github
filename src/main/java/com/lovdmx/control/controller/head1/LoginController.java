package com.lovdmx.control.controller.head1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.exception.AccountNotExitException;
import com.lovdmx.control.common.exception.PasswordErrorException;
import com.lovdmx.control.common.exception.StatusNoExitException;
import com.lovdmx.control.common.utils.OffLineAccountUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Power;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.AccountService;
import com.lovdmx.control.service.PowerService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RoleService;

@Controller
@CrossOrigin
@RequestMapping("/")
public class LoginController extends BaseController {

	@Autowired
	private AccountService accountService; // 账号

	@Autowired
	private RoleService roleService; // 权限

	@Autowired
	private ProjectService projectService; // 项目

	@Autowired
	private PowerService powerService; // 功能

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 登录
	 *
	 * @param: userName 账号
	 * @param: password 密码
	 * @param: check 记住密码
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午4:07:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月30日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public JSONObject login(@RequestBody Account act, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		try {
			// 全局作用域
			ServletContext application = session.getServletContext();
			// 登录
			Account account = accountService.login(act.getUserName(), act.getPassword());
			// 获取session 之前是否保存账号信息
			Account acct = (Account) session.getAttribute("lovdmxAdmin");
			// 登录成功
			if (account != null) {
				// 登录成功
				resultJson.put("flag", "1");
				
				if (acct != null) {
					// 判断账号是否相同,不相同把之前账号注销,但是session不能销毁,因为是同一台机器session是同一个直接覆盖就可以了.
					if (!acct.getUserName().equals(account.getUserName())) {
						// 修改登录账号为在线
						acct.setIsOnline(0);
						accountService.update(acct);
						// 修改登录账号为在线
						account.setIsOnline(1);
						accountService.update(account);
						// 移除webSocket
						OffLineAccountUtils.removeAccountWebSocket(acct);
						// application中 移除
						application.removeAttribute(acct.getUserName());
					}
				} else {

					// 获取全局
					HttpSession session01 = (HttpSession) application.getAttribute(account.getUserName());
					if (session01 != null) {
						Account at = (Account) session01.getAttribute("lovdmxAdmin");
						// 移除webSocket
						OffLineAccountUtils.removeAccountWebSocket(at);
						// application中 移除
						application.removeAttribute(account.getUserName());
						// 销毁session
						// System.out.println(session01 == session);
						if (session01 != session) {
							session01.invalidate();
						}

					} else {
						// 修改登录账号为在线
						account.setIsOnline(1);
						accountService.update(account);
					}
				}
				// 超级用户
				if (account.getRoleId() == 1) {
					// 获取项目列表
					List<Project> projectList = projectService.findAll();
					resultJson.put("projectList", projectList);
				}else {
					//获取项目信息
					Project project = projectService.findById(account.getProjectId());
					account.setProjectName(project.getProjectName());
				}

				// 获取所有角色
				List<Power> powerList = powerService.findAll();
				// 角色
				Role role = roleService.findById(account.getRoleId());
				session.setAttribute("role", role);
				session.setAttribute("lovdmxAdmin", account);
				application.setAttribute(account.getUserName(), session);
				resultJson.put("powerList", powerList);
				resultJson.put("role", role);
				resultJson.put("account", account);
			}
		} catch (AccountNotExitException e) {
			// 账号不存在
			resultJson.put("flag", "2");
		} catch (PasswordErrorException e) {
			// 密码错误
			resultJson.put("flag", "3");
		} catch (StatusNoExitException e) {
			// 账号已禁用
			resultJson.put("flag", "4");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("flag", "6");
		}
		return resultJson;
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	@ResponseBody
	public boolean remove(HttpSession session) throws IOException {
		Integer num = 0;
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		if (account != null) {
			account.setIsOnline(0);
			// 修改为不在线
			num = accountService.update(account);
			// application中 移除
			session.getServletContext().removeAttribute(account.getUserName());
			// 移除webSocket
			OffLineAccountUtils.removeAccountWebSocket(account);
			// 移除session
			session.invalidate();
			// System.out.println("===================>>>remove close");
		}
		return num == 1 ? true : false;
	}

	/**
	 * 
	 * @Function: LoginController.java
	 * @Description: 超级用户 选择指定项目
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月27日 下午5:02:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月27日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "selectAssignProject", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject selectAssignProject(Integer projectId, HttpServletRequest request, HttpSession session) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 状态
		String result = null;
		// 获取 账号信息
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (account != null) {
			// 判断角色是否是超级用户
			if (account.getRoleId() == 1) {
				// 获取项目信息
				Project project = projectService.findById(projectId);
				if (project != null) {
					account.setProjectId(project.getProjectId());
					// 全局作用域
					ServletContext application = session.getServletContext();
					session.setAttribute("lovdmxAdmin", account);
					application.setAttribute(account.getUserName(), session);
					result = "succeed";
				} else {
					result = "not exist";
				}
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
