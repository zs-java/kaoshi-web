<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath }/resources/js/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resources/css/user/myResult.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/temp.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath }/resources/js/jquery-2.0.0.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/previewResult.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
var kuId = "${kuId}";
</script>
</head>
<body>
<div class="Rightmeus sjtitle_style30" style="width:95%;border-top:0px;">
	考试名称：<span id="ksName" name="ksName" style="margin-right:50px"></span>&nbsp;&nbsp;
	考试总分：<span id="totalsorce" name="ksTotal" style="margin-right:50px"></span>&nbsp;&nbsp;
	考试时间：<span id="startTm" name="startTm" ></span>~<span id="endTm" name="endTm" style="margin-right:50px"></span>&nbsp;&nbsp;
	得分：<span id="score" name="score" style="margin-right:50px"></span>&nbsp;&nbsp;&nbsp;
	<input type="hidden"  id="userId" name="userId" value="${sessionScope.user.id}"></input>
	<input type="hidden" id="ksid1" name="ksid" value="${ksid}"></input>
	<input type="hidden" id="id" name="id" value="${id}"></input>
	<input type="hidden" id="sjid" name="sjid" value="${sjid}"></input>
</div>
<br/>
<div id="Container"></div>
</body>
</html>