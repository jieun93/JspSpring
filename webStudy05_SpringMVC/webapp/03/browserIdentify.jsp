<%@page import="kr.or.ddit.enums.BrowserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사용자의 브라우저 종류를 식별
"당신의 브라우저는 zzz 입니ㅣ다." 메세지를 alert 창으로 출력
explore : 익플
chrome : 크롬
firefox : 파이어폭스
other : 기타등등 
이넘을 써야 한다. 
각 브라우저마다 식별할수 있는 키워드를 찾아야 한다.
  <%
 	String agent = request.getHeader("User-Agent");
	String desc =  BrowserType.browserIdentify(agent); 	
	String message = String.format("당신의 브라우저는 %s입니다.",desc); 	
 %>
 <script type="text/javascript">
 	alert("<%=message %>");
 </script>
</body>
</html>