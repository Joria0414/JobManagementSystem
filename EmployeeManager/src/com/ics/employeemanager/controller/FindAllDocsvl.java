package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.DocBiz;
import com.ics.employeemanager.entity.DocEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllDocsvl
 */
public class FindAllDocsvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllDocsvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String current=request.getParameter("current");
		int currentPage=1;//当前页默认是第一页
		if(current!=null&&!"".equals(current)){
			currentPage=Integer.parseInt(current);//类型转换，String-int			
		}
		DocBiz biz=new DocBiz();
		ResultPage<DocEntity> pageInfo=new ResultPage<DocEntity>();//创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllDoc(pageInfo,title);
		request.setAttribute("pageinfo",pageInfo);
		
		/*DocBiz biz=new DocBiz();
		ArrayList<DocEntity> docs=biz.findAllDoc();
		request.setAttribute("docs", docs);*/
		request.getRequestDispatcher("document/document.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
