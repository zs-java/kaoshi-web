<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试系统</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/dashboard.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/user/joinExam.css" rel="stylesheet" /> 
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
var beginDateString = "<fmt:formatDate value='${ksDataCustom.beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>";
</script>
<style type="text/css">
.button{
	width:135px;
	height: 50px;
	background: rgb(248, 144, 21);
	text-align: center;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius:6px;
	cursor: pointer;
}
.button a{
	color: rgba(251, 248, 248, 0.98);
	display: inline-block;
	height: 50px;
	line-height: 50px;
	text-decoration:none;
	font-weight: bold;
	font-size: 17px;
	font-family: "微软雅黑";
}
#wapper {
	margin-top:100px;
}
</style>
</head>
<body>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			<a class="brand" href="./">在线考试系统</a>
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li>
						<a href="#"><span class="badge badge-warning">7</span></a>
					</li>
					<li class="divider-vertical"></li>
					<li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle " href="#">
							${user.username }<b class="caret"></b>							
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="./account.html"><i class="icon-user"></i> 账号设置  </a>
							</li>
							<li>
								<a href="./change_password.html"><i class="icon-lock"></i> 修改密码</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="${pageContext.request.contextPath }/loginout.action"><i class="icon-off"></i> 退出</a>
							</li>
						</ul>
					</li>
				</ul>
			</div> <!-- /nav-collapse -->
		</div> <!-- /container -->
	</div> <!-- /navbar-inner -->
</div> <!-- /navbar -->

<div id="wapper">
	<!--start contentsMain-->
	<div class="contentsMain">
		<!--start mainTop-->
		<div class="mainTop">
			<ul>
			<!-- kecheng.png -->
				<li><img src="/pic/${ksDataCustom.pic }" style="width: 488px;height: 279px;" alt="${ksDataCustom.name }" /></li>
			</ul>
			<dl id="info">
				<dt>考试名称：${ksDataCustom.name }</dt>
				<dd>考试开始时间:${ksDataCustom.beginTimeString }</dd>
				<dd>考试结束时间:${ksDataCustom.endTimeString }</dd>
				<dd>总分：${ksDataCustom.sjDataCustom.totalScore }</dd>
				<dd>及格分：${ksDataCustom.passScore }</dd>
				<dd>
					<!-- 学分：0
					<input type='hidden' value='yjs'/> -->
				</dd>
				
				<dd id="time">
					倒计时：
					<span id="day_show">0天</span>
					<span id="hour_show">0时</span>
					<span id="minute_show">0分</span>
					<span id="second_show">0秒</span>
				</dd>
				<dd></dd><dd></dd>
				<dd>
					<c:choose>
					<c:when test="${ksUser.state >= 4 }">
						<div class="button btn btn-primary">
							<a href="javascript:void(0);" >考试完毕</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="button btn btn-info">
							<a href="javascript:void(0);" onclick='beginExam(${ksDataCustom.ksId}, ${ksUser.id});'>立即考试</a>
						</div>
					</c:otherwise>
					</c:choose>
					
				</dd>
			</dl>
		</div>
		<div class="mainCenter">
			<div class="mainNavi">
				<ul>
					<li class="selected"><a>考试须知</a></li>
				</ul>
			</div>
			<div id="content">
				<div class="catalog">
					${ksDataCustom.des }
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./include/footer.jsp"></jsp:include>

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.bootstrap.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.pie.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.orderBars.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.resize.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/bootstrap/js/charts/bar.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/js/user/joinExam.js"></script>
</body>
</html>