     <header >
		   <nav class="navbar navbar-default">
				  <div class="container-fluid">
				    <div class="navbar-header">
				      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
					              data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				      <a class="navbar-brand"  href="<c:url value="/welcome" />">Quod</a>
				    </div>
				
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				        <li  class="${view == 'accounting' ? 'active' : '' }"  >
				        <a href="<c:url value="/accounting" />" >Statement</a></li>
				        <li  class="${view == 'contact' ? 'active' : '' }"  >
				        <a href="<c:url value="/contact" />" >Contact</a></li>
				      </ul>
				      <ul class="nav navbar-nav navbar-right">
				         <c:url value="/j_spring_security_logout" var="logoutUrl" />
				         <li class="hidden-xs" ><a href="#"  >Welcome, admin</a></li>
				         <li><a href="javascript:logout()"  >logout</a></li>
				      </ul>
				    </div>
				  </div>
				</nav>
					<!-- csrt support -->
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" 
						name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script type="text/javascript"> 
					function logout() {
						document.getElementById("logoutForm").submit();
					}
				</script>
     </header>