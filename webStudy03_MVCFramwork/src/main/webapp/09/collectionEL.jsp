<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<H4> EL을 이용한 컬레션 사용</H4>
<pre>
	<%
	
		// 배열,  list, set, map
		String[] array = new String[]{"value1","value2"};
		pageContext.setAttribute("array", array);
		
		
		List list = Arrays.asList("listValue1", "listVlue2");
		pageContext.setAttribute("list", list);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key-2", "value2");
		pageContext.setAttribute("map", map);
		
		// set의 속성을 꺼낼 수 없다. 
		Set<String> set = new HashSet<>();
		set.add("setValue1");
		set.add("setValue2");
		pageContext.setAttribute("set", set);
	%>
	
	<%--array[14] --%>, ${array[14]}
	${list.get(1)}, ${list[14] }
	${empty map ? "empty" : map }, ${map.get("key-2") }, ${map.key-2 }, ${map["key-3"] }
	${set }
</pre>
</body>
</html>