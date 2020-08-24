<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래처 추가 페이지</title>
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
<div class = "container">
<%
	// 신규 등록, 수정할 때 공통적으로 전달
	BuyerVO buyer =(BuyerVO) request.getAttribute("buyer"); 
	Map<String,String> errors = (Map)request.getAttribute("errors");
	String message = (String)request.getAttribute("message");
	
	
	
	// 수정 시 전달
	if(buyer==null) buyer = new BuyerVO();
	if(errors==null) errors = new HashMap<>();
	if(StringUtils.isNotBlank(message)){
		out.println(message);
	}
%>
<!-- // 추가 버튼을 누르면 action이 servlet으로 가는거  -->
<form method="post" id="insertForm" action="${pageContext.request.contextPath}/buyer/insertBuyer.do" >
	
	<div class="form-group row">
		<label for="buyer_id" class="col-sm-2 col-form-label">buyer_id</label>
			<div class="col-sm-10">
			<input type="text"  class="form-control" name="buyer_id" value="${buyer.buyer_id }" required readonly >
			<span class="error">${errors.buyer_id }</span>
			</div>
	</div>
	 
  <div class="form-group row">
    <label for="buyer_name" class="col-sm-2 col-form-label">거래처명</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="buyer_name" name="buyer_name" value="${buyer.buyer_name}">
      <span class="error">${errors.buyer_name }</span>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="buyer_lgu" class="col-sm-2 col-form-label">LGU</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="buyer_lgu" name="buyer_lgu"  value="${buyer.buyer_lgu}">
      <span class="error">${errors.buyer_lgu}</span>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="text" class="col-sm-2 col-form-label">COMTEL</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="buyer_comtel" name="buyer_comtel"  value="${buyer.buyer_comtel}">
      <span class="error">${errors.buyer_comtel}</span>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="text" class="col-sm-2 col-form-label">FAX</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="buyer_fax" name="buyer_fax"  value="${buyer.buyer_fax}">
      <span class="error">${errors.buyer_fax}</span>
    </div>
  </div>
  
  
  	
  <div class="form-group row">
    <label for="text" class="col-sm-2 col-form-label">MAIL</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" id="buyer_mail" name="buyer_mail"  value="${buyer.buyer_mail}">
     	<span class="error">${errors.buyer_mail}</span>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="text" class="col-sm-2 col-form-label">주소1</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" id="buyer_add1" name="buyer_add1"  value="${buyer.buyer_add1}">
     	<span class="error">${errors.buyer_add1}</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="text" class="col-sm-2 col-form-label">주소2</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" id="buyer_add2" name="buyer_add2"  value="${buyer.buyer_add2}">
     	<span class="error">${errors.buyer_add2}</span>
    </div>
  </div>
  <div class="form-group row">			 
	<button class="btn btn-warning" type="reset">취소</button>			 
	<button class="btn btn-success" type="submit">추가</button>			 
	<button class="btn btn-info" type="button" onclick="location.href='buyerList.do';">목록으로</button>			 
  </div>
</form>
</div>

</body>
</html>