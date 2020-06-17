<%-- 
    Document   : home
    Created on : Jun 11, 2020, 9:44:50 AM
    Author     : Yun
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Traveling Page</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <style>

            a:active, a:visited {
                color: blue;
            }
        </style>

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $(".datepicker").datepicker();
            });
        </script>
    </head>
    <body>
        <h1>Search Tour </h1>

        <form action="search">
            <label for="txtFromPlace">From Place:</label>
            <input type="text" name="txtFromPlace" value="${param.txtFromPlace}">
            <label for="txtToPlace">To Place:</label>
            <input type="text"  name="txtToPlace">
            <label for="txtFromDate">From Date:</label>
            <input type="text" class="datepicker"  name="txtFromDate">
            <label for="txtToDate">To Date:</label>
            <input type="text" class="datepicker" name="txtToDate">
            <label for="price">Price</label>
            <input type="text"  name="price">
            <input type="submit" value="Search" />
        </form> <br/><br/>


        <a href="login.html">Click here to login</a>





    </body>
</html>
