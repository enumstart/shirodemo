<%--
  Created by IntelliJ IDEA.
  User: enum
  Date: 2018/1/24
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h1>List Page</h1>
    welcome:<shiro:principal></shiro:principal>
    <shiro:hasRole name="admin">
        <a href="admin.jsp">Admin Page</a>
    </shiro:hasRole>
    <shiro:hasRole name="user">
        <a href="user.jsp">User Page</a>
    </shiro:hasRole>
    <a href="/shiro/logout">登出</a>
</body>
</html>
