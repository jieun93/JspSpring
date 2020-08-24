package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(MemberViewServlet.class);
	
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String select = req.getParameter("who");
		
		int status = 200;
		String goPage = null;
		
		if(StringUtils.isEmpty(select)) {
			status = 400;
		}else {
			try {
				MemberVO vo = service.readMember(select);
				req.setAttribute("member", vo);
				goPage ="/WEB-INF/views/member/memberView.jsp";
			} catch (DataNotFoundException e) {
				status =400;
			}
		}
		if(status == 200) {
			req.getRequestDispatcher(goPage).forward(req, resp);
			
		}else {
			resp.sendError(status);
		}
		
	}
}
