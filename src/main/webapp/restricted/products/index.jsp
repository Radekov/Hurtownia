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
        <title>Produkty</title>
    </head>
    <body>
        <a href="../../index.jsp">Główne</a>
        <c:catch var="exception"><h1>Zalogowany jako: ${sessionScope.login}</h1></c:catch>
        <c:if test="${sessionScope.login != null}"><%--ZMIENIĆ--%>
            <form action="showAllProducts">
                Pokazanie wszystkich:<input type="text" name="nazwa" title="By pokazać wszystke zostaw puste"/>
                <input type="submit" value="Pokaż wsio"/>
            </form>
            <form action="addProduct">
                Dodanie nowego produktu:
                Nazwa:<input type="text" name="nazwa" >
                Kategoria:<input type="text" name="kategoria"/>
                Ilość:<input type="number" name="ilosc"/>
                Cena:<input type="text" name="cena"/>
                <input type="submit" value="Dodaj to"/>
            </form>
        </c:if>

    </body>
</html>
