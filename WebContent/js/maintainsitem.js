var userModel = null;
var showrawdiv = function() {
	$('#rawdiv').show();
	$('#maindiv').hide();
};
var showprocessdiv = function() {
	$('#process').show();
	$('#maindiv').hide();
};
/**
 * 提交订单
 */
var sumitorder = function() {
	if ($('#providerId').val() == null || $('#providerId').val() == '') {
		alert('请选择商家，你需要在该商家处接受服务。');
		return;
	}
	var type = $('#ordertype').val();
	// 校验是否已经等了，没有登录则弹出登录界面
	if ($('#userId').val() == null || $('#userId').val() == '') {
		$('#maindiv').hide();
		$('#logindiv').show();
		return;
	}
	var price = 0;
	var priceJifen = 0;
	var returnJifen = 0;
	if (type == 1) {
		price = modelService.serviceInfo.priceOnline;
		priceJifen = 0;
		returnJifen = modelService.serviceInfo.returnJifenOnline;
	} else if (type == 2) {
		price = modelService.serviceInfo.priceXianjin;
		priceJifen = 0;
		returnJifen = modelService.serviceInfo.returnJifenXianjin;
	} else {
		price = 0;
		priceJifen = modelService.serviceInfo.priceJifen;
		returnJifen = modelService.serviceInfo.returnJifenJifen;
	}
	var userinfo = '{"payType":"' + type + '","price":"' + price
			+ '","priceJifen":"' + priceJifen + '","returnJifen":"'
			+ returnJifen + '","serviceClassId":"' + $('#serviceClassId').val()
			+ '","serviceId":"' + $('#serviceId').val() + '","providerId":"'
			+ $('#providerId').val() + '","userId":"' + $('#userId').val()
			+ '"}';

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "buyService.do",
		data : {
			buynoteStr : userinfo
		},
		error : function() {
			alert('连接出错!');
		},
		success : function(data) {
			alert('成功购买服务');
			// 调转至订单列表页面
			window.location.href = 'queryBuynoteListByUserId.do?userId='
					+ data.no + '&page=1';
		}
	});
}

var selectprovider = function(checkbox, providerId, priceOnline, priceJifen,
		returnJifenOnline) {
	if (checkbox.checked) {
		$("input[name='checkedbox']").each(function(i, o) {
			if (o != checkbox) {
				o.checked = false;
			}
		});
		$('#price').val(priceOnline);
		$('#priceJifen').val(priceJifen);
		$('#returnJifen').val(returnJifenOnline)
		$('#providerId').val(providerId)
	}

};
/**
 * 
 */
var pagequeryprovider = function() {
	window.location.href = 'serviceItemDetail.do?serviceId='
			+ $('#serviceId').val() + '&serviceClassId='
			+ $('#serviceClassId').val() + '&start=' + $('#start').val()
			+ '&count=' + $('#count').val();
};
/**
 * 分页查询车主评价
 */
var pagingqueryapprise = function(pageindex) {
	var endindex = pageindex * 5;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "queryServiceAppraiseList.do",
		data : {
			serviceId : $('#serviceId').val(),
			serviceClassId : $('#serviceClassId').val(),
			start : 0,
			count : endindex
		},
		error : function() {
			alert('连接出错!');
		},
		success : function(data) {
			var html = '';
			var fetchcount = 0;
			for (i in data) {
				fetchcount++;
				html = html + '<div class="cardLayer"><div class="m10">'
						+ '<div class=" greenText">' + data[i].userName
						+ '</div><div>' + data[i].appraise
						+ '</div><div class=" tart greyText">'
						+ data[i].dateStr + '</div></div></div>';
			}
			var currentindex = fetchcount;
			$('#appriseindex').val(currentindex);
			$('#remarks').html(html);
			queryServiceAppraiseCount(currentindex);
			/*
			 * $('#apppagebar').html( currentindex + '/共' +
			 * $('#rowcoount').val() + '条');
			 */
		}
	});
};
var queryServiceAppraiseCount = function(currentindex) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "queryServiceAppraiseCount.do",
		data : {
			serviceId : $('#serviceId').val(),
			serviceClassId : $('#serviceClassId').val()
		},
		error : function() {
			alert('连接出错!');
		},
		success : function(data) {
			$('#apppagebar').html(currentindex + '/共' + data + '条');
		}
	});

};

var showcommentdiv = function() {
	$('#comment').css('width', $(window).width() - 100);
	$('#commentdiv').show();
	var e = document.getElementById("maindiv");
	scrollTo(0, e.scrollHeight);
};

var sendcomment = function() {
	if ($('#userId').val() == null || $('#userId').val() == '') {
		$('#maindiv').hide();
		$('#logindiv').show();
		return;
	}
	if ($('#comment').val() == null || $('#comment').val() == '') {
		alert('请输入内容');
		return;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "serviceAppraiseDo.do",
		data : {
			serviceId : $('#serviceId').val(),
			serviceClassId : $('#serviceClassId').val(),
			content : $('#comment').val(),
			userId : $('#userId').val()
		},
		error : function() {
			alert('连接出错!');
		},
		success : function(data) {
			// 刷新评论列表
			pagingqueryapprise(1);
			$('#comment').val('')
		}
	});
};

var showmess = function(type) {
	if (type == '1') {
		// 支付类型为1的为在线支付，直接下载app
		 window.location.href='downapp.do';
		 return;
	}
	// 先判断是否选择商家
	if ($('#providerId').val() == null || $('#providerId').val() == '') {
		alert('请选择商家，你需要在该商家处接受服务。');
		return;
	}
	var usertype = '1';
	usertype = $('#usertype').val();
	if ($('#userId').val() == null || $('#userId').val() == '' || usertype == 2) {
		$('#maindiv').hide();
		$('#logindiv').show();
		return;
	}
	//
	var mess = '';
	if (type == '1') {// 在线支付
		/*
		 * $('#mess').text( '在线支付需要通过支付宝等第三方支付平台进行支付。需要支付' +
		 * modelService.serviceInfo.priceOnline + '元,返积分：' +
		 * modelService.serviceInfo.returnJifenOnline + '。支付吗？');
		 */
		// mess = '在线支付需要通过支付宝等第三方支付平台进行支付。需要支付'
		// + modelService.serviceInfo.priceOnline + '元,返积分：'
		// + modelService.serviceInfo.returnJifenOnline + '。支付吗？';
		window.location.href = 'downapp.do';

	} else if (type == '2') {// 现金支付

		/*
		 * $('#mess') .text( '现金支付方式需要你把相应的现金直接给商家。需要支付' +
		 * modelService.serviceInfo.priceXianjin + '元,返积分：' +
		 * modelService.serviceInfo.returnJifenXianjin + '。支付吗？');
		 */
		mess = '现金支付方式需要你把相应的现金直接给商家。需要支付'
				+ modelService.serviceInfo.priceXianjin + '元,返积分：'
				+ modelService.serviceInfo.returnJifenXianjin + '。支付吗？';
	} else {// 积分抵
		getUserjifen();
		if (userModel.jifen - modelService.serviceInfo.priceJifen < 0) {

			// $('#mess').text(
			// '你的积分' + userModel.jifen + '小于该项目需要积分'
			// + modelService.serviceInfo.priceJifen + '，不能积分抵付');
			alert('你的积分' + userModel.jifen + '小于该项目需要积分'
					+ modelService.serviceInfo.priceJifen + '，不能积分抵付');
			return;
			// $('#cancelbutton').text('知道了');
			// $('#okbutton').css('display', 'none');
		} else {
			/*
			 * $('#mess').text( '积分抵付就是用你的已有积分当做现金的使用，不用花钱呀。你现在有' +
			 * userModel.jifen + '积分。该服务需要' +
			 * +modelService.serviceInfo.priceJifen + '积分。支付吗？');
			 */
			mess = '积分抵付就是用你的已有积分当做现金的使用，不用花钱呀。你现在有' + userModel.jifen
					+ '积分。该服务需要' + +modelService.serviceInfo.priceJifen
					+ '积分。支付吗？';
		}

	}
	var returnvalue = confirm(mess);
	if (!returnvalue) {
		return;
	} else {
		$('#ordertype').val(type);
		sumitorder();
	}
	// 获取跟此项目相关的信息

	// $('#messdialog').css('top', winSize.windowHeight * 0.3 + top);
	// $('#messdialog').css('left', availWidth / 2 - 100);
	// $('#messdialog').show();

};
var cancel = function() {
	$('#cancelbutton').text('否');
	$('#okbutton').css('display', 'inline');
	$('#shadowDiv').remove();
	$('#messdialog').hide();
};
var getUserjifen = function() {
	// 
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getUserjifen.do",
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			userModel = data;
		}
	});
}
/**
 * 异步登录
 */
var asynclogin = function() {
	if ($('#username').val() == '' || $('#username').val() == '账号') {
		alert('请填写账号');
		return;
	}
	if ($('#password').val() == '' || $('#password').val() == '密码') {
		//alert('请填写密码');
		//return;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "Login.do",
		data : {
			userNo : $('#username').val(),
			password : $('#password').val(),
			type : 1
		},
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			if (data.ok == true) {
				// 显示主面板
				$('#userId').val(data.no);
				$('#usertype').val(data.level);
				userModel = data;
				alert('登录成功。');
				$('#maindiv').show();
				$('#logindiv').hide();
			} else {
				if (data.loginmess == 'nouser') {
					alert('登录账号不正确！');
				} else if (data.loginmess == 'errorpasswd') {
					alert('密码不正确！');
				} else {
					alert('登录账号或者是密码不正确！');
				}
			}

		}
	});
}