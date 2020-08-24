<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<input type="number" name="leftOp" required value="${simple.leftOp}"/>
	
	<select id="operator" name="operator">
		<option value="">연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MUTIPLY">*</option>
		<option value="DIVIED">/</option>
	
	</select>
	
<form:errors path="simple.operator" element="span" cssClass="error"/>
	<%-- <span class="error">${errors.operator}</span> --%>
		
	<script>
		document.getElementById("operator").value="${simple.operator}";
	</script>
	
	<input type="number" name="rightOp" value="${simple.rightOp}"/> 
	<input type="submit" value="="/> 
	<span>${simple.result}</span>
	
</form>
</body>
</html>