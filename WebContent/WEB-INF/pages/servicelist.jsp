<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link href="../css/menu.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="../js/servicelist.js" type="text/javascript"></script>
<script
	src="http://api.map.baidu.com/api?v=1.5&ak=3fntf7DHiY8PIbllO4UU0inr"
	type="text/javascript"></script>
</head>
<body>
	<div class="navBar" style="height: 38px;">
		<table class="layoutTbl" width="100%">
			<tr>
				<td class="tart">&lt;找商家</td>
				<td style="text-align: right; width: 30px;">
					<table class="layoutTbl" style="text-align: right;" width="100%">
						<tr>
							<!-- 							<td width="60"><input class="plain" style="background: #390" /></td> -->
							<td width="10"><a href="javascript:switchsearchdiv();"><img
									src="../img/actionbar_search_icon.png" width="30" height="30" /></a></td>
							<td width="10"><a href="javascript:switchmapdiv();"><img
									src="../img/shift.png" width="25" height="25" /></a></td>
							<td width="10" style="text-align: center;">
								<ul id="nav"
									style="width: 80px; height: 20px; margin: 0; padding: 0; text-align: left;">
									<li style="width: 50px;"><c:choose>
											<c:when test="${sort==1}">
												<a id="sorttypeicon" style="width: 40px; font-size: 15px;">&nbsp;&nbsp;距离</a>
											</c:when>
											<c:when test="${sort==2}">
												<a id="sorttypeicon" style="width: 40px; font-size: 15px;">&nbsp;&nbsp;口碑</a>
											</c:when>
											<c:when test="${sort==3}">
												<a id="sorttypeicon" style="width: 40px; font-size: 15px;">&nbsp;&nbsp;价格</a>
											</c:when>
											<c:otherwise>
												<a id="sorttypeicon" style="width: 40px; font-size: 15px;">&nbsp;&nbsp;距离</a>
											</c:otherwise>
										</c:choose>

										<ul style="z-index: 99; background: #000;">
											<li style="background: #000;">
												<div>
													<table style="font-size: 15px;">
														<tr>
															<td><img src="../img/byjuli.png"
																style="width: 20px; height: 20px;" /></td>
															<td><a href="javascript:changesearchetype(1,'距离');"
																style="color: #fff; font-size: 15px;">按距离</a></td>
														</tr>
													</table>
												</div>
											</li>
											<li style="background: #000;">
												<table>
													<tr>
														<td><img src="../img/bykoubei.png"
															style="width: 20px; height: 20px;" /></td>
														<td><a href="javascript:changesearchetype(2,'口碑');"
															style="color: #fff; font-size: 15px;">按口碑</a></td>
													</tr>
												</table>
											</li>
											<li style="background: #000;">
												<table>
													<tr>
														<td><img src="../img/bymoney.png"
															style="width: 20px; height: 20px;" /></td>
														<td><a href="javascript:changesearchetype(3,'价格');"
															style="color: #fff; font-size: 15px;">按价格</a></td>
													</tr>
												</table>
											</li>
										</ul></li>
								</ul>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div class="searchBar">
		<div id="itemnamediv">
			<c:out value="${pricestr}" />
			<c:out value="${itemname}" />
		</div>
		<div id="searchdiv" style="display: none;">
			<table class="layoutTbl" width="100%">
				<tr>
					<td><input class="plain" id="searchetext"
						style="width: 95%; color: #fff;" /></td>
					<td width="20"><a href="javascript:searche();"><img
							src="../img/actionbar_search_icon.png"
							style="width: 30px; height: 30px;" /> </a></td>
					<td width="20"><a href="javascript:switchsearchdiv();"><img
							src="../img/delete.png" style="width: 30px; height: 30px;" /> </a></td>
				</tr>
			</table>
		</div>

	</div>
	<input type="hidden" name="strSysItemList" id="strSysItemList"
		value="<c:out value="${strSysItemList}" />" />
	<input type="hidden" name="level" id="level"
		value="<c:out value="${level}" />" />
	<input type="hidden" name="sort" id="sort"
		value="<c:out value="${sort}" />" />
	<input type="hidden" name="itemname" id="itemname"
		value="<c:out value="${itemname}" />" />
	<div id="serviceslist"
		style="display: none; padding-left: 5px; padding-right: 5px;">
		<div id="serlist">
			<c:forEach items="${providers}" var="modelProvider"
				varStatus="status" end="9">
				<div class="listBox" style="margin-top: 10px;">
					<a
						href="providerdetail.do?providerId=<c:out
										value="${modelProvider.providerId}" />">
						<h3>
							<c:out value="${modelProvider.title}" />
						</h3>
						<div class="greyText">
							<c:out value="${modelProvider.addr}" />
						</div>
						<div class="redText">
							<c:out value="${modelProvider.business}" />
							次成效/
							<c:out value="${modelProvider.browse}" />
							次浏览
						</div>
					</a>
				</div>

				<c:forEach items="${modelProvider.providerItems}" var="providerItem"
					varStatus="status">
					<div class=" listBox">
						<table class="layoutTbl" width="100%">
							<tr>
								<td><span class="textIcon"> <c:out
											value="${providerItem.sysItemName}" />

								</span> <c:out value="${providerItem.title}" /></td>
								<td width="80" class="greenText"
									style="font-size: 14px; text-align: right;"><c:if
										test="${providerItem.price!=null&&providerItem.price!=0}">
									￥<fmt:formatNumber value="${providerItem.price}"
											pattern="#,#00.00#" />
										<%-- 									<c:out value="${providerItem.price}" /> --%>
									</c:if> <c:if
										test="${providerItem.price==null||providerItem.price==0}">
										还未定价
									</c:if> <!-- 								还未定价 --></td>
							</tr>
						</table>
					</div>
				</c:forEach>
				<table class="layoutTbl" width="100%">
					<tr>
						<td></td>
						<td width="80" class="greenText" style="text-align: right;"><span
							class="textIcon">都有了</span></td>
					</tr>
				</table>

			</c:forEach>
		</div>
		<div class="tart" style="margin: 10px; text-align: right;">
			<c:if test="${providers== null || fn:length(providers) <=10}">
				<span class="textIconActive">都有了</span>
			</c:if>
			<c:if test="${providers!= null&&fn:length(providers)>10}">
				<a href="javascript:loadnextpage();"><span
					class="textIconActive" id="loadnextpagebtn">加载下一页</span></a>
			</c:if>
			<input type="hidden" name="rowindex" id="rowindex" value="9" />
		</div>
	</div>
	<div id="map" style="height: 600px; padding: 0 0.5em; display: block;"></div>
</body>
<script language="javascript" type="text/javascript">
	window.onload = initialize;
	var serviceItems = <c:out
	value="${serviceItems}" escapeXml="false"  />
</script>
</html>
