<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Lab</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
#allmap {
	width: 100%;
	height: 500px;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}

#searchForm {
	margin: 0 0 0;
}
</style>
<link
	href="https://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"
	rel="stylesheet" />

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/dataTables.buttons.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script src="//cdn.datatables.net/buttons/1.4.2/js/buttons.html5.min.js"></script>



<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=aUoRtZCHgO6B1gqebx0XLGoG9hl2Ghce"></script>
</head>
<body>
	<form id="searchForm">
		<input type="text" id="suggestId" size="20" placeholder="请输入大概位置..."
			class="input-large search-query" />
		<div id="searchResultPanel"
			style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>



		<input type="text" id="query" placeholder="鼠标拾取经纬度"
			class="input-large search-query" disabled /> <input type="button"
			id="button" class="btn btn-primary" value="搜索周边站点"
			onclick="getSearchResultJSON();" />
	</form>
	<div id="allmap"></div>
	<section class="panel">
		<table id="siteInfoTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>站址名称</th>
					<th>站址编码</th>
					<th>产品配置</th>
					<th>价格</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</section>

</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var dataTable;
	var hashMap = new Map();
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(118.079909, 24.480661);
	var convertor = new BMap.Convertor();
	var mapZoomSize = 15;
	map.centerAndZoom(point, mapZoomSize);
	map.addEventListener("click", function(e) {
		$("#query").attr("value", e.point.lng + "," + e.point.lat);
	});

	function getSearchResultJSON() {
		var query = $("#query").val();
		var data = {};
		data["query"] = query;
		$.ajax({
			url : "/DemoFJSupportSystem/getSearchResultJSON",
			type : "GET",
			data : "query=" + query,
			dataType : "text",
			success : function(data) {
				obj = JSON.parse(data);
				reDrawBaiduMap(new BMap.Point(query.split(",")[0], query
						.split(",")[1]), obj);

				for ( var k in obj) {
					var staId = k;
					var staName = obj[k]["站址名称"];
					var productConfiguration = obj[k]["产品配置"];
					var price = obj[k]["价格"];
					if (hashMap.has(staId)) {
						console.log("if");
					} else {
						console.log("else");
						hashMap.set(staId, "");
						dataTable.row.add([ staId, staName,
								productConfiguration, price ]);
					}
				}
				dataTable.draw();

			}
		});
	}
	function reDrawBaiduMap(center, dataObjJSON) {
		//		map.clearOverlays();
		var opts = {
			width : 200, // 信息窗口宽度
			height : 20, // 信息窗口高度
			title : "站址名称", // 信息窗口标题
			enableMessage : true,//设置允许信息窗发送短息
			message : "亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
		}
		map.centerAndZoom(center, mapZoomSize);
		for ( var k in dataObjJSON) {
			var longitude = dataObjJSON[k]["经度"];
			var latitude = dataObjJSON[k]["纬度"];
			var staName = dataObjJSON[k]["站址名称"];
			var tempPoint = new BMap.Point(longitude, latitude);
			var marker = new BMap.Marker(tempPoint);

			map.addOverlay(marker);
			(function(staName, tempPoint) {
				marker.addEventListener("click", function() {
					var infoWindow = new BMap.InfoWindow(staName); // 创建信息窗口对象 
					map.openInfoWindow(infoWindow, tempPoint); //开启信息窗口
				});
			})(staName, tempPoint);

		}
		var circle = new BMap.Circle(center, 1000, {
			fillColor : "blue",
			strokeWeight : 1,
			fillOpacity : 0.3,
			strokeOpacity : 0.3,
		});
		map.addOverlay(circle);
	}
	//百度地图搜索大概位置Start
	var ac = new BMap.Autocomplete( //建立一个自动完成的对象
	{
		"input" : "suggestId",
		"location" : map
	});
	ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = "
				+ value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = "
				+ value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district
				+ _value.street + _value.business;
		G("searchResultPanel").innerHTML = "onconfirm<br />index = "
				+ e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	function setPlace() {
		map.clearOverlays(); //清除地图上所有覆盖物
		function myFun() {
			var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
			map.centerAndZoom(pp, mapZoomSize);
			map.addOverlay(new BMap.Marker(pp)); //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete : myFun
		});
		local.search(myValue);
	}
	function G(id) {
		return document.getElementById(id);
	}
	//百度地图搜索大概位置End

	//jquery dataTable start
	$(document)
			.ready(
					function() {
						dataTable = $('#siteInfoTable')
								.DataTable(
										{
											"language" : {
												"url" : "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Chinese.json"
											},
											dom : '<B><lfrtip>',
											buttons : [ 'copyHtml5',
													'excelHtml5' ],
											"lengthMenu" : [
													[ 10, 25, 50, -1 ],
													[ 10, 25, 50, "All" ] ]
										});
					});

	//jquery dataTable end
</script>


