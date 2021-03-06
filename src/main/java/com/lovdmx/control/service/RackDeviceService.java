package com.lovdmx.control.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.RackDevice;

public interface RackDeviceService extends BaseService<RackDevice> {

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 根据父类ID获取所有机架信息
	 *
	 * @param:parentId 父类ID
	 * @return：List<RackDevice>
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
	List<RackDevice> findByParentId(Integer parentId);

	/**
	 * 
	 * @Function: RackDeviceService.java
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
	Integer batchInsert(List<RackDevice> list);

	/**
	 * 
	 * /**
	 * 
	 * @Function: RackDeviceService.java
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
	Integer countByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: RackDeviceService.java
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
	 * @Description: 根据项目id和RTRMAC 地址获取 机架信息
	 *
	 * @param:projectId 项目ID
	 * @param: rtrMac RTR MAC地址
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
	RackDevice findByAndProjectIdRtrMac(Integer projectId, String rtrMac);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 根据项目id和SpriteMAC 地址获取 机架信息
	 *
	 * @param:projectId 项目ID
	 * @param: spriteMac Sprite MAC地址
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
	RackDevice findByAndProjectIdSpriteMac(Integer projectId, String spriteMac);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 模糊查询
	 *
	 * @param:rackDevice 机架信息
	 * @return：List<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午4:03:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	List<RackDevice> findByRackNameOrRackDeviceOrProjectId(RackDevice rackDevice);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 模糊查询机架信息 分页
	 *
	 * @param: rackDevice 机架信息
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<RackDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午4:35:42
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<RackDevice> queryPageListByWhereRackNameOrRackIdOrProjectId(RackDevice rackDevice, Integer pageNo,
			Integer rows);

	/**
	 * 
	 * @Function: RackDeviceService.java
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
	RackDevice findByProjectIdAndRackIndex(Integer projectId, Integer rackIndex);

	/**
	 * 
	 * @Function: RackDeviceService.java
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
	RackDevice findByProjectIdAndRackName(Integer projectId, String rackName);

	/**
	 * 
	 * @Function: RackDeviceService.java
	 * @Description: 批量删除
	 *
	 * @param: arrIds 根据数组id
	 * @return：arrIds
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月27日 下午3:55:33
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(Integer[] arrIds);

	/**
	 * 
	 * @Function: RackDeviceService.java
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
	List<RackDevice> findRackDeviceJoinMonitoringDeviceByProjectId(Integer projectId);

}