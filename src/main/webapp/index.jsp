<%@ page import="cn.honeyjam.dorm.common.Identify" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <meta http-equiv="pragma"content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires"content="0">

</head>
<body>

<img id="img" src="image/2.jpg" alt="">
<div class="container">
    <div>
        <h2>佛大宿舍管理系统</h2>
    </div>
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">用户登录</h3>
            <form id="form" action="/checkLogin.do" method="post">
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="请输入登录名" id="loginName" name="userId" autofocus="autofocus" maxlength="20" required="required" value="${user_Login.id}"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input id="btf" class="form-control required" type="password" placeholder="请输入密码" id="password" name="password" maxlength="8" required="required" value=""/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <div class="col-md-16">
                            <!-- 样式1 -->
                            <select class="form-control" name="sign">
                                <option value="学生">学生</option>
                                <option value="宿管">宿管</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="checkbox pull-left">
                            <input id="rem" type="checkbox" name="remember" id="remember" checked/>记住我
                        </label>
                        <label class="checkbox pull-right">
                            <a href="menu.html" >忘记密码？</a>
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button id="send" class="btn btn-success col-lg-4" name="submit">登录</button>
                        <button type="reset" class="btn btn-success col-lg-4 pull-right" name="submit">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <h4 class="col-md-12" style="color: red;text-align: center">${error_msg}</h4>
    </div>
</div>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        var b = <%= session.getAttribute("user")!=null?true:false%>;
        var c =<%= session.getAttribute("user_Login")!=null?true:false%>;
        if(b)
        {
            window.location.href=history.back();
        }
        var ele = $("#btf");
        if(c)
        {
            ele.val(${user_Login.password});
            $("#rem").prop("checked",true);
        }
        else
        {
            ele.val("");
            $("#rem").prop("checked",false);
        }

        $("#send").click(function () {
            $("#form").submit();
        })
        // window.opener=null;
        // window.open('','_self');
        // window.close();
    })
</script>
</html>
