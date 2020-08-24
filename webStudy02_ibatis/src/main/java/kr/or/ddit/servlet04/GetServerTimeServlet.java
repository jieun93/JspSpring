package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 서로 다른 언어로 구현된 이기종 시스템 간의 커뮤니케이션 
 * JavaScript		(XML/JSON)				Java
 * 1. 메시지 전송 
 * 		native data(Java) -> marshalling -> Serialization ~~~~~
 * 2. 메세지 수신
 * 		~~~~~ Deserialization -> Unmarshalling  -> native data (JavaScript)
 * 
 */


//요청 받음 1
@WebServlet("/getServletTimd.do")
public class GetServerTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
		// 모든 요청을 다 받을거 
		// 요청을 분석 2
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청을 분석 (클라이언트가 요구하는 응답 컨텐츠의 형태, Accept request header)
		// 비동기(ajax ->dataType:[html(text/html), json(application/json), xml(application/xml), text(text/plain)])
		// data type이 결정되는 속성 accept 
		String accept =  req.getHeader("Accept");
		Date serverTime = new Date();
		
		if(accept.contains("html")) {
			
			req.setAttribute("serverTime", serverTime);
			String goPage = "/WEB-INF/views/getServletTimd.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
			
		}else {
			// 마쉘링과 직렬화가 반복이 된다.
			Object content = serverTime;
			String mime= "text/plain;charset=UTF-8";
			
			if (accept.contains("xml")) {
				// 자바객체를 xml으로 바꾸고 내보내는거
				// 마쉘링, 직렬화
				mime = "application/xml;charset=UTF-8";
				// <time>data</time>
				content = String.format("<time>%s</time>",serverTime.toString());
			} else if (accept.contains("json")) { // accept의 요청이 어떤거냐에 딸라 달라진다. 
				// 마쉘링, 직렬화
				mime = "application/json;charset=UTF-8";
				//json {"time":"시간"}
				content = String.format("{\"time\":\"%s\"}", serverTime.toString()); // 마쉘링 
			}
			
			resp.setContentType(mime);
			
			
			// 직렬화
			try(
				PrintWriter out =  resp.getWriter();	
			){
				out.print(content);
			}
		}
		
	}

}
