<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.servlet03.dao.DDITStudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
      border: 1px solid black;   
   }
   th,td{
      border: 1px solid black;
   }
   .yellow{
      background-color: yellow;
   }
</style>
</head>
<body>
등록된 모든 학생 조회
<a href="<%=request.getContextPath() %>/ddit/regist.do">신규등록</a>
<table>
	<thead>
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>			
		</tr>
	</thead>
	<tbody>
<%
//  String	 student = (String) session.getAttribute("last"); // 마지막 학생
//  session.removeAttribute("student"); // 세션을 지워주는거 
 
 List<DDITStudentVO> allStudents = (List) request.getAttribute("allStudents");  // 모든학생
 DDITStudentVO lastStudent = (DDITStudentVO)session.getAttribute("lastStudent");
 // session 은 필요없어지면 지워야 한다. .. 새로고침하면 class 적용이 사라짐 
 session.removeAttribute("lastStudent");
 
	for( DDITStudentVO vo : allStudents){
		String clzName =  vo.equals(lastStudent)?"yellow" : "normal";
		%>
		<tr class="<%=clzName%>">
			<td><%=vo.getCode()%></td>
			<td><a href="<%=request.getContextPath() %>/ddit/studentView.do?code=<%=vo.getCode()%>"><%=vo.getName()%></a></td>
			<td><%=vo.getAge()%></td>
			<td><%=vo.getGen()%></td>
		</tr>
		<%
	}

%>
</tbody>

</table>
</body>
</html>