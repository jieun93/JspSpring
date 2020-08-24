package kr.or.ddit.commons.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.IndextType;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;


//@WebServlet("/index.do")
@CommandHandler
public class IndexController{
	private static final long serialVersionUID = 1L;
	
	@URIMapping("/")
	public 	String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		1. 파라미터 꺼내서
 leftMenu를 선택했을 때 해당 파일을 불러오기
 선택한 값 가져오기  -> 이넘에 파일이름을 쓰고 이넘파일의 인덱스 번호랑 leftMenu의 이름이 같은지 확인
 -> indexservlet에서 요청 받은 파라미터 값을 가져와서 요청을 분석하기 ->	비동기식처리로 내용 공유 	
		2. 스탠다드 JSP 
		이넘을 사용해서 가져오기
		
	 이넘에서의 파라미터와 동일한 파라미터가 있는 경우에만 컨테츠 url을 갖도록 해야한다. 
	 
	  jsp 에서 content에 경로를 설정해야한다. 
	  경로를 맞출때 파일이 존재하는지에 대한 여부를 검사해야한다.
	  --커스텀태그를 쓰는 이유는 가독성을 높이기 위해서 사용한다.--
		 		
*/
		String command = request.getParameter("command");
		//command의 값이 무엇인지 확인하기 --> 이넘의 파라미터
		
		 if(StringUtils.isNotBlank(command)) {
			 try {
				String desc = IndextType.findContentUrl(command); // 이넘의 파라미터 받은 값
				request.setAttribute("includePage", desc);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		 }
		 
		return "index";
//		request.getRequestDispatcher(goPage).forward(request, response);
	}

	

}
