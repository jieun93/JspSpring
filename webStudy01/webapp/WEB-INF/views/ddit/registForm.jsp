<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<div class="container">
<h4>
	<!--// 신규 등록, 수정할떄 공통적으로  전달  -->
	<%=request.getAttribute("newStudent") %>
	<%=request.getAttribute("errors") %>
	<%=request.getAttribute("message") %>
	
	
	// 수정시 전달 
	// null포인트를 피해가기 위해서 사용 
	
</h4>
<!-- 이름(name), 생년월일(birthday), 나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<form method="post"> <!-- 액션이 없으면 상단에 있는 주소가 사용된다.  -->
  <input type="hidden" name ="code" >
  <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" name="name" >
      
    </div>
  </div>
  <div class="form-group row">
    <label for="birthday" class="col-sm-2 col-form-label">생년월일</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="birthday" name="birthday" >
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">나이</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="age" name="age" >
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">학력</label>
    <div class="col-sm-10">
      <select name="grade" class="form-control">
			<option value>학력</option>
			<%
				List<Map<String, Object>> gradeList = (List)request.getAttribute("gradeList");
				for( Map<String, Object> map : gradeList){
					%>
						<option value="<%=map.get("code") %>"><%=map.get("text") %></option>
					<%
				}
			%>
		 </select>
    </div>
  </div>
 <div class="form-group row">
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="gen" id="genF" value="F">
	  <label class="form-check-label" for="genF">여자</label>
	</div>
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="gen" id="genM" value="M">
	  <label class="form-check-label" for="genM">남자</label>
	</div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">자격증</label>
    <div class="col-sm-10">
		<select class="form-control" name="lic_codes" multiple>
			<%
			List<Map<String, Object>> licenseList = (List)request.getAttribute("licenseList");
			for( Map<String, Object> map : licenseList){
					String code = (String)map.get("code");
					String name = (String)map.get("text");
					%>
					<option value="<%=code %>"><%=name %></option>
					<%
				}
			%>
		</select>
	</div>
  </div>	
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">경력사항</label>
    <div class="col-sm-10">
     	<textarea class="form-control" name="career" rows="5" cols="50"></textarea>
    </div>
  </div>
  <div class="form-group row">
	<button class="btn btn-success" type="submit">등록</button>			 
	<button class="btn btn-warning" type="reset">취소</button>			 
	<button class="btn btn-info" type="button">걍버튼</button>			 
  </div>
</form>
</div>
</body>
</html>	














