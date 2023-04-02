$(function() {
	//加载考试表格
	$('#examTable').bootstrapTable({
        url: baseUrl + '/user/queryMyExam.json',//请求后台的URL（*）
        method: 'POST',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "desc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        paginationPreText: '‹',//指定分页条中上一页按钮的图标或文字,这里是<  
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>  
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        height : 700,						//表格高度
        contentType: "application/x-www-form-urlencoded",
        smartDisplay:false,
        //得到查询的参数
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名  
                sortOrder: params.order //排位命令（desc，asc） 
            };
            return temp;
        },
        columns: [ {
        	title: '序号',//标题  可不加  
        	width : 50,
        	valign : "middle",
            align : "center",
        	formatter: function (value, row, index) {
                var pageSize = $('#examTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#examTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        }, {
        	field: 'ksName',
            title: '考试名称',
            sortable: true,
            width : 220,
            formatter : function(value, row, index) {
            	html = "<img src='/pic/" + row.ksDataCustom.pic + "' style='width:60px;height:60px'></img>&nbsp;&nbsp;&nbsp;&nbsp;";
            	html += row.ksDataCustom.name;
            	return html;
            }
        }, {
        	field: 'beginTime',
            title: '开始时间',
            valign : "middle",
            align : "center",
            width : 150,
            formatter : function(value, row, index) {
            	return row.ksDataCustom.beginTimeString;
            },
            sortable: true
        }, {
        	field: 'endTime',
            title: '结束时间',
            valign : "middle",
            align : "center",
            width : 150,
            sortable: true,
            formatter : function(value, row, index) {
            	return row.ksDataCustom.endTimeString;
            }
        }, {
        	field: 'totalScore',
            title: '总分',
            valign : "middle",
            align : "center",
            width : 80,
            sortable: true,
            formatter : function(value, row, index) {
            	return row.ksDataCustom.sjDataCustom.totalScore;
            }
        }, {
        	field: 'score',
            title: '得分',
            valign : "middle",
            align : "center",
            width : 80,
            sortable: true
        }, {
            field: 'cz',
            title: '操作',
            valign : "middle",
            align : "center",
            width : 100,
            formatter : function(value, row, index) {
            	var state = row.state;
            	var now = new Date();
            	if(now.getTime() > row.ksDataCustom.endTime.time) {
        			return "<button class='btn btn-danger' disabled='disabled'>已结束</button>";
        		}
            	if(state == 0) {
            		//未报名
            		//获取当前时间
            		var now = new Date();
            		//如果当前时间大于考试时间，则显示已结束
            		if(now.getTime() > row.ksDataCustom.endTime.time) {
            			return "<button class='btn btn-danger' disabled='disabled'>已结束</button>";
            		} else {
            			return "<button class='btn btn-primary' onclick='signUp(" + row.id + ");' >报名</button>";
            		}
            	} else if(state == 1) {
            		return "<button class='btn btn-warning' disabled='disabled'>报名中</button>";
            	} else if(state == 2) {
            		return "<button class='btn btn-info' onclick='joinExam(" + row.id + ");'>参加考试</button>";
            	} else if(state == 3){
            		return "<button class='btn btn-info' onclick='joinExam(" + row.id + ");'>进入考试</button>";	
            	} else {
            		return "<button class='btn btn-success' disabled='disabled'>已完毕</button>";
            	}
            	
            	
            }
        }],
        onLoadSuccess: function () {
        	//加载未读信息提示
        	loadNotRead();
        },
        onLoadError: function () {
            showTips("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            var id = row.id;
            EditViewById(id, 'view');
        },
    });
	$("#allBtn").prop("disabled", "disabled");
});
function search(obj) {
	clearBtnDisable();
	$(obj).prop("disabled", "disabled");
	var name = $(obj).attr("name");
	var state = -1;
	if(name == 'all') {
		state = -1;
	} else if (name == 'unPass') {
		state = 0;
	} else if (name == "onPass") {
		state = 1;
	} else if (name == "notStart") {
		state = 2;
	} else if (name == "starting") {
		state = 3;
	} else if (name == 'started') {
		state = 4;
	}
	
	$("#examTable").bootstrapTable("refresh", {
		url : baseUrl + '/user/queryMyExam.json',
		query : {
			"state" : state
		}
	});
}
function clearBtnDisable() {
	$(allBtn).prop("disabled", "");
	$(unPassBtn).prop("disabled", "");
	$(onPassBtn).prop("disabled", "");
	$(notStartBtn).prop("disabled", "");
	$(startingBtn).prop("disabled", "");
	$(startedBtn).prop("disabled", "");
}
//报名
function signUp(id) {
	
	$.ajax({
		url : baseUrl + "/user/signUpExam.action",
		type : "post",
		data : {"id" : id},
		dataType : "json",
		success : function(result) {
			if(result.msg == 'success') {
				$.messager.popup("报名成功，等待审核中！");
				clearBtnDisable();
				$("#examTable").bootstrapTable("refresh", {
					url : baseUrl + '/user/queryMyExam.json',
					query : {
						"state" : -1
					}
				});
			} else if (result.msg == 'error') {
				$.messager.popup("发生错误，报名失败！" + result.info);
			} else {
				alert(result.msg);
			}
		}
	});
}
//参加考试
function joinExam(kuId) {
	window.open(baseUrl + "/user/joinExam.html?kuId=" + kuId);
}

