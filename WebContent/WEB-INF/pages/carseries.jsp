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
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/carseries.js" type="text/javascript"></script>
</head>
<body>
	<div class="navBar">&lt;选择车系</div>
	<div id="brandlsit" style="padding-left: 5px; padding-right: 5px;">
		<c:forEach items="${series}" var="modelCarSerise" varStatus="status">
			<c:if test="${classId=='03'||classId=='04'}">
				<div class="brandItem"
					onclick="javascript:queryservice('<c:out value="${modelCarSerise.id}" />');">
					<a
						href="javascript:queryservice('<c:out value="${modelCarSerise.id}" />');">
						<c:out value="${modelCarSerise.name}" />
					</a>
				</div>
			</c:if>
			<c:if test="${classId=='02'}">
				<div class="brandItem"
					onclick="javascript:querycars('<c:out value="${modelCarSerise.id}" />','<c:out value="${classId}" />','<c:out value="${lastselcarid}" />');">
					<a
						href="carlist.do?serieid=<c:out value="${modelCarSerise.id}" />&classId=<c:out value="${classId}" />&lastselcarid=<c:out value="${lastselcarid}" />">
						<c:out value="${modelCarSerise.name}" />
					</a>
				</div>
			</c:if>

		</c:forEach>
		<div class="brandItem" style="background: none"></div>
	</div>
	<div class="m10">没有您要的车系？请在公众号留言我们尽快加上</div>
	<input type="hidden" id="classId" value="<c:out value="${classId}" />">
	<input type="hidden" name="lastselcarid" id="lastselcarid"
		value="<c:out value="${lastselcarid}" />">
</body>
</html>
