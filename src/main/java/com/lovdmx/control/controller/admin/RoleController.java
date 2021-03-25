package com.lovdmx.control.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Power;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.PowerService;
import com.lovdmx.control.service.RoleService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: RoleController.java
 * @Description: 角色管理 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月19日 下午3:33:14
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/limit/role/")
public class RoleController extends BaseController {

	// 角色
	@Autowired
	private RoleService roleService;

	// 权限
	@Autowired
	private PowerService powerService;

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 角色列表展示
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:37:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(ModelMap map) {
		// 获取所有角色
		List<Role> roleList = roleService.findRoleInfoAll();
		map.put("roleList", roleList);
		return "limit/role/role_list";
	}

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 添加角色信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:43:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject add() {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 获取所有权限信息
		List<Power> powerList = powerService.findAll();

		resultJson.put("powerList", powerList);
		return resultJson;
	}

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 编辑角色信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:43:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject edit(Integer roleId) {
		// 反馈
		JSONObject resultJson = new JSONObject();

		// 获取角色信息
		Role role = roleService.findById(roleId);
		// 获取所有权限信息
		List<Power> powerList = powerService.findAll();

		resultJson.put("role", role);
		resultJson.put("powerList", powerList);

		return resultJson;
	}

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 保存角色信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:45:28
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject save(Role role) throws Exception{
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 保存角色
		Integer saveId = roleService.save(role);
		if (saveId == 1) {
			// 获取 权限名称
			String limitNames = powerService.findByRoleLimit(role.getRoleLimit());
			role.setLimitNames(limitNames);
		}
		resultJson.put("result", saveId == 0 ? false : true);
		resultJson.put("role", role);

		return resultJson;
	}

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 修改角色信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:45:28
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(Role role)throws Exception {
		// 反馈
		JSONObject resultJson = new JSONObject();
		try {
			// 修改角色
			Integer updateId = roleService.update(role);
			if (updateId == 1) {
				// 获取 权限名称
				String limitNames = powerService.findByRoleLimit(role.getRoleLimit());
				role.setLimitNames(limitNames);
			}
			resultJson.put("result", updateId == 0 ? false : true);
			resultJson.put("role", role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultJson;
	}

	/**
	 * 
	 * @Function: RoleController.java
	 * @Description: 删除角色信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:47:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public String delete(Integer roleId) {
		// 删除
		Integer deleteId = roleService.deleteById(roleId);
		return deleteId == 0 ? "刪除失败" : "删除成功";
	}

}
