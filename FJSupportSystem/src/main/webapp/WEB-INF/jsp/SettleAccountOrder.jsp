<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<rapid:override name="page_css">
	<!-- datePicker -->
	<link rel="stylesheet" type="text/css"
		href="datePicker/css/jquery.datetimepicker.css"/ >
</rapid:override>
<rapid:override name="page_content">
	<ol class="breadcrumb">
		<li class="breadcrumb-item">数据集市</li>
		<li class="breadcrumb-item active">出账调账</li>
	</ol>

	<div class="row">
		<form class="container"
			action="/FJSupportSystem/downloadSettleAccountOrder" method="post">
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="selectedCity">地市：</label> <select class="form-control"
						class="custom-select" id="selectedCity" name="selectedCity">
						<option selected value="福建">请选择</option>
						<option value="福州">福州</option>
						<option value="厦门">厦门</option>
						<option value="泉州">泉州</option>
						<option value="漳州">漳州</option>
						<option value="宁德">宁德</option>
						<option value="莆田">莆田</option>
						<option value="南平">南平</option>
						<option value="三明">三明</option>
						<option value="龙岩">龙岩</option>
					</select>
				</div>
				<div class="col-md-6 mb-3">
					<label>账期：</label> <input class="form-control" id="datetimepicker"
						name="datetimepicker" type="text"></input>
				</div>
			</div>
			<button class="btn btn-primary" type="submit">导出Excel</button>
		</form>
	</div>
</rapid:override>
<rapid:override name="page_script">
	<!-- datePicker -->
	<script src="datePicker/js/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$.datetimepicker.setLocale('ch');
			$('#datetimepicker').datetimepicker({
				lang : 'ch',
				format : 'Y-m-d',
				timepicker : false,
				timepickerScrollbar : false,
				scrollMonth : false,
				scrollTime : false,
				scrollInput : false
			});
		})
	</script>
</rapid:override>
<%@include file="DashboardBase.jsp"%>
