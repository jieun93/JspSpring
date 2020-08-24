<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.servlet04.FileWrapper"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/browsing.css?time=<%=new Date().getTime()%>">

<%
	 FileWrapper[] childArray = (FileWrapper[]) request.getAttribute("childArray");	
%>
<ul id="browser">
	<li id="/"><span class="caret"><%=request.getContextPath()%></span>
</ul>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/browsing.js?time=<%=new Date().getTime()%>"></script>
<script type="text/javascript">
 	$("#browser").browse({
 		browsingURL: "<%=request.getContextPath()%>/ddit/contextBrowse.do",
		eleClass : "caret"
		,processURL :"<%=request.getContextPath()%>/ddit/fileProcess.do"
	});
</script>
