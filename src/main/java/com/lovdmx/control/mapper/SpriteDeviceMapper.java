package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.SpriteDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.SpriteDevice;

public interface SpriteDeviceMapper extends BaseMapper<SpriteDevice> {

	/**
	 * 
	 * @Function: SpriteDeviceMapper.java
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
	Integer batchInsert(@Param("list") List<SpriteDevice> list);

	/**
	 * 
	 * @Function: SpriteDeviceService.java
	 * @Description: 根据在线状态 获取数量
	 *
	 * @param: status 状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月24日 下午4:39:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月24日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer findNumberByProjectIdAndIsOnlineStatus(@Param("status") Integer status,
			@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: SpriteDeviceMapper.java
	 * @Description: 根据机架ID获取录放精灵设备信息
	 *
	 * @param:rackId 机架ID
	 * @return：List<SpriteDevice>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月8日 上午11:14:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月8日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SpriteDevice> findRackId(@Param("rackId") Integer rackId);

	/**
	 * 
	 * @Function: SpriteDeviceMapper.java
	 * @Description: 根据Mac 获取Sprite信息
	 *
	 * @param:spriteMac 录放精灵MAC
	 * @return：SpriteDevice
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月3日 下午5:37:27
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月3日
	 *        Administrator v1.0.0 修改原因
	 */
	SpriteDevice findByMac(@Param("spriteMac") String spriteMac);

	/**
	 * 
	 * @Function: SpriteDeviceMapper.java
	 * @Description: 根据sprite设备信息模糊查询
	 *
	 * @param: spriteDeviceInfo 设备对象
	 * @return：List<SpriteDeviceJoinRackDeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 下午4:19:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SpriteDeviceJoinRackDeviceVo> findSpriteDeviceJoinRackDeviceByOrSpriteMacOrModeIdOrIsOnlineOrRackId(
			SpriteDeviceJoinRackDeviceVo spriteDeviceInfo);
}