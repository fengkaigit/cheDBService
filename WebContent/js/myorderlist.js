var cancelorder = function(busiNo) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "updateBusinoteInfo.do",
		data : {
			busiNo : busiNo,
			newStatus : 0
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			window.location.href = "queryBuynoteListByUserId.do?userId="
					+ $('#userId').val() + '&page=' + $('#page').val();
		}
	});

};
/**
 * 完成订单
 */
var finishorder = function(busiNo) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "updateBusinoteInfo.do",
		data : {
			busiNo : busiNo,
			newStatus : 3
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			window.location.href = "queryBuynoteListByUserId.do?userId="
					+ $('#userId').val() + '&page=' + $('#page').val();
		}
	});

};

/**
 * 给商家爱打分
 */
var scoreBusi = function(busiNo, busiscore) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "updateBusinoteInfo.do",
		data : {
			busiNo : busiNo,
			newStatus : 4,
			score : busiscore
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			window.location.href = "queryBuynoteListByUserId.do?userId="
					+ $('#userId').val() + '&page=' + $('#page').val();
		}
	});

};
var refreshOrder = function(orderno) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "queryBusinoteById.do",
		data : {
			busiNo : orderno
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			if (data.status == 2) {
				$('#zf' + orderno).removeClass('curProcActive');
				$('#zf' + orderno).addClass('curProc');
				$('#sj' + orderno).removeClass('curProc');
				$('#sj' + orderno).addClass('curProcActive');
				$('#sx' + orderno).remove();
			} else {
				alert('商家未确认，告诉商家订单号的最后3位。')
			}
		}
	});
}
/**
 * 显示下一页
 */
var shownextpage = function(pageindex) {
	window.location.href = 'queryBuynoteListByUserId.do?page=' + pageindex;

}
