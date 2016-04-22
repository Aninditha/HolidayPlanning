<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Holiday Planning</title>

<%@include file="ExtraHeader.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/label.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css">


<script type="text/javascript" src="js/bootstrap.min.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/center-block.css">

<style type='text/css'>
.textfield {
	display: block;
	width: 250px;
	border: 1px solid #AF9D72;
	background-color: #F2ECD7;
}

.register {
	vertical-align: 10px;
}

.container {
	margin-top: 10px;
}

.hero-unit {
	padding: 50px 50px 50px 50px;
	margin-left: 120px;
}

.button1 {
	display: inline-block;
	padding: 15px 25px;
	font-size: 24px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #4CAF50;
	border: none;
	border-radius: 15px;
	box-shadow: 0 9px #999;
}

body {
	background-image:
		url("https://app.truconversion.com/assets/login/img/front-page-bg.png");
	background-color: #cccccc;
	background-repeat: no-repeat;
	background-size: cover;
}

.button1:active {
	background-color: #3e8e41;
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}
</style>
</head>

<body>
	<form class="form-goup" role="form" action='Register' method="POST"
		id="myform" data-toggle="validator">
		<div class="container">
			<div class="hero-unit">
				<div class="Absolute-Center is-Responsive text-center">
					<div class="page-header">
						<h1>Registration Page</h1>
						<h3 class="section-subheading text-muted">Enter the
							registration details</h3>
					</div>
					<br />
				</div>
				<div class="form-group row" class="form-inline">
					<!-- Username -->
					<label class="control-label col-sm-3" for="username"
						class="textfield">Username</label>
					<div class="controls col-sm-6">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="" required> <span
							class="glyphicon"></span>
					</div>
				</div>
				<div class="form-group row" class="form-inline">
					<!-- E-mail -->
					<label class="control-label col-sm-3" for="email">E-mail</label>

					<div class="controls col-sm-6">
						<input type="email" class="form-control" id="email" name="email"
							placeholder="" required> <span class="glyphicon"></span>
					</div>
				</div>
				<div class="form-group row" class="form-inline">

					<label class="control-label col-sm-3" for="password">Password</label>

					<div class="col-sm-6">
						<input required type="password" class="form-control"
							name="password1" id="password1" placeholder="New Password"
							autocomplete="off">
					</div>
					<br> <br>
					<div class="row">
						<div class="col-sm-3 col-sm-offset-3">
							<span id="8char" class="glyphicon glyphicon-remove"
								style="color: #FF0004;"></span> 8 Characters Long<br> <span
								id="ucase" class="glyphicon glyphicon-remove"
								style="color: #FF0004;"></span> One Uppercase Letter
						</div>
						<div class="col-sm-3">
							<span id="lcase" class="glyphicon glyphicon-remove"
								style="color: #FF0004;"></span> One Lowercase Letter<br> <span
								id="num" class="glyphicon glyphicon-remove"
								style="color: #FF0004;"></span> One Number
						</div>
					</div>
					<div class="form-group row" class="form-inline">

						<label class="control-label col-sm-3" for="password">Confirm
							Password</label>

						<div class="col-sm-6">
							<input required type="password" class="form-control"
								name="password2" id="password2" placeholder="Repeat Password"
								autocomplete="off">
						</div>
						<div class="row">
							<div class="col-sm-12 col-sm-offset-3">
								<span id="pwmatch" class="glyphicon glyphicon-remove"
									style="color: #FF0004;"></span> Passwords Match
							</div>
						</div>
						<br /> <br />
						<div class="form-group">
							<div class="col-xs-6 col-xs-offset-3">
								<button type="submit"
									class="col-xs-6 btn btn-primary btn-load btn-lg">Register</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; or &nbsp;&nbsp; <a
									href="LoginPage.jsp"> Login</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>

	<script>
		$("input[type=password]").keyup(function() {
			var ucase = new RegExp("[A-Z]+");
			var lcase = new RegExp("[a-z]+");
			var num = new RegExp("[0-9]+");

			if ($("#password1").val().length >= 8) {
				$("#8char").removeClass("glyphicon-remove");
				$("#8char").addClass("glyphicon-ok");
				$("#8char").css("color", "#00A41E");
			} else {
				$("#8char").removeClass("glyphicon-ok");
				$("#8char").addClass("glyphicon-remove");
				$("#8char").css("color", "#FF0004");
			}

			if (ucase.test($("#password1").val())) {
				$("#ucase").removeClass("glyphicon-remove");
				$("#ucase").addClass("glyphicon-ok");
				$("#ucase").css("color", "#00A41E");
			} else {
				$("#ucase").removeClass("glyphicon-ok");
				$("#ucase").addClass("glyphicon-remove");
				$("#ucase").css("color", "#FF0004");
			}

			if (lcase.test($("#password1").val())) {
				$("#lcase").removeClass("glyphicon-remove");
				$("#lcase").addClass("glyphicon-ok");
				$("#lcase").css("color", "#00A41E");
			} else {
				$("#lcase").removeClass("glyphicon-ok");
				$("#lcase").addClass("glyphicon-remove");
				$("#lcase").css("color", "#FF0004");
			}

			if (num.test($("#password1").val())) {
				$("#num").removeClass("glyphicon-remove");
				$("#num").addClass("glyphicon-ok");
				$("#num").css("color", "#00A41E");
			} else {
				$("#num").removeClass("glyphicon-ok");
				$("#num").addClass("glyphicon-remove");
				$("#num").css("color", "#FF0004");
			}

			if ($("#password1").val() == $("#password2").val()) {
				$("#pwmatch").removeClass("glyphicon-remove");
				$("#pwmatch").addClass("glyphicon-ok");
				$("#pwmatch").css("color", "#00A41E");
			} else {
				$("#pwmatch").removeClass("glyphicon-ok");
				$("#pwmatch").addClass("glyphicon-remove");
				$("#pwmatch").css("color", "#FF0004");
			}

		})

		$('form').on('submit', function() {
			if ($("#password1").val().length >= 8) {

				return true;
			} else {
				alert('Password length should be atleast 8 characters long.');
				return false;
			}
		})
		$('form').on('submit', function() {
			var ucase = new RegExp("[A-Z]+");
			var lcase = new RegExp("[a-z]+");
			var num = new RegExp("[0-9]+");
			if (ucase.test($("#password1").val())) {

				return true;
			} else {
				alert('Password should have atleast 1 Uppercase letter.');
				return false;
			}
		})
		$('form').on('submit', function() {
			var ucase = new RegExp("[A-Z]+");
			var lcase = new RegExp("[a-z]+");
			var num = new RegExp("[0-9]+");
			if (lcase.test($("#password1").val())) {

				return true;
			} else {
				alert('Password should have atleast 1 Lowercase letter.');
				return false;
			}
		})
		$('form').on('submit', function() {
			var ucase = new RegExp("[A-Z]+");
			var lcase = new RegExp("[a-z]+");
			var num = new RegExp("[0-9]+");
			if (num.test($("#password1").val())) {

				return true;
			} else {
				alert('Password should have atleast 1 Number.');
				return false;
			}
		});
	</script>
	;

	<script>
		$('form').on('submit', function() {
			if ($('#password1').val() != $('#password2').val()) {
				alert('Password not matches');
				return false;
			}
			return true;
		});
	</script>


</body>