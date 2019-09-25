package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.ApplyBiz;

/**
 * Servlet implementation class DisapproveSvl
 */
public class DisapproveSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisapproveSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String refusereason=request.getParameter("refusereason");
		String applyid=request.getParameter("applyid");
		//System.out.println(applyid);
		HttpSession session=request.getSession();
		int approverid=(Integer) session.getAttribute("empid");
		ApplyBiz biz=new ApplyBiz();
		boolean result=biz.disapprove(refusereason,applyid,approverid);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
