$(function() {
	//下拉列表添加“全部”选项
	$.ajax({ 
		url: baseUrl + '/admin/exam/getExamList.json' ,
		dataType: 'json', 
		success: function(data){
			data.unshift({"id" : -1, "text" : "全部"});
			$("#allExamCombobox").combobox({
				data : data,
				editable : false,
				valueField:'id',
				textField:'text',
				onLoadSuccess : function(node, da) {
					$(this).combobox("select", data[0].id);
				}
			});
		}
	});
	//load datagrid...
	loadExamDataGrid();
	loadUserGrid();
});
/**
 * 渲染数据表格
 */
function loadExamDataGrid() {
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
		url: baseUrl + '/admin/exam/queryExamMonitorList.json',
		//toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		loadMsg : '数据装载中......',
		singleSelect:true,//为true时只能选择单行
		fitColumns:true,//允许表格自动缩放，以适应父容器
		fit:true,
		sortName : 'ksId',//当数据表格初始化时以哪一列来排序
		sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
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
			}, {
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
				field : 'totalCount', 
				title : '总人数', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					return "<a href='javascript:alert(1);' style='color:blue;'>" + row.totalCount + "</a>";
				},
				sortable : true,
				sorter : function(a, b) {
					alert(a);
					alert(b);
//					return a > b ? 1 : -1;
					return -1;
				}
			}, {
				field:'notJoinCount', 
				title:'未考试人数', 
				valign : "middle",
				align : "center",
				width:80,
				formatter : function(value, row, index) {
					return "<a href='javascript:alert(1);' style='color:blue;'>" + row.notJoinCount + "</a>";
				}
			}, {
				field : 'inExamCount',
				title : '考试中人数', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					return "<a href='javascript:alert(1);' style='color:blue;'>" + row.inExamCount + "</a>";
				}
			}, {
				field : 'examOverCount',
				title : '已交卷人数', 
				valign : "middle",
				align : "center",
				width : 80,
				formatter : function(value, row, index) {
					return "<a href='javascript:alert(1);' style='color:blue;'>" + row.examOverCount + "</a>";
				}
			}
	      ]],
		  pagination : true,//分页
		  rownumbers : true,//行数
		  onLoadSuccess:function(data){  
		  }
	 });
}
function loadUserGrid() {
	
}
//条件查询
function querySubmit() {
	$('#kuDg').datagrid('load',{
		'ksMonitor.ksId': $('#allExamCombobox').val(),
	});
}