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
        <title>Admin Page</title>

        <style>

            a:active, a:visited {
                color: blue;
            }
            .center {
                display: flex;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <c:set var="admin" value="${sessionScope.ADMIN.fullName}"/>

        <c:url var="logOutLink" value="logOut">
        </c:url>


        <c:if test="${empty admin}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>



        <a href="${logOutLink}"><font color="blue">Log out </font></a><br/><br/>

        <c:if test="${empty admin}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>

        <h1><font color="red">Welcome ${admin}!</font></h1>


        <c:set var="seachResult" value="${requestScope.SEARCH_RESULT}" />
        <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}" />

        <h1>Search Tour </h1>

        <form action="search">
            <label for="txtFromPlace">From Place:</label>
            <input type="text" name="txtFromPlace" value="${param.txtFromPlace}">
            <label for="txtToPlace">To Place:</label>
            <input type="text"  name="txtToPlace"  value="${param.txtToPlace}">
            <label for="txtFromDate">From Date:</label>
            <input type="date" id="txtFromDate"  name="txtFromDate" required  value="${param.txtFromDate}">
            <label for="txtToDate">To Date:</label>
            <input type="date"  name="txtToDate" required value="${param.txtToDate}">
            <label for="price">Price</label>
            <input type="text"  name="price" value="${param.price}">
            <input type="hidden" name="urlForward" value="Search_Admin"/>
            <input type="submit" value="Search" />
        </form> <br/><br/>

        <a href="create_tour.jsp"><font color="blue">Create Tour </font></a><br/><br/>

        <c:if test="${not empty seachResult}">
            <div class="center" style="width: 100%">
                <table border="1" style="width: 100%">
                    <thead>
                        <tr>

                          
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
                               
                                <td style="text-align:center">
                                    ${dto.tourName}


                                </td>
                                <td style="text-align:center">
                                    ${dto.fromDate}
                                </td>
                                <td style="text-align:center">
                                    ${dto.toDate}

                                </td>

                                <td style="text-align:center">
                                    ${dto.price} $
                                </td>

                                <td style="text-align:center">
                                    ${dto.quota}
                                </td>
                                <td width="125px" valign="top" style="text-align:center">
                                    <c:if test="${not empty dto.imageName}">
                                        <img src="images/${dto.imageName}" style="display:block; width:300px; height:300px;">
                                    </c:if>
                                    <c:if test="${empty dto.imageName}">
                                        <img src="images/notFound.png" style="display:block; width:300px; height:300px;">
                                    </c:if>
                                </td>
                                <td style="text-align:center">
                                    ${dto.fromPlace}
                                </td>
                                <td style="text-align:center">
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

        <br/><br/>

        <div class="center">
            <c:forEach begin="1" end="${totalPage}" var="i">
                <c:url var="currentPageLink" value="search">

                    <c:param name="page" value="${i}" />
                    <c:param name="txtFromPlace" value="${param.txtFromPlace}" />
                    <c:param name="txtToPlace" value="${param.txtToPlace}" />
                    <c:param name="txtFromDate" value="${param.txtFromDate}" />
                    <c:param  name="txtToDate" value="${param.txtToDate}" />
                    <c:param name="price" value="${param.price}" />
                    <c:param name="urlForward" value="Search_Admin" />

                </c:url>
                <a id="${i}"  style="margin: 5px" href="${currentPageLink}">${i}</a>
            </c:forEach>

        </div>





        <script type="text/javascript">
            <c:if test="${empty param.txtFromDate}">
            var date = new Date();
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            var today = year + "-" + month + "-" + day;

            document.getElementById('txtFromDate').value = today;
            </c:if>


        </script>

    </body>
</html>
