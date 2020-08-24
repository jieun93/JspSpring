<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원 식별</h4>
<pre>
	URI(Uniform resource Identifier) : 자원을 식별하는 통칭하는 언어이다. 
	1. URL(Locator) : root를 기준으로 경로를 설정하고 자원을 식별.
	2. URN(Name)	: 자원에 명명규칙을 적용.
	3. URC(Content)	: 자원의 속성을 통해 식별 .
	
</pre>
URL 표현 방법 <br>
1.절대경로  / 가 붙어있는거  : 루트부터 모든 경로를 표기
	경로를 어디서 사용하느냐에 따라 사용하는 방법이 달라진다. 
	1) client side :  도메인, ip 이후의 컨테스트명부터 모든경로를 표기 
	ex) /webStudy01/images/prod-1.jpg
	
	2) server side : context root 이후의 경로를 표기 
	 ex) /images/B000210202001301017213.jpg
	 자신의 위치를 알고 있음  
	 시작위치가 다르다.
	<%
// 		ServletContext ctx = getServletContext();
// 		out.println(application ==ctx);
		String realpath = application.getRealPath(request.getContextPath()+"/images/B000210202001301017213.jpg");
		// application : 서버사이드에서 웹리소스를 찾을 떄 쓴다.
		// 자원을 찾아 낼때는 바뀌지 않는 경로를 찾아 내야 한다. 
		out.println(realpath);
	%>
	
2.상대경로 	: 현재 위치를 기준으로 상대적으로 경로를 표기 


<br>
절대경로 표현방식  2가지
<img src="http://localhost/sample/images/B000210202001301017213.jpg" /> <!-- 하드코딩 -->
<img src="<%=request.getContextPath()%>/images/B000210202001301017213.jpg" /> <!-- 클라이언트사이드 절대 경로 -->
<br><br><br><br>
상대경로  현재위치르 봐야 하낟. 브라우저을 봐야 함
<img src="../images/B000210202001301017213.jpg" />
</body>
</html>