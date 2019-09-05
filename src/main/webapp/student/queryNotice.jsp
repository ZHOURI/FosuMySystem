<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/20
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是公告页面
${msg}
<c:forEach items="${noticeList}" var="notice" varStatus="n">
    <p>${n.count}</p>
    <p>${notice.content}</p>
    <p><fmt:formatDate value="${notice.noticeTime}" pattern="yyyy-mm-dd"></fmt:formatDate> </p>
</c:forEach>
</body>
</html>
