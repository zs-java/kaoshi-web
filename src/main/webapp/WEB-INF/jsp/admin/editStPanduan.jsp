<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加判断题</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
var isClose = true;
$(document).ready(function() {
	loadBaseInfo();
	
	var type = '${type }';
	var questionId = '${questionId }';
	var title = '${typeName}';
	
	if(type == 'edit') {
		//编辑试题
		$("#questionIdValue").val(questionId);
		loadStInfo(questionId);
		$("#closeFalseBtn").remove();
		$("#qsnForm").form({
			url : "${pageContext.request.contextPath}/admin/shiti/updateStPanduan.action",
			onSubmit : function() {
				return $('#qsnForm').form("validate");
			},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg == 'success') {
					alert("修改试题成功！");
					parent.redirect("${pageContext.request.contextPath}/admin/shiti/stManager.html");
				} else if (result.msg == 'error') {
					setTimeout($.messager.show({
						title:'提示',
						msg:'添加试题失败！' + result.info,
						timeout:3000
					}), 0);
				}
			}
		});
	}
});
function loadBaseInfo() {
	loadCombotree();
	UE.getEditor('answerEditor');
	UE.getEditor('answerTitleEditor');
	$("#qsnForm").form({
		url : "${pageContext.request.contextPath}/admin/shiti/saveStPanduan.action",
		onSubmit : function() {
			return $('#qsnForm').form("validate");
		},
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				if(isClose) {
					alert("添加试题成功！");
					parent.closeThisTab();
				} else {
					//不关闭，清空表单信息
					var answerTitleEditor = UE.getEditor("answerTitleEditor");
					answerTitleEditor.ready(function() {
						answerTitleEditor.setContent(''); 
					});
					var answerEditor = UE.getEditor("answerEditor");
					answerEditor.ready(function() { 
						answerEditor.setContent(''); 
					});
					$("#rightKeyValue").val('');
					$("#titleValue").val('');
					$("#titleTextValue").val('');
					$("#detailValue").val('');
					$("#questionIdValue").val('-1');
					//loadCombotree();
					$("#radioWrong").attr("checked", false);
					$("#radioRight").attr("checked", false);
					setTimeout($.messager.show({
						title:'提示',
						msg:'添加试题成功！',
						timeout:3000
					}), 0);
				}
			} else if (result.msg == 'error') {
				setTimeout($.messager.show({
					title:'提示',
					msg:'添加试题失败！' + result.info,
					timeout:3000
				}), 0);
			}
		}
	});
}
/**
 * 加载试题分类、难度、知识点下拉选框
 */
function loadCombotree() {
	$.ajax({
		url:'${pageContext.request.contextPath }/admin/shiti/getAllStClassifyTree.json',
		type:'POST',
		dataType:'json',
		async : false,
		success:function(data) {
			$("#stClassifyCombotree").combotree({data:data});
		}
	});
	$.ajax({
		url:'${pageContext.request.contextPath }/admin/shiti/getAllStLevelTree.json',
		type:'POST',
		dataType:'json',
		async : false,
		success:function(data) {
			$("#stLevelCombotree").combotree({data:data});
		}
	});
	$.ajax({
		url:'${pageContext.request.contextPath }/admin/shiti/getAllStKnowledgeTree.json',
		type:'POST',
		dataType:'json',
		async : false,
		success:function(data) {
			$("#stKnowledgeCombotree").combotree({data:data});
		}
	});
}
function loadStInfo(questionId) {
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shiti/getStMainById.action",
		dataType:"json",
		loadMsg : '数据装载中......',
		data:{"questionId":questionId},
		success:function(result) {
			if(result.msg == 'success') {
				//加载成功，初始化试题信息
				var stMain = result.info;
				$("#stClassifyCombotree").combotree("setValue", stMain.stClassifyId);
				$("#stLevelCombotree").combotree("setValue", stMain.stLevelId);
				$("#stKnowledgeCombotree").combotree("setValue", stMain.stKnowledgeId);
				$("#questionIdValue").val(stMain.questionId);
				$("#visableCmb").combobox("setValue", stMain.visable);
			} else if(result.msg == "error") {
				alert(result.info);
			}
		}
	});
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shiti/getStPanduanById.action",
		dataType:"json",
		loadMsg : '数据装载中......',
		data:{"questionId":questionId},
		success:function(result) {
			if(result.msg == 'success') {
				//加载成功，初始化单选题信息
				var stPanduan = result.info;
				//设置题目信息
				var answerTitleEditor = UE.getEditor("answerTitleEditor");
				answerTitleEditor.ready(function() {
					var t = stPanduan.title;
					answerTitleEditor.setContent(t); 
				});
				//设置题目解析信息
				var answerEditor = UE.getEditor("answerEditor");
				answerEditor.ready(function() { 
					var d = stPanduan.detail;
					answerEditor.setContent(d); 
				});
				//设置正确选项
				if(stPanduan.rightKey == true)
					document.getElementById("radioRight").checked = "checked";
				else 
					document.getElementById("radioWrong").checked = "checked";
			} else if(result.msg == "error") {
				alert(result.info);
			}
		}
	});
};
function radioChecked(obj) {
	var type = $(obj).attr("aid");
	if(type == 1) {
		document.getElementById("radioRight").checked = "checked";
	} else if (type == 0) {
		document.getElementById("radioWrong").checked = "checked";
	}
}
function saveQsn(type)  {
	
	if(type == 1) 
		isClose = true;
	else 
		isClose = false;
	
	var error = 0;//错误数
	var titleContent = UE.getEditor("answerTitleEditor").getContent();
	var titleText = UE.getEditor("answerTitleEditor").getContentTxt();
	var detailContent = UE.getEditor("answerEditor").getContent();
	var rightKey = -1;
	//alert(document.getElementById("radioRight").checked);
	if(document.getElementById("radioRight").checked)
		rightKey = 1;
	else if (document.getElementById("radioWrong").checked)
		rightKey = 0;
	if (rightKey != 1 && rightKey != 0) {
		$("#answer_daan_content").html("请选择判断题是否正确！");
		error++;
	} else {
		$("#answer_daan_content").html("");
	}
	if (titleContent == "" || titleContent == null) {
		$("#answer_title_content").html("请输入题干内容！");
		error++;
	} else {
		$("#answer_title_content").html("");
	}
	if (error > 0) {
		return;
	}
	//可见性
	if(document.getElementById("visableCmb")) {
		$("#visable").val($("#visableCmb").val());
	}
	$("#rightKeyValue").val(rightKey);
	$("#titleValue").val(titleContent);
	$("#titleTextValue").val(titleText);
	$("#detailValue").val(detailContent);
	$('#qsnForm').submit();
	return false;
}
</script>
<style type="text/css">
.lable_span{
	border: 1px solid #c3d9e0;
	border-radius: 5px;
   	background-color: #c3d9e0;
   	color:#404040;
   	font-weight: bold ;
   	line-height: 35px;
   	padding:5px;
}
ol li{
	list-style-type :upper-alpha;
}
.blue_color_a:link {
FONT-SIZE: 12px; COLOR: #1A7BC9; LINE-HEIGHT: 18px; FONT-FAMILY: "宋体"; TEXT-DECORATION: none
}
.blue_color_a:hover {
	FONT-SIZE: 12px; COLOR: #66C; LINE-HEIGHT: 18px; FONT-FAMILY: "宋体"
}
.panel_qsn{
	width:95%;
	padding-left:30px;
	padding:10px;
	background:#fafafa;
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
}
</style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div region="center" style="background: #fafafa;padding:30px;border:0px;font-size: 12px;">
		<form method="post" id="qsnForm">
		<div class="easyui-panel panel_qsn"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">试题属性</span><br/><br/>
			试题分类：<input id="stClassifyCombotree" data-options="required:true" name="classifyId" style="width:220px;height:28px;" />&nbsp;&nbsp;&nbsp;&nbsp;
			难度：<input id="stLevelCombotree" data-options="required:true" name="levelId" style="width:150px;height:28px;" /> &nbsp;&nbsp;&nbsp;&nbsp;
			知识点：<input id="stKnowledgeCombotree" data-options="required:true" name="knowledgeId" style="width:150px;height:28px;" />
			<c:if test="${user.readPrivateSt == 1 }">
			可见性：
			<select id="visableCmb" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
				<option value="1">公开题目</option>
				<option value="0">私有题目</option>
			</select>
			</c:if>
			<br/>   
		</div><br/>
		<div class="easyui-panel panel_qsn"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">试题描述</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_title_content' style='color:red;'></span>
			<br/><br/>
			<textarea id="answerTitleEditor" name="content" style="width:90%;height:100px;" > </textarea>  
		</div><br/>
		<div class="easyui-panel panel_qsn"    
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">答案选项</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_daan_content' style='color:red;'></span>
			<br/>
			<br/>
			<input id="radioRight" type="radio" value="1" name="daan"/>&nbsp;&nbsp;
			<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-ok'" aid="1" onClick="radioChecked(this);"></a>
			<input id="radioWrong" type="radio" value="0" name="daan"/>&nbsp;&nbsp;
			<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-cancel'" aid="0" onClick="radioChecked(this);"></a>
			<br/>
		</div><br/>
		<div class="easyui-panel panel_qsn"    
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">答题解析</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_content' style='color:red;'></span>
			<br/><br/>
			<textarea id="answerEditor" name="content" style="width:90%;height:80px;" > </textarea>
		</div><br/>
		<div class="easyui-panel"    
			style="width:95%;background:#fafafa;padding:10px;text-align: center;border: 0px;"  
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<input type="hidden" id="questionIdValue" name="questionId"/>
			<input type="hidden" id="titleValue" name="title"/>
			<input type="hidden" id="titleTextValue" name="titleText"/>
			<input type="hidden" id="rightKeyValue" name="rightKey"/>
			<input type="hidden" id="detailValue" name="detail"/>
			<input type="hidden" id="visable" name="visable" value="1"/>
			<a class="easyui-linkbutton" id="closeFalseBtn" iconCls="icon-save" onclick="saveQsn(0);" style="width:130px;height:40px;">保存并继续添加</a>
			&nbsp;&nbsp;<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveQsn(1);" style="width:130px;height:40px;">保存并关闭</a>
		</div>
		<br/>
		</form>
	</div>
</div>  
</body>
</html>