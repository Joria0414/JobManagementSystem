package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.DocBiz;
import com.ics.employeemanager.entity.DocEntity;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class AddDocSql
 */
public class AddDocSql extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDocSql() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建smartupload对象，第三方插件
		SmartUpload smu=new SmartUpload();
		smu.setCharset("utf-8");//防止中文文件乱码
		//初始化
		smu.initialize(getServletConfig(),request,response);
		try {
			smu.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Files files=smu.getFiles();//获取上传文件的列表
		File file=files.getFile(0); //获取第一个文件
		//System.out.println(files.getSize());
		String filepath="";
		//判断上传文件列表和上传文件长度是否大于0，是否有文件上传
		if(files.getSize()>0&&file.getSize()>0){
			filepath=file.getFileName();//获取文件名字
			try {
				file.saveAs("C:/Users/samsung/Desktop/file/"+filepath);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//保存文件
			
		}
		//1.获取页面数据
		String title=smu.getRequest().getParameter("title");
		String empid=smu.getRequest().getParameter("empid");
		String remark=smu.getRequest().getParameter("remark");
		
		DocEntity doc=new DocEntity(Integer.parseInt(empid),title,filepath,remark);
		//2.创建对象，调用方法
		DocBiz biz=new DocBiz();
		int flag=biz.addDoc(doc);
		
		//3.把数据保存到请求域
		request.setAttribute("msg", flag>0?"上传成功":"上传失败");
		//4.请求转发
		request.getRequestDispatcher("document/showAddDocument.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
