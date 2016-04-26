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

import com.ssdi.POJO.HotelCostBean;
import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class HotelBook
 */
@WebServlet("/HotelBook")
public class HotelBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelBook() {
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
			String username = request.getSession().getAttribute("username").toString();
			String hotelName = request.getParameter("source");
			String date = request.getParameter("date");
			int numberOfRooms = Integer.parseInt(request.getParameter("no_rooms"));
			int numberOfNights = Integer.parseInt(request.getParameter("no_nights"));
			String roomType = request.getParameter("type_rooms");
			double finalCost = Double.parseDouble(request.getParameter("cost"));

			HotelCostBean cost = new HotelCostBean();
			userbean user = new userbean();

			user.setEmail(username);
			cost.setHotelName(hotelName);
			cost.setDate(date);
			cost.setNumberOfNights(numberOfNights);
			cost.setNumberOfRooms(numberOfRooms);
			cost.setRoomType(roomType);
			cost.setPrice(finalCost);

			int status = serviceDao.bookHotel(user, cost);
			
			if (request.getSession().getAttribute("username") != null){
				if(status == 1){
					RequestDispatcher rd = request.getRequestDispatcher("/hotelBookSuccess.jsp");
					rd.forward(request, response);
				} else if(status == 2){
					RequestDispatcher rd = request.getRequestDispatcher("/hotelBookFail.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}