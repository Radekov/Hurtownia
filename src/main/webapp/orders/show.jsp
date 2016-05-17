<%-- 
    Document   : show
    Created on : 2016-05-16, 18:34:56
    Author     : r
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="pl.pawww.hurt.jpa.Users" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zamówienia pokazator</title>
    </head>
    <body>
        <a href="index.jsp">POWRÓT</a>
        <c:catch var="exception"><h1>${sessionScope.sign}</h1></c:catch>
        <c:if test="${sessionScope.sign == null}">
            <%--POWRÓT--%>
        </c:if>
        <c:if test="${sessionScope.sign == null}">
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
                    <form action="realizujZamowienie">
                        <tr>
                            <td><input type="number" value="${order.id}" name="id" readonly/></td>
                            <td>${order.idShops.sklep}</td><%--Czy zadzaiała--%>
                            <td><%--pętla dla produktów--%></td>
                            <td>${order.dateStart}</td><><%--Czy wyświetli prawidłowo--%>
                            <td>
                                <c:if test="${sessionScope.dateend != null}">
                                    ${sessionScope.dateend}<%--Czy wyświetli prawidłowo--%>
                                </c:if>
                                <c:if test="${sessionScope.dateend == null}">
                                    <input type="submit" value="Realizuj"/><%--Dać kolejne waruki czy z listy produktów są wszystkie dostępne--%>
                                </c:if>
                            </td>
                            <td><c:if test="${sessionScope.dateend == null}">
                                    <form action="modyfikujZamowienie">
                                        <input type="submit" value="Modyfikuj"/><%--Lub jakoś inaczej obsłużyć modyfikację--%>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
