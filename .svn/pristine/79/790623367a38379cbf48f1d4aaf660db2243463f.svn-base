package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.SpriteDeviceJoinRackDeviceVo;
import com.lovdmx.control.pojo.SpriteDevice;

public interface SpriteDeviceService extends BaseService<SpriteDevice> {

	/**
	 * 
	 * @Function: SpriteDeviceService.java
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
	Integer batchInsert(List<SpriteDevice> list);

	/**
	 * 
	 * @Function: SpriteDeviceService.java
	 * @Description: 根据项目ID和在线状态 获取数量
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
	Integer findNumberByProjectIdAndIsOnlineStatus(Integer status, Integer projectId);

	/**
	 * 
	 * @Function: SpriteDeviceService.java
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
	List<SpriteDevice> findRackId(Integer rackId);

	/**
	 * 
	 * @Function: SpriteDeviceService.java
	 * @Description: 模糊查询 Sprite设备信息 分页
	 *
	 * @param:spriteDeviceInfo 设备信息
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<SpriteDeviceJoinRackDeviceVo>
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
	PageInfo<SpriteDeviceJoinRackDeviceVo> queryPageListByWhereOrSpriteMacOrIsOnlineOrRackId(
			SpriteDeviceJoinRackDeviceVo spriteDeviceInfo, Integer pageNo, Integer rows);

	/**
	 * 
	 * @Function: SpriteDeviceService.java
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
	SpriteDevice findByMac(String spriteMac);
}