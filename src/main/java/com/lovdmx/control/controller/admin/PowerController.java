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
import com.lovdmx.control.service.PowerService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: PowerController.java
 * @Description: 权限功能 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月19日 下午4:24:12
 *
 */
@Controller
@RequestMapping("/lovdmx/ht/limit/power/")
public class PowerController extends BaseController {

	// 权限
	@Autowired
	private PowerService powerService;

	/**
	 * 
	 * @Function: PowerController.java
	 * @Description: 权限展示列表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:27:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(ModelMap map) {
		// 获取所有权限
		List<Power> powerList = powerService.findAll();
		map.put("powerList", powerList);
		return "limit/power/power_list";
	}

	/**
	 * 
	 * @Function: PowerController.java
	 * @Description: 编辑权限
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:28:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject edit(Integer id, ModelMap map) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 获取权限信息
		Power power = powerService.findById(id);
		resultJson.put("power", power);
		return resultJson;
	}

	/**
	 * 
	 * @Function: PowerController.java
	 * @Description: 保存权限
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:31:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject save(Power power) {
		// 反馈
		JSONObject resultJson = new JSONObject();

		// 保存
		Integer saveId = powerService.save(power);

		resultJson.put("result", saveId == 0 ? false : true);
		resultJson.put("power", power);

		return resultJson;
	}

	/**
	 * 
	 * @Function: PowerController.java
	 * @Description: 修改权限
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:31:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(Power power) {
		// 反馈
		JSONObject resultJson = new JSONObject();
		// 更新
		Integer updateId = powerService.update(power);

		resultJson.put("result", updateId == 0 ? false : true);
		return resultJson;
	}

	/**
	 * 
	 * @Function: PowerController.java
	 * @Description: 删除权限
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月19日 下午4:35:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月19日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Integer id) {
		// 刪除
		Integer deleteId = powerService.deleteById(id);
		return deleteId == 0 ? "刪除失败" : "删除成功";
	}
}
