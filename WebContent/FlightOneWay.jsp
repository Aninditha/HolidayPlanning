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
		url("http://i.slimg.com/sc/sl/photo/a/ai/air-passengerplaneaboveclouds-dd.jpg");
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
	<!-- Include Bootstrap Datepicker -->
	<link rel="stylesheet"
		href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
	<link rel="stylesheet"
		href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

	<style type="text/css">
/**
 * Override feedback icon position
 * See http://formvalidation.io/examples/adjusting-feedback-icon-position/
 */
#dateRangeForm .form-control-feedback {
	top: 0;
	right: -15px;
}
</style>

	<section>

		<div class="container text-center">
			<h3>Search for flights</h3>
			<p>Enter the details</p>


			<FORM NAME="radioLinks">
				<INPUT TYPE="radio" NAME="pickme" id="OneWay" checked=""
					onClick="document.location='http://localhost:8080/HolidayPlanning/FlightOneWay.jsp';" />
				One Way <INPUT TYPE="radio" NAME="pickme" id="RoundTrip"
					onClick="document.location='http://localhost:8080/HolidayPlanning/FlightSearch.jsp';" />
				Round Trip

			</FORM>

			<form id="dateRangeForm" action="FlightSearch" method="POST"
				class="form-horizontal">


				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Source:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="usr" name="source"
							placeholder="Enter source">
					</div>
				</div>
				<div class="form-group">

					<label class="control-label col-sm-3" for="usr">Destination:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="usr"
							name="destination" placeholder="Enter destination">
					</div>

				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">Start Date</label>
					<div class="col-sm-6">
						<div class="input-group input-append date" id="dateRangePicker">
							<input type="text" class="form-control" name="startDate" /> <span
								class="input-group-addon add-on"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3">End Date</label>
					<div class="col-sm-6">
						<div class="input-group input-append date" id="dateRangePicker1">
							<input type="text" class="form-control" name="endDate" / disabled>
							<span class="input-group-addon add-on"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
				<script>


$(document).ready(function() {
    $('#dateRangePicker')
        .datepicker({
            format: 'yyyy-mm-dd',
            startDate: '2010-01-01',
            endDate: '2020-01-01'
        })
        .on('changeDate', function(e) {
            // Revalidate the date field
            $('#dateRangeForm').formValidation('revalidateField', 'startdate');
        });
    $('#dateRangePicker1')
    .datepicker({
        format: 'yyyy-mm-dd',
        startDate: '2010-01-01',
        endDate: '2020-01-01'
    })
    .on('changeDate', function(e) {
        // Revalidate the date field
        $('#dateRangeForm').formValidation('revalidateField', 'enddate');
    });

    $('#dateRangeForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'The date is required'
                    }
                }
            },
            startDate: {
                validators: {
                    notEmpty: {
                        message: 'The start date is required'
                    },
                    date: {
                        format: 'MM/DD/YYYY',
                        min: '01/01/2010',
                        max: 'enddate',
                        message: 'The date is not a valid'
                    }
                }
            },
            endDate: {
                validators: {
                    notEmpty: {
                        message: 'The end date is required'
                    },
                    date: {
                        format: 'MM/DD/YYYY',
                        min: 'startdate',
                        message: 'The end date is not a valid'
                    }
                }
            }
        }
        })
        .on('success.field.fv', function(e, data) {
            if (data.field === 'startdate' && !data.fv.isValidField('enddate')) {
                // We need to revalidate the end date
                data.fv.revalidateField('enddate');
            }

            if (data.field === 'enddate' && !data.fv.isValidField('startdate')) {
                // We need to revalidate the start date
                data.fv.revalidateField('startdate');
            }
    });
    var datepickerBegin = $("#dateRangePicker").val(); // lets, returning in mm/dd/yy format
    var datepickerEnd = $("#dateRangePicker1").val(); // lets, returning in mm/dd/yy format

    if ( ($.datepicker.parseDate('mm/dd/yy', datepickerBegin) -  $.datepicker.parseDate('mm/dd/yy', datepickerEnd)) > 7) {
    alert('more than a week apart!') ;
    }
});

</script>
				<div class="form-group">
					<label class="control-label col-sm-3" for="usr">Capacity:</label>
					<div class="col-sm-6">
						<input type="number" class="form-control" id="usr" required
							name="capacity" placeholder="Enter number of people travelling"
							min="1" / max="10" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-6 col-xs-offset-3">
						<button type="submit" class="btn btn-default">Search</button>
					</div>
				</div>
			</form>

		</div>
		<!-- end Container-->

		<!-- end well-->
	</section>
	<!-- Call to action -->

	<%@include file="footer.jsp"%>


</body>
</html>