<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/25
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>宿舍详情页</title>
    <link rel="stylesheet" href="/css/style.css">
    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<button onclick="fresh()" style="float: right">刷新</button>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>宿舍基本信息</p>
    </div>
    <div class="bodyBox">
        <table style="margin: 0 auto">
            <tr>
                <td><label>宿舍号：</label><span>${room.roomId}</span></td>
                <td><label>宿舍成员：</label><span>${room.members}</span></td>
            </tr>
            <tr>
                <td><label>宿舍人数：</label><span>${room.count}</span></td>
                <td><label>所在楼区：</label><span>${room.dormName}</span></td>
            </tr>
            <tr>
                <td><label>所在校区：</label><span>${room.schoolName}</span></td>
            </tr>
        </table>

    </div></div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>宿舍违纪记录</p>
    </div>
    <div class="bodyBox">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>宿舍号</th>
                <th>违纪类型</th>
                <th>备注</th>
                <th>违纪时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageResult.items}" var="roomPuish" varStatus="r">
                <tr>
                    <td>${r.count}</td>
                    <td>${roomPuish.roomId}</td>
                    <td>${roomPuish.type}</td>
                    <td>${roomPuish.comments}</td>
                    <td><fmt:formatDate value="${roomPuish.createtime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a href="/manage/roomPunishForm.jsp?roomId=${room.roomId}">添加宿舍违纪</a>
</div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>项目名称四</p>
    </div>
    <div class="bodyBox">
    </div>
</div>
<table>
    <c:forEach items="${room.roomPunishList}" var="roomPunish" varStatus="r">
        <tr>
            <td>${r.count}</td>
            <td>${roomPunish.roomId}</td>
            <td>${roomPunish.comments}</td>
            <td>${roomPunish.type}</td>
            <td> <fmt:formatDate value="${roomPunish.createtime}" pattern="yyyy-MM-dd"/></td>
            <td> <fmt:formatDate value="${roomPunish.punishtime}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
</table>

<hr/>
<table>
    <c:forEach items="${room.waterList}" var="water" varStatus="w">
        <tr>
            <td>${w.count}</td>
            <td>${water.roomId}</td>
            <td>${water.pay}</td>
            <td><fmt:formatDate value="${water.nowTime}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${water.lastTime}" pattern="yyyy-MM-dd"/></td>
            <td>${water.paystatus}</td>
            <c:if test="${water.paystatus == '未缴费'}">
                <td><input type="button" value="确认缴费" onclick="editWater(${water.id})"></td>
            </c:if>
            <c:if test="${water.paystatus != '未缴费'}">
                <td><input type="button" value="取消缴费" onclick="editWater(${water.id})"></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<a href="/manage/roomWaterForm.jsp?roomId=${room.roomId}">添加宿舍水电费</a>
<hr/>
<table>
    <c:forEach items="${room.healthList}" var="health" varStatus="r">
        <tr>
            <td>${r.count}</td>
            <td>${health.roomId}</td>
            <td>${health.remark}</td>
            <td>${health.checkresult}</td>
            <td><fmt:formatDate value="${health.checktime}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
</table>
<a href="/manage/roomHealthForm.jsp?roomId=${room.roomId}">添加宿舍卫生</a>
<hr/>
<table>
    <c:forEach items="${room.repairList}" var="repair" varStatus="r">
        <tr>
            <td>${r.count}</td>
            <td>${repair.roomId}</td>
            <td>${repair.reason}</td>
            <td>${repair.reporter}</td>
            <td><fmt:formatDate value="${repair.reporttime}" pattern="yyyy-MM-dd"/></td>
            <td>${repair.status}</td>
            <c:if test="${repair.status == '待审核'}">
                <td><input type="button" value="审核通过" onclick="editRepair(${repair.id})"></td>
            </c:if>
            <c:if test="${repair.status != '待审核'}">
                <td><input type="button" value="取消通过" onclick="editRepair(${repair.id})"></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>

</html>
