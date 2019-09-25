package com.ics.employeemanager.controller;




import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.EmployeeBiz;
import com.ics.employeemanager.entity.EmployeeEntity;




/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//1、接收页面的参数
		String empname=request.getParameter("loginname");//获取输入框里面的值
		String password=request.getParameter("password");
		//2、创建业务逻辑层的对象，调用方法，拿到返回值
		//System.out.println(empname+" "+password);
		EmployeeBiz biz=new EmployeeBiz();
		EmployeeEntity emp = null;
		try {
			emp = biz.login(empname, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.把数据保存到域对象
		//如果得到的数据为null是登录失败，返回到登入页面，并返回错误信息；如果不为空，判断角色跳转到不同页面。
		String url="";
		if(emp==null){
			request.setAttribute("msg","员工姓名或密码错误");//把数据保存到请求域
			url="loginForm.jsp";//请求转发的目标资源
		}else{
			HttpSession session=request.getSession();//获得session对象
			session.setAttribute("empid",emp.getEmpId());
			session.setAttribute("empname",emp.getEmpname());
			session.setAttribute("role", emp.getRole());
			//1.判断角色 ①普通用户 ②管理员
			if(emp.getRole().equals("1")){
				//4.请求转发
				url="emp_main.jsp";
			}
			else{
				url="admin_main.jsp";
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}




}