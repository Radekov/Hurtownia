<%-- 
    Document   : show
    Created on : 2016-05-16, 18:34:56
    Author     : r
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="pl.pawww.hurt.jpa.Orders" %>
<%@page import="pl.pawww.hurt.jpa.OrdersFacade" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zamówienia pokazator</title>
    </head>
    <body>
        <a href="index.jsp">Główne</a>
        <c:catch var="exception"><h1>${sessionScope.user.login}</h1></c:catch>
        <c:if test="${sessionScope.user != null}">
            <table>
                <thead>
                    <tr>
                        <td>ID Zamówienia</td>
                        <td>Nazwa sklepu</td>
                        <td>Produkty</td>
                        <td>Data zamówienia</td>
                        <td>Data realizacji</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${requestScope.orders}">

                        <tr>
                            <td>${order.id}</td>
                            <td>${order.idShops.sklep}</td><%--Czy zadzaiała--%>
                            <td>
                                <table>
                                    <thead>
                                        <tr>
                                            <td>Nazwa</td>
                                            <td>Ilosc</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${order.ordersProdutCollection}">
                                            <tr>
                                                <td>${product.idProduct.nazwa}</td>
                                                <td>
                                                    ${product.liczbaSztuk}
                                                    <c:if test="${order.dateEnd == null}">/${product.idProduct.liczbaSztuk}</c:if>
                                                    </td>
                                                </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                            <td>${order.dateStart}</td>
                            <td>
                                <c:if test="${order.dateEnd != null}">
                                    ${order.dateEnd}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${order.dateEnd == null}">
                                    <form action="realizujZamowienie">
                                        <input type="number" value="${order.id}" name="id" readonly hidden/>
                                        <input type="submit" value="Realizuj"/><%--Dać kolejne waruki czy z listy produktów są wszystkie dostępne--%>
                                    </form>
                                </c:if>
                            </td>
                            <td><c:if test="${order.dateEnd == null}">
                                    <form action="modyfikujZamowienie">
                                        <input type="number" value="${order.id}" name="id" readonly hidden/>
                                        <input type="submit" value="Modyfikuj"/><%--Lub jakoś inaczej obsłużyć modyfikację przez AJAX--%>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
