<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑试卷 </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//loadSjClassifyCombotree();
	//creatGdGrid();
});
var pageType = "${pageType}";
var sjId = "${sjId}";
$(function() {
	//加载主页面上的试卷类型下拉选框
	$('#sjClassifyCombotree').combotree({
		multiple : false,
		checkbox : false,
		lines : true,
		animate : true,
		editable : false,
		url : '${pageContext.request.contextPath }/admin/shijuan/getAllSjClassify.json',
		required : true
	});
	//加载固定选题对话框的试题分类下拉选框
	$('#searchclassify').combotree({
		multiple : false,
		checkbox : false,
		lines : true,
		animate : true,
		editable : false,
		url : '${pageContext.request.contextPath }/admin/shiti/getAllStClassifyTree.json',
		required : false
	});
	//加载固定选题对话框的试题等级下拉选框
	$('#searchlevel').combotree({
		multiple : false,
		checkbox : false,
		lines : true,
		animate : true,
		editable : false,
		url : '${pageContext.request.contextPath }/admin/shiti/getAllStLevelTree.json',
		required : false
	});
	//加载固定选题对话框的试题知识点下拉选框
	/* $('#searchknowledge').combotree({
		multiple : false,
		checkbox : false,
		lines : true,
		animate : true,
		editable : false,
		url : '${pageContext.request.contextPath }/admin/shiti/getAllStKnowledgeTree.json',
		required : false
	}); */
	//题型下拉选框
	$('#searchshititype').combobox({
		url : '${pageContext.request.contextPath }/admin/shiti/getAllStType.json',
		editable : false,
		required : false,
		valueField : 'typeId',
		textField : 'name'
	});
	//固定试题选择对选框
	$('#gdQsn').dialog({
		fit:false,
		title : '固定试题选择',
		width:950,
		height:500,
		closed : true,
		modal : true,
		draggable:true,
		shadow : false,
		onClose:sumfen,
		onBeforeOpen:function(){
				/* $('#itemlist').datagrid("clearSelections");
				$('#itemlist').datagrid("clearChecked"); */
		}
	});
	$('#yxQsn').dialog({
		fit:false,
		title : '已选试题',
		width:950,
		height:500,
		closed : true,
		modal : true,
		draggable:true,
		shadow : false,
		onClose:sumfen,
		onBeforeOpen:function(){
				/* $('#itemlist').datagrid("clearSelections");
				$('#itemlist').datagrid("clearChecked"); */
		}
	});
	$('#sjForm').form({
		url : '${pageContext.request.contextPath}/admin/shijuan/saveShijuan.action',
		onSubmit : function() {
			var zf=$("#sjZF").val();
//			var intTpye=/^[1-9]+[0-9]*]*$/;
//			if(!intTpye.test(zf)){
//				msgShow("<span style='color:red'>试卷总分必须为正整数！</span>");
//				return false;
//			}
			if(zf<=0 || zf ==''){
				msgShow("<span style='color:red'>试卷总分不能为空或0！</span>");
				return false;
			}
			return $('#sjForm').form("validate");
		},
		success : function(data) {
			var result = JSON.parse(data);
			if (result.msg == 'success') {
				if(pageType == "add") {
					//msgShow("添加成功！");
					window.parent.closeTabByTitle("试卷预览");
					window.parent.addTab("试卷预览", "${pageContext.request.contextPath}/admin/shijuan/previewSj.html?sjId="+result.info);
					window.parent.closeTabByTitle("添加试卷");
				} else if(pageType == "edit") {
					//msgShow("修改成功！");
					window.parent.closeTabByTitle("试卷预览");
					window.parent.addTab("试卷预览", "${pageContext.request.contextPath}/admin/shijuan/previewSj.html?sjId="+result.info);
					window.parent.closeTabByTitle("编辑试卷");
				}
			} else {
				msgShow("<span style='color:red'>未知错误！请稍后重试！</span>");
			}
		}
	});
	//加载固定试题列表
	creatGrid();
	
	createYxGrid();
	//如果是编辑试卷，则加载试卷信息
	if(pageType == "edit"){
		getSjInfo();
	}
});
function loadSjClassifyCombotree() {
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/shijuan/getAllSjClassify.json' ,
		success: function(data){
			var result = JSON.parse(data);
			// 修改ajax返回的值
			var datas = result;
			$('#sjClassifyCombotree').combotree({            
				data:datas,
				editable:false //不可编辑
			});
		}
	});
}
//记录选择行
var oldIndex=0;
//行号
var tdindex=1;
function addTableRow() {
	var tdHtml="<tr height='35px'>" +
			"<td align='left'><input id='ms_"+tdindex+"' class='input_user_info' indexid='"+tdindex+"' style='width:100%;height:25px;' type='text'/></td>" +
			"<td align='center'><a id='gdxz_"+tdindex+"' indexid='"+tdindex+"' onclick='showGdDia(this);'>固定选题</a>|<a id='yx_"+tdindex+"' indexid='"+tdindex+"' onclick='yxQsn(this);'>已选</a></td>" +
			"<td align='center'><a href='javascript:;' indexid='"+tdindex+"' class='blue_color_a' id='gdnum_"+tdindex+"' onclick='showGdGridDia(this)'>0</a>" +
			"<input type='hidden' id='gdQsnids_"+tdindex+"'></td>" +
			"<td align='center'><a id='sjxz_"+tdindex+"' indexid='"+tdindex+"' onclick='showSjDia(this);'>随机抽题</a></td>" +
			"<td align='center'><a href='javascript:;' indexid='"+tdindex+"' class='blue_color_a' id='sjnum_"+tdindex+"' onclick='showSjGridDia(this)'>0</a>" +
			"<input type='hidden' id='sjQsnCs_"+tdindex+"'></td>" +
			"<td align='center'><input type='text' id='fen_"+tdindex+"' onblur='qsnFenOnkeyup(this.id)' value='0' style='width:60px;height:25px;border: 1px solid #c3d9e0;text-align:center;'/></td>" +
			"<td align='center'><a id='manage_"+tdindex+"' indexid='"+tdindex+"' onclick='delSjMessage(this);'>删除</a></td>" +
			"</tr>";
	$('#sjInfoTable').append(tdHtml);
	$('#gdxz_'+tdindex).linkbutton({
		iconCls : 'icon-add',
		plain:true
	});
	$('#sjxz_'+tdindex).linkbutton({
		iconCls : 'icon-add',
		plain:true
	});
	$('#manage_'+tdindex).linkbutton({
		iconCls : 'icon-no',
		plain:true
	}); 
	tdindex++;
}
//删除段落
function delSjMessage(obj){
	var indexid=$(obj).attr("indexid");
	var delIds=$("#gdQsnids_"+indexid).val();
	var oldAllQsnIds=$("#gdQsnList").val();
	var newAllQsnIds=oldAllQsnIds.replace(delIds,"");
	$("#gdQsnList").val(newAllQsnIds);
	$(obj).parent().parent().remove();
	sumfen();
}
//显示选择固定试题列表
function showGdDia(obj){
	$('#gdQsn').dialog("open");
	oldIndex=$(obj).attr("indexid");
	seachQsnByParam();
	$('#itemlist').datagrid('clearSelections');
}
//显示随机抽题
function showSjDia(obj) {
	alert("随机抽题功能正在努力开发中~");
}
/**
 * easyUi dataGrid注册方式说明，防止二次渲染 class注册方式一般是为了初始化属性，js方式则属性和事件都可初始化
 * 但是不管是class方式还是js方式注册组件，每次注册，只要被设置过url属性就会做请求。
 * 所以在不可避免要使用js方式注册的情况下，索性就不要使用class方式注册了。
 */
//选择固定试题列表
function creatGrid() {
	$('#itemlist').datagrid(
		{
			fit : true,// 设置为true时铺满它所在的容器.
			fitColumns : true,// 设置为true将自动使列适应表格宽度以防止出现水平滚动
			nowrap : false,// 设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,// 设置为true将交替显示行背景。
			idField : 'questionId',
			collapsible : true,// 定义是否显示可折叠按钮。
			singleSelect : false,// 设置为true将只允许选择一行。
			border : false,
			remoteSort : false,// 定义是否通过远程服务器对数据排序。
			pagination : true,// 分页组件是否显示
			pageNumber : 1,// 起始页
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 50 ],// 每页显示多少行
			rownumbers : true,// 行号
			url : '${pageContext.request.contextPath }/admin/shijuan/queryStList.json',
//			checkOnSelect:false,
			frozenColumns : [ [ {
				field : 'select',
				title : '选择',
				width : 50,
				checkbox : true
			} ] ],
			columns : [ [{
				field : 'title',
				title : '题目',
				width : 200,
				fixed : true,
				align : 'left',
				sortable : true,
				sorter : datasort,
				formatter:titleLength
			}, {
				field : 'classifyname',
				title : '试题分类',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stClassifyCustom.name;
				}
			}, {
				field : 'shititypename',
				title : '试题类型',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stTypeCustom.name;
				}
			}, {
				field : 'levelname',
				title : '试题难度',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stLevelCustom.name;
				}
			}, {
				field : 'knowledgename',
				title : '试题知识点',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stKnowledgeCustom.name;
				}
			}, {
				field : 'insUser',
				title : '创建人',
				width : 80,
				align : 'left',
				sortable : true
			} ] ]
		});
}
function createYxGrid() {
	$('#yxItemlist').datagrid(
		{
			fit : true,// 设置为true时铺满它所在的容器.
			fitColumns : true,// 设置为true将自动使列适应表格宽度以防止出现水平滚动
			nowrap : false,// 设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,// 设置为true将交替显示行背景。
			idField : 'questionId',
			collapsible : true,// 定义是否显示可折叠按钮。
			singleSelect : false,// 设置为true将只允许选择一行。
			border : false,
			remoteSort : false,// 定义是否通过远程服务器对数据排序。
			pagination : false,// 分页组件是否显示
			rownumbers : true,// 行号
			url : '${pageContext.request.contextPath }/admin/shijuan/queryYxStList.json',
//			checkOnSelect:false,
			frozenColumns : [ [ {
				field : 'select',
				title : '选择',
				width : 50,
				checkbox : true
			} ] ],
			columns : [ [{
				field : 'title',
				title : '题目',
				width : 200,
				fixed : true,
				align : 'left',
				sortable : true,
				sorter : datasort,
				formatter:titleLength
			}, {
				field : 'classifyname',
				title : '试题分类',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stClassifyCustom.name;
				}
			}, {
				field : 'shititypename',
				title : '试题类型',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stTypeCustom.name;
				}
			}, {
				field : 'levelname',
				title : '试题难度',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stLevelCustom.name;
				}
			}, {
				field : 'knowledgename',
				title : '试题知识点',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(value, row, index) {
					return row.stKnowledgeCustom.name;
				}
			}, {
				field : 'insUser',
				title : '创建人',
				width : 80,
				align : 'left',
				sortable : true
			} ] ]
		});
}
//当试题分数输入时触发，如果输入非数字则赋值为0
function qsnFenOnkeyup(obj){
	var fenvalue=$("#"+obj).val();
	var re = /^[0-9]\d*([.][1-9])?$/;
	if(!re.test(fenvalue)){
		$.messager.alert('提示','每题分数必须为正整数或一位小数，如：0.5！');    
		$("#"+obj).val(0);
	}
	if(parseFloat(fenvalue) > 100){
		$.messager.alert('提示','每题分数不可大于100');    
		$("#"+obj).val(0);
	}
	sumfen();
}
//计算总分
function sumfen(){
	var items=$("#sjInfoTable").find("input.input_user_info");
	var sjfenSum=0;
	var qsnCountSum=0;
	for(var i=0;i<items.length;i++){
		var indexid=$(items[i]).attr("indexid");
		var gdnum=$("#gdnum_"+indexid).html();
		var sjnum=$("#sjnum_"+indexid).html();
		var fen=$('#fen_'+indexid).val();
		var qsnfenSum=(parseInt(gdnum)+parseInt(sjnum))*fen;
		sjfenSum=sjfenSum+qsnfenSum;
		qsnCountSum=qsnCountSum+(parseInt(gdnum)+parseInt(sjnum));
	}
	$('#sjZF').val(sjfenSum);
	$('#qsnCountSum').val(qsnCountSum);
}
//查询固定试题信息
function seachQsnByParam() {
	var visable = 1;
	if(document.getElementById("query_stVisable")) {
		visable = $("#query_stVisable").val();
	}
	$('#itemlist').datagrid('load', {
		'stMainCustom.title': $('#searchtitle').val(),
		'stClassifyCustom.classifyId': $('#searchclassify').combotree("getValue"),
		'stTypeCustom.typeId': $('#searchshititype').combobox("getValue"),
		'stLevelCustom.levelId':$("#searchlevel").combotree("getValue"),
		//'stKnowledgeCustom.classifyId':$('#searchknowledge').combotree("getValue"),
		'ids' : $("#gdQsnList").val(),
		'visable' : visable
	});
}
//保存已选择的固定试题
function showSelectSum(){
	var items=$('#itemlist').datagrid("getSelections");
	for(var i=0;i<items.length;i++){
		var oldIds=$("#gdQsnids_"+oldIndex).val();
		var oldAllIds=$("#gdQsnList").val();
		$("#gdQsnids_"+oldIndex).val(oldIds+","+items[i].questionId);
		$("#gdQsnList").val(oldAllIds+","+items[i].questionId);
	}
	var oldQsnIds=$("#gdQsnids_"+oldIndex).val();
	var qsnCount=oldQsnIds.split(",").length-1;
	$("#gdnum_"+oldIndex).html(qsnCount);
	$('#gdQsn').dialog("close");
}
//排序
function datasort(a, b) {
	return (a > b ? 1 : -1);
}
function titleLength(value, rowData, rowIndex){
	var strArray=value.split("<img");
	var res=value;
	if(strArray.length > 0){
		for(var i=0;i<strArray.length;i++){
			var str=strArray[i];
			if(str.indexOf("src")>0){
				str=str.substr(0,str.indexOf("/>")+2);
				var imgStr="<img"+str;
				res=res.replace(imgStr,"");
			}
		}
	}
	var vArray=res.split("<video");
	if(vArray.length > 0){
		for(var i=0;i<vArray.length;i++){
			var str=vArray[i];
			if(str.indexOf("/video")>0){
				str=str.substr(0,str.indexOf("</video>")+8);
				var vStr="<video"+str;
				res=res.replace(vStr,"[影音文件]");
			}
		}
	}
	var wvArray = res.split("<embed");
	if(wvArray.length > 0){
		for(var i=0;i<wvArray.length;i++){
			var str=wvArray[i];
			if(str.indexOf("type")>0){
				str=str.substr(0,str.indexOf("/>")+2);
				var vStr="<embed"+str;
				res=res.replace(vStr,"[影音文件]");
			}
		}
	}
	if(res.length>15){
		return res.substr(0,15);
	}
	res += "<a href='javascript:;' onclick='previewSt("+rowData.questionId+");' class='easyui-linkbutton'  plain='true' iconCls='icon-search'>预览</a>";
	return res;
}
function previewSt(id) {
	parent.previewSt(id);
}
//组织段落数据
function fmtSjJson(){
	var allQsnids=$("#gdQsnList").val();
	var d_dlmix={"allids":allQsnids};
	var d_xtmix={};
	var pJson={
		"gdxt":d_dlmix,
		"sjxt":d_xtmix
	};
	var items=$("#sjInfoTable").find("input.input_user_info");
	for(var i=0;i<items.length;i++){
		var indexid=$(items[i]).attr("indexid");
		var sjQsnCsValue=$("#sjQsnCs_"+indexid).val();
		var qsnFen=parseFloat($("#fen_"+indexid).val());
		/* if(qsnFen==0){
			return "段落"+(i+1)+"分数不能为0";
			break;
		} */
		var key=(i+1)+"";
		d_dlmix[key]={"t":$(items[i]).val(),"ids":$("#gdQsnids_"+indexid).val(),"fen":qsnFen};
		
		if(sjQsnCsValue != '' && sjQsnCsValue != null){
			var data=eval("(" + sjQsnCsValue + ")");
			d_xtmix[key]={
					"did":i+1,
					"classifyid":data.classifyid,
					"shititypeid":data.shititypeid,
					"levelid":data.levelid,
					"knowledgeid":data.knowledgeid,
					"num":data.randnum,
					"fen":qsnFen
					};
		}
		
	}
	var jsonStr=JSON.stringify(pJson);
	$("#dlInfo").val(jsonStr);
	return "ok";
}
//保存试卷信息
function saveShiJuan(){
	var okstate=fmtSjJson();
	if(okstate!="ok"){
		msgShow("<span style='color:red'>"+okstate+"</span>");
		return;
	}
	$('#sjForm').submit();
	return false;
}
//加载试卷信息
function getSjInfo(){
	$.post("${pageContext.request.contextPath}/admin/shijuan/getSjDataById.json",{"sjId":sjId},function(data){
		$('#sjClassifyCombotree').combotree("setValue",data.sjClassifyId);
		$('#sjtitle').val(data.title);
		$('#sjDesEditor').val(data.des);
		$('#sjId').val(data.sjId);
		$('#qsnCountSum').val(data.count);
		$('#sjZF').val(data.totalScore);
		$('#gdQsnList').val(data.des);
		
		//可见性
		$("#visableCmb").combobox("setValue", data.visable);
		
//		$('#sjDesEditor').val(data.des);
		var gdItems=eval("("+data.gdxt+")");
		var sjItems=eval("("+data.sjxt+")");
		$('#gdQsnList').val(gdItems.allids);
		delete gdItems.allids;
		var num=1;
		var sjInx=1;
		for(var i in gdItems){
			var gdids=gdItems[num+""].ids.split(",");
			var gdCount=gdids.length-1;
			var tdHtml="<tr height='35px'>" +
			"<td align='left'><input id='ms_"+num+"' class='input_user_info' indexid='"+num+"' style='width:100%;height:25px;' type='text' value='"+gdItems[num+""].t+"'/></td>" +
			"<td align='center'><a id='gdxz_"+num+"' indexid='"+num+"' onclick='showGdDia(this);'>固定选题</a>|<a id='yx_"+num+"' indexid='"+num+"' onclick='yxQsn(this);'>已选</a></td>" +
			"<td align='center'><a href='javascript:;' indexid='"+num+"' class='blue_color_a' id='gdnum_"+num+"' onclick='showGdGridDia(this)'>"+gdCount+"</a>" +
			"<input type='hidden' id='gdQsnids_"+num+"' value='"+gdItems[num+""].ids+"'/></td>" +
			"<td align='center'><a id='sjxz_"+num+"' indexid='"+num+"' onclick='showSjDia(this);'>随机抽题</a></td>" +
			"<td align='center'><a href='javascript:;' indexid='"+num+"' class='blue_color_a' id='sjnum_"+num+"' onclick='showSjGridDia(this)'>0</a>" +
			"<input type='hidden' id='sjQsnCs_"+num+"'></td>" +
			"<td align='center'><input type='text' id='fen_"+num+"' onblur='qsnFenOnkeyup(this.id)' value='"+gdItems[num+""].fen+"' style='width:60px;height:25px;border: 1px solid #c3d9e0;text-align:center;'/></td>" +
			"<td align='center'><a id='manage_"+num+"' indexid='"+num+"' onclick='delSjMessage(this);'>删除</a></td>" +
			"</tr>";
			$('#sjInfoTable').append(tdHtml);
			$('#gdxz_'+num).linkbutton({
				iconCls : 'icon-add',
				plain:true
			});
			$('#sjxz_'+num).linkbutton({
				iconCls : 'icon-add',
				plain:true
			});
			$('#manage_'+num).linkbutton({
				iconCls : 'icon-no',
				plain:true
			}); 
			num++;
		}
		for(var j in sjItems){
			$("#sjnum_"+sjInx).html(sjItems[sjInx+""].num);
			var param="{classifyid:'"+sjItems[sjInx+""].classifyid+"',shititypeid:"+sjItems[sjInx+""].shititypeid+",levelid:"+sjItems[sjInx+""].levelid+",knowledgeid:'"+sjItems[sjInx+""].knowledgeid+"',randnum:"+sjItems[sjInx+""].num+"}";
			$("#sjQsnCs_"+sjInx).val(param);
			sjInx++;
		}
		tdindex=num;
	},"json");
}
function noSubmit() {
	window.parent.closeTabByTitle("试卷管理");
	parent.addTab('试卷管理', '${pageContext.request.contextPath}/admin/shijuan/sjManager.html?');
	if(pageType == "edit")
		window.parent.closeTabByTitle("编辑试卷");
	else 
		window.parent.closeTabByTitle("新建试卷");
}
function yxQsn(obj) {
	oldIndex=$(obj).attr("indexid");
	var splitArr = $("#gdQsnids_"+oldIndex).val().split(",");
	var selectedArr = [];
	for(var i=0;i<splitArr.length;i++) {
		if(splitArr[i] != '')
			selectedArr.push(splitArr[i]);
	}
	$('#yxQsn').dialog("open");
	$('#yxItemlist').datagrid('load', {
		'ids' : selectedArr
	});
	$('#yxItemlist').datagrid('clearSelections');
}
function deleteSelectedSt() {
	var items=$('#yxItemlist').datagrid("getSelections");
	var ids = splitArr($("#gdQsnids_"+oldIndex).val());
	var allIds = splitArr($("#gdQsnList").val());
	
	for(var i=0;i<items.length;i++) {
		var newIds = [];
		for(var j=0;j<ids.length;j++) {
			if(ids[j] != items[i].questionId)
				newIds.push(ids[j]);
		}
		ids = newIds;
		var newAllIds = [];
		for(var j=0;j<allIds.length;j++) {
			if(allIds[j] != items[i].questionId) 
				newAllIds.push(allIds[j]);
		}
		allIds = newAllIds;
	}
	var temp1 = "";
	var temp2 = "";
	for(var i=0;i<ids.length;i++) { temp1 += ","+ids[i];}
	for(var i=0;i<allIds.length;i++) { temp2 += ","+allIds[i];}
	$("#gdQsnids_"+oldIndex).val(temp1);
	$("#gdQsnList").val(temp2);
	var oldQsnIds=$("#gdQsnids_"+oldIndex).val();
	var qsnCount=oldQsnIds.split(",").length-1;
	$("#gdnum_"+oldIndex).html(qsnCount);
	$('#yxQsn').dialog("close");
}
function splitArr(strArr) {
	var temp = strArr.split(",");
	var arr = [];
	for(var i=0;i<temp.length;i++) {
		if(temp[i] != '')
			arr.push(temp[i]);
	}
	return arr;
}
</script>
<style type="text/css">
.lable_span {
	border: 1px solid #c3d9e0;
	border-radius: 5px;
	background-color: #c3d9e0;
	color: #404040;
	font-weight: bold;
	line-height: 35px;
	padding: 5px;
}
.input_user_info {
	border: 1px solid #c3d9e0;
	width: 180px;
	height: 32px;
}
.div_sj_info {
	float: left;
	width: 45%;
	margin: 10px;
}
ol li {
	list-style-type: upper-alpha;
}
.blue_color_a:link {
	FONT-SIZE: 16px;
	COLOR: #1A7BC9;
	LINE-HEIGHT: 18px;
	TEXT-DECORATION: none
}
.blue_color_a:hover {
	FONT-SIZE: 16px;
	COLOR: #66C;
	LINE-HEIGHT: 18px;
}
.panel_qsn {
	width: 95%;
	padding-left: 30px;
	padding: 10px;
	background: #fafafa;
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
}
.div_qsn_text{
width:100px;
text-align: right;
float:left;
padding: 10px;
}
.div_qsn_info{
float:left;
padding: 10px;
}
</style>
</head>
<body class="easyui-layout">
	<div region="center"
		style="background: #fafafa;padding:20px; border: 0px;">
			<div class="easyui-panel"
				style="background:#fafafa;padding:10px;border: 0px;"
				data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<form method="post" id="sjForm">
				<span class="lable_span">试卷属性</span><br />
				<br />
				<div class="div_sj_info">
					试卷名称：<input class="easyui-validatebox input_user_info"
						style="width:300px;height:25px;" type="text"
						data-options="required:true" name="title" id="sjtitle"/>
				</div>
				<div class="div_sj_info">
					试卷分类：<input id="sjClassifyCombotree" data-options="required:true" style="width:200px;height:28px;" name="sjClassifyId"/>
				</div>
				<c:if test="${user.readPrivateSt == 1 }">
				<div class="div_sj_info">
				可见性：
				<select id="visableCmb" name="visable" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
					<option value="1">公开试卷</option>
					<option value="0">私有试卷</option>
				</select>
				</div>
				</c:if>
				<div class="div_sj_info" style="width:90%">
					试卷描述：<br />
					<br />
					<textarea id="sjDesEditor" class="input_user_info" name="des"
						style="width:80%;height:80px;" >...</textarea>
				</div>
				<div class="div_sj_info">
					<input type="hidden" id="qsnCountSum" name="totalshiti" value="0"/>
					总分：<input class="input_user_info" readonly="readonly"
						style="width:50px;height:25px;text-align: center" type="text" id="sjZF" value="0" name="totalscore"/>&nbsp;&nbsp;分。
				</div>
				<div class="div_sj_info">注：总分根据所选题的分数自动统计</div>
				<br />
				<input type="hidden" id="dlInfo" name="stinfo"/>
				<input type="hidden" id="sjId" name="sjId"/>
				<input type="hidden" id="gdQsnList" name="gdids"/>
				</form>
			</div>
			<br />
			<div class="easyui-panel "
				style="background:#fafafa;padding:10px;border: 0px;"
				data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<a class="easyui-linkbutton" iconCls="icon-add"
					onclick="addTableRow();" style="width:130px;height:40px;">添加试卷内容</a>
				<br /> <br />
				<table id="sjInfoTable" style="border: 1px dashed #ccc;" width="72%">
					<tr style="background-color: #ccc">
						<th width="30%" rowspan="2" align="left">描述</th>
						<th width="25%" colspan="2" align="center">固定题</th>
						<th width="25%" colspan="2" align="center">随机题</th>
						<th width="10%" rowspan="2" align="center">每题分数</th>
						<th width="10%" rowspan="2" align="center">操作</th>
					</tr>
					<tr style="background-color: #ccc">
						<th align="center">选择试题</th>
						<th align="center">试题数</th>
						<th align="center">选择试题</th>
						<th align="center">试题数</th>
					</tr>
				</table>
			</div>
		<div id="gdQsn">
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;height:400px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<table id="itemlist" toolbar="#tbar"></table>
				<div id="tbar" style="height:34px;padding-top:10px;font-size:12px;">
					题目：<input class="easyui-validatebox input_user_info" style="width:120px;height:25px;" maxlength="20" type="text" id="searchtitle"/>
					试题分类：<input id="searchclassify" style="width:120px;height:28px;" />  
					题型：<input id="searchshititype" style="width:120px;height:28px;" /> 
					难度：<input id="searchlevel" style="width:120px;height:28px;" /> 
					<!-- 知识点：<input id="searchknowledge" style="width:120px;height:28px;" /> --> 
					<c:if test="${user.readPrivateSt == 1 }">
					可见性：
					<select id="query_stVisable" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
						<option value="-1">全部</option>
						<option value="1">公开题目</option>
						<option value="0">私有题目</option>
					</select>
					</c:if>
					<a class="easyui-linkbutton"
					   iconCls="icon-search"  plain="true" onclick="seachQsnByParam();">查询</a>
				</div>
			</div>
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;text-align: center;height:50px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<a class="easyui-linkbutton" iconCls="icon-save" onclick="showSelectSum();"
					style="width:130px;height:40px;">保存</a>
			</div>
		</div>
		<div id="yxQsn">
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;height:400px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<table id="yxItemlist"></table>
			</div>
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;text-align: center;height:50px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<a class="easyui-linkbutton" iconCls="icon-delete" onclick="deleteSelectedSt();"
					style="width:130px;height:40px;">删除试题</a>
			</div>
		</div>
		<!-- <div id="sjQsn">
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;padding: 10px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<div class="div_qsn_text">试题分类：</div><div class="div_qsn_info"><input id="qsnclassify" name="classifyid" style="width:180px;height:28px;" /></div>  
				<div class="div_qsn_text">试题题型：</div><div class="div_qsn_info"><input id="qsnshititype" name="shititypeid" style="width:180px;height:28px;" /> </div> 
				<div class="div_qsn_text">试题难度：</div><div class="div_qsn_info"><input id="qsnlevel" name="levelid" style="width:180px;height:28px;" /> </div> 
				<div class="div_qsn_text">试题知识点：</div><div class="div_qsn_info"><input id="qsnknowledge" name="knowledgeid" style="width:180px;height:28px;" /></div> 
				<div class="div_qsn_text">试题题数：</div>
     			<div class="div_qsn_info"><input id="qsnNum" name="randnum" class="easyui-numberspinner" data-options="min:0,value:0" style="width:80px;height:28px;"   
     			data-options="min:0"/>&nbsp;&nbsp;/&nbsp;&nbsp;<span id="qsnCount" countid=""></span></div> 
			</div>
			<div class="easyui-panel "
			style="background:#fafafa;border: 0px;text-align: center;height:50px;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
				<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveSjQSN();"
					style="width:130px;height:40px;">保存</a>
			</div>
		</div> -->
		<div id="sjQsnGrid">
			<table id="sjGrid"></table>
		</div>
		<div id="gdQsnGrid">
			<table id="gdGrid"></table>
		</div>
	</div>
	<div region="south" split="false"
		style="height:50px;padding:0px;text-align:center;line-height:50px;border: 0px;" >
		<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveShiJuan();"
			style="width:130px;height:40px;">保存并预览</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" iconCls="icon-back" onclick="noSubmit();"
			style="width:130px;height:40px;">返回试卷管理</a>
	</div>
</body>

</html>