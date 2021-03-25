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
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumRtrLoaded;
import com.lovdmx.control.pojo.enums.EnumUploadRole;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.UploadVideosService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: UploadVideoController.java
 * @Description: 上传视频 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月15日 下午4:20:58
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/uploadVideo/")
public class UploadVideoController extends BaseController {

	@Autowired
	private UploadVideosService uploadVideosService; // 上传

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: UploadVideoController.java
	 * @Description: 展示 上传视频列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月15日 下午4:20:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月15日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(UploadVideos uploadVideo, Integer pageNo, ModelMap map) {
		// 条件查询 上传视频文件 分页
		PageInfo<UploadVideos> pageInfo = uploadVideosService.queryPageListByWhereByRtrLoadedOrUploadRoleOrProjectId(uploadVideo,
				pageNo, rows);
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();
		map.put("pageInfo", pageInfo);
		map.put("uploadVideo", uploadVideo);
		map.put("projectList", projectList);
		map.put("rtrLoadedList", EnumRtrLoaded.values());
		map.put("uploadRoleList", EnumUploadRole.values());

		return "/task/uploadVideo_list";
	}

}
