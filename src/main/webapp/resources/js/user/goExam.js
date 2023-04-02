var allids = "";
var shitiTotal=0;
//未答试题数组
var notAnswerQesArr=[];
//计时器Id
var timeID = '';
//自动保存试卷计时器id
var saveTimer = '';
//考试的uuid
var uuid = "";
/**
 * 禁用点击右键 F12禁用
 */
$(document).bind("contextmenu",function(){return false;});
$(document).keydown(function(){
	var currKey=0;
	var evt=e || window.event;
	currKey=evt.keyCode||evt.which||evt.charCode;//支持IE、FF
	if (currKey == 123) {
		if(evt.preventDefault) {
			evt.preventDefault();
        } else {
        	evt.keyCode = 0;
        	evt.returnValue = false;
        }
	}
	
});
$(function() {
	$(window).scroll(function () {
		if ($(window).scrollTop() > 100) {
			$(".testNyLeft").css({position:"fixed",top:"40px"});
			$(".testTop").css({position:"fixed",top:"-15px"});
			
		}else{
			$(".testNyLeft").css({position:"",top:""});
			$(".testTop").css({position:"",top:""});
		}
	});
	if (!Array.prototype.indexOf)
	{
	  Array.prototype.indexOf = function(elt /*, from*/)
	  {
	    var len = this.length >>> 0;

	    var from = Number(arguments[1]) || 0;
	    from = (from < 0)
	         ? Math.ceil(from)
	         : Math.floor(from);
	    if (from < 0)
	      from += len;

	    for (; from < len; from++)
	    {
	      if (from in this &&
	          this[from] === elt)
	        return from;
	    }
	    return -1;
	  };
	}
	if(!Array.prototype.remove){
		Array.prototype.remove=function(dx) 
		{ 
		    if(isNaN(dx)||dx>this.length){return false;} 
		    for(var i=0,n=0;i<this.length;i++) 
		    { 
		        if(this[i]!=this[dx]) 
		        { 
		            this[n++]=this[i] ;
		        } 
		    } 
		    this.length-=1 ;
		};
	}
	
	if(timeID != ''){
		clearTimeout(timeID);
	}
	// 设置每一秒调用一次倒计时函数
	timeID = setTimeout("count_down()",1000);
	
	uuid = $("#ksUuid").val();
	loading();
	
	$.ajax({
		url : baseUrl + "/user/showExam.json",
		type : "post",
		data : {"sjId" : sjId},
		dataType : "json",
		success : function(data) {
			loadSjData(data);
			$("#loading").hide();
			saveTimer=setTimeout('saveShiJuan()',300000);
		}
	});
});

/**
 * 加载试卷信息
 */
function loadSjData(data) {
	var gdxt = JSON.parse(data.gdxt);
//	alert(JSON.stringify(gdxt));
	var stData = JSON.parse(data.xzst);
//	alert(JSON.stringify(stData));
	//保存所有试题id
	allids = gdxt.allids;
//	alert(allids);
	//删除段落信息中的allids属性
	delete gdxt.allids;
	//段落索引
	var dlIndex = 1;
	var C = $("#Container");
	//选项卡
	var X = $("#datika");
	//试题索引
	var qsnIndex = 1;
	var index = 1;
	
	for(var dl in gdxt) {
		var keyArray=[];
		var idArray=gdxt[dlIndex+""].ids.split(",");
		var qsnData=stData.data;
		for(var dindex in qsnData){
			var oqid=qsnData[dindex].questionId+"";
			if(idArray.indexOf(oqid) > -1){
				keyArray.push(dindex);
			}
		}
		//段落标题
		var dlTitle = gdxt[dlIndex].t;
		if(dlTitle != "" && dlTitle != null){
			// 如果段落标题不为空，则向C中追加段落标题
			var titleHtml="<span class='duanluo' id='duanluo"+index+"'><h3>"+dlTitle+"(每题"+gdxt[dlIndex+""].fen+"分)</h3></span>";
			var dlxxkHtml="<h2 class='danxuanka' id='danxuanka"+index+"'>"+dlTitle+"(每题"+gdxt[dlIndex+""].fen+"分)</h2>";
			X.append(dlxxkHtml);
			C.append(titleHtml);
		}
		var key = dlIndex + "";
		
		//试题数据
		var num=1;
		var stNumUlHtml='<ul style="border-top: 0px" id="duanluo_'+index+'"></ul>';
		X.append(stNumUlHtml);
		var stNumHtml="";
		var shitiLength=idArray.length-1;
		
		if(qsnRandomFlg == "false") {
			//试题正常顺序
			var qsn=1;
			for(var qs in qsnData){
				var oldid=qsnData[qsn].questionId+"";
				// 循环所有试题数据
				if(idArray.indexOf(oldid)>-1){
					// 如果试题ID是此段落试题则追加试题HTML
					var qsnHtml='<table class="shitiTable" id="shiti_'+qsnIndex+'" did="'+index+'" shitiId='+qsnData[qsn].qsnid+' border="0" cellpadding="0" cellspacing="0">';
//					stNumHtml+='<li class="danxuankaNumber_'+index+'" id="biao_'+qsnData[qsn].qsnid+'" style="border-top: 1px solid #ccc;"><a href="'+baseUrl+'/mta/F020/goExam.html?ksUuid='+uuid+'#dl_qsn_'+qsnIndex+'" onclick="showQsn('+qsnIndex+')">'+qsnIndex+'</a></li>';
					stNumHtml+='<li class="danxuankaNumber_'+index+' daTiKa" id="biao_'+qsnIndex+'" style="border-top: 1px solid #ccc; cursor: pointer;"onclick="showQsn('+qsnIndex+')">'+qsnIndex+'</li>';
					
					qsnHtml+=loadQsnInfo(qsnIndex,qsnData[qsn],gdxt[dlIndex+""].fen,0);
					qsnHtml+='</table>';
					C.append(qsnHtml);
					qsnIndex++;
					shitiTotal++;
					if(num==idArray.length-1){
						num=1;
						break;
					}
					num++;
					// 考虑试题不会重复则终止循环
					delete stData.data.qsn;
				}
				qsn++;
			}
		} else {
			//试题打乱顺序
			for(var kindex=0;kindex<shitiLength;){
				var random=parseInt(shitiLength*Math.random());
				var keyStr=keyArray[random];
				var rqsnData = qsnData[keyStr];
				var oldid=rqsnData.questionId+"";
				// 循环所有试题数据
				if(idArray.indexOf(oldid)>-1){
					// 如果试题ID是此段落试题则追加试题HTML
					var qsnHtml='<table class="shitiTable" id="shiti_'+qsnIndex+'" did="'+index+'" shitiId='+rqsnData.qsnid+' border="0" cellpadding="0" cellspacing="0">';
					//stNumHtml+='<li class="danxuankaNumber_'+index+'" id="biao_'+rqsnData.qsnid+'" style="border-top: 1px solid #ccc;"><a href="'+baseUrl+'/mta/F020/goExam.html?ksUuid='+uuid+'#dl_qsn_'+qsnIndex+'">'+qsnIndex+'</a></li>';
					stNumHtml+='<li class="danxuankaNumber_'+index+' daTiKa" id="biao_'+qsnIndex+'" style="border-top: 1px solid #ccc; cursor: pointer;"onclick="showQsn('+qsnIndex+')">'+qsnIndex+'</li>';
					
					qsnHtml+=loadQsnInfo(qsnIndex,rqsnData,gdxt[dlIndex+""].fen,0);
					qsnHtml+='</table>';
					C.append(qsnHtml);
					qsnIndex++;
					shitiTotal++;
					if(num==idArray.length-1){
						num=1;
						break;
					}
					num++;
					// 考虑试题不会重复则终止循环
					delete stData.data.keyStr;
					keyArray.remove(random);
					shitiLength--;
				}
			}
		}
		$("#duanluo_"+index).append(stNumHtml);
		index++;
		dlIndex++;
	}
	if(pageModel=="true"){
		// 逐题模式
		zhuti();
	}
	
	$(".daTiKa").each(function(i){
		var danxLength=$("#danxuan_"+(i+1)).length;
		if($("#danxuan_"+(i+1)).find("input[type='radio']").is(":checked")){
			$("#biao_"+(i+1)).addClass("blue");
		}
		var pdLength=$("#panduan_"+(i+1)).length;
		if($("#panduan_"+(i+1)).find("input[type='radio']").is(":checked")){
			$("#biao_"+(i+1)).addClass("blue");
		}
		var duoxLength=$("#duoxuan_"+(i+1)).length;
		if($("#duoxuan_"+(i+1)).find("input[type='checkbox']").is(":checked")){
			$("#biao_"+(i+1)).addClass("blue");
		}
		var jdLength=$("textarea[name='jianda_"+(i+1)+"']").length;
		var jianda=$("textarea[name='jianda_"+(i+1)+"']").val();
		if($.trim(jianda)!=""){
			$("#biao_"+(i+1)).addClass("blue");
		}
		
		if(danxLength>0&&!$("#biao_"+(i+1)).hasClass("blue")){
			notAnswerQesArr.push(i+1);
		}
		if(pdLength>0&&!$("#biao_"+(i+1)).hasClass("blue")){
			notAnswerQesArr.push(i+1);
		}
		if(duoxLength>0&&!$("#biao_"+(i+1)).hasClass("blue")){
			notAnswerQesArr.push(i+1);
		}
		if(jdLength>0&&!$("#biao_"+(i+1)).hasClass("blue")){
			notAnswerQesArr.push(i+1);
		}
		var kongLength=$("#tiankong_"+(i+1)).find(".kong").length;
		if(kongLength>0){
			var kongCount=0;
			$("#tiankong_"+(i+1)).find(".kong").each(function(j){
				if($.trim($(this).val())!=""){
					$("#biao_"+(i+1)).addClass("blue");
					kongCount++;
				}
			});
			if(kongCount<kongLength){
				notAnswerQesArr.push(i+1);
			}
		}
		var yuduLength=$(".dl_qsn_yd_"+(i+1)).length;
		if(yuduLength>0){
			var yudeCount=0;
			$(".dl_qsn_yd_"+(i+1)).each(function(j){
				if($("#danxuan_yd_"+(i+1)+"_"+(j+1)).find("input[type='radio']").is(":checked")){
					$("#biao_"+(i+1)).addClass("blue");
					yudeCount++;
				}
				if($("#panduan_yd_"+(i+1)+"_"+(j+1)).find("input[type='radio']").is(":checked")){
					$("#biao_"+(i+1)).addClass("blue");
					yudeCount++;
				}
				if($("#duoxuan_yd_"+(i+1)+"_"+(j+1)).find("input[type='checkbox']").is(":checked")){
					$("#biao_"+(i+1)).addClass("blue");
					yudeCount++;
				}
				var jianda_yuedu=$("textarea[name='jianda_"+(i+1)+"_"+(j+1)+"']").val();
				if($.trim(jianda_yuedu)!=""){
					$("#biao_"+(i+1)).addClass("blue");
					yudeCount++;
				}
			});
			
			if(yudeCount<yuduLength){
				notAnswerQesArr.push(i+1);
			}
		}
		
	});
}
function loading() {
	// 浏览器可见区域的高度
	//var wHeight=$(window).height();
	// 浏览器可见区域的宽度
	//var dWidth=$(window).width();
	//var popHeight= $("#loading").height();
	//var popWidth= $("#loading").width();
	//var height=(wHeight-popHeight)/2;
	//var width=(dWidth-popWidth)/2;
	//$("#loading").css("top",height+$(document).scrollTop());
	//$("#loading").css("left",width+"px");
	$("#loading").show();
}
/**
 * 
 * @param qindex
 * @param qsnData
 * @param qsnfen
 * @param isSuiJI
 *            0：固定 1：随机
 * @returns {String}
 */
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
	var gdQsnHtml='<hr class="splitHr" /><tr id="dl_qsn_'+qindex+'" suiji="0" defen='+defen+' class="shiti" shitiId="'+qsnData.questionId+'" fen="'+qsnfen+'" shitiType="'+qsnData.typeId+'"><th colspan="3" align="left" style="vertical-align: top;">第'+qindex+'题:<span class="title">'+qsnData.title+'</span>（分值：'+qsnfen+'分） </th>'
	+'<th width="100"><input type="hidden" id="hidden_'+qindex+'" value=\''+daan+'\'/><a onclick="add_biaoji(this)" class="biaoji" stIndex='+qindex+' stId="'+qsnData.questionId+'" href="javascript:;"><img src="' + baseUrl + '/resources/img/biaoji.png" width="90" height="33" alt="标记" /></a></th></tr>';
	// 单选题
	if("options" in qsnData && qsnData.typeId == 1){
		gdQsnHtml += '<tr id="danxuan_'+qindex+'"><td width="720"><ol>';
//		var xxdata=eval("("+qsnData.options+")");
		var options = qsnData.options;
		if(optionRandomFlg=="true"){
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
			for(var i=0; i<keys.length; i++){
				xxNum = keys[i];
			//for(var xx in xxdata){
				if(luanxuArray[xxNum-1]==ksdaan){
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="radio" checked="checked" id="danxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" onclick="chooseRadio(this);" onchange="calScore_danxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
					gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
				}else{
					gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+'>';
					gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="radio" id="danxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" onclick="chooseRadio(this);" onchange="calScore_danxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
					gdQsnHtml +='<label><input name="danxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="radio" id="danxuan_'+qindex+'_'+xxNum+'_xx" onclick="chooseRadio(this);" onchange="calScore_danxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
		if(optionRandomFlg=="true"){
			//是否选项乱序 ：是 
			var optionsLength=getJsonLength(options);
			var luanxuArray= shuffle(create_shuzu(optionsLength));
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
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
						gdQsnHtml +=options[luanxuArray[xxNum-1]].option+'</label></li>';
					}else{
						gdQsnHtml +='<li indexid='+qindex+'_'+luanxuArray[xxNum-1]+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
					gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+luanxuArray[xxNum-1]+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+luanxuArray[xxNum-1]+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
						gdQsnHtml +=options[xxNum].option+'</label></li>';
					}else{
						gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.qsnid+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
					gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" stIndex='+qindex+' stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" onclick="chooseCheckbox(this)" onchange="calScore_duoxuan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_1_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
				gdQsnHtml +='√</label></li>';
	
				gdQsnHtml +='<li indexid='+qindex+'_0 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_0_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
				gdQsnHtml +='×</label></li>';
			}else{
				gdQsnHtml +='<li indexid='+qindex+'_1 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_1_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
				gdQsnHtml +='√</label></li>';
				
				gdQsnHtml +='<li indexid='+qindex+'_0 >';
				gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="panduan_'+qindex+'_0_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
				gdQsnHtml +='×</label></li>';
			}
		}else{
			gdQsnHtml +='<li indexid='+qindex+'_1 >';
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="1" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
			gdQsnHtml +='√</label></li>';

			gdQsnHtml +='<li indexid='+qindex+'_0 >';
			gdQsnHtml +='<label><input name="panduan_'+qindex+'_daan" type="radio" value="0" stIndex='+qindex+' stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" onclick="chooseRadio(this)" onchange="calScore_panduan('+qindex+','+qsnfen+','+suffixStr+');" class="inputStyle"/>';
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
						gdQsnHtml += '<input type="text" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+kongArray[kai]+'" onchange="tiankong(this);calScore_tiankong('+qindex+','+qsnfen+');" class="kong" name="tk_'+qindex+'" id="tabletextfield" />';
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
						gdQsnHtml += '<input type="text" stIndex='+qindex+' stId="'+qsnData.questionId+'" onchange="tiankong(this);calScore_tiankong('+qindex+','+qsnfen+');" class="kong" name="tk_'+qindex+'" id="tabletextfield" />';
					}
				}
				key++;
			}
		}
		gdQsnHtml += "<br/><br/>";
		gdQsnHtml += '</th></tr>';
	}
	// 简答题
//	if(qsnData.shititypeid == 5){
//		gdQsnHtml += '<tr>';
//		gdQsnHtml +='<td width="40">&nbsp;</td>';
//		if(typeof(ksdaan)!="undefined"){
//			gdQsnHtml +='<td width="680"><textarea name="jianda_'+qindex+'" onchange="jianda(this)" class="jiandaInput" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="tabletextarea" cols="45" rows="5">'+ksdaan+'</textarea></td>';
//		}else{
//			gdQsnHtml +='<td width="680"><textarea name="jianda_'+qindex+'" onchange="jianda(this)" class="jiandaInput" stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="tabletextarea" cols="45" rows="5"></textarea></td>';
//		}
//		gdQsnHtml +='<td colspan="2"></td>';
//		gdQsnHtml +='</tr>';
//	}
	// 阅读理解
//	if("data" in qsnData && qsnData.shititypeid == 6){
//		/**
//		 * 阅读理解子试题中试题数据 tx：题型；daan：答案；title：试题标题；xx：选项；
//		 */
//		if(typeof(dnandata) != "undefined"&&dnandata!=null){
//			var childrenData;
//			if(isSuiJi==0){
//				for(var i=0;i<dnandata.length;i++){
//					if(dnandata[i].sid==qsnid){
//						childrenData=dnandata[i].children;
//						break;
//					}
//				}
//			}else if(isSuiJi==1){
//				for(var i=0;i<rdaandata.length;i++){
//					if(rdaandata[i].sid==qsnid){
//						childrenData=rdaandata[i].children;
//						break;
//					}
//				}	
//			}
//		}
//		var zhQsnData=eval("("+qsnData.data+")");
//		//阅读理解里的试题个数
//		var childrenShiTiCount=getJsonLength(zhQsnData);
//		var cindex=1;
//		for(var yudu in zhQsnData){
//			if(typeof(childrenData)!="undefined"){
//				ksdaan=childrenData[cindex].ksdaan;
//				defen=childrenData[cindex].defen;
//			}
//			var childData=zhQsnData[cindex];
//			var tx="";
//			if(childData.tx=="danxuan"){
//				tx="单选题";
//			}else if(childData.tx=="duoxuan"){
//				tx="不定项选择题";
//			}else if(childData.tx=="panduan"){
//				tx="判断题";
//			}else if(childData.tx=="jianda"){
//				tx="简答题";
//			}
//			gdQsnHtml+='<tr class="dl_qsn_yd_'+qindex+'" defen="'+defen+'" id="dl_qsn_yd_'+qindex+'_'+cindex+'" title="'+childData.title+'" tx="'+childData.tx+'"><th colspan="3" align="left">'+cindex+'.'+tx+':'+childData.title+' <input type="hidden" id="hidden_yd_'+qindex+'_'+cindex+'" value="'+childData.daan+'"/></th></tr>';
//			// 单选
//			if(childData.tx=="danxuan" && "xx" in childData){
//				gdQsnHtml += '<tr class="yudu" id="danxuan_yd_'+qindex+'_'+cindex+'" tx="danxuan"><td width="720"><ol>';
//				var xxdata=childData.xx;
//				//var xxNum=1;
//				var keys=[];
//				for(var xxNum in xxdata){
//					keys.push(xxNum);
//				}
//				keys.sort(function(a,b){
//		            return a-b;}
//				);
//				for(var i=0; i<keys.length; i++){
//					xxNum = keys[i];
//				//for(var xx in xxdata){
//					if(typeof(ksdaan)!="undefined"&&xxNum==ksdaan){
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_'+xxNum+' >';
//						gdQsnHtml +='<label><input name="danxuan_'+qindex+'_'+cindex+'_xx" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+xxNum+'" type="radio" id="danxuan_'+qindex+'_'+cindex+'_'+xxNum+'_xx" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +=xxdata[xxNum].xx+'</label></li>';
//					}else{
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_'+xxNum+' >';
//						gdQsnHtml +='<label><input name="danxuan_'+qindex+'_'+cindex+'_xx" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+xxNum+'" type="radio" id="danxuan_'+qindex+'_'+cindex+'_'+xxNum+'_xx" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +=xxdata[xxNum].xx+'</label></li>';
//					}
//					//xxNum++;
//				}
//				gdQsnHtml += '</ol></td></tr>';
//			}
//			// 多选
//			if(childData.tx=="duoxuan" && "xx" in childData){
//				var daanArray="";
//				if(typeof(ksdaan)!="undefined"){
//					daanArray=ksdaan.split(",");
//				}
//				gdQsnHtml += '<tr class="yudu" id="duoxuan_yd_'+qindex+'_'+cindex+'" tx="duoxuan"><td width="720"><ol>';
//				var xxdata=childData.xx;
//				//var xxNum=1;
//				var keys=[];
//				for(var xxNum in xxdata){
//					keys.push(xxNum);
//				}
//				keys.sort(function(a,b){
//		            return a-b;}
//				);
//				for(var i=0; i<keys.length; i++){
//					xxNum = keys[i];
//				//for(var xx in xxdata){
//					if(daanArray.indexOf(xxNum+"") > -1){
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_'+xxNum+' >';
//						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_'+cindex+'_xx" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+xxNum+'" type="checkbox" id="duoxuan_'+qindex+'_'+cindex+'_xx" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +=xxdata[xxNum].xx+'</label></li>';
//					}else{
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_'+xxNum+' >';
//						gdQsnHtml +='<label><input name="duoxuan_'+qindex+'_'+cindex+'_xx" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="'+xxNum+'" type="checkbox" id="duoxuan_'+qindex+'_'+cindex+'_xx" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +=xxdata[xxNum].xx+'</label></li>';
//					}
//					//xxNum++;
//				}
//				gdQsnHtml += '</ol></td></tr>';
//			}
//			// 判断
//			if(childData.tx=="panduan"){
//
//				gdQsnHtml +='<tr class="yudu" id="panduan_yd_'+qindex+'_'+cindex+'" tx="panduan"><td width="720"><ol>';
//				if(typeof(ksdaan)!="undefined"&&ksdaan!=""){
//					if(ksdaan == "1"){
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_1 >';
//						gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="1" type="radio" id="panduan_'+qindex+'_'+cindex+'_1_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +='√</label></li>';
//	
//						gdQsnHtml +='<li indexid='+qindex+'_0 >';
//						gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="0" type="radio" id="panduan_'+qindex+'_'+cindex+'_0_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +='×</label></li>';
//					}else{
//						gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_1 >';
//						gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="1" type="radio" id="panduan_'+qindex+'_'+cindex+'_1_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +='√</label></li>';
//	
//						gdQsnHtml +='<li indexid='+qindex+'_0 >';
//						gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" checked="checked" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="0" type="radio" id="panduan_'+qindex+'_'+cindex+'_0_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//						gdQsnHtml +='×</label></li>';
//					}
//				}else{
//					gdQsnHtml +='<li indexid='+qindex+'_'+cindex+'_1 >';
//					gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="1" type="radio" id="panduan_'+qindex+'_'+cindex+'_1_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//					gdQsnHtml +='√</label></li>';
//					
//					gdQsnHtml +='<li indexid='+qindex+'_0 >';
//					gdQsnHtml +='<label><input name="panduan_'+qindex+'_'+cindex+'_daan" stIndex='+qindex+' stId="'+qsnData.qsnid+'" value="0" type="radio" id="panduan_'+qindex+'_'+cindex+'_0_daan" onclick="yuedu(this)" onchange="calScore_yuedu('+qindex+','+cindex+','+qsnfen+','+childrenShiTiCount+');" class="inputStyle yuedu"/>';
//					gdQsnHtml +='×</label></li>';
//					gdQsnHtml += "</ol></td></tr>";
//				}
//			}
//			// 简答
//			if(childData.tx=="jianda"){
//				gdQsnHtml += '<tr class="yudu" tx="jianda">';
////				gdQsnHtml +='<td width="40">&nbsp;</td>';
//				if(typeof(ksdaan)!="undefined"){
//					gdQsnHtml +='<td colspan="3"><textarea onchange="yuedu(this)" class="jiandaInput yuedu" name="jianda_'+qindex+'_'+cindex+'"  stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="tabletextarea" cols="45" rows="5">'+ksdaan+'</textarea></td>';
//				}else{
//					gdQsnHtml +='<td colspan="3"><textarea onchange="yuedu(this)" class="jiandaInput yuedu" name="jianda_'+qindex+'_'+cindex+'"  stIndex='+qindex+' stId="'+qsnData.qsnid+'" id="tabletextarea" cols="45" rows="5"></textarea></td>';
//				}
////				gdQsnHtml +='<td colspan="2"></td>';
//				gdQsnHtml +='</tr>';
//			}
//			cindex++;
//		}
//	}
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
//乱序
shuffle = function(o){
	for(var j, x, i = o.length; i; j = parseInt(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
	return o;
};
//生成数组
create_shuzu=function(length){
	var a=[];
	for(var i=1;i<=length;i++){
		a.push(i);
	}
	return a;
};
function sortAsc(a,b){
	return a-b>0?1:-1;
}
function showQsn(qsnindex){
	if(pageModel=="true"){
		// 逐题模式
		$(".shitiTable").hide();
		$(".duanluo").hide();
		$("#shiti_"+qsnindex).show();
		var did=parseInt($("#shiti_"+(qsnindex+1)).attr("did"));
		$("#duanluo"+did).show();
		$("#up").show();
		$("#down").show();
		if(qsnindex==shitiTotal){
			$("#down").hide();
		}
		if(qsnindex==1){
			$("#up").hide();
		}
	}else{
		//由于提交及答题卡浮动所以锚点跳上一题，如果是阅读理解题则定位无法精确
		if(qsnindex > 1){
			location.hash='#dl_qsn_'+(qsnindex-1);
		}else{
			location.hash='#dl_qsn_1';
		}
	}
}
/**
 * 添加标记 (选项卡选中)
 * 
 * @param obj
 */
function add_biaoji(obj){
	var tid=$(obj).attr("stIndex");
	$(obj).attr("onclick","del_biaoji(this)");
	$("li[id='biao_"+tid+"']").addClass("org");
}
/**
 * 删除标记 (选项卡选中)
 * 
 * @param obj
 */
function del_biaoji(obj){
	var tid=$(obj).attr("stIndex");
	$(obj).attr("onclick","add_biaoji(this)");
	$("li[id='biao_"+tid+"']").removeClass("org");
}
/**
 * 单选 判断 (选项卡选中)
 * @param obj
 */
function chooseRadio(obj){
	var tid=parseInt($(obj).attr("stIndex"));
	if(!$(obj).is(":checked")){
		$("li[id='biao_"+tid+"']").removeClass("blue");
		notAnswerQesArr.push(tid);
	}else{
		//删除数组元素
		$("li[id='biao_"+tid+"']").addClass("blue");
		var arrayIndex=notAnswerQesArr.indexOf(tid);
		if(arrayIndex >= 0)
			notAnswerQesArr.remove(arrayIndex);
	}
}
/**
 *  多选 (选项卡选中)
 *  
 *  @param obj 
 */
function chooseCheckbox(obj){
	var tid=parseInt($(obj).attr("stIndex"));
	var inputName=$(obj).attr("name");
	var count=0;
	$("input[name='"+inputName+"']").each(function(){
		if($(this).is(":checked")){
			count++;
		}
	});
	if(count>0){
		//删除数组元素
		var arrayIndex=notAnswerQesArr.indexOf(tid);
		if(arrayIndex >= 0)
			notAnswerQesArr.remove(arrayIndex);
		$("li[id='biao_"+tid+"']").addClass("blue");
	}else{
		notAnswerQesArr.push(tid);
		$("li[id='biao_"+tid+"']").removeClass("blue");
	}
}
/**
 * 填空 (选项卡选中)
 * 
 * @param obj 
 */
function tiankong(obj){
	var tid=parseInt($(obj).attr("stIndex"));
	var kongLength=$(obj).parent().find(".kong").length;
	var kongCount=0;
	$(obj).parent().find(".kong").each(function(){
		if($(obj).val()!=""){
			kongCount++;
		}
	});
	if(kongCount>0){
		$("li[id='biao_"+tid+"']").addClass("blue");
		var arrayIndex=notAnswerQesArr.indexOf(tid);
		if(arrayIndex != -1)
			notAnswerQesArr.remove(arrayIndex);
	}else{
		$("li[id='biao_"+tid+"']").removeClass("blue");
		if( kongCount<kongLength){
			notAnswerQesArr.push(tid);
		}
	}
}
//单选题计算分数
function calScore_danxuan(index,fen,suffixStr,tindex){
	fen=parseFloat(fen);
	if(suffixStr=="1"){
		suffixStr="";
	}
	var deFen=parseFloat($("#dl_qsn_"+suffixStr+index).attr("defen"));
	if(deFen!=0){
		score=score-deFen;
	}
	//正确答案
	var daan=$("#hidden_"+suffixStr+index).val();
	
	//考生答案
	var ksDaan=$("#danxuan_"+suffixStr+index).find("input:radio:checked").val();
	if(daan==ksDaan){
		$("#dl_qsn_"+suffixStr+index).attr("defen",fen);
		score=score+fen;
	}else{
		$("#dl_qsn_"+suffixStr+index).attr("defen",0);
	}
	if(suffixStr==""){
		pushErrorQsn(index,fen);
	}
}
//多选题计算分数
function calScore_duoxuan(index,fen,suffixStr){
	fen=parseFloat(fen);
	if(suffixStr=="1"){
		suffixStr="";
	}
	var deFen=parseFloat($("#dl_qsn_"+suffixStr+index).attr("defen"));
	if(deFen!=0){
		score=score-deFen;
	}
	//正确答案
	var daan=$("#hidden_"+suffixStr+index).val();
	daan = daan.substring(daan.indexOf('[') + 1, daan.indexOf(']'));
	
	//考生答案l
	var chooseIdStr="";
	$("#duoxuan_"+suffixStr+index).find("input:checkbox:checked").each(function(){
		chooseIdStr=chooseIdStr+$(this).val()+",";
	});
	var ksDaan=chooseIdStr.substring(0,chooseIdStr.length-1);
	if(optionRandomFlg == "true"){
		// 选项乱序
		ksDaan=ksDaan.split(",").sort(sortAsc).join();
	}
	if(ksDaan.length==0){
		$("#dl_qsn_"+suffixStr+index).attr("defen",0);
		if(suffixStr==""){
			pushErrorQsn(index,fen);
		}
		return ;
	}
	
	// 后台控制 多选题少选半分
	if(selectFlg==1){
		//多选题少选半分
		if(ksDaan.length>daan.length){
			//考生答案的长度大于正确答案的场合。答题错误
			$("#dl_qsn_"+suffixStr+index).attr("defen",0);
		}else{
			if(ksDaan.length==daan.length){
				//考生答案和正确答案长度相等的场合
				var ksDaanArr=ksDaan.split(",");
				var errorFlg=0;
				for(var i=0;i<ksDaanArr.length;i++){
					if(daan.indexOf(ksDaanArr[i])==-1){
						$("#dl_qsn_"+suffixStr+index).attr("defen",0);
						errorFlg=1;
						break;
					}
				}
				if(errorFlg!=1){
					$("#dl_qsn_"+suffixStr+index).attr("defen",fen);
					score=score+fen;
				}
			}else if(ksDaan.length<daan.length){
				//考生答案小于正确答案的场合
				var ksDaanArr=ksDaan.split(",");
				var flg=0;
				for(var i=0;i<ksDaanArr.length;i++){
					if(daan.indexOf(ksDaanArr[i])==-1){
						$("#dl_qsn_"+suffixStr+index).attr("defen",0);
						flg=1;
						break;
					}
				}
				if(flg!=1){
					$("#dl_qsn_"+suffixStr+index).attr("defen",fen/2);
					score=score+fen/2;
				}
			}else{
				$("#dl_qsn_"+suffixStr+index).attr("defen",0);
			}
		}
	}else{
		//非多选题少选半分
		if(ksDaan.length==daan.length){
			var ksDaanArr=ksDaan.split(",");
			var errorFlg=0;
			for(var i=0;i<ksDaanArr.length;i++){
				if(daan.indexOf(ksDaanArr[i])==-1){
					//不存在
					$("#dl_qsn_"+suffixStr+index).attr("defen",0);
					errorFlg=1;
					break;
				}
			}
			if(errorFlg!=1){
				$("#dl_qsn_"+suffixStr+index).attr("defen",fen);
				score=score+fen;
			}
		}else{
			$("#dl_qsn_"+suffixStr+index).attr("defen",0);
		}
	}
	if(suffixStr==""){
		pushErrorQsn(index,fen);
	}
}
//判断题计算分数
function calScore_panduan(index,fen,suffixStr){
	fen=parseFloat(fen);
	if(suffixStr=="1"){
		suffixStr="";
	}
	var deFen=parseFloat($("#dl_qsn_"+suffixStr+index).attr("defen"));
	if(deFen!=0){
		score=score-deFen;
	}
	//正确答案
	var daan=$("#hidden_"+suffixStr+index).val();
	//考生答案
	var ksDaan=$("#panduan_"+suffixStr+index).find("input:radio:checked").val();
	if(daan==ksDaan){
		$("#dl_qsn_"+suffixStr+index).attr("defen",fen);
		score=score+fen;
	}else{
		$("#dl_qsn_"+suffixStr+index).attr("defen",0);
	}
	if(suffixStr==""){
		pushErrorQsn(index,fen);
	}
}
//计算填空题的分数
function calScore_tiankong(index,fen){
	fen=parseFloat(fen);
	var deFen=parseFloat($("#dl_qsn_"+index).attr("defen"));
	if(deFen!=0){
		score=score-deFen;
	}
	//正确答案
	var daan= JSON.parse($("#hidden_"+index).val());
//	alert(JSON.stringify(daan));
	
	//空总数
	var kongCount=$("#tiankong_"+index).find("input:text").size();
	//答对空的个数
	var daduiKongCount=0;

	$("#tiankong_"+index).find("input:text").each(function(i){
		var ksDaan=$.trim($(this).val());
		var indx=(i+1)+"";
		var kongArray=daan[indx]["key"].split("#");
		if(ksDaan!=""){
			for(var j=0;j<kongArray.length;j++){
				if(ksDaan==kongArray[j]){
					daduiKongCount++;
					break;
				}
			}
		}
	});
	var defen=fen*daduiKongCount/kongCount;
	$("#dl_qsn_"+index).attr("defen",defen);
	score=score+defen;
	//把错题放到错题集合中
	pushErrorQsn(index,fen);
}
/**
 * 将错题放入错题集合中
 * @param index 试题索引
 * @param fen 试题分数
 */
function pushErrorQsn(index,fen){
	var stid=$("#dl_qsn_"+index).attr("shitiId");
	var arrayIndex=errorShiTi.indexOf(stid);
	var defen=parseFloat($("#dl_qsn_"+index).attr("defen"));
	if(arrayIndex==-1&&defen<fen){
		//添加错 题ID
		errorShiTi.push(stid);
	}else if(arrayIndex !=-1 && defen==fen){
		errorShiTi.remove(arrayIndex);
	}
}
function zhuti(){
	$(".duanluo").hide();
	$(".shitiTable").hide();
	var did=$("#shiti_1").attr("did");
	$("#duanluo"+did).show();
	$("#shiti_1").show();
	$("#button").show();
	$("#up").hide();
	$("hr").remove();
	$("br").remove();
}
/**
 * 上一题
 */
function up(){
	var idStr = $(".shitiTable:visible:first").attr("id");
	var id = parseInt(idStr.substring(6,idStr.lengh));
	if((id-1)==1){
		$("#up").hide();
	}
	$("#down").show();
	$("#shiti_"+id).hide();
	$("#shiti_"+(id-1)).show();
	var did=parseInt($("#shiti_"+(id-1)).attr("did"));
	$("#duanluo"+did).show();
	$("#duanluo"+(did+1)).hide();
}
/**
 * 下一题
 */
function down(){
	var idStr = $(".shitiTable:visible:last").attr("id");
	var id = parseInt(idStr.substring(6,idStr.lengh));
	if(id==shitiTotal-1){
		$("#down").hide();
	}
	$("#up").show();
	$("#shiti_"+id).hide();
	$("#shiti_"+(id+1)).show();
	var did=parseInt($("#shiti_"+(id+1)).attr("did"));
	$("#duanluo"+(did-1)).hide();
	$("#duanluo"+did).show();
}
/**
 * 保存试卷
 * 
 * @returns
 */
function saveShiJuan(){
//	if(!saveFlg){
//		alert("您已经被管理员强制暂停，您现在的任何答题记录将不会被保存。");
//		return;
//	}
	fmtExamDaAn();

	submit(0);

//	saveTimer=setTimeout('saveShiJuan()',300000);
}
/**
 * 格式化考试答题信息
 */
function fmtExamDaAn(){
	var gdStJsonArray=[];
	var sjStJsonArray=[];
	$(".shiti").each(function(i){
		// 随机 1:随机 ;0:固定
		var suiji=parseInt($(this).attr("suiji"));
		// 试题ID
		var sid=$(this).attr("shitiId");
		// 试题标题
		var title=$(this).find(".title").html();
		var jiexi=$(this).attr("jieda");
		// 试题分数
		var fen=$(this).attr("fen");
		// 试题类型
		var tx=$(this).attr("shitiType");
		var hiddenDate=$("#hidden_"+(i+1)).val();
		//得分
		var defen=$("#dl_qsn_"+(i+1)).attr("defen");
		if(suiji==1){
		}else{
			// 固定试题
			var gdStJson={};
			if(tx==1){
				var ksDaan=$("input[name='danxuan_"+(i+1)+"_xx']:checked").val();
				if(typeof(ksDaan) == "undefined"){
					ksDaan="";
				}
				// 单选题
				gdStJson={
					"sid":sid,// 试题ID
					"title":title,// 试题标题
					"jiexi":jiexi,// 试题解析
					"ksdaan":ksDaan,// 考生答案
					"daan":hiddenDate,// 答案
					"fen":fen,// 分数
					"defen":defen,//得分
					"tx":tx // 试题题型
				};
			}else if(tx==2){
				// 多选题
				var ksDaan="";
				$("input[name='duoxuan_"+(i+1)+"_xx']").each(function(){
					if($(this).is(":checked")){
						ksDaan+=$(this).val()+",";
					}
				});
				ksDaan=ksDaan.substring(0,ksDaan.lastIndexOf(","));
				gdStJson={
						"sid":sid,// 试题ID
						"title":title,// 试题标题
						"jiexi":jiexi,// 试题解析
						"ksdaan":ksDaan,// 考生答案
						"daan":hiddenDate,// 答案
						"fen":fen,// 分数
						"defen":defen,//得分
						"tx":tx // 试题题型
					};
				
			}else if(tx==3){
				// 判断题
				var ksDaan=$("input[name='panduan_"+(i+1)+"_daan']:checked").val();
				if(typeof(ksDaan) == "undefined"){
					ksDaan="";
				}
				gdStJson={
						"sid":sid,// 试题ID
						"title":title,// 试题标题
						"jiexi":jiexi,// 试题解析
						"ksdaan":ksDaan,// 考生答案
						"daan":hiddenDate,// 答案
						"fen":fen,// 分数
						"defen":defen,//得分
						"tx":tx // 试题题型
					};
			}else if(tx==4){
				// 填空题
				var kongJson={};
				$("input[name='tk_"+(i+1)+"']").each(function(k){
					var daan=$(this).val().replace(/\s/g, "");
					var key=(k+1)+"";
					var kong={"kong":daan};
					kongJson[key]=kong;
				});
				gdStJson={
						"sid":sid,// 试题ID
						"title":title,// 试题标题
						"jiexi":jiexi,// 试题解析
						"ksdaan":kongJson,// 考生答案
						"daan":hiddenDate,// 答案
						"fen":fen,// 分数
						"defen":defen,//得分
						"tx":tx // 试题题型
					};
			}
			gdStJsonArray.push(gdStJson);
		}
	});
	var gdStStr=JSON.stringify(gdStJsonArray);
	$("#gdStr").val(gdStStr);
//	var sjStStr=JSON.stringify(sjStJsonArray);
//	$("#sjStr").val(sjStStr);
}
/**
 * 保存标识 1：交卷0：保存
 * 
 * @param oprateFlg
 */
function submit(oprateFlg){
	var sjId=$("#sjId").val();
//	var kuId=$("#kuId").val();
	var gdStr=$("#gdStr").val();
//	var sjStr=$("#sjStr").val();
	var passScore=$("#okrate").val();
//	var credit=$("#credit").val();
//	var errorShitiStr="["+errorShiTi.join()+"]";
	var errorShitiStr = JSON.stringify(errorShiTi);
//	alert(errorShitiStr);
//	alert(score);
	$.ajax({
		async : true,
		url : baseUrl + "/user/submitExam.action",
		data : {
			sjId : sjId,
			kuId : kuId,
			gdStr : gdStr,
//			sjStr : sjStr,
			score : score,
			oprateFlg : oprateFlg,
			passScore : passScore,
			uuid : uuid,
//			credit : credit
			stids : errorShitiStr
		},
		type : 'post',
		dataType : 'json',
		success : function(result){
			if(result.msg == 'success' && oprateFlg == 0) {
//				alert("保存成功！");
				saveSuccessPop();
			} else if(result.msg == 'success' && oprateFlg == 1) {
				//alert("得分：" + result.info);
				var totalScore = result.info;
				$("#defen").text("得分："+totalScore);
				$("#chengjiDiv").show();
			} else if(result.msg == 'error') {
				alert(result.info);
			}
//			alert(JSON.stringify(data));
//			totalScore=data.totalScore;
//			if(data.status==1&&oprateFlg==1){
//				successPop();
//			}
//			if(data.status==1&&oprateFlg==0){
//				saveSuccessPop();
//			}
		}
	});
}
/**
 * 提交试卷
 * 
 * @returns
 */
function submitShiJuan(str){
	var ksId = $("#ksId").val();
	$.ajax({
		url : baseUrl + "/user/isCanSubmitExam.json",
		data : {"ksId" : ksId},
		type : "post",
		dataType : "json",
		success : function(result) {
			if(result.msg == 'error') {
				alert("考试开始"+result.info+"分钟后才可以交卷!");
			} else if (result.msg == 'success') {
				if(str=="hand"){
					//手动交卷
					var message="确定交卷吗？";
					if(notAnswerQesArr.length>0){
						message="你还有未答完的试题，确认交卷吗？";
					}
					if(confirm(message)) {
						$("#saveButton").css("cursor", "default");
						$("#submitButton").css("cursor", "default");
						$('#submitButton').removeAttr('onclick'); 
						$('#saveButton').removeAttr('onclick'); 
						// 把提交和保存按钮置不好用
						fmtExamDaAn();
						submit(1);
						//时间停止
						stopTime();
					}
				}else{
					//自动交卷
					$("#saveButton").css("cursor", "default");
					$("#submitButton").css("cursor", "default");
					$('#submitButton').removeAttr('onclick'); 
					$('#saveButton').removeAttr('onclick'); 
					// 把提交和保存按钮置不好用
					fmtExamDaAn();
					submit(1);
					//时间停止
					stopTime();
				}
			}
		}
	});
};
//定义倒计时函数
function count_down(){
	var int_day, int_hour, int_minute, int_second;
	var flag = false;
	time_distance = $("#time").val();
	time_distance -= 1;
	$("#time").val(time_distance);
	console.log(time_distance);
	if(time_distance >= 0){
		flag = true;
		// 相减的差数换算成天数
		int_day = Math.floor(time_distance/86400);
		time_distance -= int_day * 86400;
		// 相减的差数换算成小时
		int_hour = Math.floor(time_distance/3600);
		time_distance -= int_hour * 3600;
		// 相减的差数换算成分钟
		int_minute = Math.floor(time_distance/60);  
		time_distance -= int_minute * 60; 
		// 相减的差数换算成秒数
		int_second = Math.floor(time_distance);
		
		// 判断小时小于10时，前面加0进行占位
		if(int_hour < 10){
			int_hour = "0" + int_hour;
		} 
		// 判断分钟小于10时，前面加0进行占位
		if(int_minute < 10){
			int_minute = "0" + int_minute;
		} 
		// 判断秒数小于10时，前面加0进行占位
		if(int_second < 10){
			int_second = "0" + int_second;
		}
		// 显示倒计时效果
		var tmSec = int_day + '天 ' + int_hour + '时 ' + int_minute + '分 ' + int_second + '秒';
		$("#countDown").html("交卷倒计时："+tmSec);
	}else{
		//倒计时交卷
		submitShiJuan("countdown");
	}
	if(flag){
		timeID = setTimeout("count_down()",1000);
	}
}
//时间停止
function stopTime(){
	if(timeID != ''){
		clearTimeout(timeID);
	}
	if(time_distance!=0){
		$("#timer").val(time_distance);
	}
	//清理自动保存时间
	clearTimeout(saveTimer);
}
//显示提交成功
function successPop() {
	var scorePublish=$("#scorePublishFlg").val();
	// 浏览器可见区域的高度
	//var wHeight=$(window).height();
	// 浏览器可见区域的宽度
	//var dWidth=$(window).width();
	//var popHeight= $("#chengjiDiv").height();
	//var popWidth= $("#chengjiDiv").width();
	//var height=(wHeight-popHeight)/2;
	//var width=(dWidth-popWidth)/2;
	//$("#chengjiDiv").css("top",height+$(document).scrollTop());
	//$("#chengjiDiv").css("left",width+"px");
	$("#chengjiDiv").show();
	var systime=Date.parse(new Date())/1000;
	var fsTime= parseInt(ResultPublishTimeLong);
	if(systime > fsTime){
		$("#defen").text("得分："+totalScore);
	}else{
//		$("#bluetext").text("查看证书");
//		$("#bluetext").attr("href","mta/P010/myCenter.html");
		$("#defen").hide();
	}
	//打开遮罩层
	//show();
}
//显示保存成功
function saveSuccessPop() {
	// 浏览器可见区域的高度
	//var wHeight=$(window).height();
	// 浏览器可见区域的宽度
	//var dWidth=$(window).width();
	//var popHeight= $("#savePop").height();
	//var popWidth= $("#savePop").width();
	//var height=(wHeight-popHeight)/2;
	//var width=(dWidth-popWidth)/2;
	//$("#savePop").css("top",height+$(document).scrollTop());
	//$("#savePop").css("left",width+"px");
	$("#savePop").show();
	setTimeout('close()', 2000);
}
function close(){
	$("#savePop").hide();
}