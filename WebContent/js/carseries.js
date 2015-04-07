/**
 * 查找查
 */
var querycars = function(seriseid, classId, lastselcarid) {
	window.location.href = 'carlist.do?serieid=' + seriseid + '&classId='
			+ classId + '&lastselcarid=' + lastselcarid;
}
/**
 * 查询服务
 */
var queryservice = function(seriesid) {
	var classid = $('#classId').val();
	var view = '';
	if (classid == '02') {
		view = 'carmaintains';
	} else if (classid == '03') {
		view = 'carrepaire';
	} else {
		view = 'cartyre';
	}
	window.location.href = 'serviceindex.do?classId=' + $('#classId').val()
			+ '&view=' + view + '&yearid=' + seriesid + '&lastselcarid='
			+ $('#lastselcarid').val() + '&carId=' + seriesid;
}