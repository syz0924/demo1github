package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.RelayMapper;
import com.lovdmx.control.pojo.Relay;
import com.lovdmx.control.service.RelayService;

@Service("relayService")
public class RelayServiceImpl extends BaseServiceImpl<Relay> implements RelayService {

	@Autowired
	private RelayMapper relayMapper;

	@Override
	public List<Relay> findByGatewayMac(String gatewayMac) {
		// TODO Auto-generated method stub
		return relayMapper.findByGatewayMac(gatewayMac);
	}

	@Override
	public List<Relay> findGrouprs485toNetIpBygatewayMac(String gatewayMac) {
		// TODO Auto-generated method stub
		return relayMapper.findGrouprs485toNetIpBygatewayMac(gatewayMac);
	}

	@Override
	public List<Relay> findByGatewayMacAndRs485toNetIp(String gatewayMac, String rs485toNetIp) {
		// TODO Auto-generated method stub
		return relayMapper.findByGatewayMacAndRs485toNetIp(gatewayMac, rs485toNetIp);
	}

	@Override
	public PageInfo<Relay> queryPageListByWhereRelay(Relay relay, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<Relay> list = this.relayMapper.findByRelay(relay);

		PageInfo<Relay> pageInfo = new PageInfo<Relay>(list);
		return pageInfo;
	}

	@Override
	public List<Relay> findByRelay(Relay relay) {
		// TODO Auto-generated method stub
		return relayMapper.findByRelay(relay);
	}

	@Override
	public Integer deleteByGatewayMacAndRs485toNetIp(String gatewayMac, String rs485toNetIp) {
		// TODO Auto-generated method stub
		return relayMapper.deleteByGatewayMacAndRs485toNetIp(gatewayMac, rs485toNetIp);
	}
}
