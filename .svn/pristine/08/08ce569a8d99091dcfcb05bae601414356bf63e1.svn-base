package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.RealtimeTasks;

public interface RealtimeTasksService extends BaseService<RealtimeTasks> {

	/**
	 * 
	 * @Function: RealtimeTasksService.java
	 * @Description: 根据项目id 和任务名 获取即时任务 信息
	 *
	 * @param: projectId 项目ID
	 * @param: taskName 项目名
	 * @return：RealtimeTasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月25日 下午3:01:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月25日
	 *        Administrator v1.0.0 修改原因
	 */
	RealtimeTasks findByTaskName(Integer projectId, String taskName);

	/**
	 * 
	 * @Function: RealtimeTasksService.java
	 * @Description: 根据项目 id获取所有即时任务
	 *
	 * @param:projectId 项目id
	 * @return：List<RealtimeTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月25日 下午5:12:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月25日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RealtimeTasks> findByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: RealtimeTasksService.java
	 * @Description: 分页获取实时任务信息
	 *
	 * @param: realtimeTasks 实时任务对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：PageInfo<RealtimeTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月11日 上午11:27:06
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月11日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<RealtimeTasks> queryPageListByWhereByTaskName(RealtimeTasks realtimeTasks, Integer pageNo, Integer rows);

}
