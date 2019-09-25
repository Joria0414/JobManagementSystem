package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.ReportBiz;
import com.ics.employeemanager.entity.ReportEntity;

/**
 * Servlet implementation class FindExactReportSvl
 */
public class FindExactReportSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindExactReportSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reportid=Integer.parseInt(request.getParameter("reportid"));
		ReportBiz biz=new ReportBiz();
		
		ReportEntity report;
		report=biz.findExactReport(reportid);
		
		request.setAttribute("report", report);
		if(Integer.parseInt(report.getReporttype())==1){
			request.getRequestDispatcher("dreport/previewDReport.jsp").forward(request, response); //日报
		}else{
			request.getRequestDispatcher("mreport/previewMReport.jsp").forward(request, response); //月报
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
