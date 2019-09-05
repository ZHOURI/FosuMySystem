<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/27
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加宿舍违纪记录</title>
</head>
<body>
<button onclick="self.history.back()">返回</button>
<form action="" method="post" id="myform">
    <input id="roomId" type="text" name="roomId" required>
    <input type="date" name="lastTime" >
    <input type="date" name="nowTime" >
    <select name="type">
        <option value="不合格">不合格</option>
        <option value="合格">合格</option>
        <option value="良好">良好</option>
        <option value="优秀">优秀</option>
    </select>
    <input type="text" name="water" >
    <input type="text" name="electric">
    <input type="text" name="pay">
    <select name="paystatus">
        <option value="未缴费">未缴费</option>
        <option value="已缴费">已缴费</option>
    </select>
    <input type="submit" value="提交" id="btf">
    <input type="reset" value="重置">
</form>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $(function () {
        $("#roomId").val(getUrlParam("roomId"));
        $("#myform").submit(function (e) {
            e.preventDefault();
            var para = $("#myform").serializeObject() ;
            alert(para);
            para = JSON.stringify(para) ;
            $.ajax({
                url:"/man/addRoomWater.do",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                data:para,
                success:function (data) {
                    if(data.status=="true")
                    {
                        alert(data.success);
                        self.history.back();
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
