package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.httpVo.excelExprot.dmx.ExcelExportDmx512DeviceVo;
import com.lovdmx.control.mapper.Dmx512DeviceDetailsMapper;
import com.lovdmx.control.pojo.Dmx512DeviceDetails;
import com.lovdmx.control.service.Dmx512DeviceDetailsService;

@Service("dmx512DeviceDetailsService")
public class Dmx512DeviceDetailsServiceImpl extends BaseServiceImpl<Dmx512DeviceDetails>
		implements Dmx512DeviceDetailsService {

	@Autowired
	private Dmx512DeviceDetailsMapper dmx512DeviceDetailsMapper;

	@Override
	public Dmx512DeviceDetails findByDmx512MacAndToday(String dmx512Mac, String today) {
		// TODO Auto-generated method stub
		return dmx512DeviceDetailsMapper.findByDmx512MacAndToday(dmx512Mac, today);
	}

	@Override
	public List<ExcelExportDmx512DeviceVo> findLesstodayByToday(String today) {
		// TODO Auto-generated method stub
		return dmx512DeviceDetailsMapper.findLesstodayByToday(today);
	}

	@Override
	public Integer deleteLesstodayByToday(String today) {
		// TODO Auto-generated method stub
		return dmx512DeviceDetailsMapper.deleteLesstodayByToday(today);
	}

}
