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

<title>中控后台-录放精灵设备列表</title>

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
								href="${path }/lovdmx/ht/spriteDevice/index.do"><cite>录放精灵设备列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/spriteDevice/index.do" method="GET">
								
								<div class="lovdmx-inline" id="project_select">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty spriteDeviceInfo.projectId }">
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
															readonly value="${spriteDeviceInfo.projectName }"
															data-value="${spriteDeviceInfo.projectId }"
															placeholder="请选择项目" /><input type="hidden"
															class="lovdmx-input"
															value='${spriteDeviceInfo.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${spriteDeviceInfo.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${spriteDeviceInfo.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline" id="rack_select">
									<label class="lovdmx-form-label">节点列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty spriteDeviceInfo.projectId }">
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
															value="${spriteDeviceInfo.rackName }"
															data-value="${spriteDeviceInfo.rackId }"
															placeholder="请选择节点" /><input type="hidden"
															class="lovdmx-input"
															value='${spriteDeviceInfo.rackId }' name="rackId" /><input
															type="hidden" class="lovdmx-input"
															value='${spriteDeviceInfo.rackName }' name="rackName" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${spriteDeviceInfo.rackId == null?'lovdmx-this':''}">请选择节点</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }"
																class="${spriteDeviceInfo.rackId eq lst.rackId?'lovdmx-this':'' }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">MAC地址</label>
									<div class="lovdmx-input-block">
										<input type="text" name="spriteMac" value="${param.spriteMac }"
											class="lovdmx-input" placeholder="请输入" />
									</div>
								</div>
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">在线状态</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty spriteDeviceInfo.isOnline }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect isOnline_info"
															readonly value="请选择" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="isOnline"
															value="-1" /> <span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择</dd>
														<dd data-value="0">未在线</dd>
														<dd data-value="1">在线</dd>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect isOnline_info"
															readonly
															value="${spriteDeviceInfo.isOnline eq 1 ?'在线':'未在线' }"
															data-value="${spriteDeviceInfo.isOnline }" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input"
															value='${spriteDeviceInfo.isOnline }' name="isOnline" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择</dd>
														<dd data-value="0"
															class="${spriteDeviceInfo.isOnline eq 0 ?'lovdmx-this':'' }">未在线</dd>
														<dd data-value="1"
															class="${spriteDeviceInfo.isOnline eq 1 ?'lovdmx-this':'' }">在线</dd>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="spriteDevice-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path}/lovdmx/ht/spriteDevice/batchDelete.do"
										onclick="batchDelete(this);">批量删除</button> --%>
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path }/lovdmx/ht/spriteDevice/add.do"
										id="spriteDevice-add">添加</button> --%>
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
														<span>机架下标</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>机架名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>MAC地址</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>以丢失的灯光文件</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>已存在的灯光文件</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>以丢失的定时任务</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>已存在的定时任务</span>
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
														<td colspan="9" style="color: red; font-size: 16px;">没有数据！！</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${pageInfo.list }" var="list"
														varStatus="vs">
														<tr class="spriteId${list.spriteId }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.spriteId }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="spriteId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rackIndex">${list.rackIndex }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rackName">${list.rackName }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="spriteMac">${list.spriteMac }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="loseLmxMd5s">${list.loseLmxNames }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="existLmxMd5s">${list.existLmxNames }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="loseTaskNames">${list.loseTaskNames }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="existTaskNames">${list.existTaskNames }</span>
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
																		data-link="${path }/lovdmx/ht/spriteDevice/edit.do?spriteId=${list.spriteId}"
																		class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs spriteDevice-edit">
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
													href="${path }/lovdmx/ht/spriteDevice/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${spriteDeviceInfo.projectId}&projectName=${spriteDeviceInfo.projectName}&rackId=${spriteDeviceInfo.rackId}&rackName=${spriteDeviceInfo.rackName}&spriteMac=${param.spriteMac}&isOnline=${param.isOnline}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/spriteDevice/index.do?pageNo=${pageNo2}&projectId=${spriteDeviceInfo.projectId}&projectName=${spriteDeviceInfo.projectName}&rackId=${spriteDeviceInfo.rackId}&rackName=${spriteDeviceInfo.rackName}&spriteMac=${param.spriteMac}&isOnline=${param.isOnline}"
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
													href="${path }/lovdmx/ht/spriteDevice/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${spriteDeviceInfo.projectId}&projectName=${spriteDeviceInfo.projectName}&rackId=${spriteDeviceInfo.rackId}&rackName=${spriteDeviceInfo.rackName}&spriteMac=${param.spriteMac}&isOnline=${param.isOnline}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/spriteDevice/index.do?projectId=${spriteDeviceInfo.projectId}&projectName=${spriteDeviceInfo.projectName}&rackId=${spriteDeviceInfo.rackId}&rackName=${spriteDeviceInfo.rackName}&spriteMac=${param.spriteMac}&isOnline=${param.isOnline}'>

											<span class="lovdmx-page-skip"> 到第<input
												type="text" name="pageNo" min="1"
												value="${pageInfo.pageNum }" class="lovdmx-input"
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
		$("#spriteDevice-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//在线状态
					var isOnline = $(".lovdmx-select-title .isOnline_info")
							.attr("data-value");
					//机架id
					var rackId = $(".lovdmx-select-title .rack_info")
							.attr("data-value");
					var rackName = $(".lovdmx-select-title .rack_info").val();
					var projectId = $(".lovdmx-select-title .project_info")
							.attr("data-value");
					var projectName = $(".lovdmx-select-title .project_info")
							.val();
					$form.find("input[name='isOnline']").val(
							isOnline == -1 ? null : isOnline);
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