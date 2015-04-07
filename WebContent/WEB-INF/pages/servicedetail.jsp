<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="../js/servicedetail.js" type="text/javascript"></script>
</head>
<body>
	<div id="serviceinfo">
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="">&lt;商家：<c:out value="${modelProvider.providerId}" /></td>
					<td width="20">
						<!-- 					<img src="../img/contextMenu.png" width="20" --> <!-- 						height="20" /> -->
					</td>
				</tr>
			</table>
		</div>
		<div id="detailinfo"
			style="display: block; padding-left: 6px; padding-right: 6px;">
			<h3 class="h31">
				<c:out value="${modelProvider.title}" />
				<!-- 	全诚汽车修理厂 -->
			</h3>
			<div>
				<c:out value="${modelProvider.summary}" />
			</div>
			<div style="padding-top: 15px;">
				<table class="layoutTbl" width="100%">
					<tr>
						<td class="">地址： <c:out value="${modelProvider.addr}" />
						</td>
						<td width="100"><button class="greenBtn"
								onclick="javascript:switchdiv('<c:out
									value="${modelProvider.providerId}" />')">地图导航</button></td>
					</tr>
					<tr>
						<td class="">电话: <c:out value="${modelProvider.phone}" />
						</td>
						<td width="100"><a
							href="tel:<c:out value="${modelProvider.phone}" />"><button
									class="greenBtn">电话咨询</button> </a></td>
					</tr>
				</table>
			</div>
			<div class="listBox" style="margin-top: 10px; line-height: 200%">
				综合打分：<span class="redText"><c:out
						value="${modelProvider.score}" /> <!-- 		未有人打分或评价 --> </span><br />
				商家人气：<span class=" redText"><c:out
						value="${modelProvider.business}" />次成效/<c:out
						value="${modelProvider.browse}" />次浏览</span>
			</div>
			<div class="m10">
				<c:if test="${imgsrcs!=null}">
					<c:forEach items="${imgsrcs}" var="imgpsth" varStatus="status">
						<a
							href="providerimglist.do?providerId=<c:out
										value="${modelProvider.providerId}" />"><img
							src="../img/provider/<c:out
										value="${modelProvider.providerId}" />/<c:out
										value="${imgpsth}" />.jpg"
							width="55" height="65" /> </a>
					</c:forEach>
				</c:if>
				<c:if test="${imgsrcs==null}">
					<img src="../img/ic_launcher.png" />
				</c:if>
			</div>
			<div>
				<c:out value="${modelProvider.remark}" />
			</div>
			<hr class="seperator" />
			<h3 class="h31">服务项目</h3>
			<c:forEach items="${items}" var="modelProviderItem"
				varStatus="status">
				<a
					href="../serviceitemdetail.do?itemId=<c:out value="${modelProviderItem.labelId}" />"
					style="text-decoration: none; color: #000;"><div
						class="listBox">
						<span class="textIcon"> <c:out
								value="${modelProviderItem.sysItemName}" />
						</span>
						<c:out value="${modelProviderItem.title}" />


						<table class="layoutTbl" width="100%">
							<tr>
								<td colspan="2"><div class="greyText"
										style="font-size: 15px;">
										<c:out value="${modelProviderItem.summary}" />
									</div></td>
							</tr>
							<tr>
								<td class=" greenText"></td>
								<td width="210" class="greenText"
									style="font-size: 15px; text-align: right"><c:if
										test="${modelProviderItem.price!=null&&modelProviderItem.price!='0'}">
									￥<fmt:formatNumber value="${modelProviderItem.price}"
											pattern="#,#00.00#" />
										<%-- 									<c:out value="${modelProviderItem.pricestr}" /> --%>
										<c:if
											test="${modelProviderItem.oldprice!=null&&modelProviderItem.oldprice!=0}">
									(原价<fmt:formatNumber value="${modelProviderItem.oldprice}"
												pattern="#,#00.00#" />元)
									</c:if>
									</c:if> <c:if
										test="${modelProviderItem.price==null||modelProviderItem.price==0}">
										还未定价
									</c:if></td>
							</tr>
						</table>
					</div> </a>
			</c:forEach>
		</div>
		<!-- 		<div id="map" style="height: 600px; padding: 0 0.5em; display: none;"> -->
		<!-- 		</div> -->
	</div>
</body>
</html>
