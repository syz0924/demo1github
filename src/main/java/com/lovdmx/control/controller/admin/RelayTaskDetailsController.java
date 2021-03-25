package com.lovdmx.control.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.httpVo.RackAndGatewayAndRelayTaskDetailsAndWeekVo;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.pojo.Relay;
import com.lovdmx.control.pojo.Week;
import com.lovdmx.control.service.IntelligentGatewayService;
import com.lovdmx.control.service.ProjectService;
import com.lovdmx.control.service.RackDeviceService;
import com.lovdmx.control.service.RelayService;
import com.lovdmx.control.service.RelayTaskDetailsService;
import com.lovdmx.control.service.WeekService;

@RequestMapping("/lovdmx/ht/relayTaskDetails/")
@Controller
public class RelayTaskDetailsController extends BaseController {

	@Autowired
	private RelayTaskDetailsService relayTaskDetailsService; // 继电器定时任务详情

	@Autowired
	private RackDeviceService rackDeviceService; // 机柜

	@Autowired
	private IntelligentGatewayService intelligentGatewayService; // 智能网关

	@Autowired
	private WeekService weekService; // 星期

	@Autowired
	private RelayService relayService; // 继电器

	@Autowired
	private ProjectService projectService; // 项目

	/**
	 * 
	 * @Function: RelayTaskDetailsController.java
	 * @Description: 定时任务详情
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月25日 上午10:11:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(RackAndGatewayAndRelayTaskDetailsAndWeekVo relayTaskDetailsVo, Integer pageNo, ModelMap map) {
		// 根据继电器id 获取 定时任务详情信息
		PageInfo<RackAndGatewayAndRelayTaskDetailsAndWeekVo> pageInfo = relayTaskDetailsService
				.queryPageListByWhereRelayTimedTaskDetails(relayTaskDetailsVo, pageNo, rows);
		// 所有星期信息
		List<Week> weekList = weekService.findAll();
		// 获取所有项目
		List<Project> projectList = projectService.findAll();
		if (relayTaskDetailsVo.getProjectId() != null) {
			// 所有机柜信息
			List<RackDevice> rackDeviceList = rackDeviceService.findByParentId(relayTaskDetailsVo.getProjectId());
			map.put("rackDeviceList", rackDeviceList);
			if (relayTaskDetailsVo.getRackId() != null) {
				// 根据机柜id 获取 网关信息
				List<IntelligentGateway> intelligentGatewayList = intelligentGatewayService
						.findByRackId(relayTaskDetailsVo.getRackId());
				map.put("intelligentGatewayList", intelligentGatewayList);
				if (relayTaskDetailsVo.getGatewayMac() != null) {
					// 根据网关MAC 获取继电器信息
					List<Relay> relayList = relayService.findByGatewayMac(relayTaskDetailsVo.getGatewayMac());
					map.put("relayList", relayList);
				}
			}
		}
		map.put("pageInfo", pageInfo);
		map.put("projectList", projectList);
		map.put("relayTaskDetailsVo", relayTaskDetailsVo);
		map.put("weekList", weekList);

		return "intelligentGateway/relayTask/relayTaskDetails_list";
	}

}
