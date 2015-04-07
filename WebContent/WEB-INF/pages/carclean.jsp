<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/carclean.js" type="text/javascript"></script>
</head>

<body>
	<div class="navBar">
		<table class="layoutTbl" width="100%">
			<tr>
				<td class="tart"><a href="index.jsp"
					style="color: white; text-decoration: none;">&lt;洗车</a></td>
				<td width="60"></td>
				<td width="20"></td>
				<td width="20"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<h3 class="h32">人工洗车</h3>
	<div style="padding-left: 5px; padding-right: 5px;">
		<div class="card1">
			<div class="cardTitle tact">可选项目</div>
			<c:forEach items="${servicelist}" var="modelService"
				varStatus="status">
				<div class="cardLayer"
					onclick="javascript:queryservicedetail('<c:out value="${modelService.serviceId}" />','01');">
					<table class="layoutTbl" width="100%">

						<tr>
							<td><a
								href="javascript:queryservicedetail('<c:out value="${modelService.serviceId}" />','01');"
								style="color: #000; text-decoration: none;"> <c:out
										value="${modelService.name}" />
							</a></td>
							<td width="80" class="tart"></td>
						</tr>

						<tr>
							<td><span class="redText tart" style="font-size: 16px;">￥<c:out
										value="${modelService.priceOnline}" />
							</span> <c:if test="${modelService.priceOld!=0}">
									<del class="num">
										<span>￥</span><c:out value="${modelService.priceOld}" />
									</del>
								</c:if></td>
							<td><c:if test="${modelService.preferential==1}">
									<div class="tejia"></div>
								</c:if></td>
						</tr>
					</table>
				</div>

			</c:forEach>
		</div>

		<div class="listBox">
			提供比目前更高品质的洗车服务；<br /> 1.集中配送车大邦认证的洗车液，不伤车漆 2.更严谨的工序保证每步工作都等待到位
		</div>
		<div class="m10 greyText">在车大顾平台上购买服务，如出现任何问题，车大邦负责协调解决。</div>
	</div>
</body>
</html>

