package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.IntelligentGateway;

public interface IntelligentGatewayService extends BaseService<IntelligentGateway> {

	/**
	 * 
	 * @Function: IntelligentGatewayService.java
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
	List<IntelligentGateway> findRackJoinIntelligentGatewayByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: IntelligentGatewayService.java
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
	 * @Function: IntelligentGatewayService.java
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
	IntelligentGateway findByMac(String gatewayMac);

	/**
	 * 
	 * @Function: IntelligentGatewayService.java
	 * @Description: 模糊查询 智能网关 分页
	 *
	 * @param: intelligentGateway 智能网关
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<RtrDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 下午5:14:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<IntelligentGateway> queryPageListByWhereRackIdOrMacOrIsOnline(IntelligentGateway intelligentGateway,
			Integer pageNo, Integer rows);

	/**
	 * 
	 * @Function: IntelligentGatewayService.java
	 * @Description: 根据 机柜id 获取智能网关信息
	 *
	 * @param: rackId 机柜id
	 * @return：List<IntelligentGateway>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月6日 下午4:53:16
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月6日
	 *        Administrator v1.0.0 修改原因
	 */
	List<IntelligentGateway> findByRackId(Integer rackId);
}
