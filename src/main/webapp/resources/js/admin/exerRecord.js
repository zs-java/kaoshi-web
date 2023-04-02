$(function() {
	loadGroupCombotree();
	loadDataGrid();
});
function loadGroupCombotree() {
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
/**
 * 渲染数据表格
 */
function loadDataGrid() {
	$('#exerDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#tb",
		//sortable:true,
		url: baseUrl + '/admin/exer/queryUserExer.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
//		sortName : 'totalCount',//当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		columns:[[ {
				field : 'username',
				title : '用户名', 
				valign : "middle",
				align : "center",
				//sortable : true,
				width : 50,
				formatter : function(value, row, index) {
					return row.user.username;
				}
			}, {
				field : 'group',
				title : '用户组', 
				valign : "middle",
	            align : "center",
				width : 100,
				formatter : function(value, row, index) {
					return row.user.groupCustom.groupName;
				}
			}, {
				field : 'dateString', 
				title : '刷题日期', 
				valign : "middle",
				align : "center",
				width : 120
			},{
				field : 'totalCount', 
				title : '刷题数量', 
				valign : "middle",
				align : "center",
				width : 120,
				sortable:true,
				sorter : function(a, b) {
//					return a > b ? 1 : -1;
					return 1;
				}
			},{
				field : 'rightCount', 
				title : '正确数量', 
				valign : "middle",
				align : "center",
				width : 80
			}, {
				field:'rightPercent', 
				title:'正确率', 
				valign : "middle",
				align : "center",
				width:80
			}, {
				field : 'cx',
				title : '操作', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					var id = row.user.id;
					var name = row.user.username;
					var date = row.dateString;
					return "<a href='#' class='easyui-linkbutton' onclick='showDetail("+id+", \""+name+"\", \""+date+"\");' plain='true' iconCls='icon-search'>详细</a>";
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
function querySubmit() {
	$('#exerDg').datagrid('load',{
		'username': $('#query_username').val(),
		'groupId': $('#userGroupCombotree').combotree("getValue"),
		'date' : $("#dateBox").val()
	});
}
function queryNotExer() {
	$('#exerDg').datagrid('load',{
		'username': $('#query_username').val(),
		'groupId': $('#userGroupCombotree').combotree("getValue"),
		'date' : $("#dateBox").val(),
		'type' : 1
	});
}
function showAll() {
	var row = $('#exerDg').datagrid('getSelected');
	if(row) {
		showDetail(row.user.id, row.user.username, '');
	}
}
function showDetail(userId, username, date) {
	var queryData = {};
	var title = "";
	if(date == '' || date == 'undefined') {
		queryData = {
			"userId" : userId
		};
		title = "用户 [" + username + "] 的全部刷题信息";
	} else {
		queryData = {
			"userId" : userId,
			"dateString" : date
		};
		title = "用户 [" + username + "] " + date + "的刷题信息";
	}
	
	$("#exerDialog").dialog({
		title : title
	});
	$("#exerDialog").dialog("open");
	$('#detailDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
//		toolbar:"#tb",
		nowrap : false,
		url: baseUrl + '/admin/exer/getExercisesByUserId.json',
		loadMsg : '数据装载中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
		queryParams : queryData,
		columns:[[ {
				field : 'dayDateString',
				title : '日期', 
				valign : "middle",
				align : "center",
				width : 120
			}, {
				field : 'classifyName',
				title : '试题分类', 
				valign : "middle",
	            align : "center",
				width : 150
			}, {
				field : 'knowledgeName', 
				title : '知识点分类', 
				valign : "middle",
				align : "center",
				width : 150
			},{
				field : 'totalCount', 
				title : '刷题数量', 
				valign : "middle",
				align : "center",
				width : 50
			},{
				field : 'rightCount', 
				title : '正确数量', 
				valign : "middle",
				align : "center",
				width : 50
			}, {
				field:'rightPercent', 
				title:'正确率', 
				valign : "middle",
				align : "center",
				width:60
			}, {
				field:'beginTimeString', 
				title:'开始时间', 
				valign : "middle",
				align : "center",
				width:150
			}, {
				field:'time', 
				title:'刷题时长/分钟',
				valign : "middle",
				align : "center",
				width:80
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){
//			  $('.easyui-linkbutton').linkbutton();
		  }
	 });
}
