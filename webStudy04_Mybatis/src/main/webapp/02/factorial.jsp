<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	private int factorial(int text, StringBuffer expression){
	
	if(text<1) throw new IllegalArgumentException(text+"은 연산불가"); //예외처리 
	
	else if(text<=1) {
		expression.append(text);
		return text;
	}
		
		else{
			expression.append(text+"*");
			return text * factorial(text-1, expression);
		}
	
	
	
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>02/factorial.jsp</title>
</head>
<body>
<%
// 입력 칸의 값을 유지하기위해서 위로 빼준것
String num = request.getParameter("num");
%>
<!-- 사용자로부터 10이하의 숫자를 입력받아, factorial 연산 수행
1. jsp 하나로 모든 처리 
2. recursive 호출 구조 사용 / 재귀 호출 구조/ 메소드 필요 - 선언부  -->
<form action="<%=request.getContextPath() %>/02/factorial.jsp" method="get" >
<%-- 입력창에 null이 아니라 빈칸을 주기 위해 value="<%=Objects.toString(num,"")% 씀 --%>
<input type="text" name="num" max="10" value="<%=Objects.toString(num,"")%>">
<input type="text" name="pName"/> 
<input type="submit" value="전송">
</form>
<%!
	// 데이터 검증
	private boolean validate(String num){
	
	boolean chk = true;
	if(StringUtils.isBlank(num) ){
		chk = false;
	}else{
		try{ //  파싱하는 과정에서 문제가 발생되는 예외 NumberFormatException
		Integer.parseInt(num);
		}catch(NumberFormatException e){ // 숫자가 아닌게 넘어온 부분
			chk = false;
			
		}
	}
	return chk;
}

%>

<%
	
	if(StringUtils.isBlank(num)){
		return;
	}
	

	if(!validate(num)){
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	
	int number = Integer.parseInt(num);
	
	try{
			StringBuffer expression = new StringBuffer("(");
			int result = factorial(number, expression);
			expression.append(")");
			out.println(String.format("%d!=%d  %s", number, result, expression));	
	}catch(IllegalArgumentException e){
		response.sendError(400, e.getMessage());
		return;
	}
%>



</body>

</html>