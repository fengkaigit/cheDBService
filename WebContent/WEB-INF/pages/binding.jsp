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
<script src="js/binding.js" type="text/javascript"></script>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>

<body style="background: #eee;">
	<div class="navBar">
		<table width="100%" class="layoutTbl">
			<tr>
				<td><a href="index.jsp"
					style="color: white; text-decoration: none;">&nbsp;&lt;绑定账号</a></td>
				<td width="40" class="tart"></td>
			</tr>
		</table>

	</div>
	<div class="loginCard">
		<table width="100%" class="layoutTbl" c>
			<tr>
				<td colspan="2"><span class="greyText" style="font-size: 15px;">账号</span>&nbsp;<input
					type="text" class="plain" id="username" name="username"
					style="width: 160px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><span class="greyText" style="font-size: 15px;">密码</span>
					<input type="password" class="plain" id="password" name="password"
					style="width: 160px;" /></td>
			</tr>
		</table>
		<input type="hidden"  id="openid" name="openid" value="<c:out value="${openid}" />"
			style="width: 160px;" />
	</div>
	<div class="m10 tact">
		<button class="greenBtn3" style="width: 100%;"
			onclick="javascript:binduser();">绑定</button>
	</div>
	<div class="m10 greyText">车主用户登录后，才可以购买服务项目，才可以打分或评价才可以累积记分，积分是可当现金用的。</div>
</body>
</html>
