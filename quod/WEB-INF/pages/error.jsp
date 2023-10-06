<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	    <link href="<c:url value="/resources/images/favicon.png" />"  rel="icon"  type="image/x-icon"   >
	    <title>Quod</title>
	</head>
<body>

	
     
	<div class="container"   style="margin-bottom: 21%;" >
				  <div class="row">
				    <div class="span12">
				      <div class="hero-unit center">
				               <h1><small><font face="Tahoma" color="red">Error </font></small></h1>
				               <br>
					          <h1>Company Financial data Not Found</h1>
					          <br />
					          <p>The page you requested could not be found, Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
					          <p><b>Or you could just press this neat little button</b></p>
					          <a type="button" href="<c:url value="/accounting" />" class="btn btn-large btn-default"><i class="icon-home icon-white"></i> Take Me Home</a>
				         </div>
				    </div>
				  </div>
		   </div>

	<%@include file="fragment/footer.jsp" %>
			
    <script type="text/javascript"  src="<c:url value="/resources/js/jquery.js" />" ></script>
    <script type="text/javascript"  src="<c:url value="/resources/js/bootstrap.js" />"  ></script>	
</body>
</html>