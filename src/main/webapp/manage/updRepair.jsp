<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/28
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <%--<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            mySelect("${repair.paystatus}");
            updateRepair();
        })
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改学生宿舍费缴纳记录</h3>
    <form action="#" method="post" id="myform">
        <input type="hidden" name="id" value="${repair.id}">
        <div class="form-group">
            <label for="roomId">宿舍号：</label>
            <input type="text" class="form-control" id="roomId" name="roomId" value="${repair.roomId}" />
        </div>
        <div class="form-group">
            <label for="repair">水用量：</label>
            <input type="text" class="form-control" id="repair" name="repair" value="${repair.repair}" />
        </div>
        <div class="form-group">
            <label for="electric">电用量：</label>
            <input type="text" class="form-control" id="electric" name="electric" value="${repair.electric}" />
        </div>
        <div class="form-group">
            <label for="pay">水电费：</label>
            <input type="text" class="form-control" id="pay" name="pay" value="${repair.pay}" />
        </div>
        <div class="form-group">
            <label for="paytime">缴费时间：</label>
            <input type="date" id="paytime" class="form-control" name="paytime" value="<fmt:formatDate value='${repair.paytime}' pattern='yyyy-MM-dd'/>" />
        </div>
        <div class="form-group">
            <label for="select">缴费状态：</label>
            <select name="paystatus" id="select" class="form-control">
                <option value="未缴费">未缴费</option>
                <option value="已缴费">已缴费</option>
            </select>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" id="submit1" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
        </div>
    </form>
</div>
</body>
</html>

