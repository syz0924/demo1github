package com.lovdmx.control.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.TimedTasks;

public interface TimedTasksService extends BaseService<TimedTasks> {

	/**
	 * 
	 * @Function: TimedTasksService.java
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
	List<TimedTasks> findByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: TimedTasksService.java
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
	 * @Function: TimedTasksService.java
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
	TimedTasks findByTaskMd5(String taskMd5, Integer projectId);

	/**
	 * 
	 * @Function: TimedTasksService.java
	 * @Description: 条件查询 分页
	 *
	 * @param: timedTasks 继电器定时任务
	 * @return：PageInfo<TimedTasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月9日 上午11:42:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月9日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<TimedTasks> queryPageListByWhereTimedTasks(TimedTasks timedTasks, Integer pageNo,
			Integer rows);

	/**
	 * 
	 * @Function: TimedTasksService.java
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
	 * @Function: TimedTasksService.java
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
	Integer batchDelete(List<Integer> list);

	/**
	 * 
	 * @Function: TimedTasksService.java
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
	Integer batchUpdateRackIds(List<TimedTasks> list);

}
