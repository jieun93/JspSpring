<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생관리 </title>
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

<!-- <style type="text/css">
	table {
      border: 1px solid black;   
   }
   th,td{
      border: 1px solid black;
   }
   .yellow{
      background-color: yellow;
   }
</style> -->
</head>
<body>
등록된 모든 학생 조회
<a href="${pageContext.request.contextPath}/ddit/regist.do">신규등록</a>
<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>			
		</tr>
	</thead>
	<tbody>
<%--
//  String	 student = (String) session.getAttribute("last"); // 마지막 학생
//  session.removeAttribute("student"); // 세션을 지워주는거 
 PagingVO<DDITStudentVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
 List<DDITStudentVO> allStudents = pagingVO.getDataList();  // 모든학생
//  DDITStudentVO lastStudent = (DDITStudentVO)session.getAttribute("lastStudent");

	 for( DDITStudentVO vo : allStudents){

--%>
<c:set var="allStudents" value="${pagingVO.dataList}" />
<c:forEach items="${allStudents}" var="Students">

	<tr>
		<%-- <td><%=vo.getCode()%></td> --%>
		<td>${Students.code}</td>
		<td><a href="${pageContext.request.contextPath}/ddit/studentView.do?code=${Students.code}">${Students.name}</a></td>
		<td>${Students.age}</td>
		<td>${Students.gen}</td>
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
					<%-- 	<%=pagingVO.getPagingHTML()%> --%>
						${pagingVO.pagingHTML}
					</nav>
					<div class="form-inline" id="searchUI" >
						<select name="searchType" class="form-control">
							<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected":"" }>전체</option>
							<option value="name" ${pagingVO.searchVO.searchType eq 'name' ? "selected":"" }>이름</option>
							<option value="age" ${pagingVO.searchVO.searchType eq 'age' ? "selected":"" }>나이</option>
							<option value="gen" ${pagingVO.searchVO.searchType eq 'gen' ? "selected":"" }>성별</option>
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
<form id="searchForm">
	<input type="text" name="page"/>
	<input type="text" name="searchType" value="${pagingVO.searchVO.searchType}"/>
	<input type="text" name="searchWord" value="${pagingVO.searchVO.searchWord}"/>
</form>
<script type="text/javascript">
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