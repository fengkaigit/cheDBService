<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="logindiv" style="display: none">
	<div class="navBar">
		<table width="100%" class="layoutTbl">
			<tr>
				<td><a href="index.jsp"
					style="color: white; text-decoration: none;">&nbsp;&lt;登录</a></td>
				<td width="40" class="tart"><input name="" type="button"
					class="greenBtn2" value="注册"
					onclick="javascript:window.location.href='regist.do'" /></td>
			</tr>
		</table>

	</div>
	<div class="loginCard">
		<table width="100%" class="layoutTbl" c>
			<tr>
				<td><input type="text" id="username" name="username"
					class="plain"
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
			onclick="javascript:asynclogin();">登录</button>
	</div>
	<div class="m10 greyText">车主用户登录后，才可以购买服务项目，才可以打分或评价才可以累积记分，积分是可当现金用的。</div>
</div>

