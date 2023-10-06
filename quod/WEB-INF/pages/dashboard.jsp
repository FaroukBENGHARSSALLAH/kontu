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
		<link rel="icon"  type="image/x-icon"  href="<c:url value="/resources/images/favicon.png" />"  />
	    <title>quod</title>
	    <style type="text/css">
	       .content-footer{
	          margin-top: 10%;
	       }
	       @media only screen and (orientation: portrait) and (max-width:1000px){
	              
	              }
	              
	      @media only screen and (orientation: landscape) and (max-width:1000px){
	              
	              }
	              
	      @media only screen and (orientation: landscape) and (min-width:1100px){
	              
	              }
	    </style>
	</head>
	
	<body>
	
	<%@include file="fragment/header.jsp" %>
	
		<div class="container">
		    <div class="row">
	             <div id="custom-search-input" class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 search-container-result">
                      <form action="<c:url value="/querycompanyt" />"  method="get" >
                          <div class="typeahead__container">
						        <div class="typeahead__field">
							            <span class="typeahead__query">
							                <input class="js-typeahead-country_v1" name="company" placeholder="symbol" autocomplete="off" type="search"  th:value="${company}" />
							            </span>
							            <span class="typeahead__button">
							                <button type="submit" style="background-color: mintcream;">
							                    <i class="typeahead__search-icon"></i>
							                </button>
							            </span>
					            </div>
					    </div>
                        </form>
	             </div>
	         </div>
	         <hr>
	          <div class="row">
	             <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                <div class="panel panel-default" >
	                    <div class="panel-heading" >${stock.name}</div>
	                    <div class="panel-body" >
	                           <div class="row">
	                               <div  class="col-md-3 col-lg-3">
	                            <td><label class="label label-success">symbol</label></td> : <td>${stock.symbol}</td>
	                            <td><label class="label label-success">price</label></td> : <td>${stock.price} $</td>
	                            <td><label class="label label-success">change</label></td> : <td>${stock.change}</td>
	                             <td><label class="label label-success">percentage</label></td> : <td>${stock.percentage} % </td>
	                           </div>
	                           <div  class="col-md-3 col-lg-3">
	                            <c:if test="stock.bookValuePerShare != 'null">
	                            <td><label class="label label-success">Book Value Per Share</label></td> : <td>${stock.bookValuePerShare}</td>
	                            </c:if>
	                            <c:if test="stock.EBITDA != 'null">
	                            <td><label class="label label-success">EBITDA</label></label></td> : <td>${stock.EBITDA}</td>
	                            </c:if>
	                           </div>
	                           <div  class="col-md-3 col-lg-3">
	                           <c:if test="stock.eps != 'null">
	                            <td><label class="label label-success">EPS</label></td> : <td>${stock.eps} $</td>
	                            </c:if>
	                            <c:if test="stock.epsEstimateCurrentYear != 'null">
	                            <td><label class="label label-success">EPS Estimate Current Year</label> </td> : <td>${stock.epsEstimateCurrentYear} $</td>
	                             </c:if>
	                            <c:if test="stock.epsEstimateNextQuarters != 'null">
	                            <td><label class="label label-success">EPS Estimate Next Quarter</label></td> : <td>${stock.epsEstimateNextQuarter} $</td><br>
	                             </c:if>
	                             </div></div>
	                              <div class="row">
	                           <div  class="col-md-3 col-lg-3">
	                           <c:if test="stock.marketCap != 'null">
	                            <td><label class="label label-success">Market Capital</label></td> : <td>${stock.marketCap}</td><br>
	                             </c:if>
	                            <c:if test="stock.oneYearTargetPrice != 'null">
	                            <td><label class="label label-success">One Year Target Price</label></td> : <td>${stock.oneYearTargetPrice}</td><br>
	                           </c:if>
	                           <c:if test="stock.pe != 'null">
	                            <td><label class="label label-success">Pe</label></td> : <td>${stock.pe}</td><br>
	                            </c:if>
	                            </div>
	                           <div  class="col-md-3 col-lg-3">
	                           <c:if test="stock.peg != 'null">
	                            <td><label class="label label-success">Peg</label></td> : <td>${stock.peg}</td><br>
	                            </c:if>
	                           <c:if test="stock.priceBook != 'null">
	                            <td><label class="label label-success">Book Price</label></td> : <td>${stock.priceBook}</td><br>
	                             </c:if>
	                            <c:if test="stock.priceSales != 'null">
	                            <td><label class="label label-success">Price Sales</label></td> : <td>${stock.priceSales}</td><br>
	                             </c:if>
	                            </div>
	                           <div  class="col-md-3 col-lg-3">
	                            <c:if test="stock.oneYearTargetPrice != 'null">
	                            <td><label class="label label-success">One Year Target Price</label></td> : <td>${stock.oneYearTargetPrice}</td><br>
	                            </c:if>
	                            <c:if test="stock.revenue != 'null">
	                            <td><label class="label label-success">Revenue</label></td> : <td>${stock.revenue}</td><br>
	                            </c:if>
	                            </div>
	                           <div  class="col-md-3 col-lg-3">
	                            <c:if test="stock.roe != 'null">
	                            <td><label class="label label-success">Roe</label></td> : <td>${stock.roe}</td><br>
	                             </c:if>
	                            <c:if test="stock.shortRatio != 'null">
	                            <td><label class="label label-success">Short Ratio</label></td> : <td>${stock.shortRatio}</td><br>
	                             </c:if>
	                       </div></div>
	                    </div>
	                </div> 
	             </div>
		 </div>
		 
		 </div>
	
		  
		 <%@include file="fragment/footer.jsp" %>
		  
		 <script   src="<c:url value="/resources/js/jquery.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/typeahead.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/autocomplete.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/gritter.js" />" type="text/javascript"  ></script>
		 
		  <script type="text/javascript"    >

					$(document).ready(function () {
				
  
				
				
				         });



		</script>
		
		
	</body>
	
</html>