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
        <title>Administratorzy pokazator</title>
    </head>
    <body>
        <a href="index.jsp">INDEX</a>
    <c:catch var="exception"><h1>${sessionScope.sign}</h1></c:catch>
    <c:if test="${sessionScope.sign == null}">
        <%--POWRÓT--%>
    </c:if>
   <c:if test="${sessionScope.sign == null}"><%--ZMIENIĆ--%>
        <table>
            <thead>
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        login
                    </td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>
                            ${user.id}
                        </td>
                        <td>
                            ${user.login}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
