<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加角色</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function checkedAll(cbxObj,moduleId, funcCount) {
	//alert("moduleId=" + moduleId + ",funcCount=" + funcCount);
	if(cbxObj.checked) {
		//选中所有checkbox
		for ( var i = 0; i < funcCount; i++) {
			$("#cbx_" + moduleId + "_" + i).prop("checked",true);
		}
	} else {
		//取消选中所有checkbox
		for ( var i = 0; i < funcCount; i++) {
			$("#cbx_" + moduleId + "_" + i).prop("checked",false);
		}
	}
}
function submit() {
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/admin/role/addRole.action",
		data: $('#addRoleForm').serialize(),
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				document.getElementById("addRoleForm").reset();
				$.messager.show({
					title:'提示',
					msg:'添加角色成功',
					timeout:3000,
				});
			} else if(result.msg == 'error') {
				$.messager.show({
					title:'提示',
					msg:'添加角色失败：' + result.info,
					timeout:3000,
				});
			}
		}
	});
}
</script>
<style type="text/css">
#headDiv {
	position: absolute;
	/* border: 1px solid black; */
	width: 100%;
	overflow: auto;
	left: 0px;
	right: 0px;
	top: 0px;
	bottom: 60px;
	padding-left: 20px;
	padding-top: 10px;
}
body {
	background-color: #FAFAFA;
	overflow-x: hidden; 
}
.panel-title {
	font-weight: bold;
	color: #404040;
}
#addPanel {
	/*border: 1px solid red;*/
	padding: 0px;
}
#addPanel ul li {
	list-style: none;
	color: #747474;
	/*padding: 0px;
	margin: 0px;*/
}
#addPanelUl {
	/*border: #3C6E31 solid 1px;*/
	padding: 0px;
}
#info {
	/* border: 1px solid red; */
	width: 850px;
	height: 60px;
}
#infoUl li {
	float: left;
	/* border: 1px solid #747474; */
	padding-left: 20px;
	padding-right: 100px;
	padding-top: 10px;
	font-size: 16px;
	font-family: "宋体";
}
#infoUl {
	padding-left: 0px;
	/*padding-left: 20px;
	padding-right: 50px;*/
}
#funcDiv {
	padding-left: 10px;
}
#funcDiv span {
	font-size: 16px;
	font-weight: 700;
	color: #404040;
}
#funcFieldset {
	width: 600px;
}
fieldset {
	font-size: 15px;
	border: 1px solid;
}
#footDiv {
	position: absolute;
	left: 0px;
	right: 0px;
	bottom: 0px;
	height: 60px;
	/* border: 1px solid blue; */
	display:flex;
    justify-content:center;
    align-items:center;
}
#addBtnDiv {
	width: 150px;
	height: 45px;
	float: left;
	/* margin: auto 0; */
	/* border: 1px solid red; */
	color: white;
	background-color: #0A6CB7;
	display:flex;
    justify-content:center;
    align-items:center;
}
#addBtnDiv:HOVER {
	background-color: #9CC8F7;
	color: black;
	cursor: pointer; 
}
#addBtnDiv img {
	margin-top: 5px;
}
</style>
</head>
<body>
<div id="headDiv">
<form id="addRoleForm">
<div id="addPanel" class="easyui-panel" iconCls="icon-tip" title="添加角色" style="width:900px;height:600px;background-color: FAFAFA;">
	<ul id="addPanelUl">
		<li>
			<div id="info">
				<ul id="infoUl">
					<li>
						<span>角色名称：</span>
						<input type="text" name="roleName" class="easyui-textbox" data-options="required:true" style="height: 35px;width: 200px;" />
					</li>
					<li>
						<span>角色描述：</span>
						<input type="text" name="des" class="easyui-textbox" style="height: 35px;width: 200px" />
					</li>
				</ul>
			</div>
		</li>
		<li>
			<div id="funcDiv">
				<span>权限</span><br />
				<fieldset id="funcFieldset">
					<c:forEach items="${modules }" var="module">
						<fieldset>
							<legend>
								${module.name }&nbsp;&nbsp;&nbsp;全选
								<input type="checkbox" onchange="checkedAll(this,${module.moduleId }, ${fn:length(module.functions) });" />
							</legend>
							<c:forEach items="${module.functions }" var="function" varStatus="index">
								<input type="checkbox" name="functionIds" value="${function.functionId }" id="cbx_${module.moduleId }_${index.index }" />
								${function.name }&nbsp;&nbsp;
							</c:forEach>
						</fieldset>
					</c:forEach>
				</fieldset>
			</div>
		</li>
	</ul>
</div>
</form>
</div>
<div id="footDiv">
	<div id="addBtnDiv" onclick="submit();">
		<img src="${pageContext.request.contextPath }/resources/js/easyui/themes/icons/filesave.png">
		<span style="padding-top: 3px;">保存角色信息</span>
	</div>
</div>
</body>
</html>