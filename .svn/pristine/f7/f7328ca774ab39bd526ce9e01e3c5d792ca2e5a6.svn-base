package com.lovdmx.control.common.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lovdmx.control.common.utils.OffLineAccountUtils;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.service.AccountService;

public class OnlineUserListener implements HttpSessionListener, ServletContextListener {

	private AccountService accountService;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();

		ServletContext application = session.getServletContext();
		// 获取账号信息
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		if (account != null) {
			// 移除 application中session信息
			application.removeAttribute(account.getUserName());
			// 移除webSocket
			try {
				OffLineAccountUtils.removeAccountWebSocket(account);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			account.setIsOnline(0);
			// 修改为不在线
			accountService.update(account);
			//System.out.println("===================>>>session close");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext servletContext = event.getServletContext();
		// 获取IOC所有对象
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// 账号 业务层
		accountService = (AccountService) context.getBean("accountService");
	}

}
