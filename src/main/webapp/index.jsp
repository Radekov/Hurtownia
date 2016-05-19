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
        <title>JSP Page</title>
    </head>
    <body>
    <c:catch var="exception"><h1>Hello ${sessionScope.user.login}</h1></c:catch>
    <c:if test="${sessionScope.user == null}">
        <form action="loginServlet" method="POST">
            Login:<input type="text" name="login"/>
            Hasło:<input type="password" name="password"/>
            Zapamiętaj<input type="checkbox" name="zapamietaj"/>
            <input type="submit" value="Zaloguj"/>
        </form>
    </c:if>
    <c:if test="${sessionScope.user != null}"><%--ZMIENIĆ--%>
        <form action="logoutServlet">
            <input type="submit" value="Wyloguj"/>
        </form>
        <form action="restricted/products/index.jsp">
            <input type="submit" value="Przejrzyj rzeczy"/>
        </form>
        <form action="restricted/users/index.jsp">
            <input type="submit" value="Przeglądaj użytkowników"/>
        </form>
        <form action="restricted/shops/index.jsp">
            <input type="submit" value="Przeglądaj sklepy"/>
        </form>
        <form action="restricted/orders/sendProductsToOrders">
            <input type="submit" value="Przeglądaj zamówienia"/>
        </form>
    </c:if>
</body>
</html>
