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
        <title>Produkty pokazator</title>
    </head>
    <body>
        <a href="index.jsp">POWRÓT</a>
        <c:catch var="exception"><h1>Hello ${sessionScope.sign}</h1></c:catch>
        <c:if test="${sessionScope.sign == null}">
            <%--POWRÓT--%>
        </c:if>
        <c:if test="${sessionScope.sign == null}">
            <table>
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Nazwa</td>
                        <td>Kategoria</td>
                        <td>Liczba Sztuk</td>
                        <td>Cena</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.products}">
                        <tr>
                    <form action="modyfikujProdukt">
                        <td><input type="number" value="${product.id}" name="id" readonly/></td>
                        <td>${product.nazwa}</td>
                        <td>${product.kategoria}</td>
                        <td><input type="number" value="${product.liczbaSztuk}" name="liczbaSztuk"/></td>
                        <td><input type="text" value="${product.cena}" name="cena"/></td>
                        <td>
                            <input type="submit" value="Modyfikuj"/>
                        </td>
                    </form>
                    <td>
                        <form action="usunProdukt">
                            <input type="number" name ="id" value="${product.id}" readonly hidden/>
                            <input type="submit" value="Pozbadz sie tego"/>
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
