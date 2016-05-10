<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<head>
<meta charset="utf-8">
<title>header</title>
<meta name="description" content="Hello World">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<style>
body {
	background-image:
		url("https://www.corning-cc.edu/sites/default/files/styles/headline/public/header-academic-calendar-ss-59653657-788x360.jpg?itok=NhhyIGGb");
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
	<%@include file="user_header.jsp"%>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h4 class="section-subheading">Hotel Bookings</h4>
				</div>
			</div>
			<form id="feedbackForm" method="post" action=""
				class="form-horizontal">
				<table class="tablesorter table table-bordered table-striped"
					class="sortable" id="myTable">
					<tbody>
						<tr>
							<th>Booking ID</th>
							<th>Hotel name</th>
							<th>Date of booking</th>
							<th>Type of Room</th>
							<th>Number of rooms</th>
							<th>Number of nights</th>
							<th>Total cost</th>
						</tr>
						<c:forEach var="item" items="${requestScope.hotelBookingList}">
							<tr>
								<td><c:out value="${item.hotelBooking_ID}" /></td>
								<td><c:out value="${item.hotelName}" /></td>
								<td><c:out value="${item.dateOfBooking}" /></td>
								<td><c:out value="${item.typeOfRoom}" /></td>
								<td><c:out value="${item.numberOfRooms}" /></td>
								<td><c:out value="${item.numberOfNights}" /></td>
								<td><c:out value="${item.hotelTotalCost}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</section>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">

					<h4 class="section-subheading">Flight Bookings</h4>
				</div>
			</div>
			<form id="feedbackForm" method="post" action=""
				class="form-horizontal">
				<table class="tablesorter table table-bordered table-striped"
					class="sortable" id="myTable">

					<tbody>
						<tr>
							<th>Booking ID</th>
							<th>Trip type</th>
							<th>Destination</th>
							<th>Source</th>
							<th>Cost</th>
							<th>Date of departure</th>
							<th>Date of arrival</th>
							<th>Time of departure</th>
							<th>Time of arrival</th>
							<th>Date of booking</th>
						</tr>
						<c:forEach var="item" items="${requestScope.flightBookingList}">

							<tr>
								<td><c:out value="${item.flightID}" /></td>
								<td><c:out value="${item.tripType}" /></td>
								<td><c:out value="${item.destination1}" /></td>
								<td><c:out value="${item.source1}" /></td>
								 <td><c:out value="${item.price}" /></td> 
								<td><c:out value="${item.dateOfDeparture1}" /></td>
								<td><c:out value="${item.dateOfArrival1}" /></td>
								<td><c:out value="${item.departureTime1}" /></td>
								<td><c:out value="${item.arrivalTime1}" /></td>
								<td><c:out value="${item.dateOfBooking}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</section>
</body>
</html>