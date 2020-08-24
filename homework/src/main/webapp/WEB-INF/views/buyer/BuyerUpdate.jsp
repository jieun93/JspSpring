<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래처 수정 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
<c:if test="${not empty message}">
<script type="text/javascript">
		alert("${message }");
</script>	
<c:remove var="message" scope="session"/>
</c:if>
</head>
<%-- <% 
	BuyerVO buyer =(BuyerVO) request.getAttribute("buyer_id"); 
	Map<String, String> errors = (Map)request.getAttribute("errors");
	String message = (String)request.getAttribute("message");
	
	
	
	// 수정 시 전달
	if(buyer==null) buyer = new BuyerVO();
	if(errors==null) errors = new HashMap<>();
	if(StringUtils.isNotBlank(message)){
		out.println(message);
	}
%>
<c:set var="vo" value="${vo.buyer_id}"/> --%>
<body>
	<form id="updateForm"  method="post" >
		<table class="table table-bordered text-center">
			<tr class="row">
				<th class="col-3">buyer_id</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_id" required readonly
					value="${buyer_id.buyer_id }">
					<span class="error">${errors["buyer_id"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_name</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_name" required 
					value="${buyer_id.buyer_name }">
					<span class="error">${errors["buyer_name"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_lgu</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_lgu" required
					value="${buyer_id.buyer_lgu }">
					<span class="error">${errors["buyer_lgu"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_comtel</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_comtel" required
					value="${buyer_id.buyer_comtel }">
					<span class="error">${errors["buyer_comtel"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_fax</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_fax" required
					value="${buyer_id.buyer_fax }">
					<span class="error">${errors["buyer_fax"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_mail</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_mail"
					value="${buyer_id.buyer_mail }">
					<span class="error">${errors["buyer_mail"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_add1</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_add1" required
					value="${buyer_id.buyer_add1 }">
					<span class="error">${errors["buyer_add1"] }</span></td>
			</tr>
			<tr class="row">
				<th class="col-3">buyer_add2</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="buyer_add2" required
					value="${buyer_id.buyer_add2 }">
					<span class="error">${errors["buyer_add2"] }</span></td>
			</tr>
			
			<tr class="row">
				<td class="col text-left">
					<input class="btn btn-warning mr-2" type="reset" value="취소"> 
					<input class="btn btn-success mr-2" type="submit" value="수정">
					<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
						onclick="history.back()" >
				</td>
			</tr>
		</table>
	</form>

<script  type="text/javascript">
$("#updateForm").on("click", function(){

$.ajax({
	url : "${pageContext.request.contextPath}/buyer/buyerList.do",
	data :{
		buyer : buyer
	},
	method:"post",
	dataType :"html",
	success: function(resp){
		alert("수정성공");
	},
	error :  function(errorResp){
		console.log("수정 실패");
	}
	
	})
}) 

</script>
<!-- <script type="text/javascript">
		var updateForm = $("#upBtn");
		/* var passInput = $("#updateForm input[name='mem_pass']"); */
		$("#upBtn").on("click", function(){
// 			let password = passInput.val();
			/* let password = prompt("비번입력"); */
			/* updateForm.find("[name='mem_pass']").val(password); */
			updateForm.submit();
		}); -->
	

</body>
</html>