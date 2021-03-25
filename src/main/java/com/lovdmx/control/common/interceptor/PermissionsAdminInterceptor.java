package com.lovdmx.control.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 后端 拦截器
 *
 * @author syz
 * @Date 2018年12月15日
 * @version 2.0
 */
public class PermissionsAdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
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

	private boolean isLogin(HttpSession session) {
		if (session != null && session.getAttribute("iadmin") != null) {
			return true;
		}

		return false;
	}

}
