package com.lovdmx.control.mapper;

import java.util.List;

import com.lovdmx.control.pojo.Role;

public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 
	 * @Function: RoleMapper.java
	 * @Description: 获取角色所有信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月20日 下午3:55:38
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月20日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Role> findRoleInfoAll();

}