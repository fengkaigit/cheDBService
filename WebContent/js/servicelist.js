var strSysItemList = null;
var level = null;
var sort = null;
var map = null;
/**
 * 
 */
var changesearchetype = function(vsort, icon) {
	$('#sorttypeicon').text(icon);
	if (vsort == sort) {
		return;
	} else {
		sort = vsort;
		$('#sort').val(sort);
		searche();
	}
};
var searche = function() {
	var searchetext = $('#searchetext').val();
	if (searchetext != null && searchetext != '') {
		getServiceByNumber(searchetext);

	} else {
		window.location.href = 'queryProviderListForSearch.do?strSysItemList='
				+ $('#strSysItemList').val() + '&level=' + $('#level').val()
				+ '&sort=' + $('#sort').val() + '&itemname='
				+ encodeURI($('#itemname').val());

	}

};

var getServiceByNumber = function(searchetext) {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "queryProviderListBySearch.do",
				data : {
					searchStr : searchetext
				},
				error : function() {
					alert('连接出错！');
				},
				success : function(data) {
					var html = '';
					for (i in data) {
						html = html
								+ '<div class=\"listBox\" style=\"margin-top: 10px;\">'
								+ '<a href=\"providerdetail.do?providerId='
								+ data[i].providerId + '\"><h3>'
								+ data[i].title
								+ '</h3><div class=\"greyText\">'
								+ data[i].addr
								+ '</div><div class=\"redText\">'
								+ data[i].business + '次成效/' + data[i].browse
								+ '次浏览</div></a></div>';
						for (j in data[i].providerItems) {
							html = html + '<div class=" listBox">'
									+ '<table class="layoutTbl" width="100%">'
									+ '<tbody><tr>'
									+ '<td><span class="textIcon">'
									+ data[i].providerItems[j].sysItemName
									+ '</span>'
									+ data[i].providerItems[j].title + '</td>';
							if (data[i].providerItems[j].price != null
									|| data[i].providerItems[j].price != '') {
								html = html
										+ '<td width="80" class="greenText" style="font-size: 14px; text-align: right;">￥'
										+ data[i].providerItems[j].price.toFixed(2)
										+ '</td>' + '</tr>';
							} else {
								html = html
										+ '<td width="80" class="greenText" style="font-size: 14px; text-align: right;">'
										+ '还未定价' + '</td>' + '</tr>';
							}

							html = html + '</tbody></table>' + '</div>';
						}
						html = html
								+ '<table class="layoutTbl" width="100%">'
								+ '<tbody><tr><td></td><td style="text-align: right;"><span class="textIcon">都有了</span></td><tr>'
								+ '<td></td>'
								+ '<td width="120" class="greenText" style="text-align: right;"><span class="textIconActive">已是全部</span></td>'
								+ '</tr>' + '</tbody></table>';

					}
					$('#serlist').html(html);
					serviceItems.items = data;
					locateaddr();
				}
			});
};
var switchsearchdiv = function() {
	var hidden = $("#itemnamediv").is(":hidden")
	if (!hidden) {
		$("#itemnamediv").hide();
		$('#searchdiv').show();
	} else {
		$("#itemnamediv").show();
		$('#searchdiv').hide();
	}
};
var switchmapdiv = function() {
	var hidden = $("#serviceslist").is(":hidden")
	if (!hidden) {
		$("#serviceslist").hide();
		$('#map').show();
	} else {
		$("#serviceslist").show();
		$('#map').hide();
	}

};
function initialize() {
	map = new BMap.Map('map');
	map.centerAndZoom(new BMap.Point(111.662076, 40.864993), 15);
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
	locateaddr();

};
var locateaddr = function() {
	map.clearOverlays();
	for (i in serviceItems.items) {
		var new_point = new BMap.Point(serviceItems.items[i].longitude,
				serviceItems.items[i].latitude);
		var marker = null;
		if (i <= 9) {
			var index = i * 1 + 1;
			var imgurl = '../img/icon_mark_' + index + '.png';
			var myIcon = new BMap.Icon(imgurl, new BMap.Size(32, 70), {
				imageOffset : new BMap.Size(0, 0)
			});
			marker = new BMap.Marker(new_point, {
				icon : myIcon
			});
			marker.setZIndex(((10 - i * 1) * 1 + 1));
		} else {
			marker = new BMap.Marker(new_point);
			marker.setZIndex(1);
		}
		var htmlmess = '<a href="providerdetail.do?providerId='
				+ serviceItems.items[i].providerId
				+ '" style="text-decoration: none;color: #000;"><div><div style="font-size:18px;">'
				+ serviceItems.items[i].title
				+ '</div><br><div style="font-size:15px;">'
				+ serviceItems.items[i].addr + '</div></div></a>';
		addClickHandler(htmlmess, marker);
		map.addOverlay(marker, 1);
		map.panTo(new_point);
	}
};
var opts = {
	width : 250,
	height : 80
// ,
// title : "信息窗口",
// enableMessage : true
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
 * 加载下一页
 */
var loadnextpage = function() {
	var rowindex = $('#rowindex').val();
	var html = '';
	var data = serviceItems.items;
	// var i=rowindex;
	var i = rowindex * 1;
	for (i; i++; i - data.length < 0) {
		if (i - data.length >= 0 || i - (rowindex * 1 + 10) > 0) {
			break;
		}
		/*
		 * var listboxdiv = $('<div></div>').addClass('listBox').css('margin-top',
		 * 10); var listboxa = $('<a></a>').attr('href',
		 * 'providerdetail.do?providerId=' + data[i].providerId); listboxa =
		 * listboxa.append($('<h3></h3>').text(data[i].title)).append( $('<div></div>').addClass('greyText').text(data[i].addr));
		 * listboxa = listboxa.append($('<div></div>').addClass('redText').text(
		 * data[i].business + '次成效/' + data[i].browse + '次浏览'));
		 * alert(listboxa.html()); listboxdiv.append(listboxa);
		 */
		html = html + '<div class=\"listBox\" style=\"margin-top: 10px;\">'
				+ '<a href=\"providerdetail.do?providerId='
				+ data[i].providerId + '\"><h3>' + data[i].title
				+ '</h3><div class=\"greyText\">' + data[i].addr
				+ '</div><div class=\"redText\">' + data[i].business + '次成效/'
				+ data[i].browse + '次浏览</div></a></div>';

		for (j in data[i].providerItems) {
			/*
			 * var childbox = $('<div></div>').addClass('listBox'); var tablei =
			 * $('<table></table>').addClass('layoutTbl').css( 'width',
			 * '100%'); var tri = $('<tr></tr>'); var tdIcon = $('<td></td>').append(
			 * $('<span></span>').addClass('textIcon').text(
			 * data[i].providerItems[j].sysItemName)).append(
			 * data[i].providerItems[j].title); tri.append(tdIcon); var
			 * greenTexttd = $('<td></td>');
			 */
			if (data[i].providerItems[j].price != null
					|| data[i].providerItems[j].price != '') {
				/*
				 * html = html + '<td width="80" class="greenText"
				 * style="font-size: 14px; text-align: right;">￥' +
				 * data[i].providerItems[j].price + '</td>' + '</tr>';
				 */

				/*
				 * greenTexttd.css('width', 80).addClass('greenText').css(
				 * 'font-size', '14p').css(' text-align', 'right').append( '￥' +
				 * data[i].providerItems[j].price);
				 */

			} else {
				/*
				 * html = html + '<td width="80" class="greenText"
				 * style="font-size: 14px; text-align: right;">' + '还未定价' + '</td>' + '</tr>';
				 */
				/*
				 * greenTexttd.css('width', 80).addClass('greenText').css(
				 * 'font-size', '14p').css(' text-align', 'right').append(
				 * '还未定价');
				 */
			}
			// tri.append(greenTexttd);
			// tablei = tablei.append(tri);
			// childbox = childbox.append(tablei);

			html = html + '<div class=" listBox">'
					+ '<table class="layoutTbl" width="100%">' + '<tbody><tr>'
					+ '<td><span  class="textIcon">'
					+ data[i].providerItems[j].sysItemName + '</span>'
					+ data[i].providerItems[j].title + '</td>';
			if (data[i].providerItems[j].price != null
					|| data[i].providerItems[j].price != '') {
				html = html
						+ '<td width="80" class="greenText" style="font-size: 14px; text-align: right;">￥'
						+data[i].providerItems[j].price. toFixed(2) + '</td>' + '</tr>';
			} else {
				html = html
						+ '<td width="80" class="greenText" style="font-size: 14px; text-align: right;">'
						+ '还未定价' + '</td>' + '</tr>';
			}

			html = html + '</tbody></table>' + '</div>';

			// listboxdiv.append(childbox);
		}
		html = html
				+ '<table class="layoutTbl" width="100%">'
				+ '<tbody><tr><td></td><td style="text-align: right;"><span class="textIcon">都有了</span></td></tr></tbody></table>';

		/*
		 * var buttontab = $('<table></table>').append( $('<tr></tr>').append($('<td></td>')).append(
		 * $('<td></td>').css('text-align', 'right').append( $('<span></span>').addClass('textIcon').text(
		 * '都有了'))));
		 */
		// listboxdiv = listboxdiv.append(buttontab);
	}
	$('#serlist').append(html);
	// $('#serlist').append(listboxdiv);
	if (i >= data.length) {
		$('#loadnextpagebtn').text('都有了');
	}
	$('#rowindex').val(i - 1);

};
