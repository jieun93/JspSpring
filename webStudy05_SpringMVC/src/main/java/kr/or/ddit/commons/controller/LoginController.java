package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginController{
	@Inject   // 컨테이너를 받아올수있다. 
	WebApplicationContext container; 
	ServletContext application;
	
	
	@PostConstruct 
	public void init() {
		application = container.getServletContext();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IAuthenticateService service;

//	@URIMapping(value="/login/login.do", method=HttpMethod.POST)
	@PostMapping("/login/login.do")
	public String doPost(HttpSession session,
						@RequestParam(required = false) String mem_id, 
						@RequestParam(required = false) String mem_pass, 
						@RequestParam(required = false) String idSave,
						HttpServletResponse response,
						Model model) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
		
//			if(session.isNew()) {
////				response.sendError(400, "폼이 없는데 어케 로그인해???");
//				throw new DataNotFoundException();
////				return null;
//			}
		
	// 2. 파라미터 확보
//		String mem_id = request.getParameter("mem_id");
//		String mem_pass = request.getParameter("mem_pass");
//		String idSave = request.getParameter("idSave");
		
	// 3. 검증    
		String goPage = null;
		String message = null;
		if(StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)){
//	 	2) 불통
//	 		: Bad Request 전송
//	 		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//	 		return;
			goPage = "redirect:/login/loginForm.jsp";
			message = "누락 데이터 발생";
		}else {
			MemberVO member = new MemberVO(mem_id, mem_pass);
			 ServiceResult result = service.authenticated(member);
//		 	1) 통과
//		 		a) 인증 (id==password)
			if(ServiceResult.OK.equals(result)){
//		 			성공 : 웰컴페이지로 이동, redirect (command 처리 완료)
				goPage = "redirect:/";
				session.setAttribute("authUser", member);
				Cookie idCookie = CookieUtils.createCookie("idCookie", mem_id, 
						application.getContextPath(), TextType.PATH);
				int maxAge = 0;
				if("saveId".equals(idSave)) {
					maxAge = 60*60*24*7;
				}
				idCookie.setMaxAge(maxAge);
				response.addCookie(idCookie);
				
			}else{
//		 			실패 : loginForm.jsp 로 이동, forward
				goPage = "redirect:/login/loginForm.jsp";
				message = "존재하지 않거나, 비번 오류, 인증 실패";
			}
		}
		
//		model.addAttribute("message", message); // session scope 에 attribute 저장.
		
		return goPage;

	}
	
	
}



