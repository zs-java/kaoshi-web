$(function() {
	var now = new Date();
	var beginDate = new Date(beginDateString.replace(/-/g,"/"));
	if(now.getTime() > beginDate.getTime()) {
		//已经开始考试
		$("#time").html("倒计时：正在考试......");
	} else {
		var intDiff = parseInt((beginDate.getTime() - now.getTime()) / 1000);
		timer(intDiff);
	}
});

//立即考试
function beginExam(ksId, kuId) {
	$.ajax({
		url : baseUrl + "/user/isCanBeginExam.action",
		type : "post",
		data : {"kuId" : kuId},
		dataType : "json",
		success : function(result) {
			if(result.msg == "success") {
//				$.messager.popup("可以开始考试 ！");	
				location = baseUrl + "/user/goExam.html?kuId=" + kuId;
			} else if (result.msg == "error") {
				$.messager.popup(result.info);
			}
		}
	});
}
//计时器
function timer(intDiff){
	window.setInterval(function(){
	var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值		
	if(intDiff > 0){
		day = Math.floor(intDiff / (60 * 60 * 24));
		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	}
	if (minute <= 9) minute = '0' + minute;
	if (second <= 9) second = '0' + second;
	$('#day_show').html(day+"天");
	$('#hour_show').html('<s id="h"></s>'+hour+'时');
	$('#minute_show').html('<s></s>'+minute+'分');
	$('#second_show').html('<s></s>'+second+'秒');
	intDiff--;
	}, 1000);
} 