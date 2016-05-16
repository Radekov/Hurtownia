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
        <title>Sklepy pokazator</title>
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
                        <td>Sklep</td>
                        <td>Adres</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="shop" items="${requestScope.shops}">
                    <form action="modyfikujProdukt">
                        <tr>
                            <td>${shop.sklep}</td>
                            <td>${shop.adres}</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
