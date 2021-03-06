package com.lovdmx.control.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description: 现在时间加（秒）是否大于传过来的时间
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:28:09
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static boolean compare_date(String date2, Integer time) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			Date dt1 = addOneminute(new Date(), time);
			Date dt2 = df.parse(date2);

			if (dt1.getTime() >= dt2.getTime()) {
				return true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description:
	 *
	 * @param:字符串类型日期 获取日期格式
	 * @return：Date
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:26:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static Date currentlyDate(String strDate) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description:
	 *
	 * @param:获取日期
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:26:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static String currentlyStrDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null) {
			return sdf.format(date);
		} else {
			return "";
		}

	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description:
	 *
	 * @param:获取日期
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:26:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static String currentlyStrDate1(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		if (date != null) {
			return sdf.format(date);
		} else {
			return "";
		}

	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(long lt) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description: 将日期格式的字符串转换为长整型
	 *
	 * @param:strDate 字符串时间格式
	 * @return：Long
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:20:49
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static Long converStrToLong(String strDate) {

		Long result = null;

		try {
			Date date = converStrToDate(strDate);
			result = date.getTime();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description: 字符串日期时间转日期时间
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:25:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static Date converStrToDate(String strDate) {

		Date date = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description: 时间类型转字符串格式件的时间
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:18:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static String fmtDateToStrTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		if (date != null) {
			return sdf.format(date);
		} else {
			return "";
		}

	}

	/**
	 * 
	 * @Function: DateUtils.java
	 * @Description: 字符串类型时间转时间
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年4月3日 下午5:18:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年4月3日
	 *        Administrator v1.0.0 修改原因
	 */
	public static Date fmtStrDateToTime(String strDate) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;

	}

	/**
	 * today加一天
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date addOneDay(Date today) {

		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * today加30秒
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date addOneSecond(Date today) {

		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.SECOND, 30);
		date = calendar.getTime();
		return date;
	}

	/**
	 * today加多少秒
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date addOneminute(Date today, Integer second) {

		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.SECOND, second);
		date = calendar.getTime();
		return date;
	}

}
