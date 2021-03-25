package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.WeekMapper;
import com.lovdmx.control.pojo.Week;
import com.lovdmx.control.service.WeekService;

@Service("weekService")
public class WeekServiceImpl extends BaseServiceImpl<Week> implements WeekService {

	@Autowired
	private WeekMapper weekMapper;

	@Override
	public List<Week> findRelayNotExistsWeekByRelayId(Integer relayId) {
		// TODO Auto-generated method stub
		return weekMapper.findRelayNotExistsWeekByRelayId(relayId);
	}

}
