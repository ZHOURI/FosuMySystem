<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/14
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/manager/updPass" method="post">
    <input type="password" name="password" id="psd1" placeholder="填写你的新密码">
    <input type="password" id="psd2" placeholder="确认密码">
    <input type="submit" value="修改密码">
</form>
</body>
</html>
