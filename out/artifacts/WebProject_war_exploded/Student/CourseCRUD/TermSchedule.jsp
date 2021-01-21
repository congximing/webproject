<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/9/2
  Time: 17:24
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
    <title>学期表</title>
    <style>
        .d2{
            margin-top: -10px;
            width: 1800px;
            height: 500px;
            border: 1px solid red;

        }
        li{
            margin-top: 35px;
        }
    </style>
</head>
<body>
<div style="border: 1px solid red;">
    <div class="d2" >

        <h3 style="margin-left: 500px;">查看学期课表</h5>

        <ul style="margin-left: 500px;margin-top: 20px">
            <li title="大一第一学期">
                <a id="0" href="" target="Student">大一第一学期</a>
            </li>

            <li title="大一第二学期">
                <a id="1" href="" target="Student">大一第二学期</a>
            </li>
            <li title="大二第一学期">
                <a id="2" href="/FindStudentCourseByPageServlet1?term=3" target="Student">大二第一学期</a>
            </li>

            <li title="大二第二学期">
                <a id="3" href="" target="Student">大二第二学期</a>
            </li>
            <li title="大三第一学期">
                <a id="4" href="" target="Student">大三第一学期</a>
            </li>
            <li title="大三第二学期">
                <a id="5" href="" target="Student">大三第二学期</a>
            </li>
            <li title="大四第一学期">
                <a id="6" href="/FindTermTable?term=7" target="Student">大四第一学期</a>
            </li>
            <li title="大四第二学期">
                <a id="7" href="/FindTermTable?term=8" target="Student">大四第二学期</a>
            </li>

        </ul>
    </div>
</div>
</body>
</html>
