<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/temp.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resources/css/top.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resources/js/top.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/js/user/goExam.js" type="text/javascript"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
var kuId = "${ksUser.id}";
var sjId = "${ksData.sjId}";
var score = parseFloat("${score}");
var dnandata = ${gdStr};
//错题集合
var errorShiTi=${stIds};

var publishTime = "${ksData.resultPublishTimeLong}";

var qsnRandomFlg = "${ksData.qsnRandomFlg}";
var optionRandomFlg = "${ksData.optionRandomFlg}";
var pageModel = "${ksData.pageSize}";
var selectFlg = "${ksData.selectFlg}";
</script>
<style type="text/css">
body{
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
a{
	COLOR: #fff;
	cursor: pointer;
	TEXT-DECORATION: none;
}
a:visited{
	COLOR: #fff; 
}
#Container ol {
	padding:0px;
	margin-left:30px;
}
#Container ol li {
	width:600px;
	list-style-type: upper-alpha;
	*list-style-type: upper-alpha;
	height:0 auto;
}
.pop-mask {
	z-index: 9997;
	position:fixed;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background:#000;
	opacity:0.4;
	filter:alpha(opacity=40);
	display:none
}
.pop{position:fixed;z-index:9998;left:37%;top:25%;margin-left:width/2;margin-top:height/2;margin:auto;display: none;}
</style>
</head>
<body oncontextmenu="return false"  onselectstart="return false" ondragstart="return false" onbeforecopy="return false">
<div id="wapper">
	<div class="contentsList">
		<div class="testTop">
			<input id="error" type="hidden" value="${error}"/>
			<dl>
				<!-- 考试名称 -->
				<dt id="sjTitle">考试名称：${ksData.name}</dt>
				<dd><input id="time" value="${time}" type="hidden"/><a id="submitButton" href="javascript:;" onclick="submitShiJuan('hand');" ><img src="${pageContext.request.contextPath }/resources/img/jiaojuan.png" width="83" height="30" alt="" /></a></dd>
				<dd><a href="javascript:void(0);" id="saveButton" onclick="saveShiJuan();"><img src="${pageContext.request.contextPath }/resources/img/baochun.png" width="83" height="30" alt="" /></a></dd>
				<!-- <dd><button onclick="saveShiJuan();">保存</button></dd> -->
				<!-- 倒计时 -->
				<dd id="countDown"></dd>
			</dl>
		</div>
		<div class="testNy">
			<div class="testNyLeft">
				<h2>答题卡</h2>
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
						<dd>标记</dd>
					</dl>
				</div>
				<div id="datika" style="width: 280px;height: 400px;overflow-y:auto;">
				
				</div>
			</div>
			<form id="form1" action="mta/F020/submitExam.html" method="post">
				<input id="scorePublishFlg" type="hidden" value="${scorePublishFlg}"/>
				<input name="sjId" id="sjId" type="hidden" value="${ksData.sjId}"/>
				<input name="ksId" id="ksId" type="hidden" value="${ksData.ksId}"/>
				<input name="okrate" id="okrate" type="hidden" value="${ksData.passScore}"/>
				<input name="gdStr" id="gdStr" type="hidden"/>
				<input name="sjStr" id="sjStr" type="hidden"/>
				<input name="ksUuid" id="ksUuid" type="hidden" value="${ksData.uuid }"/>
				<input name="qsnRandomFlg" id="qsnRandomFlg" type="hidden" value="${qsnRandomFlg}"/>
				<input name="credit" id="credit" type="hidden" value="${credit}"/>
			</form>
			<!-- 试题 -->
			<div id="Container" class="testNyRight"></div>
			<div id="button" class="testNyRight" style="display:none;">
				<ul>
					<li id="up"><a href="javascript:;" onclick="up();" ><img src="${pageContext.request.contextPath }/resources/img/pre.png" width="77" height="35" alt="上一题" /></a></li>
					<li id="down"><a href="javascript:;" onclick="down();"><img src="${pageContext.request.contextPath }/resources/img/next.png" width="77" height="35" alt="下一题" /></a></li>
				</ul> 
			</div>
		</div>
	</div>
</div>
<!--start 弹出页-->
<div id="chengjiDiv" class="kaoshi pop">
	<dl>
		<dt>考试名称：${ksData.name}</dt>
		<dt>总分：${ksData.sjDataCustom.totalScore}</dt>
		<dt>及格分：${ksData.passScore}</dt>
		<dt id="defen"></dt>
		<dd class="blue"><a id="bluetext" href="${pageContext.request.contextPath }/user/myResult.html">查看成绩</a></dd>
		<dd class="org"><a href="${pageContext.request.contextPath }/user/center.html">个人中心</a></dd>
	</dl>
</div>
<!--end 弹出页-->

<!--start 弹出页-->
<div id="savePop" class="kaoshi pop">
	<dl style="width:300px;height: 100px;padding: 0px;">
		<dt style="width:270px;height:100px;text-align: center;line-height: 100px;"><h1>保存成功!</h1></dt>
	</dl>
</div>
<!-- start 返回顶部 -->
<div class="floating_ck">
	<dl>
		<dt></dt>
		<dd class="return">
			<span onClick="gotoTop();return false;">返回顶部</span>
		</dd>
	</dl>
</div>
<!-- end 返回顶部 -->
<!-- 进度 --> 
<div id="loading" class="pop">
	<img src="${pageContext.request.contextPath }/resources/img/loading2.gif" style="background-color: transparent;" alt="" />
</div>
<!-- 停止考试 -->
<div class="pop-mask"></div>
<div id="stopDiv" class="kaoshi pop">
	<dl>
		<dt>考试已经被暂停，此时其他的操作都是无效操作，请耐心等待......</dt>
	</dl>
</div>
</body>
</html>