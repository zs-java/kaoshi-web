<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="main">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<SCRIPT type="text/javascript">
	<!--
	var curMenu = null, zTree_Menu = null;
	var setting = { 
		view: {
			showLine: true,
			selectedMulti: false,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onNodeCreated: this.onNodeCreated,
			beforeClick: this.beforeClick,
			onClick: this.onClick
		}
	};

	var zNodes =[
		<c:forEach items="${moduleCustomList }" var="moduleCustom">
			{ id:'${moduleCustom.moduleId }', pId:0, name:'${moduleCustom.name }', open:true },
			<c:forEach items="${moduleCustom.functions }" var="function">
			<c:if test="${function.url not empty}">
			{ id:'10${function.functionId }', pId:'${function.moduleId}',  name:'${function.name }', open:false/*,  url:'${pageContext.request.contextPath }/${function.url}' */},
			</c:if>
			</c:forEach>
		</c:forEach>
	];
	
	var functionsUrl = {
		<c:forEach items="${moduleCustomList }" var="moduleCustom">
			<c:forEach items="${moduleCustom.functions }" var="function">
			<c:if test="${function.url not empty}">
			"${function.functionId}":"${function.url}",
			</c:if>
			</c:forEach>
		</c:forEach>
	};
	
	function beforeClick(treeId, node) {
		if (node.isParent) {
			if (node.level === 0) {
				var pNode = curMenu;
				while (pNode && pNode.level !==0) {
					pNode = pNode.getParentNode();
				}
				if (pNode !== node) {
					var a = $("#" + pNode.tId + "_a");
					a.removeClass("cur");
					zTree_Menu.expandNode(pNode, false);
				}
				a = $("#" + node.tId + "_a");
				a.addClass("cur");

				var isOpen = false;
				for (var i=0,l=node.children.length; i<l; i++) {
					if(node.children[i].open) {
						isOpen = true;
						break;
					}
				}
				if (isOpen) {
					zTree_Menu.expandNode(node, true);
					curMenu = node;
				} else {
					zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
					curMenu = node.children[0];
				}
			} else {
				zTree_Menu.expandNode(node);
			}
		}
		return !node.isParent;
	}
	function onClick(e, treeId, node) {
		/* alert("treeId:"+treeId+",node:"+node+",e:"+e); */
		var moduleId = node.pId;
		var functionId = node.id - 100;
		var a = $("#clickAtag");
		a.href = "${pageContext.request.contextPath }/"+functionsUrl[functionId];
		a.click();
		window.open("${pageContext.request.contextPath }/"+functionsUrl[functionId],"main");
		/* alert(functionsUrl[functionId]); */
		/* alert(moduleId);
		alert(functionId); */
		
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
		zTree_Menu.selectNode(curMenu);
		var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
		a.addClass("cur");
	});
	//-->
</SCRIPT>

<style type="text/css">
.ztree li a.level0 {width:200px;height: 20px; text-align: center; display:block; background-color: #0B61A4; border:1px silver solid;}
.ztree li a.level0.cur {background-color: #66A3D2; }
.ztree li a.level0 span {display: block; color: white; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
.ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
.ztree li span.button.switch.level0 {display:none;}
#treeDemo {margin-top: 0px;}
	</style>
</head>
<body style="overflow: scroll;overflow-x:hidden;overflow-y:hidden;">
	<div class="zTreeDemoBackground left" style="position:absolute; left:0; top:0px; width:100%; height:100%;">
		<ul id="treeDemo" class="ztree" style="width:100%; height:100%;"></ul>
		<a href="" target="main" id="clickAtag"></a>
	</div>
</body>
</html>