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

<script
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

</head>
<body>
	<div class="row">
		<!-- Doughnut -->
		<div class="col-lg-6">
			<section class="panel">
				<div id="columns" class="panel-body text-center">
					<canvas height="300" width="400"></canvas>
				</div>
			</section>
		</div>
		<!-- Pie -->
		<div class="col-lg-6">
			<section class="panel">
				<div id="pie" class="panel-body text-center">
					<canvas height="300" width="400"></canvas>
				</div>
			</section>
		</div>

		<div class="col-lg-12">
			<section class="panel">
				<table id="example" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>站址名称</th>
							<th>告警</th>
							<th>负责人</th>
							<th>地市</th>
							<th>告警开始时间</th>
							<th>告警持续时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${stations}" var="station">
							<tr>
								<td>${station.name}</td>
								<td>${station.alarm}</td>
								<td>${station.leaderName}</td>
								<td>${station.bigLeaderName}</td>
								<td>${station.startTime}</td>
								<td>${station.duration}</td>
								<td>${station.state}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable({
            "oLanguage" : {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                 "sSearch": "名称:",
                "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
                }
            }
        });
	});
	$(document).ready(function() {
		// Build the chart
		Highcharts.chart('pie', {
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false,
				type : 'pie'
			},
			title : {
				text : '告警占比'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : false
					},
					showInLegend : true
				}
			},
			series : [ {
				name : '占比',
				colorByPoint : true,
				data : [
					<c:forEach items="${alarmNameMapRatio}" var="entry">
					{
						name : '${entry.key}',
						y : ${entry.value}
					}, 
					</c:forEach>
				]
			} ]
		});
	});

	Highcharts
			.chart(
					'columns',
					{
						chart : {
							type : 'column'
						},
						title : {
							
							text : '重保站点告警数量监控'
						},
						subtitle : {
							<fmt:formatDate var="someDate" value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
							text : '${someDate}'
						},
						xAxis : {
							categories : [
								   '活动告警',
							],
							crosshair : true
						},
						yAxis : {
							min : 0,
							allowDecimals: false,
							title : {
								text : '数量 (个)'
							}
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
									+ '<td style="padding:0"><b>{point.y:.0f} 个</b></td></tr>',
							footerFormat : '</table>',
							shared : true,
							useHTML : true
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : [
							<c:forEach items="${alarmNameMapAmount}" var="entry">
							{
								name : '${entry.key}',
								data : [ ${entry.value} ]
							},
							</c:forEach>
						]
					});
</script>




