/**
 * 预览试题
 */
$(function() {
	$("#previewStDialog").html("");
	$("#previewStDialog").dialog({
		title:'试题预览',
		width:600,
		height:500,
		collapsible: false,
        maximizable: false,
        resizable: true,
        modal: true,
	});
	$("#previewStDialog").dialog("close");
});
function previewSt(qsnId) {
	$("#previewStDialog").dialog("open");
	$.post(baseUrl + "/admin/shiti/getStById.json", {"qsnId":qsnId}, function(data){
		loadPreviewSt(data);
	}, "json");
}
function loadPreviewSt(qsnData) {
//	curQsnData = qsnData;
	var qsnIndex = 0;
	var index = 0;
	var qsnHtml='<table class="shitiTable" id="shiti_'+qsnIndex+'" did="'+index+'" shitiId='+qsnData.questionId+' border="0" cellpadding="0" cellspacing="0">';
	//stNumHtml+='<li class="danxuankaNumber_'+index+' daTiKa" id="biao_'+qsnIndex+'" style="border-top: 1px solid #ccc; cursor: pointer;"onclick="showQsn('+qsnIndex+')">'+qsnIndex+'</li>';
	qsnHtml+=loadQsnInfo(0,qsnData,0,0);
	qsnHtml+='</table>';
	qsnHtml+="<div id='qsnDetail' style='display:none;'><h4>试题解析：</h4><div>" + qsnData.detail + "</div><div>";
	qsnHtml+="<div id='result'></div>";
	
	$("#previewStDialog").html(qsnHtml);
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
		var rightKey = qsnData.rightKey;
		for(var num=1;num<=optionsLength;num++) {
			if(num == rightKey) {
				gdQsnHtml +='<li indexid='+qindex+'_'+num+'>';
				gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" id="danxuan_'+qindex+'_'+num+'_xx" class="inputStyle" checked="checked"/>';
				gdQsnHtml +=options[num].option+'</label></li>';
			}else {
				gdQsnHtml +='<li indexid='+qindex+'_'+num+'>';
				gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" id="danxuan_'+qindex+'_'+num+'_xx" class="inputStyle"/>';
				gdQsnHtml +=options[num].option+'</label></li>';
			}
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 多选题
	if("options" in qsnData && qsnData.typeId == 2){
		gdQsnHtml += '<tr id="duoxuan_'+qindex+'"><td width="720"><ol>';
		var options = qsnData.options;
		var optionsLength=getJsonLength(options);
		var rightKeys = qsnData.rightKey;
		for(var num=1;num<=optionsLength;num++) {
			if(rightKeys.indexOf(num) > -1) {
				gdQsnHtml +='<li indexid='+qindex+'_'+num+' >';
				gdQsnHtml +='<label><input checked="checked" name="duoxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+num+'_xx" class="inputStyle"/>';
				gdQsnHtml +=options[num].option+'</label></li>';
			} else {
				gdQsnHtml +='<li indexid='+qindex+'_'+num+' >';
				gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+num+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+num+'_xx" class="inputStyle"/>';
				gdQsnHtml +=options[num].option+'</label></li>';
			}
		}
		gdQsnHtml += '</ol></td></tr>';
	}
	// 判断题
	if(qsnData.typeId == 3){
		gdQsnHtml +='<tr id="panduan_'+qindex+'"><td width="720"><ol>';
		gdQsnHtml +='<li indexid='+qindex+'_1 >';
		if(qsnData.rightKey == 1) {
			gdQsnHtml +='<label><input checked="checked" name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
			gdQsnHtml +='√</label></li>';
			gdQsnHtml +='<li indexid='+qindex+'_0 >';
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
			gdQsnHtml +='×</label></li>';
		} else {
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" class="inputStyle"/>';
			gdQsnHtml +='√</label></li>';
			gdQsnHtml +='<li indexid='+qindex+'_0 >';
			gdQsnHtml +='<label><input checked="checked" name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" class="inputStyle"/>';
			gdQsnHtml +='×</label></li>';
		}
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