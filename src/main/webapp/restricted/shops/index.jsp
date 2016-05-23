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
        <title>Sklepy</title>
    </head>
    <body>
        <a href="../../index.jsp">Główne</a>
        <c:catch var="exception"><h1>${sessionScope.login}</h1></c:catch>
        <c:if test="${sessionScope.login != null}"><%--ZMIENIĆ--%>
            <form action="showAllShops">
                Pokazanie wszystkich:<input type="text" name="sklep" title="By pokazać wszystke zostaw puste"/>
                <input type="submit" value="Pokaż wsio"/>
            </form>
            <form action="addShop">
                Dodanie nowego sklepu:
                Sklep:<input type="text" name="sklep" >
                Adres:<input type="text" name="adres"/>
                <input type="submit" value="Dodaj to"/>
            </form>
        </c:if>

    </body>
</html>
