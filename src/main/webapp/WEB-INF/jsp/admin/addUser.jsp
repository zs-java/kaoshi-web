<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/demo.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
$(function() {
	$("#groupCombotree").combotree({
		url:'${pageContext.request.contextPath }/admin/user/getAllGroups.json',
		valueField:'id',    
	    textField:'text',
	    editable: false,
		onLoadSuccess:function(node,data) {
			var t = $("#groupCombotree").combotree('tree');//获取tree  
			for (var i=0;i<data.length ;i++ ){
			   node= t.tree("find",data[i].id);  
			   t.tree('expandAll',node.target);//展开所有节点  
			}
			$("#groupValue").val(data[0].id);
			$("#groupCombotree").combotree('setValue',data[0].id);
		},
		onSelect:function(node) {
			$("#groupValue").val(node.id);
		}
	});
});
$(function() {
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/user/getAllRoles.json' ,
		dataType: 'json', 
		success: function(data){   
			$('#roleCombobox').combobox({            
				data:data,
				valueField:'roleId',        
				textField:'roleName',
				editable:false, //不可编辑
				onSelect:function(node) {
					$("#roleValue").val(node.roleId);
				}
			});
			for(var i=0; i < data.length; i++) {
				if(data[i].roleName == '学员') {
					$('#roleCombobox').combobox("select", data[i].roleId);
					$("#roleValue").val(data[i].roleId);
					break;
				}
			}
		}
	});
});
function submitForm() {
	if($("#pwd").val() != $("#rpwd").val()) {
		alert('两次输入密码不一致!');
		return;
	}
	
	$.ajax({
		cache: true,
		type: "POST",
		url:  "${pageContext.request.contextPath }/admin/user/addUser.action",
		data: $('#addUserForm').serialize(),// 你的formid
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(data) {
			if(data=='success') {
				alert('添加成功！');
				location.reload();
				return;
			}
			if(data=='usernameExists') {
				alert('用户名已经存在！');
				$("#username").val('');
				return;
			}
		}
	});
}
</script>
<style type="text/css">
body {
	overflow-x: hidden;
}
.input_user_info{
	border:1px solid #c3d9e0;
	width:200px;
	height:32px;
}
#all ul li {
	float: left;
	list-style: none;
	margin-left: 30px;
}
#footDiv {
	width: 100%;
	position: absolute;
	bottom: 0px;
	left: 0px;
	height: 50px;
	/* border: 1px solid black; */
	z-index: 99;
	font-size: 20px;
}
table tr {
	height: 50px;
}
#footDiv a {
	font-size: 20px;
}
</style>
</head>
<body>
  	<form id="addUserForm">
  	<div style="margin: 20px 0; "></div>
	 <div id="all">
		<ul>
			<li>
				<div class="easyui-panel" title="基本信息（必填）" style="width:500px;padding-bottom: 10px;">
		<div style="padding:20px 60px 20px 60px">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>用户名：</td>
	    			<td><input id="username" class="easyui-textbox" type="text" name="username" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td><input id="pwd" class="easyui-textbox" type="password" name="password" data-options="required:true,validType:'password'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>确认密码：</td>
	    			<td><input id="rpwd" class="easyui-textbox" type="password" name="rpwd" data-options="required:true,validType:'password'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>用户组：</td>
	    			<td>
						<input type="hidden" id="groupValue" name="groupId" />
						<select id="groupCombotree" class="easyui-combotree" style="width:156px;">
							<!-- <option>全部</option> -->
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>角色：</td>
	    			<td>
	    				<input id="roleValue" type="hidden" name="roleId" />
	    				<input id="roleCombobox" class="easyui-combobox" style="width: 100px;" />
	    			</td>
	    		</tr>
	    	</table>
	    </div>
	</div>
			</li>
			<li>
				<div class="easyui-panel" title="详细信息（选填）" style="width:500px">
		<div style="padding:20px 60px 20px 60px">
	    	<table cellpadding="5">
	    		<!-- <tr>
	    			<td>昵称：</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:false"></input></td>
	    		</tr> -->
	    		<tr>
	    			<td>真实姓名：</td>
	    			<td><input class="easyui-textbox" type="text" name="readlyName"></input></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱：</td>
	    			<td><input class="easyui-textbox" type="text" name="email" data-options="required:false,validType:'email'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>性别 ：</td>
	    			<td>
	    				<div class="input_user_info" style="width:200px;background-color: #fff;line-height: 32px;text-align: center;">
							<input name="intGender" type="radio" value="1" checked="checked"/>男
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="intGender" type="radio" value="0" />女
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>生日：</td>
	    			<td>
	    				<input id="dd" type="text" class="easyui-datebox" data-options="editable:false" name="birthday" style="width:205px;height:35px;float: left;">
	    			</td>
	    		</tr>
	    	</table>
	</div>
	    </div>
			</li>
		</ul>
	    
	</div>
    </form>
    <div id="groupsContent" class="menuContent" style="display:none; position: absolute;z-index: 999">
		<ul id="groups" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
	<div id="footDiv" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存用户信息</a>
    </div>
</body>
</html>