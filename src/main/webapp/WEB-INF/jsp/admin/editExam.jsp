<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑考试</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/bootstrap/css/bootstrap.min.css"> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/admin/editExam.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/resources/js/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
var ksId =  "${ksId}";
var baseUrl = "${pageContext.request.contextPath}";

$(document).ready(function() {
	UE.getEditor('examDesc');
});
</script>
<style type="text/css">
#addShiJuan_table tr{
	height: 50px;
}
</style>
</head>
<body class="easyui-layout">
	<div region="center" style="background: #fafafa;padding:10px;font-size: 14px;">
		<div class="easyui-panel" title="添加考试" style="position:relative;width:870px;padding:10px;background:#fafafa;" data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<!-- 考试基本信息 -->
			<form id="addExamForm" method="post">
			<div class="easyui-panel" style="background:#fafafa;border:0px;margin-bottom:10px;">
				<div style="width:100%;float:left;">
					<div style="width:78%;float:left;">
						<h3>考试设置</h3>
						<div class="div_line_left">
							<!-- 考试名称： -->
							<span style="padding-left: 20px;">考试名称：</span>
							<input id="examName" name="name" class="easyui-validatebox input_course" type="text" data-options="required:true"/>
							<span style="padding-left: 20px;">考试分类：</span>
							<input id="examClassify" name="ksClassifyId" style="width:185px;height:32px;"/>
						</div>
						<div style="margin-top:10px;font-size: 14px;margin-left:10px;">
							<span style="float:left;display:block;margin-left:20px;">考试描述：</span>
							<!-- <textarea id="examDesc" name="des" class="input_course" style="width:485px;height: 60px;margin-left:4px;"></textarea> -->
						</div>
						<div style="width: 490px;margin-left: 103px;">
							<textarea id="examDesc" name="des" style="width:490px;height:80px;margin-left:4px;"></textarea>
						</div>
						<!-- 封面hidden -->
						<!-- <input id="cover" type="hidden" name="pic"/> -->
						<div class="div_line_left" style="margin-top:10px;">
			 				<span style="padding-left: 20px;">总分：</span><label id="totalsorce" style="width:80px">0分</label>
							<span style="padding-left: 20px;">及格分：</span>
							<input id="passScore" name="okrate" class="easyui-validatebox input_course" value="60" style="width:100px;height:26px;" type="text" data-options="required:true,validType:'oKNumber'"/>
						</div>
						<div class="div_line_left">
							<span style="padding-left: 20px;">考试次数：</span>
							<select id="maxTimes" name="maxtimes" style="width:100px;height:26px;" class="easyui-combobox" 
									data-options="panelHeight: 'auto',editable:false">
								<option value="-1">不限</option>
								<option value="1">1次</option>
								<option value="2">2次</option>
								<option value="3">3次</option>
								<option value="4">4次</option>
								<option value="5">5次</option>
							</select>
							<!-- 考试时间： -->
							<span style="padding-left: 20px;">考试时间：</span>
							<input name="beginTime" class="easyui-datetimebox" data-options="showSeconds:false,editable:false,required:true" style="width:129px;height:26px;" type="text" id="studyTimeStart"/>
							<!--~-->
							~
							<input name="endTime" class="easyui-datetimebox" data-options="showSeconds:false,editable:false,required:true" style="width:129px;height:26px;" type="text" id="studyTimeEnd"/>
						</div>
						<div class="div_line_left">
							<span style="padding-left: 20px;">答卷时长：</span>
							<input id="totalTime" name="totaltm" type="text" class="easyui-validatebox input_course" value="60" style="width:172px;height:26px;" data-options="required:true,validType:'cishu'" />分钟
							<span style="padding-left: 20px;">答卷模式：</span>
							<!-- <input name="pagesize" type="text" class="easyui-validatebox input_course" style="width:172px;height:26px;" data-options="required:true" /> -->
							<select id="pageSize" name="pagesize" style="width:85px;height:26px;" class="easyui-combobox" 
									data-options="panelHeight: 'auto',editable:false">
								<option value="0">整卷模式</option>
								<option value="1">逐题模式</option>
							</select>
						</div>
					</div>
				</div>
				<div style="width:100%;float:left;">
					<div style="width:50%;float:left;">
						<h3>考试控制</h3>
						<div class="div_line_left">
							<span style="padding-left: 20px;"><input id="userSignupFlg" type="checkbox"/></span>
							考试需要报名
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input id="passingAgainFlg" type="checkbox"/></span>
							及格后不能再考
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input id="qsnRandomFlg" type="checkbox"/></span>
							试题乱序
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input id="optionChaosFlg" type="checkbox"/></span>
							选择题选项乱序
						</div>
						<!-- <div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input name="examManualFlg" type="checkbox"/></span>
							需要人工评分：
							<select name="exammanual" style="width:93px;height:26px;" class="easyui-combobox" 
								data-options="
									panelHeight: 'auto',editable:false">
								<option value="1">按人评分</option>
								<option value="2">按题评分</option>
							</select>
						</div> -->
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;">计时方式：</span>
							<select id="unityduration" name="unityduration" style="width:120px;height:26px;" class="easyui-combobox" 
								data-options="
									panelHeight: 'auto',editable:false">
								<option value="0">按打开试卷时间</option>
								<option value="1">按考试开始时间</option>
							</select>
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"></span>
							多选题分数计算方式：
							<select id="selectFlg" name="selectFlg" style="width:100px;height:26px;" class="easyui-combobox" 
								data-options="panelHeight: 'auto',editable:false">
								<option value="0" selected="selected">少选不得分</option>
								<option value="1">少选得半分</option>
							</select>
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;">开始考试</span>
							<span style="padding-left: 5px;">
								<input name="disableexam" id="disableexam" class="easyui-validatebox input_course" value="0" type="text" style="width:50px;height:26px;" data-options="validType:'startMinute'"/>
							</span>
							<span style="padding-left: 5px;">分钟后禁止考生参加</span>
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;">开始考试</span>
							<span style="padding-left: 5px;">
								<input name="disablesubmit" id="disablesubmit" class="easyui-validatebox input_course" value="0" type="text" style="width:50px;height:26px;" data-options="validType:'startMinute'"/>
							</span>
							<span style="padding-left: 5px;">分钟内禁止考生交卷</span>
						</div>
					</div>
					<div style="width:auto">
						<h3>成绩发布</h3>
						<div class="div_line_left">
							<span style="padding-left: 20px;"><input id="rdoPubTime" type="radio" onclick="changeTimeValidateTrue(this);" name="scorePulic" value="1"/></span>
							指定时间发布
							<span style="padding-left: 5px;">
								<input id="resultPublishTime" name="resultPublishTime" class="easyui-datetimebox" data-options="showSeconds:false,editable:false" style="width:150px;height:26px;"/>
							</span>
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input type="radio" id="rdoNow" onclick="changeTimeValidateFalse();" name="scorePulic" value="2" checked="checked"/></span>
							交卷后立刻发布
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input id="publishAnswerFlg" checked="checked" type="checkbox"/></span>
							发布后允许查看试卷和答案
						</div>
						<div class="div_line_left" style="margin-top:10px;">
							<span style="padding-left: 20px;"><input id="isResultRank" type="checkbox"/></span>
							显示排名
						</div>
					</div>
				</div>
				<!-- <div class="div_line_left" style="padding-left: 20px;">
					手机是否可以参加考试：
					<input name="phoneflag" value="1" type="radio"/>是
					<input name="phoneflag" value="0" checked="checked" type="radio"/>否
					<span style="padding-left:20px;font-size: 14px;color: green;font-weight: bold;"></span>
				</div> -->
			</div>
			<input type="hidden" name="usersignupflg" value="0"/>
			<input type="hidden" name="passingagainflg" value="0"/>
			<input type="hidden" name="qsnrandomflg" value="0"/>
			<input type="hidden" name="optionChaosFlg" value="0"/>
			<input type="hidden" name="publishanswerflg" value="0"/>
			<input type="hidden" name="isresultrank" value="0"/>
			<input type="hidden" name="sjid" id="sjid"/>
			<input type="hidden" name="cerId" id="cerId"/>
			<input type="hidden" name="userGroupId" id="userGroupId"/>
			<input type="hidden" name="userIds" id="userIds"/>
			</form>
			<div style="right:20px;top:30px;position:absolute">
				<form id="uploadForm" method="post" enctype="multipart/form-data">
					<div style="width:auto;float:right;text-align: center;padding-top: 18px;">
						<div style="border:1px solid #c3d9e0;padding:1px;1px;1px;1px;">
							<img id="coverPic" src="" width="180px" height="150px"/>
						</div>
						<div></div>
						<input id="uploadCoverFile" class="easyui-filebox btn btn-default" name="ksPic" data-options="prompt:'请选择一张图片',buttonText:'选择文件',accept:'image/*'" style="width:140px;height:30px;"/>
					</div>
				</form>
			</div>
			<div class="easyui-panel" style="background:#fafafa;border:0px;margin-bottom:10px;">
				<h3>考试管理</h3>
				<div id="tab" class="easyui-tabs" style="background:#fafafa;">
					<div title="试卷" style="background:#fafafa;width:100%">
						<table id="shijuanManage" class="easyui-datagrid"  style="background:#fafafa;height:auto"
								data-options="rownumbers:true,singleSelect:false,toolbar:'#toolbar'">
							<thead>
								<tr>
									<!-- <th data-options="field:'ck',checkbox:true"></th> -->
									<th data-options="field:'title',width:'30%'">试卷名称</th>
									<th data-options="field:'classifyname',width:'15%',formatter:classifyNameFormatter">试卷分类</th>
									<th data-options="field:'totalScore',width:'15%'">试卷总分</th>
									<th data-options="field:'count',width:'15%'">试题数量</th>
									<th data-options="field:'yl',width:'15%',align:'center',formatter:ylFormatter">预览</th>
								</tr>
							</thead>
						</table>
						<div id="toolbar" style="background:#fafafa;height:auto;padding-right: 10px;text-align: right;">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addShiJuan()">添加</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="delShiJuan()">删除</a>
						</div>
						<div id="visableMsg" data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;padding-right: 10px;display: none;">
						<center style="color:red;font-size:18px;">权限不足，无法查看该试卷！</center>
						</div>
					</div>
					<div title="用户/组" style="background:#fafafa;">
						<div style="margin:20px 0 0 20px;background:#fafafa;">
							<!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-man'" onclick="showUserGroupDialog();" style="width:180px;">按用户组添加</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-man'" style="width:180px;margin: 0 0 0 20px" onclick="createAddUserDialog()">按用户添加</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-no'" style="width:180px;margin: 0 20px 0 20px;float: right;" onclick="deleteUser()">删除所选用户</a> -->
						</div>
						<div style="margin-top:30px;height: 400px;">
							<div style="text-align:center;width:100%;height:25px;padding:10px 0 0 0;background: none repeat scroll 0 0 rgb(235, 235, 235);background:#fafafa;">已添加用户不能修改</div>
							<table id="addedUserList" name="addedUserList" class="easyui-datagrid" style="height: 400px;width: 100%" 
								data-options="rownumbers:true,fitColumns:true">
								<!-- <thead>
									<tr>
										<th data-options="field:'ck',checkbox:true"></th>
										<th data-options="field:'username',width:120">用户名</th>
										<th data-options="field:'realname',width:120">真实姓名</th>
										<th data-options="field:'nickname',width:120">昵称</th>
										<th data-options="field:'rolename',width:80">角色</th>
										<th data-options="field:'groupname',width:120">所属用户组</th>
										<th data-options="field:'positionname',width:120">职位</th>
										<th data-options="field:'examcard',width:120">学历</th>
									</tr>
								</thead> -->
							</table>
						</div>
					</div>
					<!-- <div title="证书" style="width:100%">
						<table id="certificatesManage" class="easyui-datagrid" style="height:auto"
								data-options="rownumbers:true,fitColumns:true,toolbar:'#toolbar_certificates'">
							<thead>
								<tr>
									<th data-options="field:'ck',checkbox:true"></th>
									<th data-options="field:'name',width:'30%'">证书名称</th>
									<th data-options="field:'number',width:'25%'">证书编号</th>
									<th data-options="field:'issueAgency',width:'15%'">发证机构</th>
									<th data-options="field:'expiryDate',width:'10%'">证件有效期</th>
									<th data-options="field:'issueDate',width:'10%'">发证时间</th>
								</tr>
							</thead>
						</table>
						<div id="toolbar_certificates" style="height:auto;float: right;margin-right: 10px">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addCertificates()">添加</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="delCertificates()">删除</a>
						</div>
					</div> -->
				</div>
			</div>
			<div class="easyui-panel" style="border:0px;text-align: center">
				<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveExam();" style="width:150px;height:50px;">保存考试信息</a>
			</div>
		</div>
	</div>
	<div id="addShiJuan" class="easyui-dialog" title="添加试卷" style="width:800px;height:400px;" data-options="modal : true,closed:true">
		<div class="easyui-panel "
			style="background:#fafafa;border: 0px;height:90%;"
			data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
			<table id="addShiJuan_table" class="easyui-datagrid" toolbar="#ebar" style="">
			</table>
			<div id="ebar" style="height:34px;padding:10px 0 0 10px;font-size:12px;">
				试卷分类：<input class="easyui-combobox" panelHeight="auto" style="width:120px" id="sjClassify"/>
				试卷名称：<input class="easyui-textbox" style="width:120px" id="query_title" name="title" />
				创建人： <input class="easyui-textbox" style="width:80px" id="query_insUser" name="insUser" />
				<c:if test="${user.readPrivateSt == 1 }">
				可见性：
				<select id="query_visable" class="easyui-combobox" style="width: 120px;" data-options="editable:false">
					<option value="-1">全部</option>
					<option value="1">公开试卷</option>
					<option value="0">私有试卷</option>
				</select>
				</c:if>
				<a id="seach_Shijuan" onclick="queyrSjData();" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="float:right;margin-right: 10px">查询</a>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;padding-right: 10px;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveShiJuan()" style="width:80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addShiJuan').window('close')" style="width:80px">取消</a>
		</div>
	</div>
	<!-- <div id="addCertificates" class="easyui-dialog" title="添加证书" style="width:800px;height:430px;padding:5px;" data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">
			<div id="ebar" style="padding:10px 0 0 10px;font-size:12px;">
				证书名称：<input class="easyui-textbox" style="width:150px" id="cer_name" name="sjclassifyid"/>
				证书编码：<input class="easyui-textbox" style="width:150px" id="cer_num" name="title" />
				发证机构：<input class="easyui-textbox" style="width:150px" id="cer_ent" name="insuserid" />
				<p>发证时间：考试时间：
					<input id="cer_begintm" name="begintm" class="easyui-datebox" style="width:100px"/>
					~~
					<input id="cer_endtm" name="endtm" class="easyui-datebox" style="width:100px"/>
					<a id="seach_Cer" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="float:right;margin-right: 10px">查询</a>
				</p>
			</div>
			<table id="addCertificates_table" style="max-height:250px">
			</table>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveCertificates()" style="width:80px">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addCertificates').window('close')" style="width:80px">取消</a>
			</div>
		</div>
	</div> -->
	<div id="usersDia" class="easyui-dialog" title="按用户添加" style="width:800px;height:450px;padding:0px;" data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">
			<div region="center" style="background: #fafafa;border:0px;">
				<div id="ubar" style="height:34px;padding-top:10px;font-size:12px;">
					<input type="hidden" id="ingroupids" value=""/>
					用户组：<input id="userGroupComboxTree" name="groupid" style="width:120px;height:28px;" /> 
					用户名:<input class="easyui-validatebox input_user_info" style="width:130px;height:25px;" type="text" id="userLike"/>
					角色：<input style="width:120px;height:28px;" type="text" id="roleCombobox"/>
					性别：<!-- <input class="easyui-validatebox input_user_info" style="border:1px solid #c3d9e0;width:80px;height:25px;" type="text" id="examCardLike"/> -->
					<select id="query_gender" class="easyui-combobox" style="border:1px solid #c3d9e0;width:80px;height:25px;" data-options="editable:false">
						<option value="-1">全部</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
					<a id="seach" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="seachUserByParam();">查询</a>
				</div>
				<table id="unAddUserList" style="max-height: 300px"></table>
			</div>
			<div  region="south" style="background: #fafafa;padding:10px;font-size: 14px;text-align: center;border:0px;">
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="addUsersTOAddedGrid();" style="width:130px;height:32px;"> 保存已选用户</a> 
			</div>
		</div>
	</div>
	<div id="updGroupIdsDia" title="按用户组添加" class="easyui-dialog" style="width:400px;height:400px;padding: 0px;" data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">
			<div region="center" style="background: #fafafa;border:1px solid #c3d9e0;">
				<ul id="userGroupTree"></ul>
			</div>
			<div  region="south" style="background: #fafafa;padding:5px;font-size: 14px;text-align: center;border-top:1px solid #c3d9e0;height:45px;">
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="searchGroupUsers();" style="width:130px;height:32px;">添加用户组用户</a> 
			</div>
		</div>
	</div>
</body>
<style type="text/css">
.div_line_left{
	margin:0 0 10px 10px;
	width:auto;
	font-size: 14px;
}
.input_course{
	border:1px solid #c3d9e0;
	width:200px;
	height:28px;
}
.pageContainerBox{
	background: #fff none repeat scroll 0 0;
	border: 1px solid #ccc;
	margin-bottom: 10px;
	overflow: hidden;
	padding: 10px 0;
}

.borderDotted {
    border-bottom: 1px dotted #b6b6b6;
    overflow: hidden;
    padding: 20px 0;
}

.formItem {
    float: left;
    width: 100%;
}
.borderDotted .formField {
    width: 750px;
}
.formField {
    width: 500px;
}
.formField {
    float: left;
    line-height: 30px;
    text-align: left;
    width: 600px;
}
.moseoverYellow:hover{ background:#fdffee;}
.moseoverYellow{ position:relative;}
.tr3{
	background: #f5f5f5 none repeat scroll 0 0;
}
#userTable td{
	border-color: #c3d9e0;
	margin: 0;
	padding: 0;
	border-style: dotted;
	border-width: 0px 1px 1px 0px;
}
#insTable td{
	border-color: #c3d9e0;
	margin: 0;
	padding: 0;
	border-style: dotted;
	border-width: 0px 1px 1px 0px;
}
.examTable td{
	border-color: #c3d9e0;
	margin: 0;
	padding: 0;
	border-style: dotted;
	border-width: 0px 1px 1px 0px;
}
.exam_div{
	margin:0 0 10px 10px;
	width:auto;
	font-size: 14px;
}
.datagrid-btable tr{
	height: 28px;
	text-align: center;
}
</style>
</html>