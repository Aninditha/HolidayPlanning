package com.ssdi.controller;


// contributed by rakesh balan
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
 * Servlet implementation class addHotelServlet
 */

public class addHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String String = null;
	private ServicesDao serviceDao;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		ServletContext context = getServletContext();
		databaseFactory factory = databaseFactory.getInstance(context.getInitParameter("environment"));
		serviceDao = factory.createServiceDao();
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addHotelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			RequestDispatcher rd1 = null;

			String regionID = request.getParameter("regionID");
			String hotelID = request.getParameter("hotelID");
			String hotelName = request.getParameter("hotelName");
			String description = request.getParameter("description");
			double rating = Double.parseDouble(request.getParameter("rating"));

			hotelBean hotel = new hotelBean();

			hotel.setRegionID(regionID);
			hotel.setHotelBooking_ID(hotelID);
			hotel.setHotelName(hotelName);
			hotel.setDescription(description);
			hotel.setRating(rating);


			int flag = serviceDao.addHotel(hotel);
			
			

			if (flag == 1) {
				String str = "Hotel added successfully";
				request.setAttribute("msg", str);

				rd1 = request.getRequestDispatcher("/addHotel.jsp");
				rd1.include(request, response);

			} else if (flag == 0) {
				String str = "Registration failed";
				System.out.println(str);
				request.setAttribute("msg", str);// has to be deleted after
													// adding this message in
													// jsp page
				rd1 = request.getRequestDispatcher("/addHotel.jsp");
				rd1.include(request, response);
			}

		} catch (Exception ex) {
			System.out.println("Exception in add hotel controller");
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
