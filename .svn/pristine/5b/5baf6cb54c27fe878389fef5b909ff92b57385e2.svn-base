package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.SpriteEdlmxAndRackAndSpriteDeviceVo;
import com.lovdmx.control.mapper.SpriteEdlmxMapper;
import com.lovdmx.control.pojo.SpriteEdlmx;
import com.lovdmx.control.service.SpriteEdlmxService;

@Service("spriteEdlmxService")
public class SpriteEdlmxServiceImpl extends BaseServiceImpl<SpriteEdlmx> implements SpriteEdlmxService {

	@Autowired
	private SpriteEdlmxMapper spriteEdlmxMapper;

	@Override
	public List<SpriteEdlmx> findBySpriteMac(String spriteMac) {
		// TODO Auto-generated method stub
		return spriteEdlmxMapper.findBySpriteMac(spriteMac);
	}

	@Override
	public PageInfo<SpriteEdlmxAndRackAndSpriteDeviceVo> queryPageListByWhereByRackIdAndSpriteMac(SpriteEdlmxAndRackAndSpriteDeviceVo spriteEdlmxInfo,
			Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<SpriteEdlmxAndRackAndSpriteDeviceVo> list = this.spriteEdlmxMapper
				.findSpriteEdlmxJoinSpriteDeviceJoinRackDeviceByRackIdAndSpriteMac(spriteEdlmxInfo);

		PageInfo<SpriteEdlmxAndRackAndSpriteDeviceVo> pageInfo = new PageInfo<SpriteEdlmxAndRackAndSpriteDeviceVo>(
				list);
		return pageInfo;
	}
}