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
	
		<div class="container-fluid">
		     <div class="row">    
				       <div class="col-sm-8 col-md-8 col-xs-10 col-md-offset-2 col-sm-offset-2 col-xs-offset-1" > 
				           <div class="text-center"><h4 style="margin-top: 20%; font-style: italic;font-weight: 300;">quod</h4></div>
				           <div id="custom-search-input" class="">
				                          <form  action="<c:url value="/querystatement" />"  method="get" >
				                                 <div class="row">
				                                   
				                                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" >
				                                          <label   style="margin-left: 15px;" >Country</label>
				                                          <div class="col-md-2"  style="margin-left: 3px;margin-right: -44px;top: 26px;"  >
				                                              <select id="countries" style="width: 150px;">
				                                                <option value='argentina' selected >Argentina</option>
				                                                <option value='australia' >Australia</option>
				                                                <option value='austria' >Austria</option>
				                                                <option value='belguim'  >Belguim</option>
				                                                <option value='brazil' >Brazil</option>
				                                                <option value='canada' >Canada</option>
				                                                <option value='chile' >Chile</option>
				                                                <option value='china' >China</option>
				                                                <option value='colombia' >Colombia</option>
																<option value='czech-republic' >Czech Republic</option>
																<option value='denmark' >Denmark</option>
																<option value='estonia' >Estonia</option>
																<option value='finland' >Finland</option>
																<option value='france' >France</option>
																<option value='germany' >Germany</option>
																<option value='greece' >Greece</option>
																<option value='hong-kong' >Hong kong</option>
																<option value='hungary' >Hungary</option>
																<option value='iceland' >Iceland</option>
																<option value='india' >India</option>
																<option value='indonesia' >Indonesia</option>
																<option value='ireland' >Ireland</option>
																<option value='japan' >Japan</option>
						                                        <option value='united-kingdom' >United Kingdom</option>										
																<option value='united-states' >United States</option>
				                                         </select>
				                                          </div>
				                                     </div>
				                                      <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"  style="margin-left: 50px;margin-right: 50px;" >
				                                           <label  style="margin-left: 15px;"  >Exchange</label>
				                                           <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
				                                                <select  id="exchanges" style="width: 150px;"  >
				                                                     <c:forEach items="${exchanges}" var="exchange">
				                                                              <option value='${exchange}' >${exchange}</option>
				                                                     </c:forEach>
				                                               </select>
				                                          </div>
				                                      </div>
				                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"  style="margin-left: 85px;top: 16px;"   >
															 <div class="typeahead__container">
																        <div class="typeahead__field">
																	            <span class="typeahead__query">
																	                <input class="typeahead" id="cnt" name="company" placeholder="symbol"  onkeyup="this.value = this.value.toUpperCase();" autocomplete="off" type="search"   />
																	            </span>
																	            <span class="typeahead__button">
																	                <button type="submit" style="background-color: mintcream;">
																	                    <i class="typeahead__search-icon"></i>
																	                </button>
																	            </span>
															            </div>
															    </div>
												         </div>
												    </div>
						                            <div class="input-group input-group col-md-8 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-7 col-xs-offset-2" style="padding-top:5%" >
						                                  <label class="radio-inline"><input type="radio" name="type"  value="income"  checked="true" />income</label>
                                                          <label class="radio-inline"><input type="radio" name="type"   value="balance"     />balance sheet</label>
                                                          <label class="radio-inline"><input type="radio" name="type"   value="cash"      />cash flow</label> 
						                            </div>
						                             <div class="input-group col-md-8 col-md-offset-4 col-sm-8 col-sm-offset-4 col-xs-7 col-xs-offset-3" style="padding-top:5%" >
						                                  <label class="radio-inline"><input type="radio" name="period"  value="annual"  checked="true"     />annual</label>
                                                          <label class="radio-inline"><input type="radio" name="period"   value="quarter"         />quarter</label>
						                            </div>
				                            </form>
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
		 <script   src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"  type="text/javascript" ></script>
		  <script type="text/javascript"    >

					$(document).ready(function () {
						
						
						
						   $('#countries').on('change', function() {
							               $('#cnt').val("");
										   $.get('<c:url value="/company/exchanges" />', 
												  {country : $(this).val() }, 
												  function(data, status){
								       	               var html = '';
								       	               for(i in data){
								       	            	  html = html + "<option value='" + data[i] + "' " + ((i==0) ? " selected " : "") + " >" + data[i] 
								       	            	        + "</option>";
								       	                     }
								       	               $('#exchanges').html(html);
								       	           });
						         });
						   
						   
						   
						   
						   $('#exchanges').on('change', function(){
							               $('#cnt').val("");
										   $.get('<c:url value="/exchange" />', 
													  {exchange : $(this).val() 
											   });
					         });
						   
						   
							  $('#cnt').autocomplete({
							      source: '<c:url value="/companies" />'
							    });
				
  
                               <c:if test="${index_data != null}" >
                                        var name_0 = '${index_data[2].name}';
                                        var body_0 = '${index_data[2].change}';
                                        var perc_0 = '${index_data[2].percentage}';
                                        var price_0 = '${index_data[2].price}';
                                        
                                        var name_1 = '${index_data[1].name}';
                                        var body_1 = '${index_data[1].change}';
                                        var perc_1 = '${index_data[1].percentage}';
                                        var price_1 = '${index_data[1].price}';
                                        
                                        var name_2 = '${index_data[0].name}';
                                        var body_2 = '${index_data[0].change}';
                                        var perc_2 = '${index_data[0].percentage}';
                                        var price_2 = '${index_data[0].price}';
                                        
                                        var body = '';
                                        if(body_0.includes('-')){
                                                  body = "<p class='text-danger' >" + body_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p>';
                                                     }
                                        else{
                                        	      body = "<p class='text-success' >" + body_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p>';
                                                     }
									    $.gritter.add({
									    		  title: name_0,
									    		  text: body ,
									    		  class_name: 'gritter-light',
									    		  after_close: function(){
										    		           if(!price_1.includes('N/A')){
												    		        	 body = '';
									                                     if(body_1.includes('-')){
									                                                  body = "<p class='text-danger' >" + body_1 + '  (' + perc_1 + '%) </p><p>' + price_1 + '</p>';
									                                                     }
									                                     else{
									                                        	      body = "<p class='text-success' >" + body_1 + '  (' + perc_1 + '%) </p><p>' + price_1 + '</p>';
									                                                     }
														    			  $.gritter.add({
																    		  title: name_1,
																    		  text: body,
																    		  class_name: 'gritter-light',
																    		  after_close: function(){
															    		           if(!price_2.includes('N/A')){
																	    		        	 body = '';
														                                     if(body_2.includes('-')){
														                                                  body = "<p class='text-danger' >" + body_2 + '  (' + perc_2 + '%) </p><p>' + price_2 + '</p>';
														                                                     }
														                                     else{
														                                        	      body = "<p class='text-success' >" + body_2 + '  (' + perc_2 + '%) </p><p>' + price_2 + '</p>';
														                                                     }
																			    			  $.gritter.add({
																					    		  title: name_2,
																					    		  text: body,
																					    		  class_name: 'gritter-light'
																					    		  
																			    			  });
															    		                }
																		  }
																    		  
														    			  });
										    		                }
													  }
									    		});
                                   </c:if>
				
				         });



					  //]]>
		</script>
		
		
	</body>
	
</html>