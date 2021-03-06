package com.lovdmx.control.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.pojo.enums.EnumCyclicMode;
import com.lovdmx.control.pojo.enums.EnumTaskType;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.TasksService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: TimedTaskController.java
 * @Description: 录放精灵定时任务 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年8月8日 下午2:36:12
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/spriteTimedTask/")
public class SpriteTimedTaskController extends BaseController {

	@Autowired
	private TasksService tasksService; // 定时任务

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: SpriteTimedTaskController.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年8月8日 下午3:15:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年8月8日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Tasks task, Integer pageNo, ModelMap map) {
		// 时间范围
		String timeScope = task.getTimeScope();
		if (timeScope != null && !timeScope.equals("")) {
			// 分割
			String[] split = timeScope.split("-");
			// 开始时间
			task.setStartTime(DateUtils.fmtStrDateToTime(split[0].trim()));
			// 结束时间
			task.setEndTime(DateUtils.fmtStrDateToTime(split[1].trim()));
		}

		// System.out.println(task.getEndTime());
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();

		// 分页
		PageInfo<Tasks> pageInfo = tasksService.queryPageListByWhereTasks(task, pageNo, rows);

		map.put("pageInfo", pageInfo);
		map.put("task", task);
		map.put("projectList", projectList);
		map.put("taskTypeList", EnumTaskType.values());
		map.put("cyclicModeList", EnumCyclicMode.values());

		return "task/timedTask_list";
	}

}
