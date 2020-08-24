<%@page import="kr.or.ddit.buyer.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="kr.or.ddit.buyer.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuyerView.jsp/ 거래처 상세페이지 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous">
</script>
<script type="text/javascript" src ="${pageContext.request.contextPath}/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<%--
//  서블릿에서 BuyerVO 의 값을 받아오는거 
	BuyerVO buyer = (BuyerVO)request.getAttribute("buyer"); // BUYER가 어디서 온건가???
	
	
	거래처와 id가 동일한 id의 물품 정보를 내보내는거 
	
--%>

<a class="btn btn-info" href="${pageContext.request.contextPath}/buyer/updateBuyer.do?buyer_id=${buyer.buyer_id}">수정하기</a>
	<table class="table table-bordered">
		<tr>
			<th>BUYER_ID</th>
			<td>${buyer.buyer_id}</td>
		</tr>
		<tr>
			<th>BUYER_NAME</th>
			<td>${buyer.buyer_id}</td>
		</tr>
		<tr>
			<th>BUYER_LGU</th>
			<td>${buyer.buyer_lgu}</td>
		</tr>
		<tr>
			<th>BUYER_COMTEL</th>
			<td>${buyer.buyer_comtel}</td>
		</tr>
		<tr>
			<th>BUYER_FAX</th>
			<td>${buyer.buyer_fax}</td>
		</tr>
		<tr>
			<th>BUYER_MAIL</th>
			<td>${buyer.buyer_mail}</td>
		</tr>
		<tr>
			<th>BUYER_ADD1</th>
			<td>${buyer.buyer_add1}</td>
		</tr>
		<tr>
			<th>BUYER_ADD2</th>
			<td>${buyer.buyer_add2}</td>
		</tr>
		
			<th>물품 목록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>PROD_ID</th>
							<th>PROD_NAME</th>
							<th>PROD_LGU</th>
							<th>PROD_BUYER</th>
							<th>PROD_COST</th>
							<th>PROD_PRICE</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prodList" value="${buyer.prodList }" />
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<tr>
										<td>${prod.prod_id }</td>
										<td>${prod.prod_name }</td>
										<td>${prod.prod_lgu }</td>
										<td>${prod.prod_buyer }</td>
										<td>${prod.prod_cost }</td>
										<td>${prod.prod_price }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">구매목록이 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					 
							
							<%--
						}
					}else{
						--%>
						<%-- <c:if test="${empty prodList}" or "${prodList == null }" >
							<tr>
								<td> 구매목록이 없음</td>
							</tr>
						</c:if> --%>
						<%--
					}
					--%>
					</tbody>
				</table>
	 		<!-- </td>
		</tr>
			
		</tbody>
		

		</tbody>
	</table> --> 
</body>
<a class="btn btn-info" href="${pageContext.request.contextPath }/buyer/buyerList.do">뒤로가기</a>	
	
	
</html>