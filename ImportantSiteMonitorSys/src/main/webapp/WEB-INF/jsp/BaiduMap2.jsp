<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}

#l-map {
	height: 100%;
	width: 78%;
	float: left;
	border-right: 2px solid #bcbcbc;
}

#r-result {
	height: 100%;
	width: 20%;
	float: left;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=aUoRtZCHgO6B1gqebx0XLGoG9hl2Ghce"></script>

<link
	href="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.js"></script>
<title>活动告警地图展示</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap", {mapType:BMAP_HYBRID_MAP});
	var point = new BMap.Point(118.139496, 24.4923);
	map.addControl(new BMap.MapTypeControl()); 
	map.centerAndZoom(point, 13);
	map.enableScrollWheelZoom(true);
	var ctrl = new BMapLib.TrafficControl({
		showPanel: true //是否显示路况提示面板
	});      
	map.addControl(ctrl);
	ctrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);  
	
	
	
	function getBoundary(city){       
		var bdary = new BMap.Boundary();
		bdary.get(city, function(rs){       //获取行政区域
			var count = rs.boundaries.length; //行政区域的点有多少个
			if (count === 0) {
				alert('未能获取当前输入行政区域');
				return ;
			}
          	var pointArray = [];
			for (var i = 0; i < count; i++) {
				var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				pointArray = pointArray.concat(ply.getPath());
			}    
		});   
	}

	setTimeout(function(){
		var cities = ["福州市","厦门市","泉州市","漳州市", "宁德市", "莆田市", "南平市", "三明市", "龙岩市"];
		
		for(var i=0; i<cities.length; i++){
			console.log(cities[i]);
			getBoundary(cities[i]);
		}
	}, 2000);
	
	
	var host = window.location.host;
	console.log(host);
	
	var name = "empty";
	var convertor = new BMap.Convertor();
	var pointArr = [];
	<c:forEach items="${stations}" var="station">  
		setTimeout(function(){
	        var convertor = new BMap.Convertor();
	        var pointArr = [];
	        pointArr.push(new BMap.Point(${station.longitude}, ${station.latitude}));
	        convertor.translate(pointArr, 1, 5, function (data){
		        if(data.status === 0) {
		            var point = data.points[0];
		            var myIcon;
		            var marker;
		            if("${station.alarm}" != ""){
		            	if("${station.state}" != "处理中"){
		            		myIcon = new BMap.Icon("http://"+host+"/spring-validator/images/redIcon1.png", new BMap.Size(24,24));
		    	  			marker = new BMap.Marker(point, {icon:myIcon});
		            	}else{
		            		myIcon = new BMap.Icon("http://"+host+"/spring-validator/images/blueIcon1.png", new BMap.Size(24,24));
		    	  			marker = new BMap.Marker(point, {icon:myIcon});
//			        	var label = new BMap.Label("${station.alarm}",{offset:new BMap.Size(20,-10)});
//			        	marker.setLabel(label);
		            	}
		    	  	}else{
		    	  		myIcon = new BMap.Icon("http://"+host+"/spring-validator/images/greenIcon1.png", new BMap.Size(24,24));
		    	  		marker = new BMap.Marker(point, {icon:myIcon});
		    	  	}

		    	  	map.addOverlay(marker);
		    	  	if("${station.alarm}" != "")
		    	  		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		    	  	var opts = {
		    			width : 250,     // 信息窗口宽度
		    			height: 100,     // 信息窗口高度
		    			title : "${station.name}<br>${station.leaderName}" , // 信息窗口标题
		    			enableMessage:true,//设置允许信息窗发送短息
		    			message:"empty"
		    		}
		    		var infoWindow = new BMap.InfoWindow("告警名称:<br>${station.alarm}", opts);  // 创建信息窗口对象 
		    		marker.addEventListener("click", function(){          
		    			map.openInfoWindow(infoWindow, point); //开启信息窗口
		    		});	
		          }
		        })
	    }, 1000);
	</c:forEach> 
	
	
</script>