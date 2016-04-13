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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<style>
body {
	background-image:
		url("http://blog.vouchercodes.in/wp-content/uploads/2015/09/3-Star-Hotel-in-India.jpg");
	background-color: #cccccc;
	background-repeat: no-repeat;
	background-size: cover;
}

.ta-bar {
	margin: 0 auto;
	background-color: rgba(255, 255, 255, 0.5);
}
</style>

</head>

<body>

	<%@include file="Header.jsp"%>
	<section>

		<div class="container text-center">
			<h3>Search for hotels</h3>
			<p>Enter a location</p>

			<br />
			<p align="right">
				<font color="red"><h3>The place you entered does not exists.. Try
						again..!!</h3></font>
			</p>
			<br />

			<form action="HotelSearch" method="POST" class="form-inline">
				<div class="form-group">
					<label for="subscription" for="region">Location: </label> <input
						required type="text" class="form-control" name="region"
						id="subscription" placeholder="Enter a city">
				</div>
				<button type="submit" class="btn btn-default">Search</button>
			</form>
		</div>
		<!-- end Container-->

		<!-- end well-->
	</section>
	<!-- Call to action -->
	<%@include file="footer.jsp"%>
</body>
</html>