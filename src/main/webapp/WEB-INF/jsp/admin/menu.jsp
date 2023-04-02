<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理-在线考试系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/previewQsn.js"></script>
<script type="application/javascript">
var baseUrl = "${pageContext.request.contextPath }";
function addTab(title, url){
	if ($('#tt').tabs('exists', title)){
		$('#tt').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tt').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}
function closeSelectTab() {
	var tab=$('#tt').tabs('getSelected');//获取当前选中tabs  
    var index = $('#tt').tabs('getTabIndex',tab);//获取当前选中tabs的index  
    $('#tt').tabs('close',index);//关闭对应index的tabs  
}
function closeTab(title){
	$('#tt').tabs('close',title)
}
function closeTabByTitle(title) {
	$('#tt').tabs('close', title);
	var tabs = $('#tt').tabs('tabs');
	if (tabs.length <= 5) {
		$('#tt').tabs('hideTool');
	}
}
function openEditDialog(id) {
	$("#editUserDialog").dialog({
		title:'用户信息修改',
		width:500,
		height:600,
		collapsible: false,
        maximizable: false,
        resizable: true,
        modal: true,
		//closed: true,
		href:'${pageContext.request.contextPath }/admin/user/editUser.html?id=' + id,
		//content:"<iframe scrolling='auto' frameborder='0' src='${pageContext.request.contextPath }/admin/user/editUser.html?id=" + id + "' style='width:100%; height:100%;'></iframe>",
		iconCls:"icon-ok",
		buttons:[{
			text:'修改 ',
			iconCls:'icon-save',
			handler:function() {
				
				if($("#newpwd").val() != $("#rpwd").val()) {
					alert('两次密码必须相同');
					return;
				}
				
				var formData = new FormData(document.getElementById("editUserForm"));//表单id
				$.ajax({
				   url: '${pageContext.request.contextPath}/admin/user/updateUser.action' ,
				   type: 'POST',
				   data: formData,
				   async: false,
				   cache: false,
				   contentType: false,
				   processData: false,
				   success: function (result) {
					   if(result == 'success') {
						   alert('修改成功');
						   $('#editUserDialog').dialog('close');
					   } else {
						   alert(result);
					   }
					   
				   }
				});
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function() {
				$('#editUserDialog').dialog('close');
			}
		}
		],
		onLoad:function() {
			var date = $("#birthdayValue").val();
			$("#birthday").datebox("setValue", date);
			
			$("#iconFile").filebox({
				onChange:function() {
					var file = $(this).context.ownerDocument.activeElement.files[0];
					$("#iconImg").attr("src", getObjectUrl(file));
				}
			});
			$("#query_group").combotree({
				onSelect:function(node) {
					$("#groupHidden").val(node.id);
				}
			});
		}
	});
}
function getObjectUrl(file) {
	var url = null;
	if(window.createObjectURL != undefined)
		url = window.createObjectURL(file);
	else if (window.URL != undefined)
		url = window.URL.createObjectURL(file);
	else if (window.webkitURL != undefined)
		url = window.webkitURL.createObjectURL(file);
	return url;
}
</script>
<style type="text/css">
#topMenu {
	width:100%; height:70px; background:black/* #0A6CB7 */;
	border: 1px solid #C3D9E0; 
	color: white;
}
#title {
	float: left;
	position: relative;
	margin: 15px 20px 10px 40px;
	font-size: 25px;
	font-family: "微软雅黑";
}
#version {
	float: left;
	position: relative;
	margin: 30px 15px 10px 15px;
	font-size: 14px;
}
#welcome {
	float: left;
	position: absolute;
	margin-left: 1000px;
	margin-top: 17px;
	font-size: 13px;
}
#menu {
	/* float: left; */
	/* position: relative; */
	/* margin-left: 50px; */
}
#menu ul {
	list-style-type: none;
	float: left;
	margin-left: 950px;
	margin-top: 37px;
	margin-bottom: 0px;
	position: absolute;
	
}
#menu ul li {
	float: left;
	/* position: relative; */
	margin-left: 0px;
	font-size: 13px;
	/* padding-right: 7px; */
	padding: 3px 5px 3px 5px;
	margin-bottom: 0px;
	/* border: 1px solid black; */
}
.colsLine {
	padding: 0px;
	margin: 0px;
}
#menu ul li a {
	color: white;
	text-decoration: none;
}
.menuButton:HOVER {
	background-color: #9CC8F7;
}
.btn {
	border: 1px solid;
	text-align:center;
	width: 140px;
	height: 25px;
	margin-left: 10px;
	margin-top: 10px;
}
</style>
</head>
<body class="easyui-layout">
<div id="topMenu" data-options="region:'north',border:false" style="">
	<div id="title">在线考试系统</div>
	<div id="version">v1.0</div>
	<div id="welcome">欢迎您：${user.username }</div>
	<div id="menu">
		<ul>
			<li class="menuButton"><a href="">首页</a></li>
			<li class="colsLine">|</li>
			<li class="menuButton"><a href="">修改密码</a></li>
			<li class="colsLine">|</li>
			<li class="menuButton"><a target="_top" href="${pageContext.request.contextPath }/admin/logout.action">退出登陆</a></li>
			
		</ul>
	</div>
</div>
<div data-options="region:'west',split:true,title:'管理菜单'" style="width:170px;padding:0px;">
	<div class="easyui-accordion" style="position: absolute;top:28px;left:0px;right:0px;height: 100%;">
		<c:forEach items="${moduleCustomList }" var="moduleCustom">
			<div title="${moduleCustom.name }">
				<c:forEach items="${moduleCustom.functions }" var="function">
				<c:if test="${not empty function.url}">
				<a href="javascript:void(0);" class="easyui-linkbutton c1 btn" onclick="addTab('${function.name}', '${pageContext.request.contextPath }/${function.url };')">${function.name}</a>
				</c:if>
				<br/>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div> 
<!--<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>-->
<!--<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>-->
<div data-options="region:'center'">
	<div id="tt" class="easyui-tabs" style="width:100%;height:100%;padding: 0px;">
		<div title="欢迎页">
			<iframe scrolling='auto' frameborder='0' src='${pageContext.request.contextPath }/welcome.html' style="width:100%; height:100%;"></iframe>
		</div>
	</div>
</div>
<div id="editUserDialog"></div>
<div id="previewStDialog"></div>
</body>
</html>