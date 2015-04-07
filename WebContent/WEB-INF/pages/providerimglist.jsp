<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/swipebox.css">
<script src="../js/jquery-2.1.0.min.js"></script>
<script src="../js/galleria-1.4.2.min.js"></script>
<title>图片列表</title>
</head>
<body style="background-color: #000;">
	<div id="galleria" style="width: 500px; height: 600px;">
		<c:if test="${imgsrcs!=null}">
			<c:forEach items="${imgsrcs}" var="imgpsth" varStatus="status">
				<img
					src="../img/provider/<c:out
										value="${modelProvider.providerId}" />/<c:out
										value="${imgpsth}" />.jpg"
					width="60" height="65" /> 
			</c:forEach>
		</c:if>

		<!-- 
		<a href="../img/a.JPG" class="swipebox" title="My Caption"> <img
			src="../img/b.JPG" alt="image">
		</a> <a href="../img/b.JPG" class="swipebox" title="My Caption"> <img
			src="../img/a.JPG" alt="image">
		</a>
		 -->
	</div>
</body>
<script>
	var availHeight = $(window).height();
	var availWidth = $(window).width();
	$('#galleria').css('height', availHeight);
	$('#galleria').css('width', availHeight);
	Galleria.loadTheme('../js/galleria.classic.js');
	Galleria.run('#galleria');
</script>
</html>