<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> 화면전환되는 부분 -->
<title>04/autoRequest.jsp</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
 <script type="text/javascript">
  	$(function(){
  		let timearea = $("#timearea");
  		let secArea = $("#secArea");
  		$("#cancelBtn").on("click",function(){
  			clearInterval(jobId);
  			$(this).prop("disabled",true);
  		});
//   		setTimeout(function(){
//   			location.reload();
//   		},3000);
		let initTime = 5;
		let jobId =  setInterval(function(){
			secArea.text(--initTime);
			
	  		$.ajax({
				url : "<%=request.getContextPath()%>/getServletTimd.do", /* 절대경로 */ 
				method : "get",
				dataType :"xml",  //사용 데이터 타입에 따라 달라진다. 
				success : function(resp){
//	 				alert(resp.time);
// 					timearea.text(resp.time); // json 일떄 
// 					timearea.html(resp); // html
// 					timearea.text(resp); // text
					//dom 트리 구조
					let serverTime =  $(resp).find("time").text(); // xml에 해당하는 구조 
					timearea.text(serverTime);
				},
				error : function(errorResp){
					console.log(errorResp.status+":"+errorResp.responseText);
				}
	  		});
		},1000); //1초마다 바뀌는걸로 셋팅
  	});
  </script>
</head>
<body>
<button id="cancelBtn">비동기 요청 취소</button>
<span id="secArea"></span> 뒤에 네이버로 이동합니다.
<h4> auto request</h4>
<pre>
	: 서버 사이드에서 주기적으로 갱신되는 데이터를 실시간으로 보여주기 위함
	1. server side : refresh header
	서버로 부터 한번 가져온 시간 : <%=new Date()%>
	서버의 현재 갱신되는 시간 : <span id="timearea"></span>
	<%--
	//전체 부분을 다 refresh 할 필요없다.
	// rock 어떤상황이 발생했을 때 제어권한을 주는거 
	// rock 동기
	// rock 없으면 비동기(ex)눈치게임 --> 순서가 없다. 제어가 없는 상황 )
		response.setIntHeader("Refresh", 1); // 갱신할수 있는 시간을 제공 기본으로 int -> 초 
	--%>
	2. client side :
		자바스크립트의 스케쥴링 함수 
		
		1)javascript의 스케줄링 함수 
			setTimeout  : 일정시간 동안 지연시켰다가 단 한번 실행
			setInterval : 일정시간을 주기로 계속 반복되는 실행 
		
		2) html meta 태그 사용.
		
		
	

</pre>
</body>
</html>