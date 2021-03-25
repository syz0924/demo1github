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

<title>中控后台-上传灯光文件列表</title>

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
								href="${path }/lovdmx/ht/uploadEdlmx/index.do"><cite>上传灯光文件列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/uploadEdlmx/index.do" method="GET">
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty uploadEdlmx.projectId }">
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
															readonly value="${uploadEdlmx.projectName }"
															data-value="${uploadEdlmx.projectId }" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input"
															value='${uploadEdlmx.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${uploadEdlmx.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${uploadEdlmx.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">上传状态</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty uploadEdlmx.rtrLoaded }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rtrLoaded_info"
															readonly value="所有" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="rtrLoadedValue" /><input
															type="hidden" class="lovdmx-input" name="rtrLoaded" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">所有</dd>
														<c:forEach items="${rtrLoadedList }" var="lst">
															<dd data-value="${lst.index }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rtrLoaded_info"
															readonly value="${uploadEdlmx.rtrLoadedValue }"
															data-value="${uploadEdlmx.rtrLoaded }" placeholder="请选择" />
														<input type="hidden" class="lovdmx-input"
															value='${uploadEdlmx.rtrLoadedValue }'
															name="rtrLoadedValue" /> <input type="hidden"
															class="lovdmx-input" value='${uploadEdlmx.rtrLoaded }'
															name="rtrLoaded" /><span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">所有</dd>
														<c:forEach items="${rtrLoadedList }" var="lst">
															<dd data-value="${lst.index }"
																class="${uploadEdlmx.rtrLoaded eq lst.index?'lovdmx-this':'' }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">文件角色</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty uploadEdlmx.uploadRole }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect uploadRole_info"
															readonly value="所有" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="uploadRoleValue" /><input
															type="hidden" class="lovdmx-input" name="uploadRole" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">所有</dd>
														<c:forEach items="${uploadRoleList }" var="lst">
															<dd data-value="${lst.index }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect uploadRole_info"
															readonly value="${uploadEdlmx.uploadRoleValue }"
															data-value="${uploadEdlmx.uploadRole }" placeholder="请选择" />
														<input type="hidden" class="lovdmx-input"
															value='${uploadEdlmx.uploadRoleValue }'
															name="uploadRoleValue" /> <input type="hidden"
															class="lovdmx-input" value='${uploadEdlmx.uploadRole }'
															name="uploadRole" /><span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">所有</dd>
														<c:forEach items="${uploadRoleList }" var="lst">
															<dd data-value="${lst.index }"
																class="${uploadEdlmx.uploadRole eq lst.index?'lovdmx-this':'' }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>


								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="intelligentGateway-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path}/lovdmx/ht/intelligentGateway/batchDelete.do"
										onclick="batchDelete(this);">批量删除</button> --%>
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path }/lovdmx/ht/intelligentGateway/add.do"
										id="intelligentGateway-add">添加</button> --%>
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
														<span>文件下标</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>文件路径</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>文件MD值</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>时长</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>上传状态</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>上传角色</span>
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
														<tr class="edlmxId${list.edlmxId }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.edlmxId }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="edlmxId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="fileIndex">${list.fileIndex }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="filePath">${list.filePath }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="md5">${list.md5 }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="time">${list.time }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rtrLoaded"> ${list.rtrLoaded eq 0?"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未分发</button>":"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-green'>已分发</button>"}
																	</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="uploadRole"> ${list.uploadRole eq 1?"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>Sprite上传</button>":"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-green'>web上传</button>"}
																	</span>
																</div>
															</td>
															<td class="table-last lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<a
																		class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-shanchu lovdmx-icon iconfont"></i> 编辑
																	</a><a
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
													href="${path }/lovdmx/ht/uploadEdlmx/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${uploadEdlmx.projectId}&projectName=${uploadEdlmx.projectName }&rtrLoaded=${param.rtrLoaded }&rtrLoadedValue=${param.rtrLoadedValue }&uploadRole=${param.uploadRole}&uploadRoleValue=${param.uploadRoleValue}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/uploadEdlmx/index.do?pageNo=${pageNo2}&projectId=${uploadEdlmx.projectId}&projectName=${uploadEdlmx.projectName }&rtrLoaded=${param.rtrLoaded }&rtrLoadedValue=${param.rtrLoadedValue }&uploadRole=${param.uploadRole}&uploadRoleValue=${param.uploadRoleValue}"
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
													href="${path }/lovdmx/ht/uploadEdlmx/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${uploadEdlmx.projectId}&projectName=${uploadEdlmx.projectName }&rtrLoaded=${param.rtrLoaded }&rtrLoadedValue=${param.rtrLoadedValue }&uploadRole=${param.uploadRole}&uploadRoleValue=${param.uploadRoleValue}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/uploadEdlmx/index.do?projectId=${uploadEdlmx.projectId}&projectName=${uploadEdlmx.projectName }&rtrLoaded=${param.rtrLoaded }&rtrLoadedValue=${param.rtrLoadedValue }&uploadRole=${param.uploadRole}&uploadRoleValue=${param.uploadRoleValue}'>

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
		$("#intelligentGateway-query")
				.click(
						function() {

							var $form = $(".lovdmx-form");
							//文件类型
							var projectName = $(
									".lovdmx-select-title .project_info")
									.val();
							var projectId = $(
									".lovdmx-select-title .project_info")
									.attr("data-value");
							var rtrLoadedValue = $(
									".lovdmx-select-title .rtrLoaded_info")
									.val();
							var rtrLoaded = $(
									".lovdmx-select-title .rtrLoaded_info")
									.attr("data-value");

							var uploadRoleValue = $(
									".lovdmx-select-title .uploadRole_info")
									.val();
							var uploadRole = $(
									".lovdmx-select-title .uploadRole_info")
									.attr("data-value");

							$form.find("input[name='projectName']").val(
									projectId == -1 ? null : projectName);
							$form.find("input[name='projectId']").val(
									projectId == -1 ? null : projectId);
							$form.find("input[name='rtrLoadedValue']").val(
									rtrLoaded == -1 ? null : rtrLoadedValue);
							$form.find("input[name='rtrLoaded']").val(
									rtrLoaded == -1 ? null : rtrLoaded);

							$form.find("input[name='uploadRoleValue']").val(
									uploadRole == -1 ? null : uploadRoleValue);
							$form.find("input[name='uploadRole']").val(
									uploadRole == -1 ? null : uploadRole);
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
</html>