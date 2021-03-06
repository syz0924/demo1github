package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.Tasks;

public interface TasksService extends BaseService<Tasks> {
	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据父类ID获取所有任务信息
	 *
	 * @param:projectId 父类ID
	 * @return：List<Tasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月21日 下午4:46:23
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Tasks> findByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据父类ID获取所有任务总数
	 *
	 * @param:parentId 父类ID
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午4:38:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer findCountByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据项目id和任务名 获取信息
	 *
	 * @param:taskName 任务名
	 * @param: projectId 项目Id
	 * @return：Tasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月5日 下午4:28:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月5日
	 *        Administrator v1.0.0 修改原因
	 */
	Tasks findByProjectIdAndTaskName(Integer projectId, String taskName);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据项目id和任务的md5值 获取信息
	 *
	 * @param: projectId 项目id
	 * @param: taskMd5 任务md5值
	 * @return：Tasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月12日 下午3:21:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月12日
	 *        Administrator v1.0.0 修改原因
	 */
	Tasks findByProjectIdAndTaskMd5(Integer projectId, String taskMd5);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据任务md5 修改状态
	 *
	 * @param: tasks 定时任务对象
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月25日 下午5:36:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月25日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateStatusByTaskMd5(Tasks tasks);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据项目id ，修改任务所有状态信息
	 *
	 * @param: projectId 项目Id
	 * @param: status 状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月26日 上午11:29:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateAllStatusByProjectIdAndStatus(Integer projectId, Integer status);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据定时任务md5 集合,获取定时任务id逗号隔开(,)
	 *
	 * @param: taskMd5List 定时任务Md5 集合
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月26日 下午4:42:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	String findTaskIdByTaskMd5List(List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 批量删除定时任务 根据md5（集合）
	 *
	 * @param: taskMd5List 定时任务Md5 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 上午11:28:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDeleteByTaskMd5List(List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 根据任务md5(集合) 获取任务信息
	 *
	 * @param: taskMd5List 定时任务Md5 集合
	 * @return：List<Tasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 下午3:56:03
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Tasks> findTaskListByTaskMd5List(List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksService.java
	 * @Description: 分页 根据定时任务条件查询
	 *
	 * @param: task 定时任务对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：PageInfo<Tasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年8月8日 下午2:49:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年8月8日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<Tasks> queryPageListByWhereTasks(Tasks task, Integer pageNo, Integer rows);

}