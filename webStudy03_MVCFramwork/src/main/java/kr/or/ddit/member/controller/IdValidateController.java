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
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/idValidate.do")
@CommandHandler
public class IdValidateController {
	// 동기요청을 해야 한다. 
	
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value = "/member/idValidate.do", method = HttpMethod.POST)
	public String validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mem_id =req.getParameter("inputId");
		
		if(StringUtils.isBlank(mem_id)) {
			resp.sendError(400," 아이디 누락 ");
			return null; // 이미 에러에 대한 상태코드가 나갔음 또 나갈 필요가 없음
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
		return null;
		
		
		
	
	}

}
