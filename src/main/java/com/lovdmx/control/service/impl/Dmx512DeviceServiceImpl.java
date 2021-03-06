package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.Dmx512DeviceMapper;
import com.lovdmx.control.pojo.Dmx512Device;
import com.lovdmx.control.service.Dmx512DeviceService;

@Service("dmx512DeviceService")
public class Dmx512DeviceServiceImpl extends BaseServiceImpl<Dmx512Device> implements Dmx512DeviceService {

	@Autowired
	private Dmx512DeviceMapper dmx512DeviceMapper;

	@Override
	public Integer batchInsert(List<Dmx512Device> list) {
		// TODO Auto-generated method stub
		return dmx512DeviceMapper.batchInsert(list);
	}

	@Override
	public PageInfo<Dmx512Device> queryPageListByWhereRtrMac(Dmx512Device dmx512Device, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<Dmx512Device> list = this.dmx512DeviceMapper.findByDmx512Device(dmx512Device);

		PageInfo<Dmx512Device> pageInfo = new PageInfo<Dmx512Device>(list);
		return pageInfo;
	}

	@Override
	public Dmx512Device findByDmx512Mac(String dmx512Mac) {
		// TODO Auto-generated method stub
		return dmx512DeviceMapper.findByDmx512Mac(dmx512Mac);
	}

	@Override
	public List<Dmx512Device> findByRtrMac(String rtrMac) {
		// TODO Auto-generated method stub
		return dmx512DeviceMapper.findByRtrMac(rtrMac);
	}
}