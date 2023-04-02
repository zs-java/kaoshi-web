<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
window.onload = function () {
	 loadUserDataGrid();
	 loadRoleCombobox();
	 loadSelectComboTree();
};
function loadUserDataGrid() {
	$('#userDg').datagrid({
		  iconCls : 'icon-ok',
		  //width : fixWidth(1),
		  pageSize : 15,//默认选择的分页是每页5行数据
		  pageList : [ 10, 15, 20, 25 ],//可以选择的分页集合
		  nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		  striped : true,//设置为true将交替显示行背景。
		  collapsible : true,//显示可折叠按钮
		  toolbar:"#tb",
		  url:'${pageContext.request.contextPath }/admin/user/queryUserList.json',
		  //toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		  loadMsg : '数据装载中......',
		  singleSelect:false,//为true时只能选择单行
		  fitColumns:true,//允许表格自动缩放，以适应父容器
		  fit:true,
		  sortName : 'id',//当数据表格初始化时以哪一列来排序
		  sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		  /* remoteSort : false, */
	      columns:[[
	        {field:'ck',checkbox:true},
			{field:'id',title:'用户编号 ',hidden:'true'},
			{field:'username',title:'用户名', width:140},
			{field:'roleName',title:'角色', width:70,
          	formatter:function(value, row, index) {
          		return row.roleCustom.roleName;
          	}
          },
          {field:'groupName',title:'所属组别', width:140,
          	formatter:function(value, row, index) {
          		//alert($.isEmptyObject(row.grouopCustom));
          		if(row.groupCustom != null)
          			return row.groupCustom.groupName;
          		else
          			return "";
          	}
     		},
     		{field:'readlyName',title:'真实姓名', width:140},
          {field:'gender',title:'性别', width:50,formatter:function(value, row, index) {
          		if(row.gender==1)
          			return "男";
          		else 
          			return "女";
          	}
          },
          {field:'email',title:'邮箱', width:150},
          {field:'loginCount',title:'登陆次数', width:60},
          {field:'uuid',title:'操作', width:140,
        	  formatter:function(value,row,index) {
        		  var btnHtml = "<a href='#' class='easyui-linkbutton' onclick='batchProcessing("+row.id+")' plain='true' iconCls='icon-edit'>编辑</a>";
        		  btnHtml += "<a href='${pageContext.request.contextPath}/admin/user/deleteUser.action?id="+row.id+"' class='easyui-linkbutton' plain='true' iconCls='icon-no'>删除</a>";
        		  return btnHtml;
        	}
          },
	      ]],
	      /* frozenColumns:[[{
	    	  field:'ck',
	    	  checkbox:true
	      }]], */
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
function loadRoleCombobox() {
	//下拉列表添加“全部”选项
	$.ajax({ 
		url: '${pageContext.request.contextPath }/admin/user/getAllRoles.json' ,
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
}
function loadSelectComboTree() {
	$("#query_group").combotree({
		url:'${pageContext.request.contextPath }/admin/user/getAllGroups.json',
		valueField:'id',    
	    textField:'text',
	    editable: false,
	    	onLoadSuccess:function(node,data) {
	    		 var t = $("#query_group").combotree('tree');//获取tree  
	             for (var i=0;i<data.length ;i++ ){
	                node= t.tree("find",data[i].id);  
	                t.tree('expandAll',node.target);//展开所有节点  
	             }
	             $("#query_group").combotree('setValue',data[0].id);
	    	}
	});
}
function fixWidth(percent) {
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
}
function querySubmit() {
	$('#userDg').datagrid('load',{
		'roleCustom.roleId': $('#roleCombobox').val(),
		'userCustom.username': $('#query_username').val(),
		'userCustom.readlyName':$("#query_readlyName").val(),
		'groupCustom.groupId':$('#query_group').combobox("getValue"),
		'userGender':$('#query_gender').val()
	});
}
function windowReload() {
	location.reload();
}
function deleteList() {
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
		url:'${pageContext.request.contextPath }/admin/user/deleteUserList.action',
		dataType:'text',
		data:{ 'ids': ids },
		type:'post',
		success: function(data,textStatus) { //此处data为返回值
			if(data == "success")
				location.reload();
	    }
	});
}
function batchProcessing(id){  
    parent.openEditDialog(id);  
} 
function addUser() {
	parent.addTab('新建用户', '${pageContext.request.contextPath}/admin/user/addUser.html');
}
</script>
<style type="text/css">
#managerDiv {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	right: 0px;
}
#userDg {
	width: 500px;
	height: 100%;
	position: absolute;
	right: 0px;
	border: 1px solid red;
}
#userDg tr td {
	font-size: 20px;
}
.easyui-datagrid tr{
	height: 30px;
}
#addUserBtn {
	/* float: left; */
	position: absolute;
	right: 15px;
}
.datagrid-btable tr{
	height: 30px;
	text-align: center;
}
.datagrid-row-selected {
  background: #E0ECFF;
  color: black;
}
</style>
</head>
<body>
	<div id="managerDiv">
		<table id="userDg" class="easyui-datagrid" style="position: absolute;left:0px;right:0px;height: 100%;">
		</table>
		<div id="tb" style="padding:3px">
			<span>用户组：</span>
			<select id="query_group" class="easyui-combotree" style="width:156px;">
				<!-- <option>全部</option> -->
			</select>
			<span>用户名：</span>
			<input id="query_username" style="line-height:22px;border:1px solid #ccc;width: 120px;">
			<span>真实姓名：</span>
			<input id="query_readlyName" style="line-height:22px;border:1px solid #ccc;width: 120px;">
			<span>角色：</span>
			<input id="roleCombobox" class="easyui-combobox" name="dept" style="width: 100px;" />
			<span>性别：</span>
			<select id="query_gender" class="easyui-combobox" style="width: 100px;" data-options="editable:false">
				<option value="-1">全部</option>
				<option value="1">男</option>
				<option value="0">女</option>
			</select>
			<a href="#" class="easyui-linkbutton" onclick="querySubmit();" plain="true" iconCls="icon-search">查询</a>
			<a href="#" class="easyui-linkbutton" onclick="deleteList();" plain="true" iconCls="icon-cancel">删除</a>
			<a href="#" class="easyui-linkbutton" onclick="windowReload();" plain="true" iconCls="icon-reload">刷新</a>
			<a href="#" id="addUserBtn" class="easyui-linkbutton" onclick="addUser();" plain="false" iconCls="icon-add">添加用户</a>
		</div>
	</div>
</body>
</html>