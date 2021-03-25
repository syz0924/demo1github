package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.SpriteEdlmxAndRackAndSpriteDeviceVo;
import com.lovdmx.control.pojo.SpriteEdlmx;

public interface SpriteEdlmxMapper extends BaseMapper<SpriteEdlmx> {

	/**
	 * 
	 * @Function: SpriteEdlmxMapper.java
	 * @Description: 根据录放精灵Mac 获取灯光文件信息
	 *
	 * @param: spriteMac 录放精灵MAC
	 * @return：List<SpriteEdlmx>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午10:22:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SpriteEdlmx> findBySpriteMac(@Param("spriteMac") String spriteMac);

	/**
	 * 
	 * @Function: SpriteEdlmxMapper.java
	 * @Description: spriteEdlmx Join spriteDevice Join rackDevice 多表连接 by spriteMac
	 *               = 录放精灵MAC
	 *
	 * @param: spriteEdlmxInfo 灯光文件信息对象
	 * @return：List<SpriteEdlmxAndRackAndSpriteDeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午10:33:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SpriteEdlmxAndRackAndSpriteDeviceVo> findSpriteEdlmxJoinSpriteDeviceJoinRackDeviceByRackIdAndSpriteMac(
			SpriteEdlmxAndRackAndSpriteDeviceVo spriteEdlmxInfo);
}