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

<%@include file="Header.jsp" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

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

.button1{
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

.button1:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

</style>

</head>

<body>



	<form class="form-goup " action='Register' method="POST">

		<div class="container">
			<div class="hero-unit">

				<div class="Absolute-Center is-Responsive">


					<div class="page-header">
				 		<h1>Registration Page</h1>
					</div>
					<br /> <br /> <br /> <br />

				</div>

				<div class="form-group row" class="form-inline">
					<!-- Username -->
					<h3>
						<label class="control-label col-sm-3" for="username"
							class="textfield">Username</label>
					</h3>
					<div class="controls">
						<input type="text" class="input-lg" id="username" name="username"
							placeholder="" required>
							<span class="glyphicon"></span>
					</div>
				</div>

				<div class="form-group row" class="form-inline">
					<!-- E-mail -->

					<h3>
						<label class="control-label col-sm-3" for="email">E-mail</label>
					</h3>
					<div class="controls">
						<input type="email" class="input-lg" id="email" name="email"
							placeholder="" required>
							<span class="glyphicon"></span>
					</div>
				</div>

				<div class="form-group row">
					<h3>
						<label class="col-sm-3 control-label" for="inputPassword3">
							Password</label>
					</h3>
					<div class="controls">
						<input type="password" class="input-lg"  id="inputPassword"
							name ="password" placeholder="Password" required>
					</div>
				</div>

				<div class="form-group row">
					<!-- Password -->
					<h3>
						<label class="col-sm-3" for="password_confirm">Password
							(Confirm)</label>
					</h3>
					<div class="controls">
						<input type="password" class="input-lg" id="password_confirm"
							name="password_confirm" placeholder="" required>
					</div>
				</div>
				<br> <br>
				<div class="form-group">
					<!-- Button -->

					<button class="button1	"style="position: relative; left: 230px;"
						class="btn btn-success btn-lg ">Register</button>

				</div>
			</div>
		</div>

	</form>



</body>