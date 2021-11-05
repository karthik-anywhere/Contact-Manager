package com.example.contactmanager.contact.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/logging")
public class FilterLogger implements Filter {

	private FilterConfig filterConfigObj = null;

	public void init(FilterConfig filterConfigObj) {
		this.filterConfigObj = filterConfigObj;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String remoteAddress = request.getRemoteAddr();
		String uri = ((HttpServletRequest) request).getRequestURI();
		String protocol = request.getProtocol();
		filterConfigObj.getServletContext().log("Logging pre Filter Servlet called");
		filterConfigObj.getServletContext().log("******* *******************");
		filterConfigObj.getServletContext().log(
				"User  Logged !  User IP: " + remoteAddress + " Resource File: " + uri + "  Protocol: " + protocol);
		chain.doFilter(request, response);
		filterConfigObj.getServletContext().log("Logging post Filter Servlet called");

	}

	@Override
	public void destroy() {

	}
}
