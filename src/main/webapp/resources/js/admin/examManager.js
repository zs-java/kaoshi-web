$(function() {
	//加载考试分类下拉选框
	$.ajax({ 
		url: baseUrl + '/admin/exam/getAllKsClassifyTree.json' ,
		dataType: 'json', 
		success: function(data){
			// 修改ajax返回的值
			data = [{'id':-1, 'text':'全部', 'state':'open', 'children':data}];
			$('#ksClassifyCombotree').combotree({            
				data:data,
				editable:false, //不可编辑
				onLoadSuccess:function(node,d) {
					$('#ksClassifyCombotree').combotree("setValue", d[0].id);
				}
			});
		}
	});
	//加载DataGrid
	loadKsDg();
});
//加载考试DataGrid
function loadKsDg() {
	$('#ksDg').datagrid({
		  iconCls : 'icon-ok',
		  //width : fixWidth(1),
		  pageSize : 15,//默认选择的分页是每页5行数据
		  pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		  nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		  striped : true,//设置为true将交替显示行背景。
		  collapsible : true,//显示可折叠按钮
		  toolbar:"#tb",
		  sortable:true,
		  url: baseUrl + '/admin/exam/queryKsDataList.json',
		  //toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		  loadMsg : '数据装载中......',
		  singleSelect:false,//为true时只能选择单行
		  fitColumns:true,//允许表格自动缩放，以适应父容器
		  fit:true,
		  sortName : 'insDateString',//当数据表格初始化时以哪一列来排序
		  sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		  /* remoteSort : false, */
	      columns:[[
			{field:'ck',checkbox:true},
			{field:'name',title:'考试名称', width:150},
			{field:'classifyName',title:'考试分类', width:100,
				formatter:function(value, row, index) {
					return row.ksClassifyCustom.name;
				}
			},
			{field:'totalScore', title:'考试总分', width:50,
				formatter : function(value, row, index) {
					return row.sjDataCustom.totalScore;
				}
			},
			{field:'passScore', title:'及格分数', width:50},
			{field:'reviewStuts',title:'审核状态', width:100,
				formatter:function(value, row, index) {
					var stuts = row.review;
					if(stuts == true)
				 			return '已审核';
					else if(stuts == false) 
						return '未审核';
					else 
						return 'error';
				}
			},
			{field:'begin_end_date',title:'考试日期', width:330,
				formatter:function(value, row, index) {
					return row.beginTimeString + "~" + row.endTimeString;
				}
			},
			{field:'insUser',title:'创建人', width:100},
			{field:'insDateString', title:'创建时间', wdith:150},
			{field:'cz',title:'操作', width:220,
				formatter:function(value,row,index) {
					var btnHtml = "<a href='javascript:;' class='easyui-linkbutton' onClick='editKsData(" + row.ksId + ")'  plain='true' iconCls='icon-edit'>编辑</a>";
					btnHtml += "<a href='javascript:;' class='easyui-linkbutton' onClick='editKsUser(" + row.ksId + ")'  plain='true' iconCls='icon-edit'>添加考生</a>";
					btnHtml += "<a href='javascript:;' onclick='beforeDelete(" + row.ksId + ")' class='easyui-linkbutton' plain='true' iconCls='icon-no'>删除</a>";
					return btnHtml;
				}
			},
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){  
		        $('.easyui-linkbutton').linkbutton();  
		  },
		  onClickCell: function (rowIndex, field, value) {
				//IsCheckFlag = false;
		  },
	 });
}
//查询
function querySubmit() {
	$('#ksDg').datagrid('load',{
		'ksDataCustom.name': $('#query_name').val(),
		'ksDataCustom.insUser': $('#query_insUser').val(),
		'ksDataCustom.ksClassifyId': $('#ksClassifyCombotree').combotree("getValue"),
	});
}
//添加考试
function addExam() {
	parent.addTab('新建考试', baseUrl + '/admin/exam/addExam.html');
	//window.parent.closeTabByTitle("考试管理");
}
//编辑考试
function editKsData(ksId) {
	window.parent.closeTabByTitle("编辑考试");
	parent.addTab('编辑考试', baseUrl + '/admin/exam/editExam.html?ksId=' + ksId);
}
//删除之前执行
function beforeDelete(ksId) {
	$.messager.confirm('提示', '确认要删除该考试吗？', function(r){
		if (r)
			deleteKs(ksId);
	});
}
//删除考试
function deleteKs(ksId) {
	$.ajax({
		url : baseUrl + "/admin/exam/deleteExamById.action",
		type : "post",
		data : {"ksId" : ksId},
		dataType : "json",
		success : function(result) {
			if(result.msg == 'success') {
				msgShow("删除成功！");
				querySubmit();
			} else if (result.msg == 'error') {
				msgShow("删除失败！");
				querySubmit();
			}
		}
	});
}
function editKsUser(ksId) {
	$.ajax({
		url : baseUrl + "/admin/exam/isCanAddUser.action",
		dataType : "json",
		data : {"ksId" : ksId},
		success : function(result) {
			if(result.msg == 'success') {
				loadUserDg(ksId);
			} else if (result.msg == 'error') {
				msgShow(result.info);
			}
		}
	});
}
var examId = 0;
function loadUserDg(ksId) {
	examId = ksId;
	loadCombotree();
	$("#usersDia").dialog("open");
	//用户名、用户组、性别、真实姓名
	$('#userDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
//		toolbar:"#tb",
		sortable:true,
		url: baseUrl + '/admin/user/queryNotExamUsers.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:false,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
//		sortName : 'ksId',//当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		queryParams : {"ksId":ksId},
		columns:[[ {
				field:'ck',
				checkbox:true
			},{
				field : 'username',
				title : '用户名', 
				valign : "middle",
				align : "center",
				sortable : true,
				width : 50
			}, {
				field : 'group',
				title : '用户组', 
				valign : "middle",
	            align : "center",
				width : 100,
				formatter : function(value, row, index) {
					return row.groupCustom.groupName;
				}
			},{
				field : 'genderStr', 
				title : '性别',
				valign : "middle",
				align : "center",
				width : 120,
				formatter : function(value, row, index) {
					return row.gender;
				}
			},{
				field : 'readlyName', 
				title : '真实姓名', 
				valign : "middle",
				align : "center",
				width : 120
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){
			  $('.easyui-linkbutton').linkbutton();
		  }
	 });
	
}
function loadCombotree() {
	$("#userGroupComboxTree").combotree({
		url:baseUrl + '/admin/user/getAllGroups.json',
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
}

function seachUserByParam() {
	$('#userDg').datagrid('load',{
		'ksId' : examId,
		'username': $('#userLike').val(),
		'groupId': $('#userGroupComboxTree').combotree("getValue")
	});
}
function addUserToExam() {
	var checkedItems = $('#userDg').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.unshift(item.id);
	});
	if(ids.length == 0) {
		alert("请至少选中一行数据");
		return;
	}
	$.ajax({
		url : baseUrl + "/admin/exam/addUserToExam.action",
		type : "post",
		data : {"ids" : ids, "ksId" : examId},
		dataType : "json",
		success : function(result) {
			if(result.msg == 'success') {
				msgShow("添加成功！");
				seachUserByParam();
			} else if(result.msg == 'error') {
				msgShow("添加失败：" + result.info);
			}
		}
	});
}