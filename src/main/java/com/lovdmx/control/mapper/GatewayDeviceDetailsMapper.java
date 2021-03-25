package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.excelExprot.gatewayDevice.ExcelExportGatewayDeviceVo;
import com.lovdmx.control.pojo.GatewayDeviceDetails;

public interface GatewayDeviceDetailsMapper extends BaseMapper<GatewayDeviceDetails> {

	/**
	 * 
	 * @Function: GatewayDeviceDetailsMapper.java
	 * @Description: 根据设备MAC和 当天时间 获取所有网关设备信息
	 *
	 * @param: deviceMac 设备MAC
	 * @param: today 今天
	 * @return：GatewayDeviceDetails
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午4:49:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	GatewayDeviceDetails findByDeviceMacAndToday(@Param("deviceMac") String deviceMac,
			@Param("deviceMac") String today);

	/**
	 * 
	 * @Function: GatewayDeviceDetailsMapper.java
	 * @Description:获取 小于今天的所有网关设备信息 (日期分组)
	 *
	 * @param: today 当天
	 * @return：List<ExcelExportGatewayDeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月2日 上午10:58:22
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月2日
	 *        Administrator v1.0.0 修改原因
	 */
	List<ExcelExportGatewayDeviceVo> findLesstodayByToday(@Param("today") String today);

	/**
	 * 
	 * @Function: GatewayDeviceDetailsMapper.java
	 * @Description:刪除 小于今天的所有网关设备信息
	 *
	 * @param: today 当天
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月2日 上午10:32:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月20日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer deleteLesstodayByToday(@Param("today") String today);
}