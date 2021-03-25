package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Tasks;

public interface TasksMapper extends BaseMapper<Tasks> {
	/**
	 * 
	 * @Function: TasksMapper.java
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
	List<Tasks> findByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: TasksMapper.java
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
	Integer findCountByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: TasksMapper.java
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
	Tasks findByProjectIdAndTaskName(@Param("projectId") Integer projectId, @Param("taskName") String taskName);

	/**
	 * 
	 * @Function: TasksMapper.java
	 * @Description: 根据项目id和任务的md5值 获取信息
	 *
	 * @param: projectId 项目id
	 * @param: taskMd5 任务md5值
	 * @return：Tasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月12日 下午3:22:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月12日
	 *        Administrator v1.0.0 修改原因
	 */
	Tasks findByProjectIdAndTaskMd5(@Param("projectId") Integer projectId, @Param("taskMd5") String taskMd5);

	/**
	 * 
	 * @Function: TasksMapper.java
	 * @Description: 根据定时任务md5值 修改任务状态
	 *
	 * @param: tasks 定时任务对象
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月25日 下午5:41:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月25日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateStatusByTaskMd5(Tasks tasks);

	/**
	 * 
	 * @Function: TasksMapper.java
	 * @Description: 根据项目id，修改所有任务状态信息
	 *
	 * @param: projectId 项目id
	 * @param: status 状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月26日 上午11:33:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateAllStatusByProjectIdAndStatus(@Param("projectId") Integer projectId, @Param("status") Integer status);

	/**
	 * 
	 * @Function: TasksMapper.java
	 * @Description: 根据定时任务md5 集合,获取定时任务id逗号隔开(,)
	 *
	 * @param: taskMd5List 定时任务Md5 集合
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月26日 下午4:47:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月26日
	 *        Administrator v1.0.0 修改原因
	 */
	String findTaskIdByTaskMd5List(@Param("list") List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksMapper.java
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
	Integer batchDeleteByTaskMd5List(@Param("list") List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksMapper.java
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
	List<Tasks> findTaskListByTaskMd5List(@Param("list") List<String> taskMd5List);

	/**
	 * 
	 * @Function: TasksMapper.java
	 * @Description: （OR）定时任务对象条件查询
	 *
	 * @param:Tasks 定时任务对象
	 * @return：List<Tasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年8月8日 下午2:53:03
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年8月8日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Tasks> findByOrTasks(Tasks task);

}