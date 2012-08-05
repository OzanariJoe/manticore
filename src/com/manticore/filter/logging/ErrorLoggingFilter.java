package com.manticore.filter.logging;

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

import com.mongodb.util.JSON;

@WebFilter(urlPatterns = { "/*" }, dispatcherTypes = DispatcherType.ERROR)
public class ErrorLoggingFilter implements Filter {

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

		String remoteAddress = request.getRemoteAddr();
		String uri = ((HttpServletRequest) request).getRequestURI();
		String protocol = request.getProtocol();
		this.context.log("ERROR | User IP: " + remoteAddress
				+ " | Resource File: " + uri + " | Protocol: " + protocol);
		this.context.log("ERROR | " + JSON.serialize(req.getParameterMap()));

		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		this.context = conf.getServletContext();
	}

}
