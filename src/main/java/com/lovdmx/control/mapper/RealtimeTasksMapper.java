package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.RealtimeTasks;

public interface RealtimeTasksMapper extends BaseMapper<RealtimeTasks> {

	/**
	 * 
	 * @Function: RealtimeTasksMapper.java
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
	RealtimeTasks findByTaskName(@Param("projectId") Integer projectId, @Param("taskName") String taskName);

	/**
	 * 
	 * @Function: RealtimeTasksMapper.java
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
	List<RealtimeTasks> findByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: RealtimeTasksMapper.java
	 * @Description: 根据
	 *
	 * @param: 项目和即时任务 表连接 条件项目id 和 任务名
	 * @return：List<RealtimeTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月11日 上午11:31:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月11日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RealtimeTasks> findRealtimeTaskJoinProjectByProjectIdOrTaskName(RealtimeTasks realtimeTasks);

}