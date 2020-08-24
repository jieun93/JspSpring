<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/cacheControl.jsp</title>
</head>
<body>
<h4>캐시제어</h4>
<pre>
	Cache ??  두 피어 사이에 데이터가 전송 될때 발생하는 속도차이를 보완하기 위해 사용되는 임시 데이터//모든브라우저는 자신만의 캐시메모리를 가지고 있다. 
	
	캐시를 남겨서는 안되는 경우??  결제 
	클라이언트에 저장된다(캐시데이터 ) 
	
	제어할수 있는 헤더의 종류
	1) Pragma: Http 1.0
	2) Cache-Control :Http 1.1
	3) Expires	: 버전공통, 캐시 만료 설정
	
	웹표준화 전략 
	클라이언트이 시스템이 어떤것이라도 웹 접근성을 보장해야 한다. 
	
	<%	// 캐시를 제어하려면 3가지가 필요하다.
		response.setHeader("Pragma", "no-cache");	// 1.0버전으로 캐시를 남기지 말아라 
		response.setHeader("Cache-Control", "no-cache"); // 1.1
		response.addHeader("Cache-Control", "no-store"); // Firefox  캐시를 남기지 말아라 
		
		response.setDateHeader("Expires", 0);
		
	
	%>
</pre>
</body>
</html>