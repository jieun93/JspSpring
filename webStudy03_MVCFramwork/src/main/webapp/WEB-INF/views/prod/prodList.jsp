<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" >
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- 상품 목록 조회, : 상품코드, 상품명, 거래처명, 분류명, 구매가, 판매가, outline -->
<!-- 검색 기능 : 상품 분류, 거래처, 전체 -->
<!-- 페이징과 검색기능이 적용된 상품 목록 조회  -->
<!-- ex) 전자제품 카테고리의 삼성 전자와의 거래 물품만 조회할 수 있으려며??? -->
<!-- 상품분류와 거래처를 동시에 검색 조건으로 활용하여 상세 검색을 하려면?? -->
<!-- 상품 목록에서 한건의 상품을 클릭하면, 상세 조회(/prod/prodView.do?what=P101000001) 가능. -->
<!-- 1. 비동기 처리(Modal UI) -->
<!-- 2. 상품 하나 조회시, 해당 상품을 구매한 적이 있는 회원의 목록(중복 제거)을 조회. -->

<div class="form-inline mb-2" id="searchUI">
	<select class="dynamicElement form-control mr-2" name="prod_lgu"
		data-url="<c:url value='/prod/getLprodList.do'/>"
	>
		<option value>상품분류</option>
		
	</select>
	<select class="dynamicElement form-control mr-2" name="prod_buyer"
		data-url="<c:url value='/prod/getBuyerList.do' />"
	>
		
	</select>
	<input class="btn btn-success mr-2" type="button" value="검색" id="searchBtn">
	<input class="btn btn-primary" type="button" value="신규등록" id="insertBtn">
</div>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>#</th>
			<th>상품코드</th>
			<th>상품명</th>
			<th>거래처명</th>
			<th>분류명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>OUTLINE</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<nav id="pagingArea">
					${pagingVO.pagingHTML }
				</nav>
			</td>
		</tr>
	</tfoot>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상품 정보 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" id="updateBtn" class="btn btn-primary mr-2">수정</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<form id="searchForm" action="${pageContext.request.contextPath }/prod/prodList.do">
	<input type="hidden" name="page" value="${param.page }"/>
	<input type="hidden" name="prod_lgu" value=""/>
	<input type="hidden" name="prod_buyer" value=""/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dynamicSelect.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paiging.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript">
	// 옵션 선택시 
	var optionPtrn = "<option value='%V'>%T</option>";
	var prod_lguTag = $("select[name='prod_lgu']").data("success", function(resp){
		var html = "";
		$.each(resp, function(idx, lprod){
			html += optionPtrn.replace("%V", lprod.lprod_gu).replace("%T", lprod.lprod_nm);
		});
		prod_lguTag.append(html);
	}).on("change", function(){
		let prod_lgu = $(this).val();

		prod_buyerTag.trigger("renew", {
			prod_lgu : prod_lgu
		});
	});
	
	var prod_buyerTag = $("select[name='prod_buyer']").data("success", function(resp){
		var html = "<option value>거래처</option>";
		$.each(resp, function(idx, buyer){
			html += optionPtrn.replace("%V", buyer.buyer_id).replace("%T", buyer.buyer_name);
		});
		prod_buyerTag.html(html);
	});
	
	$(".dynamicElement").dynamicSelect();
	
	var searchForm = $("#searchForm").paging({
		searchUI:"#searchUI",
		searchBtn:"#searchBtn",
		pagination:"#pagingArea",
		pageParam:"page",
		byAjax:true,
		success:function(resp){
			let prodList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			console.log(prodList);
			let trTags = [];
			if(prodList.length>0){
				$.each(prodList, function(idx, prod){
					let trTag = $("<tr>").append(
									$("<td>").text(prod.rnum)
									, $("<td>").text(prod.prod_id)
									, $("<td>").text(prod.prod_name)
									, $("<td>").text(prod.buyer.buyer_name)
									, $("<td>").text(prod.lprod_nm)
									, $("<td>").text(prod.prod_cost)
									, $("<td>").text(prod.prod_price)
									, $("<td>").text(prod.prod_outline)
								).data("prodid", prod.prod_id);
					trTags.push(trTag);
				});
			}else{
				trTags.push($("<tr>").html($("<td colspan='8'>").text("조건에 맞는 상품이 없음.")));
			}
			listBody.html(trTags);
			listBody.data("currentpage", resp.currentPage);
			$("#pagingArea").html(pagingHTML);
			searchForm.find("[name='page']").val("");
		}
	});
	
	searchForm.submit();
	// 모달창 보이는거 
	var exampleModal = $("#exampleModal").modal({
		show:false
	}).on("hidden.bs.modal", function(){
		exampleModal.find(".modal-body").empty();
		exampleModal.data("prodid", "");
	});
	// 상세 페이지로 넘어가는거 
	function loadProdView(prod_id){
		$.ajax({
			url : "<c:url value='/prod/prodView.do'/>",
			data : {
				what:prod_id
			},
			dataType : "html", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				exampleModal.find(".modal-body").html(resp);
				exampleModal.data("prodid", prod_id);
				exampleModal.modal("show");
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});
	}
	<c:if test="${not empty lastUpdateProd}">
		loadProdView("${lastUpdateProd.prod_id}");
		<c:remove scope="session" var="lastUpdateProd"/>
	</c:if>
	
	// tr를 눌렀을때 상세 화면으로 넘어가는거 
	var listBody = $("#listBody").on("click", "tr", function(){
		let prod_id = $(this).data("prodid");
		loadProdView(prod_id);
	}).css({
		cursor:"pointer"
	});
	// 수정 버튼을 눌렀을떄
	$("#updateBtn").on("click", function(){
		let prod_id = exampleModal.data("prodid");
		let currentPage = listBody.data("currentpage");
		location.href="${pageContext.request.contextPath }/prod/prodUpdate.do?what="+prod_id+"&currentPage="+currentPage;
	});
	// 추가버튼 눌렀을때 
	$("#insertBtn").on("click", function(){
		location.href="${pageContext.request.contextPath }/prod/prodInsert.do";
	});

</script>
</body>
</html>










