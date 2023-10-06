<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
	<head>
	    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"   type="text/css" />
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
		<link href="<c:url value="/resources/css/style.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/typeahead.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/gritter.css" />" rel="stylesheet"  type="text/css"/>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="icon"  type="image/x-icon"  href="<c:url value="/resources/images/favicon.png" />"  />
	    <title>quod</title>
	    <style type="text/css">
	       .content-footer{
	          margin-top: 10%;
	       }
	      
	    </style>
	</head>
	
	<body>
	
	<%@include file="fragment/header.jsp" %>
	
	    <div class="container">
	    <div  class="row">
	       <div class="col-lg-10 col-md-10 col-sm-11 col-xs-11 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1" >
	          <div class="jumbotron">
			  <h2>Farouk BEN GHARSSALLAH</h2>
			  <h4>Java, J2EE Intermediate Engineer [OCA8]</h4>
			  <p style="font-size: 15px;" > This project aims to collect worlwide stock data. It's in the alpha phase.
			    You can found the project in <a href="http://github.com/FaroukBENGHARSSALLAH/quod" target="_blank" >Github</a></p>
			  <p style="font-size: 15px;"> Phone : 00216-40 162 563 |  Mail: farouk.bengarssallah@gmail.com | 
			  Github : github.com/FaroukBENGHARSSALLAH | Blogger : faroukbengarssallah.blogspot.com  | 
			   Wordpress : faroukbengarssallah.wordpress.com</p>
			  
			 
			  <p><a class="btn btn-info btn-sm" href="mailto:farouk.bengarssallah@gmail.com" role="button">Email me </a></p>
			</div>
	       </div>
	    </div>
		 </div>
		 
	
		  
		 <%@include file="fragment/footer.jsp" %>
		  
		 <script   src="<c:url value="/resources/js/jquery.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript" ></script>
		
	</body>
	
</html>