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
<link href="css/jqx.base.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jqxcore.js"></script>
<script type="text/javascript" src="js/jqxscrollbar.js"></script>
<script type="text/javascript" src="js/jqxbuttons.js"></script>
<script src="js/carmaintains.js" type="text/javascript"></script>
</head>

<body>
	<div id="carmaintain">
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="tart"><a href="index.jsp"
						style="color: white; text-decoration: none;">&lt;保养</a></td>
					<td width="60"></td>
					<td width="20"></td>
					<td width="20"></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3 class="h32">汽车常规保养</h3>
		<div style="padding-left: 5px; padding-right: 5px;">
			<div class="card1">
				<div class="cardTitle tact">选择车型</div>
				<!-- 				<div class=" m10"> -->
				<div style="margin-left: 10px;">
					<c:forEach items="${caryears}" var="modelcar" varStatus="status">
						<div class="cardLayer" id="selectedcar1"
							style="height: 30px; padding-top: 8px; border-top: solid 1px #ccc;">
<%-- 							<c:if test="${modelcar.selected==1}"> --%>
								<input type="hidden" name="selcarid" id="selcarid"
									value="<c:out value="${modelcar.id}" />">
									<!-- 
								<input type="checkbox" checked="checked" style="zoom: 150%;"
									onclick="javascript:changeselection(this,'<c:out value="${modelcar.id}" />','<c:out value="${modelcar.carId}" />',1);" />
                                 -->
<%-- 							</c:if> --%>
							<!-- 
							<c:if test="${modelcar.selected==0}">
								<input type="hidden" name="lastselcarid" id="lastselcarid"
									value="<c:out value="${modelcar.id}" />">
								<input type="checkbox"
									onclick="javascript:changeselection(this,'<c:out value="${modelcar.id}" />','<c:out value="${modelcar.carId}" />',2);"
									style="zoom: 150%;" />
							</c:if>
							 -->
							<c:out value="${modelcar.name}" />
							<br />
						</div>
						<input type="hidden" name="checkedindex" id="checkedindex"
							value="1">
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
			<!--  -->
			<div class="card1">
				<div class="cardTitle tact">推荐项目</div>
				<div id="mileagediv1">
					<table width="100%" class="layoutTbl">
						<tr>
							<td><div id="mileage">当前里程</div></td>
							<td width="100" style="text-align: right;"><button
									class="greenBtn" onclick="javascript:recommendservice();">推荐项目</button></td>
						</tr>
					</table>
					<div class="cardLayer" id="recommendcomponent1"
						style="width: 100%; height: 50px;">
						<table>
							<tr>
								<td style="width: 40px;"><a
									href="javascript:changeinputtype(1);"><img
										src="img/keyboard.png" style="width: 40px; height: 40px;" />
								</a></td>
								<td>
									<div id='jqxWidget'
										style="font-size: 13px; font-family: Verdana; float: left; width: 100%;">
										<div style='margin-top: 10px;' id='jqxScrollBar'></div>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="cardLayer" id="recommendcomponent2"
						style="width: 100%; height: 50px; display: none;">
						<table>
							<tr>
								<td style="width: 40px;"><a
									href="javascript:changeinputtype(2);"><img
										src="img/move.png" style="width: 40px; height: 40px;" /> </a></td>
								<td><input type="text" name="moverange" id="moverange"
									style="width: 100%;"
									oninput="javascript:onMoverangeChange(this);"></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="cardLayer" id="recommend"></div>
				<div
					style="text-align: left; font-size: 15px; padding-bottom: 5px; padding-left: 5px;"
					id="recommendmess"></div>
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
								style="color: #000; text-decoration: none;">
								<div class="brandItem">
									<c:out value="${modelService.name}" />
								</div>
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
										<td><span class="redText">￥<c:out
												value="${modelService.priceOnline}" />元</span>
												<del>￥<c:out value="${modelService.priceOld}" />元</del>
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
				<p>以独有的模式提供比目前更高品质的汽车保养服务</p>
				<p>1、集中采购统一配送油品和配件，省去中间环节，做到了品质高价格低;</p>
				<p>2、严谨的工序和监督反馈机制保证商家的每步工作都执行到位;</p>
				<p>3、根据官方保养手册推荐当前需要做的保养项目，车主省心又放心;</p>
			</div>

			<div class="m10">
				<div class="greyText">在车大邦平台上网，如出现任何问题，车大邦负责协调解决。</div>
			</div>
		</div>
	</div>
	<!-- 以下为车型列表 -->
	<div id="carbrands" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>
		<div id="brandlsit" style="padding-left: 5px; padding-right: 5px;">
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
		<div id="serieslist" style="padding-left: 5px; padding-right: 5px;">
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
		<div id="carlist" style="padding-left: 5px; padding-right: 5px;">
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
		<div id="caryearlist" style="padding-left: 5px; padding-right: 5px;">
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