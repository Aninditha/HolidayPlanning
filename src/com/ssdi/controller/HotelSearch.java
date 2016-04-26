package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
 * Servlet implementation class HotelSearch
 */

@WebServlet("/HotelSearch")

public class HotelSearch extends HttpServlet {

	static List<hotelBean> hotelList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public HotelSearch() {
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
		
		String regionID;
		boolean Exist = false;
		try {
			hotelBean region = new hotelBean();
			region.setRegion(request.getParameter("regionName"));
			String place = region.getRegion();
			Exist = serviceDao.checkHotelRegion(place);
			hotelList = serviceDao.searchHotels(region);

			Iterator iter = hotelList.iterator();
			hotelBean temp = (hotelBean) iter.next();
			regionID = temp.getRegionID();

			if (Exist) {
				HttpSession Session = request.getSession();
				Session.setAttribute("regionID", regionID);

				request.setAttribute("hotelList", hotelList);
				if (request.getSession().getAttribute("username") != null) {
					RequestDispatcher rd = request.getRequestDispatcher("/HotelSearchList_user.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/HotelSearchList.jsp");
					rd.forward(request, response);
				}
			} else {
				if (request.getSession().getAttribute("username") != null) {
					RequestDispatcher rd = request.getRequestDispatcher("/hotelError_user.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/hotelError.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
