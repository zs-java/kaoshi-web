<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题分类管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/easyui/demo/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/demo.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/zTreeStyle/zTreeStyle.css"/>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/datagrid-detailview.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
//ztree插件设置信息
var setting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		showRemoveBtn: showRemoveBtn,
		showRenameBtn: showRenameBtn
	},
	data: {
		simpleData: {
			enable: true,
			idKey:"classifyId",
			pIdKey:"pid"
			//rootPid:-1
		}
	},
	callback: {
		beforeDrag: beforeDrag,
		beforeEditName: beforeEditName,
		beforeRemove: beforeRemove,
		beforeRename: beforeRename,
		onRemove: onRemove,
		onRename: onRename
	}
};

//是否允许拖动
function beforeDrag(treeId, treeNodes) {
	return false;
}
function beforeEditName(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	setTimeout(function() {
		setTimeout(function() {
			zTree.editName(treeNode);
		}, 0);
	}, 0);
	return false;
}
function beforeRemove(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("此操作将把该节点以及该分组对应的所有子分组删除！确认删除节点 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
	$.ajax({
		url:'${pageContext.request.contextPath}/admin/setting/deleteStClassify.action',
		type:'POST',
		data:{ "classifyId":treeNode.classifyId },
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				//删除成功重新加载tree
				loadTree();
				setTimeout($.messager.show({
					title:'提示',
					msg:'删除成功！',
					timeout:3000
				}), 0);
			} else if(result.msg == 'error') {
				loadTree();
				setTimeout($.messager.show({
					title:'提示',
					msg:'删除失败！' + result.info,
					timeout:3000
				}), 0);
			}
		},
		error:function() {
			alert("error");
		}
	}); 
}
function beforeRename(treeId, treeNode, newName, isCancel) {
	if (newName.length == 0) {
		alert('beforeRename');
		setTimeout(function() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.cancelEditName();
			alert("节点名称不能为空.");
		}, 0);
		return false;
	}
	return true;
}
//重命名时执行
function onRename(e, treeId, treeNode, isCancel) {
	if(treeNode.name.length != 0) {
		//执行重命名
		$.ajax({
			url:'${pageContext.request.contextPath}/admin/setting/renameStClassify.action',
			type:'POST',
			//async: false,
			data:{'classifyId':treeNode.classifyId, 'newName':treeNode.name},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg == "success") {
					setTimeout($.messager.show({
						title:"提示",
						msg:"重命名成功！",
						timeout:2000
					}), 0);
					loadTree();
				} else if (result.msg == 'error'){
					isCancel = true;
					setTimeout($.messager.show({
						title:"提示",
						msg:"重命名失败！" + result.info,
						timeout:2000
					}), 0);
					loadTree();
				}
			}
		});
	}
}
function showRemoveBtn(treeId, treeNode) {
	return !(treeNode.pid == null);
}
function showRenameBtn(treeId, treeNode) {
	return !(treeNode.pid == null);
}
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	//if (treeNode.level == 0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	btn.className = 'button add';
	if (btn) btn.bind("click", function(){
		//添加分组
		$.ajax({
			url:'${pageContext.request.contextPath}/admin/setting/addStClassify.action',
			type:'POST',
			data:{'pid':treeNode.classifyId, 'newName':'new group'},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg=='success') {
					//添加成功，重新从数据库中获取分组，重新渲染树
					var successFunc = function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						var node = zTree.getNodeByParam("classifyId",result.info);
						zTree.checkNode(node, true, true);
						beforeEditName(result.info, node);
					};
					loadTree(successFunc);
					setTimeout($.messager.show({
						title:"提示",
						msg:"添加成功！",
						timeout:2000
					}), 0);
				} else if(result.msg == 'error') { 
					setTimeout($.messager.show({
						title:"提示",
						msg:"添加失败！" + result.info,
						timeout:2000
					}), 0);
				}
			}
		});
		//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//zTree.addNodes(treeNode, {id:(100 + 100), pId:treeNode.id, name:"ne	w node"});
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};
function loadTree(successFunc) {
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/setting/getAllStClassify.json",
		type:"POST",
		async: true,
		success:function(data) {
			var result = JSON.parse(data);
			var msg = result.msg;
			if(msg == 'success') {
				var tree = result.info;
				//向tree中添加根
				tree.unshift({classifyId:0, pid:-1, name:'根', open:true });
				$.fn.zTree.init($("#treeDemo"), setting, tree);
			} else if(msg == 'error') {
				alert(result.info);
			}
			if(successFunc != null) {
				successFunc();
			}
		}
	});
};
$(document).ready(function () {
	loadTree();
});
</script>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
}
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
</head>
<body>
<div class="content_wrap">
	<ul id="treeDemo" style="width: 250px;overflow: auto;" class="ztree"></ul>
</div>
</body>
</html>