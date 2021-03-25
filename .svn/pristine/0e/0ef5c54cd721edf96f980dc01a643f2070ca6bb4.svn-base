package com.lovdmx.control.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;

import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.service.LogService;

@Controller
public class BaseController {

	// 每页的数量
	public static Integer rows = 14;

	// 日志
	protected static org.apache.commons.logging.Log logger = LogFactory.getLog(BaseController.class);

	// 事务
	@Resource(name = "transactionManager")
	public PlatformTransactionManager platformTransactionManager;

	// 日志业务层
	@Autowired
	public LogService logService;

	// 添加日志信息
	public boolean addlogInfo(Log log) {
		// 添加操作日志
		Integer save = logService.save(log);
		return save == 0 ? false : true;
	}

}
