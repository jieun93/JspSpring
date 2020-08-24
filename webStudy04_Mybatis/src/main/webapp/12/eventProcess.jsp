<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/eventProcess.jsp</title>
</head>
<body>
<h4>서버 사이드 웹 어플리케이션의 이벤트 처리 </h4>
<pre>
1. 서버 이벤트 종류
	1) request     : 요청접수 / 응답전송(lifecycle), add/remove/replace
	2) session	   : 세션생성 / 세션만료 (lifecycle), add/remove/replace  
	3) application : 컨텍스트 로딩 / 컨텍스트언로드 (lifecycle), add/remove/replace
	
2. 이벤트 처리 단계 (클라이언트 사이드에서 이벤트 처리 )
	1) 이벤트 타겟 결정(button)  						: 서버사이드 어플리케이션 자체 
	2) 타켓에 대해서 어떤 이벤트가 처리되는지 결정 (click)
	3) 이벤트 핸들러를 만들어준다.(function) 			:  listner 구현 
	4) 타켓에다가 핸들러를 붙이는 과정이 필요하다.(on / onclick) : web.xml에 부착,  @webListener
	 
	



	
</pre>
</body>
</html>