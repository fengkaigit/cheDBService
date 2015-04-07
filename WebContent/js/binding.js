/**
 * 绑定账号
 */
var binduser = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "bindinguseraccount.do",
		data : {
			userNo : $('#username').val(),
			password : $('#password').val(),
			openid : $('#openid')
		},
		error : function() {
			alert('连接出错！');
		},
		success : function(data) {
			if (data.success == true) {
				alert('绑定账号成功!');
			} else {
				alert(data.mess);
			}
		}
	});
}