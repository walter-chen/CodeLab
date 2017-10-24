<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>福建铁塔运营发展支撑系统</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="loginPageAssets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="loginPageAssets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="loginPageAssets/css/form-elements.css">
<link rel="stylesheet" href="loginPageAssets/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="loginPageAssets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="loginPageAssets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="loginPageAssets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="loginPageAssets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="loginPageAssets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>运营发展支撑系统</strong>
						</h1>
						<div class="description">
							<p>
								中国铁塔股份有限公司<a href="http://azmind.com"><strong>福建省</strong></a>分公司
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>功能模块</h3>
								<p>数据源：铁塔公司CRM、资源管理系统</p>
							</div>
							<div class="form-top-right">
								<!--  <i class="fa fa-key"></i>     -->
								<img src="images/BlackLogo.png"></img>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="siteSelection" method="get" class="login-form">
								<button class="btn">站址预选</button>
							</form>
							<br>
							<form role="form" action="statistic" method="get"
								class="login-form">
								<button class="btn">待定...</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 social-login">
					<h4>Powered by: Walter Bunny</h4>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="loginPageAssets/js/jquery-1.11.1.min.js"></script>
	<script src="loginPageAssets/bootstrap/js/bootstrap.min.js"></script>
	<script src="loginPageAssets/js/jquery.backstretch.min.js"></script>
	<script src="loginPageAssets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="loginPageAssets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>