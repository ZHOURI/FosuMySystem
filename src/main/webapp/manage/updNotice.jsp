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
            updateNotice();
        })
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改公告记录</h3>
    <form action="#" method="post" id="myform">
        <input type="hidden" name="id" value="${notice.id}">
        <div class="form-group">
            <label for="content">内容：</label>
            <input type="text" class="form-control" id="content" name="content" value="${notice.content}" />
        </div>
        <div class="form-group">
            <label for="noticeTime">访问时间：</label>
            <input type="date" id="noticeTime" class="form-control" name="noticeTime" value="<fmt:formatDate value='${notice.noticeTime}' pattern='yyyy-MM-dd'/>" />
        </div>

        <div class="form-group">
            <label for="noticePeoper">发布人：</label>
            <input type="text" class="form-control" id="noticePeoper" name="noticePeoper" value="${notice.noticePeoper}" />
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

