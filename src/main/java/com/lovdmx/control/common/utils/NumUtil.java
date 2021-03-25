package com.lovdmx.control.common.utils;

/**
 * 数字处理类
 * 
 * @author syz
 * 
 */
public class NumUtil {
	// 高位在前，低位在后
	public static byte[] int2bytes(int num) {
		byte[] result = new byte[4];
		result[0] = (byte) ((num >>> 24) & 0xff);// 说明一
		result[1] = (byte) ((num >>> 16) & 0xff);
		result[2] = (byte) ((num >>> 8) & 0xff);
		result[3] = (byte) ((num >>> 0) & 0xff);
		return result;
	}

	/**
	 * char类型 转 2个字节
	 * 
	 * @param num
	 *            char 类型数据
	 * @return byte[]
	 */
	public static byte[] char2bytes(char num) {
		byte[] result = new byte[2];
		result[0] = (byte) ((num >>> 8) & 0xFF);
		result[1] = (byte) ((num >>> 0) & 0xFF);
		return result;
	}

	// 高位在前，低位在后
	public static int bytes2int(byte[] bytes) {
		int result = 0;
		if (bytes.length == 4) {
			int a = (bytes[0] & 0xff) << 24;// 说明二
			int b = (bytes[1] & 0xff) << 16;
			int c = (bytes[2] & 0xff) << 8;
			int d = (bytes[3] & 0xff);
			result = a | b | c | d;
		}
		return result;
	}

	public static int char2int(byte[] bytes) {
		int result = 0;
		if (bytes.length == 2) {
			int a = (bytes[0] & 0xff) << 8;// 说明二
			int b = (bytes[1] & 0xff) << 0;
			result = (char) a + b;
		}
		return result;
	}

	public static byte[] ipv4Address2BinaryArray(String ipAdd) {
		byte[] binIP = new byte[4];
		String[] strs = ipAdd.split("\\.");
		for (int i = 0; i < strs.length; i++) {
			binIP[i] = (byte) Integer.parseInt(strs[i]);
		}
		return binIP;
	}

	public static String binaryArray2Ipv4Address(byte[] addr) {
		String ip = "";
		for (int i = 0; i < addr.length; i++) {
			ip += (addr[i] & 0xFF) + ".";
		}
		return ip.substring(0, ip.length() - 1);
	}

	public static void main(String[] args) {
		byte[] int2bytes = int2bytes(34888);
		for (byte b : int2bytes) {
			System.out.println(b);
		}
	}

}
