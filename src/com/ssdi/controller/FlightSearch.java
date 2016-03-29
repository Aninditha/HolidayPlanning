package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ssdi.POJO.flightBean;
import com.ssdi.model.FlightModel;

/**
 * Servlet implementation class FlightSearch
 */
@WebServlet("/FlightSearch")
public class FlightSearch extends HttpServlet {
	
	static List<flightBean> flightList = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			flightBean flight = new flightBean();
		    
			flight.setSource(request.getParameter("source"));
		    flight.setDestination(request.getParameter("destination"));
		    flight.setStartDate(request.getParameter("startDate"));
		    flight.setEndDate(request.getParameter("endDate"));
		    
		    String source = flight.getSource();
		    String destination = flight.getDestination();
		    String startDate = flight.getStartDate();
		    String endDate = flight.getEndDate();
		    
		    flightList = FlightModel.search(source, destination, startDate, endDate);
		    System.out.println("size in controller: "+flightList.size());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("flightList",flightList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList.jsp");
		rd.forward(request,response);
		
	}
}
