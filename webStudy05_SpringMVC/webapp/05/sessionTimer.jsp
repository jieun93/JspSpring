<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/sessionTimer.jsp</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous">
</script>
</head>
<body>
<div id="sessionTimer">
		<!-- 인터넷 뱅킹 타이머 구현 -->
		<!-- <div id="timerArea"></div> -->
		<!-- 1. 최초로 만료시간을 4:00의 형태로 출력 -->
		<!-- 2. 매 1초마다 타이머는 discount -->
		<!-- 3. 잔여 시간 1분이 되면, 메시지 출력 (연장 유무) -->
		<!-- 4. 예 선택시? 메시지 창 없애고, 타이머 초기화 -->
		<!-- 5. 아니오 선택시? 메시지 창 없애기 -->
		<!-- 6. 0초가 되면? 모든 작업 중단. -->

		<div id="msgArea">
			세션을 연장하시겠습니까?
		 <input type="button" value="예" class="msgBtn"	id="yesBtn" />
		 <input type="button" value="아니오" class="msgBtn" id="noBtn" />
		</div>
</div>
<%
	session.setMaxInactiveInterval(120); // 테스트 위해 2분으로 셋팅한거 
%>
<!-- sessionTimer.js에 연결하는 부분 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
<script type="text/javascript">
     /* sessionTimer 기본 화면에  초기값을 셋팅한거  120초 */
	$("#sessionTimer").sessionTimer(<%=session.getMaxInactiveInterval()%>);
</script>
</body>
</html>