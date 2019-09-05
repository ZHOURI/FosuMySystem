<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/25
  Time: 20:22
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
    <script src="../js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">查看宿舍信息页面</h3>
    <div style="float:left">
        <form class="form-inline" style="margin: 5px" action="${pageContext.request.contextPath}/man/queryStu.do?" method="post">
            <div class="form-group" style="margin-left: 10px">
                <label for="exampleInputAddress2">宿舍号</label>
                <input type="text" class="form-control" id="exampleInputAddress2" name="room_ID" value="${map.room_ID[0]}" placeholder="">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
    </div>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>宿舍号</th>
                <th>宿舍成员</th>
                <th>宿舍人数</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageResult.items}" var="room" varStatus="r">
                <tr>
                    <td>${r.count}</td>
                    <td>${room.roomId}</td>
                    <td>${room.members}</td>
                    <td>${room.count}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/man/queryRoomInfo?id=${room.roomId}&currentPage=${pageResult.currentPage}">详情</a>&nbsp;<a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/man/queryRoomInfo?id=${room.roomId}&currentPage=${pageResult.currentPage}">管理</a></td>
                </tr>
            </c:forEach>
        </table>
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
</div>
</body>
</html>
