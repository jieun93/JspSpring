package kr.or.ddit.commons.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class Mypage
 */
@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	IMemberService service = MemberServiceImpl.getInstance();
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		
		MemberVO authUser = (MemberVO)session.getAttribute("authUser");
		
		String mem_id = authUser.getMem_id();
		MemberVO member = service.readMember(mem_id); // 서비스에 mem_id를 보내는거 
		request.setAttribute("member", member);
		String goPage ="/WEB-INF/views/member/mypage.jsp"; 
		request.getRequestDispatcher(goPage).forward(request, response);
		
	}

}
