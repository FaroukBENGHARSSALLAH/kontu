<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	
		<title>quod</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Motive Mag Responsive web template, 
		                               Bootstrap Web Templates, Flat Web Templates, 
		                               Andriod Compatible web template, 
		                               Smartphone Compatible web template, 
		                               free webdesigns for Nokia, Samsung, 
		                               LG, SonyErricsson, Motorola web design" />
		                               
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"   type="text/css" />
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
		<link href="<c:url value="/resources/css/uikit.min.css" />" rel="stylesheet"  type="text/css"  />
		<link href="<c:url value="/resources/css/dataTables.uikit.min.css" />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/style.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/typeahead.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/gritter.css" />" rel="stylesheet"  type="text/css"/>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		 
		  <link rel="icon"  type="image/x-icon"  href="<c:url value="/resources/images/favicon.png" />"  />
		  <style type="text/css">
	         @media only screen and (orientation: landscape) and (max-width:1000px){
               .hidden-xs{
	                      display: block !important;
	                }
	                
	                .container-fluid {
	                    padding-right: 30px !important;
	                }
              }
		  </style>
		 
	</head>
	
	<body>
	
	       <%@include file="fragment/header.jsp" %>
		 
			<div class="container">
						<div class="row">
							     <section class="content">
								         <div class="row">
									             <div id="custom-search-input" class="col-sm-8 col-md-8 col-xs-10 col-md-offset-2 col-sm-offset-2 col-xs-offset-1 search-container-result">
									                          <form action="<c:url value="/querystatement" />"  method="get" >
									                                 <div class="row">
									                                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" >
										                                          <label   style="margin-left: 15px;" >Country</label>
										                                          <div class="col-md-2"  style="margin-left: 3px;margin-right: -44px;top: 26px;"  >
										                                              <select id="countries" style="width: 150px;">
										                                                <option value='argentina' ${country == 'argentina' ? 'selected' : '' } >Argentina</option>
										                                                <option value='australia'  ${country == 'australia' ? 'selected' : '' } >Australia</option>
										                                                <option value='austria'  ${country == 'austria' ? 'selected' : '' } >Austria</option>
										                                                <option value='belguim'  ${country == 'belguim' ? 'selected' : '' } >Belguim</option>
										                                                <option value='brazil' ${country == 'brazil' ? 'selected' : '' } >Brazil</option>
										                                                <option value='canada'  ${country == 'canada' ? 'selected' : '' } >Canada</option>
										                                                <option value='chile' ${country == 'chile' ? 'selected' : '' } >Chile</option>
										                                                <option value='china' ${country == 'china' ? 'selected' : '' } >China</option>
										                                                <option value='colombia' ${country == 'colombia' ? 'selected' : '' } >Colombia</option>
																						<option value='czech-republic'  ${country == 'czech-republic' ? 'selected' : '' } >Czech Republic</option>
																						<option value='denmark' ${country == 'denmark' ? 'selected' : '' } >Denmark</option>
																						<option value='estonia' ${country == 'estonia' ? 'selected' : '' } >Estonia</option>
																						<option value='finland' ${country == 'finland' ? 'selected' : '' } >Finland</option>
																						<option value='france' ${country == 'france' ? 'selected' : '' } >France</option>
																						<option value='germany' ${country == 'germany' ? 'selected' : '' } >Germany</option>
																						<option value='greece' ${country == 'greece' ? 'selected' : '' } >Greece</option>
																						<option value='hong-kong' ${country == 'hong-kong' ? 'selected' : '' } >Hong kong</option>
																						<option value='hungary' ${country == 'hungary' ? 'selected' : '' } >Hungary</option>
																						<option value='iceland' ${country == 'iceland' ? 'selected' : '' } >Iceland</option>
																						<option value='india' ${country == 'india' ? 'selected' : '' } >India</option>
																						<option value='indonesia' ${country == 'indonesia' ? 'selected' : '' } >Indonesia</option>
																						<option value='ireland' ${country == 'ireland' ? 'selected' : '' } >Ireland</option>
																						<option value='japan' ${country == 'japan' ? 'selected' : '' } >Japan</option>
												                                        <option value='united-kingdom' ${country == 'united-kingdom' ? 'selected' : '' } >United Kingdom</option>										
																						<option value='united-states' ${country == 'united-states' ? 'selected' : '' } >United States</option>
										                                         </select>
										                                          </div>
										                                     </div>
										                                      <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"  style="margin-left: 50px;margin-right: 50px;" >
										                                           <label  style="margin-left: 15px;"  >Exchange</label>
										                                           <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
										                                                <select  id="exchanges" style="width: 150px;"  >
										                                                     <c:forEach items="${exchanges}" var="exch">
										                                                              <option value='${exch}'  ${exch == exchange ? 'selected' : '' } >${exch}</option>
										                                                     </c:forEach>
										                                               </select>
										                                          </div>
										                                      </div>
										                                      <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"  style="margin-left: 85px;top: 16px;"   >
														                           <div class="typeahead__container">
																					        <div class="typeahead__field">
																						            <span class="typeahead__query">
																						                <input class="typeahead" id="cnt"  name="company" placeholder="symbol" autocomplete="off" type="search"  value="${company}" />
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
												                            <div class="input-group input-group col-md-8 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-7 col-xs-offset-3" style="padding-top:5%" >
												                                  <label class="radio-inline"><input type="radio" name="type"  value="income"  <c:if test="${type =='income'}" >checked="true"</c:if> />income</label>
						                                                          <label class="radio-inline"><input type="radio" name="type"   value="balance" <c:if test="${type =='balance'}" >checked="true"</c:if>     />balance sheet</label>
						                                                          <label  class="radio-inline"><input type="radio" name="type"   value="cash"  <c:if test="${type =='cash'}" >checked="true"</c:if>    />cash flow</label> 
												                            </div>
												                            <div class="input-group col-md-8 col-md-offset-4 col-sm-8 col-sm-offset-4 col-xs-7 col-xs-offset-3" style="padding-top:5%" >
												                                  <label class="radio-inline"><input type="radio" name="period"  value="annual"  <c:if test="${period =='annual'}" >checked="true"</c:if>     />annual</label>
						                                                          <label class="radio-inline"><input type="radio" name="period"   value="quarter" <c:if test="${period =='quarter'}" >checked="true"</c:if>     />quarter</label>
												                            </div>
									                            </form>
									             </div>
									         </div>
                                     </section>
             
             
									<section class="content spacer">
										
										<div class="col-md-8 col-md-offset-2 result-container">
											<div class="panel panel-default">
												<div class="panel-body">
												    <span>  
												           <a href="<c:url value="/pdfexport" />" class="btn btn-default btn-xs pull-right" style="margin-left: 5px;" >pdf</a>
												           <a href="<c:url value="/xlsxexport" />" class="btn btn-default btn-xs pull-right" style="margin-right: 5px; margin-left: 20px;" >xls</a>
												    </span>
													<div class="table-responsive">
														<table class="uk-table uk-table-hover uk-table-striped" width="100%" cellspacing="0" id="statement">
														        <thead>
														            <tr>
														                 <c:set var="j" value="0" ></c:set>
														                <th>field</th>
														                <c:forEach items="${statement.periodList}" var="period"   >
														                <th <c:if test="${j eq 0 || j eq 1 || j eq 2}"> class="hidden-xs"</c:if>>${period.title}</th>
														                <c:set var="j" value="${j + 1}" ></c:set>
														                </c:forEach>
														            </tr>
														        </thead>
															    <tbody>
															        <c:set var="i" value="0" ></c:set>
															        <c:forEach items="${keys}" var="key" varStatus="p" >
														                <tr>
														                <td>${key}</td>
														                <c:forEach items="${statement.periodList}" var="period" >
														                <td <c:if test="${i eq 0 || i eq 1 || i eq 2}"> class="hidden-xs"</c:if> >${period.data.get(key)}</td>
														                <c:set var="i" value="${i + 1}" ></c:set>
														                </c:forEach>
														                 <c:if test="${i eq 5}"><c:set var="i" value="0" ></c:set></c:if>
														            </tr>
														            </c:forEach>
														        </tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</section>
									<section class="content spacer">
									        <div class="row">
												   <div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-body">
														      <canvas id="chart-canvas-gross"></canvas>
														</div>
													</div>
												   </div>
													<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-body">
														      <canvas id="chart-canvas-revenue"></canvas>
														</div>
													</div>
												   </div>
										  </div>
										     <div class="row">
												   <div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-body">
														      <canvas id="chart-canvas-income"></canvas>
														</div>
													</div>
												   </div>
													<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-body">
														      <canvas id="chart-canvas-expense"></canvas>
														</div>
													</div>
												   </div>
										  </div>
									</section>
						</div>
		</div>
		
		
		<%@include file="fragment/footer.jsp" %>

		  
		 <script   src="<c:url value="/resources/js/jquery.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/jqtable.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/bootstraptable.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/datatabletheme.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/typeahead.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/autocomplete.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/gritter.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/chartjs.js" />" type="text/javascript"  ></script>
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
				
								$('.star').on('click', function () {
							                   $(this).toggleClass('star-checked');
							                 });
							
							    $('.ckbox label').on('click', function () {
							                  $(this).parents('tr').toggleClass('selected');
							                });
							
							    $('.btn-filter').on('click', function () {
										      var $target = $(this).data('target');
										      if ($target != 'all') {
										                       $('.table tr').css('display', 'none');
										                       $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
										                                } 
				                              else {
										                       $('.table tr').css('display', 'none').fadeIn('slow');
										                                }
							                });

							    $('#statement').DataTable({
							        "pagingType": "numbers",
							        "bSort" : false
							          });

							    <c:if test="${stock != null}" >
                                        
                                        var name_0 = '${stock.name}';
                                        var change_0 = '${stock.change}';
                                        var perc_0 = '${stock.percentage}';
                                        var price_0 = '${stock.price}';
                                        var beta_0 = '${stock.beta}';
                                        
                                        var body = '';
                                        if(change_0.includes('-')){
                                                  body = "Value : <p class='text-danger' >" + change_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p><p>Beta : ' +  beta_0 + '</p>';
                                                     }
                                        else{
                                        	      body = "Value : <p class='text-success' >" + change_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p><p>Beta : ' +  beta_0 + '</p>';
                                                     }
									    $.gritter.add({
									    		  title: name_0,
									    		  text: body ,
									    		  class_name: 'gritter-light',
									    		  after_close: function(e){
									    			  $.gritter.add({
											    		  title: 'notification',
											    		  position: 'top-left',
											    		  text: 'This is a demo !!. For real a case scenario, you can contact me; Check contact page' ,
											    		  class_name: 'gritter-light'
											    		});
													}
									    		});
                                  </c:if>
                                  
                                  
                                 
                                  
                                 


				    });


                  if('${type}' === 'income'){

                	              {
	                                var data = [<c:forEach items="${list}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
	                                var ratio_label = [];
	                                var ratio_value = [];
	                                for(i=0; i<data.length; i++){
	                                        var rat_data = data[i].split("_");
	                                        ratio_label.push(rat_data[0]);
	                                        ratio_value.push(rat_data[1]);
	                                    }
                                
                                    
                                var config = {
                                        type: 'line',
                                        data: {
                                            labels: ratio_label,
                                            datasets: [{
                                                label:'Period',
                                                backgroundColor: '#3366ff',
                                                borderColor: '#3366ff',
                                                data: ratio_value,
                                                fill: false,
                                            }]
                                        },
                                        options: {
                                            responsive: true,
                                            title:{
                                                display:true,
                                                text:'Gross Income'
                                            },
                                            tooltips: {
                                                mode: 'index',
                                                intersect: false,
                                            },
                                            hover: {
                                                mode: 'nearest',
                                                intersect: true
                                            },
                                            scales: {
                                                xAxes: [{
                                                    display: true,
                                                    scaleLabel: {
                                                        display: true,
                                                        labelString: 'Period'
                                                    }
                                                }],
                                                yAxes: [{
                                                    display: true,
                                                    scaleLabel: {
                                                        display: true,
                                                        labelString: 'Value',
                                                        ticks: {beginAtZero:true,max:100}
                                                    }
                                                }]
                                            }
                                        }
                                    };

                                        var ctx = document.getElementById("chart-canvas-gross").getContext("2d");
                                        window.myLine = new Chart(ctx, config);

                                        }



                	              {
                	            	  var data = [<c:forEach items="${list2}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
  	                                var ratio_label = [];
  	                                var ratio_value = [];
  	                                for(i=0; i<data.length; i++){
  	                                        var rat_data = data[i].split("_");
  	                                        ratio_label.push(rat_data[0]);
  	                                        ratio_value.push(rat_data[1]);
  	                                    }
                                  
                                      
                                  var config = {
                                          type: 'line',
                                          data: {
                                              labels: ratio_label,
                                              datasets: [{
                                                  label:'Period',
                                                  backgroundColor: '#8c1aff',
                                                  borderColor: '#8c1aff',
                                                  data: ratio_value,
                                                  fill: false,
                                              }]
                                          },
                                          options: {
                                              responsive: true,
                                              title:{
                                                  display:true,
                                                  text:'Sales/Revenue'
                                              },
                                              tooltips: {
                                                  mode: 'index',
                                                  intersect: false,
                                              },
                                              hover: {
                                                  mode: 'nearest',
                                                  intersect: true
                                              },
                                              scales: {
                                                  xAxes: [{
                                                      display: true,
                                                      scaleLabel: {
                                                          display: true,
                                                          labelString: 'Period'
                                                      }
                                                  }],
                                                  yAxes: [{
                                                      display: true,
                                                      scaleLabel: {
                                                          display: true,
                                                          labelString: 'Value',
                                                          ticks: {beginAtZero:true,max:100}
                                                      }
                                                  }]
                                              }
                                          }
                                      };

                                          var ctx = document.getElementById("chart-canvas-revenue").getContext("2d");
                                          window.myLineTwo = new Chart(ctx, config);

                                          }


                	              {
                	            	  var data = [<c:forEach items="${list3}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
    	                                var ratio_label = [];
    	                                var ratio_value = [];
    	                                for(i=0; i<data.length; i++){
    	                                        var rat_data = data[i].split("_");
    	                                        ratio_label.push(rat_data[0]);
    	                                        ratio_value.push(rat_data[1]);
    	                                    }
                                    
                                        
                                    var config = {
                                            type: 'line',
                                            data: {
                                                labels: ratio_label,
                                                datasets: [{
                                                    label:'Period',
                                                    backgroundColor: '#ff0066',
                                                    borderColor: '#ff0066',
                                                    data: ratio_value,
                                                    fill: false,
                                                }]
                                            },
                                            options: {
                                                responsive: true,
                                                title:{
                                                    display:true,
                                                    text:'Net Income'
                                                },
                                                tooltips: {
                                                    mode: 'index',
                                                    intersect: false,
                                                },
                                                hover: {
                                                    mode: 'nearest',
                                                    intersect: true
                                                },
                                                scales: {
                                                    xAxes: [{
                                                        display: true,
                                                        scaleLabel: {
                                                            display: true,
                                                            labelString: 'Period'
                                                        }
                                                    }],
                                                    yAxes: [{
                                                        display: true,
                                                        scaleLabel: {
                                                            display: true,
                                                            labelString: 'Value',
                                                            ticks: {beginAtZero:true,max:100}
                                                        }
                                                    }]
                                                }
                                            }
                                        };

                                            var ctx = document.getElementById("chart-canvas-income").getContext("2d");
                                            window.myLineTwo = new Chart(ctx, config);

                                            }



                	              {
                	            	  var data = [<c:forEach items="${list4}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
    	                                var ratio_label = [];
    	                                var ratio_value = [];
    	                                for(i=0; i<data.length; i++){
    	                                        var rat_data = data[i].split("_");
    	                                        ratio_label.push(rat_data[0]);
    	                                        ratio_value.push(rat_data[1]);
    	                                    }
                                    
                                        
                                    var config = {
                                            type: 'line',
                                            data: {
                                                labels: ratio_label,
                                                datasets: [{
                                                    label:'Period',
                                                    backgroundColor: '#666699',
                                                    borderColor: '#666699',
                                                    data: ratio_value,
                                                    fill: false,
                                                }]
                                            },
                                            options: {
                                                responsive: true,
                                                title:{
                                                    display:true,
                                                    text:'Gross Interest Expense'
                                                },
                                                tooltips: {
                                                    mode: 'index',
                                                    intersect: false,
                                                },
                                                hover: {
                                                    mode: 'nearest',
                                                    intersect: true
                                                },
                                                scales: {
                                                    xAxes: [{
                                                        display: true,
                                                        scaleLabel: {
                                                            display: true,
                                                            labelString: 'Period'
                                                        }
                                                    }],
                                                    yAxes: [{
                                                        display: true,
                                                        scaleLabel: {
                                                            display: true,
                                                            labelString: 'Value',
                                                            ticks: {beginAtZero:true,max:100}
                                                        }
                                                    }]
                                                }
                                            }
                                        };

                                            var ctx = document.getElementById("chart-canvas-expense").getContext("2d");
                                            window.myLineTwo = new Chart(ctx, config);

                                            }
                  }  





                  else if('${type}' === 'balance'){

    	              {
    	            	  var data = [<c:forEach items="${list}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                        var ratio_label = [];
                        var ratio_value = [];
                        for(i=0; i<data.length; i++){
                                var rat_data = data[i].split("_");
                                ratio_label.push(rat_data[0]);
                                ratio_value.push(rat_data[1]);
                            }
                    
                        
                    var config = {
                            type: 'line',
                            data: {
                                labels: ratio_label,
                                datasets: [{
                                    label:'Period',
                                    backgroundColor: '#3366ff',
                                    borderColor: '#3366ff',
                                    data: ratio_value,
                                    fill: false,
                                }]
                            },
                            options: {
                                responsive: true,
                                title:{
                                    display:true,
                                    text:'Current Assets'
                                },
                                tooltips: {
                                    mode: 'index',
                                    intersect: false,
                                },
                                hover: {
                                    mode: 'nearest',
                                    intersect: true
                                },
                                scales: {
                                    xAxes: [{
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: 'Period'
                                        }
                                    }],
                                    yAxes: [{
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: 'Value',
                                            ticks: {beginAtZero:true,max:100}
                                        }
                                    }]
                                }
                            }
                        };

                            var ctx = document.getElementById("chart-canvas-gross").getContext("2d");
                            window.myLine = new Chart(ctx, config);

                            }



    	              {
    	            	  var data = [<c:forEach items="${list2}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                          var ratio_label = [];
                          var ratio_value = [];
                          for(i=0; i<data.length; i++){
                                  var rat_data = data[i].split("_");
                                  ratio_label.push(rat_data[0]);
                                  ratio_value.push(rat_data[1]);
                              }
                      
                          
                      var config = {
                              type: 'line',
                              data: {
                                  labels: ratio_label,
                                  datasets: [{
                                      label:'Period',
                                      backgroundColor: '#8c1aff',
                                      borderColor: '#8c1aff',
                                      data: ratio_value,
                                      fill: false,
                                  }]
                              },
                              options: {
                                  responsive: true,
                                  title:{
                                      display:true,
                                      text:'Current Liabilities'
                                  },
                                  tooltips: {
                                      mode: 'index',
                                      intersect: false,
                                  },
                                  hover: {
                                      mode: 'nearest',
                                      intersect: true
                                  },
                                  scales: {
                                      xAxes: [{
                                          display: true,
                                          scaleLabel: {
                                              display: true,
                                              labelString: 'Period'
                                          }
                                      }],
                                      yAxes: [{
                                          display: true,
                                          scaleLabel: {
                                              display: true,
                                              labelString: 'Value',
                                              ticks: {beginAtZero:true,max:100}
                                          }
                                      }]
                                  }
                              }
                          };

                              var ctx = document.getElementById("chart-canvas-revenue").getContext("2d");
                              window.myLineTwo = new Chart(ctx, config);

                              }


    	              {
    	            	  var data = [<c:forEach items="${list3}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                            var ratio_label = [];
                            var ratio_value = [];
                            for(i=0; i<data.length; i++){
                                    var rat_data = data[i].split("_");
                                    ratio_label.push(rat_data[0]);
                                    ratio_value.push(rat_data[1]);
                                }
                        
                            
                        var config = {
                                type: 'line',
                                data: {
                                    labels: ratio_label,
                                    datasets: [{
                                        label:'Period',
                                        backgroundColor: '#ff0066',
                                        borderColor: '#ff0066',
                                        data: ratio_value,
                                        fill: false,
                                    }]
                                },
                                options: {
                                    responsive: true,
                                    title:{
                                        display:true,
                                        text:'Common Equity'
                                    },
                                    tooltips: {
                                        mode: 'index',
                                        intersect: false,
                                    },
                                    hover: {
                                        mode: 'nearest',
                                        intersect: true
                                    },
                                    scales: {
                                        xAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Period'
                                            }
                                        }],
                                        yAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Value',
                                                ticks: {beginAtZero:true,max:100}
                                            }
                                        }]
                                    }
                                }
                            };

                                var ctx = document.getElementById("chart-canvas-income").getContext("2d");
                                window.myLineTwo = new Chart(ctx, config);

                                }



    	              {
    	            	  var data = [<c:forEach items="${list4}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                            var ratio_label = [];
                            var ratio_value = [];
                            for(i=0; i<data.length; i++){
                                    var rat_data = data[i].split("_");
                                    ratio_label.push(rat_data[0]);
                                    ratio_value.push(rat_data[1]);
                                }
                        
                            
                        var config = {
                                type: 'line',
                                data: {
                                    labels: ratio_label,
                                    datasets: [{
                                        label:'Period',
                                        backgroundColor: '#666699',
                                        borderColor: '#666699',
                                        data: ratio_value,
                                        fill: false,
                                    }]
                                },
                                options: {
                                    responsive: true,
                                    title:{
                                        display:true,
                                        text:'Accounts Receivables'
                                    },
                                    tooltips: {
                                        mode: 'index',
                                        intersect: false,
                                    },
                                    hover: {
                                        mode: 'nearest',
                                        intersect: true
                                    },
                                    scales: {
                                        xAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Period'
                                            }
                                        }],
                                        yAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Value',
                                                ticks: {beginAtZero:true,max:100}
                                            }
                                        }]
                                    }
                                }
                            };

                                var ctx = document.getElementById("chart-canvas-expense").getContext("2d");
                                window.myLineTwo = new Chart(ctx, config);

                                }
      } 


          else if('${type}' === 'cash'){

    	              {
    	            	  var data = [<c:forEach items="${list}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                        var ratio_label = [];
                        var ratio_value = [];
                        for(i=0; i<data.length; i++){
                                var rat_data = data[i].split("_");
                                ratio_label.push(rat_data[0]);
                                ratio_value.push(rat_data[1]);
                            }
                    
                        
                    var config = {
                            type: 'line',
                            data: {
                                labels: ratio_label,
                                datasets: [{
                                    label:'Period',
                                    backgroundColor: '#3366ff',
                                    borderColor: '#3366ff',
                                    data: ratio_value,
                                    fill: false,
                                }]
                            },
                            options: {
                                responsive: true,
                                title:{
                                    display:true,
                                    text:'Depreciation/Depletion'
                                },
                                tooltips: {
                                    mode: 'index',
                                    intersect: false,
                                },
                                hover: {
                                    mode: 'nearest',
                                    intersect: true
                                },
                                scales: {
                                    xAxes: [{
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: 'Period'
                                        }
                                    }],
                                    yAxes: [{
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: 'Value',
                                            ticks: {beginAtZero:true,max:100}
                                        }
                                    }]
                                }
                            }
                        };

                            var ctx = document.getElementById("chart-canvas-gross").getContext("2d");
                            window.myLine = new Chart(ctx, config);

                            }



    	              {
    	            	  var data = [<c:forEach items="${list2}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                          var ratio_label = [];
                          var ratio_value = [];
                          for(i=0; i<data.length; i++){
                                  var rat_data = data[i].split("_");
                                  ratio_label.push(rat_data[0]);
                                  ratio_value.push(rat_data[1]);
                              }
                      
                          
                      var config = {
                              type: 'line',
                              data: {
                                  labels: ratio_label,
                                  datasets: [{
                                      label:'Period',
                                      backgroundColor: '#8c1aff',
                                      borderColor: '#8c1aff',
                                      data: ratio_value,
                                      fill: false,
                                  }]
                              },
                              options: {
                                  responsive: true,
                                  title:{
                                      display:true,
                                      text:'Issuance of Long-Term Debt'
                                  },
                                  tooltips: {
                                      mode: 'index',
                                      intersect: false,
                                  },
                                  hover: {
                                      mode: 'nearest',
                                      intersect: true
                                  },
                                  scales: {
                                      xAxes: [{
                                          display: true,
                                          scaleLabel: {
                                              display: true,
                                              labelString: 'Period'
                                          }
                                      }],
                                      yAxes: [{
                                          display: true,
                                          scaleLabel: {
                                              display: true,
                                              labelString: 'Value',
                                              ticks: {beginAtZero:true,max:100}
                                          }
                                      }]
                                  }
                              }
                          };

                              var ctx = document.getElementById("chart-canvas-revenue").getContext("2d");
                              window.myLineTwo = new Chart(ctx, config);

                              }


    	              {
    	            	  var data = [<c:forEach items="${list3}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                            var ratio_label = [];
                            var ratio_value = [];
                            for(i=0; i<data.length; i++){
                                    var rat_data = data[i].split("_");
                                    ratio_label.push(rat_data[0]);
                                    ratio_value.push(rat_data[1]);
                                }
                        
                            
                        var config = {
                                type: 'line',
                                data: {
                                    labels: ratio_label,
                                    datasets: [{
                                        label:'Period',
                                        backgroundColor: '#ff0066',
                                        borderColor: '#ff0066',
                                        data: ratio_value,
                                        fill: false,
                                    }]
                                },
                                options: {
                                    responsive: true,
                                    title:{
                                        display:true,
                                        text:'Free Cash Flow'
                                    },
                                    tooltips: {
                                        mode: 'index',
                                        intersect: false,
                                    },
                                    hover: {
                                        mode: 'nearest',
                                        intersect: true
                                    },
                                    scales: {
                                        xAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Period'
                                            }
                                        }],
                                        yAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Value',
                                                ticks: {beginAtZero:true,max:100}
                                            }
                                        }]
                                    }
                                }
                            };

                                var ctx = document.getElementById("chart-canvas-income").getContext("2d");
                                window.myLineTwo = new Chart(ctx, config);

                                }



    	              {
    	            	  var data = [<c:forEach items="${list4}" var="l" varStatus="s" >'${l}'<c:if test="${not s.last}">,</c:if></c:forEach>];
                            var ratio_label = [];
                            var ratio_value = [];
                            for(i=0; i<data.length; i++){
                                    var rat_data = data[i].split("_");
                                    ratio_label.push(rat_data[0]);
                                    ratio_value.push(rat_data[1]);
                                }
                        
                            
                        var config = {
                                type: 'line',
                                data: {
                                    labels: ratio_label,
                                    datasets: [{
                                        label:'Period',
                                        backgroundColor: '#666699',
                                        borderColor: '#666699',
                                        data: ratio_value,
                                        fill: false,
                                    }]
                                },
                                options: {
                                    responsive: true,
                                    title:{
                                        display:true,
                                        text:'Deferred Taxes & Investment Tax Credit'
                                    },
                                    tooltips: {
                                        mode: 'index',
                                        intersect: false,
                                    },
                                    hover: {
                                        mode: 'nearest',
                                        intersect: true
                                    },
                                    scales: {
                                        xAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Period'
                                            }
                                        }],
                                        yAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Value',
                                                ticks: {beginAtZero:true,max:100}
                                            }
                                        }]
                                    }
                                }
                            };

                                var ctx = document.getElementById("chart-canvas-expense").getContext("2d");
                                window.myLineTwo = new Chart(ctx, config);

                                }
            }                                               
				
				      



					 
		</script>
					 		
	</body>
	
</html>