var allids="";
var dnandata;//固定答案
var page_select_index=1;

$(function() {
	if (!Array.prototype.indexOf) {
	  Array.prototype.indexOf = function(elt /*, from*/) {
	    var len = this.length >>> 0;

	    var from = Number(arguments[1]) || 0;
	    from = (from < 0)
	         ? Math.ceil(from)
	         : Math.floor(from);
	    if (from < 0)
	      from += len;

	    for (; from < len; from++) {
	      if (from in this &&
	          this[from] === elt)
	        return from;
	    }
	    return -1;
	  };
	}
	
	$.post(baseUrl+"/user/getKsUserInfoById.json",{kuId:kuId},function(data){
		loadExamInfo(data);
		loadSjInfo(data);
	},"json");
});
function loadExamInfo(data) {
	$("#ksName").text(data.ksDataCustom.name);//考试名称
	$("#totalsorce").text(data.ksDataCustom.sjDataCustom.totalScore);//考试总分
	$("#score").text(data.score);//得分
	$("#startTm").text(data.beginTimeString);//开始时间
	$("#endTm").text(data.endTimeString);//结束时间
}
function loadSjInfo(data) {
	dnandata = JSON.parse(data.daanData);//固定答案
//	rdaandata=eval("("+data.rdaandata+")");//随机答案
	//请求试卷信息
	/**
	 * 段落信息
	 * 1~N为对应段落数据，包含t:段落标题，ids：试题ID集合，fen：分值，t可以为空
	 * allids:全部固定试题ID，应用于编辑试卷。
	 */
	var dlData=JSON.parse(data.ksDataCustom.sjDataCustom.gdxt);
	/**
	 * 随机选题
	 * 后台重组此数据，所以与数据库中不同
	 * fen：分值
	 * did：段落ID
	 * data：试题数据
	 */
//	var xtData=eval("("+data.dXtmix+")");
	/**
	 * 试题数据
	 * index:段落试题索引 如：1_7258,1段落索引，7258试题ID
	 * data：试题数据
	 */
	var stData=JSON.parse(data.ksDataCustom.sjDataCustom.xzst);
	//试卷标题
	$("#sjTitle").html(""+data.ksDataCustom.sjDataCustom.title+"");
	//保存所有试题ID
	allids=dlData.allids;
	//删除段落数据中所有试题ID
	delete dlData.allids;
	//段落索引
	var dlIndex=1;
	//HTML写入容器
	var C = $("#Container");
	//试题索引
	var qsnIndex=1;
	for(var index in dlData){
		//循环段落
		var dlTitle=dlData[dlIndex+""].t;
		if(dlTitle != "" && dlTitle != null){
			//如果段落标题不为空，则向C中追加段落标题
			var titleHtml="<div class=\"panel_qsn\" id=\"dl_"+dlIndex+"\">"+
				"<div style='float:left;width:80%'><h4>"+dlTitle+"</h4></div>"+
				"<div style='float:right;width:15%'><h4>（每题"+dlData[dlIndex+""].fen+"分）</h4></div>"+
				"<div class=\"clear\"></div>"+
				"</div>";
			C.append(titleHtml);
		}
		//段落KEY
		var key=dlIndex+"";
		/**
		 * 考试数据
		 */
		var qsnData=stData.data;
		//段落中的固定试题集合重组Array
		var idArray=dlData[dlIndex+""].ids.split(",");
		var num=1;
		for(var qsn in qsnData){
			var oldid=qsnData[qsn].questionId+"";
			//循环所有试题数据
			if(idArray.indexOf(oldid)>-1){
				//如果试题ID是此段落试题则追加试题HTML
				var qsnHtml=loadQsnInfo(qsnIndex,qsnData[qsn],qsnData[qsn].score,0);
				C.append(qsnHtml);
				qsnIndex++;
				if(num==idArray.length-1){
					num=1;
					break;
				}
				num++;
				//考虑试题不会重复则终止循环
				delete stData.data.qsn;
			}
		}
		dlIndex++;
	}
}
var xuanX=['','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','w','x','y','z'];
function loadQsnInfo(qindex,qsnData,qsnfen,ststate){
	//试题标题
	var gdQsnHtml="<div class=\"panel_qsn\" id=\"dl_qsn_"+qindex+"\">"+
				"<div class=\"div_left\">"+
				"<span style=\"font-weight: bold;font-size:20pt !important;\">"+qindex+".</span>"+
				"<br/>"+
				"<span style=\"font-weight: bold;font-size:10pt !important;color:#9cc8f7;\">"+qsnfen+"分</span>"+
				"</div>"+
				"<div class=\"div_right\">"+
				"<div class=\"div_qsn_title\">"+qsnData.title+"</div>";
	var ksdaan;
	var qsnid=qsnData.questionId;
	var daan;
	var defen;
	var tdes="";
	if(ststate==0 && qsnData.typeId<6){
		daan=qsnData.rightKey;
		for(var i=0;i<dnandata.length;i++){
			if(dnandata[i].sid==qsnid){
				ksdaan=dnandata[i].ksdaan;
				defen=dnandata[i].defen;
				if(qsnData.shititypeid==5){
					tdes=dnandata[i].tdes;
				}
				//dnandata.remove(i);
				break;
			}
		}		
	}
	//单选题
	if("options" in qsnData && qsnData.typeId == 1){
		gdQsnHtml += "<div class=\"div_qsn_content\"><ol>";
		var xxdata=qsnData.options;
		
		//for(var xxNum in xxdata){
			
			
			var keys=[];
			for(var xxNum in xxdata){
				keys.push(xxNum);
			}
			keys.sort(function(a,b){
	            return a-b;}
			);
			for(var i=0; i<keys.length; i++){
				xxNum = keys[i];
				gdQsnHtml +="<li indexid=\""+qindex+"_"+xxNum+"\" >" ;
				if(xxNum==ksdaan){
					//根据答案选中
					gdQsnHtml +="<a class=\"li_a_span\"><input type='radio' " +
						"id=\"danxuan_"+qindex+"_"+xxNum+"_xx\"" +
						" class=\"inputStyle\" " +
						"name=\"danxuan_"+qindex+"_xx\" checked=\"checked\"/>"+xxdata[xxNum].option+"</a>";
				}else{
					gdQsnHtml +="<a class=\"li_a_span\"><input type='radio' " +
						"id=\"danxuan_"+qindex+"_"+xxNum+"_xx\"" +
						" class=\"inputStyle\" " +
						"name=\"danxuan_"+qindex+"_xx\"/>"+xxdata[xxNum].option+"</a>";
				}
				gdQsnHtml +="</li></br>";
			}
		//}
		gdQsnHtml += "</ol></div>";
	}
	//多选题
	if("options" in qsnData && qsnData.typeId == 2){
		gdQsnHtml += "<div class=\"div_qsn_content\">"+
		"<ol>";
		var xxdata=qsnData.options;
		var daanArray=ksdaan.split(",");
		var keys=[];
		for(var xxNum in xxdata){
			keys.push(xxNum);
		}
		keys.sort(function(a,b){
            return a-b;}
		);
		//for(var xxNum in xxdata){
		for(var i=0; i<keys.length; i++){
			xxNum = keys[i];
			//根据答案选中
			if(daanArray.indexOf(xxNum+"") > -1){
				gdQsnHtml +="<li indexid=\""+qindex+"_"+xxNum+"\" >" +
					"<a class=\"li_a_span\"><input type='checkbox' " +
					"id=\"duoxuan_"+qindex+"_"+xxNum+"_xx\"" +
					" class=\"inputStyle\" " +
					"name=\"duoxuan_"+qindex+"_xx\" checked=\"checked\"/>"+xxdata[xxNum].option+"</a></li></br>";
			}else{
				gdQsnHtml +="<li indexid=\""+qindex+"_"+xxNum+"\" >" +
					"<a class=\"li_a_span\"><input type='checkbox' " +
					"id=\"duoxuan_"+qindex+"_"+xxNum+"_xx\"" +
					" class=\"inputStyle\" " +
					"name=\"duoxuan_"+qindex+"_xx\"/>"+xxdata[xxNum].option+"</a></li></br>";
			}
		}
		gdQsnHtml += "</ol>"+
		"</div>";
	}
	//判断题
	if(qsnData.typeId == 3){
//		var daan=qsnData.daan;
		gdQsnHtml += "<div class=\"div_qsn_content\">"+
		"<ol>";
		if(ksdaan == "1"){
			gdQsnHtml +="<li indexid=\""+qindex+"_1\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_1_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\" checked=\"checked\"/>√</a></li></br>";
			gdQsnHtml +="<li indexid=\""+qindex+"_0\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_0_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\" />×</a></li></br>";
		}else if(ksdaan == "0"){
			gdQsnHtml +="<li indexid=\""+qindex+"_1\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_1_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\" />√</a></li></br>";
			gdQsnHtml +="<li indexid=\""+qindex+"_0\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_0_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\" checked=\"checked\"/>×</a></li></br>";
		}else{
			gdQsnHtml +="<li indexid=\""+qindex+"_1\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_1_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\" />√</a></li></br>";
			gdQsnHtml +="<li indexid=\""+qindex+"_0\" >" +
						"<a class=\"li_a_span\"><input type='radio' " +
						"id=\"panduan_"+qindex+"_0_daan\"" +
						" class=\"inputStyle\" " +
						"name=\"panduan_"+qindex+"_daan\"/>×</a></li></br>";
		}
		gdQsnHtml += "</ol>"+
		"</div>";
	}
	//填空题
	if(qsnData.typeId == 4){
		gdQsnHtml += "<div class=\"div_qsn_content\ style=\"font-size:10pt !important;\">";
		var kongsData=qsnData.rightKey;
		for(var ki in ksdaan){
			var kongArray=ksdaan[ki].kong.split("#");
			var kong1Array=kongsData[ki].key.split("#");
			gdQsnHtml +="填空"+ki;
			for(var kai=0;kai<kong1Array.length;kai++){
				if(kai==0){
					gdQsnHtml += "考生答案：<input type='text' disabled='disabled' class=\"input_user_info\" value=\""+kongArray[kai]+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;正确答案："+kong1Array[kai];
				}else{
					gdQsnHtml += "</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备选答案"+kai+"：<input type='text' disabled='disabled' class=\"input_user_info\" value=\""+kong1Array[kai]+"\"/>";
				}
			}
			gdQsnHtml += "<br/><br/>";
		}
		gdQsnHtml += "</div>";
	}
	//得分
	if(qsnData.typeId != 6){
		gdQsnHtml+="<div class=\"div_qsn_answer\">本题得分："+defen+"分</div>";
	}
	//正确答案
	if(qsnData.typeId == 1){
		gdQsnHtml+="<div class=\"div_qsn_answer\">正确答案："+"<div class=\"div_qsn_answer_jx_right\">"+xuanX[daan]+"</div></div>";
	}else if(qsnData.typeId == 2){
//		daansp=daan.split(",");
		daansp=daan;
		//正确答案
		gdQsnHtml+="<div class=\"div_qsn_answer\">正确答案：<div class=\"div_qsn_answer_jx_right\">"
		for(var i=0;i<daansp.length;i++){
			gdQsnHtml+=xuanX[daansp[i]]+",";
		}
		gdQsnHtml+="</div></div>";
	}else if(qsnData.typeId == 3){
		if(qsnData.daan==0){
			gdQsnHtml+="<div class=\"div_qsn_answer\">正确答案："+"<div class=\"div_qsn_answer_jx_right\">×</div></div>";
		}else{
			gdQsnHtml+="<div class=\"div_qsn_answer\">正确答案："+"<div class=\"div_qsn_answer_jx_right\">√</div></div>";
		}
	}else if(qsnData.typeId == 5){
		gdQsnHtml+="<div class=\"div_qsn_answer\">正确答案："+"<div class=\"div_qsn_answer_jx_right\">"+qsnData.daan+"</div></div>";
	}
	//解析
	gdQsnHtml +="<div class=\"div_qsn_answer\">"+
					"<div class=\"div_qsn_answer_left\">答题解析：</div>" +
					"<div class=\"div_qsn_answer_jx_right\" style=\"height:auto\">"+qsnData.detail+"</div>"+
				"</div>"+
				"<div class=\"clear\"></div>"+
			"</div>"+
				"<div class=\"clear\"></div>"+
			"</div>";
	return gdQsnHtml;
}