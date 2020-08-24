<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberView.jsp/회원상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous">
</script>
<script type="text/javascript" src ="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	
	
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>회원아이디</th>
								<td>${member.mem_id}</td>
							</tr>
							
							<tr>
								<th>회원명</th>
								<td>${member.mem_name }</td>
							</tr>
							
							<tr>
								<th>연락처</th>
								<td>${member.mem_hp }</td>
							</tr>
							
							<tr>
								<th>이메일</th>
								<td>${member.mem_mail }</td>
							</tr>
							
							<tr>
								<th>지역</th>
								<td>${member.mem_add1 }</td>
							</tr>
							
							<tr>
								<th>마일리지</th>
								<td>${member.mem_mileage }</td>
							</tr>
							<tr>
								<th>탈퇴여부</th>
								<td>${member.mem_delete eq 'Y' ?"탈퇴":"사용중" }</td>
							</tr>
							<tr>
								<th>구매 목록</th>
								<td>
									<table>
									<thead>
										<tr>
											<th>상품코드</th>
											<th>상품명</th>
											<th>구매가</th>
											<th>판매가</th>
											<th>마일리지</th>
											<th>거래처</th>
										</tr>
									</thead>
									<tbody>
									
					<c:set var="prodList" value="${member.prodList}"/>				
					<c:choose>
						<c:when test="${not empty prodList}">
							<c:forEach items="${prodList}" var="prod">
							<tr>
										<td>${prod["prod_id"]}</td>
										<td>${prod.prod_name}</td>
										<td>${prod.prod_cost}</td>
										<td>${prod.prod_price}</td>
										<td>${prod.prod_mileage}</td>
										<td>${prod.buyer.buyer_name}</td>
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<tr>
									<td colspan="6">구매목록이 없음.</td>
								</tr>
						</c:otherwise>
					</c:choose>				
					</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				
</body>
</html>