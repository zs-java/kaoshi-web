<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加填空题</title>
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
			url : "${pageContext.request.contextPath}/admin/shiti/updateStTiankong.action",
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
		url:"${pageContext.request.contextPath}/admin/shiti/getStTiankongById.action",
		dataType:"json",
		loadMsg : '数据装载中......',
		data:{"questionId":questionId},
		success:function(result) {
			if(result.msg == 'success') {
				//加载成功，初始化单选题信息
				var tiankong = result.info;
				//将答案转json
				var kongsItems=tiankong.rightKey;
				var num=1;
				
				for(var i in kongsItems){
					//循环答案并向OL中添加答案HTML
					var liHtml="<li>"+
					"<input type='button' disabled='disabled' style='width:80px' linkageblock='options:"+num+"' value='填空"+num+"'/>&nbsp;&nbsp;"+
					"<a class='blue_color_a' href='javascript:;' cid='"+num+"' onclick='removeAnswerChild(this)'>删除</a><br/><br/>"+
					"<span style='font-size:12px;font-style: italic;'>答案：</span><input id='options_txt_"+num+"_0' class='input_user_info' type='text'/>&nbsp;&nbsp;"+
					"<input id='options_txt_"+num+"_1' class='input_user_info' type='text'/>&nbsp;&nbsp;"+
					"<input id='options_txt_"+num+"_2' class='input_user_info' type='text'/><span style='font-size:12px;font-style: italic;'>支持添加多个答案</span>"+
					"</li>";
					$("#answerOl").append(liHtml);
					var strs=kongsItems[i].key.split("#");
					for(var j=0;j<strs.length;j++){
						$('#options_txt_'+num+'_'+j).val(strs[j]);
					}
					$('#options_txt_'+num+'_0').validatebox({    
					    required: true   
					}); 
					num++;
				}
				maxNum=num;
				var newTitle=replaceTitleContentFromDate(tiankong.title);
				//设置题目信息
				var answerTitleEditor = UE.getEditor("answerTitleEditor");
				answerTitleEditor.ready(function() {
					answerTitleEditor.setContent(newTitle); 
				});
				//设置题目解析信息
				var answerEditor = UE.getEditor("answerEditor");
				answerEditor.ready(function() { 
					answerEditor.setContent(tiankong.detail); 
				});
			} else if(result.msg == "error") {
				alert(result.info);
			}
		}
	});
}
function loadBaseInfo() {
	loadCombotree();
	UE.getEditor('answerEditor');
	//题干部分丢失焦点触发删除填空选项方法
	UE.getEditor("answerTitleEditor").addListener('blur',function(editor){removeChildLiByUe();});
	UE.getEditor('answerTitleEditor');
	$("#qsnForm").form({
		url : "${pageContext.request.contextPath}/admin/shiti/saveStTiankong.action",
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
					removeChildLiByUe();
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
//被删除填空游标数组
var removeNum=[];
//下一位填空游标
var nextNum=1;
//最大填空游标
var maxNum=1;
//增加填空
function addAnswerChild(){
	
	if(removeNum.length>0){
		//如果被删除填空游标数组长度>0则排序并赋值给下一位填空游标
		removeNum=removeNum.sort(function(a,b){return a>b?1:-1});
		nextNum=removeNum[0];
	}else{
		//否则 等于最大游标
		nextNum=maxNum;
	}
	//插入内容
	var liHtml="<li>"+
		"<input type='button' disabled='disabled' style='width:80px' linkageblock='options:"+nextNum+"' value='填空"+nextNum+"'/>&nbsp;&nbsp;"+
		"<a class='blue_color_a' href='javascript:;' cid='"+nextNum+"' onclick='removeAnswerChild(this)'>删除</a><br/><br/>"+
		"<span style='font-size:12px;font-style: italic;'>答案：</span><input id='options_txt_"+nextNum+"_0' class='input_user_info' type='text'/>&nbsp;&nbsp;"+
		"<input id='options_txt_"+nextNum+"_1' class='input_user_info' type='text'/>&nbsp;&nbsp;"+
		"<input id='options_txt_"+nextNum+"_2' class='input_user_info' type='text'/><span style='font-size:12px;font-style: italic;'>支持添加多个答案</span>"+
		"</li>";
	$("#answerOl").append(liHtml);
	$('#options_txt_'+nextNum+'_0').validatebox({    
	    required: true   
	});  
	//获取题干内容
	var oldText=UE.getEditor("answerTitleEditor").getContent();
	//最佳html代码 即向editor中追加象征填空空位按钮
	UE.getEditor("answerTitleEditor").execCommand('insertHtml', "<input type='button' style='width:80px' disabled='disabled' linkageblock='options:"+nextNum+"' value='填空"+nextNum+"'/>")
	
	if(removeNum.length>0){
		//如果被删除填空游标数组长度>0则删除第一位
		removeNum.splice(0,1);
	}else{
		//最大游标数+1
		maxNum++;
	}
}
//当删除Editor中的空位按钮时删除答案选项中对应空位
function removeChildLiByUe(){
	var oldText=UE.getEditor("answerTitleEditor").getContent();
	//查询Ol下删除按钮组
	var items=$("#answerOl").find("a");
	for(var i=0;i<items.length;i++){
		var item=items[i];
		//根据删除按钮获得上一个同胞button的linkageblock属性值
		var btnlinkageblock=$(item).prev().attr("linkageblock");
		//如果Editor中内容不包含属性值则删除对应空位
		if(oldText.indexOf(btnlinkageblock) < 0){
			removeNum.push($(item).attr("cid"));
			$(item).parent().remove();
		}
	}
}
//删除答案选项
function removeAnswerChild(obj){
	removeNum.push($(obj).attr("cid"));
	var btnlinkageblock=$(obj).prev().attr("linkageblock");
	var btnValue=$(obj).prev().attr("value");
	var liItem=$(obj).parent();
	liItem.remove();
	var oldText=UE.getEditor("answerTitleEditor").getContent();
	var newText=replaceTitleContent(oldText,btnlinkageblock);
	UE.getEditor("answerTitleEditor").setContent(newText);
	
}
//为删除填空选项时同时删除Editor中的选项构建Editor中的内容
function replaceTitleContent(content,rStr){
	var newContent=content;
	var strs=content.split("<input");
	for(var i=0;i<strs.length;i++){
		var str=strs[i];
		if(str.indexOf(rStr)>0){
			str=str.substr(0,str.indexOf("/>")+2);
			var inputStr="<input"+str;
			newContent=newContent.replace(inputStr,"");
		}
	}
	return newContent;
}
//为保存，构建题干Editor中的内容
function replaceTitleContentForSave(content){
	var newContent=content;
	var strs=content.split("<input");
	for(var i=0;i<strs.length;i++){
		var str=strs[i];
		var lastIndex=str.indexOf("/>");
		if(lastIndex!==-1){
			str=str.substr(0,lastIndex+2);
			var inputStr="<input"+str;
			newContent=newContent.replace(inputStr,"（）");
		}
	}
	return newContent;
}
//为保存，构建题干Editor中的内容
function replaceTitleContentFromDate(content){
	var newContent="";
	var strs=content.trim().replace("()","（）").split("（）");
	for(var i=0;i<strs.length;i++){
		var num=i+1;
		if(i<strs.length-1){
			newContent+=strs[i]+"<input type='button' style='width:80px' disabled='disabled' linkageblock='options:"+num+"' value='填空"+num+"'/>";
		}else{
			newContent+=strs[i];
		}
	}
	return newContent;
}
//点击tab标签跳转页面
function selectTab(title,index){
	if(index==0){
		window.location.href=baseUrl+"/admin/T010/editQsn.html?type=add&qsnid=0";
	}else if(index==1){
		window.location.href=baseUrl+"/admin/T010/editSelectQsn.html?type=add&qsnid=0";
	}else if(index==2){
		window.location.href=baseUrl+"/admin/T010/editJudgeQsn.html?type=add&qsnid=0";
	}else  if(index==4){
		window.location.href=baseUrl+"/admin/T010/editShortAnswerQsn.html?type=add&qsnid=0";
	}else if(index==5){
		window.location.href=baseUrl+"/admin/T010/editReadQsn.html?type=add&qsnid=0";
	}
}
//保存试题信息
function saveQsn(type) {
	if(type == 1) 
		isClose = true;
	else 
		isClose = false;
	var error = 0;//错误数
	var obj = {};//声明选项MAP
//	var answer = 0;//初始选项答案
	var titleContent = UE.getEditor("answerTitleEditor").getContent();
	var titleContentTxt = UE.getEditor("answerTitleEditor").getContentTxt();
	var detailContent = UE.getEditor("answerEditor").getContent();
	var liitems = $("#answerOl").children("li");
	for ( var i = 0; i < liitems.length; i++) {
		//循环li数组 获取按钮对象
		var txtItem = $(liitems[i]).children("input.input_user_info");
		var answer = "";
		for(var j=0;j<txtItem.length;j++){
			if(j==0){
				answer+=$(txtItem[j]).val();
			}else{
				answer+="#"+$(txtItem[j]).val();
			}
		}
		var objchild = {
			"key" : ""
		};
		//组织选项json数据
		objchild.key = answer;
		obj[(i + 1) + ""] = objchild;
	}
	
	if (liitems.length <= 0) {
		$("#answer_daan_content").html("请添加答案！");
		error++;
	} else {
		$("#answer_daan_content").html("");
	}
	if (titleContentTxt == "" || titleContentTxt == null) {
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
	var title=replaceTitleContentForSave(titleContent);
	$("#rightKeyValue").val(JSON.stringify(obj));
	$("#titleTextValue").val(title);
	$("#titleValue").val(title);
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
	.input_user_info{
		border:1px solid #c3d9e0;
		width:200px;
		height:32px;
	}
	ol li{
		padding:5px;
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
			<span id='answer_daan_content' style='color:red;'></span>
			<br/>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAnswerChild()" style="width:100px;height:30px;">增加空格</a>
			<ol id="answerOl">
			</ol>
			<br/>
		</div><br/>
		<div class="easyui-panel panel_qsn"    
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<span class="lable_span">答题解析</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span id='answer_content' style='color:red;'></span>
			<br/><br/>
			<textarea id="answerEditor" name="content" style="width:90%;height:50px;" > </textarea>
		</div><br/>
		<div class="easyui-panel"    
			style="width:95%;background:#fafafa;padding:10px;text-align: center;border: 0px;"  
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<input type="hidden" id="titleValue" name="title"/>
			<input type="hidden" id="titleTextValue" name="titleText"/>
			<input type="hidden" id="detailValue" name="detail"/>
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