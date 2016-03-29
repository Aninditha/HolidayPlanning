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
import com.ssdi.POJO.hotelBean;
import com.ssdi.model.HotelModel;

/**
 * Servlet implementation class HotelSearch
 */
@WebServlet("/HotelSearch")
public class HotelSearch extends HttpServlet {
	
	static List<hotelBean> hotelList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelSearch() {
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
			hotelBean region = new hotelBean();
		    region.setRegion(request.getParameter("region"));
		    hotelList = HotelModel.search(region);
		    System.out.println("size in controller: "+hotelList.size());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("hotelList",hotelList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/HotelSearchList.jsp");
		rd.forward(request,response);
		
	}
}
