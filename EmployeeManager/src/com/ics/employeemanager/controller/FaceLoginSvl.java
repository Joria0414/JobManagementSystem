package com.ics.employeemanager.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.ics.employeemanager.diz.EmployeeBiz;
import com.ics.employeemanager.entity.EmployeeEntity;
import com.ics.employeemanager.face.FaceClient;

import sun.misc.BASE64Encoder;

/**
 * 刷脸登录
 * Servlet implementation class FaceLoginSvl
 */
public class FaceLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaceLoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    public  String getImageStr(String imgFile)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    } 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受页面参数
		String base = request.getParameter("base");
		
		//session对象
		HttpSession session = request.getSession();
		
		//json对象
		JSONObject json = new JSONObject();
		json.put("message", "没有此用户");
		
		
		//创建业务层对象
		EmployeeBiz biz = new EmployeeBiz();
		ArrayList<EmployeeEntity> list = biz.findAllEmp();//调用查看所有在职员工的信息
		
		for (int i = 0; i < list.size(); i++) {
			//取出第i条数据
			EmployeeEntity emp = list.get(i);
			//只要facepath和faceurl不为空，trim()去掉前后的空格。
			if (emp.getFacePath() != null && !emp.getFacePath().trim().equals("")) {
				//将图片文件进行转码
				String base1 = this.getImageStr(emp.getFacePath());
				
				FaceClient faceClient = FaceClient.getInstance();//得到AipFace对象
				//对比两张图片相似度的结果
				boolean loginBool = faceClient.faceContrast(base, base1);
				if (loginBool) {
					json.put("message", "登录成功");
					session.setAttribute("empid",emp.getEmpId());
					session.setAttribute("empname",emp.getEmpname());
					session.setAttribute("role", emp.getRole());
					String role= emp.getRole();
					json.put("role", role);      
					System.out.println(json);
					break;
				}}}
		response.getWriter().print(json.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
