package com.lovdmx.control.service;

import java.util.List;

import com.lovdmx.control.pojo.SpriteTasks;

public interface SpriteTasksService extends BaseService<SpriteTasks> {

	/**
	 * 
	 * @Function: SpriteTasksService.java
	 * @Description: 根据录放精灵MAC ，修改任务所有状态信息
	 *
	 * @param: spriteMac 录放精灵MAC
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
	Integer updateAllStatusBySpriteMacAndStatus(String spriteMac, Integer status);

	/**
	 * 
	 * @Function: SpriteTasksService.java
	 * @Description: 根据Mac和任务id（集合）修改任务状态
	 *
	 * @param: taskIds 任务id集合
	 * @param: spriteMac Mac地址
	 * @param: status 状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 上午10:11:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateAllStatusByTaskIdListAndSpirteMacAndStatus(List<String> taskIds, String spriteMac, Integer status);

	/**
	 * 
	 * @Function: SpriteTasksService.java
	 * @Description: 批量删除录放精灵任务 根据任务id(集合)
	 *
	 * @param: taskIds 任务id集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 上午11:20:26
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDeleteByTaskIdList(List<String> taskIds);
}