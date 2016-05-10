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

import com.ssdi.POJO.flightBean;
import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class FlightOnewayDetails
 */
@WebServlet("/FlightOnewayDetails")
public class FlightOnewayDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightOnewayDetails() {
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
		flightBean flight = new flightBean();
		userbean user = new userbean();
		try {
			String username = request.getSession().getAttribute("username").toString();
			flight.setSource1(request.getParameter("source"));
			flight.setDestination1(request.getParameter("destination"));
			flight.setPrice(request.getParameter("price"));
			flight.setDateOfArrival1(request.getParameter("dateOfArrival1"));
			flight.setDateOfDeparture1(request.getParameter("dateOfDeparture1"));
			user.setEmail(username);

			String source = flight.getSource1();
			String destination = flight.getDestination1();
			String price = flight.getPrice();
			String dateOfArrival1 = flight.getDateOfArrival1();
			String dateOfDeparture1 = flight.getDateOfDeparture1();
			if (request.getSession().getAttribute("username") != null) {
				request.setAttribute("source", source);
				request.setAttribute("destination", destination);
				request.setAttribute("price", price);
				request.setAttribute("dateOfArrival1", dateOfArrival1);
				request.setAttribute("dateOfDeparture1", dateOfDeparture1);

				RequestDispatcher rd = request.getRequestDispatcher("/FlightOnewayDetails.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
