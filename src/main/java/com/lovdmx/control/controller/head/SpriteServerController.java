package com.lovdmx.control.controller.head;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lovdmx.control.common.thread.AnalysisClientSocketThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;
import com.lovdmx.control.common.utils.OnLineClientUtils;
import com.lovdmx.control.common.utils.PageageUtils;
import com.lovdmx.control.controller.BaseController;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.pojo.Log;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.pojo.UploadVideos;
import com.lovdmx.control.pojo.enums.EnumOperationMode;
import com.lovdmx.control.service.UploadEdlmxService;
import com.lovdmx.control.service.UploadVideosService;
import com.lovdmx.control.vo.SocketClientInfoVo;
import com.lovdmx.control.vo.UploadFileVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SendControlServerController.java
 * @Description: 发送(主)录放精灵服务器 控制器
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年5月11日 下午2:51:58
 *
 */
@RequestMapping("/spriteServer/")
@Controller
@CrossOrigin
public class SpriteServerController extends BaseController {

	@Autowired
	private UploadVideosService uploadVideosService; // 已上传视频

	@Autowired
	private UploadEdlmxService uploadEdlmxService; // 已上传灯光文件

	/**
	 * 
	 * @Function: SendSpriteServiceController.java
	 * @Description: (主)录放精灵上传文件
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月2日 下午5:24:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年3月2日 Administrator v1.0.0 修改原因
	 */
	@ResponseBody
	@RequestMapping("uploadSpriteFile")
	public JSONObject uploadSpriteFile(UploadFileVo uploadFileVo, HttpSession session) throws Exception {
		JSONObject resultJson = new JSONObject();
		String result = "";
		// 账号
		Account account = (Account) session.getAttribute("lovdmxAdmin");
		// 判断是否在线
		if (OnLineClientUtils.judgeAccountIsOnline(account)) {
			// 判断是否在线
			SocketClientInfoVo socketClientInfoVo = OnLineClientUtils.getSpriteClient();
			if (socketClientInfoVo != null) {
				uploadFileVo.setAccountId(account.getAccountId());
				uploadFileVo.setProjectId(account.getProjectId());
				boolean uploadStatus = false;
				if (uploadFileVo.getFileType() == 0) {
					UploadVideos uploadVideo = uploadVideosService.findByMd5(uploadFileVo.getMd5());
					uploadStatus = uploadVideo == null ? true : false;
				} else {
					UploadEdlmx uploadEdlmx = uploadEdlmxService.findByMd5(uploadFileVo.getMd5());
					uploadStatus = uploadEdlmx == null ? true : false;
				}
				// 判断是否存在
				if (uploadStatus) {
					// 组装web数据（用于后续操作）
					JSONObject webJsonData = (JSONObject) JSONObject.toJSON(uploadFileVo);
					// 日志
					Log log = new Log(account.getProjectId(), account.getAccountId(), EnumOperationMode.ADD.name(),
							"添加上传信息", new Date(), new Date());
					// 获取UUID
					String uuid = UUID.randomUUID().toString();
					webJsonData.put("uuid", uuid);
					webJsonData.put("log", log);

					// 组装发送客户端数据
					JSONObject sendClientJson = new JSONObject();
					sendClientJson.put("filePath", uploadFileVo.getFilePath());
					sendClientJson.put("uuid", uuid);

					// 添加ProjectAnalysisOpCodeThread对象的map中
					ProjectAnalysisOpCodeThread.addMapInfo(uuid, webJsonData);

					// 组装数据
					byte[] dataBytes = PageageUtils.assemblyDataPackage(0x2025, sendClientJson.toJSONString());
					// 发送
					AnalysisClientSocketThread.transmit(socketClientInfoVo.getAnalysisSocketThread(), dataBytes);
					result = "send successful";
				} else {
					result = "already exists";
				}
			} else {
				result = "not online";
			}
		} else {
			result = "false";
		}
		resultJson.put("result", result);
		return resultJson;
	}

}
