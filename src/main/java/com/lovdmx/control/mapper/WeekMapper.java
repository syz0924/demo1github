package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Week;

public interface WeekMapper extends BaseMapper<Week> {

	/**
	 * 
	 * @Function: WeekMapper.java
	 * @Description: 查询 继电器还有哪些 星期没有添加定时任务
	 *
	 * @param:relayId 继电器ID
	 * @return：List<Week>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 下午2:38:49
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年5月14日 Administrator v1.0.0 修改原因
	 */
	List<Week> findRelayNotExistsWeekByRelayId(@Param("relayId") Integer relayId);

}