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


$("#myTable").tablesorter(); 

$(document).ready(function() { 
    $("table").tablesorter({ 
        // pass the headers argument and assing a object 
       /*  headers: { 
            // assign the secound column (we start counting zero) 
           // assign the third column (we start counting zero) 
            1: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            },
            2: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            },
            3: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            },
            4: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            },
            5: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            },
            6: { 
                // disable it by setting the property sorter to false 
                sorter: false 
            }
        }  */
    }); 
});

 

</script>

<script src="jquery.tablesorter.js"> </script>
<script> $('myTable').tablesorter(); </script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/center-block.css">

<style> 

 th {
    background-color:  	#A9A9A9;
    color: white;
	}
</style>

</head>
<body>
	<%@include file="user_header.jsp"%>
	<div class="container">

			<h2 style="text-align: center">List of Flights</h2>
			<br>
		
		<div class="container" style="text-align: center" >  
		<span class="label label-success" style="font-size: 30px; " >${requestScope.source}</span>
		<img src="images/flight.png" width="40" height="30">
		<img src="images/flight.png" width="40" height="30">
		<img src="images/flight.png" width="40" height="30">
		<img src="images/flight.png" width="40" height="30">
		<span class="label label-success" style="font-size: 30px">${requestScope.destination}</span>
		</div>
		
		<br> <br>
		<table class="tablesorter table table-bordered table-striped"
			class="sortable" id="myTable">

			<thead>
				<tr>
					<th class="header">FlightID</th>
<!-- 					<th>source</th>
					<th>destination</th> -->
					<th>DateOfDeparture</th>
					<th>departureTime</th>
					<th>DateOfArrival</th>
					<th>arrivalTime</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.flightList}" var="item">
					<tr>
						 <td><c:out value="${item.flightID}" /></td> 
						<%-- <td><c:out value="${item.source}" /></td>
						<td><c:out value="${item.destination}" /></td> --%>
						<td><c:out value="${item.dateOfDeparture}" /></td>
						<td><c:out value="${item.departureTime}" /></td>
						<td><c:out value="${item.dateOfArrival}" /></td>
						<td><c:out value="${item.arrivalTime}" /></td>
						<td><c:out value="${item.price}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>