<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/sellerorderlist.js" type="text/javascript"></script>
<title>车大邦</title>
</head>
<body style="background-color: #ddd;">
	<div class="navBar">
		<table class="layoutTbl" width="100%">
			<tr>
				<td class="tart"><a href="index.jsp"
					style="color: white; text-decoration: none;">&lt;业务记录</a></td>
				<td width="60"></td>
				<td width="20"></td>
				<td width="20"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="m10" style="margin: 5px;">
		<c:forEach items="${modelBusinotes}" var="modelBusinote"
			varStatus="status">
			<table width="100%" border="0" cellpadding="0" class="layoutTbl"
				style="background: #fff;">
				<tr>
					<td colspan="2"><a style="text-decoration: none; color: #000;"
						href="serviceItemDetail.do?serviceId=<c:out
								value="${modelBusinote.serviceId}" />&serviceClassId=<c:out
								value="${modelBusinote.serviceClassId}" />"><c:out
								value="${modelBusinote.serviceName}" /> </a></td>
				</tr>
				<tr>
					<td colspan="2">
					<c:choose>
							<c:when test="${modelBusinote.payType==2}">
								￥<c:out value="${modelBusinote.price}" />元
							</c:when>
							<c:when test="${modelBusinote.payType==1}">
								￥<c:out value="${modelBusinote.price}" />元
							</c:when>
							<c:when test="${modelBusinote.payType==3}">
								<c:out value="${modelBusinote.priceJifen}" />积分
							</c:when>
							<c:otherwise>
								￥<c:out value="${modelBusinote.price}" />元
							</c:otherwise>
						</c:choose>
					 
					</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${modelBusinote.payType==2}">
								现金支付
							</c:when>
							<c:when test="${modelBusinote.payType==1}">
								第三方支付
							</c:when>
							<c:when test="${modelBusinote.payType==3}">
								积分抵付
							</c:when>
							<c:otherwise>
								现金支付
							</c:otherwise>
						</c:choose></td>
				</tr>

				<tr>
					<td colspan="3"><span
						id="record<c:out value="${modelBusinote.buynoteNo}" />"><c:choose>
								<c:when test="${modelBusinote.status==1}">
							订单号：<c:out value="${modelBusinote.tempbuynoteNo}" />
								</c:when>
								<c:otherwise>
							订单号：<c:out value="${modelBusinote.buynoteNo}" />
								</c:otherwise>
							</c:choose></span></td>
				</tr>
				<c:choose>
					<c:when test="${modelBusinote.status==4}">
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">
								<div style="display: table; width: 100%;">
									<c:choose>
										<c:when test="${modelBusinote.status==1}">
											<span class="curProcActive"
												id="dg<c:out value="${modelBusinote.buynoteNo}" />"
												style="width: 4em;">用户订购</span>
										</c:when>
										<c:otherwise>
											<span class="curProc"
												id="dg<c:out value="${modelBusinote.buynoteNo}" />"
												style="width: 4em;">用户订购</span>
										</c:otherwise>
									</c:choose>
									<span class="procSeperator"><div>&nbsp;</div></span>
									<c:choose>
										<c:when test="${modelBusinote.status==2}">
											<span class="curProcActive"
												id="span<c:out value="${modelBusinote.buynoteNo}" />"
												style="width: 4em;">商户确认</span>
										</c:when>
										<c:otherwise>
											<span class="curProc"
												id="span<c:out value="${modelBusinote.buynoteNo}" />"
												style="width: 4em;">商户确认</span>
										</c:otherwise>
									</c:choose>
									<span class="procSeperator"><div>&nbsp;</div></span>
									<c:choose>
										<c:when test="${modelBusinote.status==3}">
											<span class="curProcActive" style="width: 4em;">用户打分</span>
										</c:when>
										<c:otherwise>
											<span class="curProc" style="width: 4em;">用户打分</span>
										</c:otherwise>
									</c:choose>
								</div>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr id="tr<c:out value="${modelBusinote.buynoteNo}" />">
					<td></td>
					<td>&nbsp;</td>
					<td class="textright"><c:choose>
							<c:when test="${modelBusinote.status==1}">
								<input type="button" class="greenBtn"
									onclick="javascript:openconfirmorderdiv('<c:out value="${modelBusinote.buynoteNo}" />','<c:out value="${modelBusinote.payType}" />')"
									value="我确认" />
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td colspan="3"><a
						href="provider/providerdetail.do?providerId=<c:out value="${modelBusinote.providerId}" />"
						style="text-decoration: none; color: #000; font-size: 19px;"><c:out
								value="${modelBusinote.userName}" /></td>
				</tr>
			</table>
			<br>
		</c:forEach>
	</div>
	<!--  
	<c:if test="${empty modelBusinotes}">
		<div class="m10">
			<strong> 你还没有服务记录</strong> <br> <br>
			我们建议你把爱车的每笔花费都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能， 让你对爱车的支出状况了如指掌。 <br>
			<br> <strong> 可以通过两种方法添加爱车服务记录：</strong> <br> <br>
			<li>你在商家的相应服务项目中认购:</li>
			<li>你自己进行添加，见右上角的菜单:</li>
		</div>
	</c:if>
	-->
	<div class="tart" style="text-align: right;">
		<c:if test="${endpage==1&&fn:length(modelBusinotes)!=0}">
			<button class="greenBtn">已是全部</button>
		</c:if>
		<c:if test="${endpage==0&&fn:length(modelBusinotes)!=0}">
			<button class="greenBtn"
				onclick="javascript:shownextpage('<c:out value="${page}" />')">加载下一页</button>
		</c:if>
	</div>
	<input type="hidden" name="userId" id="userId"
		value="<c:out value="${userId}" />">
	<input type="hidden" name="page" id="page"
		value="<c:out value="${page}" />">
	<div id="messdialog"
		style="z-index: 5; position: absolute; display: none; width: 210px; background: #e1eaff; font-size: 14px;">
		<div id="mess"
			style="padding: 5px; border-bottom: solid 1px; border-color: #c6d3fd;">这个订单为“现金支付”。一定收取客户现金</div>
		<div id="iniputarea">
			<table style="width: 100%;">
				<tr>
					<td><em>订单号最后三位&nbsp;</em></td>
					<td><input type="text" name="ordertailno" id="ordertailno"
						maxlength="3" style="width: 90px;font-size:16pt"></td>

				</tr>
			</table>
		</div>
		<input type="hidden" name="orderno" id="orderno">
		<div
			style="padding: 5px; border-top: solid 1px; border-color: #c6d3fd;">
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;"><a
						style="text-decoration: none; color: #000;"
						href="javascript:confirmorder();">确定</a></td>
					<td style="text-align: center;"><a href="javascript:cancel();"
						style="text-decoration: none; color: #000;">取消</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>