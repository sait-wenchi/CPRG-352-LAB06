<%-- 
    Document   : register
    Created on : 16-Oct-2021, 9:40:58 PM
    Author     : wenchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form action="ShoppingList" method="post">
            <label>Username:</label> <input type="text" name="username" value="">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name">
        </form>
        <p>${message}</p>
    </body>
</html>
