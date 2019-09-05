<%--
  Created by IntelliJ IDEA.
  User: 黑客Jack
  Date: 2019/8/25
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>佛大宿舍管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

</head>
<script>
    $(function () {
        function open(msg) {
            // var a=$("tr td:nth-child(4) a");
            // var msg = a.text();
            var login=document.getElementById('login_content');
            var beijing=document.getElementById('beijing');
            var head=document.getElementById('head');
            var submit1=document.getElementById("submit1");
            var msgElem = document.getElementById("msg");
            login.style.display='block';
            beijing.style.display='block';
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
            msgElem.innerText = msg;
            submit1.onclick=function(){
                login.style.display='none';
                beijing.style.display='none';
            }
        }
        $("tr td:nth-child(4) a").click(
            function () {
                open(this.text);
            }
        )


    })
</script>
<body>
<div class="container">
    <h3 style="text-align: center">报修单详情页面</h3>
    <div style="float:left">
        <form class="form-inline" style="margin: 5px" action="${pageContext.request.contextPath}/man/queryStay.do?" method="post">
            <div class="form-group" style="margin-left: 10px">
                <label for="exampleInputAddress2">宿舍号</label>
                <input type="text" class="form-control" id="exampleInputAddress2" name="room_ID" value="${map.room_ID[0]}" placeholder="">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
    </div>
    <div style="overflow:auto;width: 100%;">
        <table border="1" class="table table-bordered table-hover" style="width:auto">
            <tr class="success">
                <th>编号</th>
                <th>宿舍号</th>
                <th>报修人</th>
                <th>报修原因</th>
                <th>报修图片</th>
                <th>报修备注</th>
                <th>报修时间</th>
                <th>报修状态</th>
                <th>维修工人</th>
                <th>维修费用</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageResult.items}" var="repair" varStatus="r">
                <tr>
                    <td>${r.count}</td>
                    <td>${repair.roomId}</td>
                    <td>${repair.reporter}</td>
                    <td style="max-width:100px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis">
                        <a href="javascript:void(0)">${repair.reason}</a>
                    </td>
                    <td>${repair.remarks}</td>
                    <td>
                        <c:set var="var1" value="${repair.images}"/>
                        <c:forEach var="tdv" items="${fn:split(var1,',')}">
                            <c:if test="${not empty tdv}">
                                <a href="${fn:replace(fn:replace(tdv,'[',''),']','')}"><img src="${fn:replace(fn:replace(tdv,'[',''),']','')}" width="90px" height="90px" style="float: left"/></a>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td><fmt:formatDate value="${repair.reporttime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td>${repair.status}</td>
                    <td>${repair.workerName}</td>
                    <td>${repair.pay}</td>
                    <c:if test="${repair.status == '待审核'}">
                        <td><button class="btn btn-default btn-sm" onclick="editRepair(${repair.id})">审核通过</button>&nbsp;<button class="btn btn-default btn-sm" onclick="window.location.href='/man/queryRepairOne.do?id=${repair.id}'">修改</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delRepair(${repair.id})">删除</button></td>
                    </c:if>
                    <c:if test="${repair.status != '待审核'}">
                        <td><button class="btn btn-default btn-sm" onclick="editRepair(${repair.id})">取消通过</button>&nbsp;<button class="btn btn-default btn-sm" onclick="window.location.href='/man/queryRepairOne.do?id=${repair.id}'">修改</button>&nbsp;<button class="btn btn-default btn-sm" onclick="delRepair(${repair.id})">删除</button></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
    <ul class="pagination">
        <c:if test="${pageResult.currentPage<=1}">
        <li class="disabled">
            </c:if>
            <c:if test="${pageResult.currentPage>1}">
        <li>
            </c:if>
            <a href="${pageContext.request.contextPath}/man/queryRepair.do?currentPage=${pageResult.currentPage-1}&room_ID=${map.room_ID[0]}" onclick="">&laquo;</a></li>
        <c:forEach begin="1" end="${pageResult.totalPage}" step="1" varStatus="s" var="i">
            <c:if test="${pageResult.currentPage==i}">
                <li class="active"><a href="${pageContext.request.contextPath}/man/queryRepair.do?currentPage=${i}&room_ID=${map.room_ID[0]}">${i}</a></li>
            </c:if>
            <c:if test="${pageResult.currentPage!=i}">
                <li><a href="${pageContext.request.contextPath}/man/queryRepair.do?currentPage=${i}&room_ID=${map.room_ID[0]}">${i}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${pageResult.currentPage>=pageResult.totalPage}">
        <li class="disabled">
            </c:if>
            <c:if test="${pageResult.currentPage<pageResult.totalPage}">
        <li>
            </c:if>
            <a href="${pageContext.request.contextPath}/man/queryRepair.do?currentPage=${pageResult.currentPage+1}&room_ID=${map.room_ID[0]}}">&raquo;</a></li>
        <li style="font-size: 25px;margin-left: 20px">总共有${pageResult.totalCount}条记录,有${pageResult.totalPage}页</li>
    </ul>
</div>
<div id="login_content">
    <div id="head"><span>报修原因详情</span></div>
    <div id="login_tent">
        <div style="width: 200px;height: 200px;word-wrap: break-word">
            <p id="msg"></p>
        </div>
        <p style="text-align: center;"><input type="submit" value="关闭" id="submit1" style="width:50px;"/></p>
    </div>
</div>
<div id="beijing"></div>
</body>
</html>
