package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Relay;

public interface RelayMapper extends BaseMapper<Relay> {

	/**
	 * 
	 * @Function: RelayMapper.java
	 * @Description: 根据网关MAC 获取 所有继电器信息
	 *
	 * @param:gatewayMac 网关MAC
	 * @return：List<Relay>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午11:00:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Relay> findByGatewayMac(@Param("gatewayMac") String gatewayMac);

	/**
	 * 
	 * @Function: RelayMapper.java
	 * @Description: 根据网关MAC 分组 rs485toNetIp
	 *
	 * @param:gatewayMac 网关MAC
	 * @return：List<Relay>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月14日 上午11:00:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月14日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Relay> findGrouprs485toNetIpBygatewayMac(@Param("gatewayMac") String gatewayMac);

	/**
	 * 
	 * @Function: RelayMapper.java
	 * @Description: 根据rs485toNetIp 获取 节点下的所有继电器
	 *
	 * @param: gatewayMac 网关MAC
	 * @param: rs485toNetIp rs485toNet的IP地址
	 * @return：List<Relay>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月16日 上午11:21:30
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月16日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Relay> findByGatewayMacAndRs485toNetIp(@Param("gatewayMac") String gatewayMac,
			@Param("rs485toNetIp") String rs485toNetIp);

	/**
	 * 
	 * @Function: RelayMapper.java
	 * @Description: 根据 条件查询
	 *
	 * @param: relay 继电器对象
	 * @return：List<Relay>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月6日 下午5:29:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月6日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Relay> findByRelay(Relay relay);

	/**
	 * 
	 * @Function: RelayMapper.java
	 * @Description: 根据网关MAC 和 485IP 删除继电器信息
	 *
	 * @param: gatewayMac 网关MAC
	 * @param: rs485toNetIp 485Ip
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月12日 上午11:10:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月12日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer deleteByGatewayMacAndRs485toNetIp(@Param("gatewayMac") String gatewayMac,
			@Param("rs485toNetIp") String rs485toNetIp);

}