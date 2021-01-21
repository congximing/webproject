<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/8/14
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>用户登录</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="js/vector.js"></script>

    <script>
        //切换验证码
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }
    </script>
    <style>
        /*.rg {*/
            /*background-image: url("img/16.jpg");*/
            /*background-repeat: no-repeat;*/
            /*width: 1706px;*/
            /*height: 835px;*/
            /*border: 1px solid red;*/
        /*}*/

        .rg_layout {
            width: 600px;
            height: 350px;
            border: 5px solid #eeeeee;
            background-color: white;
            margin: auto;
            margin-top: 10px;
        }
        .rg_1{
            color: red;

        }
    </style>
</head>
<body>
<%--<div id="container"&lt;%&ndash; class="rg"&ndash;%&gt;>
    <h1 style="color: white;text-align: center;margin-top:190px">学生管理系统</h1>
<div class="rg_layout">
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">用户登录</h3>
        <form action="/loginServlet" method="post">
            <div class="form-group">
                <label for="user">用户名：</label>
                <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
            </div>

            <div class="form-inline">
                <label for="vcode">验证码：</label>
                <input type="text" name="checkCode" class="form-control" id="verifycode" placeholder="请输入验证码"
                       style="width: 120px;"/>
                <a href="javascript:refreshCode()">
                    <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
                </a>
            </div>
            <hr/>
            <div class="form-group" style="text-align: left;">
                <input class="btn btn btn-primary" type="submit" value="登录">
            </div>
            <div class="rg_1">
                <%=request.getAttribute("login_error")==null?"":request.getAttribute("login_error")%>
                <%=request.getAttribute("check_error")==null?"":request.getAttribute("check_error")%>
            </div>
        </form>
        &lt;%&ndash;<!-- 出错显示的信息框 -->&ndash;%&gt;
        &lt;%&ndash;<div class="alert alert-warning alert-dismissible" role="alert">&ndash;%&gt;
        &lt;%&ndash;<button type="button" class="close" data-dismiss="alert" >&ndash;%&gt;
        &lt;%&ndash;<span>&times;</span></button>&ndash;%&gt;
        &lt;%&ndash;<strong>登录失败!</strong>&ndash;%&gt;
        &lt;%&ndash;</div>&ndash;%&gt;
    </div>
</div>
</div>--%>
<div id="container">
    <div id="output">
        <div class="containerT">
            <h1>用户登录</h1>
            <form class="form" id="entry_form"action="loginServlet" method="post">
                <input type="text" placeholder="用户名" name="username" id="entry_name" >
                <input type="password" placeholder="密码" name="password" id="entry_password">
                <input type="text" name="checkCode" name="checkCode" id="verifycode" placeholder="请输入验证码"/>
                <a href="javascript:refreshCode()">
                    <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
                </a>
                <p></p>
                <button type="submit" id="entry_btn">登录</button>
                <div id="prompt" class="prompt"></div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
</script>
</body>
</html>