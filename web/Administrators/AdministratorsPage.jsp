<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/8/27
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/StudentLeft.css">
    <script src="../js/jquery-1.9.1.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <title>管理员</title>
</head>
<body>
<div class="nav" <%--style="border:1px red solid"--%>>
    <div  class="nav-list" <%--style="border:1px red solid"--%>>

        <div class="nav-tit" id="personal">
            <a href="javascript:void(0)" target="main">
                <img src="../images/personal-msg.png" style="height: 30px" alt="">
                <span>个人信息</span>
            </a>
        </div>

        <div class="nav-tit" id="student">
            <a href="javascript:void(0)" target="main">
                <img src="../images/archives-msg.png" alt="">
                <span>学生管理</span>
            </a>
        </div>
        <div class="student-list" id="student-child"><%-- id= "student-child" 与上一个div id为student对应 --%>
            <ul>
                <li><a href="/FindStudentByPageServlet" target="main">学生列表</a></li>
                <li><a href="/FindStudentCourseByPageServlet" target="main">学生课程</a></li>
                <li><a href="javascript:void(0)" target="main">待定</a></li>
                <li><a href="javascript:void(0)" target="main">待定</a></li>
            </ul>
        </div>

        <div class="nav-tit" id="teacher">
            <a href="javascript:void(0)" target="main">
                <img src="../images/job-msg.png" alt="">
                <span>教师管理</span>
            </a>
        </div>
        <div class="student-list" id="teacher-child"><%-- id= "student-child" 与上一个div id为student对应 --%>
            <ul>
                <li><a href="/FindTeacherByPageServlet" target="main">教师授课</a></li>
                <li><a href="/FindTeacherCourseByPageServlet" target="main">教师课表</a></li>
                <li><a href="javascript:void(0)" target="main">待定</a></li>
                <li><a href="javascript:void(0)" target="main">待定</a></li>
            </ul>
        </div>

        <div class="nav-tit" id="course">
            <a href="javascript:void(0)" target="main">
                <img src="../images/job-change.png" alt="">
                <span>课程管理</span>
            </a>
        </div>
        <div class="student-list" id="course-child"><%-- id= "student-child" 与上一个div id为student对应 --%>
            <ul>
                <%--<li><a href="/FindSCourseByPageServlet" target="main">所有课程</a></li>--%>
                <li><a href="/FindSCourseByPageServlet" target="main">安排教室</a></li>
                <li><a href="/FindClassroomServlet" target="main">教室状态</a></li>
                <li><a href="javascript:void(0)" target="main">待定</a></li>
            </ul>
        </div>

        <div class="nav-tit">
            <a href="javascript:void(0)" target="main">
                <img src="../images/modify-password.png" alt="">
                <span>修改密码</span>
            </a>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('#personal').on('click',function(){
            $('#personal-child').fadeToggle(300);
        });
        let aLi = $('#personal-child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });

    $(document).ready(function(){
        $('#student').on('click',function(){
            $('#student-child').fadeToggle(300);
        });
        let aLi = $('#student-child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });

    $(document).ready(function(){
        $('#teacher').on('click',function(){
            $('#teacher-child').fadeToggle(300);
        });
        let aLi = $('#teacher-child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });

    $(document).ready(function(){
        $('#course').on('click',function(){
            $('#course-child').fadeToggle(300);
        });
        let aLi = $('#course-child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });


</script>
</body>
</html>
