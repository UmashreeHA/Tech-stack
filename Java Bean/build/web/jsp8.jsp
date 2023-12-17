<%-- 
    Document   : jsp8
    Created on : 12 Mar, 2023, 10:28:40 PM
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
        <jsp:useBean id="std" class="bean.Student" scope="request"/>
        <jsp:setProperty name="std" property="*"/>
        <jsp:forward page="main.jsp"/>
                  
    </body>
</html>
