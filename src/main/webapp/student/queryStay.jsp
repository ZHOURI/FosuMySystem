<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/16
  Time: 22:54
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
这是留宿记录查询页面
总共有${pageResult.totalCount}条记录，共计${pageResult.totalPage}页
<c:forEach items="${pageResult.items}" var="stay" varStatus="s">
    <p>${s.count}</p>
    <p>${stay.stuId}</p>
    <p>${stay.reason}</p>
    <p><fmt:formatDate value="${stay.startTime}" pattern="yyyy-MM-dd"/></p>
    <p><fmt:formatDate value="${stay.endTime}" pattern="yyyy-MM-dd"/></p>
    <p>${stay.parent}</p>
    <p>${stay.teacher}</p>
    <p>${stay.status}</p>
    <hr>
</c:forEach>
<a href="/student/stayForm.jsp">提交留宿申请</a>
${msg}
<%--<form href="stu/queryStay.do">--%>
    <%--<input type="text" name="">--%>
<%--</form>--%>
</body>
</html>
