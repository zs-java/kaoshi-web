$(function() {
	loadDataGrid();
});
function loadCombotree() {
	$("#userGroupCombotree").combotree({
		url:baseUrl + '/admin/user/getAllGroups.json',
		valueField:'id', 
		textField:'text',
		editable: false,
		onLoadSuccess:function(node,data) {
			 var t = $("#userGroupCombotree").combotree('tree');//获取tree  
		     for (var i=0;i<data.length ;i++ ){
		        node= t.tree("find",data[i].id);  
		        t.tree('expandAll',node.target);//展开所有节点  
		     }
		     $("#userGroupCombotree").combotree('setValue',data[0].id);
		}
	});
}
function loadDataGrid() {
	$('#Dg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
//		toolbar:"#tb",
		sortable:true,
		url: baseUrl + '/admin/exam/queryExamMonitorList.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
//		sortName : 'ksId',//当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		columns:[[ {
				field : 'ksId',
				title : '考试Id', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 50
			}, {
				field : 'name',
				title : '考试名称', 
				valign : "middle",
	            align : "center",
				width : 100
			},{
				field : 'totalScore', 
				title : '总分', 
				valign : "middle",
				align : "center",
				width : 80
			},{
				field : 'passScore',
				title : '及格分', 
				valign : "middle",
				align : "center",
				width : 80
			},{
				field : 'beginTimeString', 
				title : '开始时间', 
				valign : "middle",
				align : "center",
				width : 120
			},{
				field : 'endTimeString', 
				title : '结束时间', 
				valign : "middle",
				align : "center",
				width : 120
			},{
				field : 'examOverCount', 
				title : '交卷人数', 
				valign : "middle",
				align : "center",
				width : 80
			},{
				field : 'notOverCount', 
				title : '未完成人数', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					return row.totalCount - row.examOverCount;
				}
			}, {
				field:'scoreAvg', 
				title:'平均分', 
				valign : "middle",
				align : "center",
				width:80
			}, {
				field : 'passPercent',
				title : '及格率', 
				valign : "middle",
				align : "center",
				width : 80
			}, {
				field : 'cx',
				title : '操作',
				valign : "middle",
				align : "center",
				width : 120,
				formatter : function(value, row, index) {
					var html = "<a href='#' class='easyui-linkbutton' onclick='showDetail("+row.ksId+", \""+row.name+"\");' plain='true' iconCls='icon-search'>详细</a>";
					html += "<a href='#' class='easyui-linkbutton' onclick='showErrorResult("+row.ksId+", \""+row.name+"\");' plain='true' iconCls='icon-search'>错题统计</a>";
					return html;
				}
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){
			  $('.easyui-linkbutton').linkbutton();
		  }
	 });
}
var examId = 0;
function showDetail(ksId, name) {
	examId = ksId;
	loadCombotree();
	$("#query_username").val("");
	$("#query_state").combobox("setValue", -1);
	$("#detailDialog").dialog({
		title : name
	});
	$("#detailDialog").dialog("open");
	//用户名、分组、考试状态、得分、开始考试时间、交卷时间、是否及格、平均分差
	//查询条件，用户名、分组、状态
	$('#detailDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#tb",
		sortable:true,
		url: baseUrl + '/admin/exam/queryKsUserByKsId.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
//		sortName : 'ksId',//当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		queryParams : {"ksId":ksId},
		columns:[[ {
				field : 'username',
				title : '用户名', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 50,
				formatter : function(value, row, index) {
					return row.userCustom.username;
				}
			}, {
				field : 'group',
				title : '用户组', 
				valign : "middle",
	            align : "center",
				width : 100,
				formatter : function(value, row, index) {
					return row.userCustom.groupCustom.groupName;
				}
			},{
				field : 'state', 
				title : '状态', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					switch(row.state) {
					case 0:
						return "未报名";
					case 1:
						return "报名中";
					case 2:
						return "未考试";
					case 3:
						return "考试中";
					case 4:
						return "已交卷";
					case 5:
						return "已评分";
					default:
						return "错误";
					}
				}
			},{
				field : 'beginTimeString', 
				title : '开始时间', 
				valign : "middle",
				align : "center",
				width : 120
			},{
				field : 'endTimeString', 
				title : '结束时间', 
				valign : "middle",
				align : "center",
				width : 120
			},{
				field : 'score',
				title : '得分', 
				valign : "middle",
				align : "center",
				width : 80
			},{
				field : 'isPass', 
				title : '是否及格', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					if(row.score >= row.ksDataCustom.passScore)
						return "及格";
					else 
						return "不及格";
				}
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){
			  $('.easyui-linkbutton').linkbutton();
		  }
	 });
}
function showErrorResult(ksId, name) {
	$("#errorReusltKsId").val(ksId);
	$("#errorResultDialog").dialog({
		title : name + "-错题统计"
	});
	$("#errorResultDialog").dialog("open");
	//下拉列表添加“全部”选项
	$.ajax({ 
		url: baseUrl + '/admin/exam/getKsUserGroupsByKsId.json' ,
		dataType: 'json',
		type: "post",
		data: {"ksId" : ksId},
		success: function(data){
			data.unshift({"groupId" : -1, "groupName" : "全部"});
			$("#groupCbx").combobox({
				data : data,
				editable : false,
				valueField:'groupId',
				textField:'groupName',
				onLoadSuccess : function(node, da) {
					$(this).combobox("select", data[0].groupId);
				},
				onChange: function (newValue, oldValue) {
					if(oldValue != '') {
						$('#errorResultDg').datagrid('load',{
							'ksId' : $("#errorReusltKsId").val(),
							'groupId': $('#groupCbx').val()
						});
					}
                }
			});
		}
	});
	$('#errorResultDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
//		pageSize : 15,//默认选择的分页是每页15行数据
//		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#errorResultTb",
		sortable:true,
		url: baseUrl + '/admin/exam/getKsUserStResult.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '正在统计中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
//		sortName : 'ksId',//当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		queryParams : {"ksId":ksId},
		columns:[[ {
				field : 'qsnId',
				title : '题目Id', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 50,
				formatter : function(value, row, index) {
					return row.qsnId;
				}
			}, {
				field : 'title',
				title : '题目', 
				valign : "middle",
	            align : "center",
				width : 100,
				formatter : function(value, row, index) {
					var title = row.stInfo.title;
					if(title.length>15){
						title =  title.substr(0,10) + "...";
					}
					return title + "<a href='javascript:;' onclick='previewSt("+row.qsnId+");' class='easyui-linkbutton'  plain='true' iconCls='icon-search'>预览</a>";
				}
			}, {
				field : 'classify',
				title : '题目分类', 
				valign : "middle",
	            align : "center",
				width : 100,
				formatter : function(value, row, index) {
					return row.stInfo.stClassifyCustom.name;
				}
			}, {
				field : 'rightCount',
				title : '正确人数', 
				valign : "middle",
	            align : "center",
	            sortable : true,
				width : 100,
				formatter : function(value, row, index) {
					return row.rightCount;
				}
			},{
				field : 'errorCount',
				title : '错误人数', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return row.errorCount;
				}
			},{
				field : 'sum',
				title : '总人数', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					return parseInt(row.errorCount) + parseInt(row.rightCount);
				}
			},{
				field : 'rate',
				title : '正确率', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return row.rate;
				}
			},{
				field : 'rightUsers',
				title : '正确学生', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					var html = "<a href='#' class='easyui-linkbutton' onclick='showUsers("+JSON.stringify(row.rightUsers)+");' plain='true' iconCls='icon-search'>查看</a>";
					return html;
				}
			},{
				field : 'errorUsers',
				title : '错误学生', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					var html = "<a href='#' class='easyui-linkbutton' onclick='showUsers("+JSON.stringify(row.errorUsers)+");' plain='true' iconCls='icon-search'>查看</a>";
					return html;
				}
			}
	      ]],
		  pagination : false,//分页
		  rownumbers : false,//行数
		  onLoadSuccess:function(data){
			  $('.easyui-linkbutton').linkbutton();
		  }
	 });
}
function previewSt(id) {
	parent.previewSt(id);
}
function showUsers(users) {
	var usernames = [];
	$.each(users, function(i) {
		usernames.push(users[i].username);
	});
	alert(JSON.stringify(usernames));
}
function querySubmit() {
	$('#detailDg').datagrid('load',{
		'ksId' : examId,
		'username': $('#query_username').val(),
		'groupId': $('#userGroupCombotree').combotree("getValue"),
		'state' : $("#query_state").val()
	});
}
function exportExcel() {
	exportFrm.ksId.value = examId;
	exportFrm.username.value = $('#query_username').val();
	exportFrm.groupId.value = $('#userGroupCombotree').combotree("getValue");
	exportFrm.state.value = $("#query_state").val();
	exportFrm.submit();
}