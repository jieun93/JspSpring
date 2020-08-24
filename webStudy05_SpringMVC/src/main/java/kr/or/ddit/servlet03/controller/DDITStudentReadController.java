package kr.or.ddit.servlet03.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

// 학생 한명 선택했을때 창 띄우는거 

//@WebServlet("/ddit/studentView.do")
@Controller
public class DDITStudentReadController{
	private static final long serialVersionUID = 1L;
	 
	// service랑 의존 관계 형성 
	@Inject
	IDDITStudentService service;
       
   // 의존관계형성 -> 요청분석 (코드 파라미터 -> 검증 ->상태 400.-> 학생한명을 조회 service를 통해서 read계열의 메서드로 가져옴 vo로  )->jsp로 보내줌
	@GetMapping(value="/ddit/studentView.do")
	public String doGet(@Valid @RequestParam(required = false)String code,
						Model model
						) throws ServletException, IOException {
		
//		String code = request.getParameter("code");
		
//		int status =  200;
		String gopage = null;
		
		
//		if(StringUtils.isBlank(code)) {
//			status = 400;
//		}else {
			try {
				
				DDITStudentVO vo =  service.readStudent(code);
				
				model.addAttribute("student", vo);
				gopage = "ddit/studentView";
			}catch(DataNotFoundException e) {
				// 정상처리 불가능  클라이언트가 잘못된 요청을 함
				throw new RuntimeException(e);
			}
//		}
		
//		if(status != 200 ) {
//			// 에러 
//			
//			response.sendError(status,"ㅇㅇ");
//			return null;
//		}else {
//			
			return gopage;
//		}
		
		
		
		
		
		
		
		
		
		
	}
}
