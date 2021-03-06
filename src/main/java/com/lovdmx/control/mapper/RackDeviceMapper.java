package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.RackDevice;

public interface RackDeviceMapper extends BaseMapper<RackDevice> {
	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 根据父类ID获取所有机架信息
	 *
	 * @param:parentId 父类ID
	 * @return：List<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月21日 下午4:44:47
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackDevice> findByParentId(@Param("parentId") Integer parentId);

	/**
	 * @Function: RackDeviceMapper.java
	 * @Description: 根据项目ID获取机架中所有设备信息
	 *
	 * @param: projectId 项目ID
	 * @return：List<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月25日 下午1:53:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月25日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackDevice> findRackDeviceAllInfoByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
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
	Integer batchInsert(@Param("list") List<RackDevice> list);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 统计机架数量
	 *
	 * @param: projectId 项目ID
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月13日 上午10:38:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月13日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer countByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 根据项目id，获取所有机架id（,）逗号隔开
	 *
	 * @param: projectId 项目ID
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月19日 下午3:14:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月19日
	 *        Administrator v1.0.0 修改原因
	 */
	String findGroupCounatRackIdByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 根据项目id和RTR MAC 地址获取 机架信息
	 *
	 * @param:projectId 项目ID
	 * @param: mac RTR MAC地址
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月25日 上午11:31:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月25日
	 *        Administrator v1.0.0 修改原因
	 */
	RackDevice findByAndProjectIdRtrMac(@Param("projectId") Integer projectId, @Param("rtrMac") String rtrMac);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 根据项目id和SpriteMAC 地址获取 机架信息
	 *
	 * @param:projectId 项目ID
	 * @param: mac Sprite MAC地址
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月25日 上午11:31:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月25日
	 *        Administrator v1.0.0 修改原因
	 */
	RackDevice findByAndProjectIdSpriteMac(@Param("projectId") Integer projectId, @Param("spriteMac") String spriteMac);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 模糊查询 机柜信息
	 *
	 * @param: rackDevice 机架信息
	 * @return：List<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午4:04:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackDevice> findByRackNameOrRackDeviceOrProjectId(RackDevice rackDevice);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 根据项目id和 机架下标 获取信息
	 *
	 * @param: projectId 项目id
	 * @param: rackIndex 机架下标
	 * @return：RackDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月25日 下午5:41:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月25日
	 *        Administrator v1.0.0 修改原因
	 */
	RackDevice findByProjectIdAndRackIndex(@Param("projectId") Integer projectId,
			@Param("rackIndex") Integer rackIndex);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 根据项目id和 机架名 获取信息
	 *
	 * @param: projectId 项目id
	 * @param: rackName 机架名
	 * @return：RackDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月25日 下午5:41:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月25日
	 *        Administrator v1.0.0 修改原因
	 */
	RackDevice findByProjectIdAndRackName(@Param("projectId") Integer projectId, @Param("rackName") String rackName);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 批量删除
	 *
	 * @param: arrIds 根据数组id删除
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月27日 下午3:56:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(@Param("list") Integer[] arrIds);

	/**
	 * 
	 * @Function: RackDeviceMapper.java
	 * @Description: 机柜和监控连接 获取 信息
	 *
	 * @param: projectId 项目id
	 * @return：List<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午3:53:15
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackDevice> findRackDeviceJoinMonitoringDeviceByProjectId(@Param("projectId") Integer projectId);

}