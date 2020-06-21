<%-- 
    Document   : book_tour
    Created on : Jun 20, 2020, 9:30:37 PM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Tour</title>
        <style>

            a:active, a:visited {
                color: blue;
            }
            .center {
                display: flex;

                justify-content: space-around;
            }
            .tourDetail{
                display: flex;
                justify-content: center;
                align-items: center;

            }
            .hidden{
                display: none;
            }

        </style>
    </head>
    <body>
        <c:set var="member" value="${sessionScope.MEMBER.fullName}"/>
        <c:set var="listTour" value="${requestScope.LIST_TOUR}"/>
        <c:set var="tourExpired" value="${requestScope.TOUR_EXPIRED}"/>
        <c:set var="tourFull" value="${requestScope.TOUR_FULL}"/>
        <c:if test="${empty member}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <div class="center">
            <a href="member.jsp">Back Home</a>
            <form action="addToCart" method="POST">
                <select id="listTour" name="txtTourID" onchange="showDetailTour()"> 
                    <c:forEach var="row" items="${listTour}">
                        <option value="${row.tourID}" 
                                <c:if test="${row.tourID == param.txtTourID}">selected</c:if>>${row.tourName}-${row.tourID}</option>
                    </c:forEach>


                </select>
                <input type="submit" value="Add to cart" name="btAction"/>

            </form> <br/><br/><br/>


            <a href="view_cart.jsp"><font color="red">View Cart</font> </a> <br/>
        </div>
        <c:if test="${not empty tourExpired }">
            <div style="display: flex;justify-content: center"><font color="red"> ${tourExpired}</font><br/><br/></div>
            </c:if>
            <c:if test="${not empty tourFull }">
            <div style="display: flex;justify-content: center"><font color="red">${tourFull}</font><br/><br/></div> 
            </c:if>




        <c:forEach var="row" items="${listTour}">
            <div id="${row.tourID}" class="tourDetail hidden">
                <c:if test="${not empty row.imageName}">
                    <img src="images/${row.imageName}" style="display:block; width:300px; height:300px;">
                </c:if>
                <c:if test="${empty row.imageName}">
                    <img src="images/notFound.png" style="display:block; width:300px; height:300px;">
                </c:if>

                <div style="margin-left: 30px;">
                    <h3> ${row.tourName}-${row.tourID}</h3>

                    <p>Date : <font color="red"><fmt:formatDate value="${row.fromDate}" pattern="dd-MMM-yyyy"/> to </font>
                        <font color="red"><fmt:formatDate value="${row.toDate}" pattern="dd-MMM-yyyy"/></font> </p>
                    <p>Place : <font color="red">${row.fromPlace}-${row.toPlace}</font></p>
                    <p>Quota : <font color="red">${row.quota}</font></p>
                </div>

            </div>
        </c:forEach>







        <script type="text/javascript">
            <c:if test="${not empty param.txtTourID}">
            var element = document.getElementById("${param.txtTourID}");
            element.classList.remove("hidden");

            </c:if>
            <c:if test="${empty param.txtTourID}">
            var totalTour = document.getElementsByClassName("tourDetail")

            totalTour[0].classList.remove("hidden");

            </c:if>
            function showDetailTour() {

                var tourID = document.getElementById("listTour").value;
                var totalTour = document.getElementsByClassName("tourDetail")
                for (var i = 0; i < totalTour.length; i++) {
                    if (totalTour[i].id == tourID) {
                        totalTour[i].classList.remove("hidden");
                    } else
                        totalTour[i].classList.add("hidden");

                }
            }


        </script>





    </body>
</html>
