<%--
  Created by IntelliJ IDEA.
  User: enum
  Date: 2018/1/23
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
    <h1>Login Page</h1>
    <form action="/shiro/login" method="post">
        UserName <input type="text" name="username"><br><br>
        Password <<input type="password" name="password"><br><br>
        <<input type="submit" name="submit">
    </form>
</body>
</html>
