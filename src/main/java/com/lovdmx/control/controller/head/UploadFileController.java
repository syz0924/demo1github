/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.controller.head 
 * @author: syz  
 * @date: 2019年2月25日 下午3:50:28 
 */
package com.lovdmx.control.controller.head;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcraft.jsch.SftpException;
import com.lovdmx.control.common.utils.SFTPUtil;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.UploadStatus;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: UploadFileController.java
 * @Description: 文件上传控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月25日 下午3:50:28
 *
 */
@Controller
@RequestMapping("/uploadFile/")
@CrossOrigin
public class UploadFileController extends BaseController {

	@Autowired
	private UploadVideosService uploadVideosService;

	/**
	 * 
	 * @Function: UploadFileController.java
	 * @Description: web上传视频文件
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 上午11:41:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月28日
	 *        Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam(value = "videoFile", required = false) CommonsMultipartFile videoFile,
			Integer minute, HttpSession session) {
		// 关闭事务自动提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);

		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 组装新增的文件路径
		StringBuilder addFilePaths = new StringBuilder();
		// 反馈状态
		String result = "";
		// sftp对象
		SFTPUtil sftp = null;
		// 文件名
		String newFileNameVideo = "";

		try {
			// 文件的MD5值
			String md5 = DigestUtils.md5Hex(videoFile.getInputStream());
			// 判断文件是否上传过
			UploadVideos video = uploadVideosService.findByMd5(md5);
			// 初始化sftp
			sftp = new SFTPUtil();
			// 认证
			sftp.login();
			// 上传文件名
			String originFileNameVideo = videoFile.getOriginalFilename();
			// 新文件名
			newFileNameVideo = UUID.randomUUID() + originFileNameVideo.substring(originFileNameVideo.lastIndexOf("."));
			if (video == null) {
				// 上传文件
				sftp.upload(SFTPUtil.basePath, SFTPUtil.videoDirectory, newFileNameVideo, videoFile.getInputStream());
				// 保存上传文件名
				addFilePaths.append(newFileNameVideo);
				// 获取web上传的最后一条信息
				UploadVideos lastUploadVideo = uploadVideosService
						.findWebUploadLastInfoByProjectIdAndUploadRole(account.getProjectId(), 0);
				int index = 1;
				if (lastUploadVideo == null) {
					index = 101;
				} else {
					// 下标加一
					index += lastUploadVideo.getFileIndex();
				}
				// 构造函数赋值
				UploadVideos uploadVideo = new UploadVideos(SFTPUtil.webBasePath + SFTPUtil.videoDirectory + newFileNameVideo,
						originFileNameVideo, account.getProjectId(), 0, index, md5, minute, 0);
				// 保存数据
				uploadVideosService.save(uploadVideo);
				result = "succeed";
			} else {
				// 文件目录
				String filePath = video.getFilePath();
				// 获取文件信息
				try {
					// 抛出异常 则该文件 不存在, 重新添加文件
					sftp.getFileInfo(filePath);
					result = "already exists";
				} catch (SftpException sftpex) {
					// 上传文件
					sftp.upload(SFTPUtil.basePath, SFTPUtil.videoDirectory, newFileNameVideo, videoFile.getInputStream());
					// 保存上传文件名
					addFilePaths.append(newFileNameVideo);
					video.setFilePath(SFTPUtil.basePath + SFTPUtil.videoDirectory + newFileNameVideo);
					// 修改文件路径
					uploadVideosService.update(video);
					result = "succeed";
				}
			}
			Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(), "上传视频",
					new Date(), new Date());
			// 添加操作日志
			addlogInfo(log);
			platformTransactionManager.commit(status);
			// 逻辑代码，可以写上你的逻辑处理代码
		} catch (Exception ex) {
			ex.printStackTrace();
			platformTransactionManager.rollback(status);
			result = "fail";
			if (sftp != null) {
				try {
					// 删除文件
					sftp.delete(SFTPUtil.basePath + SFTPUtil.videoDirectory, addFilePaths.toString());
				} catch (SftpException e) {
					e.printStackTrace();
				}
			}
		} finally {
			// 关闭流
			if (sftp != null)
				sftp.logout();
		}
		return result;
	}

	/**
	 * 这里是获取上传文件状态信息的访问接口
	 * 
	 * @param session
	 * @return UploadStatus
	 */
	@ResponseBody
	@RequestMapping("getStatus")
	public UploadStatus getStatus(HttpSession session) {
		return (UploadStatus) session.getAttribute("upload_status");
	}

	@ResponseBody
	@RequestMapping("getInfo")
	public Map<String, Object> getUploadInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		response.setHeader("Cache-Control", "no-store"); // 禁止浏览器缓存
		response.setHeader("Pragrma", "no-cache"); // 禁止浏览器缓存
		response.setDateHeader("Expires", 0); // 禁止浏览器缓存

		UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("upload_status");// 从session中读取上传信息
		if (status == null) {
			result.put("error", "没发现上传文件!");
			return result;
		}

		long startTime = status.getStartTime(); // 上传开始时间
		long currentTime = System.currentTimeMillis(); // 现在时间
		long time = (currentTime - startTime) / 1000 + 1;// 已经传顺的时间 单位：s
		double velocity = status.getBytesRead() / time; // 传输速度：byte/s
		double totalTime = status.getContentLength() / velocity; // 估计总时间
		double timeLeft = totalTime - time; // 估计剩余时间
		int percent = (int) (100 * (double) status.getBytesRead() / (double) status.getContentLength()); // 百分比
		double length = status.getBytesRead() / 1024 / 1024; // 已完成数
		double totalLength = status.getContentLength() / 1024 / 1024; // 总长度 M
		result.put("startTime", startTime);
		result.put("currentTime", currentTime);
		result.put("time", time);
		result.put("velocity", velocity);
		result.put("totalTime", totalTime);
		result.put("timeLeft", timeLeft);
		result.put("percent", percent);
		result.put("length", length);
		result.put("totalLength", totalLength);
		return result;
	}

}
