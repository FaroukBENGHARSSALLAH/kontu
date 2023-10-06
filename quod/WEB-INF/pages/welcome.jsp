<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css">
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css">
	    <link href="<c:url value="/resources/images/favicon.png" />"  rel="icon"  type="image/x-icon"   >
	    <title>Quod</title>
	    <style type="text/css">
	       @media only screen and (orientation: portrait) and (max-width:1000px){
	               div#container{
	                       width: 95%;
	                       margin-top : 80px;
	                       }
	                
	              }
	              
	      @media only screen and (orientation: landscape) and (max-width:1000px){
	               div#container{
	                       width: 80%;
	                       margin-top : 2px;
	                       }
	                       
	                      padding-bottom: 50%; 
	                     
	                   }
	              }
	              
	      @media only screen and (orientation: landscape) and (min-width:1100px){
	               div#container{
	                       width: 1000px;
	                       }
	                       
	                
	              }
          
	    </style>
	</head>
<body>

     <%@include file="fragment/header.jsp" %>
	 
	 <div id="container" class="container-fluid" >
	    <div class="row">
		   <div class="panel panel-default"  >
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

	<%@include file="fragment/footer.jsp" %>
			
    <script type="text/javascript"  src="<c:url value="/resources/js/jquery.js" />" ></script>
    <script type="text/javascript"  src="<c:url value="/resources/js/bootstrap.js" />"  ></script>	
</body>
</html>