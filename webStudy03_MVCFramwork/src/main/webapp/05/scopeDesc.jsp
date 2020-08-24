<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/scopeDesc.jsp</title>
</head>
<body>
<h4>Scope(영역)</h4>
<pre>
	: 웹 어플리케이션에서 데이터를 공유할 목적으로 관리하는 저장 공간 .(메모리)
	 저장공간의 사용범위에 따라 달라진다. 
	: 생존범위(사용범위)가 제한된 4개의 기본 객체가 관리하는 공간(Map)
	: scope를 통해 공유되는 데이터  : Attribute(name, value)
	공유해야 하는 데이터가 있을 떄  각각의 특성에 의해 사용된다.
	이동구조의 특성과 연관지어 생각해야 한다.
	스코프를 사용할때 최소한의 영역을 골라야 한다. 
	1. page[Context] scope : pageContext에 의해 제어됨.	하나의 jsp로 사용 범위 제한. 공유할수없는 영역 
	2. request scope	: request에 의해 제어됨. 해당 request가 소멸되는 순간  공간의 사용종료.
	3. session scope	: session에 의해 제어됨. 해당 session이내에서만 사용. 
	4. application scope	: ServletContext에 의해 제어됨. 서버가 죽기전까지는 살아있음 
	
	
	Attribute를 이용하는거 
	저장 :setAttribute(String name, Object value)
	가져오는거 : getAttribute(name)
	제거 : removeAttribute(name)
	
	<%
		pageContext.setAttribute("pageScopeAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청속성");
		session.setAttribute("sessionAttr", "세션속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
// 		pageContext.include("/05/viewAttribute.jsp");
// 		response.sendRedirect(request.getContextPath()+"/05/viewAttribute.jsp");
	
	%>
	
	<a href="<%=request.getContextPath()%>/05/viewAttribute.jsp">확인 ㄱㄱ</a>
</pre>
</body>
</html>