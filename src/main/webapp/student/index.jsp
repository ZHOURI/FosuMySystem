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
    </style>
</head>
<body>
<div>
    <div id="header" style="background-color: #0e90d2;width: 100%;height: 100px">
        <p style="line-height: 100px;text-align: center;color: white;font-size: 50px">佛大宿舍管理系统学生页面</p>
    </div>
    <div id="content">
        <div id="left" style="background-color: #4cae4c;width: 20%;height: auto;float: left">
            <ul>
                <li><a href="/logout.do" >退出账户</a></li>
                <li><a href="/test.do" target="myframe">试一试</a></li>
                <li><a href="/student/updPass.jsp" target="myframe">修改密码</a></li>
                <li><a href="/stu/ownInfo.do" target="myframe">查看个人信息</a></li>
                <li><a href="/stu/room.do" target="myframe">查看宿舍信息</a></li>
                <li><a href="/stu/queryStay.do" target="myframe">提交留宿申请</a></li>
                <li><a href="/stu/queryRepair.do" target="myframe">申请报修</a></li>
                <li><a href="/stu/queryNotice.do" target="myframe">查看公告</a></li>
                <li><a href="/stu/queryPunish.do" target="myframe">查看违纪记录</a></li>
                <li><a href="/stu/queryFees.do" target="myframe">查看宿舍费缴纳情况</a></li>
                <li><a href="#" target="myframe">办理退宿申请</a></li>
            </ul>
        </div>
        <div id="right" style="background-color: #d5d5d5;width: 80%;height: auto;float: left ">
            <iframe src="/stu/ownInfo.do" id="myframe" name="myframe" width="100%" height="100%" frameborder="0px"  >
            </iframe>
        </div>
    </div>
</div>
</body>
</html>
