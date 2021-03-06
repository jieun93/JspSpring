package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterEncodingFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		
	}
	// 클라이언트 요청시 처음으로  걸러지는 필터 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding(encoding); 
		logger.info("body 영역을 {} 방식으로 디코딩함 ", encoding);
		chain.doFilter(request, response); //꼭 넣어줘야 하는거
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
