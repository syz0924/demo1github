package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.RtrVideo;

public interface RtrVideoMapper extends BaseMapper<RtrVideo> {

	/**
	 * @Function: RtrVideoMapper.java
	 * @Description: 批量添加
	 *
	 * @param:rtrVideoList RTR视频文件集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月8日 上午10:44:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月8日 Administrator v1.0.0 修改原因
	 */
	Integer batchInsert(@Param("list") List<RtrVideo> rtrVideoList);
}