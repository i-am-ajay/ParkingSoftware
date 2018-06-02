<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Details</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
 <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	  integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">-->
</head>
<body>
	<%@ page import="java.sql.ResultSet, java.util.List, java.sql.Date, java.lang.String, java.lang.Boolean"%>
	<%@ page import="com.sgrh.dbo.ParkingDbo, com.sgrh.entities.Category, com.sgrh.entities.ParkingDetails" %>
	<%!	
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
			
			ParkingDetails pdObj;
			
			List<String> categoryList = null;
			//ResultSet set = null;
			//CategoryDetails details = new CategoryDetails();
			ParkingDbo dbo = new ParkingDbo();
			
		%>
		<% 
			pdObj = new ParkingDetails();
			pdObj.setEmpcode(request.getParameter("empcode"));
			pdObj.setName(request.getParameter("name"));
			pdObj.setPassNo(request.getParameter("pass_no"));
			pdObj.setCategory(request.getParameter("category"));
			pdObj.setcModel(request.getParameter("c_model"));
			pdObj.setcMake(request.getParameter("c_make"));
			pdObj.setcReg(request.getParameter("c_Reg"));
			if(request.getParameter("from_date") != null){
				try{
					pdObj.setIssueDate(Date.valueOf(request.getParameter("from_date")));
				}
				catch(Exception ex){
					pdObj.setIssueDate(null);
				}
			}
			if(request.getParameter("to_date") !=null ){
				try{
					pdObj.setValidDate(Date.valueOf(request.getParameter("to_date")));
				}
				catch(Exception ex){
					pdObj.setValidDate(null);
				}
			}
			
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
		%>
	<div class="p-2">
		<%@ include file = "header.jsp" %>
		<form action="search.jsp" method="POST" class="main">
		<!-- Employee Detail -->
			<div class="form-row">
				<div class="form-group col">
					<label for="inputName4" class="font-weight-bold">Name</label>
		      		<input type="text" class="form-control form-control-sm" id="inputName4" name="name" placeholder="Name" value=<%=name%>>
		    	</div>
		    	<div class="form-group col">
		     		<label for="inputEmpCode4" class="font-weight-bold" >EmpCode</label>
		      		<input type="text" class="form-control form-control-sm" id="inputEmpCode4" name="empcode" placeholder="Employee Code" value=<%=empcode%>>
		    	</div>
		    	<div class="form-group col">
		    		<label for="inputPassNumber" class="font-weight-bold">Pass No</label>
		    		<input type="text" class="form-control form-control-sm" id="inputPassNumber" name="pass_no" placeholder="Pass No" value=<%=passNo%>>
		    	</div>
		    	<div class="form-group col">
		  			<label for="inputModel" class="font-weight-bold">Car Model</label>
		  			<input type="text" class="form-control form-control-sm" id="inputModel" placeholder="car model" name="c_model">
		  		</div>
		  		<div class="form-group col">
		      		<label for="inputMake" class="font-weight-bold">Car Make</label>
		      		<input type="text" class="form-control form-control-sm" id="inputMake" placeholder="Car Make" name="c_make">
		    	</div>
		    	<div class="form-group col">
		      		<label for="inputMake" class="font-weight-bold">Car Registration</label>
		      		<input type="text" class="form-control form-control-sm" id="inputMake" placeholder="Car Reg" name="c_Reg">
		    	</div>
		  	</div>
		  	<!-- Car Details -->
		  	<div class="form-row">
		    	<div class="form-group col">
		      	<label for="inputCategory" class="font-weight-bold" >Category</label>
		      	 <select id="inputCategory" class="form-control form-control-sm" name="category">
		         <option selected>Select Category</option>
		        	<% 	
		        		categoryList = dbo.getCategory();
		        		for(String cat : categoryList ){
		        			%>
		        			<option><%=cat%></option>
		        	<%	}
		        	%>			        
		      	</select>
		    	</div>
		  		<div class="form-group col">
		  			<label for="inputDateto" class="font-weight-bold">Issue Date</label>
		  			<input class="form-control form-control-sm" type="date" id="inputDateFrom" name="from_date">
		  		</div>
		    	<div class="form-group col">
		    		<label for="inputDateto" class="font-weight-bold">Valid Till</label>
		      		<input class="form-control form-control-sm" type="date" id="inputDateto" name="to_date">
		    	</div>
		  </div>
		  <button type="submit" class="btn btn-secondary">Search</button>
		</form>
		
		<table class="table table-striped table-sm table-bordered table-hover mt-5">
			  	<thead>
			    	<tr>
				      	<th scope="col display-5">#</th>
				      	<th scope="col display-5">Name</th>
				      	<th scope="col">Emp Code</th>
				      	<th scope="col">Pass No</th>
				      	<th scope="col">Category</th>
				      	<th scope="col">Mobile</th>
				      	<th scope="col">Address</th>
				      	<th scope="col">Car Make</th>
				      	<th scope="col">Car Model</th>
				      	<th scope="col">Registration</th>
				      	<th scope="col">Pass Issue Date</th>
				      	<th scope="col">Pass Valid Till</th>
			    	</tr>
			  	</thead>
			  	<tbody>
			  		<%	
			  			//List<ParkingDetails> parkingList = dbo.getLatestParkingDetails(10);
		    			// send details and search.	
		    			List<ParkingDetails> parkingList = dbo.searchParkingDetails(pdObj);
			    		if(parkingList != null){
			    			int i = 1;
			    			for(ParkingDetails pd : parkingList){
			    				String ids = new Integer(i).toString();
			    				rowCount = "empCodeRow"+ids.toString();
			    				colCount = "empCodecol"+ids.toString();
			    	%>
			    				<tr class="" id="empCodeRow<%=ids%>>" onclick="send()">
							      <td><%= i%></th>
							      <td><%= pd.getName() %></td>
							      <td id="empCodecol<%=ids%>"><%=pd.getEmpcode() %></td> 
							      <td><%= pd.getPassNo() %></td>
							      <td><%= pd.getCategory() %></td>
							      <td><%= pd.getMobileNo() %></td>
							      <td><%= pd.getAddress() %></td>
							      <td><%= pd.getcMake() %></td>
							      <td><%= pd.getcModel() %></td>
							      <td><%= pd.getcReg() %></td>
							      <td><%= pd.getIssueDate() %></td>
							      <td><%= pd.getValidDate() %></td>
							    </a>
							 </tr>
					<%
				    		i++;
			    			}	
			    		}
			    		
			  		%>
			  	</tbody>
				</table>
		</div>
		<script src="js/jquery-3.3.1.slim.min.js"></script>
   		<script src="js/popper.min.js"></script>
    	<script src="js/bootstrap.bundle.min.js"></script>
    	<script>
    	var updateFlag;
    	function send(){
    		var httpRequest = new XMLHttpRequest();
    		httpRequest.open("POST","index.jpg",true);
    		httpRequest.send();
    	}
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
    		        	document.getElementById('inputCategory').value = data_array[4];
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
    	</script>
		<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
   		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
    	integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
    	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script> -->
</body>
</html>