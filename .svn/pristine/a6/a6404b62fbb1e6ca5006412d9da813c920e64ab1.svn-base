package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.MonitoringDeviceMapper;
import com.lovdmx.control.pojo.MonitoringDevice;
import com.lovdmx.control.service.MonitoringDeviceService;

@Service("monitoringDeviceService")
public class MonitoringDeviceServiceImpl extends BaseServiceImpl<MonitoringDevice> implements MonitoringDeviceService {

	@Autowired
	private MonitoringDeviceMapper monitoringDeviceMapper;

	@Override
	public PageInfo<MonitoringDevice> queryPageListByWhereOrRackId(MonitoringDevice monitoringDevice, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<MonitoringDevice> list = this.monitoringDeviceMapper
				.findMonitoringDeviceJoinRackDeviceByOrRackId(monitoringDevice);

		PageInfo<MonitoringDevice> pageInfo = new PageInfo<MonitoringDevice>(list);
		return pageInfo;
	}

	@Override
	public Integer batchDelete(Integer[] arrIds) {
		return monitoringDeviceMapper.batchDelete(arrIds);
	}

	@Override
	public MonitoringDevice findByDeviceSerail(String deviceSerial) {
		// TODO Auto-generated method stub
		return monitoringDeviceMapper.findByDeviceSerail(deviceSerial);
	}

}
