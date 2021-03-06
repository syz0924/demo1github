$(function() {

	//登录名
	$("body").on("blur","#lovdmx-popup-window input[name='loginName']",function() {
		var loginName = $(this).val();
		if (loginName == "" || loginName == null
				|| loginName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			var lgnName = $("#lovdmx-popup-window input[name='lgnName']").val();
			if (lgnName != loginName) {
				$.ajax({
					type : "GET",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/manage/findExistsByLoginName.do",
					data : "loginName=" + loginName,
					success : function(msg) {
						if (msg == "false") {
							$("#lovdmx-popup-window input[name='loginName']").addClass(
									"lovdmx-form-danger").val("")
									.focus();
							layer.msg(loginName + "登录名已存在,请重新输入!!!", {
								icon : 5
							});
						} else {
							$(this).removeClass("lovdmx-form-danger");
						}
					}
				});
			} else {
				$(this).removeClass("lovdmx-form-danger");
			}
		}
	});
	
	//登录名
	$("body").on("blur","#lovdmx-popup-window input[name='userName']",function() {
		var userName = $(this).val();
		if (userName == "" || userName == null
				|| userName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			var usName = $("#lovdmx-popup-window input[name='usName']").val();
			if (usName != userName) {
				$.ajax({
					type : "GET",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/account/findExistsByUserName.do",
					data : "userName=" + userName,
					success : function(msg) {
						if (msg == "false") {
							$("#lovdmx-popup-window input[name='userName']").addClass(
									"lovdmx-form-danger").val("")
									.focus();
							layer.msg(userName + "登录名已存在,请重新输入!!!", {
								icon : 5
							});
						} else {
							$(this).removeClass("lovdmx-form-danger");
						}
					}
				});
			} else {
				$(this).removeClass("lovdmx-form-danger");
			}
		}
	});
	
	
	//项目名
	$("body").on("blur","#lovdmx-popup-window input[name='projectName']",function() {
		var projectName = $(this).val();
		if (projectName == "" || projectName == null
				|| projectName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			var pjName = $("#lovdmx-popup-window input[name='pjName']").val();
			if (pjName != projectName) {
				$.ajax({
					type : "GET",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/project/findExistsByProjectName.do",
					data : "projectName=" + projectName,
					success : function(msg) {
						if (msg == "false") {
							$("#lovdmx-popup-window input[name='projectName']").addClass(
									"lovdmx-form-danger").val("")
									.focus();
							layer.msg(projectName + "登录名已存在,请重新输入!!!", {
								icon : 5
							});
						} else {
							$(this).removeClass("lovdmx-form-danger");
						}
					}
				});
			} else {
				$(this).removeClass("lovdmx-form-danger");
			}
		}
	});
	
	//RTRMAC
	$("body").on("blur","#lovdmx-popup-window input[name='rtrMac']",function() {
		var rtrMac = $(this).val();
		if (rtrMac == "" || rtrMac == null
				|| rtrMac == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			var before_rtrMac = $("#lovdmx-popup-window input[name='before_rtrMac']").val();
			if (before_rtrMac != rtrMac) {
				$.ajax({
					type : "GET",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/rtrDevice/judgeMacIsExits.do",
					data : "rtrMac=" + rtrMac,
					success : function(msg) {
						if (msg == "false") {
							$("#lovdmx-popup-window input[name='rtrMac']").addClass(
									"lovdmx-form-danger").val("")
									.focus();
							layer.msg(rtrMac + "MAC地址已存在,请重新输入!!!", {
								icon : 5
							});
						} else {
							$(this).removeClass("lovdmx-form-danger");
						}
					}
				});
			} else {
				$(this).removeClass("lovdmx-form-danger");
			}
		}
	});
	
	//RTRMAC
	$("body").on("blur","#lovdmx-popup-window input[name='spriteMac']",function() {
		var spriteMac = $(this).val();
		if (spriteMac == "" || spriteMac == null
				|| spriteMac == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			var before_spriteMac = $("#lovdmx-popup-window input[name='before_spriteMac']").val();
			if (before_spriteMac != spriteMac) {
				$.ajax({
					type : "GET",
					url : $("#pageContext").val()
							+ "/lovdmx/ht/spriteDevice/judgeMacIsExits.do",
					data : "spriteMac=" + spriteMac,
					success : function(msg) {
						if (msg == "false") {
							$("#lovdmx-popup-window input[name='spriteMac']").addClass(
									"lovdmx-form-danger").val("")
									.focus();
							layer.msg(spriteMac + "MAC地址已存在,请重新输入!!!", {
								icon : 5
							});
						} else {
							$(this).removeClass("lovdmx-form-danger");
						}
					}
				});
			} else {
				$(this).removeClass("lovdmx-form-danger");
			}
		}
	});
	
	//密码
	$("body").on("blur", "#lovdmx-popup-window input[name='password']", function() {
		var password = $(this).val();
		if (password == "" || password == null || password == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			if (password.length >= 6 && password.length <= 16) {
				$(this).removeClass("lovdmx-form-danger");
			} else {
				$(this).addClass("lovdmx-form-danger").val("").focus();
				layer.msg('密码格式错误，请输入6到16个字符', {
					icon : 5
				});
				return;
			}
		}
	});
	//昵称
	$("body").on("blur", "#lovdmx-popup-window input[name='nickName']", function() {
		var nickName = $(this).val();
		if (nickName == "" || nickName == null || nickName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			$(this).removeClass("lovdmx-form-danger");
		}
	});
	//电话
	$("body").on("blur", "#lovdmx-popup-window input[name='phone']", function() {
		var phone = $(this).val();
		if (phone != "" && phone != null && phone != undefined) {
			var flag = isPoneAvailable(phone);
			if (!flag) {
				layer.msg('请输入正确的号码!!!', {
					icon : 5
				});
				$(this).addClass("lovdmx-form-danger").focus();
				return;
			}
		}
	});
	//邮箱
	$("body").on("blur", "#lovdmx-popup-window input[name='email']", function() {
		var email = $(this).val();
		if (email != "" && email != null && email != undefined) {
			var flag = checkEmail(email);
			if (!flag) {
				layer.msg('请输入正确的邮箱!!!', {
					icon : 5
				});
				$(this).addClass("lovdmx-form-danger").focus();
				return;
			}
		}
	});
	
	//产品分类
	$("body").on("blur", "#lovdmx-popup-window input[name='categoryName']", function() {
		
		var categoryName = $(this).val();
		if (categoryName == "" || categoryName == null || categoryName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			$(this).removeClass("lovdmx-form-danger");
		}
	});
	
	//产品参数分类
	$("body").on("blur", "#lovdmx-popup-window input[name='typeName']", function() {
		
		var typeName = $(this).val();
		if (typeName == "" || typeName == null || typeName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			$(this).removeClass("lovdmx-form-danger");
		}
	});
	
	//产品参数
	$("body").on("blur", "#lovdmx-popup-window input[name='parameterName']", function() {
		
		var parameterName = $(this).val();
		if (parameterName == "" || parameterName == null || parameterName == undefined) {
			$(this).addClass("lovdmx-form-danger");
		} else {
			$(this).removeClass("lovdmx-form-danger");
		}
	});
	
	
	
	

	// 验证电话
	function isPoneAvailable(phone) {
		var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
		if (!myreg.test(phone)) {
			return false;
		} else {
			return true;
		}
	}

	// 验证邮箱
	function checkEmail(str) {
		var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}

});