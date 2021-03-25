$(function() {

	
	$("body").on("click","#lovdmx-popup-window .upload",function(){
		return $(this).prev("input[type='file']").click();
	});
	
	$("body").on("click","#lovdmx-popup-window  .upload-file",function(){
		return $(this).prev("input[type='file']").click();
	});
	
	

});

/* 获取图片的值 */
function getPath(file) {

	var agent = navigator.userAgent;
	if (agent.indexOf("MSIE") >= 1) {
		url = file.value;
	} else if (agent.indexOf("Firefox") > 0) {
		url = window.URL.createObjectURL(file.files.item(0));
	} else if (agent.indexOf("Chrome") > 0) {
		url = window.URL.createObjectURL(file.files.item(0));
	}
	return url;
}

// 上传单张图片
function uploadPicture(obj) {

	var $img = $(obj).next(".lovdmx-upload-img");

	// 判断是否选中图片
	var flag = fileNameIsNotImage(obj);

	if (flag) {
		var url = getPath(obj);
		$img.attr("src", url);
	}else{
		$img.attr("src",$("#pageContext").val() +"/image/zanwu.png");
	}
}

// 上传单张图片
function uploadVideo(obj) {

	var $img = $(obj).next(".lovdmx-upload-img");

	// 判断是否选中图片
	var flag = fileNameIsNotVideo(obj);

	if (flag) {
		// var url = getPath(obj);
		$img.attr("src", $("#pageContext").val() + "/image/video.jpg");
	}else{
		$img.attr("src", $("#pageContext").val() + "/image/zanwuVideo.png");
	}
}

//上传单张图片
function uploadFile(obj) {

	// 判断是否选中文件
	//var flag = fileNameIsNotFile(obj);

	var $input = $(obj).parent(".lovdmx-form-item").find("input[type='text']");
	//if (flag) {
		var val = obj.value;
		$input.val(val);
	//}else{
		//$input.val("请上传文件");
	//}
}
function updateUploadVideo(obj) {
	var $img = $(obj).next(".lovdmx-upload-img");

	// 判断是否选中图片
	var flag = fileNameIsNotVideo(obj);

	if (flag) {
		// var url = getPath(obj);
		$img.attr("src", $("#pageContext").val() + "/image/updateVideoImg.png");
	}else{
		$img.attr("src", $("#pageContext").val() + "/image/video.jpg");
	}
}

// 判断是否图片
function fileNameIsNotImage(obj) {
	var val = obj.value;
	if (val == null || val == "") {
		return false;
	} else if (!/.(gif|jpg|jpeg|png)$/.test(val.toLowerCase())) {
		layer.alert("图片类型必须是.gif,jpeg,jpg,png中的一种", {
			icon : 5
		});
		return false;
	}

	return true;
}

// 判断是否视频
function fileNameIsNotVideo(obj) {

	var val = obj.value;
	if (val == null || val == "") {
		return false;
	} else if (!/.(avi|flv|mpg|mpeg|mpe|m1v|m2v|mpv2|mp2v|dat|ts|tp|tpr|pva|pss|mp4|m4v|m4p|m4b|3gp|3gpp|3g2|3gp2|ogg|mov|qt|amr|rm|ram|rmvb|rpm|gif|jpg|jpeg|png)$/
			.test(val.toLowerCase())) {
		layer
				.alert(
						"视频或图片类型必须是.avi,flv,mpg,mpeg,mpe,m1v,m2v,mpv2,mp2v,dat,ts,tp,tpr,pva,pss,mp4,m4v,m4p,m4b,3gp,3gpp,3g2,3gp2,ogg,mov,qt,amr,rm,ram,rmvb,rpm,gif|jpg|jpeg|png中的一种",
						{
							icon : 5
						});
		return false;
	}

	return true;
}
// 判断是否文件
function fileNameIsNotFile(obj) {

	var val = obj.value;
	if (val == null || val == "") {
		return false;
	} else if (!/.(jpg|png|rar|txt|zip|doc|ppt|xls|pdf|docx|xlsx)$/.test(val
			.toLowerCase())) {
		layer.alert(
				"文件类型必须是.jpg|png|rar|txt|zip|doc|ppt|xls|pdf|docx|xlsx中的一种", {
					icon : 5
				});
		return false;
	}
	
	return true;
}

