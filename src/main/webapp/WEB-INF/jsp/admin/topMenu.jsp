<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="main">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	/* background-color: #0A6CB7; */
	color: white;
	border: 10px solid #C3D9E0; 
	overflow:scroll;
	overflow-x:hidden;
	overflow-y:hidden;
}
#all {
	position:absolute; left:0; top:0px; width:100%; height:100%; background:black/* #0A6CB7 */;
	border: 1px solid #C3D9E0; 
}
#title {
	float: left;
	position: relative;
	margin: 15px 20px 10px 40px;
	font-size: 25px;
	font-family: "微软雅黑";
}
#version {
	float: left;
	position: relative;
	margin: 30px 15px 10px 15px;
	font-size: 14px;
}
#welcome {
	float: left;
	position: absolute;
	margin-left: 1000px;
	margin-top: 17px;
	font-size: 13px;
}
#menu {
	/* float: left; */
	/* position: relative; */
	/* margin-left: 50px; */
}
#menu ul {
	list-style-type: none;
	float: left;
	margin-left: 950px;
	margin-top: 37px;
	margin-bottom: 0px;
	position: absolute;
	
}
#menu ul li {
	float: left;
	/* position: relative; */
	margin-left: 0px;
	font-size: 13px;
	/* padding-right: 7px; */
	padding: 3px 5px 3px 5px;
	margin-bottom: 0px;
	/* border: 1px solid black; */
}
.colsLine {
	padding: 0px;
	margin: 0px;
}
#menu ul li a {
	color: white;
	text-decoration: none;
}
.menuButton:HOVER {
	background-color: #9CC8F7;
}
</style>
</head>
<body>
	<div id="all">
		<div id="title">在线考试系统</div>
		<div id="version">v1.0</div>
		<div id="welcome">欢迎您：${user.username }</div>
		<div id="menu">
			<ul>
				<li class="menuButton"><a href="">首页</a></li>
				<li class="colsLine">|</li>
				<li class="menuButton"><a href="">修改密码</a></li>
				<li class="colsLine">|</li>
				<li class="menuButton"><a target="_top" href="${pageContext.request.contextPath }/admin/logout.action">退出登陆</a></li>
				
			</ul>
		</div>
	</div>
</body>
</html>