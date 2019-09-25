package com.ics.employeemanager.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.EmployeeBiz;
import com.ics.employeemanager.entity.EmployeeEntity;

/**
 * Servlet implementation class UpdateEmpSvl1
 */
public class UpdateEmpSvl1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpSvl1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post生效，修改编码模式
		//1.接收参数
		String empid=request.getParameter("id");
		String empname=request.getParameter("name");
		String cardunmber=request.getParameter("cardId");
		String sex=request.getParameter("sex");
		String jobid=request.getParameter("job_id");
		String education=request.getParameter("education");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String tel=request.getParameter("tel");
		String party=request.getParameter("party");
		String qq=request.getParameter("qqNum");
		String address=request.getParameter("address");
		String postcode=request.getParameter("postCode");
		String birthday=request.getParameter("birthday");
		String race=request.getParameter("race");
		String speciality=request.getParameter("speciality");
		String hobby=request.getParameter("hobby");
		String depid=request.getParameter("dept_id");
		String remark=request.getParameter("remark");
		
		//把出声年月转换成时间戳类型
		Timestamp changeBirthday=null;
		//创建SimpleDateFormat对象，规定要转换成的格式
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			changeBirthday=new Timestamp(sf.parse(birthday).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建对象，用来传参
		
		EmployeeEntity emp = new EmployeeEntity(Integer.parseInt(empid), Integer.parseInt(depid),Integer.parseInt(jobid), empname,cardunmber, address, postcode, tel, phone, qq, email, party, changeBirthday, race, education, speciality, hobby, remark, sex);
		
		//System.out.println(emp);
		EmployeeBiz biz=new EmployeeBiz();
		biz.UpdateEmp(emp);
		
		request.getRequestDispatcher("FindAllEmpstaffSvl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
