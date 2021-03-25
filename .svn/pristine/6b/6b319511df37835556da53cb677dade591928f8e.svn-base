package com.lovdmx.control.mapper;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Power;

public interface PowerMapper extends BaseMapper<Power> {

	/**
	 * 
	 * @Function: PowerMapper.java
	 * @Description: 根据权限id 获取权限功能名称
	 *
	 * @param:roleLimit 权限id
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月20日 下午3:39:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月20日
	 *        Administrator v1.0.0 修改原因
	 */
	String findByRoleLimit(@Param("roleLimit") String roleLimit);
}