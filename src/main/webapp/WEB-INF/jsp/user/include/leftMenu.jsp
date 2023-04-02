<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="span3">
	<div class="account-container">
		<div class="account-avatar">
			<a class="fancybox" href="/pic/${user.icon }" ><img src="/pic/${user.icon }" alt="" class="thumbnail" /></a>
		</div> <!-- /account-avatar -->
		<div class="account-details">

			<span class="account-name">${user.username }</span>

			<span class="account-role">${user.roleCustom.roleName }</span>

			<span class="account-actions">
				<a href="https://xzcoder.com/" target="_blank">博客</a> |

				<a href="/resources/img/wechat_qrcode.jpg" target="_blank">公众号</a>
			</span>

		</div> <!-- /account-details -->

	</div> <!-- /account-container -->

	<hr />

	<ul id="main-nav" class="nav nav-tabs nav-stacked">
		<% String uri = request.getRequestURI(); %>
		<li
		<%if(uri.endsWith("center.jsp")) { %>
		class="active"
		<%} %>
		>
			<a href="${pageContext.request.contextPath }/user/center.html">
				<i class="icon-home"></i>
				首页
			</a>
		</li>

		<li
		<%if(uri.endsWith("myExam.jsp")) { %>
		class="active"
		<%} %>
		>
			<a href="${pageContext.request.contextPath }/user/myExam.html">
				<i class="icon-pushpin"></i>
				我的考试
				<span id="notReadKs"></span>
			</a>
		</li>

		<li
		<%if(uri.endsWith("myResult.jsp")) { %>
		class="active"
		<%} %>
		>
			<a href="${pageContext.request.contextPath }/user/myResult.html">
				<i class="icon-th-list"></i>
				我的成绩
				<span id="notReadResult"></span>
			</a>
		</li>

		<li>
			<a href="javascript:open('${pageContext.request.contextPath }/user/myWrongExercise.html');">
				<i class="icon-th-large"></i>
				我的错题
				<span></span>
			</a>
		</li>

		<!-- <li>
			<a href="./charts.html">
				<i class="icon-signal"></i>
				我的消息
				<span></span>
			</a>
		</li> -->

		<li
		<%if(uri.endsWith("setting.jsp")) { %>
		class="active"
		<%} %>
		>
			<a href="${pageContext.request.contextPath }/user/setting.html">
				<i class="icon-user"></i>
				用户设置
			</a>
		</li>

		<li
		<%if(uri.endsWith("chooseExercises.jsp")) { %>
		class="active"
		<%} %>
		>
			<a href="${pageContext.request.contextPath }/user/chooseExercises.html">
				<i class="icon-lock"></i>
				刷题练习
			</a>
		</li>

	</ul>

	<hr />

	<div class="sidebar-extra">
		<img src="/resources/img/wechat-qrcode1.png" alt="BUG工程师" width="100%">
	</div> <!-- .sidebar-extra -->

	<br />

</div> <!-- /span3 -->
