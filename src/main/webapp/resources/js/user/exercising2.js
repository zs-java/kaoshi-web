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
	$.ajax({
		url : baseUrl + "/user/testConnection.json",
		dataType : "json",
		success : function(result) {
			if(result.msg == 'success') {
				submitResult();
			}
		}
	});
}
function submitResult() {
	
	var rightFlag = false;
	
	$("#qsnDetail").show();
	var R = $("#result");
	var html = "<strong>正确答案：</strong>";
	if(curQsnData.typeId == 1) {
		//单选题
		var da = ""; 
		for(var i = 0; i < luanxuArr.length; i++) {
			if(luanxuArr[i] == curQsnData.rightKey) {
				da = xuanX[i + 1];
			}
		}
		html+="<strong>"+da+"</strong>";
		var xx = $('input[name="danxuan_0_xx"]:checked').val();
		if(xx == curQsnData.rightKey) {
			html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
			rightFlag = true;
		} else {
			html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
		}
	}
	if(curQsnData.typeId == 2) {
		//多选题
		var rightKeys = curQsnData.rightKey.toString().split(",");
//		for(var i=0;i<rightKeys.length;i++) {
//			html+="<strong>"+xuanX[rightKeys[i]] + ",</strong>";
//		}
		var keys = [];
		var keyEles = $('input[name="duoxuan_0_xx"]:checked').each(function(){
			keys.push($(this).val());
		});
		keys.sort(function(a, b) {
			return a - b;
		});
		var da = "";
		var arr = [];
		for(var i=0; i<rightKeys.length;i++) {
			for(var j=0;j<luanxuArr.length;j++) {
				if(rightKeys[i] == luanxuArr[j]) {
					arr.push(j + 1);
					break;
				}
			}
		}
		arr.sort(function(a, b) {
			return a - b;
		});
		for(var i=0;i<arr.length;i++) {
			html+="<strong>"+xuanX[arr[i]] + ",</strong>";
		}
		html+="<strong>"+da + "</strong>";
		if(rightKeys.toString() == keys.toString()) {
			html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
			rightFlag = true;
		} else {
			html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
		}
	}
	if(curQsnData.typeId == 3) {
		//判断题
		var rightKey = curQsnData.rightKey;
		var key = $('input[name="panduan_0_daan"]:checked').val();
		if(rightKey==1)
			html+="<img src='"+baseUrl+"/resources/img/ok.png' />";
		else
			html+="<img src='"+baseUrl+"/resources/img/cancel.png' />";
		if(key == rightKey) {
			html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
			rightFlag = true;
		} else 
			html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
	}
	if(curQsnData.typeId == 4) {
		//填空题
		var rightKey = curQsnData.rightKey;
		var rightKeys = rightKey["1"]["key"].toString().split("#");
		html+="<strong>"+rightKeys[0]+"</strong>";
		var key = $('input[name="tk_0"]').val();
		var flag = false;
		if(key == rightKeys[0])
			flag = true;
		for(var i=1;i<rightKeys.length;i++) {
			html+="<strong>  备选答案" + i + "：" + rightKeys[i] + "</strong>";
			if(key == rightKeys[i])
				flag = true;
		}
		if(flag) {
			html+="<br/><br/><strong>回答正确</strong><img src='"+baseUrl+"/resources/img/ok.png' />";
			rightFlag = true;
		} else {
			html+="<br/><br/><strong>回答错误</strong><img src='"+baseUrl+"/resources/img/cancel.png' />";
		}
	}
	R.html(html);
	$.ajax({
		url : baseUrl + "/user/submitExer.action",
		data : {
			"exerId" : exerId,
			"questionId" : curQsnData.questionId,
			"rightFlag" : rightFlag
		},
		dataType : "json",
		success : function(result) {
			if(result.msg == 'success') {
				loadExerInfo();
				$("#see").hide();
				$("#timer").show();
				
				setTimeout(timer, 1000);
//				setTimeout(function() {
//					$("#timer").hide();
//					$("#down").show();
//				}, 5000);
			} else if(result.msg == "error") {
				alert(result.info);
			}
		}
	});
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
//		var xxdata=eval("("+qsnData.options+")");
		var options = qsnData.options;
//		if(optionRandomFlg=="true"){
		if(true) {
			//是否选项乱序 ：是 
			var optionsLength=getJsonLength(options);
			var luanxuArray= shuffle(create_shuzu(optionsLength));
			//var xxNum=1;
			var keys=[];
			for(var xxNum in options){
				keys.push(xxNum);
			}
			keys.sort(function(a,b){
	            return a-b;
            });
			luanxuArr = luanxuArray;
			var indexx = 1;
			for(var i=0; i<keys.length; i++){
				xxNum = keys[i];
			//TODO
			//for(var xx in xxdata){
				if(luanxuArray[xxNum-1]==ksdaan){
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input  name="danxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="radio" checked="checked" id="danxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" class="inputStyle"/>';
					gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
					alert(12333);
				}else{
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input indexx="'+indexx+'" name="danxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="radio" id="danxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" class="inputStyle"/>';
					gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
				}
				//xxNum++;
				indexx ++;
			}
		}else{
			//var xxNum=1;
			var keys=[];
			for(var xxNum in options){
				keys.push(xxNum);
			}
			keys.sort(function(a,b){
	            return a-b;}
			);
			for(var i=0; i<keys.length; i++){
				xxNum = keys[i];
			//是否选项乱序 ：否
			//for(var xx in xxdata){
				if(xxNum==ksdaan){
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" checked="checked" id="danxuan_'+qindex+'_'+xxNum+'_xx" onclick="chooseRadio(this);" onchange="calScore_danxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
					gdQsnHtml +=options[xxNum].option+'</label></li>';
				}else{
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" id="danxuan_'+qindex+'_'+xxNum+'_xx" class="inputStyle"/>';
					gdQsnHtml +=options[xxNum].option+'</label></li>';
				}
				//xxNum++;
			}
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 多选题
	if("options" in qsnData && qsnData.typeId == 2){
		gdQsnHtml += '<tr id="duoxuan_'+qindex+'"><td width="720"><ol>';
//		var xxdata=eval("("+qsnData.xx+")");
		var options = qsnData.options;
		if(true){
			//是否选项乱序 ：是 
			var optionsLength=getJsonLength(options);
			var luanxuArray= shuffle(create_shuzu(optionsLength));
			luanxuArr = luanxuArray;
			if(typeof(ksdaan)!="undefined"){
				var daanArray=ksdaan.split(",");
				//var xxNum=1;
				var keys=[];
				for(var xxNum in options){
					keys.push(xxNum);
				}
				keys.sort(function(a,b){
		            return a-b;}
				);
				for(var i=0; i<keys.length; i++){
					xxNum = keys[i];
				//for(var xx in xxdata){
					if(daanArray.indexOf(luanxuArray[xxNum-1]+"") > -1){
						gdQsnHtml +='<li indexid='+qindex+'_'+luanxuArray[xxNum-1]+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" class="inputStyle"/>';
						gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
					}else{
						gdQsnHtml +='<li indexid='+qindex+'_'+luanxuArray[xxNum-1]+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" class="inputStyle"/>';
						gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
					}
					//xxNum++;
				}
			}else{
				//var xxNum=1;
				var keys=[];
				for(var xxNum in options){
					keys.push(xxNum);
				}
				keys.sort(function(a,b){
		            return a-b;
				});
				for(var i=0; i<keys.length; i++){
					xxNum = keys[i];
				//for(var xx in xxdata){
					gdQsnHtml +='<li indexid='+qindex+'_'+luanxuArray[xxNum-1]+' >';
					gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" class="inputStyle"/>';
					gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
					//xxNum++;
				}
			}
		}else{
			if(typeof(ksdaan)!="undefined"){
				var daanArray=ksdaan.split(",");
				//var xxNum=1;
				var keys=[];
				for(var xxNum in options){
					keys.push(xxNum);
				}
				keys.sort(function(a,b){
		            return a-b;}
				);
				for(var i=0; i<keys.length; i++){
					xxNum = keys[i];
				//for(var xx in xxdata){
					if(daanArray.indexOf(xxNum+"") > -1){
						gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" class="inputStyle"/>';
						gdQsnHtml +=options[xxNum].option+'</label></li>';
					}else{
						gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" class="inputStyle"/>';
						gdQsnHtml +=options[xxNum].option+'</label></li>';
					}
					//xxNum++;
				}
			}else{
				//var xxNum=1;
				var keys=[];
				for(var xxNum in options){
					keys.push(xxNum);
				}
				keys.sort(function(a,b){
		            return a-b;}
				);
				for(var i=0; i<keys.length; i++){
					xxNum = keys[i];
				//for(var xx in xxdata){
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
					gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" class="inputStyle"/>';
					gdQsnHtml +=options[xxNum].option+'</label></li>';
					//xxNum++;
				}
			}
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 判断题
	if(qsnData.typeId == 3){
		gdQsnHtml +='<tr id="panduan_'+qindex+'"><td width="720"><ol>';
		if(typeof(ksdaan)!="undefined"&&ksdaan!=""){
			if(ksdaan == "1"){
				gdQsnHtml +='<li indexid='+qindex+'_1 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
				gdQsnHtml +='√</label></li>';
	
				gdQsnHtml +='<li indexid='+qindex+'_0 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
				gdQsnHtml +='×</label></li>';
			}else{
				gdQsnHtml +='<li indexid='+qindex+'_1 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
				gdQsnHtml +='√</label></li>';
				
				gdQsnHtml +='<li indexid='+qindex+'_0 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
				gdQsnHtml +='×</label></li>';
			}
		}else{
			gdQsnHtml +='<li indexid='+qindex+'_1 >';
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
			gdQsnHtml +='√</label></li>';

			gdQsnHtml +='<li indexid='+qindex+'_0 >';
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
			gdQsnHtml +='×</label></li>';
		}
		gdQsnHtml += "</ol></td></tr>";
	}
	// 填空题
	if(qsnData.typeId == 4){
		gdQsnHtml += '<tr  id="tiankong_'+qindex+'"><th colspan="3" align="left">';
//		var kongsData=eval("("+qsnData.daan+")");
		var kongsData = qsnData.rightKey;
		var key=1;
		if(typeof(ksdaan)!="undefined"&&ksdaan!=""){
			for(var ki in ksdaan){
				var kongArray=ksdaan[key+""].kong.split("#");
				gdQsnHtml +="空"+key;
				for(var kai=0;kai<kongArray.length;kai++){
					if(kai==0){
						gdQsnHtml += '<input type="text" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+kongArray[kai]+'" class="kong" name="tk_'+qindex+'" id="tabletextfield" />';
					}
				}
				key++;
			}
		}else{
			for(var ki in kongsData){
				var kongArray=kongsData[key+""].key.split("#");
				gdQsnHtml +="空"+key;
				for(var kai=0;kai<kongArray.length;kai++){
					if(kai==0){
						gdQsnHtml += '<input type="text" stIndex='+qindex+' stId="'+qsnData.questionId+'"  class="kong" name="tk_'+qindex+'" id="tabletextfield" />';
					}
				}
				key++;
			}
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