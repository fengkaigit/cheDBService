$(document).ready($(function() {
	getuserno();
}));

var getuserno = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getUserNewNo.do",
		data : {
			type : 0
		},
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			$('#userno').val(data);
		}
	});
};
/**
 * 登录系统
 */
var regist = function() {
	var userno = $('#username').val();
	var pass = $('#pass').val();
	if (userno == '用户名（随便起）'||userno=='') {
		alert('请填写用户名称。');
		return;
	}
	if (pass == '密码'||pass=='') {
		alert('请填写密码。');
		return;
	}
	var userinfo = '{"no":"' + $('#userno').val() + '","passwd":"'
			+ $('#pass').val() + '","phone":"' + $('#phonenum').val()
			+ '","name":"' + $('#username').val() + '"}';
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "registerUser.do",
		data : {
			userInfo : userinfo
		},
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			if (data == 'success') {
				alert('注册成功！');
				window.location.href = "queryBuynoteListByUserId.do?userId="
						+ $('#userno').val() + '&page=1';
			}
		}
	});
};
var focusfunction = function(obj) {
	if (obj.value == obj.defaultValue) {
		value = '';
		obj.style.color = '#999'
	}
};
var blurfunction = function(obj) {
	if (!obj.value) {
		obj.value = obj.defaultValue;
		obj.style.color = '#999'
	}
};
var showmess = function() {
	var display = $('#phonemess').css('display');
	if (display == 'none') {
		$('#phonemess').css('display', 'block')
	} else {
		$('#phonemess').css('display', 'none')
	}
}
