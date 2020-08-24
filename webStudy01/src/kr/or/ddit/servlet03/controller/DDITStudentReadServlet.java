package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;


@WebServlet("/ddit/studentView.do")
public class DDITStudentReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	// service랑 의존 관계 형성 
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
       
   // 의존관계형성 -> 요청분석 (코드 파라미터 -> 검증 ->상태 400.-> 학생한명을 조회 service를 통해서 read계열의 메서드로 가져옴 vo로  )->jsp로 보내줌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		
		int status =  200;
		String gopage = null;
		
		if(StringUtils.isBlank(code)) {
			status = 400;
		}else {
			try {
				
				DDITStudentVO vo =  service.readStudent(code);
				request.setAttribute("student", vo);
				gopage = "/WEB-INF/views/ddit/studentView.jsp";
			}catch(DataNotFoundException e) {
				// 정상처리 불가능  클라이언트가 잘못된 요청을 함
				status = 400;
			}
		}
		
		if(status == 200 ) {
			// 정상처리 
			request.getRequestDispatcher(gopage).forward(request, response);
		}else {
			//에러 발생
			response.sendError(status);
		}
		
		
		
		
		
		
		
		
		
		
	}
}
