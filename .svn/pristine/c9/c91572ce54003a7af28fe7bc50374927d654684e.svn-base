package com.lovdmx.control.service;

import java.util.List;

import com.lovdmx.control.pojo.GatewayDevice;

public interface GatewayDeviceService extends BaseService<GatewayDevice> {

	/**
	 * 
	 * @Function: GatewayDeviceService.java
	 * @Description: 根据设备Mac 获取网关设备信息
	 *
	 * @param:deviceMac
	 * @return：GatewayDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午4:34:06
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	GatewayDevice findByDeviceMac(String deviceMac);

	/**
	 * 
	 * @Function: GatewayDeviceService.java
	 * @Description: 根据网关Mac 获取所有网关设备信息
	 *
	 * @param:gatewayMac 网关Mac
	 * @return：List<GatewayDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午4:35:22
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	List<GatewayDevice> findByGatewayMac(String gatewayMac);

}
