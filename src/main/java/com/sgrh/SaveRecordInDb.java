package com.sgrh;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgrh.dbo.ParkingDbo;
import com.sgrh.entities.ParkingDetails;

/**
 * Servlet implementation class SaveRecordInDb
 */
public class SaveRecordInDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRecordInDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name").trim();
		
		String empCode = (request.getParameter("empcode") !=null || request.getParameter("empcode") !="")? request.getParameter("empcode").trim():"";
		String passNo = request.getParameter("pass_no").trim();
		String mobileNo = (request.getParameter("mobile_no") !=null && request.getParameter("mobile_no") !="")?request.getParameter("mobile_no").trim():"";
		String category = (request.getParameter("category")!=null && request.getParameter("category")!="")?request.getParameter("category").trim():"Staff";
		String address = (request.getParameter("address")!=null && request.getParameter("address")!="")?request.getParameter("address").trim():"";
		String carModel = (request.getParameter("c_model")!=null && request.getParameter("c_mdoel")!="")?request.getParameter("c_model").trim():"" ;
		String carMake = (request.getParameter("c_make")!=null && request.getParameter("c_make")!="")?request.getParameter("c_make").trim():"";
		String carReg = request.getParameter("c_reg").trim();
		Date fromDate = Date.valueOf(request.getParameter("from_date").trim());
		Date toDate = Date.valueOf(request.getParameter("to_date").trim());
		
		// code to add fields in database.
		ParkingDetails parkingDetails = new ParkingDetails();
		// set parking details in the fileds of ParkingDetails class.
		parkingDetails.setName(name);
		parkingDetails.setEmpcode(empCode);
		parkingDetails.setPassNo(passNo);
		parkingDetails.setMobileNo(mobileNo);
		parkingDetails.setCategory(category);
		parkingDetails.setAddress(address);
		parkingDetails.setcModel(carModel);
		parkingDetails.setcMake(carMake);
		parkingDetails.setcReg(carReg);
		parkingDetails.setIssueDate(fromDate);
		parkingDetails.setValidDate(toDate);
		
		ParkingDbo parkingDbo = new ParkingDbo();
		try {
			parkingDbo.addRecord(parkingDetails);
		}
		catch(Exception ex) {
			System.out.println("Record can't be inserted, either pass no already exists or some mandatory field is missing.");
			response.sendRedirect("Error.jsp");
			return;
		}
		
		response.sendRedirect("index.jsp");
		
		/*RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		request = null;
		rd.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
