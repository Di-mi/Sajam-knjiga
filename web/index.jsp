<%-- 
    Document   : index
    Created on : Nov 10, 2015, 9:36:54 PM
    Author     : Dimi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login </title>
    </head>
    <body>
        <h1>Login</h1>
        <span style="color:red"> ${poruka}</span>
        <br/>
        <form action="loginServlet" method="POST">
            User: 
            <input type="text" name="user" value="${user.user}"/><br/>
            Pass:
            <input type="password" name="pass" value=""/><br/>
            <input type="submit" value="login"/>
        </form>
    </body>
</html>
