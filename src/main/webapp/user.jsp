<%--
  Created by IntelliJ IDEA.
  User: promoscow
  Date: 30.07.17
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Update user</h2>
    <hr/>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users?action=update">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="registered" value="${user.registered}">
        <input type="hidden" name="roles" value="${user.roles}">
        <dl>
            <dt>Username: </dt>
            <dd><input type="text" name="name" value="${user.name}" placeholder="${user.name}"></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd><input type="text" name="password" value="${user.password}" placeholder="${user.password}"></dd>
        </dl>
        <dl>
            <dt>E-mail: </dt>
            <dd><input type="email" name="email" value="${user.email}" placeholder="${user.email}"></dd>
        </dl>
        <dl>
            <dt>Enabled: </dt>
            <dd><input type="checkbox" name="enabled" value="${user.enabled}" checked></dd>
        </dl>
        <dl>
            <dt>Calories per day: </dt>
            <dd><input type="number" name="caloriesPerDay" value="${user.caloriesPerDay}"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
