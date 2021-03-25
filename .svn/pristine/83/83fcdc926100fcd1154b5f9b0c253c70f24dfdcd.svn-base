/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.listener 
 * @author: syz  
 * @date: 2019年2月25日 下午3:40:01 
 */
package com.lovdmx.control.common.listener;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.lovdmx.control.vo.UploadStatus;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: FileUploadProgressListener.java
 * @Description: 文件上传监听器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月26日 下午4:29:54
 *
 */
@Component
public class FileUploadProgressListener implements ProgressListener {
	private HttpSession session;

	public FileUploadProgressListener(HttpSession session) {
		this.session = session;
		UploadStatus uploadStatus = new UploadStatus();
		session.setAttribute("upload_status", uploadStatus);
	}

	/**
	 * 
	 * @see org.apache.commons.fileupload.ProgressListener#update(long, long,
	 *      int)
	 * @Function: FileUploadProgressListener.java
	 * @Description: 重写分类监听器
	 *
	 * @param: bytesRead
	 *             到目前为止读取文件的比特数
	 * @param: contentLength
	 *             文件总大小
	 * @param: items
	 *             目前正在读取第几个文件
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月26日 下午4:30:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年2月26日 Administrator v1.0.0 修改原因
	 */
	//
	@Override
	public void update(long bytesRead, long contentLength, int items) {
		UploadStatus uploadStatus = (UploadStatus) session.getAttribute("upload_status");
		uploadStatus.setBytesRead(bytesRead);
		uploadStatus.setContentLength(contentLength);
		uploadStatus.setItems(items);
		uploadStatus.setUseTime(System.currentTimeMillis() - uploadStatus.getStartTime());
		uploadStatus.setPercent((int) (100 * bytesRead / contentLength));
		session.setAttribute("upload_status", uploadStatus);

	}

}
