var map = null;
var switchdiv = function(providerid) {
	window.location.href = "../provider/locateprovider.do?providerid="
			+ providerid;
}

function initialize() {
	map = new BMap.Map('map');
	map.centerAndZoom(new BMap.Point(111.662076, 40.864993), 14);
	map.addControl(new BMap.MapTypeControl());
	map.setCurrentCity("呼和浩特");
	map.enableScrollWheelZoom(true);
	var top_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});
	var top_left_navigation = new BMap.NavigationControl();
	var top_right_navigation = new BMap.NavigationControl({
		anchor : BMAP_ANCHOR_TOP_LEFT,
		type : BMAP_NAVIGATION_CONTROL_SMALL
	});
	map.addControl(top_left_control);
	map.addControl(top_left_navigation);
	map.addControl(top_right_navigation);
	// locateaddr();

};

var locateaddr = function(longitude, latitude, providerId, title, addr) {
	map = new BMap.Map('map');
	map.centerAndZoom(new BMap.Point(111.662076, 40.864993), 14);
	map.addControl(new BMap.MapTypeControl());
	map.setCurrentCity("呼和浩特");
	map.clearOverlays();
	map.centerAndZoom(new BMap.Point(111.662076, latitude), 15);
	var new_point = new BMap.Point(longitude, latitude);
	var marker = new BMap.Marker(new_point)
	map.addOverlay(marker);
	var htmlmess = '<a href="providerdetail.do?providerId='
			+ providerId
			+ '" style="text-decoration: none;color: #000;"><div><div style="font-size:18px;">'
			+ title + '</div><br><div style="font-size:15px;">' + addr
			+ '</div></div></a>';
	addClickHandler(htmlmess, marker);
	map.addOverlay(marker);
	map.panTo(new_point);
}
var opts = {
	width : 250,
	height : 80,
	// title : "信息窗口",
	enableMessage : true
};

var addClickHandler = function(content, marker) {
	marker.addEventListener("click", function(e) {
		openInfo(content, e)
	});
}
var openInfo = function(content, e) {
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
	map.openInfoWindow(infoWindow, point); // 开启信息窗口
}
/**
 * 
 */
var queryProviderItemById = function(id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "../queryProviderItemById.do",
		data : {
			itemId : id
		},
		error : function() {
			alert('连接出错');
		},
		success : function(data) {
			if (data != null) {
				if (data.sysItemName != null) {
					$('#itemname').text(data.sysItemName);
				}
				if (data.summary != null) {
					$('#summary').text(data.summary);
				}
				if (data.remark != null) {
					$('#remark').text(data.remark);
				}
				if (data.price != null) {
					$('#pricelable').text(data.price);
				}
				if (data.priceOld != null) {
					$('#priceOld').text(data.priceOld);
				}
				switchmaindiv();
			}
		}
	});

}
/**
 * 
 */
var switchmaindiv = function() {
	var hidden = $("#servicesubject").is(":hidden")
	if (!hidden) {
		$("#serviceinfo").show();
		$('#servicesubject').hide();
	} else {
		$("#serviceinfo").hide();
		$('#servicesubject').show();
	}
};

var drivingRoute = function(longitude, latitude, providerId, title, addr,
		frlongitude, frlatitude) {
	// var map = new BMap.Map("allmap");
	map = new BMap.Map('map');
	map.centerAndZoom(new BMap.Point(111.662076, 40.864993), 14);
	var p1 = new BMap.Point(frlongitude, frlatitude);
	var p2 = new BMap.Point(longitude, latitude);
	var driving = new BMap.DrivingRoute(map, {
		renderOptions : {
			map : map,
			autoViewport : true
		}
	});
	driving.search(p1, p2);

}
