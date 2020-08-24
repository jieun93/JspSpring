<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 입력 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" >
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<form action="<c:url value='${currentAction }'/>" method="post" enctype="multipart/form-data"> <!-- form 의 action을 비워두면 자기경로로 간다 -->
	<input class="form-control" type="hidden" name="currentPage" value="${param.currentPage }"/>
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td><input class="form-control" type="text" name="prod_id"  readonly
				value="${prod.prod_id }">
				<span class="error">${errors.prod_id }</span></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><input class="form-control" type="text" name="prod_name" 
				value="${prod.prod_name }">
				<span class="error">${errors.prod_name }</span></td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td>
				<select class="dynamicElement form-control mr-2" name="prod_lgu"
					data-url="<c:url value='/prod/getLprodList.do'/>"
				>
					<option value>상품분류</option>
		
				</select>
				<span class="error">${errors.prod_lgu }</span></td>
		</tr>
		<tr>
			<th>거래처코드</th>
			<td>
				<select class="dynamicElement form-control mr-2" name="prod_buyer"
					data-url="<c:url value='/prod/getBuyerList.do' />"
				>
					<option value="">거래처</option>
					
				</select>
				<span class="error">${errors.prod_buyer }</span></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input class="form-control" type="number" name="prod_cost" 
				value="${prod.prod_cost }">
				<span class="error">${errors.prod_cost }</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input class="form-control" type="number" name="prod_price" 
				value="${prod.prod_price }">
				<span class="error">${errors.prod_price }</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input class="form-control" type="number" name="prod_sale" 
				value="${prod.prod_sale }">
				<span class="error">${errors.prod_sale }</span></td>
		</tr>
		<tr>
			<th>정보</th>
			<td><input class="form-control" type="text" name="prod_outline" 
				value="${prod.prod_outline }">
				<span class="error">${errors.prod_outline }</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input class="form-control" type="text" name="prod_detail"
				value="${prod.prod_detail }">
				<span class="error">${errors.prod_detail }</span></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td><input class="form-control" type="file" name="prod_image" 
				value="${prod.prod_img }">
				<span class="error">${errors.prod_image }</span></td>
		</tr>
		<tr>
			<th>재고</th>
			<td><input class="form-control" type="number" name="prod_totalstock" 
				value="${prod.prod_totalstock }">
				<span class="error">${errors.prod_totalstock }</span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input class="form-control" type="date" name="prod_insdate"
				value="${prod.prod_insdate }">
				<span class="error">${errors.prod_insdate }</span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input class="form-control" type="number" name="prod_properstock" 
				value="${prod.prod_properstock }">
				<span class="error">${errors.prod_properstock }</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input class="form-control" type="text" name="prod_size"
				value="${prod.prod_size }">
				<span class="error">${errors.prod_size }</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input class="form-control" type="text" name="prod_color"
				value="${prod.prod_color }">
				<span class="error">${errors.prod_color }</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input class="form-control" type="text" name="prod_delivery"
				value="${prod.prod_delivery }">
				<span class="error">${errors.prod_delivery }</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input class="form-control" type="text" name="prod_unit"
				value="${prod.prod_unit }">
				<span class="error">${errors.prod_unit }</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input class="form-control" type="number" name="prod_qtyin"
				value="${prod.prod_qtyin }">
				<span class="error">${errors.prod_qtyin }</span></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input class="form-control" type="number" name="prod_qtysale"
				value="${prod.prod_qtysale }">
				<span class="error">${errors.prod_qtysale }</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input class="form-control" type="number" name="prod_mileage"
				value="${prod.prod_mileage }">
				<span class="error">${errors.prod_mileage }</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-success mr-2" value="저장" />
				<input type="reset" class="btn btn-warning mr-2" value="취소" />
				<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
					onclick="history.back();"
				/>
				<input type="button" class="btn btn-secondary" value="목록으로" 
					onclick="location.href='${pageContext.request.contextPath}/prod/prodList.do';"
				/> <!-- 동기식으로 화면전환 -->
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dynamicSelect.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paiging.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript">
	
	var optionPtrn = "<option value='%V' %S>%T</option>"; // 목록을 옵션으로 만들어 놓은거 
	
	// 상품분류의 옵션 부분
	var prod_lguTag = $("select[name='prod_lgu']").data("success", function(resp){
		var html = "";
		$.each(resp, function(idx, lprod){
			html += optionPtrn.replace("%V", lprod.lprod_gu) //  분류 GU를 넣은거 
							 .replace("%T", lprod.lprod_nm)	 //  분류 NM을 넣은거 
							 .replace("%S", lprod.lprod_gu == "${prod.prod_lgu}"?"selected":""); // 선택한 값이 같으면  선택 아니면 공백
		});
		prod_lguTag.append(html); //
	}).on("change", function(){
		let prod_lgu = $(this).val();

		prod_buyerTag.trigger("renew", {
			prod_lgu : prod_lgu
		});
	});
	
	// 거래처 부분의  옵션 부분 
	var prod_buyerTag = $("select[name='prod_buyer']").data("success", function(resp){
		var html = "<option value>거래처</option>";
		$.each(resp, function(idx, buyer){
			html += optionPtrn.replace("%V", buyer.buyer_id) // 거래처 ID
							  .replace("%T", buyer.buyer_name)	// 거래처 NAME
							  .replace("%S", buyer.buyer_id == "${prod.prod_buyer}"?"selected":"");
		});
		prod_buyerTag.html(html);
	});
	
	$(".dynamicElement").dynamicSelect();  // dynamicselect.js 파일 호출 
</script>	
</body>
</html>