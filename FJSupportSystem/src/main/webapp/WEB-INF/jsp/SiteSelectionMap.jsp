<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override  name="page_content">
      <iframe src="siteSelection" width="100%"></iframe>
</rapid:override >

<rapid:override  name="page_script">
	<script type="text/javascript">
		var height = $(".content-wrapper").outerHeight();
		console.log(height);
		$("iframe").height(height);
	</script>
</rapid:override >

<%@include file="DashboardBase.jsp" %>  
    