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
    <input type="date" name="punishtime" >
    <select name="type">
        <option value="使用大功率电器">使用大功率电器</option>
        <option value="不按规定事件熄灯">不按规定事件熄灯</option>
        <option value="夜间大声喧哗">夜间大声喧哗</option>
        <option value="恶意破坏公物">恶意破坏公物</option>
        <option value="其他">其他</option>
    </select>
    <input type="text" name="comments" required>
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
                url:"/man/addRoomPunish.do",
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
