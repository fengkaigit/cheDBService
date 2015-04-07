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
<script src="js/carlist.js" type="text/javascript"></script>
</head>
<body>
	<div class="navBar">&lt;选择车型</div>
	<div id="brandlsit" style="padding-left: 5px; padding-right: 5px;">
		<c:forEach items="${cars}" var="modelCar" varStatus="status">
			<div class="brandItem"
				onclick="javascript:querycaryeas('<c:out value="${modelCar.id}" />','<c:out value="${classId}" />','<c:out value="${lastselcarid}" />');">
				<a href="caryears.do?carid=<c:out value="${modelCar.id}" />&classId=<c:out value="${classId}" />&lastselcarid=<c:out value="${lastselcarid}" />">
					<c:out value="${modelCar.name}" />
				</a>
			</div>
		</c:forEach>
		<div class="brandItem" style="background: none"></div>
	</div>
	<div class="m10">没有您要的车系？请在公众号留言我们尽快加上</div>
	<input type="hidden" id="classId" value="<c:out value="${classId}" />">
	<input type="hidden" name="lastselcarid" id="lastselcarid"
		value="<c:out value="${lastselcarid}" />">
</body>
</html>
