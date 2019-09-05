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
            updateVisitor();
        })
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改学生宿舍费缴纳记录</h3>
    <form action="#" method="post" id="myform">
        <input type="hidden" name="id" value="${visitor.id}">
        <div class="form-group">
            <label for="visitName">来访人姓名：</label>
            <input type="text" class="form-control" id="visitName" name="visitName" value="${visitor.visitName}" />
        </div>
        <div class="form-group">
            <label for="visitPhone">来访人电话：</label>
            <input type="text" class="form-control" id="visitPhone" name="visitPhone" value="${visitor.visitPhone}" />
        </div>
        <div class="form-group">
            <label for="reason">来访原因：</label>
            <input type="text" class="form-control" id="reason" name="reason" value="${visitor.reason}" />
        </div>
        <div class="form-group">
            <label for="visitTime">访问时间：</label>
            <input type="date" id="visitTime" class="form-control" name="visitTime" value="<fmt:formatDate value='${visitor.visitTime}' pattern='yyyy-MM-dd'/>" />
        </div>
        <div class="form-group">
            <label for="leaveTime">离开时间：</label>
            <input type="date" class="form-control" id="leaveTime" name="leaveTime" value="<fmt:formatDate value='${visitor.leaveTime}' pattern='yyyy-MM-dd'/>" />
        </div>
        <div class="form-group">
            <label for="remark">备注：</label>
            <input type="text" class="form-control" id="remark" name="remark" value="${visitor.remark}" />
        </div>
        <div class="form-group">
            <label for="manageName">值班宿管：</label>
            <input type="text" class="form-control" id="manageName" name="manageName" value="${visitor.manageName}" />
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

