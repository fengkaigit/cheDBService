<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<!-- background: #e1eaff -->
<div id="messdialog"
	style="z-index: 5; position: fixed;_position:absolute; display: none; width: 230px; background: #e1eaff; font-size: 16px;">
	<div id="messtitle"
		style="padding: 5px; border-bottom: solid 1px; border-color: #c6d3fd;">确认购买</div>
	<div id="iniputarea">
		<div id="mess"
			style="padding: 5px; border-bottom: solid 1px; border-color: #c6d3fd;">
            现金支付方式需要你把相应的现金直接给商家。需要支付25元，返积分：0认购吗？

		</div>
	</div>
	<input type="hidden" name="orderno" id="orderno">
	<div
		style="padding: 5px; border-top: solid 1px; border-color: #c6d3fd;">
		<table style="width: 100%;">
			<tr>
				<td style="text-align: center;"><a
					style="text-decoration: none; color: #000;"
					href="javascript:sumitorder();" id="okbutton"><div style="width:100%;text-align:center;">是</div> </a></td>
				<td style="text-align: center;"><a href="javascript:cancel();"
					style="text-decoration: none; color: #000;" id="cancelbutton"><div style="width:100%;text-align:center;">否</div></a></td>
			</tr>
		</table>
	</div>
</div>