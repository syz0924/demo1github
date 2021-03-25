package com.lovdmx.control.controller.head;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovdmx.control.common.utils.HttpClientUtil;
import com.lovdmx.control.common.utils.JsonUtils;
import com.lovdmx.control.httpVo.MonitoringVo;
@Controller
@CrossOrigin
@RequestMapping("/httpClient/")
public class HttpClientController {

	//https://open.ys7.com/api/lapp/device/ptz/start
	@ResponseBody
	@RequestMapping(value="devicePtzStart",method=RequestMethod.GET)
	private  String devicePtzStart(MonitoringVo monitoring) {
		//地址
		String url="https://open.ys7.com/api/lapp/device/ptz/start";
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("accessToken", monitoring.getAccessToken());
		param.put("deviceSerial", monitoring.getDeviceSerial());
		param.put("channelNo", monitoring.getChannelNo()+"");
		param.put("direction", monitoring.getDirection()+"");
		param.put("speed", monitoring.getSpeed()+"");
		
		String doPostJson =HttpClientUtil.doPost(url, param);
		String json = JsonUtils.objectToJson(monitoring);
		System.err.println("=============>>"+json);
		/*
		String doPostJson = HttpClientUtil.doPost(url,json);*/
		System.out.println("=============>>"+doPostJson);
		return doPostJson;
	}
	
}
