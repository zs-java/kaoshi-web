<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	loadStClassifyCombotree();
	loadSjDataGrid();
});
function loadSjDataGrid() {
	$('#sjDg').datagrid({
		  iconCls : 'icon-ok',
		  //width : fixWidth(1),
		  pageSize : 15,//默认选择的分页是每页5行数据
		  pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		  nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		  striped : true,//设置为true将交替显示行背景。
		  collapsible : true,//显示可折叠按钮
		  toolbar:"#tb",
		  sortable:true,
		  url:'${pageContext.request.contextPath }/admin/shijuan/querySjList.json',
		  //toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		  loadMsg : '数据装载中......',
		  singleSelect:false,//为true时只能选择单行
		  fitColumns:true,//允许表格自动缩放，以适应父容器
		  fit:true,
		  sortName : 'insDateString',//当数据表格初始化时以哪一列来排序
		  sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		  /* remoteSort : false, */
	      columns:[[
	        {field:'ck',checkbox:true},
			{field:'title',title:'试卷名称', width:200,sorter : datasort},
			{field:'classifyName',title:'试卷分类', width:100,sorter : datasort,
        	formatter:function(value, row, index) {
        		return row.sjClassifyCustom.name;
        	}
        },
      {field:'totalScore', title:'试卷总分', width:70,sorter : datasort},
      {field:'count', title:'试题总数',sorter : datasort, width:70},
   	  {field:'reviewStuts',title:'审核状态',sorter : datasort, width:100,
   		formatter:function(value, row, index) {
   			var stuts = row.review;
   			if(stuts == true)
	   			return '已审核';
   			else if(stuts == false) 
   				return '未审核';
   			else 
   				return 'error';
   		}
   	  },
        {field:'insUser',title:'创建人',sorter : datasort, width:120},
        {field:'insDateString', title:'创建时间',sorter:datasort, wdith:150},
        {field:'cz',title:'操作', width:200,sorter : datasort,
      	  formatter:function(value,row,index) {
      		  var btnHtml = "<a href='javascript:;' class='easyui-linkbutton' onClick='editSjData(" + row.sjId + ")'  plain='true' iconCls='icon-edit'>编辑</a>";
      		  btnHtml += "<a href='javascript:;' onClick='previewSj(" + row.sjId + ")' class='easyui-linkbutton' plain='true' iconCls='icon-search'>预览</a>";
      		  btnHtml += "<a href='javascript:;' onclick='downloadSj();' class='easyui-linkbutton' plain='true' iconCls='icon-download'>下载</a>";
      		  btnHtml += "<a href='javascript:;' onclick='beforeDeleteSj(" + row.sjId + ")' class='easyui-linkbutton' plain='true' iconCls='icon-no'>删除</a>";
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
function loadStClassifyCombotree() {
	//下拉列表添加“全部”选项
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shijuan/getAllSjClassify.json' ,
		//dataType: 'json', 
		success: function(data){
			var result = JSON.parse(data);
				// 修改ajax返回的值
				var datas = result;
				datas = [{'id':-1, 'text':'全部', 'state':'open', 'children':datas}];
				$('#sjClassifyCombotree').combotree({            
					data:datas,
					editable:false, //不可编辑
					onLoadSuccess:function(node,d) {
						$('#sjClassifyCombotree').combotree("setValue", d[0].id);
					}
				});
		}
	});
}
//排序
function datasort(a, b) {
	return (a > b ? 1 : -1);
}
//查询
function querySubmit() {
	var visable = 1;
	if(document.getElementById("query_visable")) {
		visable = $("#query_visable").val();
	}
	$('#sjDg').datagrid('load',{
		'sjDataCustom.title': $('#query_title').val(),
		'sjDataCustom.insUser': $('#query_insUser').val(),
		'sjDataCustom.sjClassifyId': $('#sjClassifyCombotree').combotree("getValue"),
		'visable' : visable
	});
}
//添加试卷
function addSj() {
	parent.addTab('新建试卷', '${pageContext.request.contextPath}/admin/shijuan/editSj.html');
	window.parent.closeTabByTitle("试卷管理");
}
//编辑试卷
function editSjData(sjId) {
	window.parent.closeTabByTitle("编辑试卷");
	parent.addTab('编辑试卷', '${pageContext.request.contextPath}/admin/shijuan/editSj.html?pageType=edit&sjId=' + sjId);
	window.parent.closeTabByTitle("试卷管理");
}
//预览试卷
function previewSj(sjId) {
	window.parent.closeTabByTitle("试卷预览");
	parent.addTab('试卷预览', '${pageContext.request.contextPath}/admin/shijuan/previewSj.html?sjId=' + sjId);
	window.parent.closeTabByTitle("试卷管理");
}
//删除试卷前执行
function beforeDeleteSj(sjId) {
	$.messager.confirm('提示', '确认要删除该试卷吗？', function(r){
		if (r)
			deleteSj(sjId);
	});
}
//删除试卷
function deleteSj(sjId) {
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/admin/shijuan/deleteSjById.action",
		data:{"sjId":sjId},
		dataType:"json",
		success:function(result) {
			if(result.msg == 'success') {
				msgShow("删除成功！");
				querySubmit();//重新加载dg
			} else if(result.msg == 'error') {
				msgShow("删除失败！" + result.info);
			}
		}
	});
}
//批量删除试卷
function deleteList() {
	var checkedItems = $('#sjDg').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.unshift(item.sjId);
	});
	if(ids.length == 0) {
		alert("请至少选中一行数据");
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shijuan/deleteSjList.action",
		dataType:"json",
		data:{'ids':ids},
		success:function(result) {
			if(result.msg == 'success') {
				querySubmit();
				setTimeout($.messager.show({
					title:"提示",
					msg:"批量删除试卷成功！",
					timeout:3000
				}), 0);
			} else if (result.msg == 'error') {
				setTimeout($.messager.show({
					title:"提示",
					msg:"批量删除试卷 失败！" + result.info,
					timeout:3000
				}), 0);
			}
		}
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
#sjDg {
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
	<table id="sjDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
	</table>
	<div id="tb" style="padding:3px">
		<span>试卷分类：</span>
		<select id="sjClassifyCombotree" class="easyui-combotree" style="width:120px;">
			<!-- <option>全部</option> -->
		</select>
		<c:if test="${user.readPrivateSt == 1 }">
		<span>可见性：</span>
		<select id="query_visable" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
			<option value="-1">全部</option>
			<option value="1">公开试卷</option>
			<option value="0">私有试卷</option>
		</select>
		</c:if>
		<span>试卷名称：</span>
		<input id="query_title" name="dept" style="line-height:22px;border:1px solid #ccc;width: 140px;" />
		<span>创建人：</span>
		<input id="query_insUser" name="dept" style="line-height:22px;border:1px solid #ccc;width: 140px;" />
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="location.reload();" plain="true" iconCls="icon-reload">刷新</a>
		<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" onclick="addSj();" plain="false" iconCls="icon-add">添加试卷</a>
	</div>
</div>
</body>
</html>