package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.RtrDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.RtrDevice;

public interface RtrDeviceService extends BaseService<RtrDevice> {

	/**
	 * 
	 * @Function: RtrDeviceService.java
	 * @Description: 根据父类ID获取所有RTR服务器信息
	 *
	 * @param:parentId 父类ID
	 * @return：List<RtrDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月21日 下午4:52:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RtrDevice> findByParentId(Integer parentId);

	/**
	 * 
	 * @Function: RtrDeviceService.java
	 * @Description: 批量插入
	 *
	 * @param: list 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午2:16:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月28日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchInsert(List<RtrDevice> list);

	/**
	 * 
	 * @Function: RtrDeviceService.java
	 * @Description: 根据机架id获取所有RTR设备信息
	 *
	 * @param:rackId 机架ID
	 * @return：List<RtrDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月8日 上午11:13:51
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月8日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RtrDevice> findByRackId(Integer rackId);

	/**
	 * 
	 * @Function: RtrDeviceService.java
	 * @Description: 模糊查询 RTR设备信息 分页
	 *
	 * @param: rtrDeviceInfo 设备信息
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<RtrDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月1日 上午10:49:20
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月1日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<RtrDeviceJoinRackDeviceVo> queryPageListByWhereOrRtrMacOrModeIdOrIsOnlineOrRackId(RtrDeviceJoinRackDeviceVo rtrDeviceInfo,
			Integer pageNo, Integer rows);

	/**
	 * 
	 * @Function: RtrDeviceService.java
	 * @Description: 根据mac 查询rtr信息
	 *
	 * @param:rtrMac RTR MAC地址
	 * @return：RtrDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午3:38:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	RtrDevice findByMac(String rtrMac);
}