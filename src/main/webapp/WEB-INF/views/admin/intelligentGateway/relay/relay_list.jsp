<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="lovdmx" uri="www.lovdmx.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${path }/image/lei.jpg"
	type="image/x-icon" />
<link href="${path }/css/skin/jeDate-test.css" rel="stylesheet"
	type="text/css">
<link href="${path }/css/skin/jedate.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${path }/js/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${path }/css/public.css">
<link rel="stylesheet" type="text/css"
	href="${path }/css/iconfont/iconfont.css">
<link type="text/css" rel="stylesheet" href="${path }/js/sg/css/sg.css"></link>
<link rel="stylesheet" href="${path }/css/admin/style.css">
<link rel="stylesheet" href="${path }/css/admin/popup.css">
<script type="text/javascript" src="${path }/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_init.js"></script>
<script type="text/javascript" src="${path }/js/layui/layui.all.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery.js"></script>

<title>中控后台-继电器列表</title>

</head>
<body>

	<div class="lovdmx">
		<div class="lovdmx-admin">
			<!-- 侧边菜单 -->
			<%@include file="/common/admin/left.jsp"%>

			<!-- 头部 -->
			<%@include file="/common/admin/header.jsp"%>


			<!-- 主体 -->
			<div class="lovdmx-body">
				<div class="lovdmx-bodyinfo lovdmx-show">
					<div class="lovdmx-card lovdmxadmin-header">
						<div class="lovdmx-breadcrumb">
							<a>主页</a> <span>/</span> <a
								href="${path }/lovdmx/ht/relay/index.do"><cite>继电器列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/relay/index.do" method="GET">
								<div class="lovdmx-inline" id="project_select">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relay.projectId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect project_info"
															readonly value="请选择项目" data-value="-1"
															placeholder="请选择项目" /><input type="hidden"
															class="lovdmx-input" name="projectId" /><input
															type="hidden" class="lovdmx-input" name="projectName" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect project_info"
															readonly value="${relay.projectName }"
															data-value="${relay.projectId }" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input"
															value='${relay.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${relay.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${relay.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline rack_gateway_select" id="rack_select">
									<label class="lovdmx-form-label">节点列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relay.projectId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rack_info" readonly
															value="请选择项目" data-value="-1" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input" name="rackId" /><input
															type="hidden" class="lovdmx-input" name="rackName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" id="">
														<dd data-value="-1" class="lovdmx-this">请选择项目</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rack_info" readonly
															value="${relay.rackName }" data-value="${relay.rackId }"
															placeholder="请选择节点" /><input type="hidden"
															class="lovdmx-input" value='${relay.rackId }'
															name="rackId" /><input type="hidden"
															class="lovdmx-input" value='${relay.rackName }'
															name="rackName" /> <span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${relay.rackId == null?'lovdmx-this':''}">请选择节点</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }"
																class="${relay.rackId eq lst.rackId?'lovdmx-this':'' }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline" id="intelligentGateway_select">
									<label class="lovdmx-form-label">网关列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relay.rackId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect intelligentGateway_info"
															readonly value="请选择节点" data-value="-1"
															placeholder="请选择节点" /> <input type="hidden"
															class="lovdmx-input" name=gatewayMac /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择节点</dd>
														<c:forEach items="${intelligentGatewayList }" var="lst">
															<dd data-value="${lst.mac }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect intelligentGateway_info"
															readonly value="${relay.gatewayMac }"
															data-value="${relay.gatewayMac }" placeholder="请选择智能网关" />
														<input type="hidden" class="lovdmx-input"
															value='${relay.gatewayMac }' name=gatewayMac /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${relay.gatewayMac == ''?'lovdmx-this':''}">请选择智能网关</dd>
														<c:forEach items="${intelligentGatewayList }" var="lst">
															<dd data-value="${lst.mac }"
																class="${relay.gatewayMac eq lst.mac?'lovdmx-this':'' }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="relay-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<button class="lovdmx-btn tableadmin-btn" id="remote-add-relay"
										data-link="${path }/lovdmx/ht/relay/queryRs485ToNetIp.do">远程添加485继电器</button>
								</div>
								<div
									class="lovdmx-table-border lovdmx-table-view lovdmx-table-box">
									<table cellspacing="0" cellpadding="0" border="0" class="table">
										<thead>
											<tr>
												<th class="table-checked lovdmx-unselect">
													<div class="lovdmx-table-cell lovdmxtable-cell-checkbox">
														<i class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>编号</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>网关MAC</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>机架名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>485转IP</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>端口</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>回路数量</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>从站地址</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>在线状态</span>
													</div>
												</th>
												<th class="table-last lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>操作</span>
													</div>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${empty pageInfo.list }">
													<tr>
														<td colspan="9" style="color: red; font-size: 16px;">没有数据！！</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${pageInfo.list }" var="list"
														varStatus="vs">
														<tr class="relayId${list.id }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.id }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="relayId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="gatewayMac">${list.gatewayMac }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rackName">${list.rackName }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rs485toNetIp">${list.rs485toNetIp }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="port">${list.port }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="number">${list.number }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="slaveAddr">${list.slaveAddr }</span>
																</div>
															</td>
															<td class="lovdmx-unselect" style="max-width: 240px">
																<div class="lovdmx-table-cell">
																	<c:forEach items="${list.onlineStateList }" var="val">
																		<c:if test="${val eq 0}">
																			<span class="onlineState gray"></span>
																		</c:if>
																		<c:if test="${val eq 1}">
																			<span class="onlineState green"></span>
																		</c:if>
																	</c:forEach>
																</div>
															</td>
															<td class="table-last lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<c:if
																		test="${controlOnlineStatus && list.isOnline==1 }">
																		<a
																			data-link="${path }/lovdmx/ht/relay/edit.do?id=${list.id}"
																			class="lovdmx-btn lovdmx-btn-remote lovdmx-btn-xs remote-edit-relay">
																			<i
																			class="icon-biaoshilei_yuanchengpeizhi lovdmx-icon iconfont"></i>
																			远程配置
																		</a>
																	</c:if>
																	<c:if
																		test="${!controlOnlineStatus || list.isOnline==0 }">
																		<a
																			class="lovdmx-btn lovdmx-btn-xs lovdmx-btn-disabled">
																			<i
																			class="icon-biaoshilei_yuanchengpeizhi lovdmx-icon iconfont"></i>
																			远程配置
																		</a>
																	</c:if>
																	<a
																		class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-bianji lovdmx-icon iconfont"></i> 编辑
																	</a> <a class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs"
																		data-link="${path}/lovdmx/ht/relay/delete.do?id=${list.id}"
																		onclick="mydel(this)"> <i
																		class="icon-shanchu lovdmx-icon iconfont lovdmx-account-del"></i>
																		删除
																	</a>
																</div>
															</td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</div>

								<div class="lovdmx-table-page">
									<div id="table-page">
										<div class="lovdmx-box lovdmx-page-default lovdmx-lovpage">

											<c:if test="${pageInfo.isFirstPage || pageInfo.size<=0}">
												<a href="javascript:void(0);"
													class="lovdmx-page-prev lovdmx-disabled }"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>

											<c:if test="${!pageInfo.isFirstPage && pageInfo.size>0}">
												<a
													href="${path }/lovdmx/ht/relay/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${relay.projectId}&projectName=${relay.projectName}&rackId=${relay.rackId}&rackName=${relay.rackName}&gatewayMac=${relay.gatewayMac }"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/relay/index.do?pageNo=${pageNo2}&projectId=${relay.projectId}&projectName=${relay.projectName}&rackId=${relay.rackId}&rackName=${relay.rackName}&gatewayMac=${relay.gatewayMac }"
													class="lovdmx-page-num ${pageInfo.pageNum eq pageNo2?'lovdmx-page-curr':'' } "
													data-page="${pageNo2 }">${pageNo2 }</a>
											</c:forEach>

											<c:if test="${pageInfo.isLastPage || pageInfo.size==0}">
												<a href="javascript:void(0)"
													class="lovdmx-page-next lovdmx-disabled"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>

											<c:if test="${!pageInfo.isLastPage && pageInfo.size>0}">
												<a
													href="${path }/lovdmx/ht/relay/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${relay.projectId}&projectName=${relay.projectName}&rackId=${relay.rackId}&rackName=${relay.rackName}&gatewayMac=${relay.gatewayMac }"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/relay/index.do?projectId=${relay.projectId}&projectName=${relay.projectName}&rackId=${relay.rackId}&rackName=${relay.rackName}&gatewayMac=${relay.gatewayMac }'>

											<span class="lovdmx-page-skip"> 到第 <input type="text"
												name="pageNo" min="1" value="${pageInfo.pageNum }"
												class="lovdmx-input"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
												页
												<button type="button" class="lovdmx-page-btn"
													onclick="submitPageNo();">确定</button>
											</span> <span class="lovdmx-page-count"
												data-count="${pageInfo.total }">共${pageInfo.total }条</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(function() {

		//
		$("#relay-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//RTR MAC地址
					var gatewayMac = $(
							".lovdmx-select-title .intelligentGateway_info")
							.attr("data-value");

					//项目
					var projectId = $(".lovdmx-select-title .project_info")
							.attr("data-value");
					var projectName = $(".lovdmx-select-title .project_info")
							.val();
					//机柜id
					var rackId = $(".lovdmx-select-title .rack_info").attr(
							"data-value");
					//机柜名
					var rackName = $(".lovdmx-select-title .rack_info").val();
					if (rackId == -2) {
						layer.msg('该项目没有节点设备,请重新选择项目列表', {
							icon : 5
						});
						return;
					} else if (gatewayMac == -2) {
						layer.msg('该节点没有智能网关设备,请重新选择节点列表', {
							icon : 5
						});
						return;
					}

					$form.find("input[name='gatewayMac']").val(
							gatewayMac == -1 ? null : gatewayMac);
					$form.find("input[name='rackId']").val(
							rackId == -1 ? null : rackId);
					$form.find("input[name='rackName']").val(
							rackId == -1 ? null : rackName);
					$form.find("input[name='projectId']").val(
							projectId == -1 ? null : projectId);
					$form.find("input[name='projectName']").val(
							projectId == -1 ? null : projectName);
					$form.submit();
				});

		$("body")
				.on(
						"click",
						"#project_select .lovdmx-anim-upbit dd",
						function() {
							//获取 节点列表
							var $intelligentGateway_select = $("#intelligentGateway_select .lovdmx-anim-upbit");
							//移除 节点之前数据
							$intelligentGateway_select.children().remove();
							var $intelligentGateway_info = $(".intelligentGateway_info");
							$intelligentGateway_info
									.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
							//更新				
							$intelligentGateway_info.val("请选择节点");
							$intelligentGateway_info.attr("data-value", "-1");
						});
	});
</script>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_upload.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript"
	src="${path }/js/admin/jquery_selected_list.js"></script>
</html>