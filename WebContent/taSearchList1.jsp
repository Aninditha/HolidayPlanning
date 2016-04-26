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


<script>
	$("#myTable").tablesorter();

	$(document).ready(function() {
		$("table").tablesorter({
			// pass the headers argument and assing a object 
			headers : {
				// assign the secound column (we start counting zero) 
				0 : {
					// disable it by setting the property sorter to false 
					sorter : false
				},
				// assign the third column (we start counting zero) 
				1 : {
					// disable it by setting the property sorter to false 
					sorter : false
				},
				4 : {
					// disable it by setting the property sorter to false 
					sorter : false
				},
				5 : {
					// disable it by setting the property sorter to false 
					sorter : false
				}
			}
		});
	});
</script>

<script src="jquery.tablesorter.js">
	
</script>
<script>
	$('myTable').tablesorter();
</script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/center-block.css">

</head>

<body>
	<div class="container">


		<table class="tablesorter table table-bordered table-striped"
			class="sortable" id="myTable">

			<thead>
				<tr>
					<th class="header">Attractions</th>
					<th>Description</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.taList}" var="item">
					<form action="TARegionSearch?regionName=${item.regionName}"
						method="POST">
						<tr>
							<td><c:out value="${item.regionName}" /></td>
							<td><c:out value="${item.description}" /></td>
							<td><button type="submit" class="btn btn-default">view>></button>
							</td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>