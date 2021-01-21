<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改课程</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;margin-top: 60px">
    <h3 style="text-align: center;">修改课表信息</h3>
    <form action="${pageContext.request.contextPath}/UpdataCourseServlet" method="post">
        <input type="hidden" name = "number" value="${course.number}">                      <%--隐藏域--%>
        <div class="form-group">
            <label for="coursename">课表名称：</label>
            <input type="text" class="form-control" id="coursename" name="coursename" value="${course.coursename}"      <%-- readonly="readonly"  不可点击--%>
                   placeholder="请输入课表名"/>
        </div>                                                                                 <%--如何设置默认？？？   --%>

        <div class="form-group">
            <label for="time">上课时间：</label>
            <input type="text" class="form-control" value="${course.time}" id="time" name="time" placeholder="请输入上课时间"/>
        </div>

        <div class="form-group">
            <label for="place">上课地点：</label>
            <input type="text" class="form-control" value="${course.place}" id="place" name="place" placeholder="请输入上课地点">
        </div>

        <div class="form-group">
            <label for="credit">学分：</label>
            <input type="text" class="form-control" value="${course.credit}" id="credit" name="credit" placeholder="请输入学分"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>