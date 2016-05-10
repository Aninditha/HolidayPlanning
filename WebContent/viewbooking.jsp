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
				
					<h4 class="section-subheading">View your
						Hotel Bookings</h4>
				</div>
			</div>
			<form id="feedbackForm" method="post" action=""
				class="form-horizontal">
				
				
				<table class="tablesorter table table-bordered table-striped"
				class="sortable" id="myTable">

				<tbody>

					<c:forEach var="item" items="${requestScope.hotelBookingList}">
						<tr>
							<td><c:out value="${item.hotelBooking_ID}" /></td>
							<td><c:out value="${item.hotelName}" /></td>
							<td><c:out value="${item.dateOfBooking}" /></td>
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
</body>
</html>