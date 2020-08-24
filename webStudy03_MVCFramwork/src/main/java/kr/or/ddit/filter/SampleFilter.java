package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;

public class SampleFilter implements Filter {
//Filter 톰캣이 운영하는거
	private Logger logger = LoggerFactory.getLogger(SampleFilter.class);
	
	// 한번만 호출 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 초기화", this.getClass().getSimpleName());
		
	}
	
	// 요청이 들어 올때 마다 호출 , 톰캣은 필터를 chain으로 관리한다. 
	// 필터는 요청과 응답을 필터링 할 수 있다. 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; //---> 여기부터
		HttpServletResponse resp = (HttpServletResponse) response;
		logger.info("wrapper인지 확인 {}", req instanceof FileUploadRequestWrapper); // 맞으면 true ,아니면 false
		logger.info("요청 URI : {}", req.getRequestURI());  //필터 완성 --> 톰캣에 등록해야 한다.
		//--->여기까지 요청을 필터링하는 코드 
		chain.doFilter(request, response); 
		//--->응답을 필터링하는 코드
		logger.info("응답 크기 :{}", resp.getHeader("Content-Length"));
		
		
	}
	// 한번만 호출 
	@Override
	public void destroy() {
		logger.info("{}필터 소멸", this.getClass().getSimpleName());
		
	}

}
