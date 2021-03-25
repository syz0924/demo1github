package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.IntelligentGateway;

public interface IntelligentGatewayMapper extends BaseMapper<IntelligentGateway> {

	/**
	 * 
	 * @Function: IntelligentGatewayMapper.java
	 * @Description: 机架内连接智能网关 然后根据项目ID 获取网关信息
	 *
	 * @param:projectId 项目ID
	 * @return：List<IntelligentGateway>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午10:37:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	List<IntelligentGateway> findRackJoinIntelligentGatewayByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: IntelligentGatewayMapper.java
	 * @Description: 根据网关Mac 修改网关信息
	 *
	 * @param: mac MAC地址
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月25日 下午5:27:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月25日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateByMac(IntelligentGateway intelligentGateway);

	/**
	 * 
	 * @Function: IntelligentGatewayMapper.java
	 * @Description: 根据网关mac 获取智能网关信息
	 *
	 * @param:gatewayMac 网关Mac
	 * @return：IntelligentGateway
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月5日 下午3:54:41
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月5日
	 *        Administrator v1.0.0 修改原因
	 */
	IntelligentGateway findByMac(@Param("gatewayMac") String gatewayMac);

	/**
	 * 
	 * @Function: IntelligentGatewayMapper.java
	 * @Description: 根据网关对象 模糊查询
	 *
	 * @param: intelligentGateway 智能网关
	 * @return：List<IntelligentGateway>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午5:24:12
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	List<IntelligentGateway> findByRackIdOrMacOrIsOnline(IntelligentGateway intelligentGateway);

	/**
	 * 
	 * @Function: IntelligentGatewayMapper.java
	 * @Description: 根据机柜id 获取智能网关信息
	 *
	 * @param: rackId 机柜id
	 * @return：List<IntelligentGateway>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月6日 下午4:54:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月6日
	 *        Administrator v1.0.0 修改原因
	 */
	List<IntelligentGateway> findByRackId(@Param("rackId") Integer rackId);

}