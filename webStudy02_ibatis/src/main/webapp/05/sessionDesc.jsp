<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/sessionDesc.jsp</title>
</head>
<body>
<h4> HttpSession session</h4>
<pre>
	세션?
	 (시간 , 통로 )
	 시간 (웹) , connectless  : 한 클라이언트가 하나의 브라우저를 이용해서 서버 어플리케이션을 사용하고 있는 동안.
	 세션 시작   : 최초 요청 발생시 , 세션 아이디 부여  (로그인이 유지되는 기간 )
	 세션 아이디 : <%=session.getId() %>
<%-- 	 세션을 유지할 목적으로 사용하는 거 <a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션유지 </a> --%>
	 <a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션유지 </a>
	세션 생성 시점 : <%=new Date(session.getCreationTime()) %> 
	세션의 마지막 요청 시점 :<%=new Date(session.getLastAccessedTime())%>
	시간연산을 하기위해서 사용되는거 getLastAccessedTime
	세션의 timeout : <%=session.getMaxInactiveInterval()%> 's  <!-- xml에서 4분으로 셋팅함 -->
	 세션이 길면 노출될수 있는 경우가 크다. 해킹의 위험 
	 
	<%
	 session.setMaxInactiveInterval(4*60);	/* 세션의 timeout을 셋팅한거  */
	%>
	세션의 timeout : <%=session.getMaxInactiveInterval()%> 's
	
<%-- 	 
// 	 	session.invalidate();
 --%>
 			세션 유지 방법(tracking-mode)
 			1)Cookie : 세션 아디드를  JESSEIONID 와 같은 쿠키의 형태로 클라이언트에 저장
 			2) URL	: jessionid 와 같은 세션 파라미터의 형태로 새션을 전송(request line).
 			3) SSL : 세션을 유지하기 위한 정보를  secure layer 를 통해 전송
	 
	 			세션 종료
	 		1) timeout, 일정만료 시간을 설정하고, 만료 시간이내에 새로운 요청이 발생하지 않으면.
	 		2) 쿠키 삭제 
	 		3) 브라우저의 종료 
	 		4) 로그아웃  
	 				
	 통로 (데이터 베이스 ) : 서버와 클라이언트 사이의 유일하게 개방된 통로 / 데이터가 오고갈 수 있는 통로?
	 
</pre>
</body>
</html>