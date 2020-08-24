<%@page import="java.util.Arrays"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- 전송 파라미터의 처리자  -->
<table>
	<thead>
		<tr>
			<th>파라미터명</th>
			<th>파라미터값</th>
		</tr>
	</thead>
	<tbody>

	<% 
		request.setCharacterEncoding("UTF-8"); // 한글로 바꾸는거 Body의 데이터카 꺠졌을 때 사용 setCharacterEncoding
		// 파라미터가 어떻게 넘어가는지 확인 
		// get(서버의 설정이 먼저 필요한다. 데이터가 line에 있어서), post 방식의 인코딩을 확인해야 한다. 
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()){
			String paramName = entry.getKey();
			String[] values = entry.getValue();
			%>
			<tr>
				<td><%=paramName %></td>
				<td><%=Arrays.toString(values) %></td>
			</tr>
			
			<%
		}
	 %>

		
	</tbody>
</table>
</body>
</html>