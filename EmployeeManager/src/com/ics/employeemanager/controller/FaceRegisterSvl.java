package com.ics.employeemanager.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.EmployeeBiz;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class FaceRegisterSvl
 */
public class FaceRegisterSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaceRegisterSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受页面的参数 Base64编码
		String base = request.getParameter("base");
		//获取session对象
		HttpSession session=request.getSession();
		int role= Integer.parseInt((String) session.getAttribute("role"));
		//创建json对象
		JSONObject json=new JSONObject();
		json.put("message", "");//json数据类型
		json.put("role", role);
		
		//从session中把员工姓名取出
		String empname = (String)session.getAttribute("empname");
	    String path = "C:/Users/samsung/Desktop/file/headPhoto/";
        //存储头像的路径
	    String urlPath=path+empname+".jpg";
	    
	    //创建一个file对象
        File uploadDir = new File(path);
        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录是否存在
			uploadDir.mkdirs(); //如果不存在，则创建目录
		}
        path+=empname+".jpg";
		OutputStream out = null; //输出流对象
        InputStream is = null; //输入流对象
		try {
			byte[] imgByte  = new BASE64Decoder().decodeBuffer(base); 
			for (int i = 0; i < imgByte.length; ++i) {
				if (imgByte[i] < 0) {// 调整异常数据
				imgByte[i] += 256;
				}
			}
			out = new FileOutputStream(path);  //把数据读出来，读到内存里，再把读到内存的数据输出
			is = new ByteArrayInputStream(imgByte);
			byte[] buff = new byte[1024];
	        int len = 0;
	        //一个字符一个字符地读
	        while((len=is.read(buff))!=-1){
	            out.write(buff, 0, len); //写入out流
	        }
	        
		} catch (IOException e) {
			json.put("message", "注册失败");
			e.printStackTrace();
			response.getWriter().print(json.toString());//响应数据到浏览器
		}finally{
			if(is!=null){
				try {
					is.close();//关闭流对象
			       
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
		EmployeeBiz biz = new EmployeeBiz();
		biz.upDataFaceUrlByName((Integer)session.getAttribute("empid"), urlPath,path);
		json.put("message", "注册成功");
		response.getWriter().print(json.toString());//响应数据

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
