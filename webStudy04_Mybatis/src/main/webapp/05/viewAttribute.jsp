<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/viewAttribute.jsp</title>
</head>
<body>
<h4>데이터 꺼내기</h4>
<pre style="background-color : yellow">
	page scope : <%=pageContext.getAttribute("pageScopeAttr") %>
	request scope : <%=request.getAttribute("requestAttr") %>
	session scope : <%=session.getAttribute("sessionAttr") %>
	<%
		// flash attribute : 저장 후 한번 꺼내고 , 삭제. , 메세지를 전달할때 많이 사용한다. 
		session.removeAttribute("sessionAttr");
	%>
	application scope : <%=application.getAttribute("applicationAttr") %>

</pre>
</body>
</html>