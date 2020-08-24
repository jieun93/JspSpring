<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jstlDesc.jsp</title>
</head>
<body>
<h4>JSTL(Java Standard Tag Library)</h4>
<!-- 2~9단 구구단 완성 -->
<style>
	.yellow{background-color :blue;}
	.green{background-color: green;}
</style>

<table>

<c:forEach var="i" begin="2" end="9" step="2" varStatus="vs">
	<c:choose>
		<c:when test="${vs.count eq 3}">
			<c:set var="clzName" value="yellow">
			</c:set>
		</c:when>
		<c:when test="${vs.last}">
			<c:set var="clzName" value="green"></c:set>
		</c:when>
		<c:otherwise >
			<c:set var="clzName" value="nomal"></c:set>
		</c:otherwise>
	</c:choose>
	<tr class="${clzName }">
	<c:forEach  var="j" begin="1" end="9">
	<td>${i} * ${j} =${i*j} </td>
	</tr>
	</c:forEach>
</c:forEach>
</table>


<pre>
	 : 커스텀 태그들 중 많이 사용되는 것들을 모아 놓은 라이브러리
	 커스텀 태그 사용 방법  - &lt;prefix:tagname&gt;
	 JSTL 사용단계
	 1. taglib로 사용할 커스텀 태그를 로딩, prefix 결정
	 2. &lt;prefix:tagname&gt;
	 
	JSTL 의 종류
	1. core태그(c)
		1) 변수지원 : 
			set: 속성을 생성하고, 값을 할당, 
			remove : 속성 제거, 주의!! 제거할 영역 명시
			var(속성명) value(값, 표현식, EL ) scope(영역)
			<c:set var="attrName" value="속성값" scope="page"/>
			${pageScope.attrName }
 			<c:set var="attrName" value="${pageContext.request.contextPath }" scope="application"/> 
 			${applicationScope.attrName } 
			<c:remove var="attrName" scope="application"/>
			${applicationScope.attrName }
		
		2) 조건문 지원  :
		 	if : 단일 조건문, else 구조가 없음, 
		 	choose~when~otherwise (swicth케이스 ) : 다중 조건문
		 	공통점 : test(조건식, el, 표현식), 태그의 바디로 블럭 구문을 완성함 
			 <c:if test="${true}">
				조건을 만족하는 구문 
			</c:if>	 	
			
			el표현식의  삼항연산자 사용법
			${not empty param.test? param.test : "없다"}
			
			jstl의 if문 사용방법
			<c:if test="${not empty param.test}">
					${param.test}
			</c:if>
			<c:if test="${empty param.test}">
					"없다"
			</c:if>
		
			<c:choose>
				<c:when test="${not empty param.test}">
						${param.test}
				</c:when>
				<c:otherwise>
					"없다"
				</c:otherwise>
			</c:choose>
		
		
		3) 반복문 지원  : 
			foreach : 일반 for(int i = 3; i=3;  i++), 향상for문 (블럭변수 : 집합객체)
				var(블럭속성명) begin(초기값) end(종료값)step(증감치 , >0) 
				items(집합객체) varStatus(LoopTagStatus 객체로 반복에 대한 상태 정보를 가진 객체)
				
				<c:forEach var="i" begin="3" end="3" step="1">
					${i}
				</c:forEach>
				<c:forEach items='<%=Arrays.asList("value1","value2") %>' var="element">
					${element}
				</c:forEach>
			
			 forTokens : 일정 토큰을 대상으로 반복문 수행 
			 	token 	: 문장의 의미를 결정하는 최소한의 구성요소.
			 	ex) 아버지 가방에 들어가신다.
			 	<c:forTokens items="1,2,3,4,5" delims="," var="token" varStatus="vs">
			 		${token *3} ${not vs.last? ',':"" }
			 	</c:forTokens>
			 StringTokenizer , "".split
			 
			 
		4) URL 재처리 : 
		url : 클라이언트 사이드 절대경로를 완성해준다.,queryString 완성, 세션 파라미터 완성(쿠키가 없을때)
		redirect : clien side 주소 완성 후 redirection
		<c:url value="/member/memberList.do" var="listURL">
			<c:param name="page" value="2"/>
			<c:param name="searchWord" value="대전"/>
			<c:param name="searchType" value="address"/>
		</c:url> 
		<%-- <c:redirect  url="/member/memberList.do"/>  --%>
		<a href="${listURL}">회원목록으로 </a> 
	
		5) 기타  : import, out
		<c:import url="https://www.naver.com" var="naver"/>
		<c:out value="${naver}" escapeXml="false"/>
	2. fmt 태그 (fmt)
	3. function 라이브러리 (fn) 
</pre>
</body>
</html>