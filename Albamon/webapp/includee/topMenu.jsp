<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <div class="container d-flex flex-column flex-md-row justify-content-between">
  	<span class="py-2 d-none d-md-inline-block">
  		<input class="flag" type="image" src="${pageContext.request.contextPath }/resources/images/us.jpg" 
  				onclick="location.href='?lang=en';"/>
  		<input class="flag" type="image" src="${pageContext.request.contextPath }/resources/images/korea.jpg"  
  				onclick="location.href='?lang=ko';"/>
  	</span>
    <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }">
    	<spring:message code="menu1" />
    </a>
    <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }/board">
    	<spring:message code="menu2" />
    </a>
    <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }/board/form">
    	<spring:message code="menu3" />
    </a>
<%--     <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }/login"> --%>
<%--     	<spring:message code="menu4" /> --%>
<!--     </a> -->
<%--     <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }/logout"> --%>
<%--     	<spring:message code="menu5" /> --%>
<!--     </a> -->
<%--     <a class="py-2 d-none d-md-inline-block text-white" href="${pageContext.request.contextPath }/member"> --%>
<%--     	<spring:message code="menu6" /> --%>
<!--     </a> -->
  </div>
 </nav>  









