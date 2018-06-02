package com.sgrh.conf;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sgrh.dbo.ParkingDbo;
import com.sgrh.entities.User;

/**
 * Servlet implementation class ParkingServlet
 */
public class ParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		String password = request.getParameter("password");
		ParkingDbo pDbo = new ParkingDbo();
		User user = pDbo.getLoginDetails(userName);
		String page = request.getParameter("uri");
		if(!(user != null)) {
			response.sendRedirect("login.jsp");
			return;
		}
		try {
			System.out.println(user.getUserCode());
		}
		catch(Exception ex) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		if(user.getUserCode().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
			System.out.println(page);
			HttpSession session = request.getSession();
			session.setAttribute("isLogged",true);
			System.out.println(request.getRequestURI());
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
			return;
		}
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
