package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;




//@WebServlet("/login/logout.do")
@Controller
public class LogoutServlet{
	private static final long serialVersionUID = 1L;
	
	@PostMapping("/login/logout.do")
	public String doPost(HttpSession session,HttpServletRequest request) throws ServletException, IOException {
		// 세션을 검증해야한다. 
//		HttpSession session =  request.getSession();
//		if(session== null || session.isNew()) {
		//최초의 요청이 들어옴  로그아웃을 할 수 없다.

		if(!session.isNew()) {
			// 로그아웃 하기
//			session.removeAttribute("authUser"); //로그아웃이 되면 세션이 만료  새로운 아이디가 만들어짐 
			session.invalidate(); // 세션스코프 안에 있는 모든 속성을 다 지워야 한다. 
		}
		// 로그아웃되면 웰컴페이지로 가기
//		String goPage = "/";
//		response.sendRedirect(request.getContextPath()+goPage);
		return "redirect:/";
		
		
	}

}
