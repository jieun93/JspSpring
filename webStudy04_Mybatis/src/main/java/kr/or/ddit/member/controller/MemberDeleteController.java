package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class MemberDeleteServlet
 */
//@WebServlet("/member/deleteMember.do")
@CommandHandler
public class MemberDeleteController{
       
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/deleteMember.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//				
//		1. 누구를 탈퇴 시킬까?
//		2. 신원 확인
//		3. 탈퇴처리
//		4. 성공 :welcome Page로 이동 로그아웃 상태로 바꾸고 이동--> redirect  
//		5. 실패 : mypage로 이동
		
		//세션을 받아오기 
				HttpSession session = request.getSession(); // 기존에 있었던 id가져오기 
				MemberVO authUser = (MemberVO)session.getAttribute("authUser"); // 세션에서 꺼내오는거 
				String mem_id = authUser.getMem_id(); //authUser에서 id 꺼내오기
				
				String mem_pass = request.getParameter("mem_pass"); // 파라미터에서 꺼내오는거 
				
				if(StringUtils.isBlank(mem_pass)) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					
				}
				
				MemberVO vo = new MemberVO();
				vo.setMem_id(mem_id);
				vo.setMem_pass(mem_pass);
				
				
				
				Map<String, String> errors = new LinkedHashMap<>();
				request.setAttribute("errors", errors);
				String goPage = null;
				boolean redirect = false;
				String message = null;
				
				
				ServiceResult result = service.removeMember(vo); // 경우의 수 4개 
				
				switch(result){
				case INVALIDPASSWORD:
					message = "비밀번호오류";
					goPage = "redirect:/mypage.do";
//					redirect = true;
					session.setAttribute("message", message);
					break;
				case FAIL:
					message = "서버오류";
					goPage ="redirect:/mypage.do";
					
//					redirect = true;
					session.setAttribute("message", message);
					break;
				default : //ok
//					4. 성공 :welcome Page로 이동 로그아웃 상태로 바꾸고 이동--> redirect 
					goPage="redirect:/";
					session.invalidate(); //invalidate 한 후에는 session을 쓸수 없다. 
//					redirect= true;
					break;
				}
				return goPage;
				

			
				
				
	}
	
}