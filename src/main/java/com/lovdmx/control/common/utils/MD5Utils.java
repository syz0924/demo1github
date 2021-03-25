/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.utils 
 * @author: syz  
 * @date: 2019年2月28日 上午9:29:15 
 */
package com.lovdmx.control.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: MD5Utils.java
 * @Description: 获取文件或字符串MD5值 四种方法
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月28日 上午9:29:15
 *
 */
public class MD5Utils {
	private final static String[] strHex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f" };

	// 方法一
	public static String getMD5One(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(FileUtils.readFileToByteArray(new File(path)));
			for (int i = 0; i < b.length; i++) {
				int d = b[i];
				if (d < 0) {
					d += 256;
				}
				int d1 = d / 16;
				int d2 = d % 16;
				sb.append(strHex[d1] + strHex[d2]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	// 方法二
	public static String getMD5Two(String path) {
		StringBuffer sb = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(FileUtils.readFileToByteArray(new File(path)));
			byte b[] = md.digest();
			int d;
			for (int i = 0; i < b.length; i++) {
				d = b[i];
				if (d < 0) {
					d = b[i] & 0xff;
					// 与上一行效果等同
					// i += 256;
				}
				if (d < 16)
					sb.append("0");
				sb.append(Integer.toHexString(d));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	// 方法三
	public static String getMD5Three(String path) {
		BigInteger bi = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md = MessageDigest.getInstance("MD5");
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			while ((len = fis.read(buffer)) != -1) {
				md.update(buffer, 0, len);
			}
			fis.close();
			byte[] b = md.digest();
			bi = new BigInteger(1, b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi.toString(16);
	}

	// 方法四
	/*
	 * JAVA自带的commons-codec包就提供了获取16进制MD5值的方法。其底层实现上，也是分多次将一个文件读入，类似方法三。
	 * 所以性能上也不错。
	 */
	// DigestUtils.md5Hex(new FileInputStream(path));

	/***
	 * 对指定的字符串进行MD5加密
	 */
	public static String encrypByMD5(String originString) {
		try {
			// 创建具有MD5算法的信息摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
			byte[] bytes = md.digest(originString.getBytes());
			// 将得到的字节数组变成字符串返回
			String s = byteArrayToHex(bytes);
			return s.toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字节数组转换成十六进制，并以字符串的形式返回 128位是指二进制位。二进制太长，所以一般都改写成16进制，
	 * 每一位16进制数可以代替4位二进制数，所以128位二进制数写成16进制就变成了128/4=32位。
	 */
	private static String byteArrayToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byteToHex(b[i]));
		}
		return sb.toString();
	}

	/**
	 * 将一个字节转换成十六进制，并以字符串的形式返回
	 */
	public static String byteToHex(byte b) {
		int n = b;
		if (n < 0)
			n = n + 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return strHex[d1] + strHex[d2];
	}

}
