//路径
var path =$("#pageContext").val();
// 移到右边
$("body").on("click", "#parameter_add", function() {
	// 先判断是否有选中
	if (!$("#select1 option").is(":selected")) {
		layer.msg('请选中需要移动的选项', {
			icon : 5
		});
	}
	// 获取选中的选项，删除并追加给对方
	else {
		$('#select1 option:selected').appendTo('#select2');
	}
});
// 移到左边
$("body").on("click", "#parameter_remove", function() {
	// 先判断是否有选中
	if (!$("#select2 option").is(":selected")) {
		layer.msg('请选择需要移动的选项', {
			icon : 5
		});
	} else {
		$('#select2 option:selected').appendTo('#select1');
	}
});

// 全部移到右边
$("body").on("click", "#parameter_addAll", function() {
	// 获取全部的选项,删除并追加给对方
	$('#select1 option').appendTo('#select2');
});

// 全部移到左边
$("body").on("click", "#parameter_removeAll", function() {
	$('#select2 option').appendTo('#select1');
});

$("body").on("dblclick", "#select1", function() { // 绑定双击事件
	// 双击选项
	// 获取全部的选项,删除并追加给对方
	$("option:selected", this).appendTo('#select2'); // 追加给对方
});

// 双击选项
$("body").on("dblclick", "#select2", function() { // 绑定双击事件
	$("option:selected", this).appendTo('#select1');
});

//添加参数
function updateParameter(){
	//最新选中参数
	var option_list = $("#select2 option");
	
	var $parameter  = $("#parameter");
	
	var parameter_list=$parameter.find(".lovdmx-input-block .lovdmx-unselect");

	//已存在
	var list1 = new Array();
	//未存在
	var list2 = new Array();
	
	var flag=false;
	//获取过期的数据
	for(var j=0;j<parameter_list.length;j++){
		var index = $(parameter_list[j]).attr("data-id");
		for(var i=0;i<option_list.length;i++){
			var id =option_list.eq(i).val();
			if(index==id){
				list1.push(index)
				flag=true;
				break;
			}
		}
		if(!flag){
			list2.push(index);
		}
		flag=false;
	}
	var index=option_list.selectedIndex;
	//添加未存在对象
	for(var i=0;i<option_list.length;i++){
		var option=option_list[i];
		var index =option.value;
		for(var j=0;j<list1.length;j++){
			var value=list1[j];
			if(index==value){
				flag=true;
				break;
			}
		}
		if(!flag){
			var parmt = $(".upload-parameter"+option.value);
			if(parmt.length==0){
				var typeId = $(option_list[i]).attr("data-typeId");
				//图片
				if(typeId==1){
					addImgType(option,$parameter);
					//特点
				}else if(typeId==2){
					addPerformanceType(option,$parameter);
					//规格
				}else if(typeId==3){
					addSpecificationType(option,$parameter);
					//文件
				}else if(typeId==4){
					addFileType(option,$parameter);
				}
			}
		}
		flag=false;
	}
	
	//移除不存在的参数
	for(var j=0;j<list2.length;j++){
		var index=list2[j];
		$(".upload-parameter"+index).addClass("none");
	}
	
	var addParameter="";
	//移除之前所有参数
	$parameter.find(".lovdmx-input-block").empty();
	//组装最新参数
	for(var j=0;j<option_list.length;j++){
		var parameter =option_list[j];
		var tyepId=$(parameter).attr("data-typeId");
		addParameter +="<div class='lovdmx-unselect lovdmx-form-checkbox  primary parameter"+parameter.value+"' data-typeId='"+tyepId+"'  data-id='"+parameter.value+"'>";
			addParameter +="<span>"+parameter.text+"</span>";
			addParameter +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
		addParameter +="</div>";
	}
	
	//赋值最新参数
	$parameter.find(".lovdmx-input-block").append(addParameter);
	
}


// 添加图片类型
function addImgType(obj,$parameter) {
	var addImg="";
	addImg +="<div class='lovdmx-form-item none new-parameter upload-parameter"+obj.value+"' data-id='-1' >";
		addImg +="<label class='lovdmx-form-lable'>"+obj.text+"</label>";
		addImg +="<div class='lovdmx-upload-preview'>";
			addImg +="<ul class='fl_child clearFix'>";
				addImg +="<li><input type='file' name='file' hidden='hidden' onchange='uploadPicture(this)'><img src='/lovdmx_web/image/zanwu.png' class='lovdmx-upload-img upload'>";
				addImg +="</li>";
			addImg +="</ul>";
		addImg +="</div>";
	addImg +="</div>";
	
	$parameter.after(addImg);
}
// 添加性能描述类型
function addPerformanceType(obj,$parameter) {
	
	var addPerformance="";
	
	addPerformance +="<div class='lovdmx-form-item none new-parameter  upload-parameter"+obj.value+"' data-id='-1' >";
			addPerformance +="<label class='lovdmx-form-lable'>"+obj.text+"</label>";
			addPerformance +="<table class='table table-parameter'>";
				addPerformance +="<thead>";
					addPerformance +="<tr>";
						addPerformance +="<th>";
							addPerformance +="<button class='lovdmx-btn' id='add-performance' type='button'>添加特点</button>";
						addPerformance +="</th>";
						addPerformance +="<th>操作</th>";
					addPerformance +="</tr>";
				addPerformance +="</thead>";
			addPerformance +="<tbody>";
				addPerformance +="<tr>";
					addPerformance +="<td><input type='text' name='content' placeholder='请输入性能特点' class='lovdmx-input'></td>";
					addPerformance +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
				addPerformance +="</tr>";
			addPerformance +="</tbody>";
			
			addPerformance +="<tfoot>";
				addPerformance +="<tr>";
					addPerformance +="<td colspan='2'><input type='text' name='annotation'  placeholder='请输入 注释' class='lovdmx-input'></td>";
				addPerformance +="</tr>";
			addPerformance +="</tfoot>";
		addPerformance +="</table>";
	addPerformance +="</div>";
	
	$parameter.after(addPerformance);
	
}



// 添加规格类型
function addSpecificationType(obj,$parameter) {
	
	var addSpecification="";
	
	addSpecification +="<div class='lovdmx-form-item none new-parameter upload-parameter"+obj.value+"' data-id='-1' >";
		addSpecification +="<label class='lovdmx-form-lable'>"+obj.text+"</label>";
		addSpecification +="<table class='table table-parameter'>";
			addSpecification +="<thead>";
				addSpecification +="<tr>";
					addSpecification +="<th colspan='2'>";
					addSpecification +="<button class='lovdmx-btn' id='add-specification' type='button'>添加规格</button>";
					addSpecification +="</th>";
					addSpecification +="<th>操作</th>";
				addSpecification +="</tr>";
			addSpecification +="</thead>";
			addSpecification +="<tbody>";
				addSpecification +="<tr>";
					addSpecification +="<td><input type='text' name='username' placeholder='请输入规格参数' class='lovdmx-input'></td>";
					addSpecification +="<td><input type='text' name='username' placeholder='请输入规格参数' class='lovdmx-input'></td>";
					addSpecification +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
				addSpecification +="</tr>";
			addSpecification +="</tbody>";
		addSpecification +="</table>";
	addSpecification +="</div>";
	
	$parameter.after(addSpecification);
}

// 添加文件类型
function addFileType(obj,$parameter) {
	var addFile = "";
	
	addFile +="<div class='lovdmx-form-item none new-parameter  upload-parameter"+obj.value+"' data-id='-1' >";
		addFile +="<label class='lovdmx-form-lable'>"+obj.text+"</label>";
		addFile +="<div class='lovdmx-input-inline'><input type='text' placeholder='请上传文件' class='lovdmx-input'></div>";
		addFile +="<input type='file' name='file' onchange='uploadFile(this)'  class='lovdmx-upload-file'>";
		addFile +="<button class='lovdmx-btn upload-file' type='button'>上传图片</button>";
	addFile +="</div>";
	
	$parameter.after(addFile);
}

function on_offStatus(obj){
	if (!$(obj).is('.lovdmx-form-onswitch')) {
		$(obj).addClass("lovdmx-form-onswitch");
		$(obj).find("em").html("ON");
		$(obj).prev("input[type='checkbox']").attr("checked","checked");
	}else{
		$(obj).removeClass("lovdmx-form-onswitch");
		$(obj).find("em").html("OFF");
		$(obj).prev("input[type='checkbox']").attr("checked",null);
	}
}

//添加规格
$("body").on("click","#add-specification",function(){
	
	var addSpecification="";
	
	addSpecification +="<tr>";
		addSpecification +="<td><input type='text' name='username' placeholder='请输入规格参数' class='lovdmx-input'></td>";
		addSpecification +="<td><input type='text' name='username' placeholder='请输入规格参数' class='lovdmx-input'></td>";
		addSpecification +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	addSpecification +="</tr>"
		
	$(this).parents(".table-parameter").find("tbody").append(addSpecification);
});

//添加规格
$("body").on("click","#add-performance",function(){
	
	var performance="";
	
	performance +="<tr>";
		performance +="<td><input type='text' name='content'  placeholder='请输入性能特点' class='lovdmx-input'></td>";
		performance +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	performance +="</tr>";
		
	$(this).parents(".table-parameter").find("tbody").append(performance);
});

$("body").on("click","#add-aboutusInfo",function(){
	var performance="";
	performance +="<tr>";
		performance +="<td><input type='text' name='content'  placeholder='请输入简介信息' class='lovdmx-input'></td>";
		performance +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	performance +="</tr>";
	
	$(this).parents(".table").find("tbody").append(performance);
});

//移除表单 tr元素
$("body").on("click","#lovdmx-popup-window .remove-table-tr",function(){
	$(this).parents("tr").remove();
});


$("body").on("click","#lovdmx-popup-window .add-footerInfo",function(){
	//路径

	var addAddressInfo ="";
	addAddressInfo +="<tr>";
		addAddressInfo +="<td><input type='text' name='addressInfo' placeholder='请输入信息' class='lovdmx-input'></td>";
		addAddressInfo +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	addAddressInfo +="</tr>";
	
	$(this).parents(".table").find("tbody").append(addAddressInfo);
});


//添加图片
$("body").on("click","#lovdmx-popup-window .add-img",function(){
	//路径
	//var path =$("#pageContext").val();
	var add_img ="<li><input type='file' data-type='0' data-index='-1'  name='ewmImg' hidden='hidden' onchange='uploadPicture(this)' /><img src='"+path+"/image/zanwu.png' class='lovdmx-upload-img upload' /><i class='iconfont icon-ic_close remove-img'></i></li>";
	$(this).parents(".lovdmx-form-item").next(".lovdmx-form-item").find(".add-picture").append(add_img);
});

$("body").on("click",".remove-img",function(){
	$(this).parents("li").remove();
});


//添加app
$("body").on("click","#lovdmx-popup-window .add-app",function(){
	
	var add_app="";
	add_app +="<tr>";
		add_app +="<td><input type='file' name='appImg' data-type='0' data-index='-1'  hidden='hidden' onchange='uploadPicture(this)' /><img class='lovdmx-upload-img upload' src='"+path+"/image/zanwu.png' ></td>";
		add_app +="<td><===></td>";
		add_app +="<td><input type='text' name='appPath' placeholder='请输入简介信息' class='lovdmx-input'></td>";
		add_app +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	add_app +="</tr>";
	
	$(this).parents(".lovdmx-form-item").next(".lovdmx-form-item").find(".table tbody").append(add_app);
	
});

//选中置顶
$("body").on("click","#lovdmx-popup-window .lovdmx-form-switch",function(){
	//开关
	on_offStatus(this);
});


//后端管理员 设置账号状态
$("body").on("click",".manage_status .lovdmx-form-switch",function(){
	
	//开关
	on_offStatus(this);
	
	//状态
	var status=0;
	if($(this).prev("input[type='checkbox']").attr("checked")!=undefined){
		status=1;
	}
	
	var manageId=$(this).parents("tr").find(".table-checked .lovdmx-table-cell").attr("data-index");
	
	$.ajax({
		type : "GET",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/manage/updateStatus.do",
		data : "manageId=" + manageId + "&status="
				+ status
	});
	
});

//前端管理员 设置账号状态
$("body").on("click",".account_status .lovdmx-form-switch",function(){
	
	//开关
	on_offStatus(this);
	
	//状态
	var status=0;
	if($(this).prev("input[type='checkbox']").attr("checked")!=undefined){
		status=1;
	}
	
	var accountId=$(this).parents("tr").find(".table-checked .lovdmx-table-cell").attr("data-index");
	
	$.ajax({
		type : "GET",
		url : $("#pageContext").val()
				+ "/lovdmx/ht/account/updateStatus.do",
		data : "accountId=" + accountId + "&status="
				+ status
	});
	
});








//添加新闻 引导词
$("body").on("click","#add-news-word",function(){
	var news_word="";
	news_word +="<tr>";
		news_word +="<td><input type='text' name='word'  placeholder='请输入(引导)词' class='lovdmx-input'></td>";
		news_word +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	news_word +="</tr>";
	
	$(this).parents(".table").find("tbody").append(news_word);
});

//添加新闻 内容
$("body").on("click","#add-news-content",function(){
	var news_content="";
	news_content +="<tr>";
		news_content +="<td><input type='text' name='content'  placeholder='请输入内容' class='lovdmx-input'></td>";
		news_content +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	news_content +="</tr>";
	
	$(this).parents(".table").find("tbody").append(news_content);
});


//添加新闻动态 内容
$("body").on("click","#add-dynamicNews-content",function(){
	var dynamicNews_content="";
	dynamicNews_content +="<tr>";
		dynamicNews_content +="<td><input type='text' name='content'  placeholder='请输入内容' class='lovdmx-input'></td>";
		dynamicNews_content +="<td><a class='lovdmx-btn lovdmx-btn-del lovdmx-btn-xs  remove-table-tr'><i class='icon-shanchu lovdmx-icon iconfont '></i>删除</a></td>";
	dynamicNews_content +="</tr>";
	
	$(this).parents(".table").find("tbody").append(dynamicNews_content);
});

//添加图片
$("body").on("click","#lovdmx-popup-window .add-dynamicNews-img",function(){
	//路径
	//var path =$("#pageContext").val();
	var add_img ="<li><input type='file' data-type='0' data-index='-1'  name='imgs' hidden='hidden' onchange='uploadPicture(this)' /><img src='"+path+"/image/zanwu.png' class='lovdmx-upload-img upload' /><i class='iconfont icon-ic_close remove-img'></i></li>";
	$(this).parents(".lovdmx-form-item").next(".lovdmx-form-item").find(".add-picture").append(add_img);
});

//新闻动态 选择类型
$("body").on("click","#lovdmx-popup-window .lovdmx-form button.lovdmx-select-btn",function(){
	$("#lovdmx-popup-window .lovdmx-form button.lovdmx-select-btn").removeClass("active");
	$(this).addClass("active");
	var typeId= $(this).attr("data-type");
	if(typeId==1){
		$(".dynamicNews-img").removeClass("none");
		$(".dynamicNews-video").addClass("none");
	}else{
		$(".dynamicNews-img").addClass("none");
		$(".dynamicNews-video").removeClass("none");
	}
});






