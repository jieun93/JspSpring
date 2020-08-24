<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    String outer = null;
 	
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1.클라이언트로부터 최소단과 최대단을 2개의 파라미터 입력 받음.
2. submit 이벤트 발생으로 서버로 전달   gugudanservlet에게 파라미터 전송. -->
<%=new Date() %>
	<form>
		<ul>
			<li><input type="number" min="2" max="9" name="first" /></li>
			<li><select name="second">
					<%
						
						new Integer(4);
						String text = "now"; /* 지역변수 */
						for (int i = 2; i <= 9; i++) {
					%>

					<option><%=i%></option>

					<%
						}
					%>

			</select> 
			<input type="submit" value="전송"></li>
		</ul>
	</form>
	<table>
<%!
	private boolean validate(String min, String max){
		boolean valid = true;
		if(StringUtils.isBlank(min) || StringUtils.isBlank(max)
				|| !StringUtils.isNumeric(min) || !StringUtils.isNumeric(max)
				){
			valid = false;
			
		}
		return valid;
}
%>	

<%
	String minstr = request.getParameter("first");
 	String maxstr = request.getParameter("second");
	if(StringUtils.isBlank(minstr)){
		return;
	}
	if(!validate(minstr, maxstr)){
		//검증실패	
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
		
	}
	int minDan = Integer.parseInt(minstr);
	int maxDan = Integer.parseInt(maxstr);
	for(int dan = minDan; dan<=maxDan; dan++){
		%>
		<tr>
		<%
		for(int mul=1; mul<=9; mul++){
			%>
			<td><%=String.format("%d*%d=%d", dan, mul, (dan*mul)) %></td>
			<%
		}
		
		%>
		</tr>
		<%
	}

%>
	</table>
</body>
</html>