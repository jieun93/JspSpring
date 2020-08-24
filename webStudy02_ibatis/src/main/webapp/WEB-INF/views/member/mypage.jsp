<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" >
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
<c:if test="${not empty message}">
	alert("${message}");
	<c:remove var ="message" scope="session"/>
</c:if>
<%--
	String message = (String) request.getAttribute("message");
	if(StringUtils.isBlank(message)){
		message = (String)session.getAttribute("message");
	}
	if(StringUtils.isNotBlank(message)){
		%>
		alert("${message}");
		<%
		session.removeAttribute("message"); // 한번만 사용하고 지워지는거 flash attribute
	}
 --%>

</script>	
</head>
<body>
<div class="container">
<form method="post" id="change" action="${pageContext.request.contextPath}/member/updateMember.do">
<%-- <%
	
	MemberVO memVO = (MemberVO)request.getAttribute("member"); // 기본 vo에 담겨있는걸 텍스트창에 보여지게 하는거 
	Map<String, String> errors = (Map)request.getAttribute("errors");
	if(errors == null)
		errors = new HashMap<>();
	
%> --%>
	
	
	<input type="hidden" name="code">
	<div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">회원id</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_id" name="mem_id" required value="${member.mem_id}">
     	<span class = "error">${errors.mem_id}</span>
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">비밀번호</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_pass" name="mem_pass" required>
     	<span class = "error">${errors.mem_pass}</span>
     	</div>
     </div>
	 <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">회원명</label> 
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_name" name="mem_name"  required value="${member.mem_name}">
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">주민번호</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_regno1" name="mem_regno1" required value="${member.mem_regno1}">
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">연락처</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_hp" name="mem_hp"  required value="${member.mem_hp}">
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">e-mail</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_mail" name="mem_mail" required value="${member.mem_mail}">
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">주소</label>
    	<div class="col-sm-10">
     	<input type="text" class="form-control" id="mem_add1" name="mem_add1" required value="${member.mem_add1}" >
     	</div>
     </div>
     <div class="form-group row">
   	 	<label for="name" class="col-sm-2 col-form-label">탈퇴여부</label>
    	<div class="col-sm-10">	</div>
     </div>
	<div class="form-group row">
	<button class="btn btn-success" type="submit">수정</button>			 
	<button class="btn btn-warning" type="reset">취소</button>			 
	<input type="button" id="button" value="탈퇴">	 
    </div>
</form>

<form  id ="delForm"action="${pageContext.request.contextPath}/member/deleteMember.do" method="post">
	<input type="hidden" name="mem_id" value="${member.mem_id}"></input>
	<input type="hidden" name="mem_pass">
</form>

<script type="text/javascript">
	var delForm = $("#delForm");
	var change = $("#change input[name='mem_pass']");
	$("#button").on("click",function(){
		let password = prompt("비번입력");
		delForm.find("[name='mem_pass']").val(password);
		delForm.submit();
	})
</script>

<%-- <script type="text/javascript">
	var change = $("#change");
	var updateUrl = change.attr("action");
	$("#button").on("click",function(event){
		change.attr("action","${pageContext.request.contextPath}/member/deleteMember.do");
		change.submit();
		change.attr("action", change); 
		
		});
</script> --%>
</div>
</body>
</html>