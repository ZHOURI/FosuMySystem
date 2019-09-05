<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/12
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="pragma"content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires"content="0">
<html>
<head>
    <title>Title</title>
    <style>
        *{
            padding: 0px;
            margin: 0px;
        }
        ul li:hover{
            background-color: #2a85a0;
        }

    </style>
</head>
<body>
<div>
    <div id="header" style="background-color: #0e90d2;width: 100%;height: 100px">
        <p style="line-height: 100px;text-align: center;color: white;font-size: 50px">佛大宿舍管理系统宿管页面</p>
    </div>
    <div id="menuBar" style="height: 40px;background-color:darkorange">
        <ul style="line-height: 40px;padding: 0px;margin: 0px;">
            <li style="float: right;margin-left: 20px">登录</li>
            <li style="float: right;margin-left: 20px">主页</li>
            <li style="float: right;margin-left: 20px">退出</li>
        </ul>
    </div>
    <div id="content">
        <div id="left" style="background-color:lightskyblue;width: 18%;height: auto;float: left">
            <ul>
                <li><a href="/logout.do" >退出账户</a></li>
                <li><a href="/manage/updPass.jsp" target="myframe">修改密码</a></li>
                <li><a href="/man/ownInfo.do" target="myframe">查看个人信息</a></li>
                <li><a href="/man/queryStu.do" target="myframe">管理学生信息</a></li>
                <li><a href="/man/queryPunish.do" target="myframe">管理学生违纪信息</a></li>
                <li><a href="/man/queryFees.do" target="myframe">管理学生宿舍费缴纳信息</a></li>
                <li><a href="/man/queryStay.do" target="myframe">管理学生留宿申请信息</a></li>
                <li><a href="/man/queryRoom.do" target="myframe">查看宿舍信息</a></li>
                <li><a href="/man/queryWater.do" target="myframe">查看宿舍水电费信息</a></li>
                <li><a href="/man/queryRoomPunish.do" target="myframe">查看宿舍违纪情况</a></li>
                <li><a href="/man/queryHealth.do" target="myframe">查看宿舍卫生信息</a></li>
                <li><a href="/man/queryVisitor.do" target="myframe">来访登记</a></li>
                <li><a href="/man/queryRepair.do" target="myframe">审批报修申请</a></li>
                <li><a href="/man/queryNotice.do" target="myframe">发布公告</a></li>
            </ul>
        </div>
        <div id="right" style="background-color: white;width:82%;height: auto;float: left;">
            <iframe src="/man/ownInfo.do" name="myframe" width="100%" height="100%" frameborder="0px" scrolling="">
            </iframe>
        </div>
    </div>
</div>
</body>
</html>
