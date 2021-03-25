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

<title>中控后台-Dmx512控制盒列表</title>

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
								href="${path }/lovdmx/ht/dmx512Device/index.do"><cite>Dmx512控制盒列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/dmx512Device/index.do" method="GET">


								<div class="lovdmx-inline" id="project_select">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty dmx512Device.projectId }">
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
															readonly value="${dmx512Device.projectName }"
															data-value="${dmx512Device.projectId }"
															placeholder="请选择项目" /><input type="hidden"
															class="lovdmx-input"
															value='${dmx512Device.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${dmx512Device.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${dmx512Device.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline rack_rtr_select" id="rack_select">
									<label class="lovdmx-form-label">节点列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty dmx512Device.projectId }">
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
															value="${dmx512Device.rackName }"
															data-value="${dmx512Device.rackId }"
															placeholder="请选择节点" /><input type="hidden"
															class="lovdmx-input"
															value='${dmx512Device.rackId }' name="rackId" /><input
															type="hidden" class="lovdmx-input"
															value='${dmx512Device.rackName }' name="rackName" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${dmx512Device.rackId == null?'lovdmx-this':''}">请选择节点</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }"
																class="${dmx512Device.rackId eq lst.rackId?'lovdmx-this':'' }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline" id="rtr_select">
									<label class="lovdmx-form-label">RTR列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty rtrDeviceList }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rtr_info" readonly
															value="请选择MAC" data-value="-1" placeholder="请选择MAC" /><input
															type="hidden" class="lovdmx-input" name="rtrMac" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" >
														<dd data-value="-1" class="lovdmx-this">请选择MAC</dd>
														<c:forEach items="${rtrDeviceList }" var="lst">
															<dd data-value="${lst.mac }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rtr_info" readonly
															data-value="${dmx512Device.rtrMac }"
															value="${dmx512Device.rtrMac }" placeholder="请选择MAC" /><input
															type="hidden" class="lovdmx-input"
															value='${dmx512Device.rtrMac }' name="rtrMac" /><span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" id="rtr_select">
														<c:forEach items="${rtrDeviceList }" var="lst">
															<dd data-value="${lst.mac }"
																class="${dmx512Device.rtrMac eq lst.mac?'lovdmx-this':'' }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>


								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="dmx512Device-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn"></div>
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
														<span>RTR MAC</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>DMX512 MAC</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>IP</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>Universe信息</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>RDM个数</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>RDM温度 </span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>在选状态</span>
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
														<td colspan="10" style="color: red; font-size: 16px;">没有数据！！</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${pageInfo.list }" var="list"
														varStatus="vs">
														<tr class="dmxId${list.dmxId }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.dmxId }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="dmxId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="rtrMac">${list.rtrMac }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="dmx512Mac">${list.dmx512Mac }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="ip">${list.ip }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="universe">${list.universe }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rdmNums">${list.rdmNums }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rdmts">${list.rdmts }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="isOnline"> ${list.isOnline eq 1?"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-green'>在线</button>":"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未在线</button>"}
																	</span>
																</div>
															</td>
															<td class="table-last lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<a
																		class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-bianji lovdmx-icon iconfont"></i> 编辑
																	</a> <a
																		class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-shanchu lovdmx-icon iconfont"></i> 删除
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
													href="${path }/lovdmx/ht/dmx512Device/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${dmx512Device.projectId }&projectName=${dmx512Device.projectName }&rackId=${dmx512Device.rackId }&rackName=${dmx512Device.rackName}&rtrMac=${dmx512Device.rtrMac}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/dmx512Device/index.do?pageNo=${pageNo2}&projectId=${dmx512Device.projectId }&projectName=${dmx512Device.projectName }&rackId=${dmx512Device.rackId }&rackName=${dmx512Device.rackName}&rtrMac=${dmx512Device.rtrMac}"
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
													href="${path }/lovdmx/ht/dmx512Device/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${dmx512Device.projectId }&projectName=${dmx512Device.projectName }&rackId=${dmx512Device.rackId }&rackName=${dmx512Device.rackName}&rtrMac=${dmx512Device.rtrMac}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/dmx512Device/index.do?projectId=${dmx512Device.projectId }&projectName=${dmx512Device.projectName }&rackId=${dmx512Device.rackId }&rackName=${dmx512Device.rackName}&rtrMac=${dmx512Device.rtrMac}'>

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

		//查询
		$("#dmx512Device-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//RTR MAC地址
					var rtrMac = $(".lovdmx-select-title .rtr_info").attr(
							"data-value");
					//机柜id
					var rackId = $(".lovdmx-select-title .rack_info").attr(
							"data-value");
					//机柜名
					var rackName = $(".lovdmx-select-title .rack_info").val();
					//項目id
					var projectId = $(".lovdmx-select-title .project_info").attr(
							"data-value");
					//項目名
					var projectName = $(".lovdmx-select-title .project_info").val();
					
					if (rtrMac == -2) {
						layer.msg('该节点没有RTR设备,请重新选择节点列表', {
							icon : 5
						});
						return;
					}else if(rackId == -2){
						layer.msg('该项目没有节点,请重新选择项目列表', {
							icon : 5
						});
						return;
					}

					$form.find("input[name='rtrMac']").val(
							rtrMac == -1 ? null : rtrMac);
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
		
		$("body").on("click","#project_select .lovdmx-anim-upbit dd",function() {
			//获取 节点列表
			var $rtr_select = $("#rtr_select .lovdmx-anim-upbit");
			//移除 节点之前数据
			$rtr_select.children().remove();
			var $rtr_info = $(".rtr_info");
			$rtr_select.append("<dd data-value='-1' class='lovdmx-this'>请选择MAC</dd>");
			//更新				
			$rtr_info.val("请选择MAC");
			$rtr_info.attr("data-value", "-1");
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
<script type="text/javascript" src="${path }/js/admin/jquery_selected_list.js"></script>
</html>