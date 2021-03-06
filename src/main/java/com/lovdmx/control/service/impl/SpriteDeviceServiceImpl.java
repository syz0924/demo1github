package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.SpriteDeviceJoinRackDeviceVo;
import com.lovdmx.control.mapper.SpriteDeviceMapper;
import com.lovdmx.control.pojo.SpriteDevice;
import com.lovdmx.control.service.SpriteDeviceService;

@Service("spriteDeviceService")
public class SpriteDeviceServiceImpl extends BaseServiceImpl<SpriteDevice> implements SpriteDeviceService {

	@Autowired
	private SpriteDeviceMapper spriteDeviceMapper;

	@Override
	public Integer batchInsert(List<SpriteDevice> list) {
		// TODO Auto-generated method stub
		return spriteDeviceMapper.batchInsert(list);
	}

	@Override
	public Integer findNumberByProjectIdAndIsOnlineStatus(Integer status, Integer projectId) {
		// TODO Auto-generated method stub
		return spriteDeviceMapper.findNumberByProjectIdAndIsOnlineStatus(status, projectId);
	}

	@Override
	public List<SpriteDevice> findRackId(Integer rackId) {
		// TODO Auto-generated method stub
		return spriteDeviceMapper.findRackId(rackId);
	}

	@Override
	public PageInfo<SpriteDeviceJoinRackDeviceVo> queryPageListByWhereOrSpriteMacOrIsOnlineOrRackId(
			SpriteDeviceJoinRackDeviceVo spriteDeviceInfo, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<SpriteDeviceJoinRackDeviceVo> list = this.spriteDeviceMapper
				.findSpriteDeviceJoinRackDeviceByOrSpriteMacOrModeIdOrIsOnlineOrRackId(spriteDeviceInfo);

		PageInfo<SpriteDeviceJoinRackDeviceVo> pageInfo = new PageInfo<SpriteDeviceJoinRackDeviceVo>(list);
		return pageInfo;
	}

	@Override
	public SpriteDevice findByMac(String spriteMac) {
		// TODO Auto-generated method stub
		return spriteDeviceMapper.findByMac(spriteMac);
	}
}