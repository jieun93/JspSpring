<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" >
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- 한명의 학생의 모든 정보를 UI로 구성. -->
<!-- 이름(name), 생년월일(birthday), 
나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<table class="table table-bordered table-striped">
	<tr>
		<th>학번</th>
		<td>${student.code }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${student.name }</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td>${student.birthday }</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>${student.age }</td>
	</tr>
	<tr>
		<th>학력</th>
		<td>${student.grade }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${student.gen eq "M" ?"남":"여" }</td>
	</tr>
	<tr>
		<th>자격증</th>
		<td>${student.license }</td>
	</tr>
	<tr>
		<th>경력사항</th>
		<td>${student.career }</td>
	</tr>
</table>
<form method="post" name="delForm" action="${pageContext.request.contextPath }/ddit/studentDelete.do">
	<input type="hidden" name="code" value="${student.code }"> 
</form>
<a href="${pageContext.request.contextPath }/ddit/studentUpdate.do?code=${student.code }">수정</a>
<a href="#" onclick="if(confirm('삭제하시겠습니까?')) document.delForm.submit();">삭제</a>
<a href="${pageContext.request.contextPath }/ddit/dditStudent.do">목록으로</a>
</body>
</html>















