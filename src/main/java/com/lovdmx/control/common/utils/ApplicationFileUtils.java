package com.lovdmx.control.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: VersionUtils.java
 * @Description: 程序文件 工具类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年12月10日 上午9:52:21
 *
 */
public class ApplicationFileUtils {

	/**
	 * 
	 * @Function: VersionUtils.java
	 * @Description: 获取继电器程序版本号
	 *
	 * @param: path 绝对路径
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月10日 上午9:55:19
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月10日
	 *        Administrator v1.0.0 修改原因
	 */
	public static String[] getRelayApplicationFile(String path) {
		// 获取文件
		File file = new File(path);
		// 判断是否存在 , 不存在 创建
		// boolean flag = isExitsRelayMkdirFile(file);
		if (file.exists()) {
			// 字符流 读
			BufferedReader bf = null;
			// 配置文件内容
			String[] strArr = new String[2];
			try {
				// 获取读文件
				bf = new BufferedReader(new FileReader(file));
				// 文件内容
				String str = "";
				// 临时变量
				String count = "";
				// 读操作
				while ((count = bf.readLine()) != null) {
					str += count + "\n";
				}
				// 组装 数据每次加0.1
				String[] split = str.split("\n\n");
				String[] versionSplit = split[0].split("applicationVersion = ");
				String[] pathSplit = split[1].split("applicationName = ");
				strArr[0] = versionSplit[1];
				strArr[1] = pathSplit[1];
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (bf != null)
					try {
						bf.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return strArr;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Function: VersionUtils.java
	 * @Description: 设置继电器程序版本号
	 *
	 * @param: path 绝对路径
	 * @param: applicationVersion 版本号
	 * @param: applicationName 程序名称
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年12月10日 上午9:59:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月10日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void setRelayApplicationFile(String path, String applicationVersion, String applicationName) {
		// 文件
		File file = new File(path);
		// 判断是否存在 , 不存在 创建
		// boolean flag = isExitsRelayMkdirFile(file);
		if (file.exists()) {
			// 字符流 读
			BufferedReader bf = null;
			// 字符流 写
			BufferedWriter bw = null;
			try {
				// 获取读文件
				bf = new BufferedReader(new FileReader(file));
				// 文件内容
				String str = "";
				// 临时变量
				String count = "";
				// 读操作
				while ((count = bf.readLine()) != null) {
					str += count + "\n";
				}
				// 分割
				String[] split = str.split("\n\n");
				String[] versionSplit = split[0].split("applicationVersion = ");
				String[] pathSplit = split[1].split("applicationName = ");
				String ver = versionSplit[1];
				String aplPath = pathSplit[1];
				// String version = applicationVersion;
				str = str.replace("" + ver, "" + applicationVersion);
				str = str.replace("" + aplPath, "" + applicationName);
				String[] split2 = str.split("\n");
				// 写操作
				bw = new BufferedWriter(new FileWriter(file));
				// 写入
				for (String string : split2) {
					bw.write(string);
					bw.newLine();
				}
				bw.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭流
				try {
					if (bf != null) {
						bf.close();
					}
					if (bw != null) {
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean isExitsRelayMkdirFile(File file) {
		StringBuffer sb = new StringBuffer();
		// 字符流 写
		BufferedWriter bw = null;
		try {
			if (!file.exists() && !file.isDirectory()) {
				file.createNewFile();
				// 写操作
				bw = new BufferedWriter(new FileWriter(file));
				sb.append("[APPLICATIONVERSION]\n");
				sb.append("applicationVersion = v1.0.0\n");
				sb.append("\n\n");
				sb.append("[APPLICATIONNAME]\n");
				sb.append("applicationName = relay.exe\n");
				sb.append("\n\n");
				String[] split = sb.toString().split("\n");
				// 写入
				for (String string : split) {
					bw.write(string);
					bw.newLine();
				}
				bw.flush();
				return false;
			}
		} catch (Exception ex) {

		} finally {
			if (bw != null) {
				try {
					// 关闭流
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return true;
	}

}
