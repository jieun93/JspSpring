package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class Mypage
 */
//@WebServlet("/mypage.do")
@Controller
public class MypageController {
//	private static final long serialVersionUID = 1L;
    @Inject
	IMemberService service;
  
	@GetMapping("/mypage.do")
	/* requestToViewNameTranslator */
	public ModelAndView	mypage(HttpSession session) throws ServletException, IOException {
//		HttpSession session =  request.getSession();
		
		MemberVO authUser = (MemberVO)session.getAttribute("authUser");
		
		String mem_id = authUser.getMem_id();
//		model.addAttribute("member", member);
		MemberVO member = service.readMember(mem_id); // 서비스에 mem_id를 보내는거 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("member/mypage");
//		String goPage ="member/mypage"; 
		return mav;
		
	}

}
