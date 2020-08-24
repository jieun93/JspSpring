package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAuthenticateService service = AuthenticateServiceImpl.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		if(session == null || session.isNew()) {
			response.sendError(400,"폼이 없습니다. 로그인 불가능 ");
			return;
		}
			
		// 1. 인코딩 설정 
	 	request.setCharacterEncoding("UTF-8");
		// 2.파라미더 확보
	 	String mem_id =	request.getParameter("mem_id");
		String mem_pass = request.getParameter("mem_pass");
		String  idSave = request.getParameter("idSave");
		// 3. 검증
		String goPage = null;
		boolean redirect =false;
			
		// 검증
		// 경우의 수  아이디와 비번이 같게 나와야 하는경우에서 
		//1. 값이 비었을때 2. 값이 맞을 떄, 3.값이 다를때 
		 
		
		 String message = null;
		 if(StringUtils.isBlank(mem_id) ||StringUtils.isBlank(mem_pass)){
			 //불통
//	  	 bad request 전송
//	 		 response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//	 			return;	 
			goPage = "/login/loginForm.jsp";
			redirect = true;
			message ="누락데이터발생";
		 }else {
			 
			 MemberVO member = new MemberVO(mem_id, mem_pass);
			 ServiceResult result = service.authenticated(member);
			 		 
		// 통과
		 if(ServiceResult.OK.equals(result)){
			// 인증 성공 (id == pass)
			 goPage ="/";
			 redirect = true;
			 session.setAttribute("authUser", member); // 아이디 하나만 담음 / 멤버가 가지고 있는 컬럼들을 가지고 있어야 한다. 
			 
//			 // 체크박스를 선택했을때 
//			 // 검증 
			 
			 Cookie idCookie = 
					 CookieUtils.createCookie("idCookie", mem_id, request.getContextPath(), TextType.PATH);
			 
			 int maxAge = 0;
			 if("saveId".equals(idSave)) {
				 // 맥스 에이지 일주일
				 maxAge = 60*60*24*7;
			 } // 맥스 에에지 0 초
			 
			 idCookie.setMaxAge(maxAge);
			  response.addCookie(idCookie);  		 
			 
			 
			 
//		 if(StringUtils.isNotBlank(checked) || checked !=null) {
//			 // 체크박스를 선택하면 아이디를  쿠키에 저장한다. 	 
//			 // 쿠키 저장하기 
//			 Cookie loginCookie = new Cookie("loginCookie",id ); 
//			 loginCookie.setMaxAge(3*60*60*24); // 3일 유효기간 
////			 loginCookie.setPath(request.getContextPath());
//			 response.addCookie(loginCookie);
//			 
//			 }else {
//				 //체크박스를 선택하지 않았을때는 
//				 
//				 Cookie loginCookie = new Cookie("loginCookie",id ); 
//				 loginCookie.setMaxAge(0); // 쿠키를 바로 지워준다. 
//				
//				 response.addCookie(loginCookie);
//			 }
		}else{
		// 실패
			goPage ="/login/loginForm.jsp";
			redirect = true;
			message ="존재 하지 않거나, 비번 오류, 인증실패";
			}
		 }
		// 이동하기 전단계에서 공유하기 위한 설정을 하는거 
		
		session.setAttribute("message", message);	// session scope에 attribute 저장 
		
		/****** ????***** */
		if(redirect){
			response.sendRedirect(request.getContextPath()+goPage);
		}else{
			request.getRequestDispatcher(goPage).forward(request, response);
		}
		                                        
	}

}
