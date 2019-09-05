<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/18
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>佛大宿舍管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../js/jquery-3.2.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<%--<c:forEach items="${studentList}" var="student" varStatus="r">--%>
<%--<p>${r.count}</p>--%>
<%--<p>${student.reason}</p>--%>
<%--<p><fmt:formatDate value="${student.room_ID}" pattern="yyyy-MM-dd"/></p>--%>
<%--&lt;%&ndash;<c:if test="${student.images} neq null">&ndash;%&gt;--%>
<%--<c:set var="var1" value="${student.images}"/>--%>
<%--<c:forEach var="tdv" items="${fn:split(var1,',')}">--%>
<%--<c:if test="${not empty tdv}">--%>
<%--<img src="${fn:replace(fn:replace(tdv,'[',''),']','')}" width="200px" height="200px"/>--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;<img src="${student.images}"/>&ndash;%&gt;--%>
<%--<p>${student.remarks}</p>--%>
<%--<p>${student.status}</p>--%>
<%--<hr>--%>
<%--</c:forEach>--%>
<div class="container">
    <h3 style="text-align: center">学生详情页面</h3>
    <div style="float:left">
        <form class="form-inline" style="margin: 5px" action="${pageContext.request.contextPath}/man/queryStu.do?" method="post">
            <div class="form-group">
                <label for="exampleInputName2">学号</label>
                <input type="text" class="form-control" id="exampleInputName2" name="id" value="${map.id[0]}" >
            </div>
            <div class="form-group" style="margin-left: 10px">
                <label for="name">名字</label>
                <input type="text" class="form-control" id="name" name="stu_name" value="${map.stu_name[0]}" >
            </div>
            <div class="form-group" style="margin-left: 10px">
                <label for="roomId">宿舍号</label>
                <input type="text" class="form-control" id="roomId" name="room_ID" value="${map.room_ID[0]}" >
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/man/queryStu.do?currentPage=${pageResult.currentPage}" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>学号</th>
                <th>名字</th>
                <th>宿舍号</th>
                <th>床位号</th>
                <th>入住时间</th>
                <th>班级</th>
                <th>学院</th>
                <th>类型</th>
                <th>联系电话</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageResult.items}" var="student" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${student.id}</td>
                    <td>${student.stuName}</td>
                    <td>${student.roomId}</td>
                    <td>${student.bed}</td>
                    <td><fmt:formatDate value="${student.checkin}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
                    <td>${student.className}</td>
                    <td>${student.collegeName}</td>
                    <td>${student.identity}</td>
                    <td>${student.stuPhone}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/man/queryStuInfo?id=${student.id}&currentPage=${pageResult.currentPage}">管理</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <c:if test="${pageResult.items.size() gt 0}">
    <ul class="pagination">
        <c:if test="${pageResult.currentPage<=1}">
        <li class="disabled">
            </c:if>
            <c:if test="${pageResult.currentPage>1}">
        <li>
            </c:if>
            <a href="${pageContext.request.contextPath}/man/queryStu.do?currentPage=${pageResult.currentPage-1}&stu_name=${map.stu_name[0]}&room_ID=${map.room_ID[0]}" onclick="">&laquo;</a></li>
        <c:forEach begin="1" end="${pageResult.totalPage}" step="1" varStatus="s" var="i">
            <c:if test="${pageResult.currentPage==i}">
                <li class="active"><a href="${pageContext.request.contextPath}/man/queryStu.do?currentPage=${i}&stu_name=${map.stu_name[0]}&room_ID=${map.room_ID[0]}">${i}</a></li>
            </c:if>
            <c:if test="${pageResult.currentPage!=i}">
                <li><a href="${pageContext.request.contextPath}/man/queryStu.do?currentPage=${i}&stu_name=${map.stu_name[0]}&room_ID=${map.room_ID[0]}">${i}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${pageResult.currentPage>=pageResult.totalPage}">
        <li class="disabled">
            </c:if>
            <c:if test="${pageResult.currentPage<pageResult.totalPage}">
        <li>
            </c:if>
            <a href="${pageContext.request.contextPath}/man/queryStu.do?currentPage=${pageResult.currentPage+1}&stu_name=${map.stu_name[0]}&room_ID=${map.room_ID[0]}">&raquo;</a></li>
        <li style="font-size: 25px;margin-left: 20px">总共有${pageResult.totalCount}条记录,有${pageResult.totalPage}页</li>
    </ul>
    </c:if>
    <h3 style="color: blue;text-align: center">${msg}</h3>
</div>
</body>
</html>
