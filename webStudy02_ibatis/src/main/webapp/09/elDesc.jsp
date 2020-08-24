<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/elDesc.jsp</title>
</head>
<body>
<h4>ExpressionLanguage (표현언어)</h4>
<pre>
	: 데이터를 표현하기 위해 표현식을 대체하는 스크립트 언어(제어문을 갖지 않음) 중간에 예외를 발생해도 넘어간다. 
	사용형태 : \${속성명}  ==> 단순문자 
 	EL의 기능
 	1. 속성데이터 확보
 	2. EL 연산자 지원
 		1) 산술연산자 :  +, -, *, /, %
 			${1+2} , ${1+"2"} , ${"1"+"2"}
 			${1*"2"}, ${1/2}
 		2) 논리연산자 : &&(and), ||(or), !(not)
 			${true and true}, ${not true}, ${not true or false }
 		
 		3) 비교연산자 : >(gt), <(lt), >=(ge), <=(le),  ==(eq), !=(ne)
 			${1 < 2 }  ${ 1 lt 2 }, ${1 eq "1"}
 	**	4) 단항연산자 : ++, -- ==> 지원하지 않음 , empty
 		<%
 			String test = "             ";
 			pageContext.setAttribute("test", test);
 			
 			List list = new ArrayList();
 			list.add("data");
 			pageContext.setAttribute("list", list);
 		%>
 			test - ${empty test}
 			list - ${empty list}
 		${empty test}  <%-- ${not empty test } --%>
 		5) 삼항연산자  : 조건식 ? 참표현식 : 거짓 표현
 		 ${not empty list and list.size() gt 3? list :"비어있다" }
 		
 	3. (속성으로 공유되고 있는) 자바 객체에 대한 접근
 	
 	<%
 		MemberVO  mem = new MemberVO("a001", "wldms");
 		request.setAttribute("member", mem);
 	
 	%>
 	
 	<%=mem.getMem_id() %>	${member.mem_id }, ${member['mem_id']}
 	
 	4. (속성으로 공유되고 있는) Collection 객체에 대한 접근
 	
 		<a href="collection.jsp">collectionEL.jsp참고</a>
 	5. EL 객체 지원 (Scope 객체: pageScope, requestScope, sessionScope, applicationScope)
 		<a href ="elObject.jsp">elObject.jsp참고</a>
	<%
	
// 		String test =null; //변수
// 		//속성명으로 넣어줘야 한다. 
// 		pageContext.setAttribute("test", test);
// 		// el에서는 null 이 공백으로 나온다. 
	
	
		pageContext.setAttribute("attrName", "페이지 데이터");
		request.setAttribute("attrName", "요청데이터 ");
		session.setAttribute("attrName", "세션데이터");
		application.setAttribute("attrName", "어플리케이션 데이터");
	
	
	%>
	<%=pageContext.getAttribute("attrName") %>, ${sessionScope.attrName}
</pre>
</body>
</html>