package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;

/**
 * Filter : 요청과 응답에 대한 전/후 처리를 담당하는 객체, 데이터를 변환하거나, 
 *          흐름을 제어해서 파일에 대한 처리나  보안 처리.
 *          
 * Filter 구현 단계 : Decorating Filter Pattern // 필터는 핵심 기능은 아니지만 필요하면 붙이는거 
 * 1. javax.servlet.Filter 의 구현(callback 메소드 구현 )
 * 2. doFilter 내에서 실제 요청과 응답 필터링 
 *  	1) 요청 필터링
 *  	2) chain.doFilter (**핵심** 없으면  흐름이 멈춤**) 
 *  	3) 응답 필터링          
 * 3. WAS 에게 필터 등록 (web.xml)
 * 		** WAS는 등록된 필터로 FilterChain을 구성함 . - FilterChain 내에서 필터링되는 순서는 필터를 등록할떄 순서에 따름 
 * 4. 매핑 설정으로 필터링할 요청을 결정함
 * 
 * *******
 * 1. 파일이 업로드를 되고 있는지 확인하려고 , 현재 요청이 multipart request 인지 여부 확인
 * 2. multipart request라면, request wrapper구조를 활용
 *  
 */
// prodinsert와 produpdate  중복을 제거 할려고  만드는거 

//@WebFilter("/*")  // 체인내의  등록된 순서를 따라가기 떄문에 되도록 쓰지 않느넥 좋다., 순서가 상관없을 때 사용 
public class FileUploadCheckFilter  implements Filter{
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadCheckFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 초기화 ", this.getClass().getSimpleName());
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; // 다운 캐스팅해줘야 함 /multipart 리퀘스트인지 확인해야 한다. 
		String contentType = req.getContentType(); // request의 get header
		if(contentType != null && contentType.startsWith("multipart/")) { // 멀티파트 리퀘스트이면 
			FileUploadRequestWrapper wrapper = new FileUploadRequestWrapper(req); //wrapper로 front controller patter으로 넘어간다. 
			chain.doFilter(wrapper, response); // 반드시 있어야 한다. 			
		}else { // 멀티 파트 리퀘스트가 아니다 ->할일이 없다 
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸 ", this.getClass().getSimpleName());
		
	}
	

}
