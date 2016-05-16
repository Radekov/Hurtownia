<%-- 
    Document   : index
    Created on : 2016-05-16, 18:18:09
    Author     : r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="zaloguj" method="POST">
            Logowanie:<input name="login" type="text"/>
            <input name="pass" type="password"/>
            <input type="submit" value="Zaloguj"/>
        </form>
        <form action="showAllUsers">
            Pokazanie wszystkich:<input type="text" name="login" title="By pokazaÄ wszystkich zostaw puste"/>
            <input type="checkbox" name="all" title="PokaĹź zawierajÄce"/>
            <input type="submit" value="PokaĹź wsio"/>
        </form>
        <form action="addAdmin" method="POST">
            Dodanie nowego administratora:<input type="text" name="login" title="Spowoduj login" required/>
            <input type="password" name="password" title="HasĹo" required/>
            <input type="submit" value="Dodaj go"/>
        </form>
        <%--
        <c:catch var="exception"><h1>Hello ${sessionScope.login}</h1></c:catch>
        <c:if test="${not empty exception}">
            <form action="login">
                <input type="submit" value="Zaloguj"/>
            </form>
        </c:if>
        <c:if test="${empty exception}">
            <form action="LogoutServlet">
                <input type="submit" value="Wyloguj"/>
            </form>
            <form action="Stuff/index.html">
                <input type="submit" value="Przejrzyj rzeczy"/>
            </form>
            <form action="Users/index.html">
                <input type="submit" value="PrzeglĂÂdaj uÄšĹşytkownikÄĹw"/>
            </form>
        </c:if>
        --%>
    </body>
</html>
