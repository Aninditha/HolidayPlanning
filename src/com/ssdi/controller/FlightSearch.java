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
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try{
				    
		    String source = request.getParameter("source");
		    String destination = request.getParameter("destination");
		    String startDate = request.getParameter("startDate");
		    String endDate = request.getParameter("endDate");
		    int capacity = Integer.parseInt(request.getParameter("capacity"));
		    
		    flightList = FlightModel.call_Flight_model(source, destination, startDate, endDate, capacity);
		    
		} catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("source",request.getParameter("source") );
		request.setAttribute("destination",request.getParameter("destination") );
		
		request.setAttribute("flightList",flightList);
		if (request.getSession().getAttribute("username") != null){
			RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList_user.jsp");
			rd.forward(request,response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/FlightSearchList.jsp");
			rd.forward(request,response);
		}
	}
}
