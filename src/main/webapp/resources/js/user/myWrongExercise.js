var allids="";
var shitiTotal=0;
var totalScore=0;
var uuid="";
var pc;
var tp;
var xuanX=["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
$(function() {
	$(window).scroll(function () {
		if ($(window).scrollTop() > 200) {
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
	getData();
});
function getData(params) {
	if(params == null)
		params = {};
	$.ajax({
		url : baseUrl + '/user/getUserWrongQsn.json',
		type : "post",
		dataType : "json",
		data : params,
		success : function(data) {
			if(data.xtData != null && data.xtData != ''){
				pc = data.pc;
				tp = data.tp;
				$("#pcSpan").html(data.pc);
				$("#tpSpan").html(data.tp);
				loadSjDl(data);
				scrollTo(0,0);
			}else{
				alert("恭喜您！您从未出现过失误或已经消灭了所有的错题！");
				window.opener=null;window.open('','_self');window.close();
			}
		}
	});
}
function loadSjDl(data){
	// 请求试卷信息
	/**
	 * 段落信息 1~N为对应段落数据，包含t:段落标题，ids：试题ID集合，fen：分值，t可以为空 allids:全部固定试题ID，应用于编辑试卷。
	 */
	var dlData=JSON.parse(data.dlData);
	
	/**
	 * 随机选题 后台重组此数据，所以与数据库中不同 fen：分值 did：段落ID data：试题数据
	 */
	var xtData=JSON.parse(data.xtData);

//	/**
//	 * 试题数据 index:段落试题索引 如：1_7258,1段落索引，7258试题ID data：试题数据
//	 */
//	var stData=eval("("+data.dShitimix+")");

	// 保存所有试题ID
	allids=dlData.allids;
	
	// 删除段落数据中所有试题ID
	delete dlData.allids;
	// 段落索引
	var dlIndex=1;
	// HTML写入容器
	var C = $("#Container");
	C.html("");
	// 选项卡
	var X = $("#datika");
	X.html("");
	// 试题索引
	var qsnIndex=1;
	for(var index in dlData){
		//段落标题
		var dlTitle = dlData[dlIndex].t;
		if(dlTitle != "" && dlTitle != null){
			// 如果段落标题不为空，则向C中追加段落标题
			var titleHtml="<span class='duanluo' id='duanluo"+dlIndex+"'><h3>"+dlTitle+"   共"+getJsonLength(xtData[dlIndex].data)+"题</h3></span>";
			var dlxxkHtml="<h2 class='danxuanka' id='danxuanka"+dlIndex+"'>"+dlTitle+"   共"+getJsonLength(xtData[dlIndex].data)+"题</h2>";
			X.append(dlxxkHtml);
			C.append(titleHtml);
		}
		// 段落KEY
		var key=dlIndex+"";
		// 试题数据
		var num=1;
		var stNumUlHtml='<ul style="border-top: 0px" id="duanluo_'+index+'"></ul>';
		X.append(stNumUlHtml);
		var stNumHtml="";
//		alert(JSON.stringify(xtData));
		/**
		 * 随机选题 key是段落索引的字符串型，如果在随机试题数据中包含段落索引的key则代表此段落有随机选题
		 */
		if( key in xtData){
			/**
			 * 后台组成的随机试题数据
			 */
			var sjdata=xtData[key].data;
			// 具体组成试题HTML方式与固定试题类似
			for(var i=0;i<sjdata.length;i++){
				var item=sjdata[i];
				stNumHtml+='<li class="xuanxiangkaNumber_'+index+'" id="biao_'+item.questionId+'" style="border-top: 1px solid #ccc;"><a href="'+baseUrl+'/user/myWrongExercise.html#dl_qsn_'+qsnIndex+'">'+qsnIndex+'</a></li>';
				var qsnHtml='<table class="shitiTable" did="'+index+'" id="shiti_'+qsnIndex+'" border="0" cellpadding="0" cellspacing="0">';
					qsnHtml+=loadQsnInfo(qsnIndex,item,1);
					qsnHtml+='</table>';
					qsnHtml+="<div id='qsnDetail_"+item.questionId+"' style='display:none;'><h4>试题解析：</h4><div>" + item.detail + "</div><div>";
					if(qsnIndex > 1 && key == item.did+""){
							// 如果已加载试题数>1向上一道试题信息HTML后追加html
							// 向上一道试题信息HTML后追加html
							$("#dl_qsn_"+(qsnIndex-1)).parent().parent().after(qsnHtml);
					}else{
						//没有固定题的场合
						C.append(qsnHtml);
					}
					$("#dl_qsn_"+(qsnIndex)).attr("suiji",1);
				qsnIndex++;
				shitiTotal++;
			}
		}
		$("#duanluo_"+index).append(stNumHtml);
		dlIndex++;
	}
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
function loadQsnInfo(qindex,qsnData,isSuiJi){
	var daan=qsnData.rightKey;//'+qsnData.detail+'
	var gdQsnHtml='<tr id="dl_qsn_'+qindex+'" suiji="0" jieda="" class="shiti" shitiId="'+qsnData.questionId+'" shitiType="'+qsnData.typeId+'"><th colspan="3" align="left">第'+qindex+'题:<span class="title">'+qsnData.title+'</span> </th>'
	+'<th width="100"><input type="hidden" id="hidden_'+qindex+'" value=\''+daan+'\'/></th></tr>';
	// 单选题
	if("options" in qsnData && qsnData.typeId == 1){
		gdQsnHtml += '<tr><td width="720"><ol>';
		var xxdata=qsnData.options;
		var keys=[];
		for(var xxNum in xxdata){
			keys.push(xxNum);
		}
		keys.sort(function(a,b){
            return a-b;}
		);
		for(var i=0; i<keys.length; i++){
			xxNum = keys[i];
		//for(var xxNum in xxdata){
			gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
			gdQsnHtml +='<input name="danxuan_'+qindex+'_xx" value="'+xxNum+'" stId="'+qsnData.questionId+'" type="radio" id="danxuan_'+qindex+'_'+xxNum+'_xx" onclick="choose(this)" class="inputStyle"/>';
			gdQsnHtml +=xxdata[xxNum].option+'</li>';
		}
		gdQsnHtml += "</ol></td></tr>";
		if(qsnData.detail != null && qsnData.detail != '')
			gdQsnHtml += "<tr><td><h4>试题解析：</h4><div>"+qsnData.detail+"</div></td></tr>";
		gdQsnHtml += '<tr id="qsn_daan_'+qsnData.questionId+'" daan=\''+daan+'\' style=""><td><h4><img style="display:none" id="qsn_daan_'+qsnData.questionId+'_right" width="16" height="16" src="' + baseUrl + '/resources/img/ok.png"/><img style="display:none" id="qsn_daan_'+qsnData.questionId+'_wrong" width="16" height="16" src="' + baseUrl + '/resources/img/cancel.png"/><br/>正确答案：'+xuanX[parseInt(daan)]+'</h4></td></tr>';
	}
	// 多选题
	if("options" in qsnData && qsnData.typeId == 2){
//		alert(daan);
//		var daanArray=daan.split(",");
		var daan2 = daan.toString();
		var daanArray=eval(daan);
//		var daanArray=daan;
		for(var ii=0;ii<daanArray.length;ii++){
			daanArray[ii]=xuanX[parseInt(daanArray[ii])];
		}
		gdQsnHtml += '<tr><td width="720"><ol>';
		var xxdata=qsnData.options;
		var keys=[];
		for(var xxNum in xxdata){
			keys.push(xxNum);
		}
		keys.sort(function(a,b){
            return a-b;}
		);
		for(var i=0; i<keys.length; i++){
			xxNum = keys[i];
		//for(var xxNum in xxdata){
			gdQsnHtml +='<li indexid='+qindex+'_'+xxNum+' >';
			gdQsnHtml +='<input name="duoxuan_'+qindex+'_xx" value="'+xxNum+'" stId="'+qsnData.questionId+'" type="checkbox" id="duoxuan_'+qindex+'_'+xxNum+'_xx" onclick="choose(this)" class="inputStyle"/>';
			gdQsnHtml +=xxdata[xxNum].option+'</li>';
		}
		gdQsnHtml += '</ol></td></tr>';
		if(qsnData.detail != null && qsnData.detail != '')
			gdQsnHtml += "<tr><td><h4>试题解析：</h4><div>"+qsnData.detail+"</div></td></tr>";
		gdQsnHtml +='<tr id="qsn_daan_'+qsnData.questionId+'" daan=\''+daan2+'\' style=""><td>'+
		'<h4><img style="display:none" id="qsn_daan_'+qsnData.questionId+'_right" width="16" height="16" src="' + baseUrl + '/resources/img/ok.png"/>'+
		'<img style="display:none" id="qsn_daan_'+qsnData.questionId+'_wrong" width="16" height="16" src="' + baseUrl + '/resources/img/cancel.png"/><br/>'+
		'正确答案：'+daanArray.join(",")+'</h4></td></tr>';
	}
	// 判断题
	if(qsnData.typeId == 3){
		var rdaan="对";
		if(daan==0){
			rdaan="错";
		}
		gdQsnHtml +='<tr><td width="720"><ol>';
		gdQsnHtml +='<li indexid='+qindex+'_1 >';
		gdQsnHtml +='<input name="panduan_'+qindex+'_daan" type="radio" value="1" stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_1_daan" onclick="choose(this)" class="inputStyle"/>';
		gdQsnHtml +='√</li>';

		gdQsnHtml +='<li indexid='+qindex+'_0 >';
		gdQsnHtml +='<input name="panduan_'+qindex+'_daan" type="radio" value="0" stId="'+qsnData.questionId+'" id="panduan_'+qindex+'_0_daan" onclick="choose(this)" class="inputStyle"/>';
		gdQsnHtml +='×</li>';
		gdQsnHtml += "</ol></td></tr>";
		if(qsnData.detail != null && qsnData.detail != '')
			gdQsnHtml += "<tr><td><h4>试题解析：</h4><div>"+qsnData.detail+"</div></td></tr>";
		gdQsnHtml +='<tr id="qsn_daan_'+qsnData.questionId+'" daan=\''+daan+'\' style=""><td>'+
		'<h4><img style="display:none" id="qsn_daan_'+qsnData.questionId+'_right" width="16" height="16" src="' + baseUrl + '/resources/img/ok.png"/>'+
		'<img style="display:none" id="qsn_daan_'+qsnData.questionId+'_wrong" width="16" height="16" src="' + baseUrl + '/resources/img/cancel.png"/><br/>'+
		'正确答案：'+rdaan+'</h4></td></tr>';
	}
	// 填空题
	if(qsnData.typeId == 4){
		var rdaan="";
		gdQsnHtml += '<tr><th colspan="3" align="left">';
		var kongsData=daan;
		for(var ki in kongsData){
			var kongArray=kongsData[ki].key.split("#");
			gdQsnHtml +="空"+ki;
			for(var kai=0;kai<kongArray.length;kai++){
				if(kai==0){
					rdaan+="空"+ki+"正确答案:"+kongArray[kai]+"&nbsp;&nbsp;";
					gdQsnHtml += '<input type="text" stId="'+qsnData.questionId+'" onchange="tiankong(this)" class="kong" name="tk_'+qindex+'_daan" id="tabletextfield" />';
				}else{
				    if(kongArray[kai] != ''){
    					rdaan+="空"+ki+"备选答案:"+kongArray[kai]+"&nbsp;&nbsp;";
				    }
				}
			}
			rdaan+="<br/>";
		}
		gdQsnHtml += "<br/>";
		gdQsnHtml += '</th></tr>';
		if(qsnData.detail != null && qsnData.detail != '')
			gdQsnHtml += "<tr><td><h4>试题解析：</h4><div>"+qsnData.detail+"</div></td></tr>";
		gdQsnHtml +='<tr id="qsn_daan_'+qsnData.questionId+'" daan=\''+JSON.stringify(daan)+'\' style=""><td colspan="3">'+
		'<h4><img style="display:none" id="qsn_daan_'+qsnData.questionId+'_right" width="16" height="16" src="' + baseUrl + '/resources/img/ok.png"/>'+
		'<img style="display:none" id="qsn_daan_'+qsnData.questionId+'_wrong" width="16" height="16" src="' + baseUrl + '/resources/img/cancel.png"/><br/>'+
		''+rdaan+'</h4></td></tr>';
	}
	return gdQsnHtml;
}
/**
 * 单选 多选 判断
 * 
 * @param obj
 */
function choose(obj){
	var tid=$(obj).attr("stId");
	if($(obj).attr("checked")==false){
		$("li[id='biao_"+tid+"']").removeClass("blue");
	}else{
		$("li[id='biao_"+tid+"']").addClass("blue");
	}
}
function tiankong(obj){
	var tid=$(obj).attr("stId");
	var flag = false;
	$(obj).parent().find(".kong").each(function(){
		if($(obj).val()!=""){
			flag = true;
		}
	});
	if(flag){
		$("li[id='biao_"+tid+"']").addClass("blue");
	}else{
		$("li[id='biao_"+tid+"']").removeClass("blue");
	}
}
//添加标记
function add_biaoji(obj){
	var tid=$(obj).attr("stId");
	var qind=$(obj).attr("qind");
	$("li[id='biao_"+tid+"']").addClass("org");
	$("#qsn_daan_"+tid).show();
	var trObj=$(obj).parent().parent();
	var sttype=$(trObj).attr("shitiType");
	
	var isRight = false;
	if(sttype==1){
		var answer=$("input[name=danxuan_"+qind+"_xx]:checked").val();
		var rightAnswer=$("#qsn_daan_"+tid).attr("daan");
		if(answer==rightAnswer){
			$('#qsn_daan_'+tid+'_wrong').hide();
			$('#qsn_daan_'+tid+'_right').show();
			removeWrongByQsnId(tid);
			isRight = true;
		}else{
			$('#qsn_daan_'+tid+'_right').hide();
			$('#qsn_daan_'+tid+'_wrong').show();
		}
		$("input[name=danxuan_"+qind+"_xx]").attr("disabled",true);
	}else if(sttype==2){
		var answerItems=$("input[name=duoxuan_"+qind+"_xx]:checked");
		var chk_value =[];    
		$(answerItems).each(function(){    
			chk_value.push($(this).val());    
		});
		var answer=chk_value.join(",");
		var rightAnswer=$("#qsn_daan_"+tid).attr("daan");
		if(answer==rightAnswer){
			$('#qsn_daan_'+tid+'_wrong').hide();
			$('#qsn_daan_'+tid+'_right').show();
			removeWrongByQsnId(tid);
			isRight = true;
		}else{
			$('#qsn_daan_'+tid+'_right').hide();
			$('#qsn_daan_'+tid+'_wrong').show();
		}
		$("input[name=duoxuan_"+qind+"_xx]").attr("disabled",true);
	}else if(sttype==3){
		var answer=$("input[name=panduan_"+qind+"_daan]:checked").val();
		var rightAnswer=$("#qsn_daan_"+tid).attr("daan");
		if(answer==rightAnswer){
			$('#qsn_daan_'+tid+'_wrong').hide();
			$('#qsn_daan_'+tid+'_right').show();
			removeWrongByQsnId(tid);
			isRight = true;
		}else{
			$('#qsn_daan_'+tid+'_right').hide();
			$('#qsn_daan_'+tid+'_wrong').show();
		}
		$("input[name=panduan_"+qind+"_daan]").attr("disabled",true);
	}else if(sttype==4){
		var answerItems=$("input[name=tk_"+qind+"_daan]");
//		alert(answerItems.length);
		var rightAnswer=$("#qsn_daan_"+tid).attr("daan");
		$("input[name=tk_"+qind+"_daan]").attr("disabled",true);
		var kongsData=JSON.parse(rightAnswer);
//		alert(JSON.stringify(kongsData));
		var wStatus=0;
		for(var i=0;i<answerItems.length;i++){
			var ksdaan=$(answerItems[i]).val();
			var rkey=i+1;
//			alert(kongsData[rkey+""].key);
			var rDaanArray=kongsData[rkey+""].key.split("#");
			if(rDaanArray.indexOf(ksdaan)==-1){
				$('#qsn_daan_'+tid+'_right').hide();
				$('#qsn_daan_'+tid+'_wrong').show();
				wStatus=1;
				break;
			}
		}
		if(wStatus==0){
			$('#qsn_daan_'+tid+'_wrong').hide();
			$('#qsn_daan_'+tid+'_right').show();
			removeWrongByQsnId(tid);
			isRight = true;
		}
//		if(answer==rightAnswer){
//			$('#qsn_daan_'+tid+'_wrong').hide();
//			$('#qsn_daan_'+tid+'_right').show();
//			$.post(baseUrl + '/mta/P100/updUserWrongQsn.html',{qsnid:tid},function(data){
//				if(data>0){
//					alert("您已经消灭了一道错题！下次错题练习中将不会出现此试题，成功就在眼前，加油~^o^！");
//				}
//			},"json");
//		}else{
//			$('#qsn_daan_'+tid+'_right').hide();
//			$('#qsn_daan_'+tid+'_wrong').show();
//		}
		
	}
	if(!isRight && $('#qsnDetail_'+tid).find("div").html() != '')
		$('#qsnDetail_'+tid).show();
}

function removeWrongByQsnId(tid) {
	$.post(baseUrl + '/user/updUserWrongQsn.action',{"questionId":tid},function(data){
		if(data.msg == 'success') {
			alert("您已经消灭了一道错题！下次错题练习中将不会出现此试题，成功就在眼前，加油~^o^！");
		} else if(data.msg == 'error') {
			alert(data.info);
		}
	},"json");
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
function doBefore() {
	if(pc == 1) {
		alert('已经是第一页了！');
		return;
	}
	pc -= 1;
	getData({"pc" : pc});
}
function doNext() {
	if(pc == tp) {
		alert('已经是最后一页了！');
		return;
	}
	pc += 1;
	var data = {"pc" : pc};
	getData(data);
}