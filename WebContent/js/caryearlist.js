var queryservice = function(yearid) {
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
			+ '&view=' + view + '&yearid=' + yearid + '&lastselcarid='
			+ $('#lastselcarid').val()+'&carId='+$('#carid').val();
}