<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
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
	<thead class="thead-dark">
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>연락처</th>
			<th>이메일</th>
			<th>지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	
	<c:set var="memberList" value="${pagingVO.dataList}"/>
	<c:if test="${not empty memberList}">
	<c:forEach items="${memberList}" var="member">
	<tr data-target="#exampleModal" data-toggle="modal">
			<td class="idTag">${member.mem_id}</td>
			<td>${member.mem_name }</td>
			<td>${member.mem_hp }</td>
			<td>${member.mem_mail }</td>
			<td>${member.mem_add1 }</td>
			<td>${member.mem_zip }</td>
		</tr>
	</c:forEach>
	</c:if>
	
	
<%-- 		<%
// 스코프를 통해 공유되는 데이터 꺼내시
		PagingVO<MemberVO> pagingVO = (PagingVO) request.getAttribute("pagingVO");
		List<MemberVO> memberList = pagingVO.getDataList();
		for(MemberVO list :memberList){
			// 변수를 속성으로 만들 변수가 필요하다. 
			pageContext.setAttribute("list", list);
		%>
		
		<%
		}
		%> --%>
	
		
		
	</tbody>
	<tfoot>
			<tr>
				<td colspan="5">
					<nav aria-label="...">
					${pagingVO.pagingHTML}
					<%-- 	<%=pagingVO.getPagingHTML()%> --%>
					</nav>
					<div class="form-inline" id="searchUI" >
						<select name="searchType" class="form-control">
							<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected":"" }>전체</option>
							<option value="name" ${pagingVO.searchVO.searchType eq 'name' ? "selected":"" }>이름</option>
							<option value="address" ${pagingVO.searchVO.searchType eq 'address' ? "selected":"" }>지역</option>
						</select>
						
						<input type="text" class="form-control" name="searchWord"
							value="${pagingVO.searchVO.searchWord}">
						<input class="btn btn-success" type="button" value="검색" id="searchBtn">
					</div>
				</td>

			</tr>

		</tfoot>
</table>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 정보 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<form id="searchForm">
	<input type="hidden" name="page"/>
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType}"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord}"/>
</form>
<script type="text/javascript">
	// 한줄을 클릭하게 
 	var modal =$("#exampleModal").on("hidden.bs.modal",function(){
		$(this).find(".modal-body").empty();
	 // 트래버싱,  모달창을 나가면 body의 정보를  clear
	
 	}).on("show.bs.modal",function(event){
 		console.log(event);
		let idTag = $(event.relatedTarget).find("td:nth(0)");
		let who = idTag.text();
<%-- 		location.href="<%=request.getContextPath()%>/member/memberView.do?who="+who; --%>
		$.ajax({
 			url : "${pageContext.request.contextPath}/member/memberView.do",
			data : {
				who:who
			},
			method : "get",
			dataType : "html",
			success : function(resp) {
				/* $("body").find("div:nth(5)").append(resp);
				modal.show(); */
				
				modal.find(".modal-body").html(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});
		
	}).css({cursor:"pointer"});


	var searchForm = $("#searchForm");
	var searchUI= $("#searchUI");
	$(".pagination").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit(); 
		
	});
	
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