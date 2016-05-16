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
    <c:catch var="exception"><h1>Hello ${sessionScope.login}</h1></c:catch>
    <c:if test="${not empty exception}">
        <form action="login" method="POST">
            Login:<input type="text" name="login"/>
            Hasło:<input type="password" name="pass"/>
            <input type="submit" value="Zaloguj"/>
        </form>
    </c:if>
    <c:if test="${empty exception}">
        <form action="LogoutServlet">
            <input type="submit" value="Wyloguj"/>
        </form>
        <form action="products/index.jsp">
            <input type="submit" value="Przejrzyj rzeczy"/>
        </form>
        <form action="users/index.jsp">
            <input type="submit" value="Przeglądaj użytkowników"/>
        </form>
        <form action="shops/index.jsp">
            <input type="submit" value="Przeglądaj sklepy"/>
        </form>
        <form action="orders/index.jsp">
            <input type="submit" value="Przeglądaj zamówienia"/>
        </form>
    </c:if>
</body>
</html>
