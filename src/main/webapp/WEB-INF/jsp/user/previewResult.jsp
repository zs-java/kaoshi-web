<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/dashboard.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/user/myResult.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/temp.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/top.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resources/js/top.js" type="text/javascript"></script>
<!-- <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-2.0.0.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.bootstrap.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.dialog.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.messager.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/notReadCount.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/previewResult.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
var kuId = "${kuId}";
</script>
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
						<a href="#"><span id="notReadAll"></span></a>
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


<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >北大青鸟</a></div>

<div id="content">
	
	<div class="container">
		
		<div class="row">
			
			<div class="span3">
				
				<div class="account-container">
				
					<div class="account-avatar">
						<img src="/pic/${user.icon }" alt="" class="thumbnail" />
					</div> <!-- /account-avatar -->
				
					<div class="account-details">
					
						<span class="account-name">${user.username }</span>
						
						<span class="account-role">${user.roleCustom.roleName }</span>
						
						<span class="account-actions">
							<a href="javascript:;">简介</a> |
							
							<a href="javascript:;">设置</a>
						</span>
					
					</div> <!-- /account-details -->
				
				</div> <!-- /account-container -->
				
				<hr />
				
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					
					<li>
						<a href="${pageContext.request.contextPath }/user/center.html">
							<i class="icon-home"></i>
							首页 		
						</a>
					</li>
					
					<li>
						<a href="${pageContext.request.contextPath }/user/myExam.html">
							<i class="icon-pushpin"></i>
							我的考试	
							<span id="notReadKs"></span>
						</a>
					</li>
					
					<li  class="active">
						<a href="${pageContext.request.contextPath }/user/myResult.html">
							<i class="icon-th-list"></i>
							我的成绩	
							<span id="notReadResult"></span>	
						</a>
					</li>
					
					<li>
						<a href="./grid.html">
							<i class="icon-th-large"></i>
							我的练习	
							<span></span>
						</a>
					</li>
					
					<li>
						<a href="./charts.html">
							<i class="icon-signal"></i>
							我的消息	
							<span></span>
						</a>
					</li>
					
					<li>
						<a href="./account.html">
							<i class="icon-user"></i>
							用户设置							
						</a>
					</li>
					
					<li>
						<a href="./login.html">
							<i class="icon-lock"></i>
							全部测试	
						</a>
					</li>
					
				</ul>	
				
				<hr />
				
				<div class="sidebar-extra">
					<p><!-- Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. --></p>
				</div> <!-- .sidebar-extra -->
				
				<br />
		
			</div> <!-- /span3 -->
			
			
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-th-list"></i>
					我的成绩
				</h1>
				
				<div class="widget widget-table">
										
					<!-- <div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>Table</h3>
					</div> /widget-header -->
					<!-- <div id="toolbar" class="btn-group" style="width: 100%;margin-left: 20px;background-color: white;">
						<button id="allBtn" name="all" onclick="search(this);" class="btn btn-round btn-default">全部</button>
						<button id="unPassBtn" name="unPass" onclick="search(this);" class="btn btn-round btn-success">未报名</button>
						<button id="onPassBtn" name="onPass" onclick="search(this);" class="btn btn-round btn-primary">报名报名审核中</button>
						<button id="notStartBtn" name="notStart" onclick="search(this);" class="btn btn-round btn-info">即将开始</button>
						<button id="startingBtn" name="starting" onclick="search(this);" class="btn btn-round btn-warning">正在考试</button>
						<button id="startedBtn" name="started" onclick="search(this);" class="btn btn-round btn-danger">已结束</button>
					</div> -->
					<div class="widget-content table-response" style="background-image: url(${pageContext.request.contextPath}/resources/img/bodybg.jpg);">
						<div class="Rightmeus sjtitle_style30" style="width:95%;border-top:0px;">
							&nbsp;&nbsp;&nbsp;&nbsp;
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
						<%-- <c:import url="previewMyExam.jsp"></c:import> --%>
						<%-- <iframe style="width:100%;" src="${pageContext.request.contextPath }/user/previewMyExam.html?kuId=${kuId}" /> --%>
					</div> <!-- /widget-content -->
					
				</div> <!-- /widget -->
				
			</div>
	</div> <!-- /container -->
	
</div> <!-- /content -->
			
<!-- start 返回顶部 -->
<div class="floating_ck">
	<dl>
		<dt></dt>
		<dd class="return">
			<span onClick="gotoTop();return false;">返回顶部</span>
		</dd>
	</dl>
</div>		
	
<jsp:include page="./include/footer.jsp"></jsp:include>

    

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>