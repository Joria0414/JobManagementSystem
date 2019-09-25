package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.NoticeBiz;
import com.ics.employeemanager.entity.NoticeEntity;

/**
 * Servlet implementation class FindExactNoticeSvl
 */
public class FindExactNoticeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindExactNoticeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeBiz biz=new NoticeBiz();
		
		int noticeid=Integer.parseInt(request.getParameter("noticeid"));
		
		NoticeEntity notice=biz.findExactNotice(noticeid);
		
		//System.out.println(notice);
		request.setAttribute("notice",notice);
		
		request.getRequestDispatcher("notice/previewNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
