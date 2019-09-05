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
    <a href="/student/repairForm.jsp">提交报修单</a>
    ${msg}
    <%--<c:forEach items="${repairList}" var="repair" varStatus="r">--%>
        <%--<p>${r.count}</p>--%>
        <%--<p>${repair.reason}</p>--%>
        <%--<p><fmt:formatDate value="${repair.reporttime}" pattern="yyyy-MM-dd"/></p>--%>
        <%--&lt;%&ndash;<c:if test="${repair.images} neq null">&ndash;%&gt;--%>
            <%--<c:set var="var1" value="${repair.images}"/>--%>
            <%--<c:forEach var="tdv" items="${fn:split(var1,',')}">--%>
                <%--<c:if test="${not empty tdv}">--%>
                    <%--<img src="${fn:replace(fn:replace(tdv,'[',''),']','')}" width="200px" height="200px"/>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<img src="${repair.images}"/>&ndash;%&gt;--%>
        <%--<p>${repair.remarks}</p>--%>
        <%--<p>${repair.status}</p>--%>
        <%--<hr>--%>
    <%--</c:forEach>--%>
    <div class="container">
        <h3 style="text-align: center">报修单页面</h3>
        <div style="float:left">
            <form class="form-inline" style="margin: 5px" action="${pageContext.request.contextPath}/stu/queryRepair.do?" method="post">
                <div class="form-group">
                    <label for="exampleInputName2">年份</label>
                    <input type="text" class="form-control" id="exampleInputName2" name="reporter" value="${map.reporter[0]}" placeholder="">
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <label for="exampleInputAddress2">缴费状态</label>
                    <input type="text" class="form-control" id="exampleInputAddress2" name="reporttime" value="${map.reporttime[0]}" placeholder="">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div>

        <form id="form" action="${pageContext.request.contextPath}/stu/queryRepair.do?currentPage=${pageResult.currentPage}" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>编号</th>
                    <th>报修人</th>
                    <th>报修房号</th>
                    <th>报修时间</th>
                    <th>报修状态</th>
                    <th>报修原因</th>
                    <th>详情</th>
                </tr>
                <c:forEach items="${pageResult.items}" var="Repair" varStatus="r">
                    <tr>
                        <td>${r.count}</td>
                        <td>${Repair.reporter}</td>
                        <td>${Repair.roomId}</td>
                        <td><fmt:formatDate value="${Repair.reporttime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
                        <td>${Repair.status}</td>
                        <td style="max-width:100px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis">${Repair.reason}</td>
                        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/stu/queryRepairOne.do?id=${Repair.id}&currentPage=${pageResult.currentPage}">查看详情</a>&nbsp;</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <ul class="pagination">
            <c:if test="${pageResult.currentPage<=1}">
            <li class="disabled">
                </c:if>
                <c:if test="${pageResult.currentPage>1}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/stu/queryRepair.do?currentPage=${pageResult.currentPage-1}&reporter=${map.reporter[0]}&reporttime=${map.reporttime[0]}" onclick="">&laquo;</a></li>
            <c:forEach begin="1" end="${pageResult.totalPage}" step="1" varStatus="s" var="i">
                <c:if test="${pageResult.currentPage==i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/stu/queryRepair.do?currentPage=${i}&reporter=${map.reporter[0]}&reporttime=${map.reporttime[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pageResult.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/stu/queryRepair.do?currentPage=${i}&reporter=${map.reporter[0]}&reporttime=${map.reporttime[0]}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${pageResult.currentPage>=pageResult.totalPage}">
            <li class="disabled">
                </c:if>
                <c:if test="${pageResult.currentPage<pageResult.totalPage}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/stu/queryRepair.do?currentPage=${pageResult.currentPage+1}&reporter=${map.reporter[0]}&reporttime=${map.reporttime[0]}">&raquo;</a></li>
            <li style="font-size: 25px;margin-left: 20px">总共有${pageResult.totalCount}条记录,有${pageResult.totalPage}页</li>
        </ul>
    </div>

</body>

</html>
