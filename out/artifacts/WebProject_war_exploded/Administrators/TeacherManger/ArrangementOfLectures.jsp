<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
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
    <title>教师课表安排</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../../js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

</head>
                    <%--暂时不用--%>
<%--<form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">--%>
<%--<div class="row" style="border: 1px solid black">--%>
<%--<c:forEach items="${room}" var="room" varStatus="s">--%>
<%--<c:if test="${s.count <= 6 }">--%>
<%--<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place} ${s.count}</div></div></div>--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--<br>--%>
<%--<div class="row" style="border: 1px solid black">--%>
<%--<c:forEach items="${room}" var="room" varStatus="s">--%>
<%--<c:if test="${s.count >= 7 && s.count <= 12 }">--%>
<%--<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--<br>--%>
<%--<div class="row" style="border: 1px solid black">--%>
<%--<c:forEach items="${room}" var="room" varStatus="s">--%>
<%--<c:if test="${s.count >= 13 && s.count <= 18 }">--%>
<%--<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="border-right: 1px solid black;height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-2" style="height: 50px;padding: 0px"><div class="no"><div class="cen">${room.place}</div></div></div>&ndash;%&gt;--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<body>
<div class="container">
    <center><h3>安排教师授课</h3></center>
    <form id="form" action="${pageContext.request.contextPath}/AddTeacherCourseServlet" method="post"><%--修改未完成--%>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>课程代号</th>
                <th>名字</th>
                <th>课名</th>
                <th>上课时间</th>
                <th>地点</th>
                <th>课程类型</th>
                <th>学分</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${used}" var="user" varStatus="s">
                <input type="hidden" name="username" value="${user.username}">
                <tr>
                    <td>${user.number}</td>
                    <td>${user.name}</td>
                    <td>${user.coursename}</td>
                    <td>${user.time}</td>
                    <td>${user.place}</td>
                    <td>${user.type}</td>
                    <td>${user.credit}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser('${user.number}','${user.username}');">修改</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
