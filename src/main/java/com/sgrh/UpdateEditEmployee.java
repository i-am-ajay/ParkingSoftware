package com.sgrh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgrh.dbo.EmpDetailDbo;
import com.sgrh.dbo.ParkingDbo;
import com.sgrh.entities.EmpDetail;
import com.sgrh.entities.ParkingDetails;

/**
 * Servlet implementation class UpdateEditEmployee
 */
public class UpdateEditEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEditEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empcode = request.getParameter("empcode");
		String passNo1 = request.getParameter("pass_no");
		System.out.println(empcode);
		ParkingDbo pDbo = new ParkingDbo();
		ParkingDetails userParkingDetails = pDbo.fetchSingleRecord(empcode,passNo1);
		String passNo = null;
		if(userParkingDetails != null) {
			passNo = userParkingDetails.getPassNo();
			if (!(passNo != null)) {
				EmpDetailDbo empDbo = new EmpDetailDbo();
				EmpDetail empDetail = empDbo.getEmployee(empcode);
				userParkingDetails = ParkingDetailsFromEmpDetails.convertToParkingDetails(empDetail);
			}
			request.setAttribute("parkingDetails", userParkingDetails);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			System.out.println("This doesn't work.. don't know why");
			return;
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
