$(function() {
	//加载考试分类下拉选框
	$.ajax({ 
		url: baseUrl + '/admin/exam/getAllKsClassifyTree.json' ,
		type : "post",
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
	//加载dataGrid
	loadDataGrid();
});
//加载dataGrid
function loadDataGrid() {
	$('#kuDg').datagrid({
		iconCls : 'icon-ok',
		//width : fixWidth(1),
		pageSize : 15,//默认选择的分页是每页15行数据
		pageList : [ 10, 15, 20, 25, 50, 150, 300 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#tb",
		sortable:true,
		url: baseUrl + '/admin/exam/queryUnSignupKsUser.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:false,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
		sortName : 'insDateString',//当数据表格初始化时以哪一列来排序
		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		/* remoteSort : false, */
		columns:[[ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'name',
				title : '考试名称', 
				width : 150
			}, {
				field : 'classifyName',
				title : '考试分类', 
				width : 100,
				formatter : function(value, row, index) {
					return row.ksClassifyCustom.name;
				}
			}, {
				field : 'beginTime', 
				title : '开始时间', 
				width : 70,
				formatter : function(value, row, index) {
					return row.ksDataCustom.beginTimeString;
				}
			},{
				field : 'endTime', 
				title : '结束时间', 
				width : 70,
				formatter : function(value, row, index) {
					return row.ksDataCustom.endTimeString;
				}
			},{
				field : 'totalScore', 
				title : '考试总分', 
				width : 70,
				formatter : function(value, row, index) {
					return row.sjDataCustom.totalScore;
				}
			}, {
				field:'groupName', 
				title:'用户组', 
				width:70,
				formatter : function(value, row, index) {
					return userCustom.groupCustom.groupName;
				}
			}, {
				field : 'readlyName',
				title : '真实姓名', 
				width : 100,
				formatter : function(value, row, index) {
					return userCustom.readly;
				}
			}, {
				field : 'username',
				title : '用户登录名', 
				width : 330,
				formatter : function(value, row, index) {
					return row.userCustom.username;
				}
			}, {
				field : 'signupTime',
				title : '报名时间', 
				width : 100
			}, {
				field : 'sh', 
				title:'状态设置', 
				wdith:150,
				formatter : function(value, row, index) {
					return "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='signuping(" + row.id + ");'/>通过</a>";
				}
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){  
		        $('.easyui-linkbutton').linkbutton();  
		  },
		  onClickCell: function (rowIndex, field, value) {
				//IsCheckFlag = false;
		  }
	 });
}
//报名通过
function signuping(id) {
	
}