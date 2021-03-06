package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.VideoFile;
import com.lovdmx.control.pojo.UploadVideos;

public interface UploadVideosService extends BaseService<UploadVideos> {

	/**
	 * @Function: UploadVideosService.java
	 * @Description: 根据md5值查询
	 *
	 * @param: md5:文件的md5值
	 * @return：UploadVideos
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月22日 下午4:42:12
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年2月22日 Administrator v1.0.0 修改原因
	 */
	UploadVideos findByMd5(String md5);

	/**
	 * @Function: UploadVideosService.java
	 * @Description: 根据项目ID和上传角色查询 最后一条上传信息
	 *
	 * @param:projectId 项目ID
	 * @param: uploadRole
	 *             上传角色
	 * @return：UploadVideos
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 上午11:14:17
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年2月28日 Administrator v1.0.0 修改原因
	 */
	UploadVideos findWebUploadLastInfoByProjectIdAndUploadRole(Integer projectId, Integer uploadRole);

	/**
	 * @Function: UploadVideosService.java
	 * @Description: 根据项目ID获取所有已上传视频信息
	 *
	 * @param:projectId 项目ID
	 * @return：List<UploadVideos>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月1日 下午1:59:10
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月1日 Administrator v1.0.0 修改原因
	 */
	List<UploadVideos> findByProjectId(Integer projectId);

	/**
	 * @Function: UploadVideosService.java
	 * @Description: 根据数组ID,获取没有分割的视频信息
	 *
	 * @param: arrayId
	 *             已上传的视频id
	 * @return：List<UploadVideos>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月20日 上午9:59:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月20日 Administrator v1.0.0 修改原因
	 */
	List<UploadVideos> findNotSpiltByIds(String[] arrayId);

	/**
	 * @Function: UploadVideosService.java
	 * @Description: 根据数组ID,获取以分割的视频信息
	 *
	 * @param: arrayId
	 *             已上传的视频id
	 * @return：List<UploadVideos>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月20日 上午9:59:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月20日 Administrator v1.0.0 修改原因
	 */
	List<UploadVideos> findSpiltByIds(String[] arrayId);

	/**
	 * 
	 * @Function: UploadEdlmxService.java
	 * @Description: 根据父类ID和分发状态,获取所有视频文件总数
	 *
	 * @param:parentId 父类ID
	 * @param: status:分发状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午4:38:48
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月27日 Administrator v1.0.0 修改原因
	 */
	Integer findCountByProjectIdAndRtrLoaded(Integer projectId, Integer status);

	/**
	 * 
	 * @Function: UploadEdlmxService.java
	 * @Description: 根据项目ID查询
	 *
	 * @param:parentId 父类ID
	 * @return：List<VideoFile>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月15日 上午10:16:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年4月15日 Administrator v1.0.0 修改原因
	 */
	List<VideoFile> findByVideoProjectId(Integer projectId);

	/**
	 * 
	 * @Function: UploadVideosService.java
	 * @Description: 根据多个MD5获取 已上传视频信息
	 *
	 * @param:arryMd5 数组md5
	 * @return：List<UploadVideos>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月25日 下午5:17:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年4月25日 Administrator v1.0.0 修改原因
	 */
	List<UploadVideos> findByMd5s(String[] arryMd5);

	
	/**
	 * 
	 * @Function: UploadEdlmxService.java
	 * @Description:分页 根据上传状态或上传角色或项目id 条件查询
	 *
	 * @param: uploadVideo 上传视频文件对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：PageInfo<UploadVideos>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月15日 下午1:30:24
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月15日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<UploadVideos> queryPageListByWhereByRtrLoadedOrUploadRoleOrProjectId(UploadVideos uploadVideo, Integer pageNo, Integer rows);
}