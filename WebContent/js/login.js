/**
 * 登录系统
 */
var login = function() {
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
				// 跳转到我的业务列表中
				window.location.href = 'queryBuynoteListByUserId.do?userId='
						+ data.no + '&page=1';
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
};
var sailerlogin = function() {
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
			type : 2
		},
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			if (data.ok == true) {
				// 跳转到我的业务列表中
				window.location.href = 'queryBuynoteListByProviderId.do';
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