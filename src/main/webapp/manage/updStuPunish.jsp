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
                $("#select option").each(function () {
                    if(this.text=="${punish.type}")
                    {
                        this.selected="selected";
                        return;
                    }
                })
                updateStuPunish();
            })
        </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改学生违纪记录</h3>
    <form action="#" method="post" id="myform">
        <input type="hidden" name="id" value="${punish.id}">
        <div class="form-group">
            <label for="stuId">学号：</label>
            <input type="text" class="form-control" id="stuId" name="stuId" value="${punish.stuId}" />
        </div>

        <div class="form-group">
            <label for="select">违纪类型：</label>
            <select name="type" id="select" class="form-control">
                <option value="使用大功率电器">使用大功率电器</option>
                <option value="不按规定事件熄灯">不按规定事件熄灯</option>
                <option value="夜间大声喧哗">夜间大声喧哗</option>
                <option value="恶意破坏公物">恶意破坏公物</option>
                <option value="其他">其他</option>
            </select>
        </div>

        <div class="form-group">
            <label for="createtime">违纪时间：</label>
            <input type="date" id="createtime" class="form-control" name="createtime" value="<fmt:formatDate value='${punish.createtime}' pattern='yyyy-MM-dd'/>" />
        </div>

        <div class="form-group">
            <label for="comments">备注：</label>
            <input type="text" class="form-control" id="comments" name="comments" value="${punish.comments}"/>
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

