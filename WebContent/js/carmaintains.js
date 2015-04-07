/**
 * 
 */
var brandexists = false;
var selectedbrand = null;
var selectedbrandname = null;
var selectedseriesid = null;
var selectedseriesname = null;
var selectedcarid = null;
var selectedcarname = null;
var selectedyearid = null;
var selectedyearname = null;
$(document).ready(function() {
	$("#jqxScrollBar").jqxScrollBar({
		width : $(window).width() - 61,
		height : 18,
		min : 0,
		max : 150000,
		thumbMinSize : 30
	});
	$("#jqxScrollBar").on('valueChanged', function(event) {
		var value=event.currentValue;
		if (isNaN(value)) {
			value=$('#moverange').val();
			if(isNaN(value)){
				value=0 ;
				//event.currentValue=0;
				$('#jqxScrollBar').jqxScrollBar({ value:0}); 
				//alert('如果无法滑动请点击旁边的键盘切换到输入模式下进行输入。');
			}
		}
		$('#mileage').text(parseInt(value) + '公里');
		$('#moverange').val(parseInt(value));

	});
});
/**
 * 
 */
var switchdiv = function(type) {
	// brandlist.do
	window.location.href = 'brandlist.do?classId=' + $('#classId').val()
			+ '&lastselcarid=' + $('#carId').val();
	/*
	 * if (type == 1) { $("#carmaintain").hide(); if (!brandexists) {
	 * getcarbrands(); } $("#carbrands").show(); } else {
	 * $("#carmaintain").show(); $("#carbrands").hide(); }
	 */
};
/**
 * 
 */
var getcarbrands = function() {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryCarBrandList.do",
				error : function() {
					alert('����ʧ��');
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
/**
 * 
 */
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
					alert('连接出错！');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"brandItem\" onclick=\"javascript:getcars(\''
								+ data[i].id + '\',\'' + data[i].name
								+ '\');\"><a href=\"javascript:getcars(\''
								+ data[i].id + '\',\'' + data[i].name
								+ '\');\">' + data[i].name + '</a></div>'
					}
					$('#serieslist').html(html);
					$("#carbrands").hide();
					$("#carseries").show();
				}
			});
};
/**
 * 
 */
var getcars = function(seriesid, seriesname) {
	selectedseriesid = seriesid;
	selectedseriesname = seriesname;
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryCarListBySerise.do",
				data : {
					seriseId : seriesid
				},
				error : function() {
					alert('连接出错！');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"brandItem\" onclick=\"javascript:getcaryear(\''
								+ data[i].id + '\',\'' + data[i].shortName
								+ '\');\"><a href=\"javascript:getcaryear(\''
								+ data[i].id + '\',\'' + data[i].shortName
								+ '\');\">' + data[i].name + '</a></div>'
					}
					$('#carlist').html(html);
					$("#carseries").hide();
					$('#cars').show();
				}
			});
};
/**
 * 
 */
var getcaryear = function(carid, carname) {
	selectedyearid = carid;
	selectedyearname = carname;
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryCarYearListBySerise.do",
				data : {
					carId : carid
				},
				error : function() {
					alert('连接出错！');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"brandItem\" onclick=\"javascript:selectcar(\''
								+ data[i].id + '\',\'' + data[i].carId
								+ '\');\"><a href=\"#\">' + data[i].name
								+ '</a></div>'
					}
					$('#caryearlist').html(html);
					$("#cars").hide();
					$('#carversions').show();
				}
			});

};
/**
 * 
 */
var selectcar = function(yearid, carId) {
	$('#carversions').hide();
	$('#carmaintain').show();
	$('#selectedcar1').html(
			'<input type="checkbox" checked="checked" id=\"checkbox1\">' + name
					+ '');
	$('#yearid').val(yearid);
	$('#lastselcarid').val($('#carId').val());
	$('#carId').val(carId);
	query();
}
var query = function() {
	window.location.href = 'serviceindex.do?classId=' + $('#classId').val()
			+ '&view=' + $('#view').val() + '&carId=' + $('#carId').val()
			+ '&yearid=' + $('#yearid').val() + '&lastselcarid='
			+ $('#lastselcarid').val();
}
var changeselection = function(checkboxvalue, yearid, carid, index) {
	var tempvalue = '';
	var checkedindex = $('#checkedindex').val();
	if (checkedindex == index) {
		return;
	}
	if (checkboxvalue.checked) {
		tempvalue = $('#carId').val();
		$('#carId').val(carid);
		$('#lastselcarid').val(tempvalue);
		$('#checkedindex').val(index);
	} else {
		tempvalue = $('#lastselcarid').val();
		$('#lastselcarid').val(carid);
		$('#carId').val(carid);
	}

	$('#yearid').val(yearid);
	query();
}
/**
 * 
 */
var changeinputtype = function(type) {
	if (type == 1) {
		$('#recommendcomponent2').show();
		$('#recommendcomponent1').hide();
	} else {
		$('#recommendcomponent1').show();
		$('#recommendcomponent2').hide();
	}
};
/**
 * 里程输入框输入时候
 */
onMoverangeChange = function(obj) {
	if ("" != obj.value) {
		var str = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		if (obj.value != str)
			obj.value = str;
	}
	if (obj.value.indexOf('.') != -1) {
		obj.value = obj.value.replace(/[\.]/, '');
		obj.focus();
	}
	if (isNaN(Number(obj.value))) {
		obj.value = ($.trim(obj.value)).replace(/[\D]/, '');
		obj.focus();
	}
	if (obj.value == '') {
	} else {
		$('#mileage').text(parseInt(obj.value) + '公里');
		$('#jqxScrollBar').jqxScrollBar({ value:parseInt(obj.value)}); 
	}

};

/**
 * 
 */
var recommendservice = function() {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "recommendService.do",
				data : {
					classId : $('#classId').val(),
					yearid : $('#yearid').val(),
					carId : $('#carId').val(),
					moverange : $('#moverange').val()
				},
				error : function() {
					alert('连接出错！');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class="m10"><a href="serviceItemDetail.do?serviceId='
								+ data[i].serviceId
								+ '&serviceClassId=02" style="color: #000; text-decoration: none;">'
								+ '<div class="brandItem">' + data[i].name
								+ '</div>' + '<table style="width: 100%;">'
								+ '<tbody>';
						if (data[i].item1ClassTxt != null) {
							html = html
									+ '<tr><td><span class="greyText" style="padding: 0px;">'
									+ data[i].item1ClassTxt + '</span></td>'
									+ '<td></td><td></td></tr>';
						}
						if (data[i].item2ClassTxt != null) {
							html = html
									+ '<tr><td><span class="greyText" style="padding: 0px;">'
									+ data[i].item2ClassTxt + '</span></td>'
									+ '<td></td><td></td></tr>';
						}
						if (data[i].item3ClassTxt != null) {
							html = html
									+ '<tr><td><span class="greyText" style="padding: 0px;">'
									+ data[i].item3ClassTxt + '</span></td>'
									+ '<td></td><td></td></tr>';
						}
						if (data[i].item4ClassTxt != null) {
							html = html
									+ '<tr><td><span class="greyText" style="padding: 0px;">'
									+ data[i].item4ClassTxt + '</span></td>'
									+ '<td></td><td></td></tr>';
						}
						html = html
								+ '<tr><td class="redText">￥'
								+ data[i].priceOnline
								+ '元</td><td></td><td></td></tr></tbody></table></a></div>'
					}
					$('#recommend').html(html);
					if (data != null && data.length > 0) {
						$('#recommendmess').text(data[0].message);
					}

				}
			});

}
