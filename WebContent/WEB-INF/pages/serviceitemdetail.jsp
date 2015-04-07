<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/servicedetail.js" type="text/javascript"></script>
</head>
<body>
	<div class="navBar">
		<table class="layoutTbl" width="100%">
			<tbody>
				<tr>
					<td class=""><a href="javascript:switchmaindiv();"
						style="text-decoration: none; color: #fff;">&lt;项目信息 </a></td>
					<td width="20">
						<!-- 						<img src="../img/contextMenu.png" width="20" --> <!-- 							height="20" /> -->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<h3 class="h31" id="itemname">
		<c:out value="${providerItem.title}" />
	</h3>
	<c:if test="${providerItem.summary!=null}">
		<div class="m10" id="summary">
			<!-- 			项目介绍:<br /> -->
			<c:out value="${providerItem.summary}" />
			<!-- 			简要介绍服务项目 -->
			<br />
			<!-- 			大概所需要的时间竺 -->
			<!-- 			<br /> -->
		</div>
	</c:if>

	<div class="listBox">
		<span class="redText" id="pricelable"> <c:if
				test="${providerItem.price==null||providerItem.price==0}">
			暂未定价
			</c:if> <c:if test="${providerItem.price!=null&&providerItem.price!=0}">
				<fmt:formatNumber value="${providerItem.price}" pattern="#,#00.00#" />
				元
			</c:if>
		</span>
		<c:if test="${providerItem.priceOld!=null&&providerItem.priceOld!=0}">
			<span class="greyText" id="priceOld" style="padding-left: 0px;">(原价
				<fmt:formatNumber value="${providerItem.priceOld}"
					pattern="#,#00.00#" /> 元)
			</span>
		</c:if>
	</div>
	<div class="m10" id="remark">
		<!-- 		项目说明 如：<br /> 操作工序步骤如<br /> 使用的原材料及设备 <br /> 不解决有何影响等 <br /> -->
		<c:if test="${providerItem.remark!=null}">
			<c:out value="${providerItem.remark}" />
		</c:if>
	</div>
</body>
</html>