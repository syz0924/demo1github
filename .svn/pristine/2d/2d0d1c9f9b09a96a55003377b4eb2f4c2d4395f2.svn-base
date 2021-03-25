package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.SpriteEdlmxAndRackAndSpriteDeviceVo;
import com.lovdmx.control.pojo.SpriteEdlmx;

public interface SpriteEdlmxService extends BaseService<SpriteEdlmx> {

	/**
	 * 
	 * @Function: SpriteEdlmxService.java
	 * @Description: 根据录放精灵Mac 获取灯光文件信息
	 *
	 * @param: spriteMac 录放精灵Mac
	 * @return：List<SpriteEdlmx>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午10:21:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SpriteEdlmx> findBySpriteMac(String spriteMac);

	/**
	 * 
	 * @Function: SpriteEdlmxService.java
	 * @Description: 分页 根据条件查询
	 *
	 * @param: spriteEdlmxInfo 灯光文件信息对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：PageInfo<SpriteEdlmxAndRackAndSpriteDeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月17日 上午10:28:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月17日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<SpriteEdlmxAndRackAndSpriteDeviceVo> queryPageListByWhereByRackIdAndSpriteMac(
			SpriteEdlmxAndRackAndSpriteDeviceVo spriteEdlmxInfo, Integer pageNo, Integer rows);
}