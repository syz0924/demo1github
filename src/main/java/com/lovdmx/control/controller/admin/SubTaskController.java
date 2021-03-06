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
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.pojo.enums.EnumFileType;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.SubtasksService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SubTaskController.java
 * @Description: 子任务 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月15日 下午1:21:50
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/subtask/")
public class SubTaskController extends BaseController {

	@Autowired
	private SubtasksService subtasksService; // 子任务

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: SubTaskController.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月15日 下午1:40:10
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月15日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Subtasks subtask, Integer pageNo, ModelMap map) {

		// 获取所有项目
		List<Project> projectList = projectService.findAll();
		// 分页
		PageInfo<Subtasks> pageInfo = subtasksService.queryPageListByWhereOrSubtaskNameOrFileTypeOrProjectId(subtask, pageNo,
				rows);

		map.put("pageInfo", pageInfo);
		map.put("subtask", subtask);
		map.put("projectList", projectList);
		map.put("fileTypeList", EnumFileType.values());

		return "/task/subtask_list";
	}

}
