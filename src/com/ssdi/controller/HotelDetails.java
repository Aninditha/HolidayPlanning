package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssdi.POJO.hotelBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class HotelDetails
 */
@WebServlet("/HotelDetails")
public class HotelDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelDetails() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		hotelBean hotel = new hotelBean();
		try {
			HttpSession Session = request.getSession();
			String regionID = Session.getAttribute("regionID").toString();

			hotel.setHotelName(request.getParameter("hotelName"));
			String hotelName = hotel.getHotelName();
			hotel = serviceDao.searchHotelDetails(hotelName, regionID);
			
			String hotelsource = hotel.getHotelName();
			request.setAttribute("source", hotelsource);
			request.setAttribute("description", hotel.getDescription());
			request.setAttribute("roomPrice", hotel.getRoomPrice());

			if (request.getSession().getAttribute("username") != null) {
				RequestDispatcher rd = request.getRequestDispatcher("/HotelDetails_user.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/HotelDetails.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
