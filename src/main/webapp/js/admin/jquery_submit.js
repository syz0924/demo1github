
// 保存后端管理员
$("body")
		.on(
				"click",
				"#lovdmx-submit-manage",
				function() {
					// jquery对象
					var $loginName = $("#lovdmx-popup-window input[name='loginName']");
					var $password = $("#lovdmx-popup-window input[name='password']");
					var $nickName = $("#lovdmx-popup-window input[name='nickName']");
					var $phone = $("#lovdmx-popup-window input[name='phone']");
					var $email = $("#lovdmx-popup-window input[name='email']");

					// 值
					var loginName = $loginName.val();
					var password = $password.val();
					var nickName = $nickName.val();
					var phone = $phone.val();
					var email = $email.val();

					if (loginName == "" || loginName == null
							|| loginName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$loginName.addClass("lovdmx-form-danger").focus();
						return;
					}

					if (password == "" || password == null
							|| password == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$password.addClass("lovdmx-form-danger").focus();
						return;
					}

					if (nickName == "" || nickName == null
							|| nickName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$nickName.addClass("lovdmx-form-danger").focus();
						return;
					}
					
					
					var myreg = /^1[34578]\d{9}$/;
					if (phone != "" && phone != null
								&& phone != undefined) {
						 if(!myreg.test(phone)){
							  layer.msg('请输入有效的手机号码！', {
									icon : 5
							  });
							  
							  $phone.addClass("lovdmx-form-danger").val("").focus();
							  return false;
						 }
					 }
					 
					 varreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
					 if (email != "" && email != null
								&& email != undefined) {
						 if(!varreg.test(email)){
							  layer.msg('邮箱格式不正确，请重新输入！', {
									icon : 5
							  });
							  
							  $email.addClass("lovdmx-form-danger").val("").focus();
							  return false;
						 }
					  }
					

					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/manage/save.do",
								data : "loginName=" + loginName
										+ "&password=" + password
										+ "&nickName=" + nickName
										+ "&phone=" + phone + "&email="
										+ email,
								success : function(json) {
									if (json.result == "添加失败") {
										layer.alert(json.result, {
											icon : 5
										});

									} else {
										var obj = json.manage;
										var addManage = "";
										// 获取时间
										var time = formatDateTime(obj.createTime);
										
										//项目路径
										var path=$("#pageContext").val();
										
										// 组装
										addManage += "<tr class='manageId"+obj.manageId+"'>";
											addManage += "<td class='table-checked lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
													addManage += "data-index='"+obj.manageId+"'>";
													addManage += "<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='manageId'>"+obj.manageId+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='loginName'>"+obj.loginName+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='nickName'>"+obj.nickName+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='phone'>"+obj.phone+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='email'>"+obj.email+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='lovdmx-unselect  account_status'> ";
												addManage += "<div class='lovdmx-table-cell'> ";
													addManage += "<input type='checkbox' class='none' lay-filter='switch' ";
														addManage += "lay-verify='required' name='istop' lay-skin='switch' ";
														addManage += "lay-text='ON|OFF' checked=checked /> ";
													addManage += "<div ";
														addManage += "class='status lovdmx-unselect lovdmx-form-switch lovdmx-form-onswitch'> ";
														addManage += "<em>ON</em><i></i> ";
													addManage += "</div> ";
												addManage += "</div> ";
											addManage += "</td> ";
											addManage += "<td class='lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<span class='createTime'>"+time+"</span>";
												addManage += "</div>";
											addManage += "</td>";
											addManage += "<td class='table-last lovdmx-unselect'>";
												addManage += "<div class='lovdmx-table-cell'>";
													addManage += "<a data-manageId='"+obj.manageId+"'";
														addManage += "class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs manage-edit'>";
														addManage += "<i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
													addManage += "</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs'";
														addManage += "data-link='"+path+"/lovdmx/ht/manage/delelte.do?manageId="+obj.manageId+"'";
														addManage += "onclick='mydel(this)'> ";
														addManage += "<i class='icon-shanchu lovdmx-icon iconfont lovdmx-manage-del'></i> 删除";
													addManage += "</a>";
												addManage += "</div>";
											addManage += "</td>";
										addManage += "</tr>";
										

										$("tbody").prepend(addManage);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});

				});

// 修改后端管理员信息
$("body")
		.on(
				"click",
				"#lovdmx-update-manage",
				function() {
					// jquery对象
					var $loginName = $("#lovdmx-popup-window input[name='loginName']");
					var $nickName = $("#lovdmx-popup-window input[name='nickName']");
					var $phone = $("#lovdmx-popup-window input[name='phone']");
					var $email = $("#lovdmx-popup-window input[name='email']");
					var $manageId = $("#lovdmx-popup-window input[name='manageId']");

					// 值
					var loginName = $loginName.val();
					var nickName = $nickName.val();
					var phone = $phone.val();
					var email = $email.val();
					var manageId = $manageId.val();

					if (loginName == "" || loginName == null
							|| loginName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$loginName.addClass("lovdmx-form-danger").focus();
						return;
					}
					
					var myreg = /^1[34578]\d{9}$/;
					
					if (phone != "" && phone != null
								&& phone != undefined) {
						 if(!myreg.test(phone)){
							  layer.msg('请输入有效的手机号码！', {
									icon : 5
							  });
							  
							  $phone.addClass("lovdmx-form-danger").val("").focus();
							  return false;
						 }
					 }
					 
					 varreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
					 if (email != "" && email != null
								&& email != undefined) {
						 if(!varreg.test(email)){
							  layer.msg('邮箱格式不正确，请重新输入！', {
									icon : 5
							  });
							  
							  $email.addClass("lovdmx-form-danger").val("").focus();
							  return false;
						 }
					  }
					 
					 

					if (nickName == "" || nickName == null
							|| nickName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$nickName.addClass("lovdmx-form-danger").focus();
						return;
					}

					$.ajax({
						type : "POST",
						url : $("#pageContext").val()
								+ "/lovdmx/ht/manage/updateInfo.do",
						data : "manageId=" + manageId + "&loginName="
								+ loginName + "&nickName=" + nickName
								+ "&phone=" + phone + "&email=" + email,
						success : function(msg) {
							if (msg == "修改失败") {
								layer.alert(msg, {
									icon : 5
								});
							} else {
								// 更新
								var $parentManage = $(".manageId"
										+ manageId);
								$parentManage.find(".loginName").html(
										loginName);
								$parentManage.find(".nickName").html(
										nickName);
								$parentManage.find(".phone").html(phone);
								$parentManage.find(".email").html(email);

								layer.alert(msg, {
									icon : 6
								});
							}
							close();
						}
					});

				});


//保存前端管理员
$("body")
		.on(
				"click",
				"#lovdmx-submit-account",
				function() {
					// jquery对象
					var $userName = $("#lovdmx-popup-window input[name='userName']");
					var $password = $("#lovdmx-popup-window input[name='password']");
					var $nickName = $("#lovdmx-popup-window input[name='nickName']");
					var $role = $("#lovdmx-popup-window input[name='role']");
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $status = $("#lovdmx-popup-window input[name='status']");

					// 值
					var userName = $userName.val();
					var password = $password.val();
					var nickName = $nickName.val();
					var roleName = $role.val();
					var projectName = $project.val();
					var roleId = $role.attr("data-value");
					var projectId = $project.attr("data-value");
					var status = $status.is(':checked')==true?1:0;

					if (userName == "" || userName == null
							|| userName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$userName.addClass("lovdmx-form-danger").focus();
						return;
					}

					if (password == "" || password == null
							|| password == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$password.addClass("lovdmx-form-danger").focus();
						return;
					}

					if (nickName == "" || nickName == null
							|| nickName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$nickName.addClass("lovdmx-form-danger").focus();
						return;
					}
					if(roleId==-1){
						layer.msg('请选择角色', {
							icon : 5
						});
						$role.addClass("lovdmx-form-danger").focus();
						return;
					}else if(projectId==-1 && roleId!=1){
						layer.msg('请选择项目', {
							icon : 5
						});
						$project.addClass("lovdmx-form-danger").focus();
						return;
					}
					//超级管理员 项目id 默认为0
					if(roleId==1){
						projectId=0;
					}

					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/account/save.do",
								data : "userName=" + userName
										+ "&password=" + password
										+ "&nickName=" + nickName
										+ "&roleId=" + roleId + "&projectId="
										+ projectId+"&status="+status,
								success : function(json) {
									if (json.result == "添加失败") {
										layer.alert(json.result, {
											icon : 5
										});

									} else {
										var obj = json.account;
										var addAccount = "";
										// 获取时间
										var time = formatDateTime(obj.createTime);
										
										//项目路径
										var path=$("#pageContext").val();
										
										// 组装
										addAccount += "<tr class='accountId"+obj.accountId+"'>";
											addAccount += "<td class='table-checked lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
													addAccount += "data-index='"+obj.accountId+"'>";
													addAccount += "<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='accountId'>"+obj.accountId+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='userName'>"+obj.userName+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='nickName'>"+obj.nickName+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='roleName'>"+roleName+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='projectName'>"+projectName+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='isOnline'>";
														addAccount += " <button class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未在线</button>";
													addAccount += "</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											
											addAccount += "<td class='lovdmx-unselect  account_status'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<input type='checkbox' class='none' lay-filter='switch'";
														addAccount += "lay-verify='required' name='status' lay-skin='switch'";
														addAccount += "lay-text='ON|OFF'"+(obj.status==1?'checked=checked':'')+" />";
													addAccount += "<div class='status lovdmx-unselect lovdmx-form-switch "+(obj.status==1?'lovdmx-form-onswitch':'')+" '>";
														addAccount += "<em>"+(obj.status==1?'NO':'OFF')+"</em><i></i>";
													addAccount += "</div>";
												addAccount += "</div>";
											addAccount += "</td>";
											
											addAccount += "<td class='lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<span class='createTime'>"+time+"</span>";
												addAccount += "</div>";
											addAccount += "</td>";
											addAccount += "<td class='table-last lovdmx-unselect'>";
												addAccount += "<div class='lovdmx-table-cell'>";
													addAccount += "<a data-link='"+path+"/lovdmx/ht/account/edit.do?accountId="+obj.accountId+"' ";
														addAccount += "class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs account-editPassword'>";
														addAccount += "<i class='icon-bianji lovdmx-icon iconfont'></i> 修改密码 ";
														addAccount += "</a> <a data-link='"+path+"/lovdmx/ht/account/edit.do?accountId="+obj.accountId+"' ";
														addAccount += "class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs account-edit'>";
														addAccount += "<i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
														addAccount += "</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs'";
														addAccount += "data-link='"+path+"/lovdmx/ht/account/delelte.do?accountId="+obj.accountId+"'";
														addAccount += "onclick='mydel(this)'> ";
														addAccount += "<i class='icon-shanchu lovdmx-icon iconfont lovdmx-account-del'></i> 删除";
													addAccount += "</a>";
												addAccount += "</div>";
											addAccount += "</td>";
										addAccount += "</tr>";

										$("tbody").prepend(addAccount);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});

				});



//修改前端管理员信息
$("body")
		.on(
				"click",
				"#lovdmx-update-account",
				function() {
					// jquery对象
					var $nickName = $("#lovdmx-popup-window input[name='nickName']");
					var $role = $("#lovdmx-popup-window input[name='role']");
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $status = $("#lovdmx-popup-window input[name='status']");
					var $accountId = $("#lovdmx-popup-window input[name='accountId']");

					// 值
					var nickName = $nickName.val();
					var roleName = $role.val();
					var projectName = $project.val();
					var roleId = $role.attr("data-value");
					var projectId = $project.attr("data-value");
					var status = $status.is(':checked')==true?1:0;
					var accountId = $accountId.val();

					if (nickName == "" || nickName == null
							|| nickName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$nickName.addClass("lovdmx-form-danger").focus();
						return;
					}
					
					if(roleId==-1){
						layer.msg('请选择角色', {
							icon : 5
						});
						$role.addClass("lovdmx-form-danger").focus();
						return;
					}else if(projectId==-1 && roleId!=1){
						layer.msg('请选择项目', {
							icon : 5
						});
						$project.addClass("lovdmx-form-danger").focus();
						return;
					}

					$.ajax({
						type : "POST",
						url : $("#pageContext").val()
								+ "/lovdmx/ht/account/update.do",
						data : "accountId=" + accountId+ "&nickName=" + nickName
								+ "&roleId=" + roleId + "&projectId=" + projectId+ "&status=" + status,
						success : function(msg) {
							if (msg == "修改失败") {
								layer.alert(msg, {
									icon : 5
								});
							} else {
								// 更新
								var $parentManage = $(".accountId"
										+ accountId);
								$parentManage.find(".nickName").html(
										nickName);
								$parentManage.find(".roleId").html(roleName);
								$parentManage.find(".projectName").html(roleId==1?"所有":projectName);
								
								var obj=$parentManage.find(".status");
								if (status==1) {
									$(obj).addClass("lovdmx-form-onswitch");
									$(obj).find("em").html("ON");
									$(obj).prev("input[type='checkbox']").attr("checked","checked");
								}else{
									$(obj).removeClass("lovdmx-form-onswitch");
									$(obj).find("em").html("OFF");
									$(obj).prev("input[type='checkbox']").attr("checked",null);
								}
								
								layer.alert(msg, {
									icon : 6
								});
							}
							close();
						}
					});

				});

//修改前端管理员密码信息
$("body")
		.on(
				"click",
				"#lovdmx-update-accountPassword",
				function() {
					// jquery对象
					var $password = $("#lovdmx-popup-window input[name='password']");
					var $accountId = $("#lovdmx-popup-window input[name='accountId']");

					// 值
					var password = $password.val();
					var accountId = $accountId.val();

					if (password == "" || password == null
							|| password == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$password.addClass("lovdmx-form-danger").focus();
						return;
					}

					$.ajax({
						type : "POST",
						url : $("#pageContext").val()
								+ "/lovdmx/ht/account/update.do",
						data : "accountId=" + accountId+ "&password=" + password,
						success : function(msg) {
							if (msg == "修改失败") {
								layer.alert(msg, {
									icon : 5
								});
							} else {
								layer.alert(msg, {
									icon : 6
								});
							}
							close();
						}
					});

				});

//添加项目
$("body")
		.on(
				"click",
				"#submit-project",
				function() {
					// jquery对象
					var $projectName = $("#lovdmx-popup-window input[name='projectName']");
					// 值
					var projectName = $projectName.val();
					if (projectName == "" || projectName == null
							|| projectName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$projectName.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/project/save.do",
								data : {
									"projectName" : projectName
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										var obj = json.project;
										var addProject = "";
										
										// 获取时间
										var time = formatDateTime(obj.createTime);
										
										//项目路径
										var path=$("#pageContext").val();
										
										// 组装
										addProject += "<tr class='projectId"+obj.projectId+"'>";
											addProject += "<td class='table-checked lovdmx-unselect'>";
												addProject += "<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
													addProject += "data-index='"+obj.projectId+"'>";
													addProject += "<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
												addProject += "</div>";
											addProject += "</td>";
											addProject += "<td class='lovdmx-unselect'>";
												addProject += "<div class='lovdmx-table-cell'>";
													addProject += "<span class='projectId'>"+obj.projectId+"</span>";
												addProject += "</div>";
											addProject += "</td>";
											addProject += "<td class='lovdmx-unselect'>";
												addProject += "<div class='lovdmx-table-cell'>";
													addProject += "<span class='projectName'>"+obj.projectName+"</span>";
												addProject += "</div>";
											addProject += "</td>";
											addProject += "<td class='lovdmx-unselect'>";
												addProject += "<div class='lovdmx-table-cell'>";
													addProject += "<span class='createTime'>"+time+"</span>";
												addProject += "</div>";
											addProject += "</td>";
											addProject += "<td class='table-last lovdmx-unselect'>";
												addProject += "<div class='lovdmx-table-cell'>";
													addProject += "<a data-link='"+path+"/lovdmx/ht/project/edit.do?projectId="+obj.projectId+"' ";
														addProject += "class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs project-edit'>";
														addProject += "<i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
													addProject += "</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'> ";
														addProject += "<i class='icon-shanchu lovdmx-icon iconfont'></i> 删除";
													addProject += "</a>";
												addProject += "</div>";
											addProject += "</td>";
										addProject += "</tr>";

										$("tbody").prepend(addProject);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});


//修改项目
$("body").on("click", "#update-project", function() {
	// jquery对象
	var $projectName = $("#lovdmx-popup-window input[name='projectName']");
	var $projectId=$("#lovdmx-popup-window input[name='projectId']");

	// 值
	var projectName = $projectName.val();
	var projectId =$projectId.val();
	if (projectName == "" || projectName == null
			|| projectName == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$projectName.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	
	$
	.ajax({
		type : "POST",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/project/update.do",
		data : {
			"projectId":projectId,
			"projectName" : projectName
		},
		success : function(result) {
			//返回状态
			if (result == "修改失败") {
				layer.alert(result, {
					icon : 5
				});
			} else {
				// 更新
				$(".projectId"+projectId).find(".projectName").html(projectName);
				layer.alert(result, {
					icon : 6
				});
			}
			close();
			
		}
	});
	
});


//添加项目
$("body")
		.on(
				"click",
				"#submit-rackDevice",
				function() {
					// jquery对象
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $rackName = $("#lovdmx-popup-window input[name='rackName']");
					var $rackIndex = $("#lovdmx-popup-window input[name='rackIndex']");
					var $splitVideoX = $("#lovdmx-popup-window input[name='splitVideoX']");
					var $splitVideoY = $("#lovdmx-popup-window input[name='splitVideoY']");
					var $splitVideoWidth = $("#lovdmx-popup-window input[name='splitVideoWidth']");
					var $splitVideoHeight = $("#lovdmx-popup-window input[name='splitVideoHeight']");
					var $rackLongitude = $("#lovdmx-popup-window input[name='rackLongitude']");
					var $rackLatitude = $("#lovdmx-popup-window input[name='rackLatitude']");
					
					// 值
					var projectName = $project.val();
					var projectId = $project.attr("data-value");
					var rackName = $rackName.val();
					var rackIndex = $rackIndex.val();
					var splitVideoX = $splitVideoX.val();
					var splitVideoY = $splitVideoY.val();
					var splitVideoWidth = $splitVideoWidth.val();
					var splitVideoHeight = $splitVideoHeight.val();
					var rackLongitude = $rackLongitude.val();
					var rackLatitude = $rackLatitude.val();
					
					if(projectId==-1){
						layer.msg('请选择项目列表', {
							icon : 5
						});
						$project.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					if (rackName == "" || rackName == null
							|| rackName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackName.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否存在
						if(!rackNameIsExits(projectId,rackName))
							return;
					}
					if (rackIndex == "" || rackIndex == null
							|| rackIndex == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackIndex.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(rackIndex>=0){
							var uu = Math.floor(rackIndex);
							if(uu==rackIndex){
								bool=true;
								
								//判断是否存在
								if(!rackIndexIsExits(projectId,rackName,rackIndex))
									return;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$rackIndex.val("");
							$rackIndex.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoX == "" || splitVideoX == null
							|| splitVideoX == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoX.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoX>=0){
							var uu = Math.floor(splitVideoX);
							if(uu==splitVideoX){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoX.val("");
							$splitVideoX.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoY == "" || splitVideoY == null
							|| splitVideoY == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoY.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoY>=0){
							var uu = Math.floor(splitVideoY);
							if(uu==splitVideoY){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoY.val("");
							$splitVideoY.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoWidth == "" || splitVideoWidth == null
							|| splitVideoWidth == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoWidth.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoWidth>=0){
							var uu = Math.floor(splitVideoWidth);
							if(uu==splitVideoWidth){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoWidth.val("");
							$splitVideoWidth.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoHeight == "" || splitVideoHeight == null
							|| splitVideoHeight == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoHeight.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoHeight>=0){
							var uu = Math.floor(splitVideoHeight);
							if(uu==splitVideoHeight){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoHeight.val("");
							$splitVideoHeight.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (rackLongitude == "" || rackLongitude == null
							|| rackLongitude == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackLongitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if(!(rackLongitude>=0)){
						layer.msg('格式错误,请输入小数', {
							icon : 5
						});
						
						$rackLongitude.val("");
						$rackLongitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					if (rackLatitude == "" || rackLatitude == null
							|| rackLatitude == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackLatitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if(!(rackLatitude>=0)){
						layer.msg('格式错误,请输入小数', {
							icon : 5
						});
						
						$rackLatitude.val("");
						$rackLatitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/rackDevice/save.do",
								data : {
									"projectId" : projectId,
									"rackName" : rackName,
									"rackIndex" : rackIndex,
									"splitVideoX" : splitVideoX,
									"splitVideoY" : splitVideoY,
									"splitVideoWidth" : splitVideoWidth,
									"splitVideoHeight" : splitVideoHeight,
									"rackLongitude" : rackLongitude,
									"rackLatitude" : rackLatitude,
								},
								success : function(json) {
									//返回状态
									if (json.result == "保存失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										
										var rackDevice = json.rackDevice;
										var addRackDevice = "";
										
										// 获取时间
										var time = formatDateTime(rackDevice.createTime);
										
										//项目路径
										var path=$("#pageContext").val();
										
										addRackDevice +="<tr class='rackDeviceId"+rackDevice.rackId+"'>";
										addRackDevice +="<td class='table-checked lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
												addRackDevice +=" data-index='"+rackDevice.rackId+"'>";
												addRackDevice +=" <i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='rackId'>"+rackDevice.rackId+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='rackIndex'>"+rackDevice.rackIndex+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='rackName'>"+rackDevice.rackName+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect table-content'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='projectName'>"+projectName+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect table-content'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='splitVideoX'>"+rackDevice.splitVideoX+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='splitVideoY'>"+rackDevice.splitVideoY +"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='splitVideoWidth'>"+rackDevice.splitVideoWidth +"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='splitVideoHeight'>"+rackDevice.splitVideoHeight +"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='current'>0</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='voltage'>0</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='rackLongitude'>"+rackDevice.rackLongitude +"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='rackLatitude'>"+rackDevice.rackLatitude +"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<span class='createTime'>"+time+"</span>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
										addRackDevice +="<td class='table-last lovdmx-unselect'>";
											addRackDevice +="<div class='lovdmx-table-cell'>";
												addRackDevice +="<a ";
													addRackDevice +=" data-link='"+path+"/lovdmx/ht/rackDevice/edit.do?rackId="+rackDevice.rackId+"'";
													addRackDevice +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs rackDevice-edit'>";
													addRackDevice +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
												addRackDevice +="</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs' ";
													addRackDevice +=" data-link='"+path+"/lovdmx/ht/rackDevice/delete.do?rackId="+rackDevice.rackId+"' ";
													addRackDevice +=" onclick='mydel(this)'> <i";
													addRackDevice +=" class='icon-shanchu lovdmx-icon iconfont lovdmx-rackDevice-del'></i>";
													addRackDevice +=" 删除";
												addRackDevice +="</a>";
											addRackDevice +="</div>";
										addRackDevice +="</td>";
									addRackDevice +="</tr>";

									$("tbody").prepend(addRackDevice);
									layer.alert(json.result, {
										icon : 6
									});
								}
								close();
							}
						});
						
					});

//添加项目
$("body")
		.on(
				"click",
				"#update-rackDevice",
				function() {
					// jquery对象
					var $rackId = $("#lovdmx-popup-window input[name='rackId']");
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $rackName = $("#lovdmx-popup-window input[name='rackName']");
					var $rackIndex = $("#lovdmx-popup-window input[name='rackIndex']");
					var $beforeRackName = $("#lovdmx-popup-window input[name='beforeRackName']");
					var $beforeRackIndex = $("#lovdmx-popup-window input[name='beforeRackIndex']");
					var $splitVideoX = $("#lovdmx-popup-window input[name='splitVideoX']");
					var $splitVideoY = $("#lovdmx-popup-window input[name='splitVideoY']");
					var $splitVideoWidth = $("#lovdmx-popup-window input[name='splitVideoWidth']");
					var $splitVideoHeight = $("#lovdmx-popup-window input[name='splitVideoHeight']");
					var $rackLongitude = $("#lovdmx-popup-window input[name='rackLongitude']");
					var $rackLatitude = $("#lovdmx-popup-window input[name='rackLatitude']");
					
					// 值
					var rackId = $rackId.val();
					var projectName = $project.val();
					var projectId = $project.attr("data-value");
					var rackName = $rackName.val();
					var rackIndex = $rackIndex.val();
					var beforeRackName = $beforeRackName.val();
					var beforeRackIndex = $beforeRackIndex.val();
					var splitVideoX = $splitVideoX.val();
					var splitVideoY = $splitVideoY.val();
					var splitVideoWidth = $splitVideoWidth.val();
					var splitVideoHeight = $splitVideoHeight.val();
					var rackLongitude = $rackLongitude.val();
					var rackLatitude = $rackLatitude.val();
					
					
					if (rackName == "" || rackName == null
							|| rackName == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackName.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						if(beforeRackName!=rackName){
							//判断是否存在
							if(!rackNameIsExits(projectId,rackName))
								return;
						}
					}
					
					if (rackIndex == "" || rackIndex == null
							|| rackIndex == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackIndex.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(rackIndex>=0){
							var uu = Math.floor(rackIndex);
							if(uu==rackIndex){
								bool=true;
								
								if(beforeRackIndex!=rackIndex){
									//判断是否存在
									if(!rackIndexIsExits(projectId,rackName,rackIndex))
										return;
								}
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$rackIndex.val("");
							$rackIndex.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoX == "" || splitVideoX == null
							|| splitVideoX == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoX.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoX>=0){
							var uu = Math.floor(splitVideoX);
							if(uu==splitVideoX){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoX.val("");
							$splitVideoX.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoY == "" || splitVideoY == null
							|| splitVideoY == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoY.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoY>=0){
							var uu = Math.floor(splitVideoY);
							if(uu==splitVideoY){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoY.val("");
							$splitVideoY.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoWidth == "" || splitVideoWidth == null
							|| splitVideoWidth == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoWidth.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoWidth>=0){
							var uu = Math.floor(splitVideoWidth);
							if(uu==splitVideoWidth){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoWidth.val("");
							$splitVideoWidth.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (splitVideoHeight == "" || splitVideoHeight == null
							|| splitVideoHeight == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$splitVideoHeight.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else{
						//判断是否为整数
						var bool=false;
						if(splitVideoHeight>=0){
							var uu = Math.floor(splitVideoHeight);
							if(uu==splitVideoHeight){
								bool=true;
							}
						}
						if(!bool){
							layer.msg('格式错误,请输入整数', {
								icon : 5
							});
							
							$splitVideoHeight.val("");
							$splitVideoHeight.addClass("lovdmx-form-danger")
									.focus();
							return;
						}
					}
					
					if (rackLongitude == "" || rackLongitude == null
							|| rackLongitude == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackLongitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if(!(rackLongitude>=0)){
						layer.msg('格式错误,请输入小数', {
							icon : 5
						});
						
						$rackLongitude.val("");
						$rackLongitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					if (rackLatitude == "" || rackLatitude == null
							|| rackLatitude == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rackLatitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if(!(rackLatitude>=0)){
						layer.msg('格式错误,请输入小数', {
							icon : 5
						});
						
						$rackLatitude.val("");
						$rackLatitude.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/rackDevice/update.do",
								data : {
									"rackId":rackId,
									"projectId" : projectId,
									"rackName" : rackName,
									"rackIndex" : rackIndex,
									"splitVideoX" : splitVideoX,
									"splitVideoY" : splitVideoY,
									"splitVideoWidth" : splitVideoWidth,
									"splitVideoHeight" : splitVideoHeight,
									"rackLongitude" : rackLongitude,
									"rackLatitude" : rackLatitude,
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										
										//更新
										$(".rackDeviceId"+rackId).find(".rackName").html(rackName);
										$(".rackDeviceId"+rackId).find(".projectName").html(projectName);
										$(".rackDeviceId"+rackId).find(".rackIndex").html(rackIndex);
										$(".rackDeviceId"+rackId).find(".splitVideoX").html(splitVideoX);
										$(".rackDeviceId"+rackId).find(".splitVideoY").html(splitVideoY);
										$(".rackDeviceId"+rackId).find(".splitVideoWidth").html(splitVideoWidth);
										$(".rackDeviceId"+rackId).find(".splitVideoHeight").html(splitVideoHeight);
										$(".rackDeviceId"+rackId).find(".rackLongitude").html(rackLongitude);
										$(".rackDeviceId"+rackId).find(".rackLatitude").html(rackLatitude);
											
										layer.alert(json.result, {
											icon : 6
										});
									}
								close();
						}
			});
						
});



//提交rtr设备
$("body")
		.on(
				"click",
				"#submit-rtrDevice",
				function() {
					// jquery对象
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $rack = $("#lovdmx-popup-window input[name='rack']");
					var $mac = $("#lovdmx-popup-window input[name='rtrMac']");
					var $curMode = $("#lovdmx-popup-window input[name='curMode']");
					var $ip = $("#lovdmx-popup-window input[name='ip']");
					var $modelSize = $("#lovdmx-popup-window input[name='modelSize']");
					
					// 值
					var projectId = $project.attr("data-value");
					var rackId = $rack.attr("data-value");
					var mac =$mac.val();
					var curMode=$curMode.val();
					var ip =$ip.val();
					var modelSize=$modelSize.val();
					
					if (projectId == -1) {
						layer.msg('请选择项目', {
							icon : 5
						});
						$project.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if (rackId == -1) {
						layer.msg('请选择节点', {
							icon : 5
						});
						$rack.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if (mac == "" || mac == null
							|| mac == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$mac.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if (ip == "" || ip == null
							|| ip == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$ip.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if (modelSize == "" || modelSize == null
							|| modelSize == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$modelSize.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/rtrDevice/save.do",
								data : {
									"rackId" : rackId,
									"mac" : mac,
									"curMode" : curMode,
									"ip" : ip,
									"modelSize" : modelSize
								},
								success : function(json) {
									//返回状态
									if (json.result == "保存失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										var obj = json.rtrDevice;
										var addRTRDevice = "";
										
										// 获取时间
										var time = formatDateTime(obj.createTime);
										
										//项目路径
										var path=$("#pageContext").val();
										
										// 组装
										addRTRDevice +="<tr class='rtrId"+obj.rtrId+"'>";
											addRTRDevice +="<td class='table-checked lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
													addRTRDevice +=" data-index='"+obj.rtrId+"'>";
													addRTRDevice +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='rtrId'>"+obj.rtrId+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='rackIndex'>"+obj.rackIndex+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='rackName'>"+obj.rackName+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect table-content'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='projectName'>"+obj.projectName+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect table-content'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='mac'>"+obj.mac+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect table-content'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='curMode'>"+obj.curMode+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='ip'>"+obj.ip+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='modelSize'>"+obj.modelSize+"</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<span class='isOnline'>";
														addRTRDevice +="<button class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未在线</button>";
													addRTRDevice +="</span>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
											addRTRDevice +="<td class='table-last lovdmx-unselect'>";
												addRTRDevice +="<div class='lovdmx-table-cell'>";
													addRTRDevice +="<a";
														addRTRDevice +=" data-link='"+path+"/lovdmx/ht/rtrDevice/edit.do?rtrId="+obj.rtrId+"'";
														addRTRDevice +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs rtrDevice-edit'>";
														addRTRDevice +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑";
													addRTRDevice +="</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'>";
														addRTRDevice +="<i class='icon-shanchu lovdmx-icon iconfont lovdmx-rtrDevice-del'></i>";
														addRTRDevice +=" 删除";
													addRTRDevice +="</a>";
												addRTRDevice +="</div>";
											addRTRDevice +="</td>";
										addRTRDevice +="</tr>";

										$("tbody").prepend(addRTRDevice);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});

//修改rtr设备
$("body")
		.on(
				"click",
				"#update-rtrDevice",
				function() {
					// jquery对象
					var $project = $("#lovdmx-popup-window input[name='project']");
					var $rack = $("#lovdmx-popup-window input[name='rack']");
					var $rtrId = $("#lovdmx-popup-window input[name='rtrId']");
					var $curMode = $("#lovdmx-popup-window input[name='curMode']");
					var $modelSize = $("#lovdmx-popup-window input[name='modelSize']");
					
					// 值
					var projectId =$project.attr("data-value");
					var rackId = $rack.attr("data-value");
					var rtrId = $rtrId.val();
					var curMode=$curMode.val();
					var modelSize=$modelSize.val();
					
					if(projectId == -1){
						layer.msg('请选择项目', {
							icon : 5
						});
						$project.addClass("lovdmx-form-danger")
						.focus();
						return;
					}else if (rackId == -1) {
						layer.msg('请选择节点', {
							icon : 5
						});
						$rackId.addClass("lovdmx-form-danger")
						.focus();
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/rtrDevice/update.do",
								data : {
									"rtrId" : rtrId,
									"rackId" : rackId,
									"curMode" : curMode,
									"modelSize" : modelSize
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										var rtrDevice = json.rtrDevice;
										
										$(".rtrId"+rtrId).find(".rackName").html(rtrDevice.rackName);
										$(".rtrId"+rtrId).find(".rackIndex").html(rtrDevice.rackIndex);
										$(".rtrId"+rtrId).find(".projectName").html(rtrDevice.projectName);
										$(".rtrId"+rtrId).find(".curMode").html(rtrDevice.curMode);
										$(".rtrId"+rtrId).find(".modelSize").html(rtrDevice.modelSize);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});


$("body").on("click","#submit-spriteDevice",function() {
		// jquery对象
		var $rack = $("#lovdmx-popup-window input[name='rack']");
		var $mac = $("#lovdmx-popup-window input[name='spriteMac']");
		var $ip = $("#lovdmx-popup-window input[name='ip']");
		
		// 值
		var rackId = $rack.attr("data-value");
		var mac =$mac.val();
		var ip =$ip.val();
		
		if (rackId == -1) {
			layer.msg('请选择机柜', {
				icon : 5
			});
			return;
		}
		if (mac == "" || mac == null
				|| mac == undefined) {
			layer.msg('必填项不能为空', {
				icon : 5
			});
			$mac.addClass("lovdmx-form-danger")
					.focus();
			return;
		}
		
		if (ip == "" || ip == null
				|| ip == undefined) {
			layer.msg('必填项不能为空', {
				icon : 5
			});
			$ip.addClass("lovdmx-form-danger")
					.focus();
			return;
		}
		
		$
				.ajax({
					type : "POST",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/spriteDevice/save.do",
					data : {
						"rackId" : rackId,
						"mac" : mac,
						"ip" : ip,
					},
					success : function(json) {
						//返回状态
						if (json.result == "保存失败") {
							layer.alert(json.result, {
								icon : 5
							});
						} else {
							var obj = json.spriteDevice;
							var addSpriteDevice = "";
							
							// 获取时间
							var time = formatDateTime(obj.createTime);
							
							//项目路径
							var path=$("#pageContext").val();
							
							// 组装
							addSpriteDevice +="<tr class='spriteId"+obj.spriteId+"'>";
								addSpriteDevice +="<td class='table-checked lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
										addSpriteDevice +=" data-index='"+obj.spriteId+"'>";
										addSpriteDevice +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='rtrId'>"+obj.spriteId+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='rackIndex'>"+obj.rackIndex+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='rackName'>"+obj.rackName+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect table-content'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='projectName'>"+obj.projectName+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect table-content'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='mac'>"+obj.mac+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='ip'>"+obj.ip+"</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<span class='isOnline'>";
											addSpriteDevice +="<button class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未在线</button>";
										addSpriteDevice +="</span>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
								addSpriteDevice +="<td class='table-last lovdmx-unselect'>";
									addSpriteDevice +="<div class='lovdmx-table-cell'>";
										addSpriteDevice +="<a";
											addSpriteDevice +=" data-link='"+path+"/lovdmx/ht/spriteDevice/edit.do?spriteId="+obj.spriteId+"'";
											addSpriteDevice +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs spriteDevice-edit'>";
											addSpriteDevice +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑";
										addSpriteDevice +="</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'>";
											addSpriteDevice +="<i class='icon-shanchu lovdmx-icon iconfont'></i>";
											addSpriteDevice +=" 删除";
										addSpriteDevice +="</a>";
									addSpriteDevice +="</div>";
								addSpriteDevice +="</td>";
							addSpriteDevice +="</tr>";

							$("tbody").prepend(addSpriteDevice);
							layer.alert(json.result, {
								icon : 6
							});
						}
						//关闭
						close();
					}
				});
	});

//修改rtr设备
$("body")
		.on(
				"click",
				"#update-spriteDevice",
				function() {
					// jquery对象
					var $rack = $("#lovdmx-popup-window input[name='rack']");
					var $spriteId = $("#lovdmx-popup-window input[name='spriteId']");
					
					// 值
					var rackId = $rack.attr("data-value");
					var spriteId = $spriteId.val();
					
					if (rackId == -1) {
						layer.msg('请选择机柜', {
							icon : 5
						});
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/spriteDevice/update.do",
								data : {
									"spriteId" : spriteId,
									"rackId" : rackId
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										var spriteDevice = json.spriteDevice;
										
										$(".spriteId"+spriteId).find(".rackName").html(spriteDevice.rackName);
										$(".spriteId"+spriteId).find(".rackIndex").html(spriteDevice.rackIndex);
										$(".spriteId"+spriteId).find(".projectName").html(spriteDevice.projectName);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});


//修改rtr设备
$("body")
		.on(
				"click",
				"#update-dmx512Device",
				function() {
					// jquery对象
					var $dmxId = $("#lovdmx-popup-window input[name='dmxId']");
					var $universe = $("#lovdmx-popup-window input[name='universe']");
					var $rdmNums = $("#lovdmx-popup-window input[name='rdmNums']");
					var $rdmts = $("#lovdmx-popup-window input[name='rdmts']");
					
					// 值
					var dmxId = $dmxId.val();
					var universe =$universe.val();
					var rdmNums =$rdmNums.val();
					var rdmts =$rdmts.val();
					
					if (universe == "" || universe == null
							|| universe == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$universe.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					if (rdmNums == "" || rdmNums == null
							|| rdmNums == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rdmNums.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					if (rdmts == "" || rdmts == null
							|| rdmts == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$rdmts.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/dmx512Device/update.do",
								data : {
									"dmxId" : dmxId,
									"universe" : universe,
									"rdmNums" : rdmNums,
									"rdmts" : rdmts
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										var dmx512Device = json.dmx512Device;
										
										$(".dmxId"+dmxId).find(".universe").html(dmx512Device.universe);
										$(".dmxId"+dmxId).find(".rdmNums").html(dmx512Device.rdmNums);
										$(".dmxId"+dmxId).find(".rdmts").html(dmx512Device.rdmts);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});



//修改智能网关
$("body")
		.on(
				"click",
				"#update-intelligentGateway",
				function() {
					// jquery对象
					var $intelligentGatewayId = $("#lovdmx-popup-window input[name='intelligentGatewayId']");
					var $rack = $("#lovdmx-popup-window input[name='rack']");
					
					// 值
					var intelligentGatewayId = $intelligentGatewayId.val();
					var rackName =$rack.val();
					var rackId =$rack.attr("data-value");
					if (rackId == -1) {
						layer.msg('请选择机柜', {
							icon : 5
						});
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/intelligentGateway/update.do",
								data : {
									"id" : intelligentGatewayId,
									"rackId" : rackId
								},
								success : function(json) {
									//返回状态
									if (json.result == "修改失败") {
										layer.alert(json.result, {
											icon : 5
										});
									} else {
										
										$(".intelligentgatewayId"+intelligentGatewayId).find(".rackName").html(rackName);
										layer.alert(json.result, {
											icon : 6
										});
									}
									//关闭
									close();
								}
							});
				});

//远程添加继电器
$("body").on("click","#remote-submit-relay",function(){
	var $gatewayMac = $("#lovdmx-popup-window input[name='gatewayMac']");
	var $rs485 = $("#lovdmx-popup-window input[name='rs485toNetIp']");
	var $newRs485toNetIp = $("#lovdmx-popup-window input[name='newRs485toNetIp']");
	
	var gatewayMac =$gatewayMac.val();
	var rs485toNetIp=$rs485.val();
	var newRs485toNetIp=$newRs485toNetIp.val();
	//获取值
	var rs485_val =$rs485.attr("data-value");
	if (rs485_val == -1) {
		layer.msg('请选择485Ip', {
			icon : 5
		});
		return;
	}else if(newRs485toNetIp == "" || newRs485toNetIp == null
			|| newRs485toNetIp == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$newRs485toNetIp.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	$
	.ajax({
		type : "POST",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/relay/sendRemoteConfigurationAddRelay.do",
		data : {
			"gatewayMac" : gatewayMac,
			"rs485toNetIp" : rs485toNetIp,
			"newRs485toNetIp" : newRs485toNetIp
		},
		success : function(json) {
			//返回状态
			if (json.result == "control not online") {
				layer.alert("中控不在线", {
					icon : 5
				});
			}else if(json.result == "send successful"){
				layer.alert("发送成功", {
					icon : 6
				});
			}else{
				layer.alert("异常错误", {
					icon : 5
				});
			}
			//关闭
			close();
		}
	});
	
});

//远程修改继电器
$("body")
		.on(
				"click",
				"#remote-update-relay",
				function() {
					// jquery对象
					var $relayId = $("#lovdmx-popup-window input[name='relayId']");
					var $rs485toNetIp = $("#lovdmx-popup-window input[name='rs485toNetIp']");
					var $gatewayMac = $("#lovdmx-popup-window input[name='gatewayMac']");
					var $slaveAddr = $("#lovdmx-popup-window input[name='slaveAddr']");
					var $number = $("#lovdmx-popup-window input[name='number']");
					
					// 值
					var relayId = $relayId.val();
					var gatewayMac =$gatewayMac.val();
					var rs485toNetIp=$rs485toNetIp.val();
					var slaveAddr =$slaveAddr.val();
					var number =$number.val();
					if (slaveAddr == "" || slaveAddr == null
							|| slaveAddr == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$slaveAddr.addClass("lovdmx-form-danger")
								.focus();
						return;
					}else if(number == "" || number == null
							|| number == undefined) {
						layer.msg('必填项不能为空', {
							icon : 5
						});
						$number.addClass("lovdmx-form-danger")
								.focus();
						return;
					}
					
					$
							.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/relay/sendRemoteConfigurationUpdateRelay.do",
								data : {
									"id" : relayId,
									"gatewayMac" : gatewayMac,
									"rs485toNetIp" : rs485toNetIp,
									"slaveAddr" : slaveAddr,
									"number" : number  
								},
								success : function(json) {
									//返回状态
									if (json.result == "control not online") {
										layer.alert("中控不在线", {
											icon : 5
										});
									}else if(json.result == "relay not online"){
										layer.alert("继电器不在线", {
											icon : 5
										});
									}else if(json.result == "send successful"){
										layer.alert("发送成功", {
											icon : 6
										});
									}else{
										layer.alert("异常错误", {
											icon : 5
										});
									}
									//关闭
									close();
								}
							});
				});



$("body").on("click","#submit-monitoringDevice",function() {
	// jquery对象
	var $project = $("#lovdmx-popup-window input[name='project']");
	var $rack = $("#lovdmx-popup-window input[name='rack']");
	var $deviceName = $("#lovdmx-popup-window input[name='deviceName']");
	var $deviceSerial = $("#lovdmx-popup-window input[name='deviceSerial']");
	var $authCode = $("#lovdmx-popup-window input[name='authCode']");
	var $appKey = $("#lovdmx-popup-window input[name='appKey']");
	var $appSecret = $("#lovdmx-popup-window input[name='appSecret']");
	
	// 值
	var projectId = $project.attr("data-value");
	var rackId = $rack.attr("data-value");
	var rackName = $rack.val();
	var deviceName =$deviceName.val();
	var deviceSerial =$deviceSerial.val();
	var authCode =$authCode.val();
	var appKey =$appKey.val();
	var appSecret =$appSecret.val();
	
	
	if (projectId == -1) {
		layer.msg('请选择项目', {
			icon : 5
		});
		$project.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (rackId == -1) {
		layer.msg('请选择节点', {
			icon : 5
		});
		$rack.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (deviceName == "" || deviceName == null
			|| deviceName == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$deviceName.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (deviceSerial == "" || deviceSerial == null
			|| deviceSerial == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$deviceSerial.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (authCode == "" || authCode == null
			|| authCode == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$authCode.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (appKey == "" || appKey == null
			|| appKey == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$appKey.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (appSecret == "" || appSecret == null
			|| appSecret == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$appSecret.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/monitoringDevice/save.do",
				data : {
					"rackId" : rackId,
					"deviceName" : deviceName,
					"deviceSerial" : deviceSerial,
					"authCode" : authCode,
					"appKey" : appKey,
					"appSecret" : appSecret
				},
				success : function(json) {
					//返回状态
					if (json.result == "添加成功") {
						//监控信息
						var monitoringDevice = json.monitoringDevice;
						var addMonitoringDevice = "";
						
						//项目路径
						var path=$("#pageContext").val();

						
						// 获取时间
						var createTime = formatDateTime(monitoringDevice.createTime);
						var expirationTime = formatDateTime(monitoringDevice.expirationTime);
						
						addMonitoringDevice +="<tr class='deviceId"+monitoringDevice.deviceId +"'>";
							addMonitoringDevice +="<td class='table-checked lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
									addMonitoringDevice +=" data-index='"+monitoringDevice.deviceId +"'>";
									addMonitoringDevice +=" <i ";
										addMonitoringDevice +=" class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='deviceId'>"+monitoringDevice.deviceId+"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='rackName'>"+rackName +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect table-content'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='deviceName'>"+monitoringDevice.deviceName +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='deviceSerial'>"+monitoringDevice.deviceSerial +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='authCode'>"+monitoringDevice.authCode +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='appKey'>"+monitoringDevice.appKey +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='appSecret'>"+monitoringDevice.appSecret +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='accessToken'>"+monitoringDevice.accessToken +"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='expirationTime'>"+expirationTime+"</span> ";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td> ";
							addMonitoringDevice +="<td class='lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +="<span class='createTime'>"+createTime+"</span>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
							addMonitoringDevice +="<td class='table-last lovdmx-unselect'>";
								addMonitoringDevice +="<div class='lovdmx-table-cell'>";
									addMonitoringDevice +=" <a ";
										addMonitoringDevice +=" data-link='"+path+"/lovdmx/ht/monitoringDevice/edit.do?deviceId="+monitoringDevice.deviceId+"' ";
										addMonitoringDevice +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs monitoringDevice-edit'>";
										addMonitoringDevice +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑";
									addMonitoringDevice +="</a> <a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs' ";
										addMonitoringDevice +=" data-link='"+path+"/lovdmx/ht/monitoringDevice/delete.do?deviceId="+monitoringDevice.deviceId+"' ";
										addMonitoringDevice +=" onclick='mydel(this)'> <i ";
										addMonitoringDevice +=" class='icon-shanchu lovdmx-icon iconfont'></i> ";
										addMonitoringDevice +="删除";
									addMonitoringDevice +="</a>";
								addMonitoringDevice +="</div>";
							addMonitoringDevice +="</td>";
						addMonitoringDevice +="</tr>";
						
						$("tbody").prepend(addMonitoringDevice);
						layer.alert(json.result, {
							icon : 6
						});
						
					} else {
						layer.alert(json.result, {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});

$("body").on("click","#update-monitoringDevice",function() {
	// jquery对象
	var $rack = $("#lovdmx-popup-window input[name='rack']");
	var $deviceId = $("#lovdmx-popup-window input[name='deviceId']");
	var $deviceName = $("#lovdmx-popup-window input[name='deviceName']");
	var $authCode = $("#lovdmx-popup-window input[name='authCode']");
	var $appKey = $("#lovdmx-popup-window input[name='appKey']");
	var $appSecret = $("#lovdmx-popup-window input[name='appSecret']");
	
	// 值
	var rackId = $rack.attr("data-value");
	var rackName = $rack.val();
	var deviceId =$deviceId.val();
	var deviceName =$deviceName.val();
	var authCode =$authCode.val();
	var appKey =$appKey.val();
	var appSecret =$appSecret.val();
	
	
	if (rackId == -1) {
		layer.msg('请选择节点', {
			icon : 5
		});
		$rack.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (deviceName == "" || deviceName == null
			|| deviceName == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$deviceName.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (authCode == "" || authCode == null
			|| authCode == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$authCode.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (appKey == "" || appKey == null
			|| appKey == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$appKey.addClass("lovdmx-form-danger")
				.focus();
		return;
	}else if (appSecret == "" || appSecret == null
			|| appSecret == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$appSecret.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/monitoringDevice/update.do",
				data : {
					"rackId" : rackId,
					"deviceId" : deviceId,
					"deviceName" : deviceName,
					"authCode" : authCode,
					"appKey" : appKey,
					"appSecret" : appSecret
				},
				success : function(json) {
					//返回状态
					if (json.result == "修改成功") {
						
						var monitoringDevice = json.monitoringDevice;
						
						// 获取时间
						var expirationTime = formatDateTime(monitoringDevice.expirationTime);
						
						//更新
						$(".deviceId"+deviceId).find(".rackName").html(rackName);
						$(".deviceId"+deviceId).find(".deviceName").html(monitoringDevice.deviceName);
						$(".deviceId"+deviceId).find(".authCode").html(monitoringDevice.authCode);
						$(".deviceId"+deviceId).find(".appKey").html(monitoringDevice.appKey);
						$(".deviceId"+deviceId).find(".appSecret").html(monitoringDevice.appSecret);
						$(".deviceId"+deviceId).find(".accessToken").html(monitoringDevice.accessToken);
						$(".deviceId"+deviceId).find(".expirationTime").html(expirationTime);
						
						layer.alert(json.result, {
							icon : 6
						});
						
					} else {
						layer.alert(json.result, {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});
//添加权限功能
$("body").on("click","#submit-power",function() {
	// jquery对象
	var $id = $("#lovdmx-popup-window input[name='id']");
	var $name = $("#lovdmx-popup-window input[name='name']");
	
	// 值
	var id = $id.val();
	var name =$name.val();
	
	if (id == "" || id == null
			|| id == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$id.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	if (name == "" || name == null
			|| name == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$name.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/limit/power/save.do",
				data : {
					"id":id,
					"name" : name
				},
				success : function(json) {
					//返回状态
					if (json.result) {
						var addPower = "";
						//权限功能
						var power= json.power;
						//项目路径
						var path=$("#pageContext").val();

						addPower +=" <tr class='powerId"+power.id+"'>";
							addPower +=" <td class='table-checked lovdmx-unselect'>";
								addPower +="  <div class='lovdmx-table-cell lovdmxtable-cell-checkbox'";
									addPower +=" data-index='"+power.id+"'> ";
									addPower +="  <i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i> ";
								addPower +="  </div> ";
							addPower +=" </td> ";
							addPower +=" <td class='lovdmx-unselect'> ";
								addPower +="  <div class='lovdmx-table-cell'> ";
									addPower +="  <span class='powerId'>"+power.id+"</span> ";
								addPower +="  </div> ";
							addPower +=" </td> ";
							addPower +=" <td class='lovdmx-unselect'> ";
								addPower +=" <div class='lovdmx-table-cell'> ";
									addPower +=" <span class='name'>"+power.name+"</span> ";
								addPower +=" </div> ";
							addPower +=" </td> ";
							addPower +=" <td class='table-last lovdmx-unselect'> ";
								addPower +=" <div class='lovdmx-table-cell'> ";
									addPower +="  <a ";
										addPower +=" data-link='"+path+"/lovdmx/ht/limit/power/edit.do?id="+power.id+"' ";
										addPower +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs power-edit'> ";
										addPower +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
									addPower +="  </a><a ";
										addPower +=" class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'> ";
										addPower +=" <i class='icon-shanchu lovdmx-icon iconfont'></i> 删除 ";
									addPower +=" </a> ";
								addPower +=" </div> ";
							addPower +=" </td> ";
						addPower +=" </tr> ";
						
						$("tbody").prepend(addPower);
						layer.alert("保存成功", {
							icon : 6
						});
						
					} else {
						layer.alert("保存失败", {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});

//修改权限功能
$("body").on("click","#update-power",function() {
	// jquery对象
	var $id = $("#lovdmx-popup-window input[name='id']");
	var $name = $("#lovdmx-popup-window input[name='name']");
	
	// 值
	var id =$id.val();
	var name =$name.val();
	
	
	if (name == "" || name == null
			|| name == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$name.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/limit/power/update.do",
				data : {
					"id" : id,
					"name" : name
				},
				success : function(json) {
					//返回状态
					if (json.result) {

						//更新
						$(".powerId"+id).find(".name").html(name);
						layer.alert("修改成功", {
							icon : 6
						});
						
					} else {
						layer.alert("保存失败", {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});

//添加权限功能
$("body").on("click","#submit-role",function() {
	// jquery对象
	var $roleName = $("#lovdmx-popup-window input[name='roleName']");
	
	// 值
	var roleName =$roleName.val();
	
	
	if (roleName == "" || roleName == null
			|| roleName == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$roleName.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	//选中角色权限
	var role_limit = "";
	$(".lovdmx-input-block input[type=checkbox]:checked").each(
			function() {
				role_limit += $(this).val() + ",";
			});
	//去掉最后逗号
	if (role_limit.length > 0) {
		role_limit = role_limit.substring(0, role_limit.length - 1);
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/limit/role/save.do",
				data : {
					"roleName" : roleName,
					"roleLimit" : role_limit
				},
				success : function(json) {
					//返回状态
					if (json.result) {
						var addRole = "";
						//权限功能
						var role= json.role;
						//项目路径
						var path=$("#pageContext").val();

						addRole +=" <tr class='roleId"+role.roleId+"'> ";
							addRole +=" <td class='table-checked lovdmx-unselect'> ";
								addRole +=" <div class='lovdmx-table-cell lovdmxtable-cell-checkbox' ";
									addRole +=" data-index='"+role.roleId+"'> ";
									addRole +=" <i ";
										addRole +=" class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i> ";
								addRole +=" </div> ";
							addRole +=" </td> ";
							addRole +=" <td class='lovdmx-unselect'> ";
								addRole +=" <div class='lovdmx-table-cell'> ";
									addRole +=" <span class='roleId'>"+role.roleId+"</span> ";
								addRole +=" </div> ";
							addRole +=" </td> ";
							addRole +=" <td class='lovdmx-unselect'> ";
								addRole +=" <div class='lovdmx-table-cell'> ";
									addRole +=" <span class='roleName'>"+role.roleName+"</span> ";
								addRole +=" </div> ";
							addRole +=" </td> ";
							addRole +=" <td class='lovdmx-unselect'> ";
								addRole +=" <div class='lovdmx-table-cell'> ";
									addRole +=" <span class='limitNames'>"+role.limitNames+"</span> ";
								addRole +=" </div> ";
							addRole +=" </td> ";
							addRole +=" <td class='table-last lovdmx-unselect'> ";
								addRole +=" <div class='lovdmx-table-cell'> ";
									addRole +=" <a ";
										addRole +=" data-link='"+path+"/lovdmx/ht/limit/role/edit.do?roleId="+role.roleId+"' ";
										addRole +=" class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs role-edit'> ";
										addRole +=" <i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
									addRole +=" </a><a ";
										addRole +=" class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'> ";
										addRole +=" <i class='icon-shanchu lovdmx-icon iconfont'></i> 删除 ";
									addRole +=" </a> ";
								addRole +=" </div> ";
							addRole +=" </td> ";
						addRole +=" </tr> ";
						
						$("tbody").prepend(addRole);
						layer.alert("保存成功", {
							icon : 6
						});
						
					} else {
						layer.alert("保存失败", {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});


//添加权限功能
$("body").on("click","#update-role",function() {
	// jquery对象
	var $roleId = $("#lovdmx-popup-window input[name='roleId']");
	var $roleName = $("#lovdmx-popup-window input[name='roleName']");
	
	// 值
	var roleId =$roleId.val();
	var roleName =$roleName.val();
	
	
	if (roleName == "" || roleName == null
			|| roleName == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$roleName.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	
	//选中角色权限
	var role_limit = "";
	$(".lovdmx-input-block input[type=checkbox]:checked").each(
			function() {
				role_limit += $(this).val() + ",";
			});
	//去掉最后逗号
	if (role_limit.length > 0) {
		role_limit = role_limit.substring(0, role_limit.length - 1);
	}
	
	$
			.ajax({
				type : "POST",
				url : $("#pageContext").val()
						+ "/lovdmx/ht/limit/role/update.do",
				data : {
					"roleId" : roleId,
					"roleName" : roleName,
					"roleLimit" : role_limit
				},
				success : function(json) {
					//返回状态
					if (json.result) {
						//权限功能
						var role= json.role;
						
						//更新
						$(".roleId"+roleId).find(".roleName").html(roleName);
						$(".roleId"+roleId).find(".limitNames").html(role.limitNames);
						layer.alert("修改成功", {
							icon : 6
						});
						
					} else {
						layer.alert("修改失败", {
							icon : 5
						});
						
					}
					//关闭
					close();
				}
			});
});

//添加继电器程序
$("body").on("click","#submit-relay-application",function() {
	
	var loadT = layer.msg("上传中...", {
		icon : 16,
		time : 0,
		shade : [ 0.3, '#000' ]
	 });
	var formData = new FormData();//先实例化formdata
	// jquery对象
	var $applicationVersion = $("#lovdmx-popup-window input[name='applicationVersion']");
	var $file =$("#lovdmx-popup-window  input[name='file']");
	// 值
	var file =$file.prop('files');
	var applicationVersion =$applicationVersion.val();
	
	if (applicationVersion == "" || applicationVersion == null
			|| applicationVersion == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$applicationVersion.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	formData.append("applicationVersion",applicationVersion);
	if(file.length >0){
		formData.append("file",file[0]);
	}else{
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$("#lovdmx-popup-window  input[name='text']").addClass("lovdmx-form-danger")
				.focus();
		return;
	 }
	
	 $.ajax({
	        type: "POST",
	        url: $("#pageContext").val() + "/lovdmx/ht/relay/saveApplication.do",
	        data: formData,//传过来
	        cache: false,
	        processData: false,
	        contentType: false,
	        success: function (msg) {
	        	if(msg){
	        		var addRelayApplication = "";
					//项目路径
					var path=$("#pageContext").val();
		        	addRelayApplication +="<tr> ";
		        		addRelayApplication +="<td class='table-checked lovdmx-unselect'> ";
		        			addRelayApplication +="<div class='lovdmx-table-cell lovdmxtable-cell-checkbox'> ";
		        				addRelayApplication +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i> ";
		        			addRelayApplication +="</div> ";
		        		addRelayApplication +="</td> ";
		        		addRelayApplication +="<td class='lovdmx-unselect'> ";
		        			addRelayApplication +="<div class='lovdmx-table-cell'> ";
		        				addRelayApplication +="<span class='applicationName'>"+file[0].name+"</span> ";
		        			addRelayApplication +="</div> ";
		        		addRelayApplication +="</td> ";
		        		addRelayApplication +="<td class='lovdmx-unselect'> ";
		        			addRelayApplication +="<div class='lovdmx-table-cell'> ";
		        				addRelayApplication +="<span class='applicationVersion'>"+applicationVersion+"</span> ";
		        			addRelayApplication +="</div> ";
		        		addRelayApplication +="</td> ";
		        		addRelayApplication +="<td class='table-last lovdmx-unselect'> ";
		        			addRelayApplication +="<div class='lovdmx-table-cell'> ";
		        				addRelayApplication +="<a data-link='"+path+"/lovdmx/ht/relay/editApplication.do' ";
		        					addRelayApplication +="class='lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs relay-application-edit'> ";
		        					addRelayApplication +="<i class='icon-bianji lovdmx-icon iconfont'></i> 编辑 ";
		        				addRelayApplication +="</a> <a ";
		        					addRelayApplication +="class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled'> ";
		        					addRelayApplication +="<i class='icon-shanchu lovdmx-icon iconfont'></i> 删除 ";
		        				addRelayApplication +="</a> ";
		        			addRelayApplication +="</div> ";
		        		addRelayApplication +="</td> ";
		        	addRelayApplication +="</tr> ";
		        	
		        	$("#relay-application-add").addClass("none");
		        	
		        	$("tbody").prepend(addRelayApplication);
	        		layer.alert("添加程序成功", {
						icon : 6
					});
	        	}else{
	        		layer.alert("修改程序失败", {
						icon : 5
					});
	        	}
				//关闭
				close();
			}
	});
});

//添加继电器程序
$("body").on("click","#update-relay-application",function() {
	
	var loadT = layer.msg("上传中...", {
		icon : 16,
		time : 0,
		shade : [ 0.3, '#000' ]
	 });
	var formData = new FormData();//先实例化formdata
	// jquery对象
	var $applicationVersion = $("#lovdmx-popup-window input[name='applicationVersion']");
	var $file =$("#lovdmx-popup-window  input[name='file']");
	
	// 值
	var applicationVersion =$applicationVersion.val();
	var file =$file.prop('files');
	
	
	if (applicationVersion == "" || applicationVersion == null
			|| applicationVersion == undefined) {
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$applicationVersion.addClass("lovdmx-form-danger")
				.focus();
		return;
	}
	formData.append("applicationVersion",applicationVersion);
	 if(file.length >0){
		formData.append("file",file[0]);
	 }else{
		layer.msg('必填项不能为空', {
			icon : 5
		});
		$("#lovdmx-popup-window  input[name='text']").addClass("lovdmx-form-danger")
				.focus();
		return;
	 }
	
	 $.ajax({
	        type: "POST",
	        url: $("#pageContext").val() + "/lovdmx/ht/relay/updateApplication.do",
	        data: formData,//传过来
	        cache: false,
	        processData: false,
	        contentType: false,
	        success: function (msg) {
	        	if(msg){
	        		
	        		$(".applicationName").html(file[0].name);
	        		$(".applicationVersion").html(applicationVersion);
	        		layer.alert("更新程序成功", {
						icon : 6
					});
	        	}else{
	        		layer.alert("更新程序失败", {
						icon : 5
					});
	        	}
				//关闭
				close();
			}
	});
});

//判断机架名是否存在
function rackNameIsExits(projectId,rackName){
	var flag=false;
	//判断是否存在
	$.ajax({
		type : "GET",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/rackDevice/findExistsByRackName.do",
		data : "projectId=" + projectId+"&rackName="+rackName,
		async:false, //使用同步
		success : function(msg) {
			if (msg == "false") {
				$("#lovdmx-popup-window input[name='rackName']").addClass(
						"lovdmx-form-danger").val("")
						.focus();
				layer.msg(rackName + "机柜名已存在,请重新输入!!!", {
					icon : 5
				});
				flag=false;
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

//判断机架名是否存在
function rackIndexIsExits(projectId,rackName,rackIndex){
	var flag=false;
	//判断是否存在
	$.ajax({
		type : "GET",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/rackDevice/findExistsByRackIndex.do",
		data : "projectId=" + projectId+"&rackIndex="+rackIndex,
		async:false, //使用同步
		success : function(msg) {
			if (msg == "false") {
				$("#lovdmx-popup-window input[name='rackIndex']").addClass(
						"lovdmx-form-danger").val("")
						.focus();
				layer.msg(rackName + "机柜下标"+rackIndex+"已存在,请重新输入!!!", {
					icon : 5
				});
				flag=false;
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

//时间戳转yyyy-MM-dd HH-mm-ss
function formatDateTime(inputTime) {
	var date = new Date(inputTime);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	var second = date.getSeconds();
	minute = minute < 10 ? ('0' + minute) : minute;
	second = second < 10 ? ('0' + second) : second;
	return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
};


