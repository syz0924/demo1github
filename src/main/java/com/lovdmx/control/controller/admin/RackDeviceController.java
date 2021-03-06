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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.utils.ResponseUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: rackDeviceController.java
 * @Description: 机柜控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年6月20日 下午3:59:23
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/rackDevice/")
public class RackDeviceController extends BaseController {

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 设备机柜 展示列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月22日 上午10:37:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(RackDevice rackDevice, Integer pageNo, ModelMap map) {
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();

		// 分页
		PageInfo<RackDevice> pageInfo = rackDeviceService.queryPageListByWhereRackNameOrRackIdOrProjectId(rackDevice,
				pageNo, rows);
		// 项目信息
		Project project = projectService.findById(rackDevice.getProjectId());

		map.put("projectList", projectList);
		map.put("pageInfo", pageInfo);
		map.put("project", project);

		return "rackDevice/rackDevice_list";
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 添加设备机柜
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月22日 上午10:38:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	public JSONObject add() {
		JSONObject json = new JSONObject();
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();
		json.put("projectList", projectList);
		return json;
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 编辑设备机柜
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月22日 上午10:38:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public JSONObject edit(Integer rackId) {
		JSONObject json = new JSONObject();
		// 获取机柜信息
		RackDevice rackDevice = rackDeviceService.findById(rackId);
		// 获取项目名
		rackDevice.setProjectName(projectService.findById(rackDevice.getProjectId()).getProjectName());
		// 获取所有项目信息
		List<Project> projectList = projectService.findAll();

		json.put("projectList", projectList);
		json.put("rackDevice", rackDevice);

		return json;
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 保存机柜信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月25日 下午1:48:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public JSONObject save(RackDevice rackDevice) {
		JSONObject json = new JSONObject();
		rackDevice.setCreateTime(new Date());
		Integer num = 0;
		try {
			num = rackDeviceService.save(rackDevice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("rackDevice", rackDevice);
		json.put("result", num == 0 ? "保存失败" : "保存成功");
		return json;
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 保存机柜信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月25日 下午1:48:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public JSONObject update(RackDevice rackDevice, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		// 项目信息
		Project project = projectService.findById(rackDevice.getProjectId());
		// 之前机架信息
		RackDevice beforeRackDevice = rackDeviceService.findById(rackDevice.getRackId());
		Integer num = rackDeviceService.update(rackDevice);
		if (num > 0) {
			// 项目路径
			String basePath = request.getServletContext().getRealPath("/");
			File file = new File(basePath + File.separator + "export" + File.separator + project.getProjectName()
					+ File.separator + beforeRackDevice.getRackName());
			if (file.exists() || file.isDirectory()) {
				// 重命名
				file.renameTo(new File(basePath + File.separator + "export" + File.separator + project.getProjectName()
						+ File.separator + rackDevice.getRackName()));
			}
		}
		json.put("result", num == 0 ? "修改失败" : "修改成功");
		return json;
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 删除机柜信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月25日 下午5:05:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public void delete(Integer rackId, HttpServletResponse response) {
		// 移除机柜
		Integer num = rackDeviceService.deleteById(rackId);
		ResponseUtils.renderText(response, num == 0 ? "删除失败" : "删除成功");
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 批量删除
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月27日 下午3:59:33
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月27日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "batchDelete.do", method = RequestMethod.GET)
	public void batchDelete(@RequestParam Integer[] arrIds, HttpServletResponse response) {
		String result = "";
		// 批量删除
		Integer num = rackDeviceService.batchDelete(arrIds);
		result = num == 0 ? "删除失败" : "删除成功";
		ResponseUtils.renderText(response, result);
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
	@RequestMapping(value = "findExistsByRackIndex.do", method = RequestMethod.GET)
	public void findExistsByRackIndex(Integer rackIndex, Integer projectId, HttpServletResponse response) {
		String result = "";
		RackDevice rackDevice = rackDeviceService.findByProjectIdAndRackIndex(projectId, rackIndex);
		result = rackDevice == null ? "true" : "false";
		ResponseUtils.renderText(response, result);
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
	@RequestMapping(value = "findExistsByRackName.do", method = RequestMethod.GET)
	public void findExistsByRackName(String rackName, Integer projectId, HttpServletResponse response) {
		String result = "";
		RackDevice rackDevice = rackDeviceService.findByProjectIdAndRackName(projectId, rackName);
		result = rackDevice == null ? "true" : "false";
		ResponseUtils.renderText(response, result);
	}

	/**
	 * 
	 * @Function: RackDeviceController.java
	 * @Description: 根据项目id 查询节点信息
	 *
	 * @param: projecId 项目id
	 * @return：JSONObject
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月22日 上午11:11:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月22日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "querRackDeviceInfoByProjectId.do", method = RequestMethod.GET)
	@ResponseBody
	public List<RackDevice> querRackDeviceInfoByProjectId(Integer projectId) {
		// 根据项目id 获取所有节点信息
		List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(projectId);
		return rackDeviceList;

	}

}
