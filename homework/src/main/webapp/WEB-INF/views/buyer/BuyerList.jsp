<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="kr.or.ddit.buyer.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homework/buyer.jsp 거래처관리 메인페이지 </title>
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
<h4><a href="${pageContext.request.contextPath}/buyer/buyerList.do">거래처 리스트</a> </h4>
<a class="btn btn-info" href="${pageContext.request.contextPath}/buyer/insertBuyer.do">추가하기</a>

<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>거래처id</th>
			<th>거래처명 </th>
			<th>LGU </th>
			<th>COMTEL </th>
			<th>FAX</th>
			<th>MAIL</th>
			<th>주소1</th>
			<th>주소2</th>
			
		</tr>
	</thead>

<tbody>
	<%--
	 거래처 목록 리스트 보여주기 
	PagingVO<BuyerVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
	List<BuyerVO> buyerList = pagingVO.getDataList();
	for(BuyerVO list : buyerList){
	--%>
	
	<c:set var="buyerList" value="${pagingVO.dataList}"/>
	<c:forEach var="list"  items="${buyerList}" >
			<tr>
			<td>${list.buyer_id}</td>
			<td><a href="${pageContext.request.contextPath}/buyer/buyerView.do?buyer_id=${list.buyer_id}">${list.buyer_name}</a></td>
			<td>${list.buyer_lgu}</td>
			<td>${list.buyer_comtel}</td>
			<td>${list.buyer_fax}</td>
			<td>${list.buyer_mail}</td>
			<td>${list.buyer_add1}</td>
			<td>${list.buyer_add2}</td>
			</tr>
	</c:forEach>
	<%--
	}
	--%>
</tbody>
<tfoot>
			<tr>
				<td colspan="5">
					<nav aria-label="...">
						<%--pagingVO.getPagingHTML()--%>
						${pagingVO.pagingHTML}
					</nav>
					<div class="form-inline" id="searchUI" >
					<!-- // 입력받아 검색할 조건  -->
						<select name="searchType" class="form-control">
							<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected":"" }>전체</option>
							<option value="buyer_name" ${pagingVO.searchVO.searchType eq 'buyer_name' ? "selected":"" }>거래처명</option>
							<option value="buyer_add1" ${pagingVO.searchVO.searchType eq 'buyer_add1' ? "selected":"" }>주소</option>
						</select>
						
						<input type="text" class="form-control" name="searchWord"
							value="${pagingVO.searchVO.searchWord}"
						>
						<input class="btn btn-success" type="button" value="검색" id="searchBtn">
					</div>
				</td>
			</tr>
		</tfoot>
</table>
<!-- 검색 조건에 대한 페이징 -->
<form id="searchForm">
	<input type="hidden" name="page"/>
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType}"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord}"/>
</form>
<script type="text/javascript">

 
	var searchForm = $("#searchForm");
	var searchUI=$("#searchUI");
	// 페이지 번호 눌렀을 때 실행 
	$(".pagination").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit(); 
		
	});
	// 검색 버튼 눌렀을 때 실행되늑거 
	$("#searchBtn").on("click",function(){
	 let children =	searchUI.children(":input");
// 	 console.log(children);
	$(children).each(function(idx, input){
		let name =	$(this).attr("name");
		if(name){
			let value = $(this).val();
			searchForm.find("[name='"+name+"']").val(value);		
		}
	});
	searchForm.submit();
	})
</script>
</body>
</html>