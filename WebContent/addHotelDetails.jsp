<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page language="java" session="true"%>
<%@ page import="java.sql.*" %>
<%ResultSet resultset1 =null;%>
<%ResultSet resultset2 =null;%>
<%ResultSet resultset3 =null;%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>header</title>
<meta name="description" content="Hello World">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<style>
body {
	background-image:
		url("https://awesomewallpapers.files.wordpress.com/2010/05/white.jpg");
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
	<br>
	<div class="navbar-wrapper">
		<div class="container">

			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="admin_home.jsp">Holiday Planning</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">

							
									<li><a href="addHotel.jsp">Add hotel</a></li>
									<li><a href="addHotelDetails.jsp">Add hotel details</a></li>
									<li><a href="deleteHotel.jsp">Delete hotel</a></li>
					
								</ul>
							
					</div>
				</div>
			</nav>

		</div>
	</div>
	<br>
	<br>
	<section id="Service_slot">
 <div class="container">
 
            <div class="row">
                <div class="col-lg-12 text-center">
                <h1>Admin Page</h1>
                    <h2 class="section-heading">Add hotel details</h2>
                </div>
            </div>
            
            <div class="container">
  <% if (request.getAttribute("msg")==null) {}else{%>
    <label class="has-error control-label col-sm-3" for="usr7"></label>
    <div class="col-sm-6">
      <label id="usr7" style="color=red"><%=request.getAttribute("msg") %></label>
    </div>
  <%} %>
  </div>

 <form id="addHotelForm" role="form" action="addHotelDetailServlet" method="POST" class="form-horizontal">
 
 <div class="form-group">
    
               <label class="control-label col-sm-3" for="usr"> Hotel ID:</label>                      
               <div class="col-sm-6">
      <input type="text" class="form-control" id="usr" name="hotelID" placeholder="Enter the hotel_ID of the hotel(4 characters only)" required>
  
    
  </div>
    </div>
    <div class="form-group">
    <label class="control-label col-sm-3" for="usr">DetailID</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="usr" name="detailID" placeholder="Enter the detail_ID of the hotel(4 characters only)" required>
    </div>
    </div>
     <div class="form-group">
    <label class="control-label col-sm-3" for="usr">Price</label>
    <div class="col-sm-6">
      <input type="number" class="form-control" id="usr" name="price" placeholder="Enter the price of room" min='1'  required/>
    </div>
    </div>
    
    <div class="form-group">
    <label class="control-label col-sm-3" for="usr">Type of room</label>
    <div class="col-sm-6">
      <select  class="form-control"  name="typeOfRoom" required>
      <option>Suite</option>
      <option>Deluxe</option>
      <option>Executive</option>
      </select>
    </div>
    </div>
    
    <div class="form-group">
        <div class="col-xs-6 col-xs-offset-3">
            <button type="submit" class="btn btn-primary">Add hotel detail</button>
        </div>
</div>
 </form>
 </div>
 </section>

	<!-- Latest compiled and minified JavaScript -->
	<script src="http://code.jquery-2.2.2.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>	
</html>