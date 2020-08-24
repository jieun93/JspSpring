<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorLocal.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/pageContextDesc.jsp</title>
</head>
<body>
<h4> PageContext pageContext</h4>
<pre>
	 : 해당 jsp 페이지에 대한 모든 정보를 캡슐화 하고 있는 객체로, 모든 객체중 가장 먼저 생성됨
	 1. 나머지 기본 객체 확보 (!!!!!중요!!!!!)
	  두 객체가 같은지를 확인 : <%=request == pageContext.getRequest() %>
	 	 <%=application == pageContext.getServletContext() %>
		<%=session ==pageContext.getSession() %>
		
	 2. 페이지 흐름 제어 (이동이 가능하다)  --1번의 기능이 존재 하지 않으면 2번의 기능도 존재 하지 않는다.
			: forward, include
		<%
//			두개가 같은거 		
// 			pageContext.forward("/05/applicationDesc.jsp");
// 			request.getRequestDispatcher("/05/applicationDesc.jsp").forward(request, response);
// 			pageContext.include("/05/applicationDesc.jsp"); // 페이지 모듈화에서 사용 
			
// 			request.getRequestDispatcher("/05/applicationDesc.jsp").include(request, response);					
		
		%> 
		인클루드 이후의 ui
		
	  3. 에러 데이터 확보하기 
	  	<%
// 	  		 jsp는 서블릿이어서 에러가 안난다.
	  	 	if(1==1)throw new IOException("강제발생예외");
	  		ErrorData ed = pageContext.getErrorData();
			String requestURI =ed.getRequestURI();
			Throwable e = ed.getThrowable();
			int sc = ed.getStatusCode();
	  	
	  	%>
	  	<%=requestURI %>
	  	<%=e %>
	  	<%=sc %>
	  	
	  	4.Scope의 Attribute 확보 (!!!!중요!!!!)
		
</pre>

</body>
</html>