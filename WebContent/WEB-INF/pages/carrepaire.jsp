<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/carrepaire.js" type="text/javascript"></script>
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>
<body>
	<div id="carmaintain">
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="tart"><a href="index.jsp"
						style="color: white; text-decoration: none;">&lt;检测</a></td>
					<td width="60"></td>
					<td width="20"></td>
					<td width="20"></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3 class="h32">汽车中立检测</h3>
		<div class="card1">
			<div class="cardTitle tact">选择车型</div>
			<div class=" m10">
				<c:forEach items="${returncars}" var="modelcar" varStatus="status">
					<div class="cardLayer" id="selectedcar1">
						<%-- 						<c:if test="${modelcar.selected==1}"> --%>
						<!-- 
							<input type="checkbox" checked="checked" id="checkbox1"
								onclick="javascript:changeselection(this,'<c:out value="${modelcar.seriseId}" />',1);" />
							 -->
						<input type="hidden" name="selcarid" id="selcarid"
							value="<c:out value="${modelcar.seriseId}" />">
						<%-- 						</c:if> --%>
						<!-- 
						<c:if test="${modelcar.selected==0}">
							<input type="checkbox" id="checkbox2"
								onclick="javascript:changeselection(this,'<c:out value="${modelcar.seriseId}" />',2);" />
							<input type="hidden" name="lastselcarid" id="lastselcarid"
								value="<c:out value="${modelcar.seriseId}" />">
						</c:if>
						 -->
						<c:out value="${modelcar.brandname}" />
						<c:out value="${modelcar.serisename}" />
						<br /> <input type="hidden" name="checkedindex" id="checkedindex"
							value="1">
					</div>
				</c:forEach>

				<div class="cardLayer" style="padding-top: 10px;" id="selectedcar2">
					<table width="100%" class="layoutTbl">
						<tr>
							<td>&nbsp;<span class="greyText">选择车型，查看可做的项目</span></td>
							<td width="80" style="text-align: right;"><button
									class="greenBtn" onclick="javascript:switchdiv(1);">选择车型</button></td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div class="card1">
			<div class="cardTitle tact">可选项目</div>
			<c:if test="${empty servicelist}">
				<div class="cardLayer">
					<div class="m10">
						<span class="greyText">该车型没有可选项目</span>
					</div>
				</div>
			</c:if>
			<c:forEach items="${servicelist}" var="modelService"
				varStatus="status">
				<div class="cardLayer" id="servicelist">
					<div class="m10">
						<a
							href="serviceItemDetail.do?serviceId=<c:out
									value="${modelService.serviceId}" />&serviceClassId=02"
							style="color: #000; text-decoration: none;"> <c:out
								value="${modelService.name}" />
							<table style="width: 100%;">
								<c:if test="${modelService.item1ClassTxt!=null}">
									<tr>
										<td><span class="greyText" style="padding: 0px;"><c:out
													value="${modelService.item1ClassTxt}" /></span></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${modelService.item2ClassTxt!=null}">
									<tr>
										<td><span class="greyText" style="padding: 0px;"><c:out
													value="${modelService.item2ClassTxt}" /></span></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${modelService.item3ClassTxt!=null}">
									<tr>
										<td><span class="greyText" style="padding: 0px;"><c:out
													value="${modelService.item3ClassTxt}" /></span></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${modelService.item4ClassTxt!=null}">
									<tr>
										<td><span class="greyText" style="padding: 0px;"><c:out
													value="${modelService.item4ClassTxt}" /></span></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<tr>
									<td class="redText">￥<c:out
											value="${modelService.priceOnline}" />元
									</td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</a>
					</div>
				</div>

			</c:forEach>
		</div>
		<div class="listBox">
			<p>以中立机构的身份来为车辆做检查，并提供一份客观的检测报告，不在忽悠车主过度消费，真正让你了解什么需要换，什么不需要换</p>
			<p>目前我们正在研制详细检测项目，不久开通服务。</p>
		</div>

		<div class="m10">
			<div class="greyText">在车大邦平台上网，如出现任何问题，车大邦负责协调解决。</div>
		</div>
	</div>
	<!-- 以下为车型列表 -->
	<div id="carbrands" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>
		<div id="brandlsit">
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
		</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车型列表 -->
	<!-- 以下为车系列表  -->
	<div id="carseries" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择车系</a>
		</div>
		<div id="serieslist">
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
			<div class="brandItem">本田</div>
			<div class="brandItem">奥迪</div>
		</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车系列表 -->
	<!-- 以下为车系列表  -->
	<div id="cars" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择车型</a>
		</div>
		<div id="carlist">
			<div class="brandItem">100</div>
			<div class="brandItem">200</div>
			<div class="brandItem">A4</div>
			<div class="brandItem">A6</div>
			<div class="brandItem">A6L</div>
		</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车系列表 -->
	<!-- 以下为车型列表  -->
	<div id="carversions" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择出厂日期</a>
		</div>
		<div id="caryearlist">
			<div class="brandItem">奥迪100 1.8L AT</div>
			<div class="brandItem">奥迪100 2.0L DCT</div>
		</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
		<input type="hidden" id="classId" value="<c:out value="${classId}" />">
		<input type="hidden" id="view" value="<c:out value="${view}" />">
		<c:forEach items="${caryears}" var="modelcar" varStatus="status">
			<c:if test="${modelcar.selected==1}">
				<input type="hidden" id="carId"
					value="<c:out value="${modelcar.carId}" />">
				<input type="hidden" id="yearid"
					value="<c:out value="${modelcar.id}" />">
			</c:if>
		</c:forEach>
	</div>
	<!-- 以上为车型列表 -->
</body>
</html>