<%-- 
    Document   : view_cart
    Created on : Jun 16, 2020, 11:43:40 AM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <style>

            a:active, a:visited {
                color: blue;
            }
        </style>
    </head>
    <body>

        <h1>View Your Cart </h1><br/>

        <c:set var="cart" value="${sessionScope.CART.items}"/>
        <c:set var="discountPrice" value="${requestScope.DISCOUNT_COST}"/>
        <c:set var="errorDiscount" value="${requestScope.DISCOUNT_ERROR}"/>


        <c:url var="checkOutLink" value="checkOut"></c:url>


        <c:url var="initCart" value="initCart">
        </c:url>

        <c:if test="${empty discountPrice}">
            <c:forEach var="entry" items="${cart}" varStatus="counter">

                <c:set var="totalPriceOrder" value="${totalPriceOrder + entry.value*entry.key.price}"/>
            </c:forEach>
        </c:if>
        <c:if test="${not empty discountPrice}">
            <c:forEach var="entry" items="${cart}" varStatus="counter">

                <c:set var="totalPriceOrder" value="${discountPrice}"/>
            </c:forEach>
        </c:if>

        <c:if test="${not empty cart}">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Tour Name</th>
                        <th>Amount</th> 
                        <th>Price</th>
                        <th>Total Price</th>
                        <th>Remove Tour </th>
                        <th>Update Amount Tour </th>

                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="entry" items="${cart}" varStatus="counter">
                    <form action="updateItemInCart">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${entry.key.tourName}
                            </td>
                            <td>

                                <input type="number" name="quantityItem" value="${entry.value}" step="1"/>


                            </td>
                            <td>
                                ${entry.key.price}
                            </td>
                            <td>
                                <c:set var="totalPrice" value="${entry.value*entry.key.price}" />
                                ${totalPrice}
                            </td>
                            <td>
                                <c:url var="removeLink" value="removeItemCart">
                                    <c:param name="tourID" value="${entry.key.tourID}"/>
                                </c:url>
                                <a href="${removeLink}">Remove</a>

                            </td>
                            <td>
                                <input type="hidden" value="${entry.key.tourID}" name="itemID" />
                                <input style="width: 100%" type="submit" value="Update" name="btAction"/>
                            </td>

                        </tr>
                    </form>




                </c:forEach>







            </tbody>

        </table><br/>
        <a href="${initCart}">Add More Cart</a> <br/><br/><br/>

        <form action="getDiscount" method="POST">
            <label for="discountCode">Discount code :</label>
            <input type="text" id="discountCode" name="discountCode" value="${param.discountCode}">
            <c:if test="${not empty errorDiscount.invalidCode}">
                <td> <font color="red">
                    ${errorDiscount.invalidCode}
                    </font></td>
                </c:if>
                <c:if test="${not empty errorDiscount.codeIsExpired}">
                <td> <font color="red">
                    ${errorDiscount.codeIsExpired}
                    </font></td>
                </c:if>
                <c:if test="${not empty errorDiscount.codeUsed}">
                <td> <font color="red">
                    ${errorDiscount.codeUsed}
                    </font></td>
                </c:if>
            <input type="hidden"  name="userID" value="${sessionScope.MEMBER.userID}">
            <input type="hidden"  name="totalPrice" value="${totalPriceOrder}">
            <input type="submit" value="Get discount"/>
        </form> <br/>



        <h3><font color="red">Total Price : ${totalPriceOrder} $</font></h3>

        
        <form id="formCheckOut" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_cart">
            <input type="hidden" name="return" value="${initParam['returnurl']}">
            <input type="hidden" name="upload" value="1">
            <input type="hidden" name="business" value="sb-42qmw2255498@business.example.com">

            <input type="hidden" name="no_shipping" value="0">
            <input type="hidden" name="no_note" value="1">
            <input type="hidden" name="currency_code" value="USD">
            <input type="hidden" name="lc" value="AU">
            <input type="hidden" name="bn" value="PP-BuyNowBF">
            <input type="image" src="https://www.paypal.com/en_AU/i/btn/btn_buynow_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online.">
            <img alt="" border="0" src="https://www.paypal.com/en_AU/i/scr/pixel.gif" width="1" height="1">


        </form>





    </c:if>
    <c:if test="${empty cart}">
        <h1>Your cart is not existed !!!</h1> <br/> <br/>

        <a href="${initCart}">Add More Cart</a> 


    </c:if>

    <script>
        window.onload = function () {
            getItemToCheckOut();

        }
        function getItemToCheckOut() {
            var form = document.getElementById("formCheckOut");
        <c:forEach var="entry" items="${cart}" varStatus="counter">
            var hiddenFieldName = document.createElement("input");
            hiddenFieldName.setAttribute("type", "hidden");
            hiddenFieldName.setAttribute("name", "item_name_${counter.count}");
            hiddenFieldName.setAttribute("value", "${entry.key.tourName}");
            form.appendChild(hiddenFieldName);
            var hiddenFieldAmount = document.createElement("input");
            hiddenFieldAmount.setAttribute("type", "hidden");
            hiddenFieldAmount.setAttribute("name", "amount_${counter.count}");
            hiddenFieldAmount.setAttribute("value", "${entry.value*entry.key.price}");
            form.appendChild(hiddenFieldAmount);
        </c:forEach>

        <c:if test="${not empty discountPrice}">
            var hiddenFieldTotalAmount = document.createElement("input");
            hiddenFieldTotalAmount.setAttribute("type", "hidden");
            hiddenFieldTotalAmount.setAttribute("name", "amount");
            hiddenFieldTotalAmount.setAttribute("value", "${discountPrice}");
            form.appendChild(hiddenFieldTotalAmount);


        </c:if>


        }


    </script>



</body>
</html>
