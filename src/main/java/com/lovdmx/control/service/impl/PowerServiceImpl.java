package com.lovdmx.control.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.PowerMapper;
import com.lovdmx.control.pojo.Power;
import com.lovdmx.control.service.PowerService;

@Service("powerService")
public class PowerServiceImpl extends BaseServiceImpl<Power> implements PowerService {

	@Autowired
	private PowerMapper powerMapper;
	
	@Override
	public String findByRoleLimit(String roleLimit) {
		// TODO Auto-generated method stub
		return powerMapper.findByRoleLimit(roleLimit);
	}

}
