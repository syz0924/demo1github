package com.lovdmx.control.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.TimedTasks;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.TimedTasksService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RelayTimedTaskController.java
 * @Description: 继电器定时任务 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月9日 上午11:18:59
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/relayTimedTask/")
public class RelayTimedTaskController extends BaseController {

	@Autowired
	private TimedTasksService timedTasksService; // 定时任务

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: RelayTimedTaskController.java
	 * @Description: 继电器定时任务 展示
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月9日 下午2:10:39
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月9日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(TimedTasks timedTasks, Integer pageNo, ModelMap map) {

		// 分页
		PageInfo<TimedTasks> pageInfo = timedTasksService.queryPageListByWhereTimedTasks(timedTasks, pageNo, rows);
		// 获取所有 项目列表
		List<Project> projectList = projectService.findAll();

		map.put("timedTasks", timedTasks);
		map.put("projectList", projectList);
		map.put("pageInfo", pageInfo);

		return "intelligentGateway/relayTask/relayTimeTask_list";
	}

}
