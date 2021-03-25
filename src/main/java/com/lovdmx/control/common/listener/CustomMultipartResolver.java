/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.listener 
 * @author: syz  
 * @date: 2019年2月25日 下午3:44:50 
 */
package com.lovdmx.control.common.listener;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: CustomMultipartResolver.java
 * @Description: 自定义SpringMVC文件上传解析器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月25日 下午3:44:50
 *
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

	/**
	 * 
	 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#parseRequest(javax.servlet.http.HttpServletRequest)
	 * @Function: CustomMultipartResolver.java
	 * @Description: 解析给定的servlet请求，解析其多部分元素
	 *
	 * @param: request
	 *             客户端传送数据
	 * @return：MultipartParsingResult
	 * @throws：MultipartException
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月26日 下午4:40:17
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年2月26日 Administrator v1.0.0 修改原因
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		// 编码
		String encoding = "utf-8";
		// 实例文件上传对象
		FileUpload fileUpload = prepareFileUpload(encoding);
		// 初始化监听器
		FileUploadProgressListener uploadListener = new FileUploadProgressListener(request.getSession());
		// 监听上传进度
		fileUpload.setProgressListener(uploadListener);
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}
}
