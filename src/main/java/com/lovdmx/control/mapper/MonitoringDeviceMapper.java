package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.MonitoringDevice;

public interface MonitoringDeviceMapper extends BaseMapper<MonitoringDevice> {

	/**
	 * 
	 * @Function: MonitoringDeviceMapper.java
	 * @Description: MonitoringDevice Join RackDevice By (Or rackId) 表连接条件查询
	 *
	 * @param: monitoringDevice 监控信息
	 * @return：List<MonitoringDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 上午11:24:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	List<MonitoringDevice> findMonitoringDeviceJoinRackDeviceByOrRackId(MonitoringDevice monitoringDevice);

	/**
	 * 
	 * @Function: MonitoringDeviceMapper.java
	 * @Description: 批量删除
	 *
	 * @param: arrIds 数组id
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午3:17:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(@Param("list") Integer[] arrIds);

	/**
	 * 
	 * @Function: MonitoringDeviceMapper.java
	 * @Description: 根据 序列号获取 信息
	 *
	 * @param: deviceSerial 序列号
	 * @return：MonitoringDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:31:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	MonitoringDevice findByDeviceSerail(@Param("deviceSerial") String deviceSerial);
}