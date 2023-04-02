<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>考试试卷分类管理</title>
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
<div class="easyui-tabs" style="width: 100%;height: 100%;padding: 0px;">
	<div title="试题分类">
		<iframe scrolling="auto" src="${pageContext.request.contextPath }/admin/setting/stClassifyManager.html" frameborder="0"></iframe>
	</div>
	<div title="试题难度分类">
		<iframe scrolling="auto" src="${pageContext.request.contextPath }/admin/setting/stLevelManager.html" frameborder="0"></iframe>
	</div>
	<div title="试题知识点分类">
		<iframe scrolling="auto" src="${pageContext.request.contextPath }/admin/setting/stKnowledgeManager.html" frameborder="0"></iframe>
	</div>
	<div title="试卷分类">
		<iframe scrolling="auto" src="${pageContext.request.contextPath }/admin/setting/sjClassifyManager.html" frameborder="0"></iframe>
	</div>
	<div title="考试分类">
		<iframe scrolling="auto" src="${pageContext.request.contextPath }/admin/setting/ksClassifyManager.html" frameborder="0"></iframe>
	</div>
</div>
</div>
</body>
</html>