<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷预览</title>
<link href="${pageContext.request.contextPath }/resources/js/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resources/css/admin/previewSj.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/easyui/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/admin/previewSj.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/ueditor.parse.min.js" type="text/javascript"></script>
<script type="text/javascript">
var sjId = "${sjId}";
var baseUrl = "${pageContext.request.contextPath}";
$(function() {
	
});
</script>
</head>
<body class="easyui-layout">
<div region="north" split="false" style="height:38px;padding:0px;border: 0px;background-image: url(${pageContext.request.contextPath}/resources/img/bodybg.jpg);" />
	<div id="sjTitle" class="sjtitle_style"></div>
</div>
<div region="center" class="center_view">
	<div id="Container">
	</div>
</div>
</body>
</html>