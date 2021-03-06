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
<title>雷舞后台- 我的密码</title>

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
							<a>主页</a> <span>/</span> <a><cite>设置</cite></a> <span>/</span> <a
								href="javascript:;"><cite> 我的密码</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-row lovdmx-col-space15">
							<div class="lovdmx-col-md12">
								<div class="lovdmx-card">
									<div class="lovdmx-card-header">修改密码</div>
									<div class="lovdmx-card-body">
										<div class="lovdmx-form">
											<input type="hidden" value="${sessionScope.iadmin.manageId }"
												name="manageId" />
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">当前密码</label>
												<div class="lovdmx-input-inline">
													<input type="password" name="password" data-status="0"
														class="lovdmx-input" />
												</div>
											</div>
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">新密码</label>
												<div class="lovdmx-input-inline">
													<input type="password" name="newPassword"
														class="lovdmx-input" />
												</div>
												<div class="lovdmx-form-mid lovdmx-word-aux">6到16个字符</div>
											</div>
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">确认新密码</label>
												<div class="lovdmx-input-inline">
													<input type="password" name="confirmNewPassword"
														class="lovdmx-input" />
												</div>
											</div>
											<div class="lovdmx-form-item">
												<div class="lovdmx-input-block">
													<button class="lovdmx-btn" id="update-manage-password">确认修改</button>
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
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {

		$("input[name='password']").blur(
				function() {
					//jquery 对象
					var $manageId = $("input[name='manageId']");
					var $password = $("input[name='password']");

					//值
					var manageId = $manageId.val();
					var password = $password.val();

					if (password != null && password != ""
							&& password != undefined) {
						$.ajax({
							type : "POST",
							url : $("#pageContext").val()
									+ "/lovdmx/ht/manage/findByPassword.do",
							data : "manageId=" + manageId + "&password="
									+ password,
							success : function(msg) {
								if (msg == "true") {
									$password.attr("data-status", "1");
								} else {
									$password.attr("data-status", "0");
								}
							}
						});
					}
				});

		$("#update-manage-password")
				.click(
						function() {

							//jquery 对象
							var $manageId = $("input[name='manageId']");
							var $password = $("input[name='password']");
							var $newPassword = $("input[name='newPassword']");
							var $confirmNewPassword = $("input[name='confirmNewPassword']");

							//值
							var password = $password.val();
							var manageId = $manageId.val();
							var newPassword = $newPassword.val();
							var confirmNewPassword = $confirmNewPassword.val();

							if (password == null || password == ""
									|| password == undefined) {
								layer.tips('必填项不能为空', "input[name='password']",
										{
											tips : [ 1, '#000' ],
											time : 4000
										});
								$password.addClass("lovdmx-form-danger").focus();
								return;
							}

							if (newPassword == null
									|| newPassword == ""
									|| newPassword == undefined
									|| newPassword.length < 6 || newPassword.length > 16) {
								layer.tips('密码必须6到12位，且不能出现空格',
										"input[name='newPassword']", {
											tips : [ 1, '#000' ],
											time : 4000
										});
								$newPassword.addClass("lovdmx-form-danger").focus();
								return;
							}

							if (newPassword != confirmNewPassword) {
								layer.tips('两次密码输入不一致',
										"input[name='confirmNewPassword']", {
											tips : [ 1, '#000' ],
											time : 4000
										});
								$confirmNewPassword.addClass("lovdmx-form-danger").focus();
								return;
							}
							
							var status=$password.attr("data-status");
							if(status==0){
								layer.tips('密码错误',
										"input[name='password']", {
											tips : [ 1, '#000' ],
											time : 4000
										});
								$password.addClass("lovdmx-form-danger").focus();
							}
							
							if(password==newPassword){
								layer.tips('新密码不能和旧密码一致!',
										"input[name='newPassword']", {
											tips : [ 1, '#000' ],
											time : 4000
										});
								$newPassword.addClass("lovdmx-form-danger").focus();
							}

							$.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/manage/updatePwd.do",
								data : "manageId=" + manageId + "&password="
										+ newPassword ,
								success : function(msg) {
									//重新登录
									if(msg=="true"){
										layer.alert("密码,修改成功", {
											icon : 6
										});
										window.location.href = $("#pageContext").val()+"/lovdmx/ht/remove.do";
									}else{
										layer.alert("密码,修改失败", {
											icon : 6
										});
									}
								}
							});

						});
	});
</script>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>