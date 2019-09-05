<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/26
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生违纪记录</title>
</head>
<body>
<form action="" method="post" id="myform">
    <input type="text" name="stu_ID" required>
    <input type="date" name="createTime" >
    <input type="text" name="type" required>
    <input type="text" name="comments" required>
    <input type="submit" value="提交" id="btf">
    <input type="reset" value="重置">
</form>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $(function () {
        $("#myform").submit(function (e) {
            e.preventDefault();
            var para = $("#myform").serialize() ;
            para = JSON.stringify(para) ;
            $.ajax({
                url:"/man/addStuPunish.do",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                data:para,
                success:function (data) {
                    if(data.status=="true")
                    {
                        alert(data.success);
                        window.location.href=history.back();
                    }
                    else
                    {
                        alert(data.error);
                    }

                },
                error:function () {
                    alert("添加失败");
                }
            })
            parent.location.href=history.back();
        })
    })

</script>
</html>
