var brandexists = false;
var selectedbrand = null;
var selectedbrandname = null;
var selectedseriesid = null;
var selectedseriesname = null;
var selectedcarid = null;
var selectedcarname = null;
var selectedyearid = null;
var selectedyearname = null;

var switchdiv = function(type) {
	window.location.href = 'brandlist.do?classId=' + $('#classId').val()
	+ '&lastselcarid=' + $('#selcarid').val();
	/*
	if (type == 1) {
		$("#carmaintain").hide();
		if (!brandexists) {
			getcarbrands();
		}
		$("#carbrands").show();
	} else {
		$("#carmaintain").show();
		$("#carbrands").hide();
	}*/

};

var getcarbrands = function() {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryCarBrandList.do",
				error : function() {
					alert('连接服务器出错!');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"brandItem\" onclick=\"javascript:getcarseries(\''
								+ data[i].id + '\',\'' + data[i].name
								+ '\');\"><a href=\"javascript:getcarseries(\''
								+ data[i].id + '\',\'' + data[i].name
								+ '\');\"> ' + data[i].name + '</a></div>'
					}
					$('#brandlsit').html(html);
					brandexists = true;
				}
			});
};

var getcarseries = function(breadid, breanname) {
	selectedbrand = breadid;
	selectedbrandname = breanname;
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryCarSeriseListByBrand.do",
				data : {
					breadId : breadid
				},
				error : function() {
					alert('连接服务器出错!');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"brandItem\" onclick=\"javascript:queryBySelectedCar(\''
								+ data[i].id
								+ '\',\''
								+ data[i].name
								+ '\');\"><a href=\"#\">' + data[i].name + '</a></div>'
					}
					$('#serieslist').html(html);
					$("#carbrands").hide();
					$("#carseries").show();
				}
			});
};

var selectcar = function(yearid, carId) {
	$('#carversions').hide();
	$('#carmaintain').show();
	$('#selectedcar1').html(
			'<input type="checkbox" checked="checked" id=\"checkbox1\">' + name
					+ '');
	$('#yearid').val(yearid);
	$('#carId').val(carId);
	query();
};
/**
 * 
 */
var query = function(carid) {
	window.location.href = 'serviceindex.do?classId=' + $('#classId').val()
			+ '&view=' + $('#view').val() + '&yearid=&carId='
			+ $('#selcarid').val() + '&lastselcarid='
			+ $('#lastselcarid').val();
};
var queryBySelectedCar = function(carid, carname) {
	$('#lastselcarid').val($('#selcarid').val());
	$('#selcarid').val(carid);
	query();
};
var changeselection = function(checkboxvalue, seriseId, index) {
	var tempvalue = '';
	var checkedindex = $('#checkedindex').val();
	if (checkedindex == index) {
		return;
	}
	if (checkboxvalue.checked) {
		tempvalue = $('#selcarid').val();
		$('#selcarid').val(seriseId);
		$('#lastselcarid').val(tempvalue);
		$('#checkedindex').val(index);
	} else {
		tempvalue = $('#lastselcarid').val();
		$('#lastselcarid').val(seriseId);
		$('#selcarid').val(seriseId);
	}
	query();
};
