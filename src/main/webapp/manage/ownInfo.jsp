<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/16
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    这是宿管的ownInfo页面
    ${manager.id}
    <a href="/manage/updPass.jsp">${manager.password}</a>
    ${manager.salaryList.size()}
    <c:forEach items="${manager.salaryList}" var="salary" varStatus="s" begin="0" end="5" step="1">
        <p>${s.count}</p>
        <p>${salary.years}</p>
        <p>${salary.months}</p>
        <p>${salary.basepay}</p>
        <p>${salary.deserved}</p>
    </c:forEach>
    <a href="#">查看更多工资信息</a>
</div>
</body>
</html>
