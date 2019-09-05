<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/21
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是违纪页面
<c:forEach items="${punishList}" var="punish" varStatus="p">
    <p>${punish.stuId}</p>
    <p>${punish.comments}</p>
    <p>${punish.type}</p>
    <fmt:formatDate value="${punish.createtime}" pattern="yyyy-mm-dd"/>
    <p>${punish.sign}</p>
</c:forEach>
</body>
</html>
