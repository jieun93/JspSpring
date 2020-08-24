<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/performance.jsp</title>
</head>
<body>
<h4>성능 체크와 성능 향상 요인</h4>
<pre>
	성능 테스트 :jmeter 툴을 사용한다. 
	1. 시간 : response time = latency time(99.999%) + processing time(0.1111..%) (비율이 어떻게 되는지 조회 해보자)
	      한번의 지연시간과 한번의 처리 시간 : 10ms  / 1ms 
	   100번의 지연시간과 100번의 처리 시간 : 650ms / 8ms
	      한번의 지연시간과 100번의 처리 시간 : 15ms 
		
		
		Pooling 시스템을 통해 latency time을 감소 .
		
	
	2. 공간 : memory
	


</pre>
</body>
</html>