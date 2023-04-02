<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="main">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能菜单</title>
<style type="text/css">
body {
	overflow-x:hidden;
	overflow-y:hidden;	
}
#all {
	position:absolute; left:0; top:0px; width:100%; height:100%; background:#9CC8F7;
	border: 1px solid black; 
}
ul {
	list-style-type: none;
	/* border: 1px solid black; */
}
#moduleUl{
	margin-left: 5px;
	padding-left: 5px;
}
a {
	text-decoration: none;
}
.moduleFont {
	color: black;
	font-weight: 500;
	font-family: "微软雅黑";
}
.functionFont {
	color: white;
	font-family: "微软雅黑";
}
</style>
</head>
<body>
<div id="all">
	<c:if test="${fn:length(moduleCustomList) > 0 }">
	<ul id="moduleUl">
		<c:forEach items="${moduleCustomList }" var="moduleCustom">
		<li>
			<a href="" class="moduleFont">${moduleCustom.name }</a>
			<c:if test="${fn:length(moduleCustom.functions) > 0 }">
			<ul style="list-style-type: none;">
			<c:forEach items="${moduleCustom.functions }" var="function">
				<li><a href="${pageContext.request.contextPath }/${function.url }" class="functionFont" >${function.name }</a></li>
			</c:forEach>
			</ul>
			</c:if>
		</li>
		</c:forEach>
	</ul>
	</c:if>
	
</div>
</body>
</html>