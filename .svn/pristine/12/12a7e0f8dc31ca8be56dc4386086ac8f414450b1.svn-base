<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${path }/image/lei.jpg"
	type="image/x-icon" />
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
<title>中控后台-前台管理员</title>


<style type="text/css">
</style>

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
							<a>主页</a> <span>/</span> <a><cite>权限</cite></a> <span>/</span> <a
								href="javascript:;"><cite>权限功能</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<div class="lovdmx-table">
								<div class="table-btn">
									<button class="lovdmx-btn tableadmin-btn" id="power-add"
										data-link="${path }/lovdmx/ht/limit/power/add.do">添加</button>
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
														<span>功能名称</span>
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
											<c:forEach items="${powerList }" var="list">
												<tr class="powerId${list.id }">
													<td class="table-checked lovdmx-unselect">
														<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
															data-index="${list.id }">
															<i
																class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="powerId">${list.id }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="name">${list.name }</span>
														</div>
													</td>
													<td class="table-last lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<a
																data-link="${path }/lovdmx/ht/limit/power/edit.do?id=${list.id}"
																class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs power-edit">
																<i class="icon-bianji lovdmx-icon iconfont"></i> 编辑
															</a><a
																class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																<i class="icon-shanchu lovdmx-icon iconfont"></i> 删除
															</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>