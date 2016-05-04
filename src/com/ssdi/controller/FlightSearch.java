package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ssdi.POJO.flightBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class FlightSearch
 */

@WebServlet("/FlightSearch")

public class FlightSearch extends HttpServlet {

	Map<String, flightBean> flightMap = new HashMap<String, flightBean>();;
	private ServicesDao serviceDao;
	List<flightBean> FlightArrayList = new ArrayList<flightBean>();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightSearch() {
		super();
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flightID = request.getParameter("radioButton");

		// FlightBooking... calling BookFlight in services DAO.
		if (flightID != null) {
			for (Entry<String, flightBean> entry : flightMap.entrySet()) {
				String key = entry.getKey();
				if (key.equals(flightID)) {
					String username = request.getSession().getAttribute("username").toString();
					int bookFlightStatus = ServicesDao.BookFlight(username, entry.getValue());
					if (request.getSession().getAttribute("username") != null)
						if(bookFlightStatus == 1){
							RequestDispatcher rd = request.getRequestDispatcher("/FlightBookSuccess.jsp");
							rd.forward(request, response);
						} else if(bookFlightStatus == 2){
							RequestDispatcher rd = request.getRequestDispatcher("/FlightBookFail.jsp");
							rd.forward(request, response);
						}
				}
			}
		}

		boolean roundtrip;

		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String startDate = request.getParameter("startDate");

		String endDate = request.getParameter("endDate");

		if (endDate == null)
			roundtrip = false;
		else
			roundtrip = true;

		try {
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			flightMap = serviceDao.searchFlights(source, destination, startDate, endDate, capacity, roundtrip);
			for (Entry<String, flightBean> entry : flightMap.entrySet())
				FlightArrayList.add(entry.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("source", request.getParameter("source"));
		request.setAttribute("destination", request.getParameter("destination"));
		request.setAttribute("flightList", flightMap);
		request.setAttribute("FlightArrayList", FlightArrayList);

		if (roundtrip == false) {
			if (request.getSession().getAttribute("username") != null) {
				RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList_user.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList.jsp");
				rd.forward(request, response);
			}
		} else {
			if (request.getSession().getAttribute("username") != null) {
				RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList_user_roundtrip.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList_roundtrip.jsp");
				rd.forward(request, response);
			}
		}
	}
}
