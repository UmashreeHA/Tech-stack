<%-- 
    Document   : JSPApplet
    Created on : 23 Mar, 2023, 4:14:52 PM
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
        <jsp:plugin type="applet" code="AppDemo.class" width="400" height="400">
            <jsp:fallback><p>unable t0 load applet</p></jsp:fallback>
        </jsp:plugin>
    </body>
</html>
