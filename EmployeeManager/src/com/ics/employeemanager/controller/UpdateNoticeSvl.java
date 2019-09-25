package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ics.employeemanager.diz.NoticeBiz;

/**
 * Servlet implementation class UpdateNoticeSvl
 */
public class UpdateNoticeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoticeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post生效，修改编码模式
		//1.接收参数
		String noticeid=request.getParameter("noticeid");
		String noticename=request.getParameter("noticename");
		String noticecontent=request.getParameter("noticecontent");
		//2.创建业务层对象，调用方法
		NoticeBiz biz=new NoticeBiz();
		biz.UpdateNotice(noticeid,noticename,noticecontent);
		//3.请求转发
		request.getRequestDispatcher("FindAllNoticeSvl").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
