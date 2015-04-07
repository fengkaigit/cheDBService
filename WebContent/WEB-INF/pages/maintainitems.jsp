<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/maintainsitem.js" type="text/javascript"></script>
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>

<body>
	<div id="maindiv">
		<div class="navBar">&lt;保养</div>
		<div style="padding-left: 5px; padding-right: 5px;">
			<table class="layoutTbl" width="100%">
				<tr>
					<td>
						<h3 class="h32">
							<c:out value="${modelService.name}" />
						</h3>
					</td>
					<td style="width: 90px;"><c:if
							test="${modelService.preferential==1}">
							<div class="tejia"></div>
						</c:if></td>
				</tr>
			</table>

			<div class="m10">
				<table class="layoutTbl" width="100%">
					<tr>
						<td  style="width:120px"><span class="redText tart"
							style="font-size: 15px;"> ￥<c:out
									value="${modelService.priceOnline}" />元
						</span> <c:if test="${modelService.priceOld!=0}">
								<del>￥<c:out value="${modelService.priceOld}" />元</del>
							</c:if></td>
						<td class="tart" style="text-align: right;">已售<c:out
								value="${modelService.businessNum}" />
						</td>
					</tr>
					<tr>
						<td style="width: 120px;"><div class="greyText"
								style="font-size: 15px; width: 90px;">
								反<c:out value="${modelService.returnJifenOnline}" />积分
							</div></td>
						<td style="text-align: right;">
							<div class="greyText" style="font-size: 15px;">
								积分抵付需<c:out value="${modelService.priceJifen}" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${serviceClassId!=01}">
				<c:if test="${modelService.rawUrlPath!=null}">
					<a href="<c:out value="${modelService.rawUrlPath}" />"
						style="text-decoration: none;">
				</c:if>
				<c:if test="${modelService.rawUrlPath==null}">
					<a href="#" style="text-decoration: none;">
				</c:if>
				<c:if test="${modelService.hasRaw==1}">
					<div class="card1">
						<div class="cardTitle tact" style="color: #000;">高品质原料</div>
						<div class="m10" style="color: #000;">
							<c:if test="${modelService.item1ClassTxt!=null}">
					        【<c:out value="${modelService.item1ClassTxt}" />】 <c:out
									value="${modelService.item1Txt}" />
								<br>
							</c:if>
							<c:if test="${modelService.item2ClassTxt!=null}">
					        【<c:out value="${modelService.item2ClassTxt}" />】 <c:out
									value="${modelService.item2Txt}" />
								<br>
							</c:if>
							<c:if test="${modelService.item3ClassTxt!=null}">
					        【<c:out value="${modelService.item3ClassTxt}" />】 <c:out
									value="${modelService.item3Txt}" />
								<br>
							</c:if>
							<c:if test="${modelService.item4ClassTxt!=null}">
					        【<c:out value="${modelService.item4ClassTxt}" />】 <c:out
									value="${modelService.item4Txt}" />
								<br>
							</c:if>
						</div>
						<div class="itemlist greyText" style="height: 20px;">集中采购统一配送，品质高价格低</div>
					</div>
				</c:if>
				</a>
			</c:if>
			<c:if test="${modelService.workUrlPath!=null}">
				<a href="<c:out value="${modelService.workUrlPath}" />"
					style="text-decoration: none;">
			</c:if>
			<c:if test="${modelService.workUrlPath==null}">
				<a href="#" style="text-decoration: none;">
			</c:if>

			<div class="card1">
				<div class="cardTitle tact" style="color: #000;">严谨的工序</div>
				<div class="m10" style="color: #000;">
					<c:out value="${modelService.workTitle}" />
				</div>
				<c:if test="${modelService.workUrlPath==null}">
				 <div class="greyText">商家操作工序有误可投诉</div>
				</c:if>
				<c:if test="${modelService.workUrlPath!=null}">
				<div class="itemlist greyText">商家操作工序有误可投诉</div>
				</c:if>
				
			</div>
			</a>
			<div class="card1">
				<div class="cardTitle tact">认证商家</div>
				<c:forEach items="${providers}" var="modelProvider"
					varStatus="status">
					<div class="cardLayer">
						<div class=" m10">
							<table class="layoutTbl" width="100%">
								<tr>
									<td width="100" class="tact"><c:if
											test="${modelProvider.imgIdListStr==''}">
											<img src="img/ic_launcher.png" width="100" height="100" />
										</c:if> <c:if test="${modelProvider.imgIdListStr!=''}">
											<a
												href="provider/providerimglist.do?providerId=<c:out
										value="${modelProvider.providerId}" />">
												<img
												src="img/provider/<c:out
										value="${modelProvider.providerId}" />/<c:out value="${modelProvider.defaultimg}" />.jpg"
												width="120" height="120" />
											</a>
										</c:if></td>
									<td valign="top">
										<div style="width: 100%;">
											<a
												href="provider/providerdetail.do?providerId=<c:out
										value="${modelProvider.providerId}" />"
												style="text-decoration: none; color: #000;"> <c:out
													value="${modelProvider.title}" /> <br /> <span
												class="greyText"> <c:out
														value="${modelProvider.addr}" /> <br />
											</span>
											</a>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tact"><input type="checkbox" name="checkedbox"
										onchange="javascript:selectprovider(this,'<c:out value="${modelProvider.providerId}" />','<c:out value="${modelService.priceOnline}" />','<c:out value="${modelService.priceJifen}" />','<c:out value="${modelService.returnJifenOnline}" />');"
										style="zoom: 180%;" /></td>
									<td style="text-align: left;"><span class="redText">

											<c:out value="${modelProvider.score}" />分/<c:out
												value="${modelProvider.scoreCount}" />人打分
									</span></td>
								</tr>
							</table>
						</div>
					</div>
				</c:forEach>
				<div class="cardLayer tart ">
					<table width="100%" class="layoutTbl">
						<tbody>
							<tr>
								<td></td>
								<td width="140" style="text-align: right;">&nbsp;<span
									class="greyText"><c:out value="${providercount}" />/共<c:out
											value="${totalprovidercount}" />家</span></td>
								<td width="80" style="text-align: right;">
									<button class="greenBtn"
										onclick="javascript:pagequeryprovider();">下一页</button> <input
									type="hidden" name="start" id="start"
									value="<c:out value="${start}" />"> <input
									type="hidden" name="count" id="count"
									value="<c:out value="${count}" />">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" name="serviceId" id="serviceId"
				value="<c:out value="${serviceId}" />"> <input type="hidden"
				name="serviceClassId" id="serviceClassId"
				value="<c:out value="${serviceClassId}" />"> <input
				type="hidden" name="userId" id="userId"
				value="<c:out value="${userId}" />">
				 <input
				type="hidden" name="usertype" id="usertype"
				value="<c:out value="${usertype}" />">
				 <input type="hidden"
				name="price" id="price"> <input type="hidden"
				name="priceJifen" id="priceJifen"> <input type="hidden"
				name="returnJifen" id="returnJifen"> <input type="hidden"
				name="providerId" id="providerId">
			<div class="m10">
				<table class="layoutTbl" width="100%">
					<c:if test="${modelService.allowOnline==1}">
						<tr>
							<td width="100%" class="tart">

								<div class=" bigBtn" style="width: 100%; padding: 0px;"
									onclick="javascript:showmess(1);">
									<h3>在线支付</h3>
									<span style="font-size: small">服务前可随时取消</span>
								</div>

							</td>
						</tr>
					</c:if>
					<c:if test="${modelService.allowOnline==0}">
						<tr>
							<td width="100%" class="tart">

								<div class=" graybigBtn" style="width: 100%; padding: 0px;">
									<h3>在线支付</h3>
									<span style="font-size: small">服务前可随时取消</span>
								</div>

							</td>
						</tr>
					</c:if>
					<c:if test="${modelService.allowXianjin==1}">
						<tr>
							<td width="100%"><div class=" bigBtn"
									style="width: 100%; padding: 0px;"
									onclick="javascript:showmess(2);">
									<h3>现金支付</h3>
									<span style="font-size: small">直接将现金给商家</span>
								</div></td>
						</tr>
					</c:if>
					<c:if test="${modelService.allowXianjin==0}">
						<tr>
							<td width="100%"><div class=" graybigBtn"
									style="width: 100%; padding: 0px;">
									<h3>现金支付</h3>
									<span style="font-size: small">直接将现金给商家</span>
								</div></td>
						</tr>
					</c:if>
					<c:if test="${modelService.allowJifen==1}">
						<tr>
							<td width="100%" class="tart">
								<div class=" bigBtn" style="width: 100%; padding: 0px;"
									onclick="javascript:showmess(3);">
									<h3>积分抵付</h3>
									<span style="font-size: small">用积分就不花钱</span>
								</div>
							</td>
						</tr>
					</c:if>
					<c:if test="${modelService.allowJifen==0}">
						<tr>
							<td width="100%" class="tart">
								<div class=" graybigBtn" style="width: 100%; padding: 0px;">
									<h3>积分抵付</h3>
									<span style="font-size: small">服务前可随时取消</span>
								</div>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
			<input type="hidden" name="ordertype" id="ordertype">
			<div class="card1">
				<div class="cardTitle tact">车主评价</div>
				<div id="remarks">
					<c:forEach items="${apprsises}" var="modelUserAppraise"
						varStatus="status">
						<div class="cardLayer">
							<div class="m10">
								<div class=" greenText" style="font-size: 15px;">
									<c:out value="${modelUserAppraise.userName}" />
								</div>
								<div style="font-size: 14px;">
									<c:out value="${modelUserAppraise.appraise}" />
								</div>
								<div class=" tart greyText" style="text-align: right;">
									<c:out value="${modelUserAppraise.dateStr}" />
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="cardLayer">
					<table class="layoutTbl" width="100%">
						<tr>
							<td><button class="greenBtn"
									onclick="javascript:showcommentdiv();">说两句</button></td>
							<td style="text-align: right; width: 120px;"><input
								type="hidden" name="appriseindex" id="appriseindex"
								value="<c:out value="${appriseindex}" />"> <input
								type="hidden" name="pagecount" id="pagecount"
								value="<c:out value="${pagecount}" />"> <input
								type="hidden" name="rowcoount" id="rowcoount"
								value="<c:out value="${rowcoount}" />"> <span
								class="greyText" id="apppagebar"> <c:out
										value="${appriseindex}" /> /共<c:out value="${rowcoount}" />条
							</span></td>
							<td width="80" style="text-align: right;"><c:if
									test="${endpage==1}">
									<button class="greenBtn">已全部</button>
								</c:if> <c:if test="${endpage==0}">
									<button class="greenBtn"
										onclick="javascript:pagingqueryapprise(<c:out value="${nextpage}" />);">下一页</button>
								</c:if></td>

						</tr>
					</table>
				</div>
			</div>
			<div style="width: 100%; padding-left: 10px; display: none;"
				id="commentdiv">
				<div style="float: left;">
					<textarea rows="2" value="" id="comment"></textarea>
				</div>
				<div style="float: left; padding-left: 10px; padding-top: 10px;">
					<button class="greenBtn" style="width: 70px;"
						onclick="javascript:sendcomment();">发送</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="messagepage.jsp"%>
	<%@ include file="login.jsp"%>
	<script>
		var modelService = <c:out value="${modelServiceInfo}" escapeXml="false" />;
	</script>
</body>
</html>
