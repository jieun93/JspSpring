<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/bufferDesc.jsp</title>
</head>
<body>
<h4>buffer 제어</h4>
<pre>
 	: 전송 효율 향상을 위해 서버상에 존재하는 임시 데이터 저장 공간
	  JspWriter 타입의  out 객체를 통해 버퍼의 상태 확인 및 제어.
	    버퍼가 flush되거나 비정상 클리어 된 이후에는 버퍼의 내용을 변경하거나, forward 가 불가능 
	    현재 버퍼의 크기 : <%=out.getBufferSize()%>
	    현재 버퍼의 남은 크기 :<%=out.getRemaining() %>	
	    버퍼를 비워주는거  : flush(), clear(), clearBuffer() 
	 <%
	 	for(int i = 1; i<=100; i++){
	 		out.println(i+"번째 반복");
// 	 		if(i ==94){
// 	 			out.flush();
// 	 		}
	 		if(i==95){
// 	 			throw new RuntimeException("강제 발생 예외");
	 		request.getRequestDispatcher("/05/implicitObject.jsp").include(request, response);
	 		}
	 	}
	 %>
</pre>
</body>
</html>



