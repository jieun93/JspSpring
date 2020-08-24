package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/idValidate.do")
public class IdValidateServlet extends HttpServlet {
	// 동기요청을 해야 한다. 
	
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String mem_id =req.getParameter("mem_id");
		
		if(StringUtils.isBlank(mem_id)) {
			resp.sendError(400," 아이디 누락 ");
			return;
		}
		// 마쉘링, 직렬화 할 것
		Map<String, Object> resultMap = new HashMap<>();
		
		String result = null;
		
		try {
			service.readMember(mem_id);
			 result = "DUPLICATE";
			 		
		} catch (DataNotFoundException e) {
			result = "OK";

		}
		resultMap.put("result", result);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		try(
				PrintWriter out = resp.getWriter();
			){
			
			mapper.writeValue(out, resultMap);
		}
		
		
		
	
	}

}
