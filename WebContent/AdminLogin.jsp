<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin login</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/agency.css" rel="stylesheet">
 <link href="css/datepicker.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
<link href="js/google-code-prettify/prettify.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
    body  {
      background-image: url("https://awesomewallpapers.files.wordpress.com/2010/05/white.jpg");
      background-color: #cccccc;
      background-repeat: no-repeat;
      background-size: cover;
    }
    .ta-bar {
      margin: 0 auto;
      background-color: rgba(255,255,255,0.5);
    }
    </style>

</head>

<body id="page-top" class="index">

       
     <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Login</h2>
                    <h3 class="section-subheading text-muted">Enter your login credentials</h3>
                </div>
            </div>
            <form id="loginForm" method="post" action="AdminLoginServlet" class="form-horizontal">
  <div class="form-group">
    <label class="control-label col-sm-3" for="usr">Username</label>
    <div class="col-sm-6">
      <input type="email" class="form-control" id="useername" name="username" placeholder="Enter your email ID" required>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-3" for="usr">Password</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
    </div>
  </div>
  <div class="form-group">
  <% if (request.getAttribute("loginError")==null) {}else{%>
    <label class="has-error control-label col-sm-3" for="usr7"></label>
    <div class="col-sm-6">
      <label id="usr7" style="color=red"><%=request.getAttribute("loginError") %></label>
    </div>
  <%} %>
  </div>
  <div class= "form-group">
  <div class="col-xs-offset-3">
        <div class="btn-toolbar col-xs-6 ">
            <button type="submit" class="col-xs-3 btn btn-primary btn-load btn-md">Login</button>
    
            
        </div>
        </div>
        
    </div>
  
    </form>
    </div>
     <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/classie.js"></script>
    <script src="js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/agency.js"></script>
    
    
    </body>