package com.lovdmx.control.controller.head;

import java.io.IOException;

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
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.service.AccountService;
import com.lovdmx.control.service.SpriteDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: HomeController.java
 * @Description: 首页控制器
 *
 * @version: v1.1.0
 * @author: syz
 * @date: 2019年10月15日 下午1:49:38
 *
 */
@Controller
@RequestMapping("/")
@CrossOrigin
public class HomeController extends BaseController {

	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private SpriteDeviceService spriteDeviceService;

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
		JSONObject jSONObject = new JSONObject();
		try {

			ServletContext application = session.getServletContext();
			Account acct = (Account) session.getAttribute("lovdmxAdmin");
			Account account = accountService.login(act.getUserName(), act.getPassword());
			if (account != null) {
				if (account.getRoleId() == 1) {
					// 超级用户
					jSONObject.put("flag", "0");
				} else {
					// 普通用户
					jSONObject.put("flag", "1");
				}

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
						System.out.println(session01 == session);
						if (session01 != session) {
							session01.invalidate();
						}

					} else {
						// 修改登录账号为在线
						account.setIsOnline(1);
						accountService.update(account);
					}
				}
				session.setAttribute("lovdmxAdmin", account);
				application.setAttribute(account.getUserName(), session);
				jSONObject.put("account", account);
			}
		} catch (AccountNotExitException e) {
			// 账号不存在
			jSONObject.put("flag", "2");
		} catch (PasswordErrorException e) {
			// 密码错误
			jSONObject.put("flag", "3");
		} catch (StatusNoExitException e) {
			// 账号已禁用
			jSONObject.put("flag", "4");
		} catch (Exception e) {
			e.printStackTrace();
			jSONObject.put("flag", "6");
		}
		return jSONObject;
	}

	@RequestMapping(value = "index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "mobile")
	public String index1() {
		return "mobile";
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("remove")
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
			System.out.println("===================>>>remove close");
		}
		return num == 1 ? true : false;
	}
	
	
	
	/**
	 * 
	 * @Function: ControlServerController.java
	 * @Description: 获取Sprite 在线和未在线数量
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月24日 下午4:56:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月24日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("getSpriteOnlineNumber")
	public JSONObject getSpriteOnlineNumber(HttpSession session) {

		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");

		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// Not Online 数量
			Integer notOnlineNumber = spriteDeviceService.findNumberByProjectIdAndIsOnlineStatus(0,
					account.getProjectId());
			// Online 数量
			Integer onlineNumber = spriteDeviceService.findNumberByProjectIdAndIsOnlineStatus(1,
					account.getProjectId());
			resultJson.put("online", onlineNumber);
			resultJson.put("notOnlineNumber", notOnlineNumber);
			result = "true";
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}
	
	

}
