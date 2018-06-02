<!DOCTYPE html>
<html lang="en" dir="ltr">
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	  <title>Parking Pass Details</title>
	  <link rel="stylesheet" href="css/bootstrap.min.css">
	 
	</head>
	<body>
		<%@ page import="java.sql.ResultSet, java.util.List, java.sql.Date, java.lang.String, java.lang.Boolean"%>
		<%@ page import="com.sgrh.dbo.ParkingDbo, com.sgrh.entities.Category, com.sgrh.entities.ParkingDetails" %>
		<%!	
			// declaration of variables.
			int sNo = 0;
			String name = "";
			String empcode = "";
			String passNo = "";
			String mNo = "";
			String category = "";
			String address = "";
			String cModel = "";
			String cMake = "";
			String cReg = "";
			String issueDate = "";
			String validDate = "";
			
			String rowCount = "";
			String colCount = "";
			String passColCount = "";
			
			List<String> categoryList = null;
			//ResultSet set = null;
			//CategoryDetails details = new CategoryDetails();
			ParkingDbo dbo = new ParkingDbo();
		%>
		<%
			// Login logic, if session is not set ask user to login.
			HttpSession session1 = request.getSession();
			boolean loggedIn = false;
			try{
				loggedIn = (Boolean)session1.getAttribute("isLogged");
			}
			catch(NullPointerException ex){
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
				return;
			}
			if(loggedIn == false){
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
				return;
			}
			
			ParkingDetails pDetails = (ParkingDetails)request.getAttribute("parkingDetails");
			// fetch values of varibales from ParkingDetails attribute.
			if(pDetails != null){
				if(pDetails.getName() != null){
					name = pDetails.getName();
				}
				if(pDetails.getEmpcode() != null){
					empcode = pDetails.getEmpcode();
				}
				if(pDetails.getPassNo() != null){
					passNo = pDetails.getPassNo();
				}
				if(pDetails.getMobileNo() != null){
					mNo = pDetails.getMobileNo();
				}
				if(pDetails.getCategory() != null){
					category = pDetails.getCategory();
				}
				if(pDetails.getAddress() != null){
					address = pDetails.getAddress();
				}
				if(pDetails.getcModel() != null){
					address = pDetails.getcModel();
				}
				if(pDetails.getcMake() != null){
					address = pDetails.getcMake();
				}
				if(pDetails.getcReg() != null){
					address = pDetails.getcReg();
				}
				if(pDetails.getIssueDate()!= null){
					issueDate = pDetails.getIssueDate().toString();
				}
				if(pDetails.getValidDate() != null){
					validDate = pDetails.getValidDate().toString();
				}
				
			}
		%>
		<div class="p-2">
			<%@ include file = "header.jsp" %>
			<div class="row">
			<form action="saverecord" method="POST" class="main col-8" onsubmit="warning()">
			<!-- Employee Detail -->
				<div class="form-row">
					<div class="form-group col">
						<label for="inputName4" class="font-weight-bold">Name</label>
			      		<input type="text" class="form-control form-control-sm" id="inputName4" name="name" placeholder="Name" required value=<%=name%> >
			    	</div>
			    	<div class="form-group col-3">
			     		<label for="inputEmpCode4" class="font-weight-bold" >EmpCode</label>
			      		<input type="text" onfocusout = "getParkingDetail('inputEmpCode4',null)" class="form-control form-control-sm" id="inputEmpCode4" name="empcode" placeholder="Employee Code" value=<%=empcode%>>
			    	</div>
			    	<div class="form-group col-3">
			    		<label for="inputPassNumber" class="font-weight-bold">Pass No</label>
			    		<input type="text" required class="form-control form-control-sm" id="inputPassNumber" name="pass_no" placeholder="Pass No" value=<%=passNo%> >
			    	</div>
			  	</div>
			  	
			  	<div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="inputMNumber" class="font-weight-bold">Mobile/Alternate Number</label>
			      <input type="text" class="form-control form-control-sm" id="inputMNumber" name="mobile_no" placeholder="Mobile Number" value=<%=mNo%>>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="inputCategory" class="font-weight-bold" >Category</label>
			      	 <select id="inputCategory" class="form-control form-control-sm" name="category" required placdholder="Select Category">
			          	<option disabled selected>Select Category</option>
			        	<% categoryList = dbo.getCategory();
			        		for(String cat : categoryList ){
			        			%>
			        			<option><%=cat%></option>
			        	<%	}
			        	%>			        
			      </select>
			    </div>
			  	</div>
			  	<!-- Employee Address -->
			  	<div class="form-group">
			    	<label for="inputAddress" class="font-weight-bold">Address</label>
			    	<input type="text" class="form-control form-control-sm" id="inputAddress" name="address" placeholder="#Address">
			  	</div>
			  	<!-- Car Details -->
			  	<div class="form-row">
			  		<div class="form-group col">
			  			<label for="inputModel" class="font-weight-bold">Car Model</label>
			  			<input type="text" class="form-control form-control-sm" id="inputModel" placeholder="car model" name="c_model">
			  		</div>
			  		<div class="form-group col">
			      		<label for="inputMake" class="font-weight-bold">Car Make</label>
			      		<input type="text" class="form-control form-control-sm" id="inputMake" placeholder="Car Make" name="c_make">
			    	</div>
			    	<div class="form-group form-group col">
			    		<label for="inputReg" class="font-weight-bold">Car Registration</label>
			    		<input type="text" class="form-control form-control-sm" id="inputReg" placeholder="Car Registration" name="c_reg" required>
			    	</div>
			  	</div>
			  	<!-- Date fields -->
			  	<div class="form-row">
			  		<div class="form-group col">
			  			<label for="inputDatefrom" class="font-weight-bold">Issue Date</label>
			  			<input class="form-control form-control-sm" type="date" id="inputDateFrom" name="from_date" required>
			  		</div>
			    	<div class="form-group col">
			    		<label for="inputDateto" class="font-weight-bold">Valid Till</label>
			      		<input class="form-control form-control-sm" type="date" id="inputDateto" name="to_date" required>
			    	</div>
			  </div>
			  
			  <button type="submit" class="btn btn-secondary" data-toggle="modal" data-target="#confirmation">Add</button>
			  <button type="reset" class="btn btn-danger">Clear</button>
			</form>
			<div class="col">
				<div class="row">
					<div class="display-5 mb-2 font-weight-bold text-center col">Recently Added Employees</div>
				</div>
				<table class="table table-striped table-sm table-bordered table-hover">
			  	<thead>
			    	<tr>
				      	<th scope="col">#</th>
				      	<th scope="col">Name</th>
				      	<th scope="col">Emp Code</th>
				      	<th scope="col">Pass No</th>
			    	</tr>
			  	</thead>
			  	<tbody>
			  		<%
			  			List<ParkingDetails> parkingList = dbo.getLatestParkingDetails(10);
			    		if(parkingList != null){
			    			int i = 1;
			    			for(ParkingDetails pd : parkingList){
			    				String ids = new Integer(i).toString();
			    				rowCount = "empCodeRow"+ids.toString();
			    				colCount = "empCodecol"+ids.toString();
			    				passColCount = "empPassCol"+ids.toString();
			    	%>
			    				<tr class="" id="empCodeRow<%=ids%>" onclick="getParkingDetail('empCodecol<%=ids%>','empPassCol<%=ids%>')">
							      <td><%= i %></th>
							      <td><%= pd.getName() %></td>
							      <td id="empCodecol<%=ids%>"><%=pd.getEmpcode() %></td> 
							      <td id="empPassCol<%=ids%>"><%= pd.getPassNo() %></td>
							 </tr>
					<%
				    		i++;
			    			}	
			    		}
			    		
			  		%>
			  	</tbody>
				</table>
				</div>
				</div>
			</div>
		<script src="js/jquery-3.3.1.slim.min.js"></script>
   		<script src="js/popper.min.js"></script>
    	<script src="js/bootstrap.bundle.min.js"></script>
    	<script>
    		var updateFlag;
    		function getParkingDetail(codeId, passId){
    			var val;
    			var passVal;
    			//alert(document.getElementById(id).value);
    			if(codeId == 'inputEmpCode4'){
    				val = document.getElementById(codeId).value;
    				passVal = null;
    			}
    			else{
    				val = document.getElementById(codeId).textContent;
    				passVal = document.getElementById(passId).textContent;
    			}
    			//alert(val+passVal);
    			var xhttp = new XMLHttpRequest();
    			xhttp.open("POST", "fetch", true);
    			xhttp.onreadystatechange = function() {
    		        if (this.readyState == 4 && this.status == 200) {
    		        	data_array = (this.responseText.split("^"));
    		        	//alert(data_array[4])
    		        	if((data_array[1] != null && data_array[1] != " ") || (data_array[2] != null && data_array[2] !=" ")){
	    		        	document.getElementById('inputName4').value = data_array[0];
	    		        	document.getElementById('inputEmpCode4').value = data_array[1];
	    		        	document.getElementById('inputPassNumber').value = data_array[2];
	    		        	document.getElementById('inputMNumber').value = data_array[3];
	    		        	if(data_array[4] != null && data_array[4] != "" ){
	    		        		document.getElementById('inputCategory').selectedIndex = data_array[4];
	    		        	}
	    		        	else{
	    		        		document.getElementById('inputCategory').selectedIndex = 0;
	    		        	}
	    		        	document.getElementById('inputModel').value = data_array[5];
	    		        	document.getElementById('inputMake').value = data_array[6];
	    		        	document.getElementById('inputReg').value = data_array[7];
	    		        	document.getElementById('inputAddress').value = data_array[8];
	    		        	document.getElementById('inputDateFrom').value = data_array[9].trim();
	    		        	document.getElementById('inputDateto').value = data_array[10];
    		        	}
    		        	updateFlag = data_array[11];
    		        	
    		       }
    		    };
    			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    			xhttp.send("empcode="+val+"&pass_no="+passVal);
    		}
    		function warning(){
    			if((updateFlag != false) && (updateFlag != "false")){
    				alert("Record will be updated.");
    			}
    		}
    	</script>
	</body>
</html>
