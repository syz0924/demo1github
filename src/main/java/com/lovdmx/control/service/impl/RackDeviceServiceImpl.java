package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.RackDeviceMapper;
import com.lovdmx.control.pojo.RackDevice;
import com.lovdmx.control.service.RackDeviceService;

@Service("rackDeviceService")
public class RackDeviceServiceImpl extends BaseServiceImpl<RackDevice> implements RackDeviceService {
	@Autowired
	private RackDeviceMapper rackDeviceMapper;

	@Override
	public List<RackDevice> findByParentId(Integer parentId) {
		return rackDeviceMapper.findByParentId(parentId);
	}

	@Override
	public Integer batchInsert(List<RackDevice> list) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.batchInsert(list);
	}

	@Override
	public Integer countByProjectId(Integer projectId) {
		return rackDeviceMapper.countByProjectId(projectId);
	}

	@Override
	public String findGroupCounatRackIdByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findGroupCounatRackIdByProjectId(projectId);
	}

	@Override
	public RackDevice findByAndProjectIdRtrMac(Integer projectId, String rtrMac) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findByAndProjectIdRtrMac(projectId, rtrMac);
	}

	@Override
	public RackDevice findByAndProjectIdSpriteMac(Integer projectId, String spriteMac) {
		return rackDeviceMapper.findByAndProjectIdSpriteMac(projectId, spriteMac);
	}

	@Override
	public List<RackDevice> findByRackNameOrRackDeviceOrProjectId(RackDevice rackDevice) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findByRackNameOrRackDeviceOrProjectId(rackDevice);
	}

	@Override
	public PageInfo<RackDevice> queryPageListByWhereRackNameOrRackIdOrProjectId(RackDevice rackDevice, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<RackDevice> list = this.rackDeviceMapper.findByRackNameOrRackDeviceOrProjectId(rackDevice);

		PageInfo<RackDevice> pageInfo = new PageInfo<RackDevice>(list);
		return pageInfo;
	}

	@Override
	public RackDevice findByProjectIdAndRackIndex(Integer projectId, Integer rackIndex) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findByProjectIdAndRackIndex(projectId, rackIndex);
	}

	@Override
	public RackDevice findByProjectIdAndRackName(Integer projectId, String rackName) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findByProjectIdAndRackName(projectId, rackName);
	}

	@Override
	public Integer batchDelete(Integer[] arrIds) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.batchDelete(arrIds);
	}

	@Override
	public List<RackDevice> findRackDeviceJoinMonitoringDeviceByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return rackDeviceMapper.findRackDeviceJoinMonitoringDeviceByProjectId(projectId);
	}

}