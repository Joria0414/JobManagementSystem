package com.ics.employeemanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登入过滤器
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//核心
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest re=(HttpServletRequest)request;
		HttpServletResponse rq=(HttpServletResponse)response;
		String url=re.getRequestURI();//获取资源地址
		//什么样的文件不需要过滤
		//如果url包含loginForm.jsp就放行
		if(url.indexOf("loginForm.jsp")!=-1||url.indexOf("login_face.jsp")!=-1){
			chain.doFilter(request,response);//放行，访问的下一个目的地址
		}else{
			HttpSession session=re.getSession();//获取会话对象
			Integer empid=(Integer)session.getAttribute("empid");
			//如果员工id不为空——登入状态，放行，为空——未登入状态，跳转到登入页面（继续）
			if(empid!=null&&!"".equals(empid)){
				chain.doFilter(request, response);//放行，访问下一个页面
			}else{
				//如果url是xx_main.jsp或者是Svl,就重定向到loginForm.jsp 反之是.../loginForm.jsp
				if(url.indexOf("main.jsp")!=-1||url.indexOf("Svl")!=-1){
					rq.sendRedirect("loginForm.jsp");
				}else{
					rq.sendRedirect("../loginForm.jsp");
				}
				/*rq.sendRedirect("../loginForm.jsp");*/
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
