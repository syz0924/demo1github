package com.lovdmx.control.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: DataAssembleUtils.java
 * @Description: 数据组装公共类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年7月8日 下午4:13:53
 *
 */
public class AssembleDataUtils {

	/**
	 * 
	 * @Function: DataAssembleUtils.java
	 * @Description: 有序排序在线和不在线信息
	 *
	 * @param: num 数量
	 * @param: onlineState 在线状态
	 * @return：List<Integer>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月8日 下午4:37:37
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月8日
	 *        Administrator v1.0.0 修改原因
	 */
	public static List<Integer> getAssembleDataIsOnlineStateList(Integer number, String[] onlineState) {
		List<Integer> onlineStateList = new ArrayList<Integer>();
		// 组装为map对象
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (String value : onlineState) {
			map.put(value, true);
		}
		for (int i = 0; i < number; i++) {
			if (map.containsKey(i + "")) {
				onlineStateList.add(1);
			} else {
				onlineStateList.add(0);
			}
		}
		map.clear();
		map = null;

		return onlineStateList;
	}

}
