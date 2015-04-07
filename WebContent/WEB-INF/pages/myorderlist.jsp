<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="js/myorderlist.js" type="text/javascript"></script>
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
					<td width="60"><span class="textIcon"> <c:if
								test="${modelBusinote.serviceClassId==\"01\"}">
        洗车
    </c:if> <c:if test="${modelBusinote.serviceClassId==\"02\"}">
        保养
    </c:if> <c:if test="${modelBusinote.serviceClassId==\"03\"}">
        检修
    </c:if> <c:if test="${modelBusinote.serviceClassId==\"04\"}">
          轮胎
    </c:if>
					</span></td>
					<td colspan="2"><a style="text-decoration: none; color: #000;"
						href="serviceItemDetail.do?serviceId=<c:out
								value="${modelBusinote.serviceId}" />&serviceClassId=<c:out
								value="${modelBusinote.serviceClassId}" />">
							<c:out value="${modelBusinote.serviceName}" />
					</a></td>
				</tr>
				<tr>
					<td colspan="3">订单号：<c:out value="${modelBusinote.buynoteNo}" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><c:choose>
							<c:when test="${modelBusinote.payType==2}">
								<span class="redText">现金支付</span>￥<c:out
									value="${modelBusinote.price}" />元
							</c:when>
							<c:when test="${modelBusinote.payType==1}">
								<span class="redText">线上支付</span>￥<c:out
									value="${modelBusinote.price}" />元
							</c:when>
							<c:otherwise>
								<span class="redText">积分抵付</span>
								<c:out value="${modelBusinote.priceJifen}" />积分
							</c:otherwise>
						</c:choose></td>
					<td width="160" class="textright"><c:if
							test="${modelBusinote.returnJifen!=null&&modelBusinote.returnJifen!=0}">
					“完成”后返<c:out value="${modelBusinote.returnJifen}" />积分
					</c:if> <c:if
							test="${modelBusinote.returnJifen==null||modelBusinote.returnJifen==0}">
				不返积分
					</c:if></td>
				</tr>
				<tr>
					<td colspan="3">
						<div style="display: table; width: 100%;">
							<c:choose>
								<c:when test="${modelBusinote.status==1}">
									<span class="curProcActive"
										id="zf<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 2em;">支付</span>
								</c:when>
								<c:otherwise>
									<span class="curProc"
										id="zf<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 2em;">支付</span>
								</c:otherwise>
							</c:choose>
							<span class="procSeperator"><div>&nbsp;</div></span>
							<c:choose>
								<c:when test="${modelBusinote.status==2}">
									<span class="curProcActive"
										id="sj<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 4em;">商家确认</span>
								</c:when>
								<c:otherwise>
									<span class="curProc"
										id="sj<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 4em;">商家确认</span>
								</c:otherwise>
							</c:choose>
							<span class="procSeperator"><div>&nbsp;</div></span>
							<c:choose>
								<c:when test="${modelBusinote.status==3}">
									<span class="curProcActive"
										id="wc<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 2em;">完成</span>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${modelBusinote.status==2}">
											<span class="curProc"
												id="wc<c:out value="${modelBusinote.buynoteNo}" />">
												<a style="text-decoration: none; color: #000;"
												href="javascript:finishorder('<c:out value="${modelBusinote.buynoteNo}" />')">完成</a>
											</span>
										</c:when>
										<c:otherwise>
											<span class="curProc"
												id="wc<c:out value="${modelBusinote.buynoteNo}" />"
												style="width: 2em;">完成</span>
										</c:otherwise>
									</c:choose>

								</c:otherwise>
							</c:choose>
							<span class="procSeperator"><div>&nbsp;</div></span>
							<c:choose>
								<c:when test="${modelBusinote.status==4}">
									<span class="curProcActive"
										id="df<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 2em;">打分</span>
								</c:when>
								<c:otherwise>
									<span class="curProc"
										id="df<c:out value="${modelBusinote.buynoteNo}" />"
										style="width: 2em;">打分</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				<tr id="sx<c:out value="${modelBusinote.buynoteNo}" />">
					<td><c:if test="${modelBusinote.status==1}">
							<input name="button" type="button"
								onclick="javascript:refreshOrder('<c:out value="${modelBusinote.buynoteNo}" />');"
								class="greenBtn" value="刷新" />
						</c:if></td>
					<td>&nbsp;</td>
					<td class="textright"><c:choose>
							<c:when test="${modelBusinote.status==1}">
								<input type="button" class="greenBtn"
									onclick="javascript:cancelorder('<c:out value="${modelBusinote.buynoteNo}" />')"
									value="取消订单" />
							</c:when>

							<c:when test="${modelBusinote.status==3}">
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td colspan="3"><a
						href="provider/providerdetail.do?providerId=<c:out value="${modelBusinote.providerId}" />"
						style="text-decoration: none; color: #000; font-size: 19px;"><c:out
								value="${modelBusinote.providerName}" /></td>
				</tr>
				<c:if test="${modelBusinote.status==3}">
					<tr>
						<td colspan="3">
							<table width="100%">
								<tr>
									<td colspan="5" style="text-align: right;">给商家打分（匿名的）&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</tr>
								<tr>
									<td></td>
									<td colspan="2" style="text-align: right;"><span
										class="textIcon"
										onclick="javascript:scoreBusi('<c:out value="${modelBusinote.buynoteNo}" />',1);">
											1 </span> <span class="textIcon"
										onclick="javascript:scoreBusi('<c:out value="${modelBusinote.buynoteNo}" />',2);">
											2 </span> <span class="textIcon"
										onclick="javascript:scoreBusi('<c:out value="${modelBusinote.buynoteNo}" />',3);">
											3 </span> <span class="textIcon"
										onclick="javascript:scoreBusi('<c:out value="${modelBusinote.buynoteNo}" />',4);">
											4 </span> <span class="textIcon"
										onclick="javascript:scoreBusi('<c:out value="${modelBusinote.buynoteNo}" />',5);">
											5 </span></td>
									<td></td>
									<td></td>

								</tr>
							</table>
						</td>

					</tr>
				</c:if>
				<c:if test="${modelBusinote.status==4}">
					<tr>
					</tr>
					<td></td>
					<td></td>
					<td class="textright">你给商家打了<c:out
							value="${modelBusinote.score}" />分
					</td>
				</c:if>
			</table>
			<br>
		</c:forEach>
	</div>
	<c:if test="${empty modelBusinotes}">
		<div class="m10">
			<strong> 你还没有服务记录</strong> <br> <br>
			我们建议你把爱车的每笔花费都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能， 让你对爱车的支出状况了如指掌。 <br>
			<br> <strong> 可以通过两种方法添加爱车服务记录：</strong> <br> <br>
			<li>你在商家的相应服务项目中认购:</li>
			<li>你自己进行添加，见右上角的菜单:</li>
		</div>
	</c:if>
	<c:if test="${not empty modelBusinotes}">
		<div class="tart" style="text-align: right;">
			<c:if test="${endpage==1}">
				<button class="greenBtn">已是全部</button>
			</c:if>
			<c:if test="${endpage==0}">
				<button class="greenBtn"
					onclick="javascript:shownextpage('<c:out value="${page}" />')">加载下一页</button>
			</c:if>
		</div>
	</c:if>
	<input type="hidden" name="userId" id="userId"
		value="<c:out value="${userId}" />" />
	<input type="hidden" name="page" id="page"
		value="<c:out value="${page}" />" />
</body>
</html>