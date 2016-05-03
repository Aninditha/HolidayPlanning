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

<%@include file="user_header.jsp"%>

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
			<h3>Selected flight one way Details</h3>
			<p></p>
			<form id="hotel_details" role="form" action="" method="POST"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Source:</label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.source}" name="source">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Destination:</label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.destination}" name="destination">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="SlotDate"> Total
						cost: </label>
					<div class="col-sm-6">
						<input readonly type="text" class="form-control" id="usr"
							value="${requestScope.price}" name="price">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Date of
						Departure:</label>
					<div class="col-sm-6">
						<select readonly id="no_rooms" class="form-control"
							name="dateOfDeparture1" required>
							<option class="form-control"
								value="${requestScope.dateOfDeparture1}">
								${requestScope.dateOfDeparture1} </option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Date of
						Arrival</label>
					<div class="col-sm-6">
						<select readonly id="type_rooms" class="form-control"
							name="dateOfArrival1" required>
							<option class="form-control"
								value="${requestScope.dateOfArrival1}">
								${requestScope.dateOfArrival1}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-6 col-xs-offset-3">
						<button type="submit" class="btn btn-default">Confirm
							Book</button>
					</div>
				</div>
			</form>
			<div class="col-xs-6 col-xs-offset-3">
				<a href="flight_user.jsp" class="btn btn-default" role="button">Cancel</a>

			</div>
		</div>
</body>
<script>
	$("#SlotDate").datepicker();
</script>
<script>
	$("#SlotDate").change(function() {
		var slotDate = document.getElementById("SlotDate").value;
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();

		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}

		today = mm + '/' + dd + '/' + yyyy;
		if ((Date.parse(slotDate) < Date.parse(today))) {
			alert("Booking date should be equal to or more than today's date");
			document.getElementById("SlotDate").value = "";
		}
	});
</script>
</html>