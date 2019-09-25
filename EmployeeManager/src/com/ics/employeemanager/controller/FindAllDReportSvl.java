package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.ReportBiz;
import com.ics.employeemanager.entity.ReportEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllDReportSvl
 */
public class FindAllDReportSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllDReportSvl() {
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
		String reportempname = request.getParameter("empname");
		String reporttime=request.getParameter("day");

		
		/*// 转换成时间戳类型
		Timestamp reportday = null;
		// 创建SimpleDateFormat对象，规定要转换成的格式
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			reportday = new Timestamp(sf.parse(reporttime).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		int currentPage = 1;// 当前页默认是第一页
		if (current != null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);// 类型转换，String-int
		}
		ReportBiz biz = new ReportBiz();
		ResultPage<ReportEntity> pageInfo = new ResultPage<ReportEntity>();// 创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllReport(pageInfo,reportname, reportcontent, reportempname,reporttime,"1");
		// 2.将列表保存到请求域
		request.setAttribute("pageinfo", pageInfo);
		// 3.请求转发
		request.getRequestDispatcher("dreport/dreport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
