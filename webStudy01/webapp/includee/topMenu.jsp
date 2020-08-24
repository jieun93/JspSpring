<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <div class="container d-flex flex-column flex-md-row justify-content-between">
    <a class="py-2 d-none d-md-inline-block" href="#">회원관리</a>
    <a class="py-2 d-none d-md-inline-block" href="#">상품관리</a>
    <a class="py-2 d-none d-md-inline-block" href="#">Features</a>
    <a class="py-2 d-none d-md-inline-block" href="#">Enterprise</a>
    <a class="py-2 d-none d-md-inline-block" href="#">Support</a>
    <%
    String authUser = (String) session.getAttribute("authUser");
    if(StringUtils.isBlank(authUser)){
    	%>
    <a class="py-2 d-none d-md-inline-block" href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>    	
    	<%
    }else{
    	%>
    <a class="py-2 d-none d-md-inline-block" href="#"> <%=authUser%>님</a> 	
    <a id="logoutBtn" class="py-2 d-none d-md-inline-block" href="#"> 로그아웃</a>    	
    	<%
    }
    %>
 	<form id="logoutForm" method="post" action="<%=request.getContextPath()%>/login/logout.do"></form>
  </div>
</nav>
<script type="text/javascript">
	var logoutForm = $("#logoutForm");
	$("#logoutBtn").on("click", function(){
		logoutForm.submit();
	})
</script>
