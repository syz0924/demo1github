package com.lovdmx.control.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ImageUtils {
	
	public static String changeFileName(String oldName) {
		String value = UUID.randomUUID().toString();
		value = value.replaceAll("-", "");
		return value + oldName;

	}

	public static boolean judgeFile(String fileHouZhuiMing) {

		List<String> filType = Arrays.asList("gif", "bmp", "jpg","png"); // 允许的上载图片的格式

		if (filType.contains(fileHouZhuiMing)) {
			return true;
		} else {
			return false;
		}
	}
}
