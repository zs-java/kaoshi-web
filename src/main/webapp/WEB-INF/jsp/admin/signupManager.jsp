<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试用户报名管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/signupManager.js"></script> --%>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
$(function () {
	//加载考试分类下拉选框
	$.ajax({ 
		url: baseUrl + '/admin/exam/getAllKsClassifyTree.json' ,
		type : "post",
		dataType: 'json', 
		success: function(data){
			// 修改ajax返回的值
			data = [{'id':-1, 'text':'全部', 'state':'open', 'children':data}];
			$('#ksClassifyCombotree').combotree({            
				data:data,
				editable:false, //不可编辑
				onLoadSuccess:function(node,d) {
					$('#ksClassifyCombotree').combotree("setValue", d[0].id);
				}
			});
		}
	});
	//加载dataGrid
	loadDataGrid();
});
//加载dataGrid
function loadDataGrid() {
	$('#kuDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#tb",
		sortable:true,
		url: baseUrl + '/admin/exam/querySignupingKsUser.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:false,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
		sortName : 'insDateString',//当数据表格初始化时以哪一列来排序
		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		/* remoteSort : false, */
		columns:[[ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'name',
				title : '考试名称', 
				width : 140,
				formatter : function(value, row, index) {
					return row.ksDataCustom.name;
				}
			}, {
				field : 'classifyName',
				title : '考试分类', 
				width : 80,
				formatter : function(value, row, index) {
					return row.ksDataCustom.ksClassifyCustom.name;
				}
			}, {
				field : 'beginTime', 
				title : '开始时间', 
				width : 140,
				formatter : function(value, row, index) {
					return row.ksDataCustom.beginTimeString;
				}
			},{
				field : 'endTime', 
				title : '结束时间', 
				width : 140,
				formatter : function(value, row, index) {
					return row.ksDataCustom.endTimeString;
				}
			},{
				field : 'totalScore', 
				title : '考试总分', 
				width : 70,
				formatter : function(value, row, index) {
					return row.ksDataCustom.sjDataCustom.totalScore;
				}
			}, {
				field:'groupName', 
				title:'用户组', 
				width:80,
				formatter : function(value, row, index) {
					return row.userCustom.groupCustom.groupName;
				}
			}, {
				field : 'readlyName',
				title : '真实姓名', 
				width : 80,
				formatter : function(value, row, index) {
					return row.userCustom.readly;
				}
			}, {
				field : 'username',
				title : '用户登录名', 
				width : 100,
				formatter : function(value, row, index) {
					return row.userCustom.username;
				}
			}, {
				field : 'signupTimeString',
				title : '报名时间', 
				width : 130
			}, {
				field : 'sh', 
				title:'状态设置', 
				wdith:180,
				formatter : function(value, row, index) {
					return "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='signuping(" + row.id + ");'>通过</a>";
				}
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){  
		        $('.easyui-linkbutton').linkbutton();  
		  },
		  onClickCell: function (rowIndex, field, value) {
				//IsCheckFlag = false;
		  }
	 });
}
//报名通过
function signuping(id) {
	$.ajax({
		url : baseUrl + "/admin/exam/signupKsUserById.action",
		data : {"id" : id},
		dataType : "json",
		type : "post",
		success : function(result) {
			if(result.msg == 'success') {
				msgShow("审核成功！");
				querySubmit();
			} else if (result.msg == 'error') {
				msgShow("审核失败！" + result.info);
			}
		}
	});
}
//条件查询
function querySubmit() {
	$('#kuDg').datagrid('load',{
		'ksDataCustom.name': $('#query_name').val(),
		'userCustom.username': $('#query_username').val(),
		'ksDataCustom.ksClassifyId': $('#ksClassifyCombotree').combotree("getValue"),
	});
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
#kuDg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
}
#kuDg tr td {
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
	<table id="kuDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
	</table>
	<div id="tb" style="padding:3px">
		<span>考试分类：</span>
		<select id="ksClassifyCombotree" class="easyui-combotree" style="width:120px;">
			<!-- <option>全部</option> -->
		</select>
		<span>考试名称：</span>
		<input id="query_name" style="line-height:22px;border:1px solid #ccc;width: 140px;" name="dept" />
		<span>用户名：</span>
		<input id="query_username" name="dept" style="line-height:22px;border:1px solid #ccc;width: 140px;"/>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="location.reload();" plain="true" iconCls="icon-reload">刷新</a>
	</div>
</div>
</body>
</html>