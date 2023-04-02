<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加试题</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var type = '${type }';
	var questionId = '${questionId }';
	var title = '${typeName}';
	/* var param = "?1=1";
	if(type == 'edit' && questionId != '')
		param += "&type=edit&questionId=" + questionId; */
	var questionId = "${questionId}";
	var param = "";
	if(questionId >= 0) {
		param = "?type=edit&questionId=${questionId }";
	}
	
	$('#tabs').tabs({ 
		onSelect:function(title,index){
			$("#danxuan").html('');
			$("#duoxuan").html('');
			$("#panduan").html('');
			$("#tiankong").html('');
			$("#biancheng").html('');
			if(title == '单选题') {
				var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStDanxuan.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
				$("#danxuan").html(html);
			} else if(title == '多选题') {
				var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStDuoxuan.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
				$("#duoxuan").html(html);
			} else if(title == '判断题') {
				var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStPanduan.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
				$("#panduan").html(html);
			} else if(title == '填空题') {
				var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStTiankong.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
				$("#tiankong").html(html);
			} else if(title == '编程题') {
				var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStBiancheng.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
				$("#biancheng").html(html);
			}
			
		}
	});
	var html = "<iframe src='${pageContext.request.contextPath }/admin/shiti/editStDanxuan.html" + param + "' scrolling='auto' frameborder='0'></iframe>";
	$("#danxuan").html(html);
	if(type == 'edit') {
		//修改试题
		if(title == '不定项选择题')
			title = '多选题';
		if ($('#tabs').tabs('exists', title))
			$('#tabs').tabs('select', title);
		if(type == 'edit') {
			if(title == '单选题') {
				$('#tabs').tabs('disableTab', 1);
				$('#tabs').tabs('disableTab', 2);
				$('#tabs').tabs('disableTab', 3);
				$('#tabs').tabs('disableTab', 4);
			} else if(title == '多选题') {
				$('#tabs').tabs('disableTab', 0);
				$('#tabs').tabs('disableTab', 2);
				$('#tabs').tabs('disableTab', 3);
				$('#tabs').tabs('disableTab', 4);
			} else if(title == '判断题') {
				$('#tabs').tabs('disableTab', 0);
				$('#tabs').tabs('disableTab', 1);
				$('#tabs').tabs('disableTab', 3);
				$('#tabs').tabs('disableTab', 4);
			} else if(title == '填空题') {
				$('#tabs').tabs('disableTab', 0);
				$('#tabs').tabs('disableTab', 1);
				$('#tabs').tabs('disableTab', 2);
				$('#tabs').tabs('disableTab', 4);
			} else if(title == '编程题') {
				$('#tabs').tabs('disableTab', 0);
				$('#tabs').tabs('disableTab', 1);
				$('#tabs').tabs('disableTab', 2);
				$('#tabs').tabs('disableTab', 3);
			}
		}
	}
	
});
function closeThisTab() {
	parent.closeSelectTab();
}
function redirect(url) {
	window.location = url;
	parent.closeTab('试题管理');
	parent.addTab('试题管理', url);
	parent.closeTab('编辑试题');
}
</script>
<style type="text/css">
#allDiv {
	position: absolute;
	left: 0px;
	right: 0px;
	top: 0px;
	bottom: 0px;
	height: 100%;
}
iframe {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
<div id="allDiv">
<div id="tabs" class="easyui-tabs" style="width: 100%;height: 100%;padding: 0px;">
	<div id="danxuan" title="单选题">
		<%-- <iframe id="danxan" src="${pageContext.request.contextPath }/admin/shiti/editStDanxuan.html
		<c:if test="${questionId >= 0 }">?type=edit&questionId=${questionId }</c:if>
		" scrolling="auto" frameborder="0"></iframe> --%>
	</div>
	<div id="duoxuan" title="多选题">
		<%-- <iframe id="duoxuan" src="${pageContext.request.contextPath }/admin/shiti/editStDuoxuan.html
		<c:if test="${questionId >= 0 }">?type=edit&questionId=${questionId }</c:if>
		" scrolling="auto" frameborder="0"></iframe> --%>
	</div>
	<div id="panduan" title="判断题">
		<%-- <iframe id="panduan" src="${pageContext.request.contextPath }/admin/shiti/editStPanduan.html
		<c:if test="${questionId >= 0 }">?type=edit&questionId=${questionId }</c:if>
		" scrolling="auto" frameborder="0"></iframe> --%>
	</div>
	<div id="tiankong" title="填空题">
		<%-- <iframe id="tiankong" src="${pageContext.request.contextPath }/admin/shiti/editStTiankong.html
		<c:if test="${questionId >= 0 }">?type=edit&questionId=${questionId }</c:if>
		" scrolling="auto" frameborder="0"></iframe> --%>
	</div>
	<div id="biancheng" title="编程题">
	</div>
</div>
</div>
</body>
</html>