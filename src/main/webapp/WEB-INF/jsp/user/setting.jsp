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
<link href="${pageContext.request.contextPath }/resources/css/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/dashboard.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resources/bootstrap/css/pages/plans.css" rel="stylesheet" />
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
					<i class="icon-th-large"></i>
					用户设置					
				</h1>
				
				
				<div class="row">
					
					<div class="span9">
				
						<div class="widget">
							
							<div class="widget-header">
								<h3>基本信息</h3>
							</div> <!-- /widget-header -->
									
							<div class="widget-content">
								
								
								
								<div class="tabbable">
						<ul class="nav nav-tabs">
						  <li class="active">
						    <a href="#1" data-toggle="tab">信息</a>
						  </li>
						  <li><a href="#2" data-toggle="tab">头像</a></li>
						</ul>
						
						<br />
						
							<div class="tab-content">
								<div class="tab-pane active" id="1">
								<!-- <form id="edit-profile" class="form-horizontal"> -->
								<div class="form-horizontal">
									<fieldset>
										
										<div class="control-group">											
											<label class="control-label" for="username">用户名</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" id="username" value="${requestScope.user.username }" disabled="" />
												<p class="help-block">用户名不能进行修改！</p>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										<div class="control-group">											
											<label class="control-label" for="password1">原密码：</label>
											<div class="controls">
												<input type="password" class="input-medium" id="lastPwd" value="" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										<br /><br />
										
										<div class="control-group">											
											<label class="control-label" for="password1">新密码：</label>
											<div class="controls">
												<input type="password" class="input-medium" id="newPwd" value="" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										
										<div class="control-group">											
											<label class="control-label" for="password2">确认密码：</label>
											<div class="controls">
												<input type="password" class="input-medium" id="rePwd" value="" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
											<br />
										
										<div class="form-actions">
											<button onclick="updatePwd();" class="btn btn-primary">提交</button> 
											<!-- <button type="reset" class="btn">取消</button> -->
										</div> <!-- /form-actions -->
									</fieldset>
								</div>
								<!-- </form> -->
								</div>
								
								<div class="tab-pane" id="2">
									<form id="edit-profile2" enctype="multipart/form-data" class="form-horizontal">
										<fieldset>
											<div class="control-group">
												<label class="control-label" for="accounttype">头像：</label>
												<div class="controls">
													<div id="imgDiv" style="width: 100px;height: 100px;">
													<img id="iconImg" src="/pic/${user.icon }" width="100px" height="100px"/>
													</div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="accountusername">选择图片：</label>
												<div class="controls">
												<input id="iconFile" accept="image/*" type="file" name="user_icon" onchange="iconChange();"/>
												</div>
											</div>
											<br />
											<div class="form-actions">
												<button onclick="updateIcon();" class="btn btn-primary">保存</button> <!-- <button class="btn">Cancel</button> -->
											</div>
										</fieldset>
									</form>
								</div>
								
							</div>
						</div>
							</div> <!-- /widget-content -->
							
						</div> <!-- /widget -->
						
					</div> <!-- /span9 -->
					
				</div> <!-- /row -->
				
				
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
<script type="text/javascript">
function updatePwd() {
	var data = {
		"lastPwd" : $("#lastPwd").val(),
		"newPwd" : $("#newPwd").val(),
		"rePwd" : $("#rePwd").val()
	};
	$.ajax({
		url : baseUrl + "/user/updatePwd.action",
		dataType : "json",
		type : "post",
		data : data,
		success : function(result) {
			if(result.msg == 'success') {
				alert("修改成功！");
				location.reload();
			} else {
				alert(result.info);
			}
		}
	});
}
function iconChange() {
	
	//获取文件
    var file = $("#iconFile").get(0).files[0];
 
    //创建读取文件的对象
    var reader = new FileReader();
 
    //创建文件读取相关的变量
    var imgFile;
 
    //为文件读取成功设置事件
    reader.onload=function(e) {
        imgFile = e.target.result;
        console.log(imgFile);
        $("#iconImg").attr('src', imgFile);
    };
 
    //正式读取文件
    reader.readAsDataURL(file);
}
function updateIcon() {
	var formData = new FormData(document.getElementById("edit-profile2"));//表单id
	$.ajax({
		url: baseUrl + '/user/updateIcon.action' ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (result) {
			if(result == 'success') {
				alert('修改成功');
				location.reload();
			} else {
				alert("修改失败：" + result.info);
			}
		}
	});
}
</script>


</body>
</html>