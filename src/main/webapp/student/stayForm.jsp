<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/16
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
这是提交表单的页面
<form action="" method="post" id="myform">
    <input type="text" name="reason" required>
    <input type="date" name="startTime" >
    <input type="date" name="endTime" >
    <input type="text" name="parent" required>
    <input type="text" name="teacher" required>
    <input type="submit" value="提交" id="btf">
    <input type="reset" value="重置">
</form>
</div>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $(function () {
        $("#myform").submit(function (e) {
            e.preventDefault();
            var para = $("#myform").serializeObject() ;
            para = JSON.stringify(para) ;
            alert(para);
            $.ajax({
                url:"/stu/stay.do",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                data:para,
                success:function (data) {
                    if(data.status=="true")
                    {
                        alert(data.success);
                        window.location.href="/stu/queryStay.do";
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
        })
    })

</script>
</html>
