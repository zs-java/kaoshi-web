$(function(){
	//加载试题分类下拉多选框
	$.post(baseUrl + "/user/shiti/getAllStClassifyTree.json", {}, function(data){
		data = [{"id":-1,"text":"全部","state":"open","children":data}];
		$("#stClassifyTree").tree({
			data : data,
		    editable : false,
		    checkbox : true
		});
	}, "json");
	//加载试题知识点下拉多选框
	$.post(baseUrl + "/user/shiti/getAllStKnowledgeTree.json", {}, function(data){
		data = [{"id":-1,"text":"全部","state":"open","children":data}];
		$("#stKnowledgeTree").tree({
			data : data,
			editable: false,
		    checkbox:true
		});
	}, "json");
});
function startExer() {
	var classifyNodes = $("#stClassifyTree").tree("getChecked");
	if(classifyNodes.length == 0) {
		alert("请至少选中一个试题分类节点！");
		return;
	}
	var classifyIds = [];
	for(var i=0;i<classifyNodes.length;i++) {
		classifyIds.push(classifyNodes[i].id);
	}
	var knowledgeNodes = $("#stKnowledgeTree").tree("getChecked");
	if(knowledgeNodes.length == 0) {
		alert("请至少选中一个试题知识点节点！");
		return;
	}
	var knowledgeIds = [];
	for(var i=0;i<knowledgeNodes.length;i++) {
		knowledgeIds.push(knowledgeNodes[i].id);
	}
	$.post(baseUrl + "/user/startExer.action", {"classifyIds":classifyIds, "knowledgeIds":knowledgeIds},
			function(data){
		if(data.msg == 'success') {
			location = baseUrl + "/user/exercising.html?exerId=" + data.info;
		} else if (data.msg == 'error') {
			alert("操作失败：" + data.info);
		}
	}, "json");
}
