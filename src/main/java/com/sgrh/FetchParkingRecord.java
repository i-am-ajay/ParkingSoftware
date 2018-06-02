package com.sgrh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgrh.conf.CategoryMap;
import com.sgrh.dbo.EmpDetailDbo;
import com.sgrh.dbo.ParkingDbo;
import com.sgrh.entities.EmpDetail;
import com.sgrh.entities.ParkingDetails;

/**
 * Servlet implementation class FetchParkingRecord
 */
public class FetchParkingRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchParkingRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // Find emp detail in parking detail table if it's new record then find detail in PIS
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean isRecordForUpdate = true;
		
		String empcode = request.getParameter("empcode");
		String passno = request.getParameter("pass_no");
		if(empcode != null || passno !=null) {
			ParkingDbo pd = new ParkingDbo();
			ParkingDetails pDetails = pd.fetchSingleRecord(empcode.trim(), passno.trim());
			String data = "";
			// if no parking detail is available then search in PIS. 
			if (!(pDetails != null)) {
				EmpDetailDbo empDbo = new EmpDetailDbo();
				EmpDetail empDetail = empDbo.getEmployee(empcode);
				pDetails = ParkingDetailsFromEmpDetails.convertToParkingDetails(empDetail);
				isRecordForUpdate = false;
			}
			if(pDetails != null) {
				data = data.concat(pDetails.getName()!= null ? pDetails.getName():"").concat("^ ");
				data = data.concat(pDetails.getEmpcode()!=null ? pDetails.getEmpcode() : "").concat("^ ");
				data = data.concat(pDetails.getPassNo() !=null ? pDetails.getPassNo() : "").concat("^ ");
				data = data.concat(pDetails.getMobileNo() != null ? pDetails.getMobileNo() : "").concat("^ ");
				String catString = pDetails.getCategory();
				System.out.println(catString);
				data = data.concat(pDetails.getCategory() != null ? CategoryMap.CATEGORY_MAP.get(pDetails.getCategory().toLowerCase().trim()).toString() : "").concat("^ ");
				data = data.concat(pDetails.getcModel() != null ? pDetails.getcModel() : "").concat("^ ");
				data = data.concat(pDetails.getcMake() != null ? pDetails.getcMake() : "").concat("^ ");
				data = data.concat(pDetails.getcReg() != null ? pDetails.getcReg() : "").concat("^ ");
				data = data.concat(pDetails.getAddress() != null ? pDetails.getAddress() : "").concat("^ ");
				data = data.concat(pDetails.getIssueDate() != null ? pDetails.getIssueDate().toString() : "").concat("^");
				data = data.concat(pDetails.getValidDate() != null ? pDetails.getValidDate().toString() : "").concat("^");
				data = data.concat(isRecordForUpdate.toString());
			}
			else {
				data= data.concat("^").concat(empcode);
			}
				response.setContentType("text/plain");
				PrintWriter writer = response.getWriter();
				writer.write(data);
			}
			//RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			//rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
