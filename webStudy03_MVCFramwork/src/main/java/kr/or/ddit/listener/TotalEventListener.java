package kr.or.ddit.listener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class TotalEventListener
 *
 */
@WebListener
public class TotalEventListener implements ServletContextListener, HttpSessionListener,
											ServletRequestListener, HttpSessionAttributeListener {
	
	private Logger logger = LoggerFactory.getLogger(TotalEventListener.class);
	
	
	//1. context lifecycle 처리 방법 --  이벤트 종류 
	//  서버가 시작 될땨ㅐ
	// 초기값이 만들어진다. 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("{} 컨텍스트 시작", sce.getServletContext().getContextPath());
		/*
		 * <c:set var="cPath" value="${pageContext.request.contextPath }"
		 * scope="application"/>
		 */
		ServletContext application =  sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute("userList", new HashSet<MemberVO>()); // 같은 vo는 넣지 않겠다. 
	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("{} 컨텍스트 소멸", sce.getServletContext().getContextPath());
		
	}
	
	// 2.  세션의 라이프사이크 처리 방법
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("{}  세션 생성 ", se.getSession().getId());
	}

	@Override // 만료시간이 끝났을때 
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("{}  세션 소멸 ", se.getSession().getId());
		
	}

	//3. requset의 lifecycle
	// 응답이 들오올때  모든 요청이 들어올때 마다 기록을 해야 한다.  
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// 튜닝에 사용할려면 파일시스템에 기록해야 한다....jdbc append를 잘 사용하면 오더를 받았을떄 쉽게 구현 할 수 있다. 
		
		logger.info("{}로 부터 {}요청 발생 ", sre.getServletRequest().getRemoteAddr(), new Date());
		long startTime = System.currentTimeMillis();
		sre.getServletRequest().setAttribute("startTime", startTime);
	}
	
	//응답이 나가는 시간 
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		long startTime = (Long) sre.getServletRequest().getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		logger.info("{} 요청의 소요시간  {}ms ", sre.getServletRequest().getRemoteAddr(), (endTime-startTime));
		
	}

	// 4. application Scope  서블릿 컨텍스트 
	// 5. session scope
	// 6. request scope

	@Override // 세션 스코프에 들어 갔을 때 ex) 누군가 로그인에 성공했을 떄
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 먼저 누가 왔는지 확인해야 한다. 
		String attrName =  event.getName(); // add된 넹미
		if("authUser".equals(attrName)) {
			MemberVO authUser =  (MemberVO) event.getValue();
			logger.info("누가로그인했낭?{}", authUser.getMem_name());
			// 현재 접속한 사람 보여주는거 
			Set<MemberVO> userList =  (Set<MemberVO>) event.getSession().getServletContext().getAttribute("userList");
			userList.add(authUser);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// authuser가 맞는지 확인
		String attrName =  event.getName(); // add된 넹미
		if("authUser".equals(attrName)) {
			MemberVO authUser =  (MemberVO) event.getValue();
			logger.info("누가 로그아웃 인했낭?{}", authUser.getMem_name());
			// 로그아웃을 하면 목록에서 제거되낟. 
			Set<MemberVO> userList =  (Set<MemberVO>) event.getSession().getServletContext().getAttribute("userList");
			userList.remove(authUser);
		}
	}

	@Override //값을 바꿀 때
	public void attributeReplaced(HttpSessionBindingEvent event) {
			
	}


	
	

   
	
	
}
