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
    <%--<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.min.js"></script>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改密码</h3>
    <form action="#" method="post" id="udpForm">
        <input type="hidden" name="id" value="${punish.id}">
        <div class="form-group">
            <label for="psd1">填写你的新密码：</label>
            <input type="password" class="form-control" id="psd1" name="password"  />
        </div>


        <div class="form-group">
            <label for="psd2">确认密码：</label>
            <input type="password" class="form-control" id="psd2" name="password2"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" id="submit1" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
        </div>
        <h3 id="msg" style="color: red"></h3>
    </form>
</div>
</body>
</html>

</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        var flag = false;
        $("#psd2").keyup(function () {
            if($("#psd1").val()==$("#psd2").val())
            {
                $("#msg").text("两次密码一样");
                flag = true;
            }
            else
            {
                $("#msg").text("两次密码不一样");
                flag = false;
            }
        })
        $("#udpForm").submit(function (e) {
            e.preventDefault();
            if(flag)
            {
                $.ajax({
                    url:"/man/updPass.do",
                    data:{"password":$("#psd1").val()},
                    type:"post",
                    dataType:"json",
                    // contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if(data.status=="true")
                        {
                            alert(data.success_msg);
                            window.location.href="/man/ownInfo.do";
                        }
                        else
                        {
                            alert(data.error_msg);
                        }
                    },
                    error:function () {
                        alert("访问出错");
                    }
                })
            }
            else
            {
                $("#msg").text("两次密码不一样");
            }
        })
    })
</script>
</html>
