<%-- 
    Document   : index
    Created on : Jun 11, 2020, 9:20:11 AM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="new" enctype="multipart/form-data">
            Choose a file: <input type="file" name="multiPartServlet" />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
