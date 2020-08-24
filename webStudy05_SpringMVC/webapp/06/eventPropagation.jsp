<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/eventPropagation.jsp</title>
<style type="text/css">
	div{
		padding : 10px;
		border: 1px solid black;
	}

</style>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<body>
<h4>이벤트 전파 </h4>
	Bubbling : 가장 하위에 있는 엘리먼트에서 부터 가장 상위에 있는 엘리먼트로 전파되는 이벤트   / 제이쿼리의 기본 이벤트 전파 방식 
	Capturing	: 상위 엘리먼트에서 하위 엘리먼트로 전파되는 이벤트 
	
	
	엘리먼트의 댑스 구조?\
	<div id="outer">
		<div id="middle">
			<div id="inner">
				여기를 클릭 할 것 .
			</div>
		</div>
	</div>
	
	<button id="newBtn">새로만들기 </button>
<script type="text/javascript">

//capturing 방식 
// 	var divs =  document.querySelectorAll("div");
// 	divs.forEach(function(div){
// 		div.addEventListener("click",function(event){
// 			let id =  this.id;
// 			alert(id);
// 		}, true);  // false로 하면 bubbling  방식이 된다. 
// 	});



// 버블링 방식
// 	$("div").on("click", function(){
// 	let id = $(this).prop("id");
// 	alert(id);
// 	});
 $("#newBtn").on("click", function(){ // 새로만들기버튼을 누르면 
	$("#inner").append( $("<div>").prop("id","newInner").text("새로만든 디브"));
 // inner 안에 div를 만들어준다. 
	alert(divs.length);
 });
 
 // 이벤트 핸들러를  갖다 붙일경우에는 정적인곳에 붙여야 한다.
 // 동적인 곳에는 이벤트 핸들러를 적용하지 말아라!!!!

 // propagation 구조 
 //  body가 먼저 이벤트릅 받고 다음 이벤트가 실행되낟. 
	var divs = $("body").on("click", "#inner",function(event){
		let id = $(this).prop("id");
// 	if(id=="middle"){
// 		event.stopPropagation();
// 	}
		alert(id);
	});
</script>	
</body>
</html>