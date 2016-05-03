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

import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class LoyalityPoints
 */
@WebServlet("/LoyalityPoints")
public class LoyalityPoints extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;
	int points;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoyalityPoints() {
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
		points = serviceDao.LoyalityPoints(username);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("username") != null) {
			System.out.println(points+""+request.getSession().getAttribute("username"));
			request.setAttribute("currentLoyalityPoints", points);
			RequestDispatcher rd = request.getRequestDispatcher("/loyalty.jsp");
			rd.forward(request, response);
		}
	}
}
