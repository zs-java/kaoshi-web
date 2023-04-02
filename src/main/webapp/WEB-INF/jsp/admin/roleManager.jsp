<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
window.onload = function () {
	 loadRoleDataGrid();
};
function loadRoleDataGrid() {
	$('#roleDg').datagrid({
		  iconCls : 'icon-ok',
		  //width : fixWidth(1),
		  pageSize : 50,//默认选择的分页是每页5行数据
		  pageList : [ 20, 50, 100],//可以选择的分页集合
		  nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
		  striped : true,//设置为true将交替显示行背景。
		  collapsible : true,//显示可折叠按钮
		  toolbar:"#tb",
		  url:'${pageContext.request.contextPath }/admin/role/queryRoleList.json',
		  //toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		  loadMsg : '数据装载中......',
		  singleSelect:false,//为true时只能选择单行
		  fitColumns:true,//允许表格自动缩放，以适应父容器
		  fit:true,
		  sortName : 'sort',//当数据表格初始化时以哪一列来排序
		  sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		  /* remoteSort : false, */
	      columns:[[
	        //{field:'ck',checkbox:true},
			{field:'roleId',title:'角色编号 ',hidden:'true'},
			{field:'roleName',title:'角色名称', width:140},
			{field:'des',title:'角色描述', width:125},
            {field:'functions',title:'拥有权限', width:250,
          	formatter:function(value, row, index) {
          		var functions = row.functions;
          		var str = "";
          		for(var i=0;i<functions.length; i++) {
          			str += functions[i].name;
          			if(i != functions.length - 1) 
          				str += ",";
          		}
          		return str;
          	}
     		},
     		{field:'sss',title:'排序', width:250},
            {field:'ccc',title:'操作', width:250,
        	  formatter:function(value,row,index) {
        		  //判断该角色如果是学生或超级管理员则不显示操作按钮
        		  if(row.roleId == 1 || row.roleId == 2)
        			  return null;
        		  var btnHtml = "<a href='#' class='easyui-linkbutton' onclick='editRole("+row.roleId+")' plain='true' iconCls='icon-edit'>编辑</a>";
        		  btnHtml += "<a href='javascript:void(0);' onclick='deleteRole(" + row.roleId + ")' class='easyui-linkbutton' plain='true' iconCls='icon-no'>删除</a>";
        		  return btnHtml;
        	}
          },
	      ]],
	      /* frozenColumns:[[{
	    	  field:'ck',
	    	  checkbox:true
	      }]], */
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){  
		        $('.easyui-linkbutton').linkbutton();
		  },
		  onClickCell: function (rowIndex, field, value) {
				//IsCheckFlag = false;
		  },
	 });
}
function fixWidth(percent) {
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
}
function querySubmit() {
	$('#roleDg').datagrid('load',{
		'roleCustom.roleName':$('#query_username').val()
	});
}
function windowReload() {
	location.reload();
}
function addRole() {
	parent.addTab('新建角色', '${pageContext.request.contextPath}/admin/role/addRole.html');
}
function editRole(roleId) {
	parent.addTab('编辑角色', '${pageContext.request.contextPath}/admin/role/editRole.html?roleId=' + roleId);
}
function deleteRole(roleId) {
	$.ajax({
		type:"GET",
		url:'${pageContext.request.contextPath}/admin/role/deleteRole.action',
		data:{'roleId':roleId},
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				setTimeout(function() {
					$.messager.show({
						title:"提示",
						msg:"删除成功！",
						timeout:3000
					});
				}, 0);
				loadRoleDataGrid();
			} else if (result.msg == 'error') {
				setTimeout(function() {
					$.messager.show({
						title:"提示",
						msg:"删除失败！"  + result.info,
						timeout:3000
					});
				}, 0);
			}
		}
	});
}
</script>
<style type="text/css">
#managerDiv {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	right: 0px;
}
#roleDg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
	height: auto;
}
#roleDg tr td {
	font-size: 20px;
}
.easyui-datagrid tr{
	height: 30px;
}
#addUserBtn {
	/* float: left; */
	position: absolute;
	right: 15px;
}
#tb {
	height: 40px;
	padding-top: 10px;
	padding-left: 10px;
	font-size: 13px;
}
.datagrid-btable tr{
	height: 60px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="managerDiv">
		<table id="roleDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
		</table>
		<div id="tb">
			<span>角色名称：</span>
			<input id="query_username" class="easyui-textbox" style="line-height:22px;border:1px solid #ccc;width: 120px;">
			<a href="#" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
			<a href="#" class="easyui-linkbutton" onclick="windowReload();" plain="true" iconCls="icon-reload">刷新</a>
			<a href="#" id="addUserBtn" class="easyui-linkbutton" onclick="addRole();" plain="false" iconCls="icon-add">添加角色</a>
		</div>
	</div>
</body>
</html>