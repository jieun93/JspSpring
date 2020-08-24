<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    <%! // 전역변수 전역번수의 목적은 데이터 공유  jsp에서는 대안으로 scope라는 개념을 활용함 . 
    	 public String text ="텍스트";//
    	public void test(){
    	System.out.println("테스트");
    }
    %>

<h4>JSP(Java Server Page)</h4>
<%-- <%=includeeVariable %>   --%>
<pre>
	표준구성요소
	1.정적 테스트 : 문자열, HTML, CSS, JavaScript... (클라이언트 ui를 구성하는데 사용한다.)
	
	
	2.스크립트 요소 
		1) Scriptlet(스크립틀릿) : <%//자바코드 
									 Object 변수 = null;
										test(); // 테스트 호출 					 
									 //public void test(){} 오류
									 %>
									 : 실행코드,  차후에 컨테이너에 의해 서블릿 코드가 파싱될때, 지역코드화된(_JSPService)
 		2) Expression(표현식): <%=변수%><!-- // 값을 출력할떄 사용 -->, <%=test() %> 
			:출력데이터 
		3) Directive(지시자) :<%-- <%@ %> --%>
			:  현재jsp페이지에 대한 환경정보(부가정보)설정
			page(required) : 현재 페이지에 대한 전처리
			taglib(optional) : 커스텀 태크 라이브러리 로딩
			include(optional) : 정적 내포
		4) Declaration(선언부) : <%-- <%! 전역변수, 메소드에 대한 선언 %> --%>
			
		5) Comment(주석) : <%-- --%>
			client side comment : html 주석, css, javascript
			server side comment : java, jsp --보안 , 응답데이터의 사이즈를 줄이기 위해서  이 주석을 이용하는게 좋다. 
	3. 기본 객체
		 
	4. 액션 태그
	5. 표현언어 (EL)
	6. JSTL	
	
</pre>