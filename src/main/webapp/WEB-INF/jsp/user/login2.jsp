<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="${pageContext.request.contextPath }/resources/css/user/login/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/user/login/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/user/login/userpanel.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/user/login/jquery.ui.all.css">
<script type="text/javascript">
	var msg = "${message}";
	if(msg != null && msg != '') 
		alert(msg);
</script>
</head>
<body class="login" mycollectionplug="bind">
<div class="login_m">
<div class="login_logo"><img src="${pageContext.request.contextPath }/resources/css/user/login/logo.png" width="196" height="46"></div>
<div class="login_boder">

<div class="login_padding" id="login_model">
<form action="${pageContext.request.contextPath }/login.action" method="post">
  <h2>用户名</h2>
  <label>
    <input type="text" id="username" name="username" class="txt_input txt_input2" onfocus="if (value ==&#39;your name&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;your name&#39;}" value="your name">
  </label>
  <h2>密码</h2>
  <label>
    <input type="password" name="password" id="userpwd" class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******">
  </label>
  <p class="forgot"><a id="iforget" href="javascript:void(0);">忘记密码？</a></p>
  <div class="rem_sub">
  <div class="rem_sub_l">
  <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox">记住密码</label>
   </div>
    <label>
      <input type="submit" class="sub_button" id="button" value="登录" style="opacity: 0.7;">
    </label>
  </div>
  </form>
</div>

<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>

<div id="forget_model" class="login_padding" style="display:none">
<br>

   <h1>Forgot password</h1>
   <br>
   <div class="forget_model_h2">(Please enter your registered email below and the system will automatically reset users’ password and send it to user’s registered email address.)</div>
    <label>
    <input type="text" id="usrmail" class="txt_input txt_input2">
   </label>

 
  <div class="rem_sub">
  <div class="rem_sub_l">
   </div>
    <label>
     <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now" style="opacity: 0.7;">
     　　　
     <input type="submit" class="sub_button" name="button" id="denglou" value="Return" style="opacity: 0.7;">　　
    
    </label>
  </div>
</div>






<!--login_padding  Sign up end-->
</div><!--login_boder end-->
</div><!--login_m end-->
 <br> <br>
<p align="center"> More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>



</body>
</html>