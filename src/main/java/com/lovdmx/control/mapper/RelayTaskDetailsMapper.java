package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.RackAndGatewayAndRelayTaskDetailsAndWeekVo;
import com.lovdmx.control.pojo.RelayTaskDetails;

public interface RelayTaskDetailsMapper extends BaseMapper<RelayTaskDetails> {

	/**
	 * 
	 * @Function: RelayTaskDetailsMapper.java
	 * @Description: 根据继电器id 获取 定时任务详情
	 *
	 * @param: relayId 继电器ID
	 * @return：List<RelayTaskDetails>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 下午2:42:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RelayTaskDetails> findByRelayId(@Param("relayId") Integer relayId);

	/**
	 * 
	 * @Function: RelayTaskDetailsMapper.java
	 * @Description: 批量添加继电器定时任务详情
	 *
	 * @param: List<RelayTaskDetails> relayTasksList 一个或多个定时任务
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 下午2:49:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchInsert(@Param("list") List<RelayTaskDetails> relayTaskDetailList);

	/**
	 * 
	 * @Function: RelayTaskDetailsMapper.java
	 * @Description: 根据任务id,删除信息
	 *
	 * @param:taskId 任务id
	 * @param: arrayRelayId 数组继电器id
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月5日 下午4:07:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月5日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer deleteByTaskIdAndArrayRelayId(@Param("taskId") Integer taskId, @Param("list") String[] arrayRelayId);

	/**
	 * 
	 * @Function: RelayTaskDetailsMapper.java
	 * @Description: 机柜和网关和继电器和继电器任务详情和任务表和详情表多表查询By （條件查詢）
	 *
	 * @param: relayTaskDetailsVo 机柜和网关个继电器任务详情对象
	 * @return：List<RackAndGatewayAndRelayTaskDetailsAndWeekVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月10日 下午1:58:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月10日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackAndGatewayAndRelayTaskDetailsAndWeekVo> findByRelayRelayTimedTaskDetails(
			RackAndGatewayAndRelayTaskDetailsAndWeekVo relayTaskDetailsVo);

}