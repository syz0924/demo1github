package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.RtrDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.RtrDevice;

public interface RtrDeviceMapper extends BaseMapper<RtrDevice> {

	/**
	 * 
	 * @Function: RtrDeviceMapper.java
	 * @Description: 根据父类ID获取所有RTR服务器信息
	 *
	 * @param:parentId 父类ID
	 * @return：List<RtrDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月21日 下午4:46:23
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RtrDevice> findByParentId(@Param("parentId") Integer parentId);

	/**
	 * 
	 * @Function: RtrDeviceMapper.java
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
	Integer batchInsert(@Param("list") List<RtrDevice> list);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
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
	List<RtrDevice> findByRackId(@Param("rackId") Integer rackId);

	/**
	 * 
	 * @Function: RtrDeviceMapper.java
	 * @Description: 根据mac查询RTR设备信息
	 *
	 * @param:rtrMac RTR MAC地址
	 * @return：RtrDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午3:39:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	RtrDevice findByMac(@Param("rtrMac") String rtrMac);

	/**
	 * 
	 * @Function: RtrDeviceMapper.java
	 * @Description: 根据rtr设备信息模糊查询
	 *
	 * @param: RtrDeviceJoinRackDeviceVo 设备对象
	 * @return：List<RtrDeviceJoinRackDeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 下午3:32:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RtrDeviceJoinRackDeviceVo> findRtrDeviceJoinRackDeviceByOrRtrMacOrModeIdOrIsOnlineOrRackId(
			RtrDeviceJoinRackDeviceVo rtrDeviceInfo);
}