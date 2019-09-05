<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/18
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是报修页面
<form action="" method="post" id="myform" enctype="multipart/form-data">
    <input type="text" name="reason" required>
    <input type="file" name="images" id="images" accept="image/*">
    <%--<input type="file" name="file" onchange="showPreview(this)" multiple />--%>
    <%--<img id="portrait" src="" width="70" height="75">--%>
    <input type="file" name="images1" accept="image/*">
    <input type="text" name="remarks">
    <input type="submit" value="提交" id="btf">
    <input type="reset" value="重置">
</form>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $(function () {
        $("#myform").submit(function (e) {
            e.preventDefault();//阻止默认的表单提交事件
            //单个图片上传
            // var para = $("#myform").serializeObject();
            // para = JSON.stringify(para);
            var formData = new FormData($("#myform")[0]);
            $.ajax({
                url:"/stu/repair.do",
                type:"post",
                data:formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success:function (data) {
                    alert(data);
                    window.location.href="/stu/queryRepair.do";
                },
                error:function () {
                    alert("添加失败");
                }
            })
        })
    })
    //预览图片
    function showPreview(source) {
        var file = source.files[0];
        if(window.FileReader) {
            var fr = new FileReader();
            fr.onloadend = function(e) {
                document.getElementById("portrait").src = e.target.result; //这个就是图片的流，可以直接展示到页面上
            };
            fr.readAsDataURL(file);
        }
    }
</script>
</html>
