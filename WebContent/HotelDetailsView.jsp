<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Holiday Planning</title>

<%@include file="Header.jsp"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script>
<script type="text/javascript" src="jquery-1.12.2.min.js"></script>

<script type="text/javascript" src="jquery.tablesorter.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/center-block.css">
</head>

<body>
	<link rel="stylesheet"
		href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
	<link rel="stylesheet"
		href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
	<section>

		<div class="container text-center">
			<h3>Selected Hotel Details</h3>
			<p></p>
			<form id="hotel_details" role="form" action="" method="POST"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Hotel Name:</label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.source}" name="source">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Hotel
						Details:</label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.description}" name="description">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="SlotDate">
						Check-In Date: </label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.date}" name="date">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">No. of
						rooms</label>
					<div class="col-sm-6">
						<select readonly id="no_rooms" class="form-control"
							name="no_rooms" required>
							<option class="form-control" value="${requestScope.no_rooms}">${requestScope.no_rooms}
								rooms</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Type of
						room</label>
					<div class="col-sm-6">
						<select readonly id="type_rooms" class="form-control"
							name="type_rooms" required>
							<option class="form-control" value="${requestScope.type_rooms}">${requestScope.type_rooms}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">No. of
						nights</label>
					<div class="col-sm-6">
						<select readonly id="no_nights" class="form-control"
							name="no_nights" onchange="this.form.submit();" required>
							<option class="form-control" value="${requestScope.no_nights}">
								${requestScope.no_nights} number of nights</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Cost:</label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							name="cost" value="${requestScope.cost}">
					</div>
				</div>
			</form>
			<div class="col-xs-6 col-xs-offset-3">
				<a href="hotel.jsp" class="btn btn-default" role="button">OK</a>
			</div>
		</div>
</body>

</html>