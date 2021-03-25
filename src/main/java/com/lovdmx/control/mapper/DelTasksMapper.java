package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.DelTasks;

public interface DelTasksMapper extends BaseMapper<DelTasks> {
	/**
	 * 
	 * @Function: DelTasksMapper.java
	 * @Description: 批量插入
	 *
	 * @param: list 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月27日 下午3:31:05
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchInsert(@Param("list") List<DelTasks> list);
}