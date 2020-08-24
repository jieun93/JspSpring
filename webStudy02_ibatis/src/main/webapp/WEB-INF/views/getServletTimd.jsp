<%@page import="java.util.Date"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--     <%
    
    // JSON(Jacascript Object Notation)
    // 자바에서 사용방법 
    class{
    	String prop = "value";
    }
    // 자바 스크립트에서 사용방법
    {
    	prop:"value";
    }
    
    **여기서 하는게 마쉘링 하는거  정적인 테스트로 기록(직렬확) **
    %> --%>
{"time":"<%=new Date() %>"} 
