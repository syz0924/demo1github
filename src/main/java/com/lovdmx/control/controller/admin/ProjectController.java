package com.lovdmx.control.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.service.ProjectService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProjectController.java
 * @Description:项目 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月19日 下午3:36:07
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/project/")
public class ProjectController {

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: ProjectController.java
	 * @Description: 展示
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午2:17:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Project project, ModelMap map) {
		// 获取所有项目信息
		List<Project> projectList = projectService.findProjectIdOrProjectName(project);
		map.put("projectList", projectList);
		return "project/project_list";
	}

	/**
	 * 
	 * @Function: ProjectController.java
	 * @Description: 编辑
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午2:13:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer projectId) {

		JSONObject json = new JSONObject();
		// 项目信息
		Project project = projectService.findById(projectId);
		json.put("project", project);

		return json;
	}

	/**
	 * 
	 * @Function: ProjectController.java
	 * @Description: 保存
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午2:13:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(Project project) {
		JSONObject json = new JSONObject();
		project.setCreateTime(new Date());
		// 保存
		Integer num = projectService.save(project);

		json.put("project", project);
		json.put("result", num == 1 ? "保存成功" : "保存失败");

		return json;
	}

	/**
	 * 
	 * @Function: ProjectController.java
	 * @Description: 修改
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午2:13:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public String update(Project project, HttpServletRequest request) {
		// 之前項目信息
		Project beforeProject = projectService.findById(project.getProjectId());
		// 保存
		Integer num = projectService.update(project);
		if (num > 0) {
			// 项目路径
			String basePath = request.getServletContext().getRealPath("/");
			File file = new File(
					basePath + File.separator + "export" + File.separator + beforeProject.getProjectName());
			if (file.exists() || file.isDirectory()) {
				// 重命名
				file.renameTo(
						new File(basePath + File.separator + "export" + File.separator + project.getProjectName()));
			}
		}
		return num == 1 ? "修改成功" : "修改失败";
	}

	/**
	 * 
	 * @Function: ProjectController.java
	 * @Description: 根据项目名 判断项目是否存在
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午3:30:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "findExistsByProjectName.do", method = RequestMethod.GET)
	public void findExistsByProjectName(String projectName, HttpServletResponse response) {
		String result = "";
		Project project = projectService.findByProjectName(projectName);
		result = project == null ? "true" : "false";

		ResponseUtils.renderText(response, result);
	}

}
