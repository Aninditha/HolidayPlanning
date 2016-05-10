<!DOCTYPE html>
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
		url('http://www.bizreport.com/images/shutterstock/2015/02/loyalty_151899761-thumb-380xauto-3465.jpg');
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
					<h2 class="section-heading">Loyalty points</h2>
					<h3 class="section-subheading text-muted">View your
						hard-earned Loyalty Points!!</h3>
				</div>
			</div>
			<form id="feedbackForm" method="post" action=""
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-md-4" for="usr">Current
						Loyalty Points</label>
					<div class="col-md-4">
						<input type="text" readonly class="form-control" id="usr1"
							name="currentLoyalityPoints" value="${requestScope.currentLoyalityPoints}">
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="control-label col-md-4" for="usr">Total
						Loyalty Points</label>
					<div class="col-md-4">
						<input type="text" readonly class="form-control" id="usr1"
							name="First Name" placeholder="" required>
					</div>
				</div> -->
			</form>
		</div>
	</section>
</body>
</html>