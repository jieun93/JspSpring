<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    

<div class="container">
<h4>
<%
	// 신규 등록, 수정할 때 공통적으로 전달
	DDITStudentVO student =(DDITStudentVO) request.getAttribute("student"); 
	Map<String,String> errors = (Map)request.getAttribute("errors");
	String message = (String)request.getAttribute("message");
	
	
	
	// 수정 시 전달
	if(student==null) student = new DDITStudentVO();
	if(errors==null) errors = new HashMap<>();
	if(StringUtils.isNotBlank(message)){
		out.println(message);
	}
%>
</h4>
<!-- 이름(name), 생년월일(birthday), 나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<form method="post">
	<input type="hidden" name="code" value="${student.code }" >
  <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" name="name" value="${student.name }">
      <span class="error">${errors.name }></span>
    </div>
  </div>
  <div class="form-group row">
    <label for="birthday" class="col-sm-2 col-form-label">생년월일</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="birthday" name="birthday"  value="${student.birthday}">
      <span class="error">${errors.birthday}</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">나이</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="age" name="age"  value="${student.age}">
      <span class="error">${errors.age}</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">학력</label>
    <div class="col-sm-10">
      <select name="grade" class="form-control">
			<option value>학력</option>
			
				<!-- List<Map<String, Object>> gradeList = (List)request.getAttribute("gradeList");
				for( Map<String, Object> map : gradeList){
					String selected = map.get("text").equals(student.getGrade())?"selected":"";
					 -->
					 <c:forEach items="${gradeList}" var="gradelist">
						<option ${not empty student and gradelist.TEXT eq student.grade ? "selected" : "" }${selected} value="${gradelist.CODE}">${gradelist.TEXT}</option>
				
					</c:forEach> 
					
				<%--
				}
				--%>	
			
		 </select>
		 <span class="error">${errors.grade}</span>
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
	<span class="error">${errors.gen}</span>
	<script type="text/javascript">
		$("input[value='${student.gen}]").prop("checked", true);
	</script>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">자격증</label>
    <div class="col-sm-10">
		<select class="form-control" name="lic_codes" multiple>
			<%--
			List<String> licenses = student.getLicense();
			List<Map<String, Object>> licenseList = (List)request.getAttribute("licenseList");
			for( Map<String, Object> map : licenseList){
					String code = (String)map.get("code");
					String name = (String)map.get("text");
					String selected = licenses!=null && licenses.contains(name)?"selected":"";
					--%>
					
					
			<c:forEach items="${licenseList }" var="liMap">
               <option value="${liMap.CODE }" 
               ${not empty student.license and student.license.contains(liMap.TEXT) ? "selected":"" }>${liMap.TEXT}</option>
            </c:forEach>
					
									<%--
				}
			--%>
		</select>
		<span class="error">${errors["lic_codes"]}</span>
	</div>
  </div>	
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">경력사항</label>
    <div class="col-sm-10">
     	<textarea class="form-control" name="career" rows="5" cols="50">${student.career}</textarea>
     	<span class="error">${errors.career}</span>
    </div>
  </div>
  <div class="form-group row">
	<button class="btn btn-success" type="submit">등록</button>			 
	<button class="btn btn-warning" type="reset">취소</button>			 
	<button class="btn btn-info" type="button" onclick="location.href='dditStudent.do';">목록으로</button>			 
  </div>
</form>
</div>















