$(function(){
	//添加后端用户
    $("body").on("click","#manage-add",function(){

    	var pupop_html="";

    	//组装添加用户
    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:510px;height:460px;' >";
			pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加管理员</div>";
			pupop_html +="<div class='lovdmx-content' id='lovdmx-add-manage' style='height:410px'>";
				pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
					pupop_html +="<input type='hidden' name='lgnName' value=''>";
					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>登录名</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='loginName' placeholder='请输入用户名' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>密码</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='password' name='password' placeholder='请输入用户名' class='lovdmx-input'></div>";
						pupop_html +="<div class='lovdmx-form-mid lovdmx-word-aux'>6到16个字符</div>"
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>昵称</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='nickName' placeholder='请输入用户名' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'>手机</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='phone' placeholder='请输入号码' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'>邮箱</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='email' placeholder='请输入邮箱' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'></label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='lovdmx-submit-manage' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
					pupop_html +="</div>";
				pupop_html +="</div>";
			pupop_html +="</div>";
			pupop_html +="<span class='lovdmx-setwin'></span>";
			pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
			pupop_html +="<i class='iconfont icon-ic_close close'></i>";
		pupop_html +="</div>";

		$("body").append(pupop_html);
		addBackground();
    });

    //编辑后端用户
    $("body").on("click",".manage-edit",function(){
    	//ID
    	var _href=$(this).attr("data-link");
    	var pupop_html="";
    	$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:400px;height:450px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑管理员</div>";
					pupop_html +="<div class='lovdmx-content' style='height:400px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='manageId' value='"+data.manageId+"'>";
							pupop_html +="<input type='hidden' name='lgnName' value='"+data.loginName+"'>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>登录名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+data.loginName+"' readonly name='loginName' placeholder='请输入登录名' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>昵称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+data.nickName+"' name='nickName' placeholder='请输入昵称' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>手机</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+data.phone+"' name='phone' placeholder='请输入号码' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>邮箱</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+data.email+"' name='email' placeholder='请输入邮箱' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='lovdmx-update-manage' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});

    });
    
    
  //添加前端管理员
    $("body").on("click","#account-add",function(){
    	//路径
    	var _href=$(this).attr("data-link");
    	var pupop_html="";
    	$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//账号
		    	var account=data.account;
		    	//项目
		    	var projectList=data.projectList;
		    	//角色
		    	var roleList=data.roleList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:520px;height:520px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加前段管理员</div>";
					pupop_html +="<div class='lovdmx-content' style='height:470px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>登录名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='userName' placeholder='请输入登录名' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>昵称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='nickName' placeholder='请输入昵称' value class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>密码</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='password' name='password' placeholder='请输入密码' class='lovdmx-input'></div>";
								pupop_html +="<div class='lovdmx-form-mid lovdmx-word-aux'>6到16个数字或字符</div>"
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>角色分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='role' data-value='-1' readonly value='请选择角色' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit account-role'>";
											pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择角色</dd>";
										//遍历分类
										$.each(roleList,function(i,role){
											pupop_html +="<dd data-value='"+role.roleId+"'>"+role.roleName+"</dd>";
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item none'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='-1' readonly value='请选择项目' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
											pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择项目</dd>";
										//遍历分类
										$.each(projectList,function(i,project){
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>启用状态</label>";
								pupop_html +="<div class='lovdmx-input-block'>";
									pupop_html +="<input type='checkbox' lay-filter='switch' lay-verify='required' name='status' checked='checked' lay-skin='switch' lay-text='ON|OFF'>";
									pupop_html +="<div class='lovdmx-unselect lovdmx-form-switch lovdmx-form-onswitch'><em>ON</em><i></i></div>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='lovdmx-submit-account' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});

    });
    
    //编辑前端管理员
    $("body").on("click",".account-edit",function(){
    	//路径
    	var _href=$(this).attr("data-link");
    	var pupop_html="";
    	$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//账号
		    	var account=data.account;
		    	//项目
		    	var projectList=data.projectList;
		    	//角色
		    	var roleList=data.roleList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:400px;height:450px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑管理员</div>";
					pupop_html +="<div class='lovdmx-content' style='height:400px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='accountId' value='"+account.accountId+"'>";
							pupop_html +="<input type='hidden' name='usName' value='"+account.userName+"'>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>登录名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+account.userName+"' readonly name='userName' placeholder='请输入登录名' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>昵称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' value='"+account.nickName+"' name='nickName' placeholder='请输入昵称' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>角色分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='role' data-value='"+account.roleId+"' readonly value='"+account.roleName+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit account-role'>";
									//遍历分类
									$.each(roleList,function(i,role){
										if(role.roleId==account.roleId){
											pupop_html +="<dd data-value='"+role.roleId+"' class='lovdmx-this' >"+role.roleName+"</dd>";
										}else{
											pupop_html +="<dd data-value='"+role.roleId+"'>"+role.roleName+"</dd>";
										}
									});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item "+(account.roleId==1?'none':'')+"'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='"+(account.roleId==1?-1:account.projectId)+"' readonly value='"+(account.roleId==1?'请选择项目':account.projectName)+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
									pupop_html +="<dd data-value='-1' class='"+(account.roleId==1?'lovdmx-this':'')+"'>请选择项目</dd>";
									//遍历分类
									$.each(projectList,function(i,project){
										if(project.projectId==account.projectId){
											pupop_html +="<dd data-value='"+project.projectId+"' class='lovdmx-this' >"+project.projectName+"</dd>";
										}else{
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										}
									});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							var stick_class="";
							var _checked="";
							
							if(account.status==1){
								stick_class="lovdmx-form-onswitch";
								_checked="checked";
							}
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>启用状态</label>";
								pupop_html +="<div class='lovdmx-input-block'>";
									pupop_html +="<input type='checkbox' lay-filter='switch' lay-verify='required' name='status' "+_checked+" lay-skin='switch' lay-text='ON|OFF'>";
									pupop_html +="<div class='lovdmx-unselect lovdmx-form-switch "+stick_class+"'><em>OFF</em><i></i></div>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='lovdmx-update-account' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});

    });
    
    
  //编辑前端管理员
    $("body").on("click",".account-editPassword",function(){
    	//路径
    	var _href=$(this).attr("data-link");
    	var pupop_html="";
    	$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//账号
		    	var account=data.account;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:530px;height:230px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑前端管理员密码</div>";
					pupop_html +="<div class='lovdmx-content' style='height:480px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='accountId' value='"+account.accountId+"'>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>请输入新密码</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='password' name='password' placeholder='请输入新密码' class='lovdmx-input'></div>";
								pupop_html +="<div class='lovdmx-form-mid lovdmx-word-aux'>6到16个数字或字符</div>"
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='lovdmx-update-accountPassword' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});

    });
    
	$("body").on("click","#project-add",function(){
	    	
	    	var pupop_html="";
	    	//组装添加用户
	    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:440px;height:240px;' >";
				pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加项目</div>";
				pupop_html +="<div class='lovdmx-content' id='lovdmx-add-project'style='height:190px'>";
					pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
					
						pupop_html +="<div class='lovdmx-form-item'>";
							pupop_html +="<label class='lovdmx-form-lable'>项目名</label>";
							pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='projectName' placeholder='请输入项目名' class='lovdmx-input'></div>";
						pupop_html +="</div>";
						pupop_html +="<div class='lovdmx-form-item'>";
							pupop_html +="<label class='lovdmx-form-lable'></label>";
							pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-project' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
				pupop_html +="</div>";
				pupop_html +="<span class='lovdmx-setwin'></span>";
				pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
				pupop_html +="<i class='iconfont icon-ic_close close'></i>";
			pupop_html +="</div>";
			addBackground();
			$("body").append(pupop_html);
	    });
	    
	$("body").on("click",".project-edit",function(){
		
		//ID
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//项目信息
		    	var project=data.project;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:400px;height:240px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑项目</div>";
					pupop_html +="<div class='lovdmx-content' id='lovdmx-edit-project' style='height:190px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='projectId' value='"+project.projectId+"' >";
							pupop_html +="<input type='hidden' name='pjName' value='"+project.projectName+"'>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='projectName' value='"+project.projectName+"' placeholder='请输入项目名' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-project' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				addBackground();
				$("body").append(pupop_html);
		    }
		});
	});
	    
	    
	
	
	
	
	
	//添加设备机柜
	$("body").on("click","#rackDevice-add",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//项目
		    	var projectList=data.projectList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:160px;width:420px;height:650px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加设备机柜</div>";
					pupop_html +="<div class='lovdmx-content' style='height:600px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='-1' readonly value='请选择项目' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
											pupop_html +="<dd data-value='-1' class='lovdmx-this'>请选择项目</dd>";
											//遍历分类
											$.each(projectList,function(i,project){
												pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
											});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜名称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackName' placeholder='请输入机柜名称' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜编号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackIndex' placeholder='请输入机柜编号' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割起点x</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoX' placeholder='请输入视频切割起点x' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割起点y</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoY' placeholder='请输入视频切割起点y' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割width</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoWidth' placeholder='请输入视频切割width' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割height</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoHeight' placeholder='请输入视频切割height' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>经度(小数点)</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackLongitude' placeholder='请输入经度' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>纬度(小数点)</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackLatitude' placeholder='请输入纬度' value='0' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-rackDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	//添加设备机柜
	$("body").on("click",".rackDevice-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//账号
		    	var rackDevice=data.rackDevice;
		    	//项目
		    	var projectList=data.projectList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:160px;width:420px;height:650px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>修改设备机柜</div>";
					pupop_html +="<div class='lovdmx-content' style='height:600px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						pupop_html +="<input type='hidden' name='rackId' value='"+rackDevice.rackId+"' >";
						pupop_html +="<input type='hidden' name='beforeRackName' value='"+rackDevice.rackName+"' >";
						pupop_html +="<input type='hidden' name='beforeRackIndex' value='"+rackDevice.rackIndex+"' >";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='"+rackDevice.projectId+"' readonly value='"+rackDevice.projectName+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										//遍历分类
										$.each(projectList,function(i,project){
											if(project.projectId==rackDevice.projectId){
												pupop_html +="<dd data-value='"+project.projectId+"' class='lovdmx-this' >"+project.projectName+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜名称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackName' placeholder='请输入机柜名称' value='"+rackDevice.rackName+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜编号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackIndex' placeholder='请输入机柜编号' value='"+rackDevice.rackIndex+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割起点x</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoX' placeholder='请输入视频切割起点x' value='"+rackDevice.splitVideoX+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割起点y</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoY' placeholder='请输入视频切割起点y' value='"+rackDevice.splitVideoY+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割width</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoWidth' placeholder='请输入视频切割width' value='"+rackDevice.splitVideoWidth+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>视频切割height</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='splitVideoHeight' placeholder='请输入视频切割height'  value='"+rackDevice.splitVideoHeight+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>经度(小数点)</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackLongitude' placeholder='请输入经度' value='"+rackDevice.rackLongitude+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>纬度(小数点)</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rackLatitude' placeholder='请输入纬度' value='"+rackDevice.rackLatitude+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-rackDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	   
	//添加rtr设备
	$("body").on("click","#rtrDevice-add",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//机柜信息
		    	var projectList=data.projectList;
		    	//工作模式
		    	var curModeList =data.curModeList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:490px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加RTR设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:440px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<div class='lovdmx-form-item' id='popup-project-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='-1' readonly value='请选择项目' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										pupop_html +="<dd data-value='-1' class='lovdmx-this'>请选择项目</dd>";
										//遍历项目列表
										$.each(projectList,function(i,project){
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item' id='popup-rack-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>节点列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect rack_info' name='rack' data-value='-1' readonly value='请选择节点' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										pupop_html +="<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>";
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>RTR MAC地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rtrMac' placeholder='请输入MAC地址' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>工作模式</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='curMode' data-value='1' readonly value='"+curModeList[0]+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										//遍历分类
										$.each(curModeList,function(i,curMode){
											
											if(i==0){
												pupop_html +="<dd data-value='"+(i+1)+"' class='lovdmx-this' >"+curMode+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+(i+1)+"'>"+curMode+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>ip地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' placeholder='请输入ip地址' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备模型尺寸</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='modelSize' placeholder='请输入设备模型尺寸'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-rtrDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	//编辑rtr设备
	$("body").on("click",".rtrDevice-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//RTR设备信息
		    	var rtrDevice=data.rtrDevice;
		    	//机柜信息
		    	var projectList=data.projectList;
		    	//机柜信息
		    	var rackDevice=data.rackDevice;
		    	//机柜信息
		    	var rackDeviceList=data.rackDeviceList;
		    	//工作模式
		    	var curModeList =data.curModeList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:490px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑RTR设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:440px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						pupop_html +="<input type='hidden' name='rtrId' value='"+rtrDevice.rtrId+"'/>";
							
						pupop_html +="<div class='lovdmx-form-item' id='popup-project-select'>";
							pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
							pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
								pupop_html +="<div class='lovdmx-select-title'>";
									pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='"+(rtrDevice.rackId==null?-1:rackDevice.projectId)+"' readonly value='"+(rtrDevice.rackId==null?'请选择项目':rackDevice.projectName)+"' placeholder='请选择' />";
									pupop_html +="<span class='lovdmx-edge'></span>";
								pupop_html +="</div>";
								pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
									if(rtrDevice.rackId==null){
										pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择项目</dd>";
									}
									//遍历分类
									$.each(projectList,function(i,project){
										if(rtrDevice.rackId!=null && project.projectId==rackDevice.projectId){
											pupop_html +="<dd data-value='"+project.projectId+"' class='lovdmx-this' >"+project.projectName+"</dd>";
										}else{
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										}
									});
								pupop_html +="</dl>";
							pupop_html +="</div>";
						pupop_html +="</div>";
						
						pupop_html +="<div class='lovdmx-form-item'  id='popup-rack-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>节点列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect rack_info' name='rack' data-value='"+(rtrDevice.rackId==null?-1:rackDevice.rackId)+"' readonly value='"+(rtrDevice.rackId==null?'请选择节点':rackDevice.rackName)+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										if(rtrDevice.rackId==null){
											pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择节点</dd>";
										}else{
											//遍历分类
											$.each(rackDeviceList,function(i,rackDevice){
												if(rackDevice.rackId==rtrDevice.rackId){
													pupop_html +="<dd data-value='"+rackDevice.rackId+"' class='lovdmx-this' >"+rackDevice.rackName+"</dd>";
												}else{
													pupop_html +="<dd data-value='"+rackDevice.rackId+"'>"+rackDevice.rackName+"</dd>";
												}
											});
										}
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>RTR MAC地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='_rtrMac' placeholder='请输入MAC地址' value='"+rtrDevice.mac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>工作模式</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='curMode' data-value='1' readonly value='"+rtrDevice.curMode+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										//遍历分类
										$.each(curModeList,function(i,curMode){
											if(curMode==rtrDevice.curMode){
												pupop_html +="<dd data-value='"+(i+1)+"' class='lovdmx-this' >"+curMode+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+(i+1)+"'>"+curMode+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>ip地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' placeholder='请输入ip地址' readonly='readonly'  value='"+rtrDevice.ip+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备模型尺寸</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='modelSize' placeholder='请输入设备模型尺寸' value='"+(rtrDevice.modelSize==null?'':rtrDevice.modelSize)+"'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-rtrDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	
	//添加sprite设备
	$("body").on("click","#spriteDevice-add",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//机柜信息
		    	var rackDeviceList=data.rackDeviceList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:370px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加录放精灵设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:320px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='rack' data-value='-1' readonly value='请选择机柜' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										//遍历分类
										$.each(rackDeviceList,function(i,rackDevice){
											if(i==0){
												pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择机柜</dd>";
											}else{
												pupop_html +="<dd data-value='"+rackDevice.rackId+"'>"+rackDevice.rackName+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>录放精灵 MAC地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='spriteMac' placeholder='请输入MAC地址' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>ip地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' placeholder='请输入ip地址'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-spriteDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	
	//编辑sprite设备
	$("body").on("click",".spriteDevice-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//录放精灵设备
		    	var spriteDevice=data.spriteDevice;
		    	//项目列表信息
		    	var projectList=data.projectList;
		    	//节点信息
		    	var rackDevice=data.rackDevice;
		    	//机柜列表信息
		    	var rackDeviceList=data.rackDeviceList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:400px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑录放精灵设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:350px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='spriteId' value='"+spriteDevice.spriteId+"' />";

							pupop_html +="<div class='lovdmx-form-item' id='popup-project-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='"+(spriteDevice.rackId==null?-1:rackDevice.projectId)+"' readonly value='"+(spriteDevice.rackId==null?'请选择项目':rackDevice.projectName)+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										if(spriteDevice.rackId==null){
											pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择项目</dd>";
										}
										//遍历分类
										$.each(projectList,function(i,project){
											if(spriteDevice.rackId!=null && project.projectId==rackDevice.projectId){
												pupop_html +="<dd data-value='"+project.projectId+"' class='lovdmx-this' >"+project.projectName+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'  id='popup-rack-select'>";
									pupop_html +="<label class='lovdmx-form-lable'>节点列表</label>";
									pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
										pupop_html +="<div class='lovdmx-select-title'>";
											pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect rack_info' name='rack' data-value='"+(spriteDevice.rackId==null?-1:rackDevice.rackId)+"' readonly value='"+(spriteDevice.rackId==null?'请选择节点':rackDevice.rackName)+"' placeholder='请选择' />";
											pupop_html +="<span class='lovdmx-edge'></span>";
										pupop_html +="</div>";
											pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
											if(spriteDevice.rackId==null){
												pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择节点</dd>";
											}else{
												//遍历分类
												$.each(rackDeviceList,function(i,rackDevice){
													if(rackDevice.rackId==spriteDevice.rackId){
														pupop_html +="<dd data-value='"+rackDevice.rackId+"' class='lovdmx-this' >"+rackDevice.rackName+"</dd>";
													}else{
														pupop_html +="<dd data-value='"+rackDevice.rackId+"'>"+rackDevice.rackName+"</dd>";
													}
												});
											}
										pupop_html +="</dl>";
									pupop_html +="</div>";
								pupop_html +="</div>";
							
							
								pupop_html +="<div class='lovdmx-form-item '>";
									pupop_html +="<label class='lovdmx-form-lable'>MAC地址</label>";
									pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='_spriteMac' placeholder='请输入MAC地址' value='"+spriteDevice.mac+"' readonly='readonly' class='lovdmx-input'></div>";
								pupop_html +="</div>";
								
								pupop_html +="<div class='lovdmx-form-item '>";
									pupop_html +="<label class='lovdmx-form-lable'>IP地址</label>";
									pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' placeholder='请输入ip地址' value='"+spriteDevice.ip+"' readonly='readonly' class='lovdmx-input'></div>";
								pupop_html +="</div>";
								
								pupop_html +="<div class='lovdmx-form-item'>";
									pupop_html +="<label class='lovdmx-form-lable'></label>";
									pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-spriteDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
								pupop_html +="</div>";
							pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	
	//编辑dmx512控制盒设备
	$("body").on("click",".dmx512Device-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//dmx512控制盒设备
		    	var dmx512Device=data.dmx512Device;
		    	console.log(dmx512Device);
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:500px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑录放精灵设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:450px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='dmxId' value='"+dmx512Device.dmxId+"' />";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>RTR MAC</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='_rtrMac' placeholder='请输入RTR MAC地址' value='"+dmx512Device.rtrMac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>Dmx512 MAC</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='dmx512Mac' placeholder='请输入Dmx512 MAC地址' value='"+dmx512Device.dmx512Mac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>IP地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' placeholder='请输入IP地址' value='"+dmx512Device.ip+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>Universe信息</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='universe' placeholder='请输入Universe信息' value='"+dmx512Device.universe+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>RDM个数</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rdmNums' placeholder='请输入RDM个数' value='"+dmx512Device.rdmNums+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>RDM温度</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rdmts' placeholder='请输入RDM温度' value='"+dmx512Device.rdmts+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-dmx512Device' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	//编辑智能网关设备
	$("body").on("click",".intelligentGateway-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//录放精灵设备
		    	var intelligentGateway=data.intelligentGateway;
		    	//机柜信息
		    	var rackDeviceList=data.rackDeviceList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:430px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑智能网关设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:380px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='intelligentGatewayId' value='"+intelligentGateway.id+"' />";
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='rack' data-value='"+intelligentGateway.rackId+"' readonly value='"+intelligentGateway.rackName+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										if(intelligentGateway.rackID == -1){
											pupop_html +="<dd data-value='"+rackDevice.rackId+"' class='lovdmx-this' >"+rackDevice.rackName+"</dd>";
										}
										//遍历分类
										$.each(rackDeviceList,function(i,rackDevice){
											if(rackDevice.rackId==intelligentGateway.rackId){
												pupop_html +="<dd data-value='"+rackDevice.rackId+"' class='lovdmx-this' >"+rackDevice.rackName+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+rackDevice.rackId+"'>"+rackDevice.rackName+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>MAC地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='mac' value='"+intelligentGateway.mac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>IP地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='ip' value='"+intelligentGateway.ip+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>模式</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='mode'  value='"+(intelligentGateway.mode==0?"即时任务":"定时任务")+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>版本号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='version' value='"+intelligentGateway.version+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-intelligentGateway' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	
	//远程添加继电器列表
	$("body").on("click","#remote-add-relay",function(){
		
		//获取网关MAC信息
		var gatewayMac = $(".lovdmx-select-title .intelligentGateway_info").attr("data-value");
		if (gatewayMac == -1 || gatewayMac == -2 || gatewayMac=='') {
			layer.msg('请选择智能网关', {
				icon : 5
			});
			return;
		}
		
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    data:"gatewayMac="+gatewayMac,
		    success: function(data){
		    	//录放精灵设备
		    	var relayList=data;
		    	if(relayList==null || relayList.size==0 ){
		    		layer.msg('请重新选择智能网关,改网关没有485IP', {
						icon : 5
					});
		    		return;
		    	}
		    	
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:330px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>远程添加继电器</div>";
					pupop_html +="<div class='lovdmx-content' style='height:280px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='gatewayMac' value='"+gatewayMac+"' />";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>网关MAC</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='gatewayMac' value='"+gatewayMac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>485IP地址</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='rs485toNetIp' data-value='-1' readonly value='请选择485IP地址' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										pupop_html +="<dd data-value='-1' class='lovdmx-this' >请选择485IP</dd>";
										//遍历分类
										$.each(relayList,function(i,relay){
											pupop_html +="<dd data-value='"+relay.rs485toNetIp+"'>"+relay.rs485toNetIp+"</dd>";
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>新485IP地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='newRs485toNetIp' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='remote-submit-relay' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	
	//编辑智能网关设备
	$("body").on("click",".remote-edit-relay",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//录放精灵设备
		    	var relay=data.relay;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:430px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>远程配置继电器</div>";
					pupop_html +="<div class='lovdmx-content' style='height:380px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
							pupop_html +="<input type='hidden' name='relayId' value='"+relay.id+"' />";
							pupop_html +="<input type='hidden' name='before_slaveAddr' value='"+relay.slaveAddr+"' />";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>网关MAC</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='gatewayMac' value='"+relay.gatewayMac+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>485转IP</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='rs485toNetIp' value='"+relay.rs485toNetIp+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>端口</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='port'  value='"+relay.port+"' readonly='readonly' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>从站地址</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='slaveAddr' readonly='readonly' value='"+relay.slaveAddr+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>回路数量</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='number' value='"+relay.number+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='remote-update-relay' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	
	});
	
	//添加监控设备
	$("body").on("click","#monitoringDevice-add",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//机柜信息
		    	var projectList=data.projectList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:540px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加监控设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:490px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						
							pupop_html +="<div class='lovdmx-form-item' id='popup-project-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='-1' readonly value='请选择项目' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										pupop_html +="<dd data-value='-1' class='lovdmx-this'>请选择项目</dd>";
										//遍历项目列表
										$.each(projectList,function(i,project){
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item' id='popup-rack-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>节点列表</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect rack_info' name='rack' data-value='-1' readonly value='请选择节点' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
									pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										pupop_html +="<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>";
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>监控设备名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='deviceName' placeholder='请输入设备名' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备序列号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='deviceSerial' placeholder='请输入序列号' value='D19392297'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>授权码</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='authCode' placeholder='请输入授权码' value='CULUHS'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>云石萤账号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='appKey' placeholder='请输入云石萤账号' value='1062684e37fd4abb87b46be2b9f2324c' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备秘钥</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='appSecret' placeholder='请输入设备秘钥' value='da1d97fd8947e94dc06d32b24c147e16' class='lovdmx-input'></div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-monitoringDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});
	
	//编辑监控设备
	$("body").on("click",".monitoringDevice-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//列表列表信息
		    	var projectList=data.projectList;
		    	//机柜列表信息
		    	var rackDeviceList=data.rackDeviceList;
		    	//节点信息
		    	var rackDevice=data.rackDevice;
		    	//监控信息
		    	var monitoringDevice=data.monitoringDevice;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:540px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑监控设备</div>";
					pupop_html +="<div class='lovdmx-content' style='height:490px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						pupop_html +="<input type='hidden' name='deviceId' value='"+monitoringDevice.deviceId+"' />";
							
						pupop_html +="<div class='lovdmx-form-item' id='popup-project-select'>";
							pupop_html +="<label class='lovdmx-form-lable'>项目列表</label>";
							pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
								pupop_html +="<div class='lovdmx-select-title'>";
									pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect' name='project' data-value='"+rackDevice.projectId+"' readonly value='"+rackDevice.projectName+"' placeholder='请选择' />";
									pupop_html +="<span class='lovdmx-edge'></span>";
								pupop_html +="</div>";
								pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
									//遍历分类
									$.each(projectList,function(i,project){
										if(project.projectId==rackDevice.projectId){
											pupop_html +="<dd data-value='"+project.projectId+"' class='lovdmx-this' >"+project.projectName+"</dd>";
										}else{
											pupop_html +="<dd data-value='"+project.projectId+"'>"+project.projectName+"</dd>";
										}
									});
								pupop_html +="</dl>";
							pupop_html +="</div>";
						pupop_html +="</div>";
						
						pupop_html +="<div class='lovdmx-form-item'  id='popup-rack-select'>";
								pupop_html +="<label class='lovdmx-form-lable'>机柜分类</label>";
								pupop_html +="<div class='lovdmx-input-inline lovdmx-form-select lovdmx-form-select'>";
									pupop_html +="<div class='lovdmx-select-title'>";
										pupop_html +="<input type='text' class='lovdmx-input lovdmx_unselect rack_info' name='rack' data-value='"+monitoringDevice.rackId+"' readonly value='"+monitoringDevice.rackName+"' placeholder='请选择' />";
										pupop_html +="<span class='lovdmx-edge'></span>";
									pupop_html +="</div>";
										pupop_html +="<dl class='lovdmx-anim lovdmx-anim-upbit'>";
										//遍历分类
										$.each(rackDeviceList,function(i,rackDevice){
											if(rackDevice.rackId==monitoringDevice.rackId){
												pupop_html +="<dd data-value='"+rackDevice.rackId+"' class='lovdmx-this' >"+rackDevice.rackName+"</dd>";
											}else{
												pupop_html +="<dd data-value='"+rackDevice.rackId+"'>"+rackDevice.rackName+"</dd>";
											}
										});
									pupop_html +="</dl>";
								pupop_html +="</div>";
							pupop_html +="</div>";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>监控设备名</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='deviceName' placeholder='请输入设备名' value='"+monitoringDevice.deviceName+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备序列号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='deviceSerial' placeholder='请输入序列号' readonly value='"+monitoringDevice.deviceSerial+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>授权码</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='authCode' placeholder='请输入授权码' value='"+monitoringDevice.authCode+"'  class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>云石萤账号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='appKey' placeholder='请输入云石萤账号' value='"+monitoringDevice.appKey+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>设备秘钥</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='appSecret' placeholder='请输入设备秘钥'  value='"+monitoringDevice.appSecret+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-monitoringDevice' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});
	
	
	//添加监控设备
	$("body").on("click","#power-add",function(){
		var pupop_html="";
    	//组装添加用户
    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:270px;' >";
			pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加权限功能</div>";
			pupop_html +="<div class='lovdmx-content' style='height:220px'>";
				pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
				
					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>权限功能id</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='id' placeholder='请输入功能id' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>权限功能名称</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='name' placeholder='请输入功能名称' class='lovdmx-input'></div>";
					pupop_html +="</div>";
				
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'></label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-power' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
					pupop_html +="</div>";
				pupop_html +="</div>";
			pupop_html +="</div>";
			pupop_html +="<span class='lovdmx-setwin'></span>";
			pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
			pupop_html +="<i class='iconfont icon-ic_close close'></i>";
		pupop_html +="</div>";
		$("body").append(pupop_html);
		addBackground();
	});
	
	
	
	//编辑监控设备
	$("body").on("click",".power-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//权限功能信息
		    	var power=data.power;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:700px;top:250px;width:420px;height:220px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>编辑权限功能</div>";
					pupop_html +="<div class='lovdmx-content' style='height:170px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						pupop_html +="<input type='hidden' name='id' value='"+power.id+"' />";
							
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>权限功能名称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='name' placeholder='请输入功能名称'  value='"+power.name+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-power' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});

	
	//添加角色
	$("body").on("click","#role-add",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//机柜信息
		    	var powerList=data.powerList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:520px;top:250px;width:840px;height:340px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加角色</div>";
					pupop_html +="<div class='lovdmx-content' style='height:290px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";

							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>角色名称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='roleName' placeholder='请输入角色名称' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>权限</label>";
								pupop_html +="<div class='lovdmx-input-block'>";
									$.each(powerList,function(i,power){
										pupop_html +="<input type='checkbox' name='' lay-skin='primary' value='"+power.id+"' title='"+power.name+"'/>";
										pupop_html +="<div class='lovdmx-unselect lovdmx-form-checkbox  lovdmx-checkbox-equals-width' lay-skin='primary'>";
											pupop_html +="<span>"+power.name+"</span>";
											pupop_html +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
										pupop_html +="</div>";
									});
								pupop_html +="</div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-role' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});
   
	//编辑角色
	$("body").on("click",".role-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//角色信息
		    	var role = data.role;
		    	//权限功能信息
		    	var powerList=data.powerList;
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:520px;top:250px;width:840px;height:340px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加角色</div>";
					pupop_html +="<div class='lovdmx-content' style='height:290px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
						pupop_html +="<input type='hidden' name='roleId' value='"+role.roleId+"' />";
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>角色名称</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='roleName' value='"+role.roleName+"' placeholder='请输入角色名称' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							//分割
							var ids = role.roleLimit.split(",");
							//判断是否存在
							var isExits=false;
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>权限</label>";
								pupop_html +="<div class='lovdmx-input-block'>";
									$.each(powerList,function(i,power){
										isExits=false;
										//判断数组是否大于零 且 只要判断数组下标0 是否相等,因为列表是有序的
										if(ids.length>0 && power.id==ids[0]){
											//存在 数组前移
											ids.shift();
											isExits=true;
										}
										
										pupop_html +="<input type='checkbox' name='' lay-skin='primary' value='"+power.id+"' title='"+power.name+"' "+(isExits==true?'checked':'')+" />";
										pupop_html +="<div class='lovdmx-unselect lovdmx-form-checkbox  lovdmx-checkbox-equals-width  "+(isExits==true?'lovdmx-form-checked':'')+" ' lay-skin='primary' >";
											pupop_html +="<span>"+power.name+"</span>";
											pupop_html +="<i class='icon-iconfontcheck lovdmx-icon iconfont icon-ok'></i>";
										pupop_html +="</div>";
									});
								pupop_html +="</div>";
							pupop_html +="</div>";
						
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-role' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});
	
	
	//添加角色
	$("body").on("click","#relay-application-add",function(){
		//路径
		var pupop_html="";
    	//组装添加用户
    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:520px;top:250px;width:510px;height:270px;' >";
			pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加角色</div>";
			pupop_html +="<div class='lovdmx-content' style='height:220px'>";
				pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";

					pupop_html +="<div class='lovdmx-form-item '>";
						pupop_html +="<label class='lovdmx-form-lable'>程序版本号</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='applicationVersion' placeholder='请输入版本号' class='lovdmx-input'></div>";
					pupop_html +="</div>";
					
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'>上传文件</label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='text' placeholder='请上传文件' readonly='readonly' class='lovdmx-input'></div>";
						pupop_html +="<input type='file' name='file' onchange='uploadFile(this)'  class='lovdmx-upload-file'>";
						pupop_html +="<button class='lovdmx-btn upload-file' type='button'>上传文件</button>";
					pupop_html +="</div>";
					
					pupop_html +="<div class='lovdmx-form-item'>";
						pupop_html +="<label class='lovdmx-form-lable'></label>";
						pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='submit-relay-application' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
					pupop_html +="</div>";
				pupop_html +="</div>";
			pupop_html +="</div>";
			pupop_html +="<span class='lovdmx-setwin'></span>";
			pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
			pupop_html +="<i class='iconfont icon-ic_close close'></i>";
		pupop_html +="</div>";
		$("body").append(pupop_html);
		addBackground();
	});
	
	//添加角色
	$("body").on("click",".relay-application-edit",function(){
		//路径
		var _href=$(this).attr("data-link");
		var pupop_html="";
		$.ajax({
		    type: "GET",
		    url: _href,
		    success: function(data){
		    	//程序版本号
		    	var applicationVersion=data.applicationVersion;
				//路径
				var pupop_html="";
		    	//组装添加用户
		    	pupop_html +="<div class='lovdmx-popup-window lovdmx-admin' data-value='window' id='lovdmx-popup-window' style='left:520px;top:250px;width:510px;height:270px;' >";
					pupop_html +="<div class='lovdmx-title' id='drag' style='cursor: move;' onselectstart='return false'>添加角色</div>";
					pupop_html +="<div class='lovdmx-content' style='height:220px'>";
						pupop_html +="<div class='lovdmx-form' style='padding: 20px 30px 0 0;'>";
		
							pupop_html +="<div class='lovdmx-form-item '>";
								pupop_html +="<label class='lovdmx-form-lable'>程序版本号</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' name='applicationVersion' placeholder='请输入版本号' value='"+applicationVersion+"' class='lovdmx-input'></div>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'>上传文件</label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='text' placeholder='请上传文件'readonly='readonly' class='lovdmx-input'></div>";
								pupop_html +="<input type='file' name='file' onchange='uploadFile(this)'  class='lovdmx-upload-file'>";
								pupop_html +="<button class='lovdmx-btn upload-file' type='button'>上传文件</button>";
							pupop_html +="</div>";
							
							pupop_html +="<div class='lovdmx-form-item'>";
								pupop_html +="<label class='lovdmx-form-lable'></label>";
								pupop_html +="<div class='lovdmx-input-inline'><input type='button' id='update-relay-application' placeholder='提交' class='lovdmx-btn' value='确认'></div>";
							pupop_html +="</div>";
						pupop_html +="</div>";
					pupop_html +="</div>";
					pupop_html +="<span class='lovdmx-setwin'></span>";
					pupop_html +="<span class='lovdmx-resize' id='lovdmx-resize'></span>";
					pupop_html +="<i class='iconfont icon-ic_close close'></i>";
				pupop_html +="</div>";
				$("body").append(pupop_html);
				addBackground();
		    }
		});
	});
    
});

//添加背景
function addBackground(){
	var background="";
	background +="<div class='lovdmx-shade' id='lovdmx-shade2' style='z-index: 9999; background-color: rgb(0, 0, 0); opacity: 0.3;'></div>";
	$("body").append(background);
}




