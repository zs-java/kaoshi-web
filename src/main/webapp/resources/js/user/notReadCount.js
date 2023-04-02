$(function() {
	loadNotRead();
});
//加载未读信息提示
function loadNotRead() {
	//加载未读信息提示
	$.ajax({
		url : baseUrl + "/user/getNotReadCount.json",
		type : "GET",
		dataType : "json",
		success : function(data) {
			//badge badge-warning
			//未读消息总数
			var notReadAllCount = parseInt(data.notReadCountAll);
			//未读考试数量
			var kuNotReadCount = parseInt(data.ksUserNotReadCount);
			//未读成绩数量
			var notReadResult = parseInt(data.notReadResult);
			if(notReadAllCount > 0) {
				$("#notReadAll").html(notReadAllCount);
				$("#notReadAll").addClass("badge badge-warning");
			}
			if(kuNotReadCount > 0) {
				$("#notReadKs").html(kuNotReadCount);
				$("#notReadKs").addClass("label label-warning pull-right");
			}
			if(notReadResult > 0) {
				$("#notReadResult").html(notReadResult);
				$("#notReadResult").addClass("label label-warning pull-right");
			}
		}
	});
}