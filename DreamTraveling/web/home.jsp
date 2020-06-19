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
            .center {
                display: flex;
                justify-content: center;
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
        <c:set var="seachResult" value="${requestScope.SEARCH_RESULT}" />
        <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}" />
        <c:set var="txtFromPlace" value="${param.txtFromPlace}" />
        <c:set var="txtToPlace" value="${param.txtToPlace}" />
        <c:set var="txtFromDate" value="${param.txtFromDate}" />
        <c:set var="txtToDate" value="${param.txtToDate}" />
        <c:set var="price" value="${param.price}" />
        <h1>Search Tour </h1>

        <form action="search">
            <label for="txtFromPlace">From Place:</label>
            <input type="text" name="txtFromPlace" value="${param.txtFromPlace}">
            <label for="txtToPlace">To Place:</label>
            <input type="text"  name="txtToPlace"  value="${param.txtToPlace}">
            <label for="txtFromDate">From Date:</label>
            <input type="date"  name="txtFromDate" required pattern="\d{4}-\d{2}-\d{2}" value="${param.txtFromDate}">
            <label for="txtToDate">To Date:</label>
            <input type="date"  name="txtToDate" required pattern="\d{4}-\d{2}-\d{2}" value="${param.txtToDate}">
            <label for="price">Price</label>
            <input type="text"  name="price" value="${param.price}">
            <input type="submit" value="Search" />
        </form> <br/><br/>


        <a href="login.html">Click here to login</a>


        <div class="center">
            <c:forEach begin="1" end="${totalPage}" var="i">
                <c:url var="currentPageLink" value="search">

                    <c:param name="page" value="${i}" />

                </c:url>
                <a id="${i}"  style="margin: 5px" href="${currentPageLink}">${i}</a>
            </c:forEach>

        </div>
        <c:if test="${not empty seachResult}">
            <div class="center" style="width: 100%">
                <table border="1" >
                    <thead>
                        <tr>

                            <th>No.</th>
                            <th>Tour Name</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th>Price</th>
                            <th>Quota</th>
                            <th>Image</th>
                            <th>From Place</th>
                            <th>To Place</th>


                        </tr>

                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${seachResult}" varStatus="counter">
                        <form>
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.tourName}


                                </td>
                                <td>
                                    ${dto.fromDate}
                                </td>
                                <td>
                                    ${dto.toDate}

                                </td>

                                <td>
                                    ${dto.price}
                                </td>

                                <td>
                                    ${dto.quota}
                                </td>
                                <td>
                                    ${dto.image}
                                </td>
                                <td>
                                    ${dto.fromPlace}
                                </td>
                                <td>
                                    ${dto.toPlace}
                                </td>

                            </tr>
                        </form>

                    </c:forEach>
                    </tbody>


                </table>
            </div>

        </c:if>
        <c:if test="${empty seachResult and not empty totalPage}">


            <h1>No record is matched !!!</h1>



        </c:if>



    </body>
</html>
