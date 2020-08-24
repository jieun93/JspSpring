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
<%
	Map<String, String> nameMap= new HashMap<>();
		nameMap.put("name", "이름");
		nameMap.put("birthday","생년월일");
		nameMap.put("age", "나이");
		nameMap.put("grade","학력");
		nameMap.put("gen","성별");
		nameMap.put("license","자격증");
		nameMap.put("career","경력사항");


%>
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
		request.setCharacterEncoding("UTF-8"); // 한글로 바꾸느넉
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()){
			String paramName = entry.getKey();
			String[] values = entry.getValue();
			%>
			<tr>
				<td><%=parameterMap.get(paramName)%></td>
				<td><%=Arrays.toString(values) %></td>
			</tr>
			
			<%
		}
	 %>

		
	</tbody>
</table>
</body>
</html>