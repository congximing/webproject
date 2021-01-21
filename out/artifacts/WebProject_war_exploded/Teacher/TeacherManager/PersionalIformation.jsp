<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/9/2
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/TeacherPersonalInfo.css">
    <title>个人信息</title>
</head>
<body>
<div class="container flgure">
    <div class="content">
        <ul class="clearfix info">
            <li class="active">基础信息</li>
            <li>联系方式</li>
        </ul>
        <div class="basic">
            <div class="select active">
                <p>
                    <span>编辑</span>
                    <span style="margin-left: 20px">保存</span>
                </p>
                <div class="left fl">
                    <div>
                        <label for="">姓名：</label>
                        <input type="text" value="${username}">
                    </div>
                    <div>
                        <label for="">性别：</label>
                        <input type="text" value="">
                    </div>
                    <div>
                        <label for="">出生日期：</label>
                        <input type="text" value="">
                    </div>
                    <div>
                        <label for="">身份证号：</label>
                        <input type="text" value="" class="id">
                    </div>
                </div>
                <div class="right fr">
                    <div>
                        <label for="">工号：</label>
                        <input type="number" value="">
                    </div>
                    <div>
                        <label for="">民族：</label>
                        <input type="text" value="">
                    </div>
                    <div class="label">
                        <label for="">政治面貌：</label>
                        <input type="text" value="">
                    </div>
                </div>

            </div>
            <div class="select ">
                <p>
                    <span>编辑</span>
                    <span style="margin-left: 20px">保存</span>
                </p>
                <div class="left fl">
                    <div>
                        <label for="">手机号码：</label>
                        <input type="number" value="">
                    </div>
                    <div>
                        <label for="">QQ号码：</label>
                        <input type="number" value="">
                    </div>
                </div>
                <div class="right fr">

                    <div>
                        <label for="">电子邮箱：</label>
                        <input type="email" value="">
                    </div>


                </div>

            </div>
            <div class="list list1 fl "><label>家庭地址</label> : <input type="text" value=""  name="username" style="width: 78px;"/> 省 <input type="text" value="" name="username" style="width: 98px;"/> 市 <input type="text" value=""  name="username" style="width: 98px;"/> 区/县 <input type="text" value="" name="username" style="width: 98px;"/> 街道 <input type="text" value="" name="username" style="width: 234px; margin: 30px 0 0 79px"/></div>
        </div>
    </div>
</div>
</div>
<script>
    $('.info li').click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        $('.basic .select').eq($("ul li").index(this)).addClass("active").siblings().removeClass("active")
    })
</script>
</body>
</html>
