<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>  

<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th><spring:message code="no"/></th>
			<th><spring:message code="member.mem_id"/></th>
			<th><spring:message code="member.mem_name"/></th>
			<th><spring:message code="member.mem_hp"/></th>
			<th><spring:message code="member.mem_mail"/></th>
			<th><spring:message code="member.mem_add1"/></th>
			<th><spring:message code="member.mem_mileage"/></th>
		</tr>
	</thead>
	<tbody>
		<c:set value="${pagingVO.dataList }" var="memberList" />
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member">
				<tr data-toggle="modal" data-target="#exampleModal">
					<td>${member.rnum }</td>
					<td class="idTag">${member.mem_id }</td>
					<td>${member.mem_name }</td>
					<td>${member.mem_hp }</td>
					<td>${member.mem_mail }</td>
					<td>${member.mem_add1 }</td>
					<td>${member.mem_mileage }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty memberList }">
			<tr><td colspan="7"> 조건에 맞는 회원이 없음. </td></tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<nav aria-label="...">
					${pagingVO.pagingHTML }
				<div class="form-inline" id="searchUI">
					<select name="searchType" class="form-control mr-2">
						<option value="all" ${pagingVO.searchVO.searchType eq 'all'?"selected":"" }>전체</option>
						<option value="name" ${pagingVO.searchVO.searchType eq 'name'?"selected":"" }>이름</option>
						<option value="address" ${pagingVO.searchVO.searchType eq 'address'?"selected":"" }>지역</option>
					</select>
					<input type="text" class="form-control mr-2" name="searchWord"
						value="${pagingVO.searchVO.searchWord }"
					>
					<input class="btn btn-success" type="button" value="검색" id="searchBtn">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 정보 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<form id="searchForm">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paging.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript">
	var sampleModal = $("#exampleModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	}).on("show.bs.modal", function(event){
		console.log(event);
		let idTag = $(event.relatedTarget).find("td:nth(1)");
		let who = idTag.text();
		$.ajax({
			url : "${pageContext.request.contextPath }/member/memberView.do",
			data : {
				who:who
			},
			method : "get",
			dataType : "html", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				sampleModal.find(".modal-body").html(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});	
	}).css({cursor:"pointer"});

	var searchForm = $("#searchForm").paging({
		searchUI:"#searchUI",
		searchBtn:"#searchBtn",
		pagination:".pagination",
		pageParam:"page"
	});
	
	
</script>





