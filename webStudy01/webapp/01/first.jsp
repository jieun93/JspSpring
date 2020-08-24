<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<style type = 'text/css'>
 table{
  border-collapse : collapse;
 }
 td,th{
 	border: 1px solid black;
 }
</style>
<body>
<h4>current server time : <%= new Date() %></h4>

</body>
</html>