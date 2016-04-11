<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login page</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>
<link href="js/google-code-prettify/prettify.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
body {
	background-image:
		url("https://www.inspectionsupport.net/wp-content/uploads/2014/07/LoginRed.jpg");
	background-color: #cccccc;
	background-repeat: no-repeat;
	background-size: cover;
}
.ta-bar {
	
	background-color: rgba(255, 255, 255, 0.5);
}
</style>

</head>

<body id="page-top" class="index">

	<%@include file="Header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Login</h2>
				<h3 class="section-subheading text-muted">Enter your login
					credentials</h3>
				<br />
				<p align="right"><font color="red"><h3>Invalid credentials..!! Try again</h3></font></p>
			<br/>
			</div>
		</div>
		<form action='LogIn' method="POST" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-3" for="usr">Username</label>
				<div class="col-sm-6">
					<input required type="text" class="form-control" id="usr" name="username"
						placeholder="Enter your username" required>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-3" for="usr">Password</label>
				<div class="col-sm-6">
					<input required type="password" class="form-control" id="usr" name="password"
						placeholder="Enter your password" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-3">
					<div class="btn-toolbar col-xs-6 ">
						<button type="submit"
							class="col-xs-3 btn btn-primary btn-load btn-md">Login</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Not a member yet? &nbsp;<a href="RegisterPage.jsp" role="button">Sign
							Up</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/agency.js"></script>
	<%@include file="footer.jsp"%>
</body>