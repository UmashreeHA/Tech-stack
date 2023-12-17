<%-- 
    Document   : main
    Created on : 12 Mar, 2023, 10:30:19 PM
    Author     : umash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>STUDENT DETAILS</h1>
        <jsp:useBean id="std" class="bean.Student" scope="request"/>
        <h1>NAME:<jsp:getProperty name="std" property="name"/></h1>
        <h1>BRANCH:<jsp:getProperty name="std" property="branch"/></h1>
    </body>
</html>
