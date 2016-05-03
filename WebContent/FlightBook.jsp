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

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script>
<script type="text/javascript" src="jquery-1.12.2.min.js"></script>

<script type="text/javascript" src="jquery.tablesorter.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css">


<script>

</script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/center-block.css">

<style>
th {
	background-color: #A9A9A9;
	color: white;
}
</style>

</head>
<body>
	<%@include file="user_header.jsp"%>
	<div class="container">

		<h2 style="text-align: center">Your Booking Details</h2>

		<form class="form-goup" role="form" action='BookFlight' method="POST"
			id="myform">

			<c:forEach items="${requestScope.FlightList}" var="item">
					
				flightID		<c:out value="${item.flightID}" /> 
				destination	<c:out value="${item.destination}" />
				dateOfDeparture		<c:out value="${item.dateOfDeparture}" />
				departureTime	<c:out value="${item.departureTime}" />
				dateOfArrival		<c:out value="${item.dateOfArrival}" />
				arrivalTime		<c:out value="${item.arrivalTime}" />
				price	<c:out value="${item.price}" />


			</c:forEach>


			<button type="button" class="btn btn-default"
				onclick="alert('Hi, Your Flight is booked Successfully');"
				value="Confirm Flight">Confirm Flight</button>

		</form>
	</div>

</body>
</html>