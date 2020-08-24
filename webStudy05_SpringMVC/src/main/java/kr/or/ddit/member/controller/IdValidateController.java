package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.service.IMemberService;

//@WebServlet("/member/idValidate.do")
@RestController
@RequestMapping(value = "/member/idValidate.do", 
					produces = "application/json;charset=UTF-8")
public class IdValidateController {
	// 동기요청을 해야 한다. 
	@Inject
	IMemberService service;
	
	@PostMapping
	public Map<String, Object>  validate( @RequestParam(required = false) String mem_id )  {
		
//		String mem_id =req.getParameter("inputId");
		
//		if(StringUtils.isBlank(mem_id)) {
//			resp.sendError(400," 아이디 누락 ");
//			return null; // 이미 에러에 대한 상태코드가 나갔음 또 나갈 필요가 없음
//		}
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
		
//		resp.setContentType("application/json;charset=UTF-8");
		
//		ObjectMapper mapper = new ObjectMapper();
//		try(
//				PrintWriter out = resp.getWriter();
//			){
//			
//			mapper.writeValue(out, resultMap);
//		}
//		return null;
		
		return resultMap;
		
	
	}

}
