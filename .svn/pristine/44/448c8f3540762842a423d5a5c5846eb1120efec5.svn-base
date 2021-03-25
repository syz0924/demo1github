package com.lovdmx.control.common.utils;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: StringJudgeUtils.java
 * @Description: 字符串判断 工具类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月27日 下午4:10:27
 *
 */
public class StringJudgeUtils {

	/**
	 * 
	 * @Function: StringJudgeUtils.java
	 * @Description: 判断是否有权限
	 *
	 * @param: limit 权限列表
	 * @param: powerId 权限id
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月27日 下午4:09:39
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月27日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean judgeWhetherIsLimits(String limit, int powerId) {
		// 切割
		String[] split = limit.split(",");
		// 遍历
		for (int i = 0; i < split.length; i++) {
			// 判断是否有权限
			if (Integer.parseInt(split[i]) == powerId) {
				return true;
			}
		}
		return false;
	}

}
