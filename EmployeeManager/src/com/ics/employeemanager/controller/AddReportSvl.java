package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.ReportBiz;

/**
 * Servlet implementation class AddReportSvl
 */
public class AddReportSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReportSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收页面参数
		String reporttitle= request.getParameter("reporttitle");
		String reportcontent= request.getParameter("reportcontent");
		String reporttype= request.getParameter("reporttype");
		//System.out.println(reporttype);
		HttpSession session = request.getSession();
		int empid = (Integer) session.getAttribute("empid");
		// System.out.println(depname+" "+depdetail);
		// 2.调用biz方法
		ReportBiz biz = new ReportBiz();
		boolean result = biz.insertReport(empid, reporttitle,reportcontent,reporttype);
		// System.out.println(result);
		// 3.响应数据
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
