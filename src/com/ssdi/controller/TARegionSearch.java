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
import com.ssdi.POJO.taBean;
import com.ssdi.model.taModel;

/**
 * Servlet implementation class TARegionSearch
 */
@WebServlet("/TARegionSearch")
public class TARegionSearch extends HttpServlet {
	static List<taBean> taList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TARegionSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			taBean location = new taBean();
			//System.out.println(request.getAttribute("region"));
		    location.setLocation(request.getParameter("region"));
		    String region = location.getLocation();
		    System.out.println(region);
		    taList = taModel.searchAttractions(region);
		    System.out.println("size in controller: "+taList.size());
		    System.out.println(taList);
		} catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("taList",taList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/taAttractionList.jsp");
		rd.forward(request,response);
	}
}
