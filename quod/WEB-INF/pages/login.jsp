<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"   type="text/css">
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css">
	    <link href="<c:url value="/resources/images/favicon.png" />"  rel="icon"  type="image/x-icon"   >
	    <title>Quod</title>
	    <style type="text/css">
	       @media only screen and (orientation: portrait) and (max-width:1000px){
	               div#row{
	                       padding-top:20%;
	                       }
	              }
	              
	      @media only screen and (orientation: landscape) and (max-width:1000px){
	               div#row{
	                       padding-top:10%;
	                       }
	              }
	              
	      @media only screen and (orientation: landscape) and (min-width:1100px){
	               div#row{
	                       padding-top:20%;
	                       }
	              }
          
	    </style>
	</head>
<body onload='document.loginForm.login.focus();'>


		<div class="container-fluid">
			    <div id="row" class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-12">
			    		<div class="panel panel-default">
						  	<div class="panel-heading">
						    	<h3 class="panel-title text-center" >Provide your credentials</h3>
						    	<c:if test="${not empty error}">
					                    <div class="text-center" style="padding-top: 10px;"> 
					                        <span style="color:red;" class="text-center" >${error}</span>
					                    </div>
								</c:if>
								<c:if test="${not empty message}">
									<div class="text-center" style="padding-top: 10px;">
			                           <span style="color:green;" class="text-center" >${message}</span>
			                        </div>
								</c:if>
						 	</div>
						  	<div class="panel-body">
						    	<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST' >
					                    <fieldset>
								    		<div class="form-group">
								    			<input class="form-control" placeholder="admin" name="login" type="text" value="" />
								    		</div>
								    		<div class="form-group">
								    			<input class="form-control" placeholder="admin" name="password" type="password" value="" />
								    		</div>
								    		
								    		<button class="btn btn-lg btn-info btn-block" type="submit" >
								    		    connect <i class="glyphicon glyphicon-chevron-right" ></i>
								    		</button>
								    	</fieldset>
								    	<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
						      	</form>
						    </div>
						</div>
					</div>
				</div>
			</div> 
			
			<%@include file="fragment/footer.jsp" %>
			
    <script type="text/javascript"  src="<c:url value="/resources/js/jquery.js" />" ></script>
    <script type="text/javascript"  src="<c:url value="/resources/js/bootstrap.js" />"  ></script>
</body>
</html>