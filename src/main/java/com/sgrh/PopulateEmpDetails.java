package com.sgrh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgrh.dbo.EmpDetailDbo;
import com.sgrh.entities.EmpDetail;
import com.sgrh.entities.ParkingDetails;

/**
 * Servlet implementation class PopulateEmpDetails
 */
public class PopulateEmpDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateEmpDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String empCode = request.getParameter("empcode");
		EmpDetailDbo empDbo = new EmpDetailDbo();
		EmpDetail empDetails = empDbo.getEmployee(empCode);
		// Get ParkingDetails object from EmpDetail
		ParkingDetails parkingDetails = ParkingDetailsFromEmpDetails.convertToParkingDetails(empDetails);
		
		request.setAttribute("parkingDetails", parkingDetails);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
