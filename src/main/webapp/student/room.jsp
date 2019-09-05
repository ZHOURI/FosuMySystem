<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/16
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${room.dormId}
${room.members}
<c:forEach items="${room.roomPunishList}" var="punish" varStatus="p">
    <p>${punish.type}</p>
    <p>${punish.comments}</p>
    <p><fmt:formatDate value="${punish.createtime}" pattern="yyyy-MM-dd"/></p>
</c:forEach>
</body>
</html>
