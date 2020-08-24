<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<form:form modelAttribute="member" id="updateForm" action="${pageContext.request.contextPath }/member/updateMember.do" method="post">
		<table class="table table-bordered text-center">
			<tr class="row">
				<th class="col-3">회원아이디</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_id"  readonly
					value="${member.mem_id }">
					<form:errors path="mem_id" element="span" cssClass="error"/>
					</td>
			</tr>
			<tr class="row">
				<th class="col-3">비밀번호(${member.mem_pass })</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_pass"  >
					<form:errors path="mem_pass" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">회원명</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_name" 
					value="${member.mem_name }">
					<form:errors path="mem_name" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">주민번호1</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_regno1" 
					value="${member.mem_regno1 }">
					<form:errors path="mem_regno1" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">주민번호2</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_regno2" 
					value="${member.mem_regno2 }">
					<form:errors path="mem_regno2" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">생일</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="date" name="mem_bir"
					value="${member.mem_bir }">
					<form:errors path="mem_bir" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">우편번호</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_zip" 
					value="${member.mem_zip }">
					<form:errors path="mem_zip" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">주소1</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_add1" 
					value="${member.mem_add1 }">
					<form:errors path="mem_add1" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">주소2</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_add2" 
					value="${member.mem_add2 }">
					<form:errors path="mem_add2" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">집전화번호</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_hometel" 
					value="${member.mem_hometel }">
					<form:errors path="mem_hometel" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">회사전화번호</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_comtel" 
					value="${member.mem_comtel }">
					<form:errors path="mem_comtel" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">휴대폰</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_hp"
					value="${member.mem_hp }">
					<form:errors path="mem_hp" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">이메일</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_mail" 
					value="${member.mem_mail }">
					<form:errors path="mem_mail" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">직업</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_job"
					value="${member.mem_job }">
					<form:errors path="mem_job" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">취미</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_like"
					value="${member.mem_like }">
					<form:errors path="mem_like" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">기념일</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" name="mem_memorial"
					value="${member.mem_memorial }">
					<form:errors path="mem_memorial" element="span" cssClass="error" /></td>
			</tr>
			<tr class="row">
				<th class="col-3">기념일자</th>
				<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="date" name="mem_memorialday"
					value="${member.mem_memorialday }">
					<form:errors path="mem_memorialday" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th class="col-3">마일리지</th>
				<td class="col-9 form-inline">${member.mem_mileage }</td>
			</tr>
			<tr class="row">
				<th class="col-3">탈퇴여부</th>
				<td class="col-9 form-inline">${member.mem_delete }</td>
			</tr>
			<tr class="row">
				<td class="col text-left">
					<input class="btn btn-warning mr-2" type="reset" value="취소"> 
					<input class="btn btn-success mr-2" type="submit" value="수정">
					<input type="button" class="btn btn-danger mr-2" value="탈퇴" id="delBtn">
					<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
						onclick="history.back()" >
				</td>
			</tr>
		</table>
	</form:form>
	<form id="delForm" action="${pageContext.request.contextPath }/member/deleteMember.do" method="post">
		<input type="hidden" name="mem_id" value="${member.mem_id }">
		<input type="hidden" name="mem_pass" >
	</form>
	<script type="text/javascript">
		var delForm = $("#delForm");
		var passInput = $("#updateForm input[name='mem_pass']");
		$("#delBtn").on("click", function(){
// 			let password = passInput.val();
			let password = prompt("비번입력");
			delForm.find("[name='mem_pass']").val(password);
			delForm.submit();
		});
	</script>









