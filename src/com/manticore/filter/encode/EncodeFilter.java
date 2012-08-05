package com.manticore.filter.encode;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" }, dispatcherTypes = DispatcherType.REQUEST)
public class EncodeFilter implements Filter {

	private ServletContext context;

	@Override
	public void destroy() {
		this.context = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		this.context.log("Set Character-Encoding: UTF-8");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		this.context = conf.getServletContext();
	}

}
