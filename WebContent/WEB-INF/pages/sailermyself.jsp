<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<script src="js/login.js" type="text/javascript"></script>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>
<body style="background: #eee;">
	<div class="navBarlogin">
		<table width="100%" class="layoutTbl">
			<tr>
				<td><a href="index.jsp"
					style="color: white; text-decoration: none;">&nbsp;&lt;登录</a></td>
				<td width="40" class="tart"></td>
			</tr>
		</table>

	</div>
	<div class="loginCard">
		<table width="100%" class="layoutTbl" c>
			<tr>
				<td>
			       <input type="text" id="username" name="username" class="plain"
					style="width: 100%; color: #999; border: none; height: 20px;"
					value="账号"
					onFocus="if(value==defaultValue){value='';this.style.color='#999'}"
					onBlur="if(!value){value=defaultValue;this.style.color='#999'}" />
				</td>
			</tr>
			<tr>
				<td class=""><div class="androidInputBtnBorder">&nbsp;</div></td>
			</tr>
			<tr>
				<td><input type="text" class="plain" value="密码" id="password"
					name="password"
					style="width: 100%; color: #999; border: none; height: 20px;"
					onFocus="if(value==defaultValue){value='';this.style.color='#999'}"
					onBlur="if(!value){value=defaultValue;this.style.color='#999'}" />
					</td>
			</tr>
			<tr>
				<td class=""><div class="androidInputBtnBorder">&nbsp;</div></td>
			</tr>
		</table>
	</div>
	<div class="m10 tact">
		<button class="greenBtn3" style="width: 100%;"
			onclick="javascript:sailerlogin();">登录</button>
	</div>
</body>
</html>
