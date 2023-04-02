<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/chooseExam.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
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
#Dg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
}
#Dg tr td {
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
	<table id="Dg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
	</table>
	<!-- <div id="tb" style="padding:3px">
		<span>考试分类：</span>
		<select id="ksClassifyCombotree" class="easyui-combotree" style="width:120px;">
			<option>全部</option>
		</select>
		<span>考试名称：</span>
		<input id="query_name" style="line-height:22px;border:1px solid #ccc;width: 140px;" name="dept" />
		<span>用户：</span>
		<input id="query_user" name="dept" style="line-height:22px;border:1px solid #ccc;width: 140px;"/>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="location.reload();" plain="true" iconCls="icon-reload">刷新</a>
		<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" onclick="addExam();" plain="false" iconCls="icon-add">添加考试</a>
	</div> -->
</div>
<div id="detailDialog" class="easyui-dialog" title="详细信息" style="width:1100px;height:530px;padding:0px;" data-options="closed:true">
<div class="easyui-layout" data-options="fit:true">
<div region="center" style="background: #fafafa;border:0px;">
<table id="detailDg" style="width: 100%;height: 100%;"></table>
<div id="tb" style="padding:3px">
	<span>用户组：</span>
	<select id="userGroupCombotree" class="easyui-combotree" style="width:120px;">
		<!-- <option>全部</option> -->
	</select>
	<span>用户名：</span>
	<input id="query_username" style="line-height:22px;border:1px solid #ccc;width: 140px;" name="dept" />
	<span>状态：</span>
	<select id="query_state" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
		<option value="-1" selected="selected">全部</option>
		<option value="1">已完成</option>
		<option value="0">未完成</option>
	</select>
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportExcel();" plain="true" iconCls="icon-excel">导出Excel</a>
	<form name="exportFrm" action="${pageContext.request.contextPath }/admin/exam/exportExamResult.action" method="post">
	<input type="hidden" name="ksId" value="" />
	<input type="hidden" name="username" value="" />
	<input type="hidden" name="groupId" value="" />
	<input type="hidden" name="state" value="" />
	</form>
</div>
</div>
</div>
</div>

<div id="errorResultDialog" class="easyui-dialog" title="错题统计" style="width:1100px;height:530px;padding:0px;" data-options="closed:true">
	<div class="easyui-layout" data-options="fit:true">
		<div region="center" style="background: #fafafa;border:0px;">
			<table id="errorResultDg" style="width: 100%;height: 100%;"></table>
			<div id="errorResultTb" style="padding:3px">
				<span>用户组：</span>
				<input id="groupCbx" class="easyui-combobox" style="width:120px;" />
				<input type="hidden" id="errorReusltKsId" value="">
			</div>
		</div>
	</div>
</div>
</body>
</html>