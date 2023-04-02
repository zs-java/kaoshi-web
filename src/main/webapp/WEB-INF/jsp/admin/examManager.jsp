<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/examManager.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath }";
</script>
<style type="text/css">
.icon-download{
	background:url('${pageContext.request.contextPath}/resources/js/easyui/themes/icons/arrow_down.png') no-repeat 3px 0px;
}
#managerDiv {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	right: 0px;
}
#ksDg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
}
#stDg tr td {
	font-size: 20px;
}
.easyui-datagrid tr{
	height: 30px;
}
#addBtn {
	/* float: left; */
	position: absolute;
	right: 15px;
}
.datagrid-btable tr{
	height: 30px;
	text-align: center;
}
.datagrid-row-selected {
  background: #E0ECFF;
  color: black;
}
</style>
</head>
<body>
<div id="managerDiv">
	<table id="ksDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
	</table>
	<div id="tb" style="padding:3px">
		<span>考试分类：</span>
		<select id="ksClassifyCombotree" class="easyui-combotree" style="width:120px;">
			<!-- <option>全部</option> -->
		</select>
		<span>考试名称：</span>
		<input id="query_name" style="line-height:22px;border:1px solid #ccc;width: 140px;" name="dept" />
		<span>用户：</span>
		<input id="query_user" name="dept" style="line-height:22px;border:1px solid #ccc;width: 140px;"/>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="location.reload();" plain="true" iconCls="icon-reload">刷新</a>
		<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" onclick="addExam();" plain="false" iconCls="icon-add">添加考试</a>
	</div>
</div>
<div id="usersDia" class="easyui-dialog" title="添加用户" style="width:800px;height:450px;padding:0px;" data-options="closed:true">
<div class="easyui-layout" data-options="fit:true">
	<div region="center" style="background: #fafafa;border:0px;">
		<div id="ubar" style="height:34px;padding-top:10px;font-size:12px;">
			<input type="hidden" id="ingroupids" value=""/>
			用户组：<input id="userGroupComboxTree" name="groupid" style="width:120px;height:28px;" /> 
			用户名:<input class="easyui-validatebox input_user_info" style="width:130px;height:25px;" type="text" id="userLike"/>
			<a id="seach" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="seachUserByParam();">查询</a>
		</div>
		<table id="userDg" style="max-height: 300px"></table>
	</div>
	<div  region="south" style="background: #fafafa;padding:10px;font-size: 14px;text-align: center;border:0px;">
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="addUserToExam();" style="width:130px;height:32px;"> 保存已选用户</a> 
	</div>
</div>
</div>
</body>
</html>