package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.httpVo.excelExprot.gatewayDevice.ExcelExportGatewayDeviceVo;
import com.lovdmx.control.mapper.GatewayDeviceDetailsMapper;
import com.lovdmx.control.pojo.GatewayDeviceDetails;
import com.lovdmx.control.service.GatewayDeviceDetailsService;

@Service("gatewayDeviceDetailsService")
public class GatewayDeviceDetailsServiceImpl extends BaseServiceImpl<GatewayDeviceDetails>
		implements GatewayDeviceDetailsService {

	@Autowired
	private GatewayDeviceDetailsMapper gatewayDeviceDetailsMapper;

	@Override
	public GatewayDeviceDetails findByDeviceMacAndToday(String deviceMac, String today) {
		// TODO Auto-generated method stub
		return gatewayDeviceDetailsMapper.findByDeviceMacAndToday(deviceMac, today);
	}

	@Override
	public List<ExcelExportGatewayDeviceVo> findLesstodayByToday(String today) {
		// TODO Auto-generated method stub
		return gatewayDeviceDetailsMapper.findLesstodayByToday(today);
	}

	@Override
	public Integer deleteLesstodayByToday(String today) {
		// TODO Auto-generated method stub
		return gatewayDeviceDetailsMapper.deleteLesstodayByToday(today);
	}


}
