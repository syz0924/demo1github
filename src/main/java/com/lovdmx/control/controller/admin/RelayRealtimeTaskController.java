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
import com.lovdmx.control.pojo.RealtimeTasks;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RealtimeTasksService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RealtimeTaskController.java
 * @Description: 继电器即时任务 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月11日 下午3:29:03
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/relayRealtimeTask/")
public class RelayRealtimeTaskController extends BaseController {

	@Autowired
	private RealtimeTasksService realtimeTasksService; //

	@Autowired
	private ProjectService projectService; // 项目

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(RealtimeTasks realtimeTasks, Integer pageNo, ModelMap map) {

		// 根据任务名 分页
		PageInfo<RealtimeTasks> pageInfo = realtimeTasksService.queryPageListByWhereByTaskName(realtimeTasks, pageNo,
				rows);
		// 获取项目列表
		List<Project> projectList = projectService.findAll();

		map.put("pageInfo", pageInfo);
		map.put("projectList", projectList);
		map.put("realtimeTasks", realtimeTasks);

		return "intelligentGateway/relayTask/relayRealTimeTask_list";
	}
}
