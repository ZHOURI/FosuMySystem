$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function mySelect(arg) {
    $("#select option").each(function () {
        if(this.text==arg)
        {
            this.selected="selected";
            return;
        }
    })
}
function editStay(stayId)
{
    $.ajax({
        url:"/man/editStay.do",
        data:{id:stayId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}
function fresh(){
    // 框架内页面刷新：可实现局部刷新与整个页面重定向
    self.location.reload();  //刷新框架内页面
    // window.parent.location.href='http://koushuling.top'; //页面重定向
};
function editFees(feesId)
{
    $.ajax({
        url:"/man/editFees.do",
        data:{id:feesId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}

function editWater(waterId)
{
    $.ajax({
        url:"/man/editWater.do",
        data:{id:waterId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delSelect() {
    var delSelect = document.getElementById("delSelect");
    delSelect.onclick= function () {
        var flag= confirm("确定要删除？");
        if(flag)
        {
            var uids = document.getElementsByName("uids");
            flag1 = false;
            for(var i=0;i<uids.length;i++)
            {
                if(uids[i].checked)
                {
                    flag1=true;
                    break;
                }
            }
        }
        if(flag1)
        {
            document.getElementById("form").submit();
        }
    }

    document.getElementById("first").onclick=function()
    {
        var uids = document.getElementsByName("uids");
        for(var i=0;i<uids.length;i++)
        {
            uids[i].checked = this.checked;
        }
    }
}
function editRepair(repairId)
{
    $.ajax({
        url:"/man/editRepair.do",
        data:{id:repairId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function addStuPunish() {
    var a=document.querySelector('#header a');
    var login=document.getElementById('login_content');
    var beijing=document.getElementById('beijing');
    var head=document.getElementById('head');
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
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para) ;
        alert(para);
        $.ajax({
            url:"/man/addStuPunish.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    fresh();
                }
                else
                {
                    alert(data.error);
                }

            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateStuFees() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updFees.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateRepair() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updRepair.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateRoomPunish() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updRoomPunish.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateWater() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updWater.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateHealth() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updHealth.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateVisitor() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updVisitor.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateNotice() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updNotice.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function updateStuPunish() {
    $("#submit1").click(function (e) {
        e.preventDefault();
        var para = $("#myform").serializeObject() ;
        para = JSON.stringify(para);
        alert(para);
        $.ajax({
            url:"/man/updStuPunish.do",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            data:para,
            success:function (data) {
                if(data.status=="true")
                {
                    alert(data.success);
                    self.location=document.referrer;
                }
                else
                {
                    alert(data.error);
                }
            },
            error:function () {
                alert("访问出错");
            }
        })
    })
}
function delStuPunish(punishId) {
    $.ajax({
        url:"/man/delStuPunish.do",
        data:{id:punishId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delStuFees(feesId) {
    $.ajax({
        url:"/man/delStuFees.do",
        data:{id:feesId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delRoomPunish(roomPunishId) {
    $.ajax({
        url:"/man/delRoomPunish.do",
        data:{id:roomPunishId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delWater(waterId) {
    $.ajax({
        url:"/man/delWater.do",
        data:{id:waterId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delHealth(healthId) {
    $.ajax({
        url:"/man/delHealth.do",
        data:{id:healthId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delNotice(noticeId) {
    $.ajax({
        url:"/man/delNotice.do",
        data:{id:noticeId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delVisitor(visitorId) {
    $.ajax({
        url:"/man/delVisitor.do",
        data:{id:visitorId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delRepair(repairId) {
    $.ajax({
        url:"/man/delRepair.do",
        data:{id:repairId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}
function delStay(stayId) {
    $.ajax({
        url:"/man/delStay.do",
        data:{id:stayId},
        type:"post",
        success:function (data) {
            alert(data.msg);
            fresh();
        },
        error:function () {
            alert("访问出错");
        }
    })
}

