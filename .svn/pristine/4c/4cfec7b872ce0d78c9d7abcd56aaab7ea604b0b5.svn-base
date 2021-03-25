package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.RtrDeviceJoinRackDeviceVo;
import com.lovdmx.control.mapper.RtrDeviceMapper;
import com.lovdmx.control.pojo.RtrDevice;
import com.lovdmx.control.service.RtrDeviceService;

@Service("rtrDeviceService")
public class RtrDeviceServiceImpl extends BaseServiceImpl<RtrDevice> implements RtrDeviceService {

	@Autowired
	private RtrDeviceMapper rtrDeviceMapper;

	@Override
	public List<RtrDevice> findByParentId(Integer parentId) {
		return rtrDeviceMapper.findByParentId(parentId);
	}

	@Override
	public Integer batchInsert(List<RtrDevice> list) {
		// TODO Auto-generated method stub
		return rtrDeviceMapper.batchInsert(list);
	}

	@Override
	public List<RtrDevice> findByRackId(Integer rackId) {
		// TODO Auto-generated method stub
		return rtrDeviceMapper.findByRackId(rackId);
	}

	@Override
	public PageInfo<RtrDeviceJoinRackDeviceVo> queryPageListByWhereOrRtrMacOrModeIdOrIsOnlineOrRackId(
			RtrDeviceJoinRackDeviceVo rtrDeviceInfo, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<RtrDeviceJoinRackDeviceVo> list = this.rtrDeviceMapper
				.findRtrDeviceJoinRackDeviceByOrRtrMacOrModeIdOrIsOnlineOrRackId(rtrDeviceInfo);

		PageInfo<RtrDeviceJoinRackDeviceVo> pageInfo = new PageInfo<RtrDeviceJoinRackDeviceVo>(list);
		return pageInfo;
	}

	@Override
	public RtrDevice findByMac(String rtrMac) {
		// TODO Auto-generated method stub
		return rtrDeviceMapper.findByMac(rtrMac);
	}
}