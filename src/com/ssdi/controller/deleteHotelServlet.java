package com.ssdi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssdi.POJO.hotelBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class deleteHotelServlet
 */

public class deleteHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String String = null;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteHotelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		ServletContext context = getServletContext();
		databaseFactory factory = databaseFactory.getInstance(context.getInitParameter("environment"));
		serviceDao = factory.createServiceDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try {
			RequestDispatcher rd1 = null;
			String hotelID = request.getParameter("hotelID");

			hotelBean hotel = new hotelBean();

			int flag = serviceDao.deleteHotel(hotel);
			if (flag == 1) {
				String str = "Hotel deleted successfully";
				request.setAttribute("msg", str);

				rd1 = request.getRequestDispatcher("/deleteHotel.jsp");
				rd1.include(request, response);

			}

			else if (flag == 0) {
				String str = "Deletion failed";
				System.out.println(str);
				request.setAttribute("msg", str);// has to be deleted after
													// adding this message in
													// jsp page
				rd1 = request.getRequestDispatcher("/deleteHotel.jsp");
				rd1.include(request, response);
			}

		} catch (Exception ex) {
			System.out.println("Exception in delete hotel controller");
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
