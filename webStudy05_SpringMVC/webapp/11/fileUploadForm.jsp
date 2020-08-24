<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/fileUpload</title>
</head>
<body>
<h4> 파일 업로드 </h4>
<pre>
	 파일업로드 처리 단계
	 1. 클라이언트 사이드
	 	1) form 구성 (method="post" enctype="multipart/form-data")
	 	2) input type="file" accept="mime"
	 	***enctype : body 영역의 데이터 mime 결정 , multipart? 입력 태그의 갯수만큼 body 영역이 여러개의 part로 분리됨.
	 	
	 2. 서버 사이드(servlet 3.0 이후 )
	 	1) Multipart-config 설정(Part API 사용 가능 )을 가진 서블릿
	 	2) Part  request.getPart,Collection&lt;Part&gt; request.getParts()
	 	*** part_name 은 input name ="part_name" 으로 결정.
	 	*** 데이터를 입력하지 않더라도 비어있는 part는 형성됨.
	 	*** 일반 데이터와 파일 데이터에 대한 분리 처리가 필요 .
	 	
</pre>

<form action="${pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data" >
	<ul>
		<li><input type="text" name="uploader" placeholder="업로더"/></li>
		<li><input type="file" name="uploadFile"  multiple accept="image/*"/>
			<input type="submit" value="업로드">
		</li>
	</ul>
</form>
<c:if test="${not empty sessionScope.fileList }">
	<c:forEach items="${fileList}" var="file">
		<img src ="<c:url value='/images/${file.name }'/>" />
	</c:forEach>
</c:if>
</body>
</html>