package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.GatewayDeviceMapper;
import com.lovdmx.control.pojo.GatewayDevice;
import com.lovdmx.control.service.GatewayDeviceService;

@Service("gatewayDeviceService")
public class GatewayDeviceServiceImpl extends BaseServiceImpl<GatewayDevice> implements GatewayDeviceService {

	@Autowired
	private GatewayDeviceMapper gatewayDeviceMapper;

	@Override
	public GatewayDevice findByDeviceMac(String deviceMac) {
		// TODO Auto-generated method stub
		return gatewayDeviceMapper.findByDeviceMac(deviceMac);
	}

	@Override
	public List<GatewayDevice> findByGatewayMac(String gatewayMac) {
		// TODO Auto-generated method stub
		return gatewayDeviceMapper.findByGatewayMac(gatewayMac);
	}

}
