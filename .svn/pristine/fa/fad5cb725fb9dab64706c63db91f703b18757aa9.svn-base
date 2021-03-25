package com.lovdmx.control.common.utils;


import java.io.UnsupportedEncodingException;import java.security.MessageDigest;

/** 
 * SHA加密和MD5加密比较�?
 * 1）对强行攻击的安全�?：最显著和最重要的区别是SHA-1摘要比MD5摘要�?2 位�?
         使用强行�?��，产生任何一个报文使其摘要等于给定报摘要的难度对MD5�?^128数量级的操作，�?对SHA-1则是2^160数量级的操作�?
         这样，SHA-1对强行攻击有更大的强度�?
   2）对密码分析的安全�?：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击�?
   3）�?度：在相同的硬件上，SHA-1的运行�?度比MD5慢�?
 */
public final class SecurityUtil {
    /*** 
     * SHA加密 生成40位SHA�?
     * @param 待加密字符串
     * @return 返回40位SHA�?
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    /*** 
     * MD5加密 生成32位md5�?
     * @param 待加密字符串
     * @return 返回32位md5�?
     */
    public static String md5Encode(String inStr)  {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        StringBuffer hexValue = new StringBuffer();		try {			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
			    int val = ((int) md5Bytes[i]) & 0xff;
			    if (val < 16) {
			        hexValue.append("0");
			    }
			    hexValue.append(Integer.toHexString(val));
			}		} catch (UnsupportedEncodingException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}
        return hexValue.toString();
    }


    /**
     * 测试主函�?
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("YLDadmin@228");
        System.out.println("原始" + str.length());
        System.out.println("SHA后：" + shaEncode(str));
        System.out.println("SHA后：" + shaEncode(str).length());        String md5Encode = md5Encode(str);        if ("776edefd092b0fd5ccda32ac186ef908".equals(md5Encode)){        	System.out.println("true");        	        }
        System.out.println("MD5后：" + md5Encode);
        System.out.println("MD5后：" + md5Encode.length());        
    }
}