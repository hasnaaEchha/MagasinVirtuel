package services;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beanJSF.ClientBean;
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		ClientBean client=(ClientBean)((HttpServletRequest)request).getSession().getAttribute("client");
		if(client==null || !client.isLoggedIn()){
			String contextPath=((HttpServletRequest)request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath+"/login.xhtml");
		}
		chain.doFilter(request, response);
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
