package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.VideoOrLmxTaskNameVo;
import com.lovdmx.control.pojo.Subtasks;

public interface SubtasksService extends BaseService<Subtasks> {

	/**
	 * @Function: SubtasksService.java
	 * @Description: 根据父类ID获取 所有子任务信息
	 *
	 * @param:parentId 父类ID
	 * @return：List<Subtasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月21日 下午5:36:41
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Subtasks> findByParentId(Integer parentId);

	/**
	 * 
	 * @Function: SubtasksService.java
	 * @Description: 根据子任务名查询
	 *
	 * @param:subtaskName 子任务名
	 * @param: projectId 项目ID
	 * @return：Subtasks
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月2日 下午4:00:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月2日
	 *        Administrator v1.0.0 修改原因
	 */
	Subtasks findBySubtaskName(String subtaskName, Integer projectId);

	/**
	 * 
	 * @Function: SubtasksService.java
	 * @Description: 根据子任务id获取 视频或灯光 文件的切割名
	 *
	 * @param:arrayIds 数组子任务Id
	 * @return：List<VideoOrLmxTaskNameVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月4日 下午3:48:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月4日
	 *        Administrator v1.0.0 修改原因
	 */
	List<VideoOrLmxTaskNameVo> findSpiltVideoOrLmxDataByIds(String[] arrayIds);

	/**
	 * 
	 * @Function: SubtasksService.java
	 * @Description: 数组子任务id 获取信息
	 *
	 * @param:arrayIds 数组子任务Id
	 * @return：List<Subtasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月25日 下午5:04:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月25日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Subtasks> findByIds(String[] arrayIds);

	/**
	 * 
	 * @Function: SubtasksService.java
	 * @Description: 数组子任务id 和 文件类型 获取信息
	 *
	 * @param:arrayIds 数组子任务Id
	 * @param: fileType 文件类型
	 * @return：List<Subtasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月25日 下午5:04:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月25日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Subtasks> findByIdsAndFileType(String[] arrayIds, String fileType);

	/**
	 * 
	 * @Function: SubtasksService.java
	 * @Description:分页 根据子任务名或文件类型或项目id条件查询
	 *
	 * @param: subtask 子任务对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：PageInfo<Subtasks>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月15日 下午1:30:24
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月15日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<Subtasks> queryPageListByWhereOrSubtaskNameOrFileTypeOrProjectId(Subtasks subtask, Integer pageNo, Integer rows);
}