<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/oneConnOneProcess.jsp</title>
</head>
<body>
<%
	//   목적 : 시간을 체크해보자!!
		
		long startTime = System.currentTimeMillis();
		String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = 'a001' ";
		String name = null;
		try(
				
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);	
			){
			   
			  ResultSet rs  = pstmt.executeQuery();
				if(rs.next()){
					 name =  rs.getString(1);
					
				}
			
		}catch(SQLException e){//checked  어댑티 
			throw new RuntimeException(e); //unchecked  어댑터
		}
		out.println(name);
		long endTime = System.currentTimeMillis();
	
	
	%>
	소요시간 : <%=(endTime -startTime)%> ms
	
</body>
</html>