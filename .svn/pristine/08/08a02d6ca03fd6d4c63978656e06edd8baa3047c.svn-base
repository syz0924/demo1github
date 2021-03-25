package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.mapper.UploadVideosMapper;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.service.UploadVideosService;

@Service("uploadVideosService")
public class UploadVideosServiceImpl extends BaseServiceImpl<UploadVideos> implements UploadVideosService {

	@Autowired
	private UploadVideosMapper uploadVideosMapper;

	@Override
	public UploadVideos findByMd5(String md5) {
		return uploadVideosMapper.findByMd5(md5);
	}

	@Override
	public UploadVideos findWebUploadLastInfoByProjectIdAndUploadRole(Integer projectId, Integer uploadRole) {
		return uploadVideosMapper.findWebUploadLastInfoByProjectIdAndUploadRole(projectId, uploadRole);
	}

	@Override
	public List<UploadVideos> findByProjectId(Integer projectId) {
		return uploadVideosMapper.findByProjectId(projectId);
	}

	@Override
	public List<UploadVideos> findNotSpiltByIds(String[] arrayId) {
		return uploadVideosMapper.findNotSpiltByIds(arrayId);
	}

	@Override
	public List<UploadVideos> findSpiltByIds(String[] arrayId) {
		// TODO Auto-generated method stub
		return uploadVideosMapper.findSpiltByIds(arrayId);
	}

	@Override
	public Integer findCountByProjectIdAndRtrLoaded(Integer projectId, Integer status) {
		// TODO Auto-generated method stub
		return uploadVideosMapper.findCountByProjectIdAndRtrLoaded(projectId, status);
	}

	@Override
	public List<VideoFile> findByVideoProjectId(Integer projectId) {
		return uploadVideosMapper.findByVideoProjectId(projectId);
	}

	@Override
	public List<UploadVideos> findByMd5s(String[] arryMd5) {
		// TODO Auto-generated method stub
		return uploadVideosMapper.findByMd5s(arryMd5);
	}

	@Override
	public PageInfo<UploadVideos> queryPageListByWhereByRtrLoadedOrUploadRoleOrProjectId(UploadVideos uploadVideos, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<UploadVideos> list = this.uploadVideosMapper.findByOrRtrLoadedOrUploadRoleOrProjectId(uploadVideos);

		PageInfo<UploadVideos> pageInfo = new PageInfo<UploadVideos>(list);
		return pageInfo;
	}
}