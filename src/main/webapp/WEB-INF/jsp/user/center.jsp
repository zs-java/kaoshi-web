<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
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
					心路历程				
				</h1>
				
				<div class="widget">
              <!-- <div class="row"> -->
                  <div class="" style="width: 100%;">
                  
                  	<!-- <div class="row mtbox"> --><!-- col-md-offset-1 -->
                  		<div style="float: left;">
                  		<div class="box0" style="width: 142.36px;height: 219px;margin-left: 25px;">
                  			<div class="box1">
					  			<span class="li_star"></span>
					  			<br/><br/>
					  			<h2>${user.loginCount }</h2>
                  			</div>
					  			<p>&nbsp;&nbsp;&nbsp;&nbsp;不知不觉中您已经${user.loginCount }次登录本系统，感谢您对创作团队的支持！</p>
                  		</div>
                  		</div>
                  		<div style="float: left;">
                  		<div class="col-md-2 col-sm-2 box0" style="width: 142.36px;height: 219px;margin-left: 25px;">
                  			<div class="box1">
					  			<span class="li_display"></span>
					  			<br/><br/>
					  			<h2>${examCount }</h2>
                  			</div>
					  			<p>&nbsp;&nbsp;&nbsp;&nbsp;共有${examCount }场与您相关的考试~</p>
                  		</div>
                  		</div>
                  		<div style="float: left;">
                  		<div class="col-md-2 col-sm-2 box0" style="width: 142.36px;height: 219px;margin-left: 25px;">
                  			<div class="box1">
					  			<span class="li_vallet"></span>
					  			<br/><br/>
					  			<h2>
					  				<c:choose>
					  					<c:when test="${doQsnCount eq null}">0</c:when>
					  					<c:otherwise>${doQsnCount }</c:otherwise>
					  				</c:choose>
					  			</h2>
                  			</div>
					  			<p>
					  				<c:choose>
					  					<c:when test="${doQsnCount eq null }">您还没有做任何题目，不要偷懒哦~</c:when>
					  					<c:otherwise>
					  						&nbsp;&nbsp;&nbsp;&nbsp;截至当前您已经做了${doQsnCount }道题目，加油！
					  					</c:otherwise>
					  				</c:choose>
					  			</p>
                  		</div>
                  		</div>
                  		<div style="float: left;">
                  		<div class="col-md-2 col-sm-2 box0" style="width: 142.36px;height: 219px;margin-left: 20px;">
                  			<div class="box1">
					  			<span class="li_news"></span>
					  			<br/><br/>
					  			<h2>
					  				<c:choose>
					  					<c:when test="${wrongCount == 0 }">
					  						<!-- <i class="icon-ok-circle icon-white"></i> -->
					  						<span class="icon-thumbs-up" style="font-size: 35px;color: green;"></span>
					  					</c:when>
					  					<c:otherwise>${wrongCount }</c:otherwise>
					  				</c:choose>
					  			</h2>
                  			</div>
					  			<p>
					  				<c:choose>
					  					<c:when test="${wrongCount == 0 }">
					  						&nbsp;&nbsp;&nbsp;&nbsp;恭喜你，您已经消除了所有错题，厉害啊~
					  					</c:when>
					  					<c:otherwise>
					  						&nbsp;&nbsp;&nbsp;&nbsp;共${wrongCount }道错题等待你消灭，快去消灭它们吧！
					  						</c:otherwise>
					  				</c:choose>
					  			</p>
                  		</div>
                  		</div>
                  		<div style="float: left;">
                  		<div class="col-md-2 col-sm-2 box0" style="width: 142.36px;height: 219px;margin-left: 20px;">
                  			<div class="box1">
					  			<span class="li_bubble"></span>
					  			<br/><br/>
					  			<h2>OK!</h2>
                  			</div>
					  			<p>消息系统正在努力开发中~</p>
                  		</div>
                  		</div>
                  	
                  	<!-- </div> --><!-- /row mt -->
                      <div style="clear: both;"></div>
                  </div> <!-- /col-lg-9 END SECTION MIDDLE
                      
              <!-- </div> --> <!--/row -->

      <!--main content end-->
				
				</div>
				<!-- <div class="widget">
					
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>Line Chart</h3>
					</div> /widget-header
														
					<div class="widget-content">
						
						<div id="line-chart" class="chart-holder"></div> /donut-chart
						
					</div> /widget-content
					
				</div> /widget
				 -->
				<!-- --------------------------------------------------------------------------------- -->
				<div class="widget widget-table">
				
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>刷题记录信息</h3>
					</div> <!-- /widget-header -->
					
					<div class="widget-content">
					
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>日期</th>
									<th>试题分类</th>
									<th>知识点分类</th>
									<th>刷题总数</th>
									<th>正确数</th>
									<th>正确率</th>
									<th>开始时间</th>
									<th>刷题时长 </th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${exers }" var="exer">
								<tr>
									<td><fmt:formatDate value="${exer.dayDate }" pattern="yyyy-MM-dd"/></td>
									<td>${exer.classifyName }</td>
									<td>${exer.knowledgeName }</td>
									<td>${exer.totalCount }</td>
									<td>${exer.rightCount }</td>
									<td>${exer.rightPercent }</td>
									<td>
										<fmt:formatDate value="${exer.insDate }" pattern="yyyy-MM-dd"/><br/>
										<fmt:formatDate value="${exer.insDate }" pattern="HH:mm:ss"/>
									</td>
									<td>${exer.time }分钟</td>
								</tr>
								</c:forEach>
								<!-- <tr>
									<td>1</td>
									<td>Michael</td>
									<td>Jordan</td>
									<td>@mjordan</td>
									<td>Chicago Bulls</td>
									<td class="action-td">
										<a href="javascript:;" class="btn btn-small btn-warning">
											<i class="icon-ok"></i>								
										</a>					
										<a href="javascript:;" class="btn btn-small">
											<i class="icon-remove"></i>						
										</a>
									</td>
								</tr> -->
							</tbody>
							<tfoot>
								<tr>
								<td colspan="8" align="right">
									<form name="frm" action="${pageContext.request.contextPath }/user/center.html" method="post">
										<input type="hidden" name="pc" value="${pb.pageCode }" />
										<input type="hidden" name="tp" value="${pb.totalPage }" />
									</form>
									<input type="button" class="btn" onclick="doFirst();" value="首页" />
									<c:if test="${pb.pageCode != 1 }">
										<input type="button" class="btn" onclick="doBefore();" value="上一页" />
									</c:if>
									${pb.pageCode }
									<c:if test="${pb.pageCode != pb.totalPage }">
										<input type="button" class="btn" onclick="doNext();" value="下一页" />
									</c:if>
									<input type="button" class="btn" onclick="doLast();" value="尾页" />
									<script type="text/javascript">
									function doFirst() {
										frm.pc.value = 1;
										frm.submit();
									}
									function doBefore() {
										frm.pc.value = parseInt(frm.pc.value) - 1;
										frm.submit();
									}
									function doNext() {
										frm.pc.value = parseInt(frm.pc.value) + 1;
										frm.submit();
									}
									function doLast() {
										frm.pc.value = frm.tp.value;
										frm.submit();
									}
									</script>
								</td>
								</tr>
							</tfoot>
						</table>
					
					</div> <!-- /widget-content -->
					
				</div> <!-- /widget -->
				<!-- --------------------------------------------------------------------------------- -->
				
				
				<%-- <div style="padding: 10px;">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myExam.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myExam.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myResult.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myResult.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myMessage.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myMessage.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					
					
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;margin-top: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myExam.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myExam.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;margin-top: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myResult.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myResult.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc" style="float: left;margin-left: 25px;margin-top: 25px;">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="fancybox" href="${pageContext.request.contextPath }/resources/img/myMessage.png"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/img/myMessage.png" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 --> --%>
					
					
					<div style="clear: both;"></div>
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
<script src="${pageContext.request.contextPath }/resources/js/user/line.js"></script>
<script type="text/javascript">
      $(function() {
        //    fancybox
          jQuery(".fancybox").fancybox();
      });

  </script>
  
  <script>
      //custom select box

      $(function(){
          $("select.styled").customSelect();
      });

  </script>

</body>
</html>