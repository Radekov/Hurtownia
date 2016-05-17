<%-- 
    Document   : index
    Created on : 2016-05-16, 18:18:09
    Author     : r
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zamówienia</title>
    </head>
    <body>
        <c:catch var="exception"><h1>${sessionScope.sign}</h1></c:catch>
        <c:if test="${not empty exception}">
            <%--POWRÓT--%>
        </c:if>
        <c:if test="${sessionScope.sign == null}"><%--ZMIENIĆ--%>
            <form action="showAllOrders">
                <input type="submit" value="Pokaż wszystkie"/>
            </form>
            <form action="showAllOrdersUnrealized">
                <input type="submit" value="Pokaż wszystkie niezrealizowane"/>
            </form>
            <form action="showAllOrdersRealized">
                <input type="submit" value="Pokaż wszystkie zrealizowane"/>
            </form>

            <form action="addOrder"><%--Tu dużo zmienić--%>
                Dodanie nowego zamówienia:
                Sklep:
                <select name="sklep">
                    <c:forEach var="shop" items="${requestScope.shops}">
                        <option value="${shop.sklep}">${shop.sklep}</option>
                    </c:forEach>
                </select>
                Produkty:
                <c:forEach var="produkt" items="${requestScope.produkty}">
                    ${produkt.nazwa}<input type="checkbox" name="produkt" value="${produkt.nazwa}"/>
                    Ilość:<input type="number" name="${produkt.nazwa}ilosc"/><%--AJAX pojawia się po kliknięciu checkboxa--%>
                </c:forEach>
                <input type="submit" value="Dodaj to"/>
            </form>
        </c:if>

    </body>
</html>
