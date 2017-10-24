<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script>
	function doAjaxPost() {
		$.ajax({
			type : "GET",
			url : "/spring-validator/getAjaxInfo",
			success : function(response) {
				$("#subViewDiv").html(response);
			}
		});
	}
</script>
</head>
<body>
	<input type="button" id="button" value="GO!" onclick="doAjaxPost();" />
	<div id="subViewDiv"></div>
</body>
</html>
