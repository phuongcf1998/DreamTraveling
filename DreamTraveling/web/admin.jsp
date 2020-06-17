<%-- 
    Document   : admin
    Created on : Jun 14, 2020, 10:02:08 PM
    Author     : Yun
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <c:set var="admin" value="${sessionScope.ADMIN.fullName}"/>
         
         <c:url var="logOutLink" value="logOut">
        </c:url>
         
        


        <a href="${logOutLink}"><font color="blue">Log out </font></a><br/><br/>
        
        <c:if test="${empty admin}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
         
        <h1><font color="red">Welcome ${admin}!</font></h1>
        
       
        <a href="create_tour.jsp"><font color="blue">Create Tour </font></a><br/><br/>
        
        
    </body>
</html>
