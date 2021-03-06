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
    <c:if test="${sessionScope.login == null}">
        <a href="login.html">Zaloguj</a>
    </c:if>
    <c:if test="${sessionScope.login != null}"><%--ZMIENIĆ--%>
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
        <form method="post" action="validateXML" enctype="multipart/form-data">
            <input  type="file" name="file" multiple/>
            <input type="submit" value="Waliduj XML"/>
        </form>
        <c:if test="${requestScope.poprawne != null && requestScope.niepoprawne != null}">
            <table>
                <thead>
                    <tr>
                        <td>Poprawne</td>
                        <td>Niepoprawne</td>
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
