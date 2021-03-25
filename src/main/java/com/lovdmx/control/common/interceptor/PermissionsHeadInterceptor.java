package com.lovdmx.control.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lovdmx.control.common.utils.OnLineClientUtils;

/**
 * 后端 拦截器
 *
 * @author syz
 * @Date 2018年12月15日
 * @version 2.0
 */
public class PermissionsHeadInterceptor extends HandlerInterceptorAdapter {

	
	public boolean preHandle1(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String path = request.getContextPath();
		// 验证Session是否过期
		if (!request.isRequestedSessionIdValid()) {
			// session过期,转向session过期提示页,最终跳转至登陆页面
			request.getSession(true);
			response.sendRedirect(path + "/lovdmx/ht/login.do");
			return false;
		} else {
			HttpSession session = request.getSession();
			// 用户登陆了就继续
			if (!isLogin(session)) {
				// 没登陆就返回到登陆页面
				response.sendRedirect(path + "/lovdmx/ht/login.do");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 验证Session是否过期
		if (!request.isRequestedSessionIdValid()) {
			// session过期,转向session过期提示页,最终跳转至登陆页面
			request.getSession(true);
			request.setAttribute("login_Status", false);
		} else {
			HttpSession session = request.getSession();
			// 用户登陆了就继续
			if (isLogin(session)) {
				request.setAttribute("login_Status", true);
				// 判断是否在线
				if (OnLineClientUtils.getSpriteClient() != null) {
					request.setAttribute("sprite_Online_Status", true);
				} else {
					request.setAttribute("sprite_Online_Status", false);
				}
				if (OnLineClientUtils.getControlClient() != null) {
					request.setAttribute("control_Online_Status", true);
				} else {
					request.setAttribute("control_Online_Status", false);
				}
				
			} else {
				request.setAttribute("login_Status", false);
			}
		}

		return super.preHandle(request, response, handler);
	}

	/**
	 * 
	 * @Function: PermissionsHeadInterceptor.java
	 * @Description: 判断是否 过期
	 *
	 * @param: session
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月25日 下午2:30:15
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月25日
	 *        Administrator v1.0.0 修改原因
	 */
	private boolean isLogin(HttpSession session) {
		if (session != null && session.getAttribute("lovdmxAdmin") != null) {
			return true;
		}
		return false;
	}

}
