<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginFrom.jsp</title>
<% 
//세션 속성명이 name인 속성의 값을 Object 타입으로 리턴한다. 해당 되는 속성명이 없을 경우에는 null 값을 리턴한다.
// loginprocess.jsp 연결되는 부분 
	String message = (String) session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){ // 빈칸이 비어있지 않으면 
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
		session.removeAttribute("message");
	}
%>
</head>
<body>
<%-- <form action="<%=request.getContextPath() %>/login/loginprocess.jsp" method="post"> --%>
<form action="${pageContext.request.contextPath}/login/login.do" method="post"> <!-- 익스프레션랭귀지 -->
<ul>
	<%
// 		String failedID = request.getParameter("mem_id"); //  리다이렉ㅅ션으로 오면 필요없음
		CookieUtils cookieUtils = new CookieUtils(request);
		String saveId = cookieUtils.getCookieValue("idCookie");

// // 		 //쿠키 꺼내오는거 
//  		 Cookie[] cookies = request.getCookies(); //쿠키에서 id가져오기 
//  		 String selected  = null;
//  		 if(cookies!=null){
//  			 for(Cookie tmp : cookies){
//  				 if("loginCookie".equals(tmp.getName())){ //입력한 아이디랑 기존의쿠키의 아이디중 하나랑 같으면 
// 					 String name = tmp.getName(); // 입력한 id 값 
// 					 selected = URLDecoder.decode(tmp.getValue(),"UTF-8"); // 
//  				 }
//  			 }
//  		 }
//  		 String defaultVal = null;
//  		 if(selected !=null){
//  			 defaultVal = selected;
//  		 }else{
//  			 defaultVal =  Objects.toString(failedID,"");
//  		 }
		 
	%> 
	<li>
												<!-- 잘못된 아이디를 넣으면 아이디 값이 초기화 되도록 설정 -->
		아이디 : <input type="text" name="mem_id" value="<%=Objects.toString(saveId,"")%>"/>
		<input type="checkbox" name="idSave" value="saveId"  <%=cookieUtils.exists("idCookie")?"cheked" : ""%>/> 아이디 저장하기
		
	</li>
	<li>
		비밀번호  : <input type="text" name="mem_pass"/>
		<input type="submit" value="로그인"/>
	</li>
</ul>
</form>

</body>
</html>