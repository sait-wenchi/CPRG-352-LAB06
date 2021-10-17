<%-- 
    Document   : shoppingList
    Created on : 16-Oct-2021, 9:41:16 PM
    Author     : wenchi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello, ${username} <a href="ShoppingList?action=logout">Logout</a>
        <h2>List</h2>
        <form action="ShoppingList" method="post">
            <label>Add item:</label>
            <input type="text" name="itemname" value="">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
        <form action="ShoppingList" method="post">
            <c:forEach var="itemname" items="${itemlist}">
                <input type="radio" name="selectname" value=${itemname}><label for=${itemname}>${itemname}</label><br>
            </c:forEach>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>
