<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
<title>告警数据统计</title>
<link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"
	rel="stylesheet" />

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

</head>
<body>
	<div class="row">
		<!-- Pie -->
		<div class="col-lg-6">
			<section class="panel">
				<div id="pie" class="panel-body text-center">
					<canvas height="300" width="400"></canvas>
				</div>
			</section>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						Highcharts
								.chart(
										'pie',
										{
											chart : {
												type : 'pie'
											},
											title : {
												text : '网格累计排除故障告警个数'
											},
											subtitle : {
												<fmt:formatDate var="someDate" value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
												text : '${someDate}'
											},
											plotOptions : {
												series : {
													dataLabels : {
														enabled : true,
														format : '{point.name}: {point.y:.0f}个'
													}
												}
											},

											tooltip : {
												headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
												pointFormat : '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b>个<br/>'
											},
											series : [ {
												name : '大网格',
												colorByPoint : true,
												data : [
													<c:forEach items="${bigGridList}" var="bigGrid">
														{
															name : '${bigGrid.bigGridLeader}',
															y : ${bigGrid.alarmAmount},
															drilldown : '${bigGrid.bigGridLeader}'
														},
													</c:forEach>
														]
											} ],
											drilldown : {
												series : [
													<c:forEach items="${bigGridList}" var="bigGrid">
														{
															name : '${bigGrid.bigGridLeader}',
															id : '${bigGrid.bigGridLeader}',
															data : [
																<c:forEach items="${bigGrid.grids}" var="grid">
																	[ '${grid.gridLeader}',
																		${grid.alarmAmount} ],
																</c:forEach>
																	]
														},
													</c:forEach>
														]
											}
										});
					});
</script>
