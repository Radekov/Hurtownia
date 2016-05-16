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
    <c:catch var="exception"><h1>Hello ${sessionScope.login}</h1></c:catch>
    <c:if test="${sessionScope.login == null}">
        <%--POWRÓT--%>
    </c:if>
    <c:if test="${sessionScope.login == null}">
        <table>
            <thead>
                <tr>
                    <td>
                        Nazwa
                    </td>
                    <td>
                        Kategoria
                    </td>
                    <td>
                        Liczba Sztuk
                    </td>
                    <td>
                        Cena
                    </td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <td>
                            ${product.nazwa}
                        </td>
                        <td>
                            ${product.kategoria}
                        </td>
                        <td>
                            ${product.liczbaSztuk}
                        </td>
                        <td>
                            ${product.cena}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
