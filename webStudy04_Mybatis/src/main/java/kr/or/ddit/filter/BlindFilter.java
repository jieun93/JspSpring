package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter {
			// 블랙리스트, reason
	private Map<String, String> blackList;
	
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	
	
	// 블랙리스트 기본으로 넣어준거 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blackList = new HashMap<>();
		blackList.put("127.0.0.1", "나");
		blackList.put("0:0:0:0:0:0:0:1", "나");
		blackList.put("192.168.0.167", "태유");
		
	}
	
	//여기를 통해 필터링을 수행
	// 블라인드 처리 -> 블랙리스트를 통해서 거름
	// 들어오는 요청을 모두 체크
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		1.클라이언트의 ip 확보 
		String clientIP = request.getRemoteAddr(); //getRemoteAddr : ip주소를 가져오는거 
		
		if(blackList.containsKey(clientIP)) {
//		3.블랙리스이면 통과안됨, 못들어오는 이유에 대해 메세지 보내기 	messageview로 이동, 사유 전달
			String reason = blackList.get(clientIP);
			request.setAttribute("reason", reason);
			String goPage="/WEB-INF/views/messageView.jsp";
			request.getRequestDispatcher(goPage).forward(request, response);
		}else{
//		2.블랙리스트의 대상이 아니면? 통과
			chain.doFilter(request, response);
		};
		
		
	
		
		
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
