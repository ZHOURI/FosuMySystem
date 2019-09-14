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
    <script src="../js/jquery-3.2.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="/js/common.js"></script>
    <script>
        $(function () {
            delSelect();
        })
    </script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">水电费记录详情页面</h3>
    <div style="float:left">
        <form class="form-inline" style="margin: 5px" action="${pageContext.request.contextPath}/man/queryWater.do?" method="post">
            <div class="form-group" style="margin-left: 10px">
                <label for="exampleInputAddress2">宿舍号</label>
                <input type="text" class="form-control" id="exampleInputAddress2" name="room_ID" value="${map.room_ID[0]}" placeholder="">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
    </div>
    <div style="float: right;">
        <a href="${pageContext.request.contextPath}/common/addWaterForm.jsp?currentPage=${pageResult.currentPage}"><button type="button" class="btn btn-primary">添加水电费记录</button></a>
        <a id="delSelect"><button type="button" class="btn btn-primary">删除选中</button></a>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/man/queryWater.do?currentPage=${pageResult.currentPage}" id="myform">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" name="uid" id="first"></th>
                <th>编号</th>
                <th>宿舍号</th>
                <th>水用量</th>
                <th>电用量</th>
                <th>水电费</th>
                <th>缴费状态</th>
                <th>上次抄表时间</th>
                <th>本次抄表时间</th>
                <th>操作</th>
            </tr>
            <c:if test="${empty pageResult.items}">
                <tr><td colspan="10">暂无相关消息</td></tr>
            </c:if>
            <c:if test="${not empty pageResult.items}">
                <c:forEach items="${pageResult.items}" var="water" varStatus="w">
                    <tr>
                        <td><input type="checkbox" name="uids" value="${water.id}"></td>
                        <td>${w.count}</td>
                        <td>${water.roomId}</td>
                        <td>${water.water}</td>
                        <td>${water.electric}</td>
                        <td>${water.pay}</td>
                        <td>${water.paystatus}</td>
                        <td><fmt:formatDate value="${water.lastTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                        <td><fmt:formatDate value="${water.nowTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                        <c:if test="${water.paystatus == '未缴费'}">
                            <td><button class="btn btn-default btn-sm" onclick="editOne('/man/editWater.do',${water.id})">确认缴费</button>&nbsp;<button class="btn btn-default btn-sm" onclick="queryOne('/man/queryWaterOne.do',${water.id})">修改</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delOne('/man/delWater.do',${water.id})">删除</button></td>
                        </c:if>
                        <c:if test="${water.paystatus != '未缴费'}">
                            <td><button class="btn btn-default btn-sm" onclick="editOne('/man/editWater.do',${water.id})">取消确认</button>&nbsp;<button class="btn btn-default btn-sm" onclick="queryOne('/man/queryWaterOne.do',${water.id})">修改</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delOne('/man/delWater.do',${water.id})">删除</button></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            <tr>
                <td colspan="13" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/common/addWaterForm.jsp">添加水电费记录</a></td>
            </tr>
        </table>
    </form>
    <c:if test="${pageResult.totalPage>0}">
        <ul class="pagination">
            <c:if test="${pageResult.currentPage<=1}">
                <li class="disabled"><a href="javascript:void(0)">&laquo;</a></li>
            </c:if>
            <c:if test="${pageResult.currentPage>1}">
                <li><a href="${pageContext.request.contextPath}/man/queryWater.do?currentPage=${pageResult.currentPage-1}&room_ID=${map.room_ID[0]}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="${pageResult.currentPage-5>0?(pageResult.totalPage-pageResult.currentPage<=5?pageResult.totalPage-9:pageResult.currentPage-4):1}" end="${pageResult.currentPage<=pageResult.totalPage-5?(pageResult.currentPage+5<10&&pageResult.totalPage>10?10:pageResult.currentPage+5):pageResult.totalPage}" step="1" varStatus="s" var="i">
                <c:if test="${pageResult.currentPage==i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/man/queryWater.do?currentPage=${i}&room_ID=${map.room_ID[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pageResult.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/man/queryWater.do?currentPage=${i}&room_ID=${map.room_ID[0]}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${pageResult.currentPage>=pageResult.totalPage}">
                <li class="disabled"><a href="javascript:void(0)">&raquo;</a></li>
            </c:if>
            <c:if test="${pageResult.currentPage<pageResult.totalPage}">
                <li><a href="${pageContext.request.contextPath}/man/queryWater.do?currentPage=${pageResult.currentPage+1}&room_ID=${map.room_ID[0]}">&raquo;</a></li>
            </c:if>
            <li style="font-size: 25px;margin-left: 20px">总共有${pageResult.totalCount}条记录,有${pageResult.totalPage}页</li>
        </ul><br>
        <span style="font-size: 20px;margin-top: 15px">跳转到：<input type="text" style="width: 40px;height: 30px;" id="jump" value="${pageResult.currentPage}">
            <a><button type="button" class="btn btn-primary" onclick="jump('/man/queryWater.do?room_ID=${map.room_ID[0]}',${pageResult.totalPage})">跳转到</button></a>
        </span>
    </c:if>

</div>
</body>
</html>
