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
    这是学生的ownInfo页面
    ${student.id}
    <a href="/student/updPass.jsp">${student.password}</a>
    <c:forEach items="${student.punishes}" var="punish" varStatus="p">
    <p>${punish.sign}</p>
    <p>${punish.type}</p>
    <p>${punish.comments}</p>
    <p>${punish.createtime}</p>
    </c:forEach>
    <c:forEach items="${student.feesList}" var="fees" varStatus="f">
        <p>${fees.years}</p>
        <p>${fees.fee}</p>
        <p>${fees.paystatus}</p>
        <p>${fees.paytime}</p>
    </c:forEach>
</div>
</body>
</html>
