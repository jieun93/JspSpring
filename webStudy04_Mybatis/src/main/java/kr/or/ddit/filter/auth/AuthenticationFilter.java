package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import kr.or.ddit.commons.dao.IResourceDAO;
import kr.or.ddit.commons.dao.ResourceDAOImple;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.vo.ResourceVO;




/**
 * 보호 자원에 대한 요청에 대해 현재 요청을 발생시킨 클라이언트가 인증된 클라이언트 인지 확인 
 * 인증된 클라이언트 ?  자원 제공
 * 인증전 클라이언트 ?  로그인 폼으로 이동 
 * 
 */
public class AuthenticationFilter implements Filter{
// 보호자원에 대한 명세가 필요하다. 
	
	private  static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private Map<String, Set<String>> securedResources;
	private IResourceDAO resourceDAO = new ResourceDAOImple(); //의존성 만들기 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		securedResources = new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute("securedResources",securedResources);
		// 데이터 베이스로 부터 조회 후 map 완성
		List<ResourceVO> resourceList = resourceDAO.selectResourceList();
		for(ResourceVO res : resourceList) {
			securedResources.put(res.getRes_url(), res.getRoles());
		}
	}
	
	
	// 보호자원을 누군가 사용할떄 사용하게 할건지 막을건지를 걸러내는거 
	// 1. 현재 요청이 보호자원의 요청인지를 판단해야 한다. 
	// 2. 보호자원의 요청인 경우   보호자원을 사용할수 있는지 확인 , /인증된 유저-> 통과 , 아닌 유저 -> 인증할수 있는곳으로 이동    /아닌경우  통과
	// 3.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 //ServletRequest의 하위계층 -> HttpServletRequest 바꿔주는거
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		String input = req.getRequestURI(); //현재 요청을 받는거의 주소를 가져오는거 
		String uri =  HandlerMapper.parseRequestURI(req); //핸들러맵펄에서 가져온거 
		boolean pass = true; 
		
		if( securedResources.containsKey(uri)) { //securedResources에 uri가 있으면 
			Object authUser = req.getSession().getAttribute("authUser"); //세션에서 authUser를 가져오는거 
			if(authUser == null) pass = false;
			
		}
		
		if(pass) { // PASS는 무조건 true
			chain.doFilter(request, response);	 // 통과 , 컨트롤러 창으로 가라
		}else {
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
