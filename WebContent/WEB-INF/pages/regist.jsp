<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/regist.js" type="text/javascript"></script>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="background: #eee;">
	<div class="navBarlogin">&lt;注册</div>
	<div style="padding-left: 5px; padding-right: 5px;">
		<div class="loginCard">
			账号：<br />
			<table width="100%" border="0" cellspacing="0" class="layoutTbl">
				<tr>
					<td width=""><input type="text" class="plain" id="userno"
						name="userno" style="width: 100px; border: none; height: 20px;"
						readonly="true" value="120009" /></td>
					<td width="60" class="tart" style="text-align:right;">&nbsp;<input name="" type="button"
						class="greenBtn" onclick="javascript:getuserno();" value="换一个" /></td>
				</tr>
			</table>

		</div>
		<div class="loginCard">
			<table width="100%" class="layoutTbl">
				<tr>
					<td><input type="text" id="username" name="username"
						class="plain"
						style="width: 100%; color: #999; border: none; height: 20px;"
						value="用户名（随便起）"
						onFocus="if(value==defaultValue){value='';this.style.color='#999'}"
						onBlur="if(!value){value=defaultValue;this.style.color='#999'}" />
					</td>
				</tr>
				<tr>
					<td class=""><div class="androidInputBtnBorder">&nbsp;</div></td>
				</tr>
				<tr>
					<td><input type="text" class="plain" value="密码" id="pass"
						name="pass"
						style="width: 100%; color: #999; border: none; height: 20px;"
						onFocus="if(value==defaultValue){value='';this.style.color='#999'}"
						onBlur="if(!value){value=defaultValue;this.style.color='#999'}" /></td>
				</tr>
				<tr>
					<td class=""><div class="androidInputBtnBorder">&nbsp;</div></td>
				</tr>
				<tr>
					<td><input type="text" class="plain" value="手机号（可不输）"
						id="phonenum" name="phonenum"
						style="width: 100%; color: #999; border: none; height: 20px;"
						onFocus="if(value==defaultValue){value='';this.style.color='#999'}"
						onBlur="if(!value){value=defaultValue;this.style.color='#999'}" />
					</td>
				</tr>
				<tr>
					<td class=""><div class="androidInputBtnBorder"></div></td>
				</tr>
			</table>
		</div>
		<div class="m10 tact">
			<button class="greenBtn3" style="width: 100%;"
				onclick="javascript:regist();">注册</button>
		</div>
		<div class="m10 greyText">5秒钏完成注册，不收集任何信息</div>
	</div>
</body>
</html>
