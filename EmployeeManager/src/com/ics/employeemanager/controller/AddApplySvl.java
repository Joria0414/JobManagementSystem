package com.ics.employeemanager.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.ApplyBiz;

/**
 * Servlet implementation class AddApplySvl
 */
public class AddApplySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddApplySvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收页面参数
		String applycontent = request.getParameter("applycontent");
		String type = request.getParameter("type");
		String starttime1 = request.getParameter("starttime");
		String endtime1 = request.getParameter("endtime");
		HttpSession session = request.getSession();
		int empid = (Integer) session.getAttribute("empid");
		
		// 把开始时间转换成时间戳类型
		Timestamp starttime = null;
		// 创建SimpleDateFormat对象，规定要转换成的格式
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			starttime = new Timestamp(sf.parse(starttime1).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 把开始时间转换成时间戳类型
		Timestamp endtime = null;
		try {
			endtime = new Timestamp(sf.parse(endtime1).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2.调用biz方法
		ApplyBiz biz = new ApplyBiz();
		boolean result = biz.insertApply(empid,applycontent,type,starttime,endtime);
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
