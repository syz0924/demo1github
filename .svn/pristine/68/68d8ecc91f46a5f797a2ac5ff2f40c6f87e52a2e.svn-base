package com.lovdmx.control.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.lovdmx.control.pojo.MonitoringDevice;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MonitoringUtils.java
 * @Description: 监控 工具类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月24日 下午1:39:13
 *
 */
public class MonitoringUtils {

	// 获取凭证 地址
	private static String accessToken_url = "";

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
			accessToken_url = properties.getProperty("accessToken_url");
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
	 * 
	 * @Function: MonitoringUtils.java
	 * @Description: 获取凭证
	 *
	 * @param: monitoring 监控对象
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月24日 下午1:44:16
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月24日
	 *        Administrator v1.0.0 修改原因
	 */
	public static String getAccessToken(MonitoringDevice monitoring) {
		// 组装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("appKey", monitoring.getAppKey());
		param.put("appSecret", monitoring.getAppSecret());

		// POST 请求
		String doPost = HttpClientUtil.doPost(accessToken_url, param);
		return doPost;
	}

}
