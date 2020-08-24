<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--   
    response.setHeader("Content-Type", "text/plain");
  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/responseDesc</title>
</head>
<body>
<h4>HttpServletResponse (http response)</h4>
<pre>
	: 서버에서 클라이언트쪽으로 전송되는 응답에 대한 모든 정보를 가진 객체(request vs response 1:1 구조)
	http의 응답 패키징 방식
	1. Response Line : Protocol(version), Status Code
		Status Code(응답상태코드 /명령의 결과)
		100~ : ING... WEBSOCKET 양방향 프로토콜 
		200~ : ok 성공
	**  300~ : 클라이언트의 추가 액션이 필요한 상황 
			 304(Not Modified), 302/307(Moved, Location 헤더와 함께사용->Redirect 구조 /요청한 자원이 이미 다른곳으로 이동했다는거 )
		400~ : Client side Fail
				400(Bad Request/요청이 안넘어오는경우/ 숫자가 아닌 다른형태로 넘어오는경우 ...), 
				404(NotFound / 주소가 잘못 된서 발생되는 오류),
				403(Forbidden / 금지된 자원을 요청함 /보안 ),
				401(Unauthorized / 허가되지 않음 /보안  )
		500~ : Server side Fail / 서버의 정보를 클라이언트한테 노출시키지 않기 위해서 세분화 하지않음
		response.sendError(status_Code)
		<%-- <% if(1==1) throw new RuntimeException("강제발생예외"); %> --%>
	2. Response Header : body에 있는 컨텐츠에 대한 부가정보(meta data)
		: Content-Type, Content-Length,Cache-Control, Pragma, Expires, Refresh, Location
		1) 응답 데이터의 mime 설정 : Content-Type
			정석대로 설정하는 방법 =>response.setHeader("Content-Type","text/html;charset=UTF-8")
			response.setContentType("text/html;charset=UTF-8")
			jsp의 page 지시자의 contentType 속성
		2) 캐시제어 :Cache-Control, Pragma, Expires
			<a href="cacheControl.jsp">cacheControl.jsp 참고</a>
		3) auto request : Refresh
			<a href="autoRequest.jsp">autoRequest.jsp참고</a>
		4) 흐름제어 : Location
			<a herf="flowControl.jsp">flowControl.jsp</a>
			response.setHeader(name, value) 
			주의! value의 타입은 문자열이고, 특수문자가 포함되면 , URLEncoding 이 필요함  
		
		
	3. Response Body(Contet Body, Message Body):실제 응답 데이터 영역
	출력스트림 
	respose.getWriter, response.getOutputStream, out(JSPWruter)
</pre>
<img src="<%=request.getContextPath()%>/images/B000210202001301017213.jpg"/>
</body>
</html>