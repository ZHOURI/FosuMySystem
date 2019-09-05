<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        #beijing{
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: #000;
            opacity: 0.6;
            display: none;
        }
        #login_content{
            background: white;
            border: 1px solid black;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left:-150px;
            margin-top: -150px;
            display: none;
            z-index: 2;
        }
        #login_content #login_tent{
            padding: 50px;/*也有居中奇效*/
        }

        #head{
            width: 100%;
            height: 20px;
            background: yellow;
        }
    </style>
</head>
<body>
<div id="header"><a href="javascript:void(0)">弹出登录窗口</a></div>
<div id="login_content">
    <div id="head"><span>登录窗口</span></div>
    <div id="login_tent">
        <table>
            <form id="myform" method="post">
                <caption>请先登录</caption>
                <tr><td>学号：</td><td><input type="text" name="stuId" required></td></tr>
                <tr>
                    <td>违纪类型：</td>
                    <td>
                        <%--<select name="type">--%>
                        <%--<option value="打架">打架</option>--%>
                        <%--<option value="晚归不归">晚归不归</option>--%>
                        <%--<option value="夜间大声喧哗">夜间大声喧哗</option>--%>
                        <%--<option value="恶意破坏公物">恶意破坏公物</option>--%>
                        <%--<option value="其他">其他</option>--%>
                        <%--</select>--%>
                        <input type="text" name="type">
                    </td>
                </tr>
                <tr><td>违纪记录：</td><td><textarea name="comments" rows="5" cols="25" ></textarea></td></tr>
                <tr><td><input type="submit" value="登录" id="submit1" /><td><input type="reset" value="重置" /></td></tr>
                <tr><td colspan="2"><input type="button" value="关闭" width="200px" id="btf"></td></tr>
                <p style="text-align: center;"></p>
            </form>
        </table>
    </div>
</div>
<div id="beijing"></div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>项目名称四</p>
    </div>
    <div class="bodyBox">
    </div>
</div>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    var a=document.querySelector('#header a');
    var login=document.getElementById('login_content');
    var beijing=document.getElementById('beijing');
    var head=document.getElementById('head');
    var submit1=document.getElementById("submit1");
    a.onclick=function(){
        login.style.display='block';
        beijing.style.display='block';
    }
    head.onmousedown=function(e){
        var login=document.getElementById('login_content');
        e=e||event;
        var x=e.pageX-login.offsetLeft;
        var y=e.pageY-login.offsetTop;
        document.onmousemove=function(e){
            //点击着移动，所以onmousemove嵌入在onmousedown中
            e=e||event;
            var loginX=e.pageX-x;
            var loginY=e.pageY-y;
            loginX=loginX<0?0:loginX;
            loginY=loginY<0?0:loginY;
            maxX=window.innerWidth-login.offsetWidth;
            maxY=window.innerHeight-login.offsetHeight;
            loginX=loginX>maxX?maxX:loginX;
            loginY=loginY>maxY?maxY:loginY;
            login.style.left=loginX+150+'px';
            login.style.top=loginY+150+'px';
        }
    }
    document.onmouseup=function(){
        document.onmousemove=null;
    };
    $("#btf").click(function()
    {
        login.style.display='none';
        beijing.style.display='none';
    });
    submit1.onclick=function(e){
        e.preventDefault();
        var para = $("#myform").serialize();
        para = JSON.stringify(para) ;
        alert(para);
        $.ajax({
            type:"post",
            url:"/man/addStuPunish.do",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function(data)
            {
                if(data.status=="true")
                {
                    login.style.display='none';
                    beijing.style.display='none';
                }
                else
                {
                    alert("添加出错");
                }
            },
            error:function()
            {
                alert("访问出错");
            }

        });
    }
</script>
</html>
