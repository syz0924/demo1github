package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.TimedTasks;

public interface TimedTasksMapper extends BaseMapper<TimedTasks> {

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 根据项目ID获取所有智能网关定时任务
	 *
	 * @param:projectId 项目ID
	 * @return：List<TimedTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月4日 上午11:37:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月4日
	 *        Administrator v1.0.0 修改原因
	 */
	List<TimedTasks> findByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 根据任务名获取定时任务信息
	 *
	 * @param:taskName 任务名
	 * @return：TimedTasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月4日 上午11:49:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月4日
	 *        Administrator v1.0.0 修改原因
	 */
	TimedTasks findByTaskName(@Param("taskName") String taskName);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 根据任务的md5查询定时任务
	 *
	 * @param:taskMd5 任务md5值
	 * @param:projectId 项目id
	 * @return：TimedTasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月6日 上午10:15:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月6日
	 *        Administrator v1.0.0 修改原因
	 */
	TimedTasks findByTaskMd5(@Param("taskMd5") String taskMd5, @Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 条件查询 定时任务
	 *
	 * @param: timedTasks 定时任务对象
	 * @return：List<TimedTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月9日 上午11:49:29
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<TimedTasks> findByTimedTasks(TimedTasks timedTasks);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 条件查询 获取所有定时任务信息
	 *
	 * @param: timedTasks 定时任务对象
	 * @return：List<TimedTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月13日 下午1:30:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月13日
	 *        Administrator v1.0.0 修改原因
	 */
	List<TimedTasks> queryListByConditionIf(TimedTasks timedTasks);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 批量删除
	 *
	 * @param: list 集合（任务id）
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月13日 下午3:27:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月13日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(@Param("list") List<Integer> list);

	/**
	 * 
	 * @Function: TimedTasksMapper.java
	 * @Description: 批量修改
	 *
	 * @param: list 定时任务集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月13日 下午3:29:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月13日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchUpdateRackIds(@Param("list") List<TimedTasks> list);

}