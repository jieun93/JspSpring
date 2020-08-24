package kr.or.ddit.filter.auth;

import java.io.IOException;
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

import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.vo.MemberVO;

/**
 * 인증된 클라이언트에 대해 현재 요청하고 있는 보호자원에 대한 권한을 소유했는지 여부를 확인. 
 * 관리자인지를 확인 하는거 
 * 
 * 현재 유저가 어떤 요청을 받아 왔는지를 알아야 한다. 
 */
public class AuthorizationFilter implements Filter{
	
	private  Map<String, Set<String>> securedResources;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//			filterConfig.getServletContext().getAttribute("securedResources");
			
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(securedResources == null) 
			securedResources=(Map<String, Set<String>>) request.getServletContext().getAttribute("securedResources");
			
		// 다운 캐스팅하는거 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
	
		String uri = HandlerMapper.parseRequestURI(req);
		//접근할수 있는 권한이 어떤게 있는지 알아야 한다.
		//map을 가져와야 한다. --> scope를 사용 
		Set<String> roles = securedResources.get(uri);
		boolean pass = true;
		if(roles != null) { //접근 권한 체크 
			MemberVO authUser = (MemberVO) req.getSession().getAttribute("authUser"); //로그인한 유저의 세션과 속성을 가져오기 
			Set<String> mem_roles = authUser.getMem_roles(); //사용자의 접근권한을 가져오는거 
			boolean authorized = false;
			
			for(String role : roles) {
				authorized = mem_roles.contains(role);
				if(authorized) break; //  여기에 안걸리면 false
			}
			// 두개다 true여야 통과 --> and 연산
			pass = pass  && authorized;
		}

		
		if(pass) { //통과
			chain.doFilter(request, response);
			
		}else { // 로그인이 되어 있는 유저인데 관리자가 아니면서 관리자 기능을 하려고 할때  400 에러 발생
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,uri+"는 접근할 수 없는 자원입니다.");
		}
		
		}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
