package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.RoleMapper;
import com.lovdmx.control.pojo.Role;
import com.lovdmx.control.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> findRoleInfoAll() {
		// TODO Auto-generated method stub
		return roleMapper.findRoleInfoAll();
	}
}