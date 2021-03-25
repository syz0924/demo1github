//路径
var path =$("#pageContext").val();

/*
 * html 事件
 */

//获取节点列表
$("body").on("click","#project_select .lovdmx-anim-upbit dd",function() {
	//获取项目id
	var projectId = $(this).attr("data-value");
	//获取 节点列表
	var $rack_select = $("#rack_select .lovdmx-anim-upbit");
	//移除 节点之前数据
	$rack_select.children().remove();
	var $rack_info = $(".rack_info");
	if (projectId == -1) {
		$rack_select.append("<dd data-value='-1' class='lovdmx-this'>请选择项目</dd>");
		//更新				
		$rack_info.val("请选择项目");
		$rack_info.attr("data-value", "-1");

	} else {
		$.ajax({
			type : "GET",
			url : path+"/lovdmx/ht/rackDevice/querRackDeviceInfoByProjectId.do",
			data : "projectId=" + projectId,
	        error: function (XMLHttpRequest, textStatus, errorThrown){
	              //TODO: 处理status， http status code，超时 408

	              // 注意：如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能

	                    //是"timeout", "error", "notmodified" 和 "parsererror"。
	    	   layer.alert("操作异常", {
					icon : 5
				});
	         },
			 success : function(rackDeviceList) {
				if (rackDeviceList.length > 0) {
					$rack_select.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
					//更新	
					$rack_info.val("请选择节点");
					$rack_info.attr("data-value",-1);
					$.each(rackDeviceList,function(i,rackDevice) {
						$rack_select.append("<dd data-value='"+rackDevice.rackId+"' >"
											+ rackDevice.rackName+ "</dd>");
					});
				} else {
					//更新	
					$rack_info.val("该项目无节点列表");
					$rack_info.attr(
							"data-value", "-2");
				}
			}
		});
	}

});

//获取RTR列表
$("body").on("click",".rack_rtr_select .lovdmx-anim-upbit dd",function() {
	
	//获取节点id
	var rackId = $(this).attr("data-value");
	//获取 节点列表
	var $rtr_select = $("#rtr_select .lovdmx-anim-upbit");
	//移除 节点之前数据
	$rtr_select.children().remove();
	var $rtr_info = $(".rtr_info");
	if (rackId == -1) {
		$rtr_select.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
		//更新				
		$rtr_info.val("请选择节点");
		$rtr_info.attr("data-value", "-1");

	} else {
		$.ajax({
			type : "GET",
			url : path+"/lovdmx/ht/rtrDevice/getRtrDeviceInfoByRackId.do",
			data : "rackId=" + rackId,
			success : function(rtrDeviceList) {

				if (rtrDeviceList.length > 0) {
					$.each(rtrDeviceList,function(i,rtrDevice) {
						if (i == 0) {
							$rtr_select.append("<dd data-value='"+rtrDevice.mac+"' class='lovdmx-this'>"
											+ rtrDevice.mac+ "</dd>");
							//更新	
							$rtr_info.val(rtrDevice.mac);
							$rtr_info.attr("data-value",rtrDevice.mac);
						} else {
							$rtr_select.append("<dd data-value='"+rtrDevice.mac+"' >"
											+ rtrDevice.mac+ "</dd>");
						}
					});
				} else {
					//更新	
					$rtr_info.val("该机柜无RTR列表");
					$rtr_info.attr("data-value", "-2");
				}
			}
		});
	}

});

//获取智能网关列表
$("body").on("click",".rack_gateway_select .lovdmx-anim-upbit dd",function() {

	//获取节点id
	var rackId = $(this).attr("data-value");
	//获取 节点列表
	var $intelligentGateway_select = $("#intelligentGateway_select .lovdmx-anim-upbit");
	//移除 节点之前数据
	$intelligentGateway_select.children().remove();
	var $intelligentGateway_info = $(".intelligentGateway_info");
	if (rackId == -1) {
		$intelligentGateway_select.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
		//更新				
		$intelligentGateway_info.val("请选择节点");
		$intelligentGateway_info.attr("data-value","-1");

	} else {
		$.ajax({
			type : "GET",
			url : path+"/lovdmx/ht/intelligentGateway/getIntelligentGatewayInfoByRackId.do",
			data : "rackId=" + rackId,
			success : function(intelligentGatewayList) {
				if (intelligentGatewayList.length > 0) {
					
					//更新	
					$intelligentGateway_select.append("<dd data-value='-1' class='lovdmx-this'>请选择智能网关</dd>");
					$intelligentGateway_info.val("请选择智能网关");
					$intelligentGateway_info.attr("data-value","-1");
					$.each(intelligentGatewayList,function(i,intelligentGateway) {
						$intelligentGateway_select.append("<dd data-value='"+intelligentGateway.mac+"' >"
											+ intelligentGateway.mac+ "</dd>");
					});
				} else {
					//更新	
					$intelligentGateway_info.val("该节点无智能网关列表");
					$intelligentGateway_info.attr("data-value","-2");
				}
			}
		});
	}

});



$("body").on("click",".gateway_relay_select .lovdmx-anim-upbit dd",function() {

	//获取节点id
	var gatewayMac = $(this).attr("data-value");
	//获取 节点列表
	var $relay_select = $("#relay_select .lovdmx-anim-upbit");
	//移除 节点之前数据
	$relay_select.children().remove();
	var $relay_info = $(".relay_info");
	if (gatewayMac == -1) {
		$relay_select.append("<dd data-value='-1' class='lovdmx-this'>请选择智能网关</dd>");
		//更新				
		$relay_info.val("请选择智能网关");
		$relay_info.attr("data-value","-1");
	} else {
		$.ajax({
			type : "GET",
			url : path+"/lovdmx/ht/relay/getRelayInfoByGatewayMac.do",
			data : "gatewayMac=" + gatewayMac,
			success : function(relayList) {
				if (relayList.length > 0) {
					//遍历组装 列表信息
					$.each(relayList,function(i,relay) {
						if (i == 0) {
							$relay_select.append("<dd data-value='"+relay.id+"' class='lovdmx-this'>"
											+ relay.rs485toNetIp+"==="+relay.slaveAddr+ "</dd>");
							//更新	
							$relay_info.val(relay.rs485toNetIp+"==="+relay.slaveAddr);
							$relay_info.attr("data-value",relay.id);

						} else {
							$relay_select.append("<dd data-value='"+relay.id+"' >"
											+ relay.rs485toNetIp+"==="+relay.slaveAddr+ "</dd>");
						}
					});
				} else {
					//更新	
					$relay_info.val("该网关无继电器列表");
					$relay_info.attr("data-value","-2");
				}
			}
		}); 
	}
});





/*
 * popup 事件
 */
$("body").on("click","#popup-project-select .lovdmx-anim-upbit dd",function(){
	//获取项目id
	var projectId = $(this).attr("data-value");
	//获取 节点列表
	var $rack_select = $("#popup-rack-select .lovdmx-anim-upbit");
	//移除 节点之前数据
	$rack_select.children().remove();
	var $rack_info = $("#popup-rack-select .rack_info");
	if (projectId == -1) {
		$rack_select
				.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
		//更新				
		$rack_info.val("请选择节点");
		$rack_info.attr("data-value", "-1");

	} else {
		$.ajax({
			type : "GET",
			url : path+"/lovdmx/ht/rackDevice/querRackDeviceInfoByProjectId.do",
			data : "projectId=" + projectId,
	        error: function (XMLHttpRequest, textStatus, errorThrown){
	              //TODO: 处理status， http status code，超时 408

	              // 注意：如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能

	                    //是"timeout", "error", "notmodified" 和 "parsererror"。
	    	   layer.alert("操作异常", {
					icon : 5
				});
	         },
			 success : function(rackDeviceList) {
				if (rackDeviceList.length > 0) {
					$rack_select.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
					//更新	
					$rack_info.val("请选择节点");
					$rack_info.attr("data-value",-1);
					$.each(rackDeviceList,function(i,rackDevice) {
						$rack_select.append("<dd data-value='"+rackDevice.rackId+"' >"
											+ rackDevice.rackName+ "</dd>");
					});
				} else {
					//更新	
					$rack_info.val("该项目无节点列表");
					$rack_info.attr(
							"data-value", "-2");
				}
			}
		});
	}
});

