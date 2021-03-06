/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.utils 
 * @author: syz  
 * @date: 2019年3月12日 下午4:54:20 
 */
package com.lovdmx.control.common.utils;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

/**
 * 使用 JSch 上传文件，并且控制上传的速度，同时监控上传进度
 */
public class FileUploadMain {
	public static void main(String[] args) {

		JSch jsch = new JSch();
		UploadMonitor monitor = null;
		FileInputStream fis = null;
		try {
			Session session = jsch.getSession("lovspirit", "192.168.100.7", 22);
			session.setPassword("hanbo100");
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			ChannelSftp c = (ChannelSftp) channel;

			String pwd = c.pwd();
			//System.out.println(pwd);
			String src = "e:\\99e9ddd28d2d4ca299d08121e366a2a4301_LOVDMX 录放精灵_spiritePro.mp4";
			String dst = "/mnt/spiritFile/file/";
			monitor = new UploadMonitor(new File(src).length());
			//System.err.println("" + monitor);
			// 是否限制上传速度
			boolean speedLimit = false;
			if (speedLimit) {
				OutputStream os = c.put(dst, monitor, ChannelSftp.OVERWRITE);
				byte[] buff = new byte[1024 * 10]; // 设定每次传输的数据块大小为256KB
				int read;
				if (os != null) {
					fis = new FileInputStream(src);
					do {
						read = fis.read(buff, 0, buff.length);
						if (read > 0) {
							os.write(buff, 0, read);
						}
						os.flush();
					} while (read >= 0);
				}
			} else {
				c.put(src, dst, monitor, ChannelSftp.OVERWRITE);
			}
			c.disconnect();
			session.disconnect();
		} catch (Exception e) {

			/**
			 * 发生异常后，终止监听
			 */
			monitor.stop();

		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
		}

	}

}
