<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 </title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" >
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- 한명의 학생의 모든 정보를 UI로 구성. -->
<!-- 이름(name), 생년월일(birthday), 
나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<%
   DDITStudentVO student = (DDITStudentVO) request.getAttribute("student");
%>
<table class = "table table-bordered table-striped">
   <tr>
      <th>학번</th>
      <td><%=student.getCode() %></td>
   </tr>
   <tr>
      <th>이름</th>
      <td><%=student.getName() %></td>
   </tr>
   <tr>
      <th>생년월일</th>
      <td><%=student.getBirthday() %></td>
   </tr>
   <tr>
      <th>나이</th>
      <td><%=student.getAge() %></td>
   </tr>
   <tr>
      <th>학력</th>
      <td><%=student.getGrade() %></td>
   </tr>
   <tr>
      <th>성별</th>
      <td><%="M".equals(student.getGen())?"남":"여"%></td>
   </tr>
   <tr>
      <th>자격증</th>
      <td><%=student.getLicense() %></td>
   </tr>
   <tr>
      <th>경력사항</th>
      <td><%=student.getCareer() %></td>
   </tr>
</table>
<form action="<%=request.getContextPath() %>/ddit/studentDelete.do" method="post" name="delForm">
	<input type="hidden" name="code" value="<%=student.getCode()%>">
</form>
<a  href="<%=request.getContextPath() %>/ddit/studentUpdate.do?code=<%=student.getCode()%>">수정</a>
<a href="#" onclick="if(confirm('삭제하시겟습니까?')) document.delForm.submit();">삭제</a>
<a href="<%=request.getContextPath()%>/ddit/dditStudent.do">목록으로</a>
</body>
</html>