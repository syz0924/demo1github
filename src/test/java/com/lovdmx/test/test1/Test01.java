package com.lovdmx.test.test1;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.pojo.Account;

public class Test01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", "1");
		jsonObject.put("account", new Account());
		System.out.println(jsonObject.toJSONString());
	}

}
