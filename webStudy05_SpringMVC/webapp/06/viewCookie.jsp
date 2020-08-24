<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/viewCookie.jsp</title>
</head>
<body>
<h4> 다른 경로에서 쿠키 확인하기 </h4>
<pre>
<%
	//검증
	Cookie[] cookies =  request.getCookies();
	if(cookies!=null){
		for (Cookie tmp : cookies) {
			String name = tmp.getName();
			String value = URLDecoder.decode(tmp.getValue(), "UTF-8");
			out.println(String.format("%s : %s", name, value));
		}
	}
%>
</pre>
</body>
</html>