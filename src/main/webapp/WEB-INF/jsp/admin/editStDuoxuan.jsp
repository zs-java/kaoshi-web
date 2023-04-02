<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加多选题</title>
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
var nextNum = 1;
var isClose = true; //编辑完成后是否继续编辑，默认为true，即默认关闭
$(document).ready(function (){
	loadBaseInfo();
	var type = '${type }';
	var questionId = '${questionId }';
	var title = '${typeName}';
	
	UE.getEditor('answerEditor');
	UE.getEditor('answerTitleEditor');
	
	if(type == 'edit') {
		$("#closeFalseBtn").remove();
		//如果是修改试题，设置试题默认值
		$('#qsnForm').form({
			url : "${pageContext.request.contextPath}/admin/shiti/updateStDuoxuan.action",
			onSubmit : function() {
				return $('#qsnForm').form("validate");
			},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg == 'success') {
					alert("修改试题成功！");
					parent.redirect("${pageContext.request.contextPath}/admin/shiti/stManager.html");
				} else if(result.msg == 'error') {
					setTimeout($.messager.show({
						title:'提示',
						msg:'修改试题失败！' + result.info,
						timeout:3000
					}), 0);
				}
			}
		});
		loadStInfo(questionId);
	} else {
		addOption();
		addOption();
		addOption();
		addOption();
		//添加试题，试题编号默认为-1
		$("#questionIdValue").val(-1);
	}
});
function loadStInfo(questionId) {
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/shiti/getStMainById.action",
		dataType:"json",
		loadMsg : '数据装载中......',
		data:{"questionId":questionId},
		success:function(result) {
			//alert(JSON.stringify(result));
			if(result.msg == 'success') {
				//加载成功，初始化试题信息
				var stMain = result.info;
				//loadCombotree();
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
		url:"${pageContext.request.contextPath}/admin/shiti/getStDuoxuanById.action",
		dataType:"json",
		loadMsg : '数据装载中......',
		data:{"questionId":questionId},
		success:function(result) {
			if(result.msg == 'success') {
				//加载成功，初始化单选题信息
				var stDuoxuan = result.info;
				//alert(JSON.stringify(stDuoxuan));
				//设置题目信息
				var answerTitleEditor = UE.getEditor("answerTitleEditor");
				answerTitleEditor.ready(function() {
					var t = stDuoxuan.title;
					answerTitleEditor.setContent(t); 
				});
				//设置题目解析信息
				var answerEditor = UE.getEditor("answerEditor");
				answerEditor.ready(function() { 
					var d = stDuoxuan.detail;
					answerEditor.setContent(d); 
				});
				//设置选项信息
				var options = stDuoxuan.options;
				for(var key in options) {
					//alert("key:" + key);
					//alert("key:"+key+",value:"+options[key].option);
					var liHtml = "<li>"
						+"<input type='checkbox' id='cbx_" + key + "' name='rightKeyCbx'/>答案&nbsp;&nbsp;"
						+"<a class='blue_color_a' href='javascript:;' onclick='removeOption(this)'>删除</a>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;"
						+"<span id='option_" + key + "_content' style='color:red;'></span><br/>"
						+"<textarea id='option_" + key + "' ueid='option_" + key + "' name='content' style='width:90%;height:50px;' ></textarea>";
						+"</li>";
					$("#optionsOl").append(liHtml);
					setContent("option_" + key, options[key].option);
					nextNum++;
				}
				//设置正确选项
				var rightKeys = stDuoxuan.rightKey;
				for(var i = 0; i < rightKeys.length; i++) {
					document.getElementById("cbx_" + rightKeys[i]).checked = "checked";
				}
			} else if(result.msg == "error") {
				alert(result.info);
			}
		}
	});
}
function setContent(id, content) {
	var ue = UE.getEditor(id);
	ue.ready(function() {
		ue.setContent(content);
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
function loadBaseInfo() {
	loadCombotree();
	$('#qsnForm').form({
		url : "${pageContext.request.contextPath}/admin/shiti/saveStDuoxuan.action",
		async : false,
		onSubmit : function() {
			return $('#qsnForm').form("validate");
		},
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				if(isClose) {
					//关闭标签页
					alert("添加试题成功！");
					closeThisTab();
					//window.location = "${pageContext.request.contextPath}/admin/shiti/stManager.html";
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
					$("#optionsValue").val('');
					$("#titleValue").val('');
					$("#titleTextValue").val('');
					$("#detailValue").val('');
					$("#questionIdValue").val('-1');
					//loadCombotree();
					var optionsOl = document.getElementById('optionsOl');
					optionsOl.innerHTML = '';
					addOption();
					addOption();
					addOption();
					addOption();
					setTimeout($.messager.show({
						title:'提示',
						msg:'添加试题成功！',
						timeout:3000
					}), 0);
				}
			} else if(result.msg == 'error') {
				setTimeout($.messager.show({
					title:'提示',
					msg:'添加试题失败！' + result.info,
					timeout:3000
				}), 0);
			}
		}
	});
};
function removeOption(obj) {
	var li = $(obj).parent();
	li.remove();
}
function closeThisTab(){  
    parent.closeThisTab();
}
function addOption() {
	var liHtml = "<li>"
				+"<input type='checkbox' name='rightKeyCbx'/>答案&nbsp;&nbsp;"
				+"<a class='blue_color_a' href='javascript:;' onclick='removeOption(this)'>删除</a>"
				+"&nbsp;&nbsp;&nbsp;&nbsp;"
				+"<span id='option_" + nextNum + "_content' style='color:red;'></span><br/>"
				+"<textarea id='option_" + nextNum + "' ueid='option_" + nextNum + "' name='content' style='width:90%;height:50px;' ></textarea>";
				+"</li>";
	$("#optionsOl").append(liHtml);
	UE.getEditor("option_" + nextNum);
	nextNum++;
}

function saveQsn(type)  {
	
	if(type == 1) 
		isClose = true;
	else 
		isClose = false;
	
	var error = 0;//错误数
	var obj = {};//声明选项MAP
	//var rightKey = 0;//初始选项答案
	var rightKey = [];
	var titleContent = UE.getEditor("answerTitleEditor").getContent();
	var titleText = UE.getEditor("answerTitleEditor").getContentTxt();
	var detailContent = UE.getEditor("answerEditor").getContent();
	var liitems = $("#optionsOl").children("li");
	for ( var i = 0; i < liitems.length; i++) {
		//循环li数组 获取按钮对象
		var radioItem = $(liitems[i]).children("input");
		if ($(radioItem).is(":checked")) {
			//如果按钮被选中则记录答案
			//rightKey = i + 1;
			rightKey.push(i+1);
		}
		//获取文本信息
		var textarea = $(liitems[i]).children("textarea");
		var id = $(textarea).attr("ueid");
		var content = UE.getEditor(id).getContent();
		var text = UE.getEditor(id).getContentTxt();
		if (text == "" || text == null) {
			//如果答案纯文本为空则向错误提示信息处添加错误信息
			$("#" + id + "_content").html("答案文本不能为空！");
			error = error + 1;
		} else {
			$("#" + id + "_content").html("");
		}
		var objchild = {
			"option" : 0
		};
		//组织选项json数据
		objchild.option = content;
		obj[(i + 1) + ""] = objchild;
	}
	
	if (rightKey.length == 0) {
		$("#answer_daan_content").html("请选择一项作为答案！");
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
	$("#optionsValue").val(JSON.stringify(obj));
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
	/* border: 1px solid black; */
}
</style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;">
	<div region="center" style="background: #fafafa;padding:30px;border:0px;font-size: 12px;width: 100%;height: 100%;">
		<form method="post" id="qsnForm">
		<div class="easyui-panel panel_qsn"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">试题属性</span><br/><br/>
			试题分类：<input id="stClassifyCombotree" name="classifyId" data-options="required:true" style="width:220px;height:28px;" />&nbsp;&nbsp;&nbsp;&nbsp;
			难度：<input id="stLevelCombotree" name="levelId" data-options="required:true" style="width:150px;height:28px;" /> &nbsp;&nbsp;&nbsp;&nbsp;
			知识点：<input id="stKnowledgeCombotree" name="knowledgeId" data-options="required:true" style="width:150px;height:28px;" />
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
			<span class="lable_span">题目（题干内容）</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_title_content' style='color:red;'></span>
			<br/><br/>
			<textarea id="answerTitleEditor" name="content" style="width:90%;height:50px;" > </textarea>  
		</div><br/>
		<div class="easyui-panel panel_qsn"    
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">选项（从选项中选择一个作为答案）</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_daan_content' style='color:red;'></span>
			<br/>
			<ol id="optionsOl">
			</ol><br/>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addOption()" style="width:100px;height:30px;">增加选项</a>
		</div><br/>
		<div class="easyui-panel panel_qsn"    
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">答题解析</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_content' style='color:red;'></span>
			<br/><br/>
			<textarea id="answerEditor" name="content" style="width:90%;height:50px;" ></textarea>
		</div><br/>
		<div class="easyui-panel"    
			style="width:95%;background:#fafafa;padding:10px;text-align: center;border: 0px;"  
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<input type="hidden" id="titleValue" name="title"/>
			<input type="hidden" id="titleTextValue" name="titleText"/>
			<input type="hidden" id="detailValue" name="detail"/>
			<input type="hidden" id="optionsValue" name="options"/>
			<input type="hidden" id="rightKeyValue" name="rightKey"/>
			<input type="hidden" id="questionIdValue" name="questionId"/>
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