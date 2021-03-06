/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.utils 
 * @author: syz  
 * @date: 2019年3月11日 下午2:52:50 
 */
package com.lovdmx.control.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 类说明 sftp工具类
 */
public class SFTPUtil {
	private transient Logger log = LoggerFactory.getLogger(this.getClass());

	private ChannelSftp sftp;

	private Session session;
	/** SFTP 登录用户名 */
	private static String username;
	/** SFTP 登录密码 */
	private static String password;
	/** 私钥 */
	private String privateKey;
	/** SFTP 服务器地址IP地址 */
	private static String host;
	/** SFTP 端口 */
	private static int port;
	// 服务器的基础路径
	public static String basePath;
	// 上传视频目录
	public static String videoDirectory;
	// 上传灯光目录
	public static String lmxDirectory;
	//中控的基本路径
	public static String controlBasePath;
	//web的基本路径
	public static String webBasePath;

	static {
		// 获取文本内容
		Properties properties = new Properties();
		// 输入流
		InputStream in = null;
		try {
			// 获取 输入流
			in = Thread.currentThread().getContextClassLoader().getResource("application.properties").openStream();
			// load 方法 输入流
			properties.load(in);
			username = properties.getProperty("sftp_username");
			password = properties.getProperty("sftp_password");
			host = properties.getProperty("sftp_host");
			port = Integer.parseInt(properties.getProperty("sftp_port"));
			basePath = properties.getProperty("sftp_basePath");
			videoDirectory = properties.getProperty("sftp_video_directory");
			lmxDirectory = properties.getProperty("sftp_lmx_directory");
			controlBasePath = properties.getProperty("sftp_control_basePath");
			webBasePath = properties.getProperty("sftp_web_basePath");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in = null;
			}
		}
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SFTPUtil(String privateKey) {
		this.privateKey = privateKey;
	}

	public SFTPUtil() {
	}

	/**
	 * 连接sftp服务器
	 */
	public void login() {
		try {
			JSch jsch = new JSch();
			if (privateKey != null) {
				jsch.addIdentity(privateKey);// 设置私钥
			}

			session = jsch.getSession(username, host, port);

			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 * 
	 * @param basePath
	 *            服务器的基础路径
	 * @param directory
	 *            上传到该目录
	 * @param sftpFileName
	 *            sftp端文件名
	 * @param in
	 *            输入流
	 */
	public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = basePath;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir))
					continue;
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		sftp.put(input, sftpFileName); // 上传文件
	}

	/**
	 * 下载文件。
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile)
			throws SftpException, FileNotFoundException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 * @return 字节数组
	 */
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);

		byte[] fileData = IOUtils.toByteArray(is);

		return fileData;
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	 * 
	 * @Function: SFTPUtil.java
	 * @Description: 获取指定目录信息
	 *
	 * @param:filePath 文件目录
	 * @return：Vector
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月12日 下午3:22:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月12日 Administrator v1.0.0 修改原因
	 */
	public Vector<?> getFileInfo(String filePath) throws SftpException {
		return sftp.ls(filePath);
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	// 上传文件测试
	public static void main(String[] args) throws SftpException, IOException {
		SFTPUtil sftp = new SFTPUtil();
		sftp.login();
		/*
		 * Vector<?> ls = sftp.sftp.ls(
		 * "/mnt/spiritFile/file/45bfe4dc-8acd-48af-b522-2dd379801cd61.mov");
		 * System.out.println(ls.size());
		 */

		byte[] download = sftp.download("/mnt/spiritFile/music/", "陈一发儿 - 童话镇.mp3");
		System.out.println(download.length);

		// sftp.upload("/mnt/spiritFile/", "file", "12306Bypass_1.12.69.zip",
		// is);
		sftp.logout();
	}
}
