<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>FJSupportSystem</title>
<!-- Bootstrap core CSS-->
<link href="dashboardAssets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="dashboardAssets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link href="dashboardAssets/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="dashboardAssets/css/sb-admin.css" rel="stylesheet">

<!-- datePicker -->
<link rel="stylesheet" type="text/css"
	href="datePicker/css/jquery.datetimepicker.css"/ >
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href="index.html">FJSupportSystem</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Components"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseComponents" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-wrench"></i> <span class="nav-link-text">数据集市</span>
				</a>
					<ul class="sidenav-second-level collapse show"
						id="collapseComponents">
						<li><a href="navbar.html">订单信息表</a></li>
						<li><a href="cards.html">财务数据</a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Dashboard"><a class="nav-link" href="index.html"> <i
						class="fa fa-fw fa-dashboard"></i> <span class="nav-link-text">Dashboard</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Charts"><a class="nav-link" href="charts.html"> <i
						class="fa fa-fw fa-area-chart"></i> <span class="nav-link-text">Charts</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="tables.html"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Tables</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseExamplePages" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-file"></i> <span class="nav-link-text">Example
							Pages</span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseExamplePages">
						<li><a href="login.html">Login Page</a></li>
						<li><a href="register.html">Registration Page</a></li>
						<li><a href="forgot-password.html">Forgot Password Page</a></li>
						<li><a href="blank.html">Blank Page</a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Menu Levels"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseMulti" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-sitemap"></i> <span class="nav-link-text">Menu
							Levels</span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseMulti">
						<li><a href="#">Second Level Item</a></li>
						<li><a href="#">Second Level Item</a></li>
						<li><a href="#">Second Level Item</a></li>
						<li><a class="nav-link-collapse collapsed"
							data-toggle="collapse" href="#collapseMulti2">Third Level</a>
							<ul class="sidenav-third-level collapse" id="collapseMulti2">
								<li><a href="#">Third Level Item</a></li>
								<li><a href="#">Third Level Item</a></li>
								<li><a href="#">Third Level Item</a></li>
							</ul></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Link"><a class="nav-link" href="#"> <i
						class="fa fa-fw fa-link"></i> <span class="nav-link-text">Link</span>
				</a></li>
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>退出
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">数据集市</a></li>
				<li class="breadcrumb-item active">订单信息表</li>
			</ol>

			<div class="row">
				<form class="container"
					action="/FJSupportSystem/downloadOrderInfoTable" method="post">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="selectedCity">地市：</label> <select
								class="form-control" class="custom-select" id="selectedCity"
								name="selectedCity">
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
							<label>日期：</label> <input class="form-control"
								id="datetimepicker" name="datetimepicker" type="text"></input>
						</div>
					</div>
					<button class="btn btn-primary" type="submit">导出Excel</button>
				</form>
			</div>

		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<footer class="sticky-footer">
			<div class="container">
				<div class="text-center">
					<small>Copyright © Your Website 2017</small>
				</div>
			</div>
		</footer>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Logout Modal-->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							Leave?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Select "Logout" below if you are
						ready to end your current session.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary" href="login.html">Logout</a>
					</div>
				</div>
			</div>
		</div>
		<!-- Bootstrap core JavaScript-->
		<script src="dashboardAssets/vendor/jquery/jquery.min.js"></script>
		<script
			src="dashboardAssets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script
			src="dashboardAssets/vendor/jquery-easing/jquery.easing.min.js"></script>
		<!-- Page level plugin JavaScript-->
		<script src="dashboardAssets/vendor/chart.js/Chart.min.js"></script>
		<script src="dashboardAssets/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="dashboardAssets/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="dashboardAssets/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script src="dashboardAssets/js/sb-admin-datatables.min.js"></script>
		<script src="dashboardAssets/js/sb-admin-charts.min.js"></script>

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
	</div>
</body>
</html>
