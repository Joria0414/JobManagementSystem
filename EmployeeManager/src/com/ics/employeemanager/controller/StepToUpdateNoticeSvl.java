package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.NoticeBiz;
import com.ics.employeemanager.entity.NoticeEntity;

/**
 * 因公告长度限制，所以在修改公告前必须从数据库查找公告
 * Servlet implementation class StepToUpdateNoticeSvl
 */
public class StepToUpdateNoticeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StepToUpdateNoticeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeBiz biz = new NoticeBiz();

		int noticeid = Integer.parseInt(request.getParameter("noticeid"));

		NoticeEntity notice = biz.findExactNotice(noticeid);

		// System.out.println(notice);
		request.setAttribute("notice", notice);

		request.getRequestDispatcher("notice/showUpdateNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
