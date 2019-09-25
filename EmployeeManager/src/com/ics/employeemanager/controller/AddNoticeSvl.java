package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.NoticeBiz;

/**
 * Servlet implementation class AddNoticeSvl
 */
public class AddNoticeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNoticeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收页面参数
		String title = request.getParameter("noticename");
		String content = request.getParameter("noticecontent");
		//System.out.println(title+" "+content+"WOW");
		HttpSession session=request.getSession();
		int empid=(Integer) session.getAttribute("empid") ;
		// System.out.println(depname+" "+depdetail);
		// 2.调用biz方法
		NoticeBiz biz = new NoticeBiz();
		boolean result = biz.insertNotice(empid,title, content);
		//System.out.println(result);
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
