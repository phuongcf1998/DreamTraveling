<%-- 
    Document   : create_tour
    Created on : Jun 15, 2020, 6:21:15 PM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new question</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $(".datepicker").datepicker();
            });
        </script>

        <style>
            .flex-container {
                display: flex;
                height: 350px;
                justify-content: center;
                align-items: center;
            }
            a:active, a:visited {
                color: blue;
            }
        </style>
    </head>
    <body>
        <c:set var="errors" value="${requestScope.CREATE_TOUR_ERR}"/>
        <c:set var="admin" value="${sessionScope.ADMIN.fullName}"/>

        <c:if test="${empty admin}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <c:if test="${empty admin}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>


        <c:if test="${empty errors}">
            <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Create new tour</h1> <br><br>
            <div class="flex-container" >


                <form  action="createTour" method="POST" enctype="multipart/form-data">
                    <table>
                        <tbody>

                            <tr>
                                <td>Tour ID : </td>
                                <td><input type="text" name="txtTourID" value="${param.txtTourID}" /></td>
                            </tr>

                            <tr>
                                <td>Tour Name : </td>
                                <td><input type="text" name="txtTourName" value="${param.txtTourName}" /></td>
                            </tr>

                            <tr>
                                <td>From Date : </td>
                                <td><input type="date" name="txtFromDate"  value="${param.txtFromDate}" /></td>
                            </tr>
                            <tr>
                                <td>To Date</td>
                                <td><input type="date" name="txtToDate"  value="${param.txtToDate}" /></td>
                            </tr>

                            <tr>
                                <td>From Place : </td>
                                <td><input type="text" name="txtFromPlace" value="${param.txtFromPlace}" /></td>
                            </tr>

                            <tr>
                                <td>To Place : </td>
                                <td><input type="text" name="txtToPlace" value="${param.txtToPlace}" /></td>
                            </tr>

                            <tr>
                                <td>Price</td>
                                <td><input type="number" name="price" step="0.1" value="${param.price}" /></td>
                            </tr>
                            <tr>
                                <td>Quota</td>
                                <td><input type="number" name="quota" value="${param.quota}" /></td>
                            </tr>
                            <tr>
                                <td>Image</td>
                                <td><input type="file" name="fileImage" /></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><input type="submit" value="Create New Tour" name="btAction"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <a href="admin.jsp">Go back </a> </td>
                            </tr>

                        </tbody>
                    </table>
                </form>
            </div>


        </c:if>

        <c:if test="${not empty errors}">
            <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Create new tour</h1> <br><br>
            <div class="flex-container" >


                <form  action="createTour" method="POST" enctype="multipart/form-data">
                    <table>
                        <tbody>

                            <tr>
                                <td>Tour ID : </td>
                                <td><input type="text" name="txtTourID" value="${param.txtTourID}" /></td>
                                    <c:if test="${not empty errors.tourIdIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.tourIdIsEmpty}
                                        </font></td>
                                    </c:if>
                                    <c:if test="${not empty errors.tourIdExceedLength}">
                                    <td> <font color="red">
                                        ${errors.tourIdExceedLength}
                                        </font></td>
                                    </c:if>
                            </tr>

                            <tr>
                                <td>Tour Name : </td>
                                <td><input type="text" name="txtTourName" value="${param.txtTourName}" /></td>
                                    <c:if test="${not empty errors.tourNameIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.tourNameIsEmpty}
                                        </font></td>
                                    </c:if>
                            </tr>

                            <tr>
                                <td>From Date : </td>
                                <td><input type="date" name="txtFromDate"  value="${param.txtFromDate}" /></td>
                                    <c:if test="${not empty errors.fromDateIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.fromDateIsEmpty}
                                        </font></td>
                                    </c:if>
                            </tr>
                            <tr>
                                <td>To Date</td>
                                <td><input type="date" name="txtToDate"  value="${param.txtToDate}" /></td>
                                    <c:if test="${not empty errors.toDateIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.toDateIsEmpty}
                                        </font></td>
                                    </c:if>
                            </tr>

                            <tr>
                                <td>From Place : </td>
                                <td><input type="text" name="txtFromPlace" value="${param.txtFromPlace}" /></td>
                                    <c:if test="${not empty errors.fromPlaceIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.fromPlaceIsEmpty}
                                        </font></td>
                                    </c:if>
                            </tr>

                            <tr>
                                <td>To Place : </td>
                                <td><input type="text" name="txtToPlace" value="${param.txtToPlace}" /></td>
                                    <c:if test="${not empty errors.toPlaceIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.toPlaceIsEmpty}
                                        </font></td>
                                    </c:if>
                            </tr>

                            <tr>
                                <td>Price</td>
                                <td><input type="number" step="0.1" name="price" value="${param.price}" /></td>
                                    <c:if test="${not empty errors.priceIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.priceIsEmpty}
                                        </font></td>
                                    </c:if>
                                    <c:if test="${not empty errors.priceInvalid}">
                                    <td> <font color="red">
                                        ${errors.priceInvalid}
                                        </font></td>
                                    </c:if>
                            </tr>
                            <tr>
                                <td>Quota</td>
                                <td><input type="number" name="quota" value="${param.quota}" /></td>
                                    <c:if test="${not empty errors.quotaIsEmpty}">
                                    <td> <font color="red">
                                        ${errors.quotaIsEmpty}
                                        </font></td>
                                    </c:if>
                                    <c:if test="${not empty errors.quotaInvalid}">
                                    <td> <font color="red">
                                        ${errors.quotaInvalid}
                                        </font></td>
                                    </c:if>
                            </tr>
                            <tr>
                                <td>Image</td>
                                <td><input type="file" name="fileImage" /></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><input type="submit" value="Create New Tour" name="btAction"></td>
                                    <c:if test="${not empty errors.tourIdIsExisted}">
                            <font color="red">
                            ${errors.tourIdIsExisted}
                            </font><br/><br/>
                        </c:if>
                        <c:if test="${not empty errors.invalidDate}">
                            <font color="red">
                            ${errors.invalidDate}
                            </font><br/><br/>
                        </c:if>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a href="admin.jsp">Go back </a> </td>
                        </tr>

                        </tbody>
                    </table>
                </form>
            </div>
        </c:if>
    </body>


</html>

