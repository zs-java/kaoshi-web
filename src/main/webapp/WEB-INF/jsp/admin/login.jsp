<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/login/css/style.css" tppabs="css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${pageContext.request.contextPath }/resources/js/login/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/login/js/verificationNumbers.js" tppabs="js/verificationNumbers.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/login/js/Particleground.js" tppabs="js/Particleground.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	if (window != top) 
		top.location.href = location.href; 
	//粒子背景特效
	$('body').particleground({
		dotColor: '#5cbdaa',
		lineColor: '#5cbdaa'
	});
	
	var message = '${message }';
	if(message != null && message != '')
		alert(message);
	
});
function login() {
	$("#loginForm").submit();
}
$(document).keyup(function (e) {//捕获文档对象的按键弹起事件  
    if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了  
        //此处编写用户敲回车后的代码  
        login();
    }  
});
</script>
<title>管理员登陆</title>
</head>
<body>
<form id="loginForm" action="${pageContext.request.contextPath }/admin/login.action" method="post">
<dl class="admin_login">
 <dt>
  <strong>在线考试系统后台登陆</strong>
  <em>Online Exam System Admin Login Page</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" name="username" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" name="password" class="login_txtbx"/>
 </dd>
 <dd>
  <input type="button" onclick="login();" value="立即登陆" class="submit_btn"/>
 </dd>
 <dd>
<p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
<p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
 </dd>
</dl>
</form>
</body>
</html>