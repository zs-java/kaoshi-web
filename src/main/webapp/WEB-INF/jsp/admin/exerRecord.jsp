<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刷题统计-在线考试系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/exerRecord.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
function enableDate() {
	if($("#dateCk").prop("checked")) {
		document.getElementById("dateBox").disabled = "";
		$("#queryNot").show();
	} else {
		$("#dateBox").val("");
		document.getElementById("dateBox").disabled = "disabled";
		$("#queryNot").hide();
	}
}
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
#exerDg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
}
#exerDg tr td {
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
	<table id="exerDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
	</table>
	<div id="tb" style="padding:3px">
		<span>用户组：</span>
		<select id="userGroupCombotree" class="easyui-combotree" style="width:130px;">
		</select>
		<span>用户名：</span>
		<input id="query_username" style="line-height:22px;border:1px solid #ccc;width: 140px;"/>
		&nbsp;&nbsp;
		<input id="dateCk" type="checkbox" onclick="enableDate();" />
		<span>日期：</span>
		<input id="dateBox" type="date" disabled="disabled" />
		<!-- <select id="ksClassifyCombotree" class="easyui-combotree" style="width:120px;">
			<option>全部</option>
		</select> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
		<!-- <a class="easyui-linkbutton" onclick="showAll();" plain="true" iconCls="icon-search">查看选中用户的所有刷题记录</a> -->
		<a id="queryNot" style="display: none;" class="easyui-linkbutton" onclick="queryNotExer();" plain="true" iconCls="icon-search">查看未刷题的学生</a>
	</div>
</div>
<div id="exerDialog" class="easyui-dialog" title="详细信息" style="width:1000px;height:530px;padding:0px;" data-options="closed:true">
<div class="easyui-layout" data-options="fit:true">
<div region="center" style="background: #fafafa;border:0px;">
<table id="detailDg" style="width: 100%;height: 100%;"></table>
</div>
</div>
</div>
</body>
</html>