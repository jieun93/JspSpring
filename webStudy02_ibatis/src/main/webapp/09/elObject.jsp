<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/elObject.jsp</title>
</head>
<body>
<h4> EL 기본객체</h4>
<pre>
	1. scope 객체 (Map&lt; String, Object&gt;) : page, session, request, application
		${attr_name } : 범위가 좁은 영역부터 검색.
		${aapplicationScope.attr_name } : 특정영역의 속성을 찾는다.
		${aapplicationScope["attr_name"] } : 특정영역의 속성을 찾는다.
		  
	2. requset parameter 객체 : param(Map&lt;String,String&gt;)(모든파라미터를 갖고 있는 특성), paramValues(Map&lt;String,String[]&gt;)
		<%=request.getParameter("name") %>, el표현 방식 => ${param.name } 한개값만가져옴, ${param["name"] }
		<%=request.getParameterValues("name")[0]%> , ${paramValues.name[0] }  여러개를 가져오고 싶을때, ${paramValues["name"][0]}
	
	3. request Header 객체	: header(Map&lt;String,String&gt;), headerValues(Map&lt;String,String[]&gt;)(여러의 header를 배열로 가져옴 )
		표현식:<%=request.getHeader("Accept") %>
		el : ${header.accept }
		el : ${header["accept"]}
		표현식: <%=request.getHeader("User-Agent") %>
		el :  ${header.user-agent}  ==> 연산자로 수행 됨? null과 null를 0으로 바꿈
		      ${header["user-agent"]}
		      ${headerValues["user-agent"]}
		      
	4. context parameter 객체 : initParam (Map&lt;String,String&gt;)
		표현식: <%=application.getInitParameter("contentsPath") %>
		 el : ${initParam.contentsPath }
	
	
	5. cookie 객체 :cookie (Map&lt;String, Cookie&gt;)
		표현식: <%=new CookieUtils(request).getCookieValue("JSESSIONID") %>
		속성 : ${cookie.JSESSIONID.value }	
		이름 : ${cookie.JSESSIONID.name }	
	
	
	6. pagecontext :
		${pageContext.request.contextPath}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</pre>
</body>
</html>