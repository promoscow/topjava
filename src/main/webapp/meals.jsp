<%--
  Created by IntelliJ IDEA.
  User: promoscow
  Date: 20.07.17
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<c:forEach items="${meals}" var="meal">
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr>
        <td>
                <%--${fn:formatDateTime(meal.dateTime)}--%>
        </td>
        <td>
            ${meal.description}
        </td>
        <td>
            ${meal.calories}
        </td>
    </tr>
</c:forEach>
</body>
</html>
