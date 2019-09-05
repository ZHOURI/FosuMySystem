<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/25
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>学生的详细信息</title>
    <link rel="stylesheet" href="/css/style.css">
    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
</head>
<script>
    $(function () {
        addStuPunish();
    })
</script>
<body>
<button onclick="fresh()" style="float: right">框架内刷新</button>
<div class="cardBox">
    <div class="headerBox" style="background-color: #4caf50;">
        <p>
            <a title="查看详情" style="cursor: pointer; color:white" onclick="viewXmInfo('${var.OMP_XM_ID}');">学生基本信息</a>
        </p>
    </div>
    <div class="bodyBox">
        <img src="/img/2.jpg" width="175px" height="175px" style="float: left;">
        <table class="stuInfoBox">
            <tr>
                <td><label>学号：</label><span>${student.id}</span></td>
                <td><label>姓名：</label><span>${student.stuName}</span></td>
                <td><label>性别：</label><span>${student.sex}</span></td>
            </tr>
            <tr>
                <td><label>年龄：</label><span>${student.age}</span></td>
                <td><label>学院：</label><span>${student.collegeName}</span></td>
                <td><label>班级：</label><span>${student.className}</span></td>
            </tr>
            <tr>
                <td><label>联系电话：</label><span>${student.stuPhone}</span></td>
                <td><label>家庭住址：</label><span>${student.address}</span></td>
                <td><label>宿舍号：</label><span>${student.roomId}</span></td>
            </tr>
            <tr>
                <td><label>床位号：</label><span>${student.bed}</span></td>
                <td><label>类型：</label><span>${student.identity}</span></td>
                <td><label>入住时间：</label><span><fmt:formatDate value="${student.checkin}" pattern="yyyy-MM-dd"></fmt:formatDate></span></td>
            </tr>
        </table>
        <p>项目状态：
            <a href="javascript:void(0)" class="label label-success" style="border-radius: .25em;">启动</a>
        </p>
        <button id="updStuInfo" onclick="">修改学生信息</button>
    </div>
</div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>留宿申请记录</p>
    </div>
    <div class="bodyBox">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>学号</th>
                <th>留宿原因</th>
                <th>父母名字</th>
                <th>辅导员名字</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>申请状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${student.stayList}" var="stay" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${stay.stuId}</td>
                    <td>${stay.reason}</td>
                    <td>${stay.parent}</td>
                    <td>${stay.teacher}</td>
                    <td><fmt:formatDate value="${stay.startTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td><fmt:formatDate value="${stay.endTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td>${stay.status}</td>
                    <c:if test="${stay.status == '待审核'}">
                        <td><button class="btn btn-default btn-sm" onclick="editStay(${stay.id})">通过</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delStay(${stay.id})">删除</button>
                    </c:if>
                    <c:if test="${stay.status != '待审核'}">
                    <td><button class="btn btn-default btn-sm" onclick="editStay(${stay.id})">取消</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delStay(${stay.id})">删除</button>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>项目名称四</p>
    </div>
    <div class="bodyBox">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>学号</th>
                <th>违纪类型</th>
                <th>备注</th>
                <th>违纪时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${student.punishes}" var="punish" varStatus="p">
                <tr>
                    <td>${p.count}</td>
                    <td>${punish.stuId}</td>
                    <td>${punish.type}</td>
                    <td>${punish.comments}</td>
                    <td><fmt:formatDate value="${punish.punishtime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td><button class="btn btn-default btn-sm" id="stuPunishBtf" onclick="window.location.href='/manage/updStuPunish.jsp?id=${punish.id}'">修改</button>&nbsp;
                        <button class="btn btn-default btn-sm" onclick="delStuPunish(${punish.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="cardBox">
    <div class="headerBox" style="background-color: #5BC0DE;">
        <p>项目名称四</p>
    </div>
    <div class="bodyBox">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>学号</th>
                <th>年份</th>
                <th>缴费金额</th>
                <th>缴费状态</th>
                <th>缴费时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${student.feesList}" var="fees" varStatus="f">
                <tr>
                    <td>${f.count}</td>
                    <td>${fees.stuId}</td>
                    <td>${fees.years}</td>
                    <td>${fees.paystatus}</td>
                    <td>${fees.fee}</td>
                    <td><fmt:formatDate value="${fees.paytime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <c:if test="${fees.paystatus == '未缴费'}">
                        <td>
                            <button class="btn btn-default btn-sm" onclick="editFees(${fees.id})">确认缴费</button>
                            <button class="btn btn-default btn-sm" onclick="delFees(${fees.id})">删除</button>
                        </td>
                    </c:if>
                    <c:if test="${fees.paystatus != '未缴费'}">
                        <td>
                            <button class="btn btn-default btn-sm" onclick="editFees(${fees.id})">取消缴费</button>
                            <button class="btn btn-default btn-sm" onclick="delFees(${fees.id})">删除</button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="header"><a href="javascript:void(0)">添加学生违纪记录</a></div>
<div id="login_content">
    <div id="head"><span>添加违纪记录</span></div>
    <div id="login_tent">
        <table>
            <form id="myform" method="post">
                <caption>请先登录</caption>
                <tr><td>学号：</td><td><input id="stuId" type="text" name="stuId" required></td></tr>
                <tr><td>违纪事件：</td><td><input type="date" name="punishtime"></td></tr>
                <tr>
                    <td>违纪类型：</td>
                    <td>
                        <select name="type">
                        <option value="打架">打架</option>
                        <option value="晚归不归">晚归不归</option>
                        <option value="夜间大声喧哗">夜间大声喧哗</option>
                        <option value="恶意破坏公物">恶意破坏公物</option>
                        <option value="其他">其他</option>
                        </select>
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
</body>


</html>
