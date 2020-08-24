<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	Http : Connectionless / Stateless : 비연경지향 무상태 특성 
	상태가 유지가 안되서 트랜잭션이 안된다는게 단점
	해당 단점을 보완하는 방법 : session, cookie 
	session(서버공간), cookie(클라이언트)의 차이는 저장의 차이
	Cookie : 대화 유지를 위한 상태 정보를 클라이언트 쪽에 저장하는 개념/ 서버의 저장공간을 덜기위해서 클라이언트에 저장하는거 /
			/ 작은량의 정보만 저장됨 // 쿠키는 데이터의 타입이 문자열만 가능 // 일종의 헤더이다.
		쿠키 사용 단계
		1.쿠키 생성 (서버에서 작성함)
		2. 응답과 함께 클라이언트로 전송 (서버에서 만든 쿠키를 클라이언트에 보내야 한다.)
		 
		3. 브라우저별 쿠키 저장소에 각기 따로 저장. (클라이언트) 
		4. 다음요청이 발생할때 함께 서버로 재전송. 
		
		5. 요청에 포함된 쿠키를 통해 상태 복원.
		
		----------------------------------
		**쿠키의 속성
		1. name
	  **2. value : 문자열만 가능 , 특수문자가 포함된다면, URL  encoding  방식으로 인코딩 필요**
		3. domain/host  : 쿠키를 다음 요청에 재전송시킬때 포함 여부를 결정 .
				만약, host name을 생략하면, 해당 기관의 모든 서버를 대상으로 쿠키 재전송 
				이클립스 8.5에서는 가상도메인을 안쓴다. 
				**!!!주의!!!**
				쿠키의 출처와 설정된 domain이 다른경우, 사용할 수 없음 
				
		 	
			www.naver.com   3레벨 구조  com (company)  - GTLD(Global Top Level Domain)
			www.naver.co.kr 4레벨 구조	kr(korea)		- NTLD(National Top Level Domain)
			email.naver.com
			blog.naver.com
			
		4. path : 생략한 경우, 쿠키가 생성된 경로로 설정됨.   //path로 설정된게 있으면 쿠키가 전송된다. 
			다음요청이 설정된 경로 이하로 발생한 경우, 쿠키가 재전송됨.
			 
		도메인과 path를 포괄적으로 사용된다. 
		보안에 걸리는 쿠키가 잇으면 도메인을 빼고 path를 명시적으로 붙여야 한다. 	
		
		5. maxAge: 데이터 저장 기간 , 세션보다 오래 사용하기위해  
			생략시, 기본값으로 세션과 동일한 만료시간이 설정됨.(초단위로 설정 됨)
			0 --> 기존의 저장되어 있던 쿠키까지 삭제(magAge 이외의 나머지 속성이 동일한 쿠키만 삭제됨)
			-1 --> 브라우저 종료시 만료됨.
			
			
		
		 <%
	// 		 	Cookie tmpCookie = new Cookie("firstCookie","FirstCookie");
// 			response.addCookie(tmpCookie); // 한번에 20개까지 응답데이터를 포함할수있다.
		
	// 쿠키 속성 확인 
// 			String cookieValue =  URLEncoder.encode("한글 국산 쿠기", "UTF-8");
// 			Cookie koreanCookie = new Cookie("koreanCookie", cookieValue);
// 			response.addCookie(koreanCookie);

//				톰캣 7.0으로 테스트  도메인 
// 			Cookie allDomainCookie = new Cookie("allDomainCookie","All~~Domain");
// 			allDomainCookie.setDomain(".yje.com");   // ".naver.com" 모든 호스트를 대상으로 전달된다.
// 			response.addCookie(allDomainCookie);

	//path 설정 
	// 네임 +도메인+패스 가 다 동일해야 같은 쿠키로 판단한다. 
// 			Cookie allPath = new Cookie("allPath", "All~~Path!!");
// // 			allPath.setPath(request.getContextPath());  //웹스터디 01 아래의 모든패스를 가져올수잇ㅁ음
// 			allPath.setPath(request.getContextPath()+"/06");  // 로컬호스트까지만 / 클라이언트가 쓰는 거 
// 			response.addCookie(allPath); // 현재 쿠키를 생성하는 경로로 기본쿠키로 잡힘
	
	Cookie longLive = new Cookie("longLive","long~~~live");
// 	longLive.setPath(request.getContextPath());
// 	longLive.setMaxAge(60 * 60 * 24 * 7); // 쿠키가 유지되는 시간 설정
	longLive.setMaxAge(0); //브라우저가 종료되면 바로 삭제   (0)  => 만료시간을 주지않겠다. 기존의 쿠키를 지우고 싶을때
// 	longLive.setHttpOnly(true); // 무조건 하나의 프로토콜만 전송하겠다.
	longLive.setSecure(true); // https 만 사용하겠다는 의미 (보안) 
	response.addCookie(longLive);

	// 재전송을 확인
	// 			String value = null;
	// 			Cookie[] cookies =  request.getCookies();
	// 				for( Cookie tmp : cookies){
	// 				String name =  tmp.getName();
	// 				if("firstCookie".equals(name)){ // null 포인트의 안전한 코드 
	// 				 	value = tmp.getValue();
	// 					}
	// 				}
%>
<%-- 		 출력 : <%=value %> --%>
	
			<a href="./viewCookie.jsp">동일경로에서 쿠키확인</a>
			<a href="../06/viewCookie.jsp">다른경로에서 쿠키확인</a>
			
	 
</pre>

</body>
</html>