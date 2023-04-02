var curQsnData;
var luanxuArr;
$(function() {
	nextQuestion();
	loadExerInfo();
});
function loadExerInfo() {
	$.ajax({
		url : baseUrl + "/user/getExercisesById.json",
		type : "get",
		data : {"exerId":exerId},
		dataType : "json",
		success : function(data) {
			$("#beginTime").html(data.beginTimeString);
			$("#totalCount").html(data.totalCount);
			$("#rightCount").html(data.rightCount);
			$("#rightPercent").html(data.rightPercent);
		}
	});
}
var xuanX=["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
function submit() {
	submitResult();
}
function submitResult() {
	var R = $("#result");
	var html = "<strong>正确答案：</strong>";
	if(curQsnData.typeId == 1) {
		//当前选择的答案
		var xx = $('input[name="danxuan_0_xx"]:checked').val();
		//提交答案
		$.ajax({
			url : baseUrl + "/user/submitExerDanxuan.action",
			type : "post",
			async : false,//同步请求提交
			dataType : "json",
			data : {
				"subKey" : xx,
				"qsnId" : curQsnData.questionId,
				"exerId" : exerId
			},
			success : function(result) {
				if(result.msg == 'success') {
					//请求成功
					var info = result.info;
					$("#qsnDetail").show();
					html+="<strong>"+xuanX[info.rightKey]+"</strong>";
					if(info.result == true) {
						html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
					} else {
						html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
					}
				} else {
					alert(result.info);
					return;
				}
			}
		});
	} else if(curQsnData.typeId == 2) {
		//获取用户答案
		var keys = [];
		var keyEles = $('input[name="duoxuan_0_xx"]:checked').each(function(){
			keys.push($(this).val());
		});
		keys.sort(function(a, b) {
			return a - b;
		});
		$.ajax({
			url : baseUrl + "/user/submitExerDuoxuan.action",
			type : "post",
			async : false,//同步请求提交
			dataType : "json",
			data : {
				"subKey" : keys,
				"qsnId" : curQsnData.questionId,
				"exerId" : exerId
			},
			success : function(result) {
				if(result.msg == 'success') {
					//请求成功
					var info = result.info;
					$("#qsnDetail").show();
//					html+="<strong>"+xuanX[info.rightKey]+"</strong>";
					for(var i=0;i<info.rightKey.length;i++) {
						html+="<strong>"+xuanX[info.rightKey[i]] + ",</strong>";
					}
					if(info.result == true) {
						html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
					} else {
						html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
					}
				} else {
					alert(result.info);
					return;
				}
			}
		});
	} else if(curQsnData.typeId == 3) {
		//获取判断题用户答案
		var key = $('input[name="panduan_0_daan"]:checked').val();
		$.ajax({
			url : baseUrl + "/user/submitExerPanduan.action",
			type : "post",
			async : false,//同步请求提交
			dataType : "json",
			data : {
				"subKey" : key,
				"qsnId" : curQsnData.questionId,
				"exerId" : exerId
			},
			success : function(result) {
				if(result.msg == 'success') {
					//请求成功
					var info = result.info;
					$("#qsnDetail").show();
					if(info.rightKey==1)
						html+="<img src='"+baseUrl+"/resources/img/ok.png' />";
					else
						html+="<img src='"+baseUrl+"/resources/img/cancel.png' />";
					if(info.result == true) {
						html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
					} else
						html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
				} else {
					alert(result.info);
					return;
				}
			}
		});
	} else if(curQsnData.typeId == 4) {
		$.ajax({
			url : baseUrl + "/user/submitExerTiankong.action",
			type : "post",
			async : false,//同步请求提交
			dataType : "json",
			data : {
//				"subKey" : key,
				"qsnId" : curQsnData.questionId,
				"exerId" : exerId
			},
			success : function(result) {
				if(result.msg == 'success') {
					//请求成功
					var info = result.info;
//					$("#qsnDetail").show();
//					if(info.rightKey==1)
//						html+="<img src='"+baseUrl+"/resources/img/ok.png' />";
//					else
//						html+="<img src='"+baseUrl+"/resources/img/cancel.png' />";
//					if(info.result == true) {
//						html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
//					} else
//						html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
				} else {
					alert(result.info);
					return;
				}
			}
		});
	}
	R.html(html);
	loadExerInfo();
	$("#see").hide();
	$("#timer").show();
	setTimeout(timer, 1000);
	return;
}
function timer() {
	var str = $("#number").html();
	var num = parseInt(str);
	if(num == 0) {
		$("#timer").hide();
		$("#number").html("5");
		$("#down").show();
	} else {
		num -= 1;
		$("#number").html(num);
		setTimeout(timer, 1000);
	}
}
function nextQuestion() {
	$.post(baseUrl + "/user/getNextQuestion.json", {"exerId":exerId}, function(data){
		if(data.msg == 'success') {
			reLoadNextStInfo(data.info);
		} else if(data.msg == 'isEnd') {
			alert("该类题目不存在，请重新选择题目类型！");
		} else if(data.msg == 'error') {
			alert("发生错误：" + data.info);
		}
	}, "json");
}
function reLoadNextStInfo(qsnData) {
	curQsnData = qsnData;
	var qsnIndex = 0;
	var index = 0;
	var qsnHtml='<table class="shitiTable" id="shiti_'+qsnIndex+'" did="'+index+'" shitiId='+qsnData.questionId+' border="0" cellpadding="0" cellspacing="0">';
	//stNumHtml+='<li class="danxuankaNumber_'+index+' daTiKa" id="biao_'+qsnIndex+'" style="border-top: 1px solid #ccc; cursor: pointer;"onclick="showQsn('+qsnIndex+')">'+qsnIndex+'</li>';
	qsnHtml+=loadQsnInfo(0,qsnData,0,0);
	qsnHtml+='</table>';
	qsnHtml+="<div id='qsnDetail' style='display:none;'><h4>试题解析：</h4><div>" + qsnData.detail + "</div><div>";
	qsnHtml+="<div id='result'></div>";
	$("#Container").html(qsnHtml);
	$("#down").hide();
	$("#see").show();
}
function loadQsnInfo(qindex,qsnData,qsnfen,isSuiJi){
	var suffixStr="1";
	var ksdaan;
	var defen=0;
	if(typeof(dnandata) != "undefined"&&dnandata!=null){
		var qsnid=qsnData.questionId;
		if(isSuiJi==0 && qsnData.typeId<6){
			for(var i=0;i<dnandata.length;i++){
				if(dnandata[i].sid==qsnid){
					ksdaan=dnandata[i].ksdaan;
					defen=dnandata[i].defen;
					break;
				}
			}
		}else if(isSuiJi==1 && qsnData.typeId<6){
			for(var i=0;i<rdaandata.length;i++){
				if(rdaandata[i].sid==qsnid){
					ksdaan=rdaandata[i].ksdaan;
					defen=rdaandata[i].defen;
					break;
				}
			}
		}
	}
	// 试题标题
	var daan=JSON.stringify(qsnData.rightKey);
	var gdQsnHtml='<tr id="dl_qsn_'+qindex+'" suiji="0" defen="'+defen+'"  class="shiti" shitiId="'+qsnData.questionId+'" fen="'+qsnfen+'" shitiType="'+qsnData.typeId+'"><th colspan="3" align="vertical-align: top;">题目:<span class="title">'+qsnData.title+'</span></th>'
	+'<th width="100"></th></tr>';
	// 单选题
	if("options" in qsnData && qsnData.typeId == 1){
		gdQsnHtml += '<tr id="danxuan_'+qindex+'"><td width="720"><ol>';
		
		var options = qsnData.options;
		var optionsLength=getJsonLength(options);
		for(var num=1;num<=optionsLength;num++) {
			gdQsnHtml +='<li indexid='+qindex+'_'+num+'>';
			gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" id="danxuan_'+qindex+'_'+num+'_xx" class="inputStyle"/>';
			gdQsnHtml +=options[num].option+'</label></li>';
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 多选题
	if("options" in qsnData && qsnData.typeId == 2){
		gdQsnHtml += '<tr id="duoxuan_'+qindex+'"><td width="720"><ol>';
		var options = qsnData.options;
		var optionsLength=getJsonLength(options);
		for(var num=1;num<=optionsLength;num++) {
			gdQsnHtml +='<li indexid='+qindex+'_'+num+' >';
			gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+num+'_xx" class="inputStyle"/>';
			gdQsnHtml +=options[num].option+'</label></li>';
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 判断题
	if(qsnData.typeId == 3){
		gdQsnHtml +='<tr id="panduan_'+qindex+'"><td width="720"><ol>';
		gdQsnHtml +='<li indexid='+qindex+'_1 >';
		gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
		gdQsnHtml +='√</label></li>';
		gdQsnHtml +='<li indexid='+qindex+'_0 >';
		gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
		gdQsnHtml +='×</label></li>';
		gdQsnHtml += "</ol></td></tr>";
	}
	// 填空题
	if(qsnData.typeId == 4){
		gdQsnHtml += '<tr  id="tiankong_'+qindex+'"><th colspan="3" align="left">';
		var key=1;
		var kongsData = qsnData.rightKey;
//		alert(JSON.stringify(kongsData));
		for(var ki in kongsData){
			var kongArray=kongsData[key+""].key.split("#");
			gdQsnHtml +="空"+key;
			for(var kai=0;kai<kongArray.length;kai++){
//				if(kai==0){
					gdQsnHtml += '<input type="text" stIndex='+qindex+' stId="'+qsnData.questionId+'"  class="kong" name="tk_'+qindex+'" id="tabletextfield" />';
//				}
			}
			key++;
		}
		gdQsnHtml += "<br/><br/>";
		gdQsnHtml += '</th></tr>';
	}
	gdQsnHtml += "<br/>";
	return gdQsnHtml;
}
/**
 * 获取{}类型json长度
 * @param jsonData
 * @returns {Number}
 */
function getJsonLength(jsonData){
	var jsonLength = 0;
	for(var item in jsonData){
		jsonLength++;
	}
	return jsonLength;
}
//生成数组
create_shuzu=function(length){
	var a=[];
	for(var i=1;i<=length;i++){
		a.push(i);
	}
	return a;
};
//乱序
shuffle = function(o){
	for(var j, x, i = o.length; i; j = parseInt(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
	return o;
};
function exitExer() {
	$.ajax({
		url : baseUrl + "/user/getExercisesById.json",
		type : "post",
		data : {exerId : exerId},
		dataType : "json",
		success : function(data) {
			$("#exitBtn").css("cursor", "default");
			$("#exitBtn").removeAttr('onclick');
			$("#see").css("cursor", "default");
			$("#see").removeAttr('onclick');
			$("#down").css("cursor", "default");
			$("#down").removeAttr('onclick');
			$("#res_totalCount").html(data.totalCount);
			$("#res_rightCount").html(data.rightCount);
			$("#res_rightPercent").html(data.rightPercent);
			$("#res_time").html(data.time);
			$("#resultDiv").show();
		}
	});
}