package com.lovdmx.control.service;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.MonitoringDevice;

public interface MonitoringDeviceService extends BaseService<MonitoringDevice> {

	/**
	 * 
	 * @Function: MonitoringDeviceService.java
	 * @Description: monitoringDevice监控信息条件查询 分页
	 *
	 * @param:monitoringDevice 监控信息
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<MonitoringDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 上午10:49:20
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月1日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<MonitoringDevice> queryPageListByWhereOrRackId(MonitoringDevice monitoringDevice, Integer pageNo,
			Integer rows);

	/**
	 * 
	 * @Function: MonitoringDeviceService.java
	 * @Description: 批量删除
	 *
	 * @param: arrIds 数组id
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午3:17:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(Integer[] arrIds);

	/**
	 * 
	 * @Function: MonitoringDeviceService.java
	 * @Description: 根据设备序列号 获取信息
	 *
	 * @param: deviceSerial 序列号
	 * @return：MonitoringDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午4:30:31
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	MonitoringDevice findByDeviceSerail(String deviceSerial);

}
