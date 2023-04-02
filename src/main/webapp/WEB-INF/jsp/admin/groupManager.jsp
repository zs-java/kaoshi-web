<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/demo.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/ztree/css/zTreeStyle/zTreeStyle.css"/>
<script src="${pageContext.request.contextPath }/resources/js/easyui/jquery.min.js" type="text/javascript" charset="utf-8"></script>
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
			rootPid:0
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
	return confirm("此操作将把该分组以及该分组对应的所有子分组删除，并将其对应的用户移动到默认分组！确认删除 分组 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
	$.ajax({
		url:'${pageContext.request.contextPath}/admin/group/deleteGroup.action',
		type:'POST',
		data:{
			"groupId":treeNode.id,
			"groupPid":treeNode.pId
		},
		success:function(data) {
			var result = JSON.parse(data);
			if(result.msg == 'success') {
				//删除成功重新加载tree
				loadGroupTree();
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
			url:'${pageContext.request.contextPath}/admin/group/renameGroup.action',
			type:'POST',
			async: false,
			data:{'groupId':treeNode.id, 'newName':treeNode.name},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg == "success") {
					loadGroupTree();
				} else {
					isCancel = true;
					alert(result.msg);
				}
			}
		});
	}
}
function showRemoveBtn(treeId, treeNode) {
	return !(treeNode.pId == null);
}
function showRenameBtn(treeId, treeNode) {
	return true;
}
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	if (treeNode.level == 2) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	btn.className = 'button add';
	if (btn) btn.bind("click", function(){
		//添加分组
		$.ajax({
			url:'${pageContext.request.contextPath}/admin/group/addGroup.action',
			type:'POST',
			data:{'groupPid':treeNode.id, 'groupName':'new group'},
			success:function(data) {
				var result = JSON.parse(data);
				if(result.msg=='success') {
					//添加成功，重新从数据库中获取分组，重新渲染树
					var successFunc = function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						var node = zTree.getNodeByParam("id",result.newGroupId);
						zTree.checkNode(node, true, true);
						beforeEditName(result.newGroupId, node);
					};
					loadGroupTree(successFunc);
				} else { 
					alert(result.msg);
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
function loadGroupTree(successFunc) {
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/group/getAllGroupTree.json",
		type:"POST",
		async: true,
		success:function(data) {
			$.fn.zTree.init($("#treeDemo"), setting, JSON.parse(data));
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
			if(successFunc != null) {
				successFunc();
			}
		}
	});
};
$(document).ready(function () {
	loadGroupTree();
});
</script>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
</head>
<body>
	<div class="content_wrap">
		<ul id="treeDemo" style="width: 250px;overflow: auto;" class="ztree"></ul>
	</div>
</body>
</html>