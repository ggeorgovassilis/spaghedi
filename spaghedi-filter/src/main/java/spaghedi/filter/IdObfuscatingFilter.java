package spaghedi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdObfuscatingFilter implements Filter {

	@Override
	public void destroy() {
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterCahin)
			throws IOException, ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			handle((HttpServletRequest) request, (HttpServletResponse) response, filterChain);
		} else
			filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
