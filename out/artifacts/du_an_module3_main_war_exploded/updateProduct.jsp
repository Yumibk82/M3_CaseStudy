<%--
  Created by IntelliJ IDEA.
  User: Tuan
  Date: 3/17/2021
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <title>UPDATE PRODUCT INFORMATION</title>
</head>
<body>
    <div align="center">
        <form method="post">
            <table border="1"cellpadding="5">
                <caption>
                    <h2>UPDATE PRODUCT INFORMATION</h2>
                </caption>
                <c:if test="${product!=null}">
                    <input type="hidden"name="productid"value="<c:out value='${product.getProductid()}'/> ">
                </c:if>
                <tr>
                    <th>PRODUCT NAME</th>
                    <td>
                        <input type="text"name="productname"size="45"
                                value="<c:out value='${product.getProductname()}'/> ">
                    </td>
                </tr>
                <tr>
                    <th>PRODUCT QUANTITY</th>
                    <td>
                        <input type="text"name="quantity"size="45"
                               value="${product.quantity}">
                    </td>
                </tr>
                <tr>
                    <th>PRODUCT PRICE</th>
                    <td>
                        <input type="text"name="price"size="45"
                               value="${product.categoryid}">
                    </td>
                </tr>
                <tr>
                    <th>PRODUCT DISCOUNT</th>
                    <td>
                        <input type="text"name="discount"size="45"
                               value="${product.discount}">
                    </td>
                </tr>
                <tr>
                    <th>PRODUCT CATEGORY ID</th>
                    <td>
                        <input type="text"name="categoryid"size="45"
                               value="${product.categoryid}">
                    </td>
                </tr>
                <tr>
                    <td colspan="2"align="center">
                        <input type="submit"value="SAVE">
                    </td>
                </tr>
            </table>
        </form>

    </div>
</body>
</html>
