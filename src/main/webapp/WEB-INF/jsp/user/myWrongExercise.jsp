<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的错题</title>
<link href="${pageContext.request.contextPath }/resources/css/temp.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/myWrongExercise.js" type="text/javascript"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
a{
	cursor: pointer;
}
#Container ol {
	margin-left:30px;
	width:5px;
}
#Container ol li {
	width:600px;
	list-style-type: upper-alpha;
	height:0 auto;
}
.pop{position:absolute;z-index:9998;margin:auto;display: none;}
</style>
</head>
<body>
<div id="wapper">
	<div class="contentsList">
		<div class="testTop">
			<dl>
				<!-- 考试名称 -->
				<dt id="sjTitle">练习名称：错题练习</dt>
			</dl>
		</div>
		<div class="testNy">
			<div class="testNyLeft">
				<%-- <h2>答题卡</h2>
				<div class="dtk">
					<dl>
						<dt><img src="${pageContext.request.contextPath }/resources/img/blue_pic01.png" width="23" height="23" alt="" /></dt>
						<dd>已答</dd>
					</dl>
					<dl>
						<dt><img src="${pageContext.request.contextPath }/resources/img/baise.png" width="23" height="23" alt="" /></dt>
						<dd>未答</dd>
					</dl>
					<dl>
						<dt><img src="${pageContext.request.contextPath }/resources/img/org.png" width="23" height="23" alt="" /></dt>
						<dd>已查看</dd>
					</dl>
				</div> --%>
				<div id="datika" style="width: 280px;height: 400px;overflow-y:auto;">
				
				</div>
				<div style="width: 230px;height: 30px;overflow-y: auto;font-size: 16px;background-color: #4DA6FF;padding-top: 10px;color: white;padding-left: 30px;">
				<a href="javascript:doBefore();">上一页</a>
				<a href="javascript:doNext();">下一页</a>
				第<span id="pcSpan"></span>页/共<span id="tpSpan"></span>页
				</div>
			</div>
			<!-- 试题 -->
			<div id="Container" class="testNyRight"></div>
		</div>
	</div>
</div>
</body>
</html>