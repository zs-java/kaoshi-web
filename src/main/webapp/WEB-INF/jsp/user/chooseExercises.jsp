<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刷题练习-在线考试系统</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/dashboard.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/user/jquery.fancybox.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/user/center.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/css/lineicons/style.css" rel="stylesheet" /> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
</head>
<body>

<jsp:include page="./include/top.jsp" />

<div id="content">
	
	<div class="container">
		
		<div class="row">
			
			<jsp:include page="./include/leftMenu.jsp" />
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-tags"></i>
					刷题练习				
				</h1>
				
				<div class="widget">
					选择试题类型：
					<ul id="stClassifyTree"></ul>
					<hr/>
					选择试题知识点
					<ul id="stKnowledgeTree"></ul>
					<hr/>
					<button class="btn btn-primary" onclick="startExer();">开始刷题</button>
				</div>
				
				
			</div> <!-- /span9 -->
			
			
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->

<jsp:include page="./include/footer.jsp"></jsp:include>

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/excanvas.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.pie.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.orderBars.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/jquery.flot.resize.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/bootstrap/js/charts/bar.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/css/jquery.fancybox.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath }/resources/js/user/notReadCount.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/chooseExercises.js"></script>
<script type="text/javascript">
      $(function() {
        //    fancybox
          jQuery(".fancybox").fancybox();
      });

  </script>
  
  <script>
  </script>

</body>
</html>