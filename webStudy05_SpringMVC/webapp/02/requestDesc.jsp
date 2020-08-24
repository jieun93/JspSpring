<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/requestDesc.jsp</title>
</head>
<body>
<h4>HttpServletRequest(요청)</h4>
<pre>
		HttpServletRequest req : 클라이언트의 요청 정보를 캡슐화한 객체;
		HTTP 에 의해 요청이 패키징 되는 구조
		1. Request Line : URL, Protocol(version), Http Method
			Http Method : 요청의 목적 / 방법(수단, 요청을 포장하는 방법)
			1) GET (R) : 조회, Body 없음.
			2) POST(C)	: 등록, Body 존재.
			3) PUT (U)	: 수정, Body 존재, form이 put을 지원하지 않을때, method="post"로 설정,
							"_method" name의 hidden 태그 생성.
			4) DELETE (D)	: 삭제 , 보낼 데이터는 없지만, 삭제할 정보를 넘겨야 한다.
			
			5) Headers : 차후 응답이 전송될떄, body가 없음
			6) Options : preflight 요청에 주로 사용 / 지원이 되는지 확인하는 목적
			7) Trace : server debugging 
			
		2. Requset Header : 클라이언트와 요청에 대한 부가 정보 (meta data)
		3. Requset Body(Content Body, Message Body) : 클라이언트의 명시적인 전송 컨텐츠(메세지)
		
		
		요청 파라미터의 전송 구조
		1. post : body를 통해서 전송 
		  특수문자처리  requset.setCharacterEncoding(encoding);
		  -요청의 body영역내에 있는 특수분자에 대한 디코딩 방식 결정
		2. get : line을 통해서 query String의 형태로 전송
			URL 에 queryString의 사용되는 형식 
			주소 ? queryString 
					sector1&sector2&...&setorN
										para_name=param_value
			특수문자처리
			ex) server.xml => htt connector URLEncoding 설정으로 서버의 주소 해석방법을 변경
				useBodyEncondingForURI 설정은 
				setCharacterEncoding 메소드를  get 방식에서 사용
		a 태그느 클라이언트가 사용		
		<a href="<%=request.getContextPath() %>/02/factorial.jsp?num=5">5!연산수행</a>
				
		서버에서 파라미터 확보
		1. getParameter(name)
		2. getParameterValues(name) : 하나의 파라미터명을 여러값이 전달 될떄
		3. Map&lt;String, String%gt;getParameterMap()	
		4. Enumeration&lt;String&gt;getParameterNames() : 모든 파라미터명을 확보                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 	
				
												
</pre>

</body>
</html>