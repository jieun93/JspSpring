<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <div class="container d-flex flex-column flex-md-row justify-content-between">
 
  <span class="py-2 d-none d-md-inline-block"> 
  	<input type="image" class="flag" src="${cPath }/images/us.jpg" onclick="location.href='${cPath}?lang=en';"/>
  	<input type="image" class="flag" src="${cPath }/images/korea.jpg" onclick="location.href='${cPath}?lang=ko';"/>
  </span>
 
    <a class="py-2 d-none d-md-inline-block" href="<%=request.getContextPath() %>/member/memberList.do">
    	<spring:message code="menu1"/>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="<%=request.getContextPath() %>/ddit/dditStudent.do">
    <spring:message code="menu2"/>
    </a>
   <a class="py-2 d-none d-md-inline-block" href="<%=request.getContextPath()%>/prod">
   <spring:message code="menu3"/>
   </a> 
    <a class="py-2 d-none d-md-inline-block" href="<%=request.getContentLength()%>/board">
    <spring:message code="menu4"/>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
    <spring:message code="menu5"/>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
    <spring:message code="menu6"/>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
    
    </a>
    <%
    MemberVO authUser = (MemberVO)session.getAttribute("authUser");
    if(authUser == null){
    	%>
    <a class="py-2 d-none d-md-inline-block text-white" href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>    	
    <a class="py-2 d-none d-md-inline-block text-white" href="<%=request.getContextPath()%>/member/insertView.do">회원가입</a>    	
    	<%
    }else{
    	%>
    <a class="py-2 d-none d-md-inline-block text-white" href="<%=request.getContextPath()%>/mypage.do"> <%=authUser.getMem_name()%>님(${authUser.mem_roles})</a> 	
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
