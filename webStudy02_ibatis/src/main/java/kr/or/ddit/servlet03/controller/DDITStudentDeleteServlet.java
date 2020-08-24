package kr.or.ddit.servlet03.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;

/**
 * Servlet implementation class DDITStudentDeleteServlet
 */
@WebServlet("/ddit/studentDelete.do")
public class DDITStudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 의존관계
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();

	// form 으로 전송 방식 --> post
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		1. 요청 분석 (주소, 파라미터, 헤더)
		String code =  request.getParameter("code");
//		2. 검증  통과 / 불통
		if(StringUtils.isBlank(code)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
			return;
		}
//		3. 통과 - 로직
		// 삭제하기 위한 로직
		ServiceResult result =  service.removeStudent(code);
		String goPage= null;
		boolean redirect = false;
		
		if(ServiceResult.OK.equals(result)) {
			// 삭제 하면 목록으로 이동 
			// 정보를 남겨둘 필요가 없으니 redirection 으로 이동
			goPage= "/ddit/dditStudent.do";
			redirect = true;
			
		}else {
			// 삭제가 안된 경우  view 페이지로 
			// dispatcher 명령이 많이 남아 있고 그대로 가져가야 하는 경우 
			
			goPage = "/ddit/studentView.do?code="+code;
			redirect = true;
			
		}
		
//		4. 컨테츠 공유 (scope)
//		5. view선택 -> 이동 
		if(redirect) {
			response.sendRedirect(request.getContextPath()+goPage);
		}else {
			request.getRequestDispatcher(goPage).forward(request, response);
			
		}
		
		
	}

}
