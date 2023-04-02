$(function() {
	//加载考试分类下拉选框
	$("#examClassify").combotree({
		multiple : false,
		checkbox : false,
		lines : true,
		animate : true,
		editable : false,
		url : baseUrl + '/admin/exam/getAllKsClassifyTree.json',
		required : true
	});
	//加载试卷对话框中的试卷分类下拉选框
	$.ajax({
		url : baseUrl + '/admin/shijuan/getAllSjClassify.json',
		dataType: 'json',
		success:function(data) {
			// 修改ajax返回的值
			data = [{'id':-1, 'text':'全部', 'state':'open', 'children':data}];
			$('#sjClassify').combotree({            
				data:data,
				editable:false, //不可编辑
				onLoadSuccess:function(node,d) {
					$('#sjClassify').combotree("setValue", d[0].id);
				}
			});
		}
	});
	//加载用户选择对话框中的用户组下拉选框
	$("#userGroupComboxTree").combotree({
		url: baseUrl + '/admin/user/getAllGroups.json',
		valueField:'id',    
	    textField:'text',
	    editable: false,
		onLoadSuccess:function(node,data) {
			var t = $("#userGroupComboxTree").combotree('tree');//获取tree  
			for (var i=0;i<data.length ;i++ ){
			   node= t.tree("find",data[i].id);  
			   t.tree('expandAll',node.target);//展开所有节点  
			}
			$("#userGroupComboxTree").combotree('setValue',data[0].id);
		}
	});
	//加载用户选择对话框中的角色下拉框
	$.ajax({ 
		url: baseUrl + '/admin/user/getAllRoles.json' ,
		dataType: 'json', 
		success: function(data){   
			// 修改ajax返回的值
			data.unshift({'roleId':'-1','roleName':'全部', 'des':'', 'sort':-1});   //unshift方法添加到第一行，push方法添加到末尾
			$('#roleCombobox').combobox({            
				data:data,
				valueField:'roleId',        
				textField:'roleName',
				editable:false //不可编辑
			});
			$('#roleCombobox').combobox("select", data[0].roleId);
		}
	});
	//checkbox:true
	$("#userGroupTree").tree({
		url: baseUrl + '/admin/user/getAllGroups.json',
		valueField:'id',    
	    textField:'text',
	    editable: false,
	    checkbox:true
	});
	//加载试卷DataGrid
	createSjDataGrid();
	//加载用户 DataGrid
	createUserListDataGrid();
	//加载选中用户集合
	addedUserList();
	//为考试封面添加change事件
	$("#uploadCoverFile").filebox({
		onChange:function() {
			var file = $(this).context.ownerDocument.activeElement.files[0];
			$("#coverPic").attr("src", getObjectUrl(file));
		}
	});
	//form
	$("addExamForm").form({
		url : baseUrl + '/admin/exam/addExam.action',
		//dataType : 'json',
		onSubmit : function() {
			alert(1);
			//return $('#addExamForm').form("validate");
			return false;
		},
		success : function(result) {
			alert(result);
		}
	});
});
//加载试卷DataGrid
function createSjDataGrid() {
	$('#addShiJuan_table').datagrid(
		{
			fit : true,// 设置为true时铺满它所在的容器.
			fitColumns : true,// 设置为true将自动使列适应表格宽度以防止出现水平滚动
			nowrap : false,// 设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,// 设置为true将交替显示行背景。
			idField : 'sjId',
			collapsible : true,// 定义是否显示可折叠按钮。
			singleSelect : true,// 设置为true将只允许选择一行。
			border : false,
			remoteSort : false,// 定义是否通过远程服务器对数据排序。
			pagination : true,// 分页组件是否显示
			pageNumber : 1,// 起始页
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 50 ],// 每页显示多少行
			rownumbers : false,// 行号
			url : baseUrl + '/admin/shijuan/querySjList.json',
//			checkOnSelect:false,
			/* frozenColumns : [ [ {
				field : 'select',
				title : '选择',
				width : 50,
				checkbox : true
			} ] ], */
			columns : [ [{
				field : 'ck',
				checkbox : true
			},{
				field : 'title',
				title : '试卷名称',
				width : 200,
				fixed : true,
				align : 'center',
				sortable : true,
				sorter : datasort,
				formatter:titleLength
			}, {
				field : 'classifyname',
				title : '试卷分类',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : classifyNameFormatter
			}, {
				field : 'totalScore',
				title : '试卷总分',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center'
			}, {
				field : 'count',
				title : '题目数量',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
			}, {
				field : 'yl',
				title : '预览',
				width : 80,
				align : 'center',
				sortable : true,
				formatter : ylFormatter
			} ] ],
			onLoadSuccess:function(data){  
				$('.easyui-linkbutton').linkbutton();
				$('#addShiJuan_table').datagrid("clearSelections");
				$('#addShiJuan_table').datagrid("clearChecked");
			}
	});
}
function createUserListDataGrid() {
	$('#unAddUserList').datagrid(
		{
			fit : true,// 设置为true时铺满它所在的容器.
			fitColumns : true,// 设置为true将自动使列适应表格宽度以防止出现水平滚动
			nowrap : false,// 设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,// 设置为true将交替显示行背景。
			idField : 'sjId',
			collapsible : true,// 定义是否显示可折叠按钮。
			singleSelect : false,// 设置为true将只允许选择一行。
			border : false,
			remoteSort : false,// 定义是否通过远程服务器对数据排序。
			pagination : true,// 分页组件是否显示
			pageNumber : 1,// 起始页
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 50 ],// 每页显示多少行
			rownumbers : false,// 行号
			url : baseUrl + '/admin/user/queryUserList.json',
//			checkOnSelect:false,
			/* frozenColumns : [ [ {
				field : 'select',
				title : '选择',
				width : 50,
				checkbox : true
			} ] ], */
			columns : [ [{
				field : 'ck',
				checkbox : true
			},{
				field : 'username',
				title : '用户名',
				width : 200,
				fixed : true,
				align : 'center',
				sortable : true,
				sorter : datasort,
				formatter:titleLength
			}, {
				field : 'roleName',
				title : '角色',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(index, row, value) {
					return row.roleCustom.roleName;
				}
			}, {
				field : 'groupName',
				title : '所属组别',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
				formatter : function(index, row, value) {
					return row.groupCustom.groupName;
				}
			}, {
				field : 'readlyName',
				title : '真实姓名',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
			},{
				field : 'sex',
				title : '性别',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
				formatter : function(index, row, value) {
					if(row.gender==1)
	          			return "男";
	          		else 
	          			return "女";
				}
			} ] ],
			onLoadSuccess:function(data){  
				$('.easyui-linkbutton').linkbutton();
				$('#unAddUserList').datagrid("clearSelections");
				$('#unAddUserList').datagrid("clearChecked");
			}
	});
}
//加载选中用户DataGrid
function addedUserList() {
	$('#addedUserList').datagrid(
		{
			fit : true,// 设置为true时铺满它所在的容器.
			fitColumns : true,// 设置为true将自动使列适应表格宽度以防止出现水平滚动
			nowrap : false,// 设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,// 设置为true将交替显示行背景。
			idField : 'id',
			collapsible : true,// 定义是否显示可折叠按钮。
			singleSelect : false,// 设置为true将只允许选择一行。
			border : false,
			remoteSort : false,// 定义是否通过远程服务器对数据排序。
			pagination : false,// 分页组件是否显示
			//pageNumber : 1,// 起始页
			//pageSize : 10,// 每页显示的记录条数，默认为10
			//pageList : [ 10, 20, 50 ],// 每页显示多少行
			rownumbers : false,// 行号
			url : baseUrl + '/admin/user/queryUserListByIds.json',
			//queryParams : {"ids":[]},
//			checkOnSelect:false,
			/* frozenColumns : [ [ {
				field : 'select',
				title : '选择',
				width : 50,
				checkbox : true
			} ] ], */
			columns : [ [{
				field : 'ck',
				checkbox : true
			},{
				field : 'username',
				title : '用户名',
				width : 200,
				fixed : true,
				align : 'center',
				sortable : true,
				sorter : datasort,
				formatter:titleLength
			}, {
				field : 'roleName',
				title : '角色',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'left',
				formatter : function(index, row, value) {
					return row.roleCustom.roleName;
				}
			}, {
				field : 'groupName',
				title : '所属组别',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
				formatter : function(index, row, value) {
					return row.groupCustom.groupName;
				}
			}, {
				field : 'readlyName',
				title : '真实姓名',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
			},{
				field : 'sex',
				title : '性别',
				width : 80,
				sortable : true,
				sorter : datasort,
				align : 'center',
				formatter : function(index, row, value) {
					if(row.gender==1)
	          			return "男";
	          		else 
	          			return "女";
				}
			} ] ],
			onLoadSuccess:function(data){  
				$('.easyui-linkbutton').linkbutton();
				$('#addedUserList').datagrid("clearSelections");
				$('#addedUserList').datagrid("clearChecked");
				seachUserByParam();
			}
	});
}
function classifyNameFormatter(value, row, index) {
	return row.sjClassifyCustom.name;
}
function ylFormatter(value, row, index) {
	return "<a href='javascript:;' onClick='previewSj(" + row.sjId + ")' class='easyui-linkbutton' plain='true' iconCls='icon-search'>预览</a>";
}
//添加试卷
function addShiJuan() {
	var data=$('#shijuanManage').datagrid('getData');
	if(data.rows.length >= 1) {
		msgShow("一场考试只能添加一张试卷！");
		return;
	}
	queyrSjData();
	$("#addShiJuan").dialog("open");
}
//组合条件查询试卷信息
function queyrSjData() {
	var visable = 1;
	if(document.getElementById("query_visable")) {
		visable = $("#query_visable").val();
	}
	$('#addShiJuan_table').datagrid('load',{
		'sjDataCustom.title': $('#query_title').val(),
		'sjDataCustom.insUser': $('#query_insUser').val(),
		'sjDataCustom.sjClassifyId': $('#sjClassify').combotree("getValue"),
		'reviewStauts' : 1,
		'visable' : visable
	});
}
//预览试卷
function previewSj(sjId) {
	window.parent.closeTabByTitle("试卷预览");
	parent.addTab('试卷预览', baseUrl + '/admin/shijuan/previewSj.html?sjId=' + sjId);
}
//保存试卷
function saveShiJuan() {
	var selectRows = $("#addShiJuan_table").datagrid("getSelections");
	if(selectRows.length == 0) {
		msgShow("请选中一条试卷信息再保存");
		return;
	}
	var selRow = selectRows[0];
	$("#shijuanManage").datagrid('insertRow', {
		index : 0,
		row : selRow
	});
	$('.easyui-linkbutton').linkbutton();
	
	$("#totalsorce").html(selRow.totalScore + "分");
	$("#addShiJuan").dialog("close");
}
//删除试卷
function delShiJuan() {
	var data=$('#shijuanManage').datagrid('getData');
	for(var i=0;i<data.rows.length;i++) {
		$('#shijuanManage').datagrid('deleteRow',i);
	}
	$("#totalsorce").html("0分");
}
//创建用户选择对话框
function createAddUserDialog() {
	seachUserByParam();
	$("#usersDia").dialog("open");
}
//组合条件查询用户信息
function seachUserByParam() {
	var data=$('#addedUserList').datagrid('getData');
	var rows = data.rows;
	var withOutIds = [];
	for(var i=0;i<rows.length;i++) {
		withOutIds.push(rows[i].id);
	}
	$('#unAddUserList').datagrid('load',{
		'roleCustom.roleId': $('#roleCombobox').val(),
		'userCustom.username': $('#userLike').val(),
		'groupCustom.groupId':$('#userGroupComboxTree').combobox("getValue"),
		'userGender':$('#query_gender').val(),
		'outIds':withOutIds
	});
}
//将选中的用户添加到addedGrid
function addUsersTOAddedGrid() {
	var selRows = $("#unAddUserList").datagrid("getSelections");
	var addedRows = $('#addedUserList').datagrid('getData').rows;
	if(selRows.length == 0) {
		msgShow("请至少选中一条记录再保存！");
		return;
	}
	var ids = [];
	for(var i=0;i<addedRows.length;i++) {
		ids.push(addedRows[i].id);
	}
	for(var i=0;i<selRows.length;i++) {
		ids.push(selRows[i].id);
	}
	$("#addedUserList").datagrid("load", {
		"ids" : ids
	});
}
//删除选中用户
function deleteUser() {
	var addedRows = $('#addedUserList').datagrid('getData').rows;
	var selRows = $("#addedUserList").datagrid("getSelections");
	var addedIdsStr = "";
	for(var i=0;i<addedRows.length;i++) {
		addedIdsStr += "," + addedRows[i].id;
	}
	for(var i=0;i<selRows.length;i++) {
		addedIdsStr = addedIdsStr.replace("," + selRows[i].id, "");
	}
	var ids = [];
    var split = addedIdsStr.split(",");
    for(var i=0;i<split.length;i++) {
    	if(split[i] == "")
    		continue;
    	ids.push(split[i]);
    }
    $("#addedUserList").datagrid("load", {
		"ids" : ids
	});
}
//显示用户组选择对话框
function showUserGroupDialog() {
	$("#updGroupIdsDia").dialog("open");
}
//添加选中的用户组的用户
function searchGroupUsers() {
	var nodes = $('#userGroupTree').tree('getChecked');
	if(nodes.length == 0) {
		msgShow("请至少选择一个用户组！");
		return;
	}
	var groupIds = [];
	for(var i=0;i<nodes.length;i++) {
		groupIds.push(nodes[i].id);
	}
	var addedRows = $('#addedUserList').datagrid('getData').rows;
	var ids = [];
	for(var i=0;i<addedRows.length;i++) {
		ids.push(addedRows[i].id);
	}
	$("#addedUserList").datagrid("load", {
		"ids" : ids,
		"groupIds" : groupIds
	});
	$("#updGroupIdsDia").dialog("close");
}
//添加考试
function saveExam() {
	//先上传考试封面图片再添加考试
	uplaodKsPic();
	
	
	
	return false;
}
//上传考试封面
function uplaodKsPic() {
	var picPath = "";
	var formData = new FormData(document.getElementById("uploadForm"));//表单id
	$.ajax({
	   url: baseUrl + '/admin/exam/uploadKsPic.action' ,
	   type: 'POST',
	   data: formData,
	   async: false,
	   cache: false,
	   contentType: false,
	   processData: false,
	   dataType : 'json',
	   success: function (result) {
		  if(result.msg == 'success') {
			  picPath = result.info;
			  submit(picPath);
		  } else {
			  submit("");
		  }
	   }
	});
	//return picPath == '' ? null : picPath;
}
function submit(ksPic) {
	var examName = $("#examName").val();//考试名称
	var classifyId = $("#examClassify").combotree("getValue");//考试分类 id
	var des = UE.getEditor('examDesc').getContent();
	var passScore = $("#passScore").val();//及格分数
	var maxTimes = $("#maxTimes").val();//考试次数
	var beginTime = $("#studyTimeStart").datetimebox("getValue");//考试开始时间
	var endTime = $("#studyTimeEnd").datetimebox("getValue");//考试结束时间
//	alert(beginTime);
//	alert(endTime);
//	return;
	var totalTime = $("#totalTime").val();//考试时长
	var pageSizeInt = $("#pageSize").val();//答卷模式
	var userSignupInt = $("#userSignupFlg").prop("checked")==true?1:0;//用户是否需要报名
	var passingAgainInt = $("#passingAgainFlg").prop("checked")==true?1:0;//及格后是否可以再考试
	var qsnRandomInt = $("#qsnRandomFlg").prop("checked")==true?1:0;//试题是否乱序
	var optionRandomInt = $("#optionChaosFlg").prop("checked")==true?1:0;//选项是否乱序
	var resultPublishTime = "";//成绩发布日期
	if($("#rdoNow").prop("checked"))
		resultPublishTime = "1970-1-1 00:00:00";
	else
		resultPublishTime = $("#resultPublishTime").datetimebox("getValue");
	var publishRightkeyInt = $("#publishAnswerFlg").prop("checked")==true?1:0;//发布后是否允许查询答案
	var resultRankInt = $("#isResultRank").prop("checked")==true?1:0;//是否显示排名
	var timeTypeInt = $("#unityduration").val();//计时方式
	var selectInt = $("#selectFlg").val();//多选题计分方式
	var disableTime = $("#disableexam").val();//禁止考试时间
	if (disableTime == '') disableTime = 0;
	var disableSubmit = $("#disablesubmit").val();//禁止交卷时间
	if (disableSubmit == '') disableSubmit = 0;
	
	if(examName == '') { msgShow("考试名称不能为空！"); return;}
	if(classifyId == '') { msgShow("考试分类不能为空！"); return;}
	if(passScore == '') { msgShow("请输入及格分数！"); return;}
	if(beginTime == '') { msgShow("请选择考试开始时间！"); return;}
	if(endTime == '') { msgShow("请选择考试结束时间！"); return;}
	if(totalTime == '') { msgShow("请输入考试时长！"); return;}
	if(resultPublishTime == '') { msgShow("请选择考试成绩发布日期 ！"); return;}
	
	var sjData=$('#shijuanManage').datagrid('getData');
	if(sjData.rows.length == 0) { msgShow("请选择一张试卷作为考试内容！"); return;}
	var sjId = sjData.rows[0].sjId;//试卷编号
	var totalScore = sjData.rows[0].totalScore;//总分数
	
	var userDataRows = $("#addedUserList").datagrid("getData").rows;
	if(userDataRows.length == 0) { msgShow("请至少选择一位参加考试的用户 ！"); return;}
	var userIds = [];
	for(var i=0;i<userDataRows.length;i++) {
		userIds.push(userDataRows[i].id);
	}
	
	$.ajax({
		url : baseUrl + '/admin/exam/addExam.action',
		type : "post",
		data : {
			"name" : examName,
			"ksClassifyId" : classifyId,
			"des" : des,
			"passScore" : passScore,
			"maxTimes" : maxTimes,
			"beginTime" : beginTime,
			"endTime" : endTime,
			"totalTime" : totalTime,
			"pageSizeInt" : pageSizeInt,
			"userSignupInt" : userSignupInt,
			"passingAgainInt" : passingAgainInt,
			"qsnRandomInt" : qsnRandomInt,
			"optionRandomInt" : optionRandomInt,
			"resultPublishTime" : resultPublishTime,
			"publishRightkeyInt" : publishRightkeyInt,
			"resultRankInt" : resultRankInt,
			"timeTypeInt" : timeTypeInt,
			"selectInt" : selectInt,
			"disableTime" : disableTime,
			"disableSubmit" : disableSubmit,
			"sjId" : sjId,
			"totalScore" : totalScore,
			"userIds" : userIds,
			"pic" : ksPic
		},
		dataType : 'json',
		success : function(result) {
			if(result.msg == 'success') {
				alert("添加考试成功！");
			} else if(result.msg == 'error') {
				msgShow(result.info);
			}
		}
	});
}
function loadSelectSj() {
	//根据试卷编号获取试卷信息
	$.ajax({
		url : baseUrl + "/admin/shijuan/getSjDataById.json",
		type : "post",
		data : {"sjId" : sjId},
		dataType : "json",
		success : function(data) {
			$("#shijuanManage").datagrid('insertRow', {
				index : 0,
				row : data
			});
			$("#totalsorce").html(data.totalScore + "分");
		}
	});
}

function changeTimeValidateTrue(obj) {
	if($(obj).prop("checked")) {
		$("#resultPublishTime").datetimebox({
			showSeconds:false,
			editable:false,
			required:true
		});
	} else {
		$("#resultPublishTime").datetimebox({
			showSeconds:false,
			editable:false,
			required:false
		});
	}
	$("#rdoNow").prop("checked", false);
}
//
function changeTimeValidateFalse() {
	$("#resultPublishTime").datetimebox({
		showSeconds:false,
		editable:false,
		required:false
	});
	$("#resultPublishTime").prop("checked", false);
}
//排序
function datasort(a, b) {
	return (a > b ? 1 : -1);
}
function titleLength(value, rowData, rowIndex){
	var strArray=value.split("<img");
	var res=value;
	if(strArray.length > 0){
		for(var i=0;i<strArray.length;i++){
			var str=strArray[i];
			if(str.indexOf("src")>0){
				str=str.substr(0,str.indexOf("/>")+2);
				var imgStr="<img"+str;
				res=res.replace(imgStr,"");
			}
		}
	}
	var vArray=res.split("<video");
	if(vArray.length > 0){
		for(var i=0;i<vArray.length;i++){
			var str=vArray[i];
			if(str.indexOf("/video")>0){
				str=str.substr(0,str.indexOf("</video>")+8);
				var vStr="<video"+str;
				res=res.replace(vStr,"[影音文件]");
			}
		}
	}
	var wvArray = res.split("<embed");
	if(wvArray.length > 0){
		for(var i=0;i<wvArray.length;i++){
			var str=wvArray[i];
			if(str.indexOf("type")>0){
				str=str.substr(0,str.indexOf("/>")+2);
				var vStr="<embed"+str;
				res=res.replace(vStr,"[影音文件]");
			}
		}
	}
	if(res.length>15){
		return res.substr(0,15);
	}
	return res;
}
function getObjectUrl(file) {
	var url = null;
	if(window.createObjectURL != undefined)
		url = window.createObjectURL(file);
	else if (window.URL != undefined)
		url = window.URL.createObjectURL(file);
	else if (window.webkitURL != undefined)
		url = window.webkitURL.createObjectURL(file);
	return url;
}