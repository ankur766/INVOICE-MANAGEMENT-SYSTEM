package com.filters;

import java.io.IOException;

//import kiitankur.servlet.Filter;
import kiitankur.servlet.FilterChain;
import kiitankur.servlet.FilterConfig;
import kiitankur.servlet.ServletException;
import kiitankur.servlet.ServletRequest;
import kiitankur.servlet.ServletResponse;
import kiitankur.servlet.http.HttpFilter;
import kiitankur.servlet.http.HttpServletRequest;
import kiitankur.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class HRCCorsFilter
 */

public class HRCCorsFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public HRCCorsFilter() {
		super();
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
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		System.out.println("CORSFilter HTTP Request: " + request.getMethod());

		// Authorize (allow) all domains to consume the content
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");

		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS
		// handshake
		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, servletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
