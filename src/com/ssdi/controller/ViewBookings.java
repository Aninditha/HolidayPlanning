package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssdi.POJO.flightBean;
import com.ssdi.POJO.hotelBean;
import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class LoyalityPoints
 */
@WebServlet("/ViewBookings")
public class ViewBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	ArrayList<hotelBean> hotelBookingList;
	ArrayList<flightBean> flightBookingList;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookings() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getSession().getAttribute("username").toString();
		hotelBookingList = serviceDao.ViewUserBooking(username);
		flightBookingList = serviceDao.ViewFlightBookings(username);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") != null) {	
		
		//	System.out.println(points+""+request.getSession().getAttribute("username"));
			request.setAttribute("hotelBookingList", hotelBookingList);
			request.setAttribute("flightBookingList", flightBookingList);
			RequestDispatcher rd = request.getRequestDispatcher("/viewbooking.jsp");
			rd.forward(request, response);
		}
	}
}
