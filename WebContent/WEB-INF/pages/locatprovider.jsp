<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车大邦</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script
	src="http://api.map.baidu.com/api?v=1.5&ak=3fntf7DHiY8PIbllO4UU0inr"
	type="text/javascript"></script>
<script src="../js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="../js/servicedetail.js" type="text/javascript"></script>
</head>
<body>
	<div id="map" style="height: 600px; padding: 0 0.5em;"></div>
</body>
<script language="javascript" type="text/javascript">
drivingRoute(<c:out
			value="${modelProvider.longitude}" />,
			<c:out
			value="${modelProvider.latitude}" />,
			'<c:out
			value="${modelProvider.providerId}" />',
			'<c:out
			value="${modelProvider.title}" />',
			'<c:out
			value="${modelProvider.addr}" />',
			'111.728446','40.820943');
</script>
</html>