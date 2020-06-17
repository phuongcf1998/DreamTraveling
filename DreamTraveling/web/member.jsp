<%-- 
    Document   : member
    Created on : Jun 14, 2020, 10:05:18 PM
    Author     : Yun
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Page</title>
        
        
        <style>

            a:active, a:visited {
                color: blue;
            }
        </style>
    </head>
    <body>
        <c:set var="member" value="${sessionScope.MEMBER.fullName}"/>
        <c:set var="listTour" value="${requestScope.LIST_TOUR}"/>

        <c:url var="logOutLink" value="logOut">
            <c:param name="btAction" value="LogOut" />

        </c:url>
        
         <c:url var="viewCartLink" value="viewCart">
           

        </c:url>



        <a href="${logOutLink}"><font color="blue">Log out </font></a><br/><br/>

        <c:if test="${empty member}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>

        <h1><font color="red">Welcome ${member}</font></h1>
        <br/>

        <form action="addToCart">
            <select name="txtTourID"> 
                <c:forEach var="row" items="${listTour}">
                    <option value="${row.tourID}">${row.tourName}</option>
                </c:forEach>


            </select>

            <input type="submit" value="Add to cart" name="btAction"/>
        </form> <br/><br/><br/>
        
        <a href="view_cart.jsp">View Cart </a>


    </body>
</html>
