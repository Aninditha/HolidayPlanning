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
import javax.servlet.http.HttpSession;

import com.ssdi.POJO.HotelCostBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class hotelCostCalculation
 */
@WebServlet("/hotelCostCalculation")
public class hotelCostCalculation extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public hotelCostCalculation() {
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
		try {
			HttpSession Session = request.getSession();
			String regionID = Session.getAttribute("regionID").toString();
			String hotelName = request.getParameter("source");
			String description = request.getParameter("description");
			String date = request.getParameter("SlotDate");
			int numberOfRooms = Integer.parseInt(request.getParameter("no_rooms"));
			int numberOfNights = Integer.parseInt(request.getParameter("no_nights"));
			String roomType = request.getParameter("type_rooms");

			HotelCostBean cost = new HotelCostBean();

			cost.setHotelName(hotelName);
			cost.setDescription(description);
			cost.setDate(date);
			cost.setNumberOfNights(numberOfNights);
			cost.setNumberOfRooms(numberOfRooms);
			cost.setRoomType(roomType);
			double finalCost = serviceDao.calculateCost(cost, regionID);

			if (request.getSession().getAttribute("username") != null) {
				request.setAttribute("source", cost.getHotelName());
				request.setAttribute("description", cost.getDescription());
				request.setAttribute("date", cost.getDate());
				request.setAttribute("no_rooms", cost.getNumberOfRooms());
				request.setAttribute("type_rooms", cost.getRoomType());
				request.setAttribute("no_nights", cost.getNumberOfNights());
				request.setAttribute("cost", finalCost);

				RequestDispatcher rd = request.getRequestDispatcher("/HotelBook_user.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("source", cost.getHotelName());
				request.setAttribute("description", cost.getDescription());
				request.setAttribute("date", cost.getDate());
				request.setAttribute("no_rooms", cost.getNumberOfRooms());
				request.setAttribute("type_rooms", cost.getRoomType());
				request.setAttribute("no_nights", cost.getNumberOfNights());
				request.setAttribute("cost", finalCost);

				
				RequestDispatcher rd = request.getRequestDispatcher("/HotelDetailsView.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
