/**
 * 完成订单
 */
var openconfirmorderdiv = function(busiNo,type) {
	// alert(busiNo);
	$('#orderno').val(busiNo);
	showmess('',type);
};
var confirmorder = function() {
	var busiNo = $('#ordertailno').val();
	if (busiNo == null || busiNo == '') {
		alert('请输入订单最后三位');
		return;
	}
	var orderno = $('#orderno').val();
	var tailno = orderno.split('-')[2];
	if (busiNo != tailno) {
		alert('订单号不匹配，请向车主询问订单号的最后3位');
		return;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "updateBusinoteInfo.do",
		data : {
			busiNo : $('#orderno').val(),
			// ordertailno : $('#ordertailno').val(),
			newStatus : 2
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			if (data == 'success') {
				$('#dg' + $('#orderno').val()).removeClass('curProcActive');
				$('#dg' + $('#orderno').val()).addClass('curProc');
				$('#span' + $('#orderno').val()).removeClass('curProc');
				$('#span' + $('#orderno').val()).addClass('curProcActive');
				$('#tr' + $('#orderno').val()).remove();
				$('#ordertailno').val('');
				$('#record' + $('#orderno').val()).text($('#orderno').val());
				cancel();
			}
			// 成功则修改订单状态，修改商户确认界面的样式，去掉‘我确认按钮’
		}
	});

};
var showmess = function(mess, type) {
	var availHeight = $(window).height();
	var availWidth = $(window).width();
	if(type==2){
		$('#mess').text('这个定单位“现金支付。”一定收取客户现金');
	}else{
		$('#mess').text('');	
	}
	$('#messdialog')
			.css('top', $(document).scrollTop() + availHeight / 2 - 100);
	$('#messdialog').css('left', availWidth / 2 - 100);
	$('#messdialog').show();

};
var cancel = function() {
	$('#messdialog').hide();
};
/**
 * 显示下一页
 */
var shownextpage = function(pageindex) {
	window.location.href = 'queryBuynoteListByProviderId.do?page=' + pageindex;

}
