<%-- 
    Document   : directive
    Created on : 23 Mar, 2023, 5:17:43 PM
    Author     : umash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page language="java" %>
<%@page info="composed by smvit" %>
<%@page isThreadSafe="true" %>
<%@page buffer="16kb" %>
<%@page errorPage="error.jsp" %>
<%@page autoFlush="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>showing the use of import attributes</h1>
        <h1>Todays date is <%= new Date()%></h1>
        <h1>to know how error page works ,enter n2 value as zero</h1>
        <%
            int n1=Integer.parseInt(request.getParameter("n1"));
            int n2=Integer.parseInt(request.getParameter("n2"));
        %>
        value of n1/n2 is: <%= n1/n2%>
    </body>
</html>
