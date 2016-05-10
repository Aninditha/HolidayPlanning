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
 * Servlet implementation class addHotelDetailServlet
 */

public class addHotelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addHotelDetailServlet() {
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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			RequestDispatcher rd1 = null;
			String hotelID = request.getParameter("hotelID");
			double price = Double.parseDouble(request.getParameter("price"));
			String detailID = request.getParameter("detailID");
			String typeOfRoom = request.getParameter("typeOfRoom");

			hotelBean hoteldetail = new hotelBean();

			hoteldetail.setHotelBooking_ID(hotelID);
			hoteldetail.setHoteldetailprice(price);
			hoteldetail.setTypeOfRoom(typeOfRoom);
			hoteldetail.setHotelDetailId(detailID);

			int flag = serviceDao.addHotelDetail(hoteldetail);

			if (flag == 0) {
				String str = "Hotel detail added successfully";
				request.setAttribute("msg", str);

				rd1 = request.getRequestDispatcher("/addHotelDetails.jsp");
				rd1.include(request, response);

			}

			if (flag == 1) {
				String str = "Hotel details added";
				System.out.println(str);
				request.setAttribute("msg", str);// has to be deleted after
													// adding this message in
													// jsp page
				rd1 = request.getRequestDispatcher("/addHotelDetails.jsp");
				rd1.include(request, response);
			}

		} catch (Exception ex) {
			System.out.println("Exception in add hotel details controller");
			ex.printStackTrace();
		}
		
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
