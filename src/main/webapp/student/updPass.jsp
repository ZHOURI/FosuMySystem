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
<form action="#" method="post" id="udpForm">
    <input type="password" name="password" id="psd1" placeholder="填写你的新密码" required>
    <input type="password" id="psd2" placeholder="确认密码" required>
    <input type="submit" value="修改密码" id="sub">
    <span id="msg"></span>
</form>
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
                    url:"/stu/updPass.do",
                    data:{"password":$("#psd1").val()},
                    type:"post",
                    dataType:"json",
                    // contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if(data.status=="true")
                        {
                            alert(data.success_msg);
                            window.location.href="/stu/ownInfo.do";
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
