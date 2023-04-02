<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/dashboard.css" rel="stylesheet" /> 
<!-- <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-2.0.0.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.bootstrap.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.dialog.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap/js/jquery.messager.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/notReadCount.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/myResult.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<jsp:include page="./include/top.jsp" />

<div id="content">
	
	<div class="container">
		
		<div class="row">
			
			<jsp:include page="./include/leftMenu.jsp" />
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-th-list"></i>
					我的成绩
				</h1>
				
				<!-- <div class="stat-container">
										
					<div class="stat-holder">						
						<div class="stat">							
							<span>564</span>							
							Completed Sales							
						</div> /stat						
					</div> /stat-holder
					
					<div class="stat-holder">						
						<div class="stat">							
							<span>423</span>							
							Pending Sales							
						</div> /stat						
					</div> /stat-holder
					
					<div class="stat-holder">						
						<div class="stat">							
							<span>96</span>							
							Returned Sales							
						</div> /stat						
					</div> /stat-holder
					
					<div class="stat-holder">						
						<div class="stat">							
							<span>2</span>							
							Chargebacks							
						</div> /stat						
					</div> /stat-holder
					
				</div> /stat-container -->
				
				<!-- <div class="widget">
										
					<div class="widget-header">
						<i class="icon-signal"></i>
						<h3>Area Chart</h3>
					</div> /widget-header
														
					<div class="widget-content">					
						<div id="bar-chart" class="chart-holder"></div> /bar-chart				
					</div> /widget-content
					
				</div> /widget -->
				
				
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
					<div class="widget-content table-response" style="background-color: white;">
						<table id="resultTable"></table>
					
					</div> <!-- /widget-content -->
					
				</div> <!-- /widget -->
				
			</div>
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
<jsp:include page="./include/footer.jsp"></jsp:include>

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>