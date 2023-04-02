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
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
window.onload = function () {
	 loadStDataGrid();
	 loadStTypeCombobox();
	 loadStLevelComboTree();
	 loadStClassifyComboTree();
	 //loadStKnowledgeComboTree();
};
function sort(obj,sortName,sortOrder){
    var queryParams = $('#'+obj).datagrid('options').queryParams;
    queryParams.sortName = sortName;
    queryParams.sortOrder = sortOrder;
    $('#'+obj).datagrid('reload');
}
function loadStDataGrid() {
	$('#stDg').datagrid({
		  iconCls : 'icon-ok',
		  //width : fixWidth(1),
		  pageSize : 15,//默认选择的分页是每页5行数据
		  pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		  nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		  striped : true,//设置为true将交替显示行背景。
		  collapsible : true,//显示可折叠按钮
		  toolbar:"#tb",
		  sortable:true,
		  url:'${pageContext.request.contextPath }/admin/shiti/queryStMainList.json',
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
			{field:'questionId',title:'试题编号 '},
			{field:'title',title:'题目', width:220},
			{field:'classifyName',title:'试题分类', width:100,
          	formatter:function(value, row, index) {
          		return row.stClassifyCustom.name;
          	}
          },
          {field:'typeName',title:'试题类别', width:100,
          	formatter:function(value, row, index) {
          		//alert($.isEmptyObject(row.grouopCustom));
          		return row.stTypeCustom.name;
            }
     	  },
     	  {field:'levelName',title:'试题难度', width:100,
     		formatter:function(value, row, index) {
     			return row.stLevelCustom.name;
     		}
     	  },
          {field:'knowledgeName',title:'试题知识点', width:70, 
     		formatter:function(value, row, index) {
     			return row.stKnowledgeCustom.name;
          	}
          },
          {field:'insDate',title:'创建时间', width:140,
        	formatter:function(value, row, index) {
        		return row.insDateString;
        	}
          },
          {field:'insUser',title:'创建人', width:60},
          {field:'cz',title:'操作', width:140,
        	  formatter:function(value,row,index) {
        		  var url = "${pageContext.request.contextPath}/admin/shiti/editSt.html?type=edit&questionId=" + row.questionId + "&typeName=" + row.stTypeCustom.name + "";
        		  var btnHtml = "<a href='javascript:;' onclick='previewSt("+row.questionId+");' class='easyui-linkbutton'  plain='true' iconCls='icon-search'>预览</a>";
          		  btnHtml += "<a href='javascript:;' onclick='editSt(" + row.questionId + ", \"" + row.stTypeCustom.name + "\")' class='easyui-linkbutton'  plain='true' iconCls='icon-edit'>编辑</a>";
        		  btnHtml += "<a href='javascript:;' onclick='deleteSt(" + row.questionId +")' class='easyui-linkbutton' plain='true' iconCls='icon-no'>删除</a>";
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
function loadStTypeCombobox() {
	//下拉列表添加“全部”选项
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shiti/getAllStType.json' ,
		//dataType: 'json', 
		success: function(data){
			var result = JSON.parse(data);
			// 修改ajax返回的值
			var types = result;
			types.unshift({'typeId':'-1','name':'全部', 'des':'', 'sort':-1});   //unshift方法添加到第一行，push方法添加到末尾
			$('#stTypeCombobox').combobox({            
				data:types,
				valueField:'typeId',
				textField:'name',
				editable:false, //不可编辑
				onLoadSuccess:function(node,data) {
					$('#stTypeCombobox').combobox("select", types[0].typeId);
				}
			});
		}
	});
}
function loadStClassifyComboTree() {
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shiti/getAllStClassifyTree.json' ,
		//dataType: 'json', 
		success: function(data){
			var result = JSON.parse(data);
			// 修改ajax返回的值
			var datas = result;
			//types.unshift({'typeId':'-1','name':'全部', 'des':'', 'sort':-1});   //unshift方法添加到第一行，push方法添加到末尾
			datas = [{'id':-1, 'text':'全部', 'state':'open', 'children':datas}];
			$("#stClassifyCombotree").combotree({
				data:datas,
				valueField:'id',
			    textField:'text',
			    editable: false,
    			onLoadSuccess:function(node,d) {
		             $("#stClassifyCombotree").combotree('setValue',d[0].id);
		    	}
			});
		}
	});
}
function loadStLevelComboTree() {
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shiti/getAllStLevelTree.json' ,
		//dataType: 'json', 
		success: function(data){
			var result = JSON.parse(data);
			// 修改ajax返回的值
			var datas = result;
			//types.unshift({'typeId':'-1','name':'全部', 'des':'', 'sort':-1});   //unshift方法添加到第一行，push方法添加到末尾
			datas = [{'id':-1, 'text':'全部', 'state':'open', 'children':datas}];
			$("#stLevelCombotree").combotree({
				data:datas,
				valueField:'levelId',
			    textField:'name',
			    editable: false,
    			onLoadSuccess:function(node,d) {
		             $("#stLevelCombotree").combotree('setValue',d[0].id);
		    	}
			});
		}
	});
}
function loadStKnowledgeComboTree() {
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shiti/getAllStKnowledgeTree.json' ,
		success: function(data){
			var result = JSON.parse(data);
			// 修改ajax返回的值
			var datas = result;
			datas = [{'id':-1, 'text':'全部', 'state':'open', 'children':datas}];
			$("#stKnowledgeCombotree").combotree({
				data:datas,
			    editable: false,
    			onLoadSuccess:function(node,d) {
		             $("#stKnowledgeCombotree").combotree('setValue',d[0].id);
		    	}
			});
		}
	});
}
function fixWidth(percent) {
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
}
function querySubmit() {
	var visable = 1;
	if(document.getElementById("query_stVisable")) {
		visable = $("#query_stVisable").val();
	}
	$('#stDg').datagrid('load',{
		'stMainCustom.title': $('#query_title').val(),
		'stClassifyCustom.classifyId': $('#stClassifyCombotree').combotree("getValue"),
		'stTypeCustom.typeId': $('#stTypeCombobox').combobox("getValue"),
		'stLevelCustom.levelId':$("#stLevelCombotree").combotree("getValue"),
		//'stKnowledgeCustom.classifyId':$('#stKnowledgeCombotree').combobox("getValue"),
		'visable' : visable
	});
}
function windowReload() {
	location.reload();
}
function deleteList() {
	var checkedItems = $('#stDg').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.push(item.questionId);
	});
	if(ids.length == 0) {
		alert("请至少选中一行数据");
		return;
	}
	$.ajax({
		url:'${pageContext.request.contextPath }/admin/user/deleteUserList.action',
		dataType:'text',
		data:{ 'ids': ids },
		type:'post',
		success: function(data,textStatus) { //此处data为返回值
			if(data == "success")
				location.reload();
	    }
	});
}
function addSt() {
	parent.addTab('新建试题', '${pageContext.request.contextPath}/admin/shiti/editSt.html');
}
function editSt(id, name) {
	parent.addTab('编辑试题', "${pageContext.request.contextPath}/admin/shiti/editSt.html?type=edit&questionId=" + id + "&typeName=" + name);
}
function previewSt(id) {
	parent.previewSt(id);
}
function deleteSt(questionId) {
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shiti/deleteSt.action",
		dataType:"json",
		data:{'questionId':questionId},
		success:function(result) {
			if(result.msg == 'success') {
				querySubmit();
				setTimeout($.messager.show({
					title:"提示",
					msg:"删除试题成功！",
					timeout:3000
				}), 0);
			} else if (result.msg == 'error') {
				setTimeout($.messager.show({
					title:"提示",
					msg:"删除试题失败！" + result.info,
					timeout:3000
				}), 0);
			}
		}
	});
}
function deleteList() {
	var checkedItems = $('#stDg').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.unshift(item.questionId);
	});
	if(ids.length == 0) {
		alert("请至少选中一行数据");
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shiti/deleteStList.action",
		dataType:"json",
		data:{'ids':ids},
		success:function(result) {
			if(result.msg == 'success') {
				querySubmit();
				setTimeout($.messager.show({
					title:"提示",
					msg:"批量删除试题成功！",
					timeout:3000
				}), 0);
			} else if (result.msg == 'error') {
				setTimeout($.messager.show({
					title:"提示",
					msg:"批量删除试题失败！" + result.info,
					timeout:3000
				}), 0);
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
#stDg {
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
		<table id="stDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
		</table>
		<div id="tb" style="padding:3px">
			<span>题目：</span>
			<input id="query_title" style="line-height:22px;border:1px solid #ccc;width: 120px;">
			<span>试题分类：</span>
			<select id="stClassifyCombotree" class="easyui-combotree" style="width:180px;">
				<!-- <option>全部</option> -->
			</select>
			<span>题型：</span>
			<input id="stTypeCombobox" class="easyui-combobox" name="dept" style="width: 100px;" />
			<span>难度：</span>
			<select id="stLevelCombotree" class="easyui-combotree" style="width:120px;">
				<!-- <option>全部</option> -->
			</select>
			<c:if test="${user.readPrivateSt == 1 }">
			<span>可见性：</span>
			<select id="query_stVisable" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
				<option value="-1">全部</option>
				<option value="1">公开题目</option>
				<option value="0">私有题目</option>
			</select>
			</c:if>
			<!-- <span>知识点：</span>
			<select id="stKnowledgeCombotree" class="easyui-combotree" style="width:120px;">
				<option>全部</option>
			</select> -->
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="windowReload();" plain="true" iconCls="icon-reload">刷新</a>
			<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" onclick="addSt();" plain="false" iconCls="icon-add">添加试题</a>
		</div>
	</div>
</body>
</html>