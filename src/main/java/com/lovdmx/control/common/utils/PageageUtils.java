/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.common.packet 
 * @author: syz  
 * @date: 2019年2月22日 下午5:09:00 
 */
package com.lovdmx.control.common.utils;

import com.lovdmx.control.common.utils.CRC16Util;
import com.lovdmx.control.common.utils.NumUtil;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: PageageUtils.java
 * @Description: 组装JSON格式包
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月22日 下午5:09:00
 *
 */
public class PageageUtils extends SocketUtils {

	/**
	 * 
	 * @Function: PageageUtils.java
	 * @Description: 组装包头和包尾
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月22日 下午5:18:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月22日
	 *        Administrator v1.0.0 修改原因
	 */
	public static byte[] assemblyDataPackage(int opCode, String dataJosn) {
		if (dataJosn == null) {
			dataJosn = "";
		}
		byte[] dataJsonByte = dataJosn.getBytes();
		// 数据长度
		byte[] dataByte = new byte[headByte.length + dataJsonByte.length + endByte.length];
		// 包头
		System.arraycopy(headByte, 0, dataByte, 0, headByte.length);
		// JSON内容
		System.arraycopy(dataJsonByte, 0, dataByte, headByte.length, dataJsonByte.length);
		// 包尾
		System.arraycopy(endByte, 0, dataByte, dataByte.length - 2, endByte.length);
		// opCode
		byte[] opCodeByte = NumUtil.char2bytes((char) opCode);
		// 内容长度
		byte[] lengthByte = NumUtil.char2bytes((char) dataJsonByte.length);
		// CRC16校验值
		byte[] crc16Byte = NumUtil.char2bytes((char) CRC16Util.calcCrc16(dataJsonByte));
		dataByte[3] = opCodeByte[0];
		dataByte[4] = opCodeByte[1];
		dataByte[5] = lengthByte[0];
		dataByte[6] = lengthByte[1];
		dataByte[7] = crc16Byte[0];
		dataByte[8] = crc16Byte[1];
		return dataByte;
	}

	public static void main(String[] args) {
		byte[] assemblyDataPackage = assemblyDataPackage(0x1022, null);
		System.out.println(assemblyDataPackage.length);
	}

}
