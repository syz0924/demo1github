/**
 * 
 */
package com.lovdmx.control.controller.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovdmx.control.common.exception.AccountNotExitException;
import com.lovdmx.control.common.exception.PasswordErrorException;
import com.lovdmx.control.common.exception.StatusNoExitException;
import com.lovdmx.control.common.utils.CaptchaUtil;
import com.lovdmx.control.common.utils.CookieUtils;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Manage;
import com.lovdmx.control.service.ManageService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: HomeController.java
 * @Description: 管理员登录 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月18日 上午11:42:35
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/")
public class HomeController extends BaseController {

	@Autowired
	private ManageService manageService;

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 跳转登录界面
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:42:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "login.do")
	public String login(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String loginName = "";
		String password = "";
		// cookie中的账号
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("controlAdmin_loginName")) {
					loginName = cookie.getValue();
					continue;
				}
				if (cookie.getName().equals("controlAdmin_password")) {
					password = cookie.getValue();
					continue;
				}
			}
		}
		request.setAttribute("loginName", CookieUtils.decodeBase64(loginName));
		request.setAttribute("password", CookieUtils.decodeBase64(password));

		return "login";
	}

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 登录
	 *
	 * @param: loginName
	 *             账号 password 密码
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:42:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(String loginName, String password, boolean check, HttpServletResponse response,
			HttpSession session) {
		try {
			Manage manage = manageService.login(loginName, password);
			if (manage != null) {
				// 保存到cookie中
				Cookie nameCookie = null;
				Cookie pwdCookie = null;
				nameCookie = new Cookie("controlAdmin_loginName", CookieUtils.encodeBase64(manage.getLoginName()));
				pwdCookie = new Cookie("controlAdmin_password", CookieUtils.encodeBase64(password));
				if (check == true) {
					// 设置时间
					nameCookie.setMaxAge(60 * 60 * 24 * 30);
					pwdCookie.setMaxAge(60 * 60 * 24 * 30);
				} else {
					nameCookie.setMaxAge(0);
					pwdCookie.setMaxAge(0);
				}
				nameCookie.setPath("/");
				// 账号密码保存到浏览器中
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
				session.setAttribute("iadmin", manage);
			}
			return "redirect:/lovdmx/ht/index.do";
		} catch (StatusNoExitException ex) {
			session.setAttribute("flag", ex.getMessage());
		} catch (AccountNotExitException ex) {
			session.setAttribute("flag", ex.getMessage());
		} catch (PasswordErrorException ex) {
			session.setAttribute("flag", ex.getMessage());
		} catch (Exception ex) {
			session.setAttribute("flag", ex.getMessage());
		}

		return "redirect:/lovdmx/ht/login.do";
	}

	@RequestMapping(value = "index.do")
	public String index() {
		return "index";
	}

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 获取验证码图片
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:43:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "captcha.do", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaptchaUtil.outputCaptcha(request, response);
	}

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 判断验证码是否一致
	 *
	 * @param: code
	 *             验证码
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:43:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping("areEqualsCode.do")
	public void areEqualsCode(String code, HttpSession session, HttpServletResponse response) {
		String randomString = (String) session.getAttribute("randomString");
		String flag = "0";
		// 不区分大小写
		if (randomString.equalsIgnoreCase(code))
			flag = "1";
		ResponseUtils.renderText(response, flag);
	}

	/**
	 * 
	 * @Function: HomeController.java
	 * @Description: 退出登录
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午11:44:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	@RequestMapping("remove.do")
	public String remove(HttpSession session) {
		session.removeAttribute("iadmin");
		return "redirect:/lovdmx/ht/login.do";
	}
}
