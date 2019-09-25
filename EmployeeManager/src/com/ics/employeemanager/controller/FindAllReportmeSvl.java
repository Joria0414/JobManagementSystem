package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.ReportBiz;
import com.ics.employeemanager.entity.ReportEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllReportmeSvl
 */
public class FindAllReportmeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllReportmeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 1.获取日报名称，日报内容，日报发布人，日报创建时间
		String current = request.getParameter("current");
		String reportname = request.getParameter("title");
		String reportcontent = request.getParameter("content");
		/*String reportempname = request.getParameter("empname");*/
		String reporttime=request.getParameter("day");
		String reporttype=request.getParameter("type");
		HttpSession session=request.getSession();
		String reportempname=(String) session.getAttribute("empname");
		
		int currentPage = 1;// 当前页默认是第一页
		if (current != null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);// 类型转换，String-int
		}
		ReportBiz biz = new ReportBiz();
		ResultPage<ReportEntity> pageInfo = new ResultPage<ReportEntity>();// 创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllReport(pageInfo,reportname, reportcontent, reportempname,reporttime,reporttype);
		// 2.将列表保存到请求域
		request.setAttribute("pageinfo", pageInfo);
		// 3.请求转发
		if(Integer.parseInt(reporttype)==1){
			request.getRequestDispatcher("dreport/dreportstaff.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("mreport/mreportstaff.jsp").forward(request, response);
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
