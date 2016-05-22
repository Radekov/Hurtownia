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
        <a href="../../index.jsp">Główne</a>
        <c:catch var="exception"><h1>${sessionScope.sign}</h1></c:catch>
        <c:if test="${not empty exception}">
            <%--POWRÓT--%>
        </c:if>
        <c:if test="${sessionScope.user != null}"><%--ZMIENIĆ--%>
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
                Dodanie nowego zamówienia:<br/>
                Sklep:
                <select name="sklep">
                    <c:forEach var="shop" items="${requestScope.shops}">
                        <option value="${shop.sklep}">${shop.sklep}</option>
                    </c:forEach>
                </select><br/>
                Produkty:
                <table>
                    <thead>
                        <tr>
                            <td>Nazwa</td>
                            <td></td>
                            <td>Ilość</td>
                            <td>Zostało</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="produkt" items="${requestScope.produkty}">
                            <tr>
                                <td>${produkt.nazwa}</td>
                                <td><input type="checkbox" name="produkt" value="${produkt.nazwa}"/></td>
                                <td>Ilość:<input type="number" name="${produkt.nazwa}ilosc"/><%--AJAX pojawia się po kliknięciu checkboxa--%></td>
                                <td>${produkt.liczbaSztuk}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input type="submit" value="Dodaj to"/>
            </form>



            <form action="addOrderByXML" method="post" action="validateXML" enctype="multipart/form-data">
                Dodaj zamówienia przez pliki xml
                <input type="file" name="file" multiple/>
                <input type="submit" value="Dodaj je"/>
            </form>
            <c:if test="${requestScope.poprawne != null && requestScope.niepoprawne != null}">
                <table>
                    <thead>
                        <tr>
                            <td>Dodano</td>
                            <td>Niedodano</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:forEach var="file" items="${requestScope.poprawne}">
                                    ${file}<br/>
                                </c:forEach></td>
                            <td><c:forEach var="file" items="${requestScope.niepoprawne}">
                                    ${file}<br/>
                                </c:forEach></td>
                        </tr>
                    </tbody>
                </table>
            </c:if>
        </c:if>

    </body>
</html>
