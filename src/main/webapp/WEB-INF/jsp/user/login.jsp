<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/user/login/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/user/login/css/animate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/user/login/css/style.css">
<script type="text/javascript">
var msg = "${message}";
if(msg != null && msg != '') 
	alert(msg);
</script>
</head>
<body class="style-3">
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<!-- Start Sign In Form -->
			<form action="${pageContext.request.contextPath }/login.action" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn">
				<h2>在线考试系统</h2>
				<div class="form-group">
					<label for="username" class="sr-only">Username</label>
					<input type="text" class="form-control" id="username" name="username" placeholder="Username" autocomplete="off">
				</div>
				<div class="form-group">
					<label for="password" class="sr-only">Password</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="Password" autocomplete="off">
				</div>
				<!-- <div class="form-group">
					<label for="remember"><input type="checkbox" id="remember"> 记住密码</label>
				</div>
				<div class="form-group">
					<p>没有账号？ <a href="#"> 注册</a> | <a href="#">忘记密码？</a></p>
				</div> -->
				<div class="form-group">
					<input type="submit" value="登  录" class="btn btn-primary">
				</div>
			</form>
			<!-- END Sign In Form -->

		</div>
	</div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath }/resources/css/user/login/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath }/resources/css/user/login/js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="${pageContext.request.contextPath }/resources/css/user/login/js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="${pageContext.request.contextPath }/resources/css/user/login/js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="${pageContext.request.contextPath }/resources/css/user/login/js/main.js"></script>
</body>
</html>