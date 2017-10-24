<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

</head>
<body>
	<form id="searchForm">
		<input type="text" id="keywords" class="input-large search-query">
		<input type="button" id="button" value="GO!" onclick="doAjaxPost();" />
	</form>
	<div id="subViewDiv"></div>
</body>
</html>
<script>
	function doAjaxPost() {
		var inputText = $("#keywords").val();
		var data = {};
		data["keywords"] = inputText;
		alert(inputText);

		$.ajax({
			url : "/spring-validator/getSearchResultJSON",
			type : "GET",
			data : "keywords=" + inputText,
			dataType : "text",
			success : function(data) {
				alert(data);
				obj = JSON.parse(data);
				for ( var k in obj) {
					console.log(k+obj[k]);
					for(var o in obj[k] )
						console.log(o+"->"+obj[k][o]);
				}
			}
		});
	}
</script>
