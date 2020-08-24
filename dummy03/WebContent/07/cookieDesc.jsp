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
				
		 	
			www.naver.com   3레벨 구조  com (company)  - GTLD(Global Top Level Domain)
			www.naver.co.kr 4레벨 구조	kr(korea)		- NTLD(National Top Level Domain)
			email.naver.com
			blog.naver.com
		 
		
		 <%
// 		 	Cookie tmpCookie = new Cookie("firstCookie","FirstCookie");
// 			response.addCookie(tmpCookie); // 한번에 20개까지 응답데이터를 포함할수있다.
				
			// 쿠키 속성 확인 
// 			String cookieValue =  URLEncoder.encode("한글 국산 쿠기", "UTF-8");
// 			Cookie koreanCookie = new Cookie("koreanCookie", cookieValue);
// 			response.addCookie(koreanCookie);


			Cookie allDomainCookie = new Cookie("allDomainCookie","All~~Domain");
			allDomainCookie.setDomain(".yje.com");   // ".naver.com" 모든 호스트를 대상으로 전달된다.
			response.addCookie(allDomainCookie);
	
			Cookie[] cookies =  request.getCookies();
			// 검증
			if(cookies!=null){
				
			for( Cookie tmp : cookies){
			String name =  tmp.getName();
			String value =  URLDecoder.decode(tmp.getValue(),"UTF-8");
				out.println(
						String.format("%s : %s", name, value)
						);
				}
			}
		


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
	

	 
</pre>

</body>
</html>