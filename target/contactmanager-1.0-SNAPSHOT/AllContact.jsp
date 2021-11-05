<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Map"%>
    <%@page import="java.util.Map.Entry"%>
    <%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.contactmanager.store.Contact"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>All Contacts</h5>
<%
out.println(request.getAttribute("list"));
%>
</body>
</html>