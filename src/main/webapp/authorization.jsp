<%--
  Created by IntelliJ IDEA.
  User: promoscow
  Date: 29.07.17
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'login' ? 'Log in' : 'Sign Up'}</h2>
    <hr/>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <c:set var="action" value="${param.action}"/>
    <c:if test="${action == 'login'}">
        <form method="post" action="users?action=login">
            <dl>
                <dt>Username: </dt>
                <dd><input type="text" value="${user.name}" name="name"/></dd>
            </dl>
            <dl>
                <dt>Password: </dt>
                <dd><input type="password" value="${user.password}" name="password"/></dd>
            </dl>
            <button type="submit">Log In</button>
        </form>
    </c:if>
    <c:if test="${action == 'signup'}">
        <form method="post" action="users?action=signup">
            <dl>
                <dt>Username: </dt>
                <dd><input type="text" value="${user.name}" name="name"/></dd>
            </dl>
            <dl>
                <dt>Password: </dt>
                <dd><input type="password" value="${user.password}" name="password"/></dd>
            </dl>
            <dl>
                <dt>e-mail: </dt>
                <dd><input type="email" value="${user.email}" name="email"/></dd>
            </dl>
            <button type="submit">Sign Up</button>
        </form>
    </c:if>
    <a href="index.html">Back</a>
</section>
</body>
</html>
