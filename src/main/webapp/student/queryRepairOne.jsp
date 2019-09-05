<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/23
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<button onclick="fresh()">刷新一下</button>
<input type="button" value="后退" onclick="window.location.href='/stu/queryRepair.do?currentPage=${currentPage}'" />
<p>${repair.reporter}</p>
<p>${repair.roomId}</p>
<p>${repair.reason}</p>
<p><fmt:formatDate value="${repair.reporttime}" pattern="yyyy-MM-dd"/></p>
<%--<c:if test="${repair.images} neq null">--%>
<c:set var="var1" value="${repair.images}"/>
<c:forEach var="tdv" items="${fn:split(var1,',')}">
<c:if test="${not empty tdv}">
<img src="${fn:replace(fn:replace(tdv,'[',''),']','')}" width="200px" height="200px"/>
</c:if>
</c:forEach>
<%--</c:if>--%>
<%--<img src="${repair.images}"/>--%>
<p>${repair.remarks}</p>
<p>${repair.status}</p>
<hr>
</body>
<script>
        function fresh(){
                         // 框架内页面刷新：可实现局部刷新与整个页面重定向
                         self.location.reload();  //刷新框架内页面
                       // window.parent.location.href='http://koushuling.top'; //页面重定向
                    }
</script>
</html>
