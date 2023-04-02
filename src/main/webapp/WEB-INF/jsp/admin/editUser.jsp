<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.inputTitle {
	text-align: right;
	padding-right: 20px;
}
</style>
</head>
<body>
<form id="editUserForm" enctype="multipart/form-data" method="post">
<table border="1" style="width: 100%;height: 550px;">
	<tr>
		<td class="inputTitle" style="width: 100px;text-align: right;padding-right: 20px;">头像：</td>
		<td>
			<input type="hidden" name="id" value="${user.id }" />
			<div id="imgDiv" style="width: 100px;height: 100px;">
			<img id="iconImg" src="/pic/${user.icon }" width="100px" height="100px"/>
			</div>
			<input id="iconFile" name="user_icon" class="easyui-filebox" data-options="prompt:'请选择一张图片',buttonText:'选择文件',accept:'image/*'" style="width: 250px;" />
		</td>
	</tr>
	<tr>
		<td style="text-align: right;padding-right: 20px;">用户名：</td>
		<td>
			<input id="edit_username" class="easyui-textbox" type="text" name="username" data-options="required:true" value="${user.username }"></input>
		</td>
	</tr>
	<tr>
		<td style="text-align: right;padding-right: 20px;">原密码：</td>
		<td>
			<input class="easyui-textbox" type="text" readonly="readonly" value="${user.password }"></input>
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">新密码：</td>
		<td><input id="newpwd" type="password" name="password" value="${user.password }" /></td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">确认密码：</td>
		<td>
			<input id="rpwd" type="password" name="rpwd" value="${user.password }" class="easyui-validatebox"  />
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">所属组别 ：</td>
		<td>
			<input type="hidden" id="groupHidden" name="groupId" value="${user.groupCustom.groupId }" />
			<select id="query_group" url="${pageContext.request.contextPath }/admin/user/getAllGroups.json" class="easyui-combotree" style="width:156px;">
				<!-- <option>全部</option> -->
				<option value="${user.groupCustom.groupId }">${user.groupCustom.groupName }</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">真实姓名：</td>
		<td>
			<input class="easyui-validatebox" type="text" name="readlyName" value="${user.readlyName }" ></input>
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">性别：</td>
		<td>
			<select id="query_gender" name="intGender" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
				<option value="1"
					<c:if test="${user.gender eq true }">selected="selected"</c:if>
				>男</option>
				<option value="0"
					<c:if test="${user.gender eq false }">selected="selected"</c:if>
				>女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">邮箱：</td>
		<td>
			<input id="rpwd" name="email" type="text" value="${user.email }" class="easyui-validatebox" data-options="validType:'email'"/>
		</td>
	</tr>
	<tr>
		<td class="inputTitle" style="text-align: right;padding-right: 20px;">生日：</td>
		<td>
			<input id="birthdayValue" type="hidden" name="" value="<fmt:formatDate value='${user.birthday }' pattern='yyyy-MM-dd' />" />
			<input id="birthday" name="birthday" data-options="validType:'birthd'" class="easyui-datebox" />
		</td>
	</tr>
</table>
</form>
</body>
</html>